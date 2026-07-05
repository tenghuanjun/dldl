package com.huya.live.ns;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class NSInitParam {
    private String appid;
    private boolean enableMonitor;
    private String guid;
    private long uid;

    public String getGuid() {
        return this.guid;
    }

    public NSInitParam setGuid(String str) {
        this.guid = str;
        return this;
    }

    public long getUid() {
        return this.uid;
    }

    public NSInitParam setUid(long j) {
        this.uid = j;
        return this;
    }

    public String getAppid() {
        return this.appid;
    }

    public NSInitParam setAppid(String str) {
        this.appid = str;
        return this;
    }

    public boolean isEnableMonitor() {
        return this.enableMonitor;
    }

    public NSInitParam setEnableMonitor(boolean z) {
        this.enableMonitor = z;
        return this;
    }
}
