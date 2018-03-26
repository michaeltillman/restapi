package com.serve.openstack.extendedservice.openstack;

import com.serve.openstack.extendedservice.core.OpenStackSetImageMetaDataRequest;
import com.serve.openstack.extendedservice.core.protectImageRequest;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

/**
 * Created by bselv2 on 8/12/2015.
 */
public class OSImageMetaDataManager {
    private static final String images_get_metadata_url_frag    = "/images/%s/metadata/%s"; // requires image UUID, support GET & PUT
    private static final String images_set_metadata_url_frag    = "/images/%s/metadata"; // requires image UUID, support GET & PUT
    private static final String images_set_protected_url_frag   = "/v2/images/%s"; // requires image UUID, support GET & PUT

    private static final Logger LOGGER = LoggerFactory.getLogger(OSImageMetaDataManager.class);

    //set image protected using jersey
    public Boolean setImageProtected2(String baseUrl,String imageId, String authToken) {
        String resourceUri = baseUrl + String.format(images_set_protected_url_frag, imageId);

        WebResource webResource = OSRestClient.getWebResource(resourceUri);
        LOGGER.info("Setting image protected(" + imageId + ") begins.");
/*
        protectImageRequest payload = new protectImageRequest();
        payload.setOp("replace");
        payload.setPath("/protected");
        payload.setValue(true);
        */

        JSONObject prop = new JSONObject();

        try{

            prop.put("op", "replace");
            prop.put("path", "/protected");
            prop.put("value", true);

        }catch (JSONException e ){
            LOGGER.error("Issue while creating JSON Object: {}", e.toString());
        }


        JSONArray payload = new JSONArray();
        payload.put(prop);


        ClientResponse response = OSRestClient.httpPostRequest(
                authToken
                , payload
                , resourceUri
                , OSRestClient.HEADER_APPLICATION_JSON_PATCH
        );
        //String responsePayload = response.getEntity(String.class);
        if (response.getStatus() == 200) {
            return true;
        } else {
            LOGGER.error("Image could not be set as protected OS reason is : {}", response.toString());
        }
        return false;
    }

    //set image protected using apache httpclient
    public Boolean setImageProtected(String baseUrl,String imageId, String authToken) {
        String resourceUri = baseUrl + String.format(images_set_protected_url_frag, imageId);

        CloseableHttpClient httpClient = null;
        HttpPatch httpPatch = null;
        CloseableHttpResponse response = null;

        try {
            httpClient = HttpClients.createDefault();
            httpPatch = new HttpPatch(resourceUri);


            httpPatch.setHeader("Content-Type", "application/openstack-images-v2.1-json-patch");
            httpPatch.setHeader("X-Auth-Token", authToken);
            StringEntity params = new StringEntity("[{\"op\": \"replace\",\"path\": \"/protected\",\"value\": true}]");
            params.setContentType("application/openstack-images-v2.1-json-patch");
            httpPatch.setEntity(params);

            response = httpClient.execute(httpPatch);

            int responseCode = response.getStatusLine().getStatusCode();
            //String responsePayload = response.getEntity(String.class);
            if (responseCode == 200) {
                return true;
            } else {
                LOGGER.error("Image could not be set as protected OS reason is : {}", response.getStatusLine());
            }
        }catch (Exception ex){
            LOGGER.error("Exception occurred while setting image as protected {}", ex.toString());
        }finally {
            try{
                response.close();
                httpClient.close();
            }catch (Exception ex) {
                LOGGER.error("Exception occurred while setting image as protected {}", ex.toString());
            }
        }
        return false;
    }

    public Boolean setImageMetaData(String baseUrl,String imageId, String keyName, String keyValue, String authToken){
        String resourceUri = baseUrl + String.format(images_set_metadata_url_frag, imageId, keyName);

        WebResource webResource = OSRestClient.getWebResource(resourceUri);
        LOGGER.info("Setting image meta-data(" + imageId + ") begins.");

        OpenStackSetImageMetaDataRequest payload = new OpenStackSetImageMetaDataRequest();
        payload.setMetadata(keyName, keyValue);

        ClientResponse response = OSRestClient.httpPostRequest(
                authToken
                , payload
                , resourceUri
                , OSRestClient.HEADER_APPLICATION_JSON
        );
        //String responsePayload = response.getEntity(String.class);
        if (response.getStatus() == 200) {
           return true;
        } else {
            LOGGER.error("Image meta-data could not be set and OS reason is : {}", response.toString());
        }
        return false;

    }

    public JSONObject  getImageMetaData(String baseUrl, String imageId,String metaKey, String authToken){
        //String baseUrl = "http://openstack.tpatest.servevirtual.net:8774/v2/0ccffcafddc64202be2a83dfa04456f5/images/26d569ee-7fdd-4705-b4a9-ebdfa1663709/metadata/cloud_environment";
        String resourceUri = baseUrl + String.format(images_get_metadata_url_frag, imageId, metaKey);
        //downloadedImage.setRemoteURI(resourceUri);
        WebResource webResource = OSRestClient.getWebResource(resourceUri);

        LOGGER.info("Getting image meta-data("+ imageId +") begins.");

        ClientResponse response = webResource.accept("application/json")
                .type("application/json")
                .header("X-Auth-Token", authToken)
                .get(ClientResponse.class);

        if (response.getStatus() == 200) {

            String jsonStr = response.getEntity(String.class);

            try {
                JSONObject json = new JSONObject(jsonStr);
                LOGGER.info("Image data received for {}", imageId);
                return json;
            } catch (JSONException e) {

            }

        } else {
            LOGGER.error("Could not retrieve Image meta data for {}", imageId);
        }
        return null;
    }

    public String getImageId(String imageName, String ComputeServiceUrl, String tokenId)
    {
        JSONArray images = getImages(ComputeServiceUrl, tokenId);
        try {
            for (int i = 0, size = images.length(); i < size; i++) {
                JSONObject image = images.getJSONObject(i);
                if (image.getString("name").equals(imageName)) {
                    String imageId = image.getString("id");
                    return imageId;
                }
            }
        } catch (JSONException e) {
            LOGGER.error("Can't find Image id for the specified image id: {}", e.getMessage());
        }
        return null;
    }

    public JSONArray getImages(String url, String tokenId) {
        try {
            ClientResponse response = OSRestClient.httpGetRequest(
                    tokenId
                    , url
                    , OSRestClient.HEADER_APPLICATION_JSON
                    , OSRestClient.HEADER_APPLICATION_JSON);
            String output = response.getEntity(String.class);
            LOGGER.info("JSON response from OS: {}" , output);

            JSONObject jsonObj = new JSONObject(output);
            JSONArray jsonAry = jsonObj.getJSONArray("images");

            return jsonAry;

        } catch (JSONException e) {
            LOGGER.error("could not convert to json Object and its error message: {}", e.getMessage());
        }
        return null;
    }


}
