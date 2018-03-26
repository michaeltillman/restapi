package com.serve.openstack.extendedservice.core;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by bselv2 on 8/13/2015.
 */
public class codeCleanRequest {

    private String targetTenantName;

    private String imageName;

    private String tenantname;

   /* private String metadatavalue;

    private String metadatakey;*/

    public String getTargetTenantName ()
    {
        return targetTenantName;
    }

    public void setTargetTenantName (String targetTenantName)
    {
        this.targetTenantName = targetTenantName;
    }

    public String getImageName ()
    {
        return imageName;
    }

    public void setImageName (String imageName)
    {
        this.imageName = imageName;
    }

    public String getTenantname ()
    {
        return tenantname;
    }

    public void setTenantname (String tenantname)
    {
        this.tenantname = tenantname;
    }

   /* public String getMetadatavalue ()
    {
        return metadatavalue;
    }

    public void setMetadatavalue (String metadatavalue)
    {
        this.metadatavalue = metadatavalue;
    }

    public String getMetadatakey ()
    {
        return metadatakey;
    }

    public void setMetadatakey (String metadatakey)
    {
        this.metadatakey = metadatakey;
    }*/

    @Override
    public String toString()
    {
        return "ClassPojo [targetTenantName = "+targetTenantName+", imageName = "+imageName+", tenantname = "+tenantname+"]";
    }

}
