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
        "issued_at",
        "expires",
        "id",
        "tenant",
        "audit_ids"
})
public class Token {

    @JsonProperty("issued_at")
    private String issuedAt;
    @JsonProperty("expires")
    private String expires;
    @JsonProperty("id")
    private String id;
    @JsonProperty("tenant")
    private Tenant tenant;
    @JsonProperty("audit_ids")
    private List<String> auditIds = new ArrayList<String>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     * The issuedAt
     */
    @JsonProperty("issued_at")
    public String getIssuedAt() {
        return issuedAt;
    }

    /**
     *
     * @param issuedAt
     * The issued_at
     */
    @JsonProperty("issued_at")
    public void setIssuedAt(String issuedAt) {
        this.issuedAt = issuedAt;
    }

    /**
     *
     * @return
     * The expires
     */
    @JsonProperty("expires")
    public String getExpires() {
        return expires;
    }

    /**
     *
     * @param expires
     * The expires
     */
    @JsonProperty("expires")
    public void setExpires(String expires) {
        this.expires = expires;
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
     * The tenant
     */
    @JsonProperty("tenant")
    public Tenant getTenant() {
        return tenant;
    }

    /**
     *
     * @param tenant
     * The tenant
     */
    @JsonProperty("tenant")
    public void setTenant(Tenant tenant) {
        this.tenant = tenant;
    }

    /**
     *
     * @return
     * The auditIds
     */
    @JsonProperty("audit_ids")
    public List<String> getAuditIds() {
        return auditIds;
    }

    /**
     *
     * @param auditIds
     * The audit_ids
     */
    @JsonProperty("audit_ids")
    public void setAuditIds(List<String> auditIds) {
        this.auditIds = auditIds;
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