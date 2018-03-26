package com.serve.openstack.extendedservice.core;

/**
 * Created by bselv2 on 8/14/2015.
 */
public class ShareImageRequestParameters {
    private String tenantid;

    private String imageid;

    public String getTenantid ()
    {
        return tenantid;
    }

    public void setTenantid (String tenantid)
    {
        this.tenantid = tenantid;
    }

    public String getImageid ()
    {
        return imageid;
    }

    public void setImageid (String imageid)
    {
        this.imageid = imageid;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [tenantid = "+tenantid+", imageid = "+imageid+"]";
    }
}
