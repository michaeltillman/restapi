package com.serve.openstack.extendedservice.resources;

import com.codahale.metrics.annotation.Timed;
import com.serve.openstack.extendedservice.core.OpenStackSetImageMetaDataRequest;
import com.serve.openstack.extendedservice.core.codeCleanRequest;
import com.serve.openstack.extendedservice.openstack.OSGlanceManager;
import com.serve.openstack.extendedservice.openstack.OSImageMetaDataManager;
import com.serve.openstack.extendedservice.openstack.OSLoginManager;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;

/**
 * Created by bselv2 on 8/12/2015.
 */

@Path("/image/v2")
@Produces(MediaType.APPLICATION_JSON)
public class ImageResourceV1 {

    private static final Logger LOGGER = LoggerFactory.getLogger(ImageResourceV1.class);
    private static final String images_members_v2_url_frag = "/v2/images";
    private static final String images_get_list_url_frag    = "/images";

    private final String tenantUserName;
    private final String tenantPassword;
    private final String sourcComputeUrl;
    private final String tenantName;

    public ImageResourceV1(String userName, String password, String computeUrl, String tenantName) {
        this.tenantUserName = userName;
        this.tenantPassword = password;
        this.sourcComputeUrl = computeUrl;
        this.tenantName = tenantName;
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Timed
    @Path("/metadata/{tenantname}/{imageid}/{metadatakeyname}")
    public String getMetaData(@PathParam("tenantname") String tenantname, @PathParam("imageid") String imageid, @PathParam("metadatakeyname") String metadatakeyname) {

        String metaDataKeyName = metadatakeyname;
        String imageId = imageid ;
        String tenantName = tenantname;

        OSLoginManager srcLogin = new OSLoginManager();
        StatusMessage message = new StatusMessage();

        if (srcLogin.authenticate(sourcComputeUrl, tenantUserName, tenantPassword, tenantName)) {
            try {
                OSImageMetaDataManager compute = new OSImageMetaDataManager();

                String srcToken = srcLogin.getAuthenticatedToken(srcLogin.getJSONResponse());
                String ComputeServiceUrl = srcLogin.getComputeServiceUrl();
                JSONObject response = compute.getImageMetaData(ComputeServiceUrl, imageId, metaDataKeyName, srcToken);
                if (response != null) {
                    return  response.toString();
                    //String metaDataKeyValue = response.getString(metaDataKeyName);
                    //message.setMetaKeyValue(metaDataKeyValue.toString());
                }

                //return Response.status(Response.Status.OK).entity(message).build();

            } catch (Exception ex) {
                message.setRequestStatus("JSON Parsing error occurred with this message: " + ex.getMessage());
                LOGGER.error("Parsing error: {}", ex.getMessage());
            }
        }
            return null;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Timed
    @Path("/codeclean")
    public Response codeClean(codeCleanRequest params){
        OSLoginManager srcLogin = new OSLoginManager();
        String imageName = params.getImageName();
        String tenantName = params.getTenantname();
        String metaDataKeyName = "promoted";
        String metaDataKeyValue = "true";
        String targetTenantName = params.getTargetTenantName();

        StatusMessage message = new StatusMessage();

        if (srcLogin.authenticate(sourcComputeUrl, tenantUserName, tenantPassword, tenantName)) {
            LOGGER.info("Authenticated successfully!");

            try{

                OSImageMetaDataManager compute = new OSImageMetaDataManager();

                String srcToken = srcLogin.getAuthenticatedToken(srcLogin.getJSONResponse());
                String ComputeServiceBaseUrl = srcLogin.getComputeServiceUrl();
                String ComputeServiceUrl = ComputeServiceBaseUrl  + images_get_list_url_frag;
                String GlanceServiceUrl = srcLogin.getGlanceImageServiceUrl();

                String imageId = compute.getImageId(imageName, ComputeServiceUrl, srcToken);
                Boolean response = compute.setImageProtected(GlanceServiceUrl, imageId, srcToken);

                if (!response) {
                    return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("{\"message\":\"failed to set image as protected\"}").build();
                }

                response = compute.setImageMetaData(ComputeServiceBaseUrl, imageId, metaDataKeyName, metaDataKeyValue, srcToken);

                if (!response) {
                    return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("{\"message\":\"failed to set metadata\"}").build();
                }

                OSLoginManager tarLogin  = new OSLoginManager();

                if (tarLogin.authenticate(sourcComputeUrl, tenantUserName, tenantPassword, targetTenantName)) {
                    String imageServiceUrl = srcLogin.getGlanceImageServiceUrl();
                    String tenantId =tarLogin.getTenantID(tarLogin.getJSONResponse());

                    imageServiceUrl = imageServiceUrl + images_members_v2_url_frag;
                    OSGlanceManager glance = new OSGlanceManager();

                    Boolean addMemberStatus = glance.addMember(
                            imageId
                            , srcToken
                            , tenantId
                            , imageServiceUrl);

                    if(!addMemberStatus ){
                        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("{\"message\":\"failed to set metadata\"}").build();
                    }

                }else {
                    LOGGER.info("PROD tenant aas not authenticated");
                    message.setRequestStatus("Authentication Failed.");
                    return Response.status(Response.Status.FORBIDDEN).entity(message).build();
                }

                message.setRequestStatus("Code clean process completed successfully.");

            } catch (Exception e) {
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

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Timed
    @Path("/setmetadata")
    public Response setMetaData(OpenStackSetImageMetaDataRequest params){

        OSLoginManager srcLogin = new OSLoginManager();
        String imageName = params.getImageName();
        String tenantName = params.getTenantname();
        String keyName = params.getKeyName();
        String keyValue = params.getKeyValue();

        if (srcLogin.authenticate(sourcComputeUrl, tenantUserName, tenantPassword, tenantName)) {
            OSImageMetaDataManager compute = new OSImageMetaDataManager();

            String srcToken = srcLogin.getAuthenticatedToken(srcLogin.getJSONResponse());
            String ComputeServiceBaseUrl = srcLogin.getComputeServiceUrl();
            String ComputeServiceUrl = ComputeServiceBaseUrl  + images_get_list_url_frag;

            String imageId = compute.getImageId(imageName, ComputeServiceUrl, srcToken);

            Boolean response = compute.setImageMetaData(ComputeServiceBaseUrl, imageId, keyName, keyValue, srcToken);

            if (!response) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("{\"message\":\"failed to set metadata\"}").build();
            }

            return Response.status(Response.Status.OK).entity("{\"message\":\"succesfully set metadata\"}").build();
        }
        return  null;
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Timed
    @Path("/jvmproperties")
    public String getJVMProperties() {
        String trustStore = System.getProperty("javax.net.ssl.trustStore");
        return "trustStore-" + trustStore;
    }

    private class StatusMessage {

        private String requestStatus;

        public String getRequestStatus() { return requestStatus; }
        public void setRequestStatus(String requestStatus) { this.requestStatus = requestStatus; }

    }
}
