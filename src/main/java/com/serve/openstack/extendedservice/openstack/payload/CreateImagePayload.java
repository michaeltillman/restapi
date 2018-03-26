package com.serve.openstack.extendedservice.openstack.payload;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
/**
 * Created by mtillman on 3/6/15.
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"id","name","visibility", "tags"})
public class CreateImagePayload {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("visibility")
    private String visibility = "public";

    @JsonProperty("tags")
    private List<String> tags = new ArrayList<String>();

    @JsonProperty("disk_format")
    private String diskFormat;

    @JsonProperty("container_format")
    private String containerFormat;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

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

    /**
     *
     * @return
     * The tags
     */
    @JsonProperty("tags")
    public List<String> getTags() {
        return tags;
    }

    /**
     *
     * @param tags
     * The tags
     */
    @JsonProperty("tags")
    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    /**
     *
     * @return
     * The visibility
     */
    @JsonProperty("visibility")
    public String getVisibility() {
        return visibility;
    }

    /**
     *
     * @param visibility
     */
    @JsonProperty("visibility")
    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    /**
     *
     * @return
     */
    @JsonProperty("disk_format")
    public void setDiskFormat(String format) {
        this.diskFormat = format;
    }

    /**
     *
     * @return
     */
    @JsonProperty("disk_format")
    public String getDiskFormat() {
        return diskFormat;
    }

    @JsonProperty("container_format")
    public void setContainerFormat(String containerFormat) {
        this.containerFormat = containerFormat;
    }

    @JsonProperty("container_format")
    public String getContainerFormat() {
        return containerFormat;
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
