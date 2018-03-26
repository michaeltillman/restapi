package com.serve.openstack.extendedservice.openstack.domain.keystone;

import java.util.HashMap;
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
        "adminURL",
        "region",
        "internalURL",
        "id",
        "publicURL"
})
public class Endpoint {

    @JsonProperty("adminURL")
    private String adminURL;
    @JsonProperty("region")
    private String region;
    @JsonProperty("internalURL")
    private String internalURL;
    @JsonProperty("id")
    private String id;
    @JsonProperty("publicURL")
    private String publicURL;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     * The adminURL
     */
    @JsonProperty("adminURL")
    public String getAdminURL() {
        return adminURL;
    }

    /**
     *
     * @param adminURL
     * The adminURL
     */
    @JsonProperty("adminURL")
    public void setAdminURL(String adminURL) {
        this.adminURL = adminURL;
    }

    /**
     *
     * @return
     * The region
     */
    @JsonProperty("region")
    public String getRegion() {
        return region;
    }

    /**
     *
     * @param region
     * The region
     */
    @JsonProperty("region")
    public void setRegion(String region) {
        this.region = region;
    }

    /**
     *
     * @return
     * The internalURL
     */
    @JsonProperty("internalURL")
    public String getInternalURL() {
        return internalURL;
    }

    /**
     *
     * @param internalURL
     * The internalURL
     */
    @JsonProperty("internalURL")
    public void setInternalURL(String internalURL) {
        this.internalURL = internalURL;
    }

    /**
     *
     * @return
     * The id
     */
    @JsonProperty("id")
    public String getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The publicURL
     */
    @JsonProperty("publicURL")
    public String getPublicURL() {
        return publicURL;
    }

    /**
     *
     * @param publicURL
     * The publicURL
     */
    @JsonProperty("publicURL")
    public void setPublicURL(String publicURL) {
        this.publicURL = publicURL;
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