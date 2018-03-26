package com.serve.openstack.extendedservice.openstack.payload;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Created by mtillman on 3/4/15.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
        "tenantName",
        "passwordCredentials"
})
public class Auth {

    @JsonProperty("tenantName")
    private String tenantName;
    @JsonProperty("passwordCredentials")
    private PasswordCredentials passwordCredentials;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     * The tenantName
     */
    @JsonProperty("tenantName")
    public String getTenantName() {
        return tenantName;
    }

    /**
     *
     * @param tenantName
     * The tenantName
     */
    @JsonProperty("tenantName")
    public void setTenantName(String tenantName) {
        this.tenantName = tenantName;
    }

    /**
     *
     * @return
     * The passwordCredentials
     */
    @JsonProperty("passwordCredentials")
    public PasswordCredentials getPasswordCredentials() {
        return passwordCredentials;
    }

    /**
     *
     * @param passwordCredentials
     * The passwordCredentials
     */
    @JsonProperty("passwordCredentials")
    public void setPasswordCredentials(PasswordCredentials passwordCredentials) {
        this.passwordCredentials = passwordCredentials;
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
