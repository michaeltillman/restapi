package com.serve.openstack.extendedservice.resources;

import com.serve.openstack.extendedservice.core.PromotionRequestParameter;
import com.serve.openstack.extendedservice.core.DownloadedImage;
import com.google.common.base.Optional;
import com.codahale.metrics.annotation.Timed;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.serve.openstack.extendedservice.core.ShareImageRequestParameters;
import com.serve.openstack.extendedservice.openstack.OSGlanceManager;
import com.serve.openstack.extendedservice.openstack.OSLoginManager;

import com.sun.jersey.api.client.ClientResponse;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by mtillman on 3/2/15.
 */

@Path("/image/v1")
@Produces(MediaType.APPLICATION_JSON)
public class PromotionResourceV1 {

    private static final Logger LOGGER = LoggerFactory.getLogger(PromotionResourceV1.class);


    // keystone
    // private static final String url = "http://10.115.228.58:5000/v2.0/tokens";
    // private static final String user = "admin";
    // private static final String pw = "48f2c6c0372745f8"; // admin

    // private static final String user = "demo";
    // private static final String pw = "85a81478b7a249c3"; // demo


    private final String sourceUserName;
    private final String sourcePassword;
    private final String sourceKeyUrl;
    private final String sourceGlanceUrl;
    private final String sourceTenant;
    private final String targetUserName;
    private final String targetPassword;
    private final String targetKeyUrl;
    private final String targetGlanceUrl;
    private final String targetTenantsUrl;
    private String targetTenant;

    public PromotionResourceV1(String srcUser, String srcPassword, String srcKeyUrl, String srcGlanceUrl,
                               String sourceTenant, String targetUser, String targetPassword,
                               String targetKeyUrl, String targetGlanceUrl, String targetTenantsUrl) {
        this.sourceUserName = srcUser;
        this.sourcePassword = srcPassword;
        this.sourceKeyUrl = srcKeyUrl;
        this.sourceGlanceUrl = srcGlanceUrl;
        this.sourceTenant = sourceTenant;
        this.targetUserName = targetUser;
        this.targetPassword = targetPassword;
        this.targetKeyUrl = targetKeyUrl;
        this.targetGlanceUrl = targetGlanceUrl;
        this.targetTenantsUrl = targetTenantsUrl;
    }

