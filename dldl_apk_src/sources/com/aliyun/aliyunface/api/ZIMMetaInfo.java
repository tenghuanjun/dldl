package com.aliyun.aliyunface.api;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class ZIMMetaInfo {
    private String apdidToken;
    private String appName;
    private String appVersion;
    private String bioMetaInfo;
    private String deviceModel;
    private String deviceType;
    private String osVersion;
    private String sdkVersion;
    private String zimVer;

    public String getZimVer() {
        return this.zimVer;
    }

    public void setZimVer(String str) {
        this.zimVer = str;
    }

    public String getApdidToken() {
        return this.apdidToken;
    }

    public void setApdidToken(String str) {
        this.apdidToken = str;
    }

    public String getDeviceType() {
        return this.deviceType;
    }

    public void setDeviceType(String str) {
        this.deviceType = str;
    }

    public String getDeviceModel() {
        return this.deviceModel;
    }

    public void setDeviceModel(String str) {
        this.deviceModel = str;
    }

    public String getAppName() {
        return this.appName;
    }

    public void setAppName(String str) {
        this.appName = str;
    }

    public String getAppVersion() {
        return this.appVersion;
    }

    public void setAppVersion(String str) {
        this.appVersion = str;
    }

    public String getOsVersion() {
        return this.osVersion;
    }

    public void setOsVersion(String str) {
        this.osVersion = str;
    }

    public String getBioMetaInfo() {
        return this.bioMetaInfo;
    }

    public void setBioMetaInfo(String str) {
        this.bioMetaInfo = str;
    }

    public String getSdkVersion() {
        return this.sdkVersion;
    }

    public void setSdkVersion(String str) {
        this.sdkVersion = str;
    }

    public String toString() {
        return "ZIMMetaInfo{apdidToken='" + this.apdidToken + "', deviceType='" + this.deviceType + "', deviceModel='" + this.deviceModel + "', appName='" + this.appName + "', appVersion='" + this.appVersion + "', osVersion='" + this.osVersion + "', bioMetaInfo='" + this.bioMetaInfo + "', zimVer='" + this.zimVer + "', sdkVersion='" + this.sdkVersion + "'}";
    }
}
