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
        "token",
        "serviceCatalog",
        "user",
        "metadata"
})
public class Access {

    @JsonProperty("token")
    private Token token;
    @JsonProperty("serviceCatalog")
    private List<ServiceCatalog> serviceCatalog = new ArrayList<ServiceCatalog>();
    @JsonProperty("user")
    private User user;
    @JsonProperty("metadata")
    private Metadata metadata;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     * The token
     */
    @JsonProperty("token")
    public Token getToken() {
        return token;
    }

    /**
     *
     * @param token
     * The token
     */
    @JsonProperty("token")
    public void setToken(Token token) {
        this.token = token;
    }

    /**
     *
     * @return
     * The serviceCatalog
     */
    @JsonProperty("serviceCatalog")
    public List<ServiceCatalog> getServiceCatalog() {
        return serviceCatalog;
    }

    /**
     *
     * @param serviceCatalog
     * The serviceCatalog
     */
    @JsonProperty("serviceCatalog")
    public void setServiceCatalog(List<ServiceCatalog> serviceCatalog) {
        this.serviceCatalog = serviceCatalog;
    }

    /**
     *
     * @return
     * The user
     */
    @JsonProperty("user")
    public User getUser() {
        return user;
    }

    /**
     *
     * @param user
     * The user
     */
    @JsonProperty("user")
    public void setUser(User user) {
        this.user = user;
    }

    /**
     *
     * @return
     * The metadata
     */
    @JsonProperty("metadata")
    public Metadata getMetadata() {
        return metadata;
    }

    /**
     *
     * @param metadata
     * The metadata
     */
    @JsonProperty("metadata")
    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
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