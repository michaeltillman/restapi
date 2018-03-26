package com.serve.openstack.extendedservice.openstack.domain.keystone;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
        "endpoints",
        "endpoints_links",
        "type",
        "name"
})
public class ServiceCatalog {

    @JsonProperty("endpoints")
    private List<Endpoint> endpoints = new ArrayList<Endpoint>();
    @JsonProperty("endpoints_links")
    private List<Object> endpointsLinks = new ArrayList<Object>();
    @JsonProperty("type")
    private String type;
    @JsonProperty("name")
    private String name;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     * The endpoints
     */
    @JsonProperty("endpoints")
    public List<Endpoint> getEndpoints() {
        return endpoints;
    }

    /**
     *
     * @param endpoints
     * The endpoints
     */
    @JsonProperty("endpoints")
    public void setEndpoints(List<Endpoint> endpoints) {
        this.endpoints = endpoints;
    }

    /**
     *
     * @return
     * The endpointsLinks
     */
    @JsonProperty("endpoints_links")
    public List<Object> getEndpointsLinks() {
        return endpointsLinks;
    }

    /**
     *
     * @param endpointsLinks
     * The endpoints_links
     */
    @JsonProperty("endpoints_links")
    public void setEndpointsLinks(List<Object> endpointsLinks) {
        this.endpointsLinks = endpointsLinks;
    }

    /**
     *
     * @return
     * The type
     */
    @JsonProperty("type")
    public String getType() {
        return type;
    }

    /**
     *
     * @param type
     * The type
     */
    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    /**
     *
     * @return
     * The name
     */
    @JsonProperty("name")
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     * The name
     */
    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}