package com.serve.openstack.extendedservice.core;

import com.fasterxml.jackson.annotation.JsonProperty;
/**
 * Created by mtillman on 3/2/15.
 */
public class PromotionRequestParameter {

    @JsonProperty("Content")
    private String content;

    @JsonProperty("devImageID")
    private String devImageID;

    @JsonProperty("oSSourceKeyUrl")
    private String oSSourceKeyUrl;

    @JsonProperty("oSSourceGlanceUrl")
    private String oSSourceGlanceUrl;

    @JsonProperty("oSSourceUser")
    private String oSSourceUser;

    @JsonProperty("oSSourceTenant")
    private String oSSourceTenant;

    @JsonProperty("oSSourcePasswd")
    private String oSSourcePasswd;

    @JsonProperty("oSTargetKeyUrl")
    private String oSTargetKeyUrl;

    @JsonProperty("oSTargetGlanceUrl")
    private String oSTargetGlanceUrl;

    @JsonProperty("oSTargetUser")
    private String oSTargetUser;

    @JsonProperty("oSTargetTenant")
    private String oSTargetTenant;

    @JsonProperty("oSTargetPasswd")
    private String oSTargetPasswd;

    public PromotionRequestParameter() {

    }

    public PromotionRequestParameter(String content) {
        this.content = content;
    }

    @JsonProperty("Content")
    public String getContent() {
        return content;
    }

    @JsonProperty("Content")
    public void setContent(String content) {
        this.content = content;
    }

    @JsonProperty("devImageID")
    public String getDevImageID() {
        return devImageID;
    }

    @JsonProperty("devImageID")
    public void setDevImageID(String devImageID) {
        this.devImageID = devImageID;
    }

    @JsonProperty("oSSourceKeyUrl")
    public String getOSSourceKeyUrl() {
        return oSSourceKeyUrl;
    }

    @JsonProperty("oSSourceKeyUrl")
    public void setOSSourceKeyUrl(String url) {
        oSSourceKeyUrl = url;
    }

    @JsonProperty("oSSourceGlanceUrl")
    public String getOSSourceGlanceUrl() {
        return oSSourceGlanceUrl;
    }

    @JsonProperty("oSSourceGlanceUrl")
    public void setOSSourceGlanceUrl(String url) {
        oSSourceGlanceUrl = url;
    }

    @JsonProperty("oSSourceUser")
    public String getOSSourceUser() {
        return oSSourceUser;
    }

    @JsonProperty("oSSourceUser")
    public void setOSSourceUser(String user) {
        oSSourceUser = user;
    }

    @JsonProperty("oSSourceTenant")
    public String getOSSourceTenant() {
        return oSSourceTenant;
    }

    @JsonProperty("oSSourceTenant")
    public void setOSSourceTenant(String tenant) {
        this.oSSourceTenant = tenant;
    }

    @JsonProperty("oSSourcePasswd")
    public String getOSSourcePasswd() {
        return oSSourcePasswd;
    }

    @JsonProperty("oSSourcePasswd")
    public void setOSSourcePasswd(String passwd) {
        oSSourcePasswd = passwd;
    }

    @JsonProperty("oSTargetKeyUrl")
    public String getOSTargetKeyUrl() {
        return oSTargetKeyUrl;
    }

    @JsonProperty("oSTargetKeyUrl")
    public void setOSTargeKeyUrl(String url) {
        oSTargetKeyUrl = url;
    }

    @JsonProperty("oSTargetGlanceUrl")
    public String getOSTargetGlanceUrl() {
        return oSTargetGlanceUrl;
    }

    @JsonProperty("oSTargetGlanceUrl")
    public void setOSTargeGlanceUrl(String url) {
        oSTargetGlanceUrl = url;
    }

    @JsonProperty("oSTargetUser")
    public String getOSTargetUser() {
        return oSTargetUser;
    }

    @JsonProperty("oSTargetUser")
    public void setOSTargetUser(String user) {
        oSTargetUser = user;
    }

    @JsonProperty("oSTargetTenant")
    public String getOSTargetTenant() {
        return oSTargetTenant;
    }
    @JsonProperty("oSTargetTenant")
    public void setOSTargetTenant(String tenant) {
        this.oSTargetTenant = tenant;
    }

    @JsonProperty("oSTargetPasswd")
    public String getOSTargetPasswd() {
        return oSTargetPasswd;
    }

    @JsonProperty("oSTargetPasswd")
    public void setoSTargetPasswd(String passwd) {
        oSTargetPasswd = passwd;
    }
}