    @GET
    @Timed
    @Path("/copy")
    public PromotionRequestParameter info(@QueryParam("name") Optional<String> name) {
        final String value = String.format(sourceUserName, name.or("Hi World."));
        return new PromotionRequestParameter(value);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Timed
    @Path("/copy")
    public Response promote(PromotionRequestParameter params) {

        String devImgID = params.getDevImageID();
        String osTarTenant = params.getOSTargetTenant();

        OSLoginManager srcLogin = new OSLoginManager();
        OSLoginManager tarLogin = new OSLoginManager();

        StatusMessage message = new StatusMessage();
        // targetUsername is usually the same as target tenant 'cause name of tenant is normally derived from user name
        if (srcLogin.authenticate(sourceKeyUrl, sourceUserName, sourcePassword, sourceTenant)
                && tarLogin.authenticate(targetKeyUrl, targetUserName, targetPassword, targetUserName)) {
            LOGGER.info("Authenticated successfully!");
            try {
                OSGlanceManager glance = new OSGlanceManager();

                String srcToken = srcLogin.getAuthenticatedToken(srcLogin.getJSONResponse());
                String tarToken = tarLogin.getAuthenticatedToken(tarLogin.getJSONResponse());

                // glance to download and then upload
                DownloadedImage downloadedImage = glance.downloadImage(
                        devImgID,
                        srcToken,
                        sourceGlanceUrl);
                message.setSrcImageLocalPath(downloadedImage.getFullImagePath());
                message.setSrcImageDownloadStatus(downloadedImage.isDownloaded());
                JSONObject imageMetaData = glance.getImageMetadata(
                        devImgID,
                        srcToken,
                        sourceGlanceUrl);

                // create an image using the same name as the source image
                String srcImageName = imageMetaData.getString("name");

                JSONObject imgResponseJson = glance.createImage(targetGlanceUrl, srcImageName, tarToken);
                if (imgResponseJson != null) {
                    String targetImgID = imgResponseJson.getString("id");
                    message.setNewImageId(targetImgID);

                    boolean uploaded = glance.uploadImage(
                            devImgID,
                            targetImgID,
                            tarToken,
                            targetGlanceUrl);
                    if (!uploaded) {
                        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("{\"message\":\"failed to upload\"}").build();
                    }
                }

                JSONArray tenants = tarLogin.getTenants(this.targetTenantsUrl, tarToken);
                String tenantId = tarLogin.getMatchedTenantID(tenants, osTarTenant);
                // String imgUUID, String tokenID, String targetTenantID, String baseUrl
                glance.addMember(
                        imgResponseJson.getString("id")
                        , tarToken
                        , tenantId
                        , targetGlanceUrl);
                message.setRequestStatus("Image is copied successfully.");
            } catch (JSONException e) {
                message.setRequestStatus("JSON Parsing error occurred with this message: " + e.getMessage());
                LOGGER.error("Parsing error: {}", e.getMessage());
            }
        } else {
            LOGGER.info("Was not authenticated");
            message.setRequestStatus("Authentication Failed.");
            return Response.status(Response.Status.FORBIDDEN).entity(message).build();
        }

        return Response.status(Response.Status.OK).entity(message).build();

    }

    private class StatusMessage {

        private String newImageId;
        private boolean srcImageDownloaded;
        private String srcImageLocalPath;
        private String requestStatus;

        public String getRequestStatus() { return requestStatus; }
        public void setRequestStatus(String requestStatus) { this.requestStatus = requestStatus; }

        public String getNewImageId() { return newImageId; }
        public void setNewImageId(String newImageId) { this.newImageId = newImageId; }

        public boolean isSrcImageDownloaded() { return srcImageDownloaded; };
        public void setSrcImageDownloadStatus(boolean srcImageDownloaded) {
            this.srcImageDownloaded = srcImageDownloaded;
        }

        public String getSrcImageLocalPath() { return srcImageLocalPath; }
        public void setSrcImageLocalPath(String srcImageLocalPath) {
            this.srcImageLocalPath = srcImageLocalPath;
        }

    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Timed
    @Path("/shareimage")
    public String shareImagewithTenant(ShareImageRequestParameters params)
    {
        String imageId = params.getImageid();
        String tenantId = params.getTenantid();
        String images_members_v2_url_frag = "/v2/images/%s/members";


        OSLoginManager srcLogin = new OSLoginManager();

        if (srcLogin.authenticate(sourceKeyUrl, sourceUserName, sourcePassword, sourceTenant)) {
            OSGlanceManager glance = new OSGlanceManager();
            LOGGER.info("Authenticated successfully!");

            String srcToken = srcLogin.getAuthenticatedToken(srcLogin.getJSONResponse());
            String imageServiceUrl = srcLogin.getGlanceImageServiceUrl();
            imageServiceUrl = imageServiceUrl + String.format(images_members_v2_url_frag, imageId);

            ClientResponse response = glance.addMembertoImage(imageId, srcToken, tenantId, imageServiceUrl);

            String jsonStr = response.getEntity(String.class);
            if (response.getStatus() == 200) {
                try {
                    JSONObject json = new JSONObject(jsonStr);
                    //LOGGER.info("Image data received for {}", imageId);
                    return json.toString();
                } catch (Exception e) {
                    LOGGER.error("Parsing error: {}", e.getMessage());
                }

            }else {
                   LOGGER.error("Image meta-data could not be set and OS reason is : {}", response.toString());
            }
       }

        return null;
        }

}
