package com.sdk.sq.net;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
@Deprecated
public class VolyConfig {
    private static final int DEFAULT_TIME_OUT = 10000;
    private int timeOut = 10000;

    public void setTimeOut(int timeOut) {
        this.timeOut = timeOut;
    }

    public int getTimeOut() {
        return this.timeOut;
    }
}
