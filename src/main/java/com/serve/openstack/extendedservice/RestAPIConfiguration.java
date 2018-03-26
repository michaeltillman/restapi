package com.serve.openstack.extendedservice;

import io.dropwizard.Configuration;
import lombok.Getter;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by mtillman on 3/2/15.
 */
public class RestAPIConfiguration extends Configuration {

    /*
    * sourceKeyUrl: http://10.115.228.58:5000/v2.0/tokens
sourceUser: demo
sourcePassword: 85a81478b7a249c3
sourceTenant: demo
targetKeyUrl: http://10.115.26.157:5000/v2.0/tokens
targetUser: admin
targetPassword: admin
targetTenant: admin
    * */
    @NotEmpty
    private String sourceUser;

    @NotEmpty
    private String sourcePassword;

    @NotEmpty
    private String sourceKeyUrl;

    @NotEmpty
    private String sourceGlanceUrl;

    @NotEmpty
    private String sourceTenant;

    @NotEmpty
    private String targetUser;

    @NotEmpty
    private String targetPassword;

    @NotEmpty
    private String targetKeyUrl;

    @NotEmpty
    private String targetGlanceUrl;

    @NotEmpty
    private String targetTenantsUrl;

    @NotEmpty
    private String apiClientUser;

    @NotEmpty
    private String apiClientPassword;

    @JsonProperty("apiClientUser")
    public String getApiClientUser() {
        return apiClientUser;
    }

    @JsonProperty("apiClientUser")
    public void setApiClientUser(String apiClientUser) {
        this.apiClientUser = apiClientUser;
    }


    @JsonProperty("apiClientPassword")
    public String getApiClientPassword() {
        return apiClientPassword;
    }

    @JsonProperty("apiClientPassword")
    public void setApiClientPassword(String apiClientPassword) {
        this.apiClientPassword = apiClientPassword;
    }

    @JsonProperty("sourceUser")
    public String getSourceUser() {
        return sourceUser;
    }

    @JsonProperty("sourceUser")
    public void setSourceUser(String openstackUsername) {
        this.sourceUser = openstackUsername;
    }

    @JsonProperty("sourcePassword")
    public String getSourcePassword() {
        return sourcePassword;
    }

    @JsonProperty("sourcePassword")
    public void setSourcePassword(String openstackPassword) {
        this.sourcePassword = openstackPassword;
    }

    @JsonProperty("sourceKeyUrl")
    public String getSourceKeyUrl() {
        return sourceKeyUrl;
    }

    @JsonProperty("sourceKeyUrl")
    public void setSourceKeyUrl(String keystoneUrl) {
        this.sourceKeyUrl = keystoneUrl;
    }

    @JsonProperty("sourceGlanceUrl")
    public String getSourceGlanceUrl() { return sourceGlanceUrl; }

    @JsonProperty("sourceGlanceUrl")
    public void setSourceGlanceUrl(String sourceGlanceUrl) {
        this.sourceGlanceUrl = sourceGlanceUrl;
    }

    @JsonProperty("sourceTenant")
    public String getSourceTenant() {
        return sourceTenant;
    }

    @JsonProperty("sourceTenant")
    public void setSourceTenant(String sourceTenant) {
        this.sourceTenant = sourceTenant;
    }

    @JsonProperty("targetUser")
    public String getTargetUser() {
        return targetUser;
    }

    @JsonProperty("targetUser")
    public void setTargetUser(String openstackUsername) {
        this.targetUser = openstackUsername;
    }

    @JsonProperty("targetPassword")
    public String getTargetPassword() {
        return targetPassword;
    }

    @JsonProperty("targetPassword")
    public void setTargetPassword(String openstackPassword) {
        this.targetPassword = openstackPassword;
    }

    @JsonProperty("targetKeyUrl")
    public String getTargetKeyUrl() {
        return targetKeyUrl;
    }

    @JsonProperty("targetKeyUrl")
    public void setTargetKeyUrl(String keystoneUrl) {
        this.targetKeyUrl = keystoneUrl;
    }

    @JsonProperty("targetGlanceUrl")
    public String getTargetGlanceUrl() {
        return targetGlanceUrl;
    }

    @JsonProperty("targetGlanceUrl")
    public void setTargetGlanceUrl(String targetGlanceUrl) {
        this.targetGlanceUrl = targetGlanceUrl;
    }

    @JsonProperty("targetTenantsUrl")
    public String getTargetTenantsUrl() {
        return targetTenantsUrl;
    }

    @JsonProperty("targetTenantsUrl")
    public void setTargetTenantsUrl(String targetTenantsUrl) {
        this.targetTenantsUrl = targetTenantsUrl;
    }
}
