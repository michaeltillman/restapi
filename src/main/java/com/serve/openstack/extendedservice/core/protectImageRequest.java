package com.serve.openstack.extendedservice.core;

/**
 * Created by bselv2 on 8/14/2015.
 */
public class protectImageRequest {
    private String op;

    private Boolean value;

    private String path;

    public String getOp ()
    {
        return op;
    }

    public void setOp (String op)
    {
        this.op = op;
    }

    public Boolean getValue ()
    {
        return value;
    }

    public void setValue (Boolean value)
    {
        this.value = value;
    }

    public String getPath ()
    {
        return path;
    }

    public void setPath (String path)
    {
        this.path = path;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [op = "+op+", value = "+value+", path = "+path+"]";
    }
}
