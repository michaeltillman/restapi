package com.serve.openstack.extendedservice.core;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by bselv2 on 8/12/2015.
 */
public class OpenStackSetImageMetaDataRequest {

    private String tenantName;
    private String keyName;
    private String imageName;
    private String keyValue;
    private Map<String ,String> metadata;

    public Map<String ,String > getMetadata()
    {
        return metadata;
    }

    public void setMetadata(String keyName, String keyValue)
    {
        this.metadata = new HashMap();
        this.metadata.put(keyName, keyValue);
    }

    public String getTenantname ()
    {
        return tenantName;
    }

    public void setTenantname (String tenantName)
    {
        this.tenantName = tenantName;
    }

    public String getKeyName ()
    {
        return keyName;
    }

    public void setKeyName (String keyName)
    {
        this.keyName = keyName;
    }

    public String getKeyValue ()
    {
        return keyValue;
    }

    public void setKeyValue (String keyValue)
    {
        this.keyValue = keyValue;
    }

    public String getImageName ()
    {
        return imageName;
    }

    public void setImageName (String imageName)
    {
        this.imageName = imageName;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [tenantname = "+tenantName+", keyname = "+keyName+", keyvalue = "+keyValue+", imagename = "+imageName+"]";
    }
}
