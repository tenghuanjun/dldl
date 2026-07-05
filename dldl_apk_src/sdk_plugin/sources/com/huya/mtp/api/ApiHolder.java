package com.huya.mtp.api;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class ApiHolder {
    private DebugApi mDebugApi;
    private LogApi mLogApi;

    public DebugApi getDebugApi() {
        return this.mDebugApi;
    }

    public void setDebugApi(DebugApi debugApi) {
        this.mDebugApi = debugApi;
    }

    public LogApi getLogApi() {
        return this.mLogApi;
    }

    public void setLogApi(LogApi logApi) {
        this.mLogApi = logApi;
    }
}
