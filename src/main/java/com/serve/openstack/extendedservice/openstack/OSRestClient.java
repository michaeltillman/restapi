package com.serve.openstack.extendedservice.openstack;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by mtillman on 3/3/15.
 */
public class OSRestClient {

    public static final String HEADER_APPLICATION_JSON = "application/json";
    public static final String HEADER_APPLICATION_JSON_PATCH = "application/openstack-images-v2.1-json-patch";
    private static final Logger LOGGER = LoggerFactory.getLogger(OSRestClient.class);

    static ClientResponse httpPostRequest(String xAuthTokenId, Object payload,
                                          String url, String typeHeader) {

        WebResource webResource = getWebResource(url);

        ClientResponse response = webResource
                .type(typeHeader)
                .header("X-Auth-Token", xAuthTokenId)
                .post(ClientResponse.class, payload);

        return response;
    }

    static ClientResponse httpGetRequest(String xAuthTokenId, String url,
                                         String acceptHeader, String typeHeader) {

        WebResource webResource = getWebResource(url);

        ClientResponse response = webResource.accept(acceptHeader)
                .type(typeHeader)
                .header("X-Auth-Token", xAuthTokenId)
                .get(ClientResponse.class);

        return response;
    }

    static ClientResponse httpGetRequest(String url, String acceptHeader, String typeHeader) {
        WebResource webResource = getWebResource(url);

        ClientResponse response = webResource.accept(acceptHeader)
                .type(typeHeader)
                .get(ClientResponse.class);

        return response;
    }

    static ClientResponse httpPostRequest(Object payload, String url, String acceptHeader, String typeHeader) {

        WebResource webResource = getWebResource(url);

        ClientResponse response = webResource.accept(acceptHeader)
                .type(typeHeader)
                .post(ClientResponse.class, payload);

        return response;
    }

    static WebResource getWebResource(String url) {

        ClientConfig clientconfig = new DefaultClientConfig();
        clientconfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);

        Client client = Client.create(clientconfig);
        WebResource webResource = client.resource(url);

        return webResource;
    }

}
