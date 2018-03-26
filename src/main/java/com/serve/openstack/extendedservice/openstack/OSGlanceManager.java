package com.serve.openstack.extendedservice.openstack;

import com.serve.openstack.extendedservice.core.DownloadedImage;
import com.serve.openstack.extendedservice.openstack.payload.*;
import org.apache.commons.io.IOUtils;
import org.apache.commons.codec.binary.Hex;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.apache.commons.io.FileUtils;

import javax.ws.rs.core.MediaType;
import java.io.*;

/**
 * Created by mtillman on 3/5/15.
 */
public class OSGlanceManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(OSGlanceManager.class);

    private static final String images_file_url_frag    = "/%s/file"; // requires image UUID, support GET & PUT
    private static final String images_members_url_frag = "/%s/members";
    private static final String dir_path                = "/tmp/ImagePromotionRestAPI/%s";
    private static final String file_path               = dir_path + "/%s.qcow2";

    // on dev there is ubuntu image with ID d31b4c3c-d9be-441c-a71e-e90bb6b8e356

    /**
     * Download raw virtual image file and store it in temporary directory.
     *
     * @param uuid
     * @param tokenID
     * @param baseUrl base url should include version and resource i.g. http://openstack.com/v2/images
     * @return boolean true when download is successful
     */
    public DownloadedImage downloadImage(String uuid, String tokenID, String baseUrl) {

        // TODO: check if a request is made already with the same UUID
        // Just testing

        DownloadedImage downloadedImage = new DownloadedImage();
        downloadedImage.setImageUUID(uuid);
        JSONObject imageMetaData = getImageMetadata(uuid, tokenID, baseUrl);

        try {
            // TODO: create a lock file to prevent the same request from being executed.
            String checksum = imageMetaData.getString("checksum");
            String resourceUri = baseUrl + String.format(images_file_url_frag, uuid);
            downloadedImage.setRemoteURI(resourceUri);
            WebResource webResource = OSRestClient
                    .getWebResource(resourceUri);

            LOGGER.info("Downloading image("+uuid+") begins.");
            ClientResponse response = webResource.accept("application/json")
                    .type("application/json")
                    .header("X-Auth-Token", tokenID)
                    .get(ClientResponse.class);

            InputStream in = response.getEntityInputStream();
            if (in != null) {

                new File(String.format(dir_path, uuid)).mkdirs();
                String fullFilePath = String.format(file_path, uuid, uuid);
                File download = new File(fullFilePath);

                downloadedImage.setFullImagePath(fullFilePath);
                if (download.exists()) {
                    String md5 = getMD5Checksum(download);
                    downloadedImage.setMd5Checksum(md5);
                    // file exists already so checking md5 checksum
                    if (checksum.equals(md5)) {
                        // checksum comparison result positive, so will just use it.
                        downloadedImage.setDownloaded(true);
                        return downloadedImage;
                    }
                }

                OutputStream out = new FileOutputStream(download);

                byte[] buffer = new byte[8 * 1024];
                int bytesRead;
                while ((bytesRead = in.read(buffer)) != -1 ) {
                    out.write(buffer, 0, bytesRead);
                }

                IOUtils.closeQuietly(in);
                IOUtils.closeQuietly(out);

                if (downloadedImage.getMd5Checksum() == null) {
                    downloadedImage.setMd5Checksum(getMD5Checksum(download));
                }

                // FileUtils.copyInputStreamToFile(in, download);
                downloadedImage.setDownloaded(true);
                LOGGER.info("Successfully downloaded image file to /tmp/ImagePromotionRestAPI/" + uuid + "/" + uuid + ".gcow2");
                return downloadedImage;

            }
        } catch (FileNotFoundException e) {

            LOGGER.error("File could not be created : {}", e.getMessage());

        } catch (IOException e) {

            LOGGER.error("Error occurred while reading the input stream from Openstack : {}", e.getMessage());

        } catch (JSONException e) {

            LOGGER.error("Tried to get checksum info from meta data, but failed with {}", e.getMessage());
        }

        return downloadedImage;

    }

    private String getMD5Checksum(File file) {

        int byteArraySize = 2048;
        InputStream in;
        try {
            in = new FileInputStream(file);
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.reset();
            byte[] bytes = new byte[byteArraySize];
            int numBytes;
            while ((numBytes = in.read(bytes)) != -1) {
                md.update(bytes, 0, numBytes);
            }
            byte[] digest = md.digest();
            String result = new String(Hex.encodeHex(digest));
            IOUtils.closeQuietly(in);

            return result;

        } catch (NoSuchAlgorithmException e) {

            LOGGER.error("MD5 Algorithm does not exist: {}", e.getMessage());

        } catch (IOException e) {
            LOGGER.error("Error during conversion of File to FileInputStream: {}", e.getMessage());
        }

        return null;
    }

    public JSONObject getImageMetadata(String uuid, String tokenID, String baseUrl) {

        WebResource webResource = OSRestClient
                .getWebResource(baseUrl + "/" + uuid);

        ClientResponse response = webResource.accept("application/json")
                .type("application/json")
                .header("X-Auth-Token", tokenID)
                .get(ClientResponse.class);

        if (response.getStatus() == 200) {

            String jsonStr = response.getEntity(String.class);

            try {
                JSONObject json = new JSONObject(jsonStr);
                LOGGER.info("Image data received for {}", uuid);
                return json;
            } catch (JSONException e) {

            }

        } else {
            LOGGER.error("Could not retrieve Image meta data for {}", uuid);
        }
        return null;
    }

    public JSONObject createImage(String targetUrl, String imageName, String tokenID) {

        CreateImagePayload payload = new CreateImagePayload();
        payload.setName(imageName);
        payload.setVisibility("private");
        payload.setContainerFormat("bare");
        payload.setDiskFormat("qcow2");

        ClientResponse response = OSRestClient.httpPostRequest(
                tokenID
                , payload
                , targetUrl
                , OSRestClient.HEADER_APPLICATION_JSON
        );

        String responsePayload = response.getEntity(String.class);
        if (response.getStatus() == 201) {
            // successfully created.
            try {

                JSONObject responsePayloadJson = new JSONObject(responsePayload);
                LOGGER.info("Image was created with meta data.");
                return responsePayloadJson;

            } catch (JSONException e) {
                LOGGER.error("Parsing error while converting JSON string into JSON Object");
            }

        } else {
            LOGGER.error("Image could not be created and OS reason is : {}", response.toString());
        }

        return null;
    }

    public boolean uploadImage(String srcUUID, String tarUUID, String tokenID, String baseUrl) {

        String fullFilePath = String.format(file_path, srcUUID, srcUUID);
        File sourceFile = new File(fullFilePath);

        if (fullFilePath != null && sourceFile.exists()) {
            // TODO: checksum against img in OS to make sure to upload a valid file only to OS

            try {
                WebResource webResource = OSRestClient
                        .getWebResource(baseUrl + String.format(images_file_url_frag, tarUUID));

                // InputStream in = new FileInputStream(sourceFile);
                ByteArrayInputStream vmFileInputStream = new ByteArrayInputStream(FileUtils.readFileToByteArray(sourceFile));

                // String contentDisposition = "attachment; filename=\"" + sourceFile.getName() + "\"";
                LOGGER.info("Uploading image("+tarUUID+") begins.");
                ClientResponse response = webResource.accept("application/json")
                        .type(MediaType.APPLICATION_OCTET_STREAM)
                        .header("X-Auth-Token", tokenID)
                //        .header("Content-Disposition", contentDisposition)
                        .put(ClientResponse.class, vmFileInputStream);
                if (response.getStatus() > 300) {
                    LOGGER.error("Status code : {}", response.getStatus());
                } else {
                    LOGGER.info("Successfully image has been uploaded : {}", response.toString());
                    return true;
                }
            } catch (IOException e) {
                LOGGER.error("Error occurred while uploading the image : {}", e.getMessage());
            }
        }

        return false;
    }

    public boolean addMember(String imgUUID, String tokenID, String targetTenantID, String baseUrl) {

        String postUrl = baseUrl + String.format(images_members_url_frag, imgUUID);
       /* LOGGER.info("POST url is " + postUrl);*/
        /*MembersPost postPayload = new MembersPost();
        postPayload.setMember(targetTenantID);

        LOGGER.info("Adding tenant ID " + targetTenantID + " to image " + imgUUID);
        ClientResponse postResponse = OSRestClient.httpPostRequest(
                tokenID
                , postPayload
                , postUrl
                , OSRestClient.HEADER_APPLICATION_JSON);*/

        ClientResponse postResponse = addMembertoImage(imgUUID, tokenID,targetTenantID,postUrl);
        LOGGER.info("OS Response for adding member is: {}", postResponse.getEntity(String.class));

        /*String putUrl = postUrl + "/" + targetTenantID;
        WebResource webResource = OSRestClient.getWebResource(putUrl);
        MemberPut putPayload = new MemberPut();
        putPayload.setStatus("accepted");

        LOGGER.info("PUT url is " + putUrl);
        LOGGER.info("Accepting member request for the image " + imgUUID);
        ClientResponse putResponse = webResource.accept(OSRestClient.HEADER_APPLICATION_JSON)
                .type(OSRestClient.HEADER_APPLICATION_JSON)
                .header("X-Auth-Token", tokenID)
                .put(ClientResponse.class, putPayload);*/

        //ClientResponse putResponse = setMemberImageStatus(imgUUID, tokenID, targetTenantID, baseUrl);

        //LOGGER.info("OS Response for accepting member is: {}", putResponse.getEntity(String.class));

        /*if (postResponse.getStatus() == 200 && putResponse.getStatus() == 200) {
            return true;
        }*/

        if (postResponse.getStatus() == 200 ) {
            return true;
        }

        return false;
    }

    public ClientResponse addMembertoImage(String imgUUID, String tokenID, String targetTenantID, String postUrl) {
        //String postUrl = baseUrl + String.format(images_members_url_frag, imgUUID);

        LOGGER.info("POST url is " + postUrl);
        MembersPost postPayload = new MembersPost();
        postPayload.setMember(targetTenantID);

        LOGGER.info("Adding tenant ID " + targetTenantID + " to image " + imgUUID);
        ClientResponse postResponse = OSRestClient.httpPostRequest(
                tokenID
                , postPayload
                , postUrl
                , OSRestClient.HEADER_APPLICATION_JSON);
        return  postResponse;
    }

    /*public ClientResponse setMemberImageStatus(String imgUUID, String tokenID, String targetTenantID, String postUrl) {
        //String postUrl = baseUrl + String.format(images_members_url_frag, imgUUID);
        LOGGER.info("POST url is " + postUrl);

        String putUrl = postUrl + "/" + targetTenantID;
        WebResource webResource = OSRestClient.getWebResource(putUrl);
        MemberPut putPayload = new MemberPut();
        putPayload.setStatus("accepted");

        LOGGER.info("PUT url is " + putUrl);
        LOGGER.info("Accepting member request for the image " + imgUUID);
        ClientResponse putResponse = webResource.accept(OSRestClient.HEADER_APPLICATION_JSON)
                .type(OSRestClient.HEADER_APPLICATION_JSON)
                .header("X-Auth-Token", tokenID)
                .put(ClientResponse.class, putPayload);
        return putResponse;
    }*/

    }
