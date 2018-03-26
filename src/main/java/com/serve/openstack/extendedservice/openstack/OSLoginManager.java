package com.serve.openstack.extendedservice.openstack;

import com.serve.openstack.extendedservice.openstack.payload.Auth;
import com.serve.openstack.extendedservice.openstack.payload.OSKeystoneRequestPayload;
import com.serve.openstack.extendedservice.openstack.payload.PasswordCredentials;
import com.sun.jersey.api.client.ClientResponse;
import com.serve.openstack.extendedservice.openstack.domain.keystone.Response;
import org.codehaus.jettison.json.JSONObject;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by mtillman on 3/3/15.
 *
 * Stores keystone authenticated token.
 * update for hook
 */

public class OSLoginManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(OSLoginManager.class);
    public static final String NOVA_SERVICE = "nova";
    public static final String NEUTRON_SERVICE = "neutron";
    public static final String CINDER_V2_SERVICE = "cinderv2";
    public static final String GLANCE_SERVICE = "glance";
    public static final String CEILOMETER_SERVICE = "ceilometer";
    public static final String HEAT_CFN_SERVICE = "heat-cfn";
    public static final String CINDER_SERVICE = "cinder";
    public static final String MURANO_SERVICE = "muranoapi";
    public static final String NOVA_EC2_SERVICE = "nova_ec2";
    public static final String KEYSTONE_SERVICE = "keystone";

    private JSONObject osKeystoneResponseInJson;
    private ClientResponse osKeystoneResponse;
    private Response keystoneResponse;

    public JSONObject getJSONResponse() {
        return osKeystoneResponseInJson;
    }

    public boolean authenticate(String url, String user, String pw, String tenant) {
        try {
            OSKeystoneRequestPayload osRequestPayload = new OSKeystoneRequestPayload();

            // first checking into a branch feature/authentication
            // set pojo object, later to be parsed into json by jackson
            Auth auth = new Auth();
            PasswordCredentials passwordCredentials = new PasswordCredentials();
            passwordCredentials.setPassword(pw);
            passwordCredentials.setUsername(user);
            auth.setPasswordCredentials(passwordCredentials);
            auth.setTenantName(tenant);
            osRequestPayload.setAuth(auth);

            osKeystoneResponse = OSRestClient.httpPostRequest(osRequestPayload, url
                    , OSRestClient.HEADER_APPLICATION_JSON
                    , OSRestClient.HEADER_APPLICATION_JSON);

            if (osKeystoneResponse != null) {
                String output = osKeystoneResponse.getEntity(String.class);
                osKeystoneResponseInJson = new JSONObject(output);
                LOGGER.info("Authenticated successfully: {}", osKeystoneResponse.toString());

                return true;

            }
        } catch (RuntimeException e) {
            LOGGER.error("Authentication error: {}", e.getMessage());
        } catch (JSONException e) {
            LOGGER.error("Error while parsing tokens response from OS: {}", e.getMessage());
        }
        return false;
    }

    public String getRespMessage() {

        String output = osKeystoneResponseInJson.toString();
        return output;

    }

    // TODO: replace fixed glance image service url from configuration.yaml file with the value from token response.
    public String getGlanceImageServiceUrl() {

        if (osKeystoneResponseInJson != null) {
            try {

                JSONArray serviceCatalog = osKeystoneResponseInJson.getJSONObject("access").getJSONArray("serviceCatalog");
                return getCatalogEndpoint(GLANCE_SERVICE, serviceCatalog);
            } catch (JSONException e) {
                LOGGER.error("Maybe specified json data does not exist?: {}", e.getMessage());
            }
        }
        return null;
    }

    public String getComputeServiceUrl() {

        if (osKeystoneResponseInJson != null) {
            try {

                JSONArray serviceCatalog = osKeystoneResponseInJson.getJSONObject("access").getJSONArray("serviceCatalog");
                return getCatalogEndpoint(NOVA_SERVICE, serviceCatalog);
            } catch (JSONException e) {
                LOGGER.error("Maybe specified json data does not exist?: {}", e.getMessage());
            }
        }
        return null;
    }

    // TODO: create additional methods to get other services

    public String getCatalogEndpoint(String service, JSONArray serviceCatalog) {
        try {
            for (int i = 0, size = serviceCatalog.length(); i < size; i++) {
                JSONObject serviceObj = serviceCatalog.getJSONObject(i);
                if (serviceObj.getString("name").equals(service)) {
                    JSONObject endpoint = serviceObj.getJSONArray("endpoints").getJSONObject(0);
                    String url = endpoint.getString("publicURL");
                    return url;
                }
            }
        } catch (JSONException e) {
            LOGGER.error("Maybe specified json data does not exist?: {}", e.getMessage());
        }
        return null;
    }


    public String getTenantID(JSONObject jsonResponse) {
        if (jsonResponse != null) {
            try {
                String data = jsonResponse.getJSONObject("access")
                        .getJSONObject("token")
                        .getJSONObject("tenant")
                        .getString("id");

                return data;
            } catch (JSONException e) {
                LOGGER.error("Maybe JSON data is bad?: {}", jsonResponse.toString());
            }
        }

        return null;
    }


    public String getAuthenticatedToken(JSONObject jsonResponse) {

        if (jsonResponse != null) {
            try {
                String data = jsonResponse.getJSONObject("access")
                        .getJSONObject("token")
                        .getString("id");

                return data;
            } catch (JSONException e) {
                LOGGER.error("Maybe specified json data does not exist? : {}", e.getMessage());
            }
        }
        return null;
    }

    public JSONArray getTenants(String url, String tokenId) {
        try {
            ClientResponse response = OSRestClient.httpGetRequest(
                    tokenId
                    , url
                    , OSRestClient.HEADER_APPLICATION_JSON
                    , OSRestClient.HEADER_APPLICATION_JSON);
            String output = response.getEntity(String.class);
            LOGGER.info("JSON response from OS: {}" , output);

            JSONObject jsonObj = new JSONObject(output);
            JSONArray jsonAry = jsonObj.getJSONArray("tenants");

            return jsonAry;

        } catch (JSONException e) {
            LOGGER.error("could not convert to json Object and its error message: {}", e.getMessage());
        }
        return null;
    }

    public String getMatchedTenantID(JSONArray tenants, String matchingTenantName) {
        try {
            for (int i = 0, len = tenants.length(); i < len; i++) {
                JSONObject tenant = tenants.getJSONObject(i);
                String tenantName = tenant.getString("name");
                if (matchingTenantName.equalsIgnoreCase(tenantName)) {
                    LOGGER.info("Tenant name \"" + matchingTenantName +"\" was matched.");
                    LOGGER.info("    returning tenant ID: " + tenant.getString("id"));
                    return tenant.getString("id");
                }
            }
        } catch (JSONException e) {

        }

        return null;
    }

}