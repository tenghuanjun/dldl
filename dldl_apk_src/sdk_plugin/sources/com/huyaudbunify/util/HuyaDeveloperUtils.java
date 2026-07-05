package com.huyaudbunify.util;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class HuyaDeveloperUtils {
    private static volatile HuyaDeveloperUtils mHuyaDeveloperUtils;
    private boolean isTcp = false;
    private int isPre = 0;
    private boolean isForbidLog = true;
    private boolean isDeveloper = false;
    private String appId = "";
    private boolean useMars = true;
    private int netType = 3;
    private int carrierType = 2;
    private String host = "unknow";

    public boolean isUseMars() {
        return this.useMars;
    }

    public void setUseMars(boolean z) {
        this.useMars = z;
    }

    public int getNetType() {
        return this.netType;
    }

    public void setNetType(int i) {
        this.netType = i;
    }

    public int getCarrierType() {
        return this.carrierType;
    }

    public void setCarrierType(int i) {
        this.carrierType = i;
    }

    public String getHost() {
        return this.host;
    }

    public void setHost(String str) {
        this.host = str;
    }

    public static HuyaDeveloperUtils getInstance() {
        if (mHuyaDeveloperUtils == null) {
            synchronized (HuyaDeveloperUtils.class) {
                if (mHuyaDeveloperUtils == null) {
                    mHuyaDeveloperUtils = new HuyaDeveloperUtils();
                }
            }
        }
        return mHuyaDeveloperUtils;
    }

    public String getAppId() {
        return this.appId;
    }

    public void setAppId(String str) {
        this.appId = str;
    }

    public boolean isTcp() {
        return this.isTcp;
    }

    public void setTcp(boolean z) {
        this.isTcp = z;
    }

    public int getIsPre() {
        return this.isPre;
    }

    public void setIsPre(int i) {
        this.isPre = i;
    }

    public boolean isForbidLog() {
        return this.isForbidLog;
    }

    public void setForbidLog(boolean z) {
        this.isForbidLog = z;
    }

    public boolean isDeveloper() {
        return this.isDeveloper;
    }

    public void setDeveloper(boolean z) {
        this.isDeveloper = z;
    }
}
