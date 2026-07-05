package com.sqwan.common.mod.plugin;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class PluginBean {
    private int mConfId;
    private String mUrl;
    private int mVersion;

    public void setVersion(int i) {
        this.mVersion = i;
    }

    public void setConfId(int i) {
        this.mConfId = i;
    }

    public void setPluginUrl(String str) {
        this.mUrl = str;
    }

    public int getVersion() {
        return this.mVersion;
    }

    public int getConfId() {
        return this.mConfId;
    }

    public String getUrl() {
        return this.mUrl;
    }
}
