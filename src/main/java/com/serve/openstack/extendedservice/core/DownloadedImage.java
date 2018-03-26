package com.serve.openstack.extendedservice.core;

/**
 * Created by mtillman on 4/2/15.
 */
public class DownloadedImage {

    private String fullImagePath;
    private String md5Checksum;
    private String remoteURI;
    private String imageUUID;
    private boolean downloaded = false;

    public void setFullImagePath(String imagePath) {
        this.fullImagePath = imagePath;
    }
    public String getFullImagePath() { return fullImagePath; }

    public void setMd5Checksum(String md5) {
        this.md5Checksum = md5;
    }
    public String getMd5Checksum() { return md5Checksum; }

    public void setRemoteURI(String uri) {
        this.remoteURI = uri;
    }
    public String getRemoteURI() { return remoteURI; }

    public void setImageUUID(String uuid) {
        this.imageUUID = uuid;
    }
    public String getImageUUID() { return imageUUID; }

    public void setDownloaded(boolean successful) {
        this.downloaded = successful;
    }
    public boolean isDownloaded() { return downloaded; }

}
