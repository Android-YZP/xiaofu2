package cn.company1075.xiaofu.utils;

import java.util.Date;

public class ShopUserDeviceMan {
    private Long mirrorId;

    private Long shopId;

    private String mirrorName;

    private String mirrorMac;

    private String mirrorVersion;

    private Date gmtCreate;

    private Date gmtModified;

    public Long getMirrorId() {
        return mirrorId;
    }

    public void setMirrorId(Long mirrorId) {
        this.mirrorId = mirrorId;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getMirrorName() {
        return mirrorName;
    }

    public void setMirrorName(String mirrorName) {
        this.mirrorName = mirrorName == null ? null : mirrorName.trim();
    }

    public String getMirrorMac() {
        return mirrorMac;
    }

    public void setMirrorMac(String mirrorMac) {
        this.mirrorMac = mirrorMac == null ? null : mirrorMac.trim();
    }

    public String getMirrorVersion() {
        return mirrorVersion;
    }

    public void setMirrorVersion(String mirrorVersion) {
        this.mirrorVersion = mirrorVersion == null ? null : mirrorVersion.trim();
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }
}