package com.huya.statistics.core;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class StatisticsOption {
    private String dty;
    private String from;
    private boolean isEncrypted;
    private String platform;
    private String pro;
    private String url;
    private String ver;
    private String viersionCode;

    public StatisticsOption(String str, String str2, String str3, String str4) {
        this(str, str2, str3, str4, "mobile/andriod", null);
    }

    public StatisticsOption(String str, String str2, String str3, String str4, String str5) {
        this(str, str2, str3, str4, str5, null);
    }

    public StatisticsOption(String str, String str2, String str3, String str4, String str5, String str6) {
        this.pro = str;
        this.from = str2;
        this.ver = str3;
        this.url = str6;
        this.dty = str4;
        this.platform = str5;
    }

    public StatisticsOption(String str, String str2, String str3, String str4, String str5, String str6, String str7, boolean z) {
        this.from = str;
        this.ver = str2;
        this.pro = str3;
        this.url = str4;
        this.dty = str5;
        this.platform = str6;
        this.viersionCode = str7;
        this.isEncrypted = z;
    }

    public boolean isEncrypted() {
        return this.isEncrypted;
    }

    public void setEncrypted(boolean z) {
        this.isEncrypted = z;
    }

    public StatisticsOption(String str) {
        this.url = str;
    }

    public String getFrom() {
        return this.from;
    }

    public String getVer() {
        return this.ver;
    }

    public String getPro() {
        return this.pro;
    }

    public String getUrl() {
        return this.url;
    }

    public void setFrom(String str) {
        this.from = str;
    }

    public void setVer(String str) {
        this.ver = str;
    }

    public void setPro(String str) {
        this.pro = str;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public void setDty(String str) {
        this.dty = str;
    }

    public String getDty() {
        return this.dty;
    }

    public String getViersionCode() {
        return this.viersionCode;
    }

    public void setViersionCode(String str) {
        this.viersionCode = str;
    }

    public String getPlatform() {
        return this.platform;
    }

    public void setPlatform(String str) {
        this.platform = str;
    }
}
