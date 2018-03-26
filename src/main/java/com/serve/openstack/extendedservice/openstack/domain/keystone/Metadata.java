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
        "is_admin",
        "roles"
})
public class Metadata {

    @JsonProperty("is_admin")
    private Integer isAdmin;
    @JsonProperty("roles")
    private List<String> roles = new ArrayList<String>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     * The isAdmin
     */
    @JsonProperty("is_admin")
    public Integer getIsAdmin() {
        return isAdmin;
    }

    /**
     *
     * @param isAdmin
     * The is_admin
     */
    @JsonProperty("is_admin")
    public void setIsAdmin(Integer isAdmin) {
        this.isAdmin = isAdmin;
    }

    /**
     *
     * @return
     * The roles
     */
    @JsonProperty("roles")
    public List<String> getRoles() {
        return roles;
    }

    /**
     *
     * @param roles
     * The roles
     */
    @JsonProperty("roles")
    public void setRoles(List<String> roles) {
        this.roles = roles;
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