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
        "username",
        "roles_links",
        "id",
        "roles",
        "name"
})
public class User {

    @JsonProperty("username")
    private String username;
    @JsonProperty("roles_links")
    private List<Object> rolesLinks = new ArrayList<Object>();
    @JsonProperty("id")
    private String id;
    @JsonProperty("roles")
    private List<Role> roles = new ArrayList<Role>();
    @JsonProperty("name")
    private String name;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     * The username
     */
    @JsonProperty("username")
    public String getUsername() {
        return username;
    }

    /**
     *
     * @param username
     * The username
     */
    @JsonProperty("username")
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     *
     * @return
     * The rolesLinks
     */
    @JsonProperty("roles_links")
    public List<Object> getRolesLinks() {
        return rolesLinks;
    }

    /**
     *
     * @param rolesLinks
     * The roles_links
     */
    @JsonProperty("roles_links")
    public void setRolesLinks(List<Object> rolesLinks) {
        this.rolesLinks = rolesLinks;
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
     * The roles
     */
    @JsonProperty("roles")
    public List<Role> getRoles() {
        return roles;
    }

    /**
     *
     * @param roles
     * The roles
     */
    @JsonProperty("roles")
    public void setRoles(List<Role> roles) {
        this.roles = roles;
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