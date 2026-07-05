package com.huya.mtp.api;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class DebugApiDelegate implements DebugApi {
    private DebugApi mDebugApi;

    public DebugApiDelegate() {
        this(null);
    }

    public DebugApiDelegate(DebugApi debugApi) {
        this.mDebugApi = debugApi;
    }

    public void setDebugApi(DebugApi debugApi) {
        this.mDebugApi = debugApi;
    }

    @Override // com.huya.mtp.api.DebugApi
    public void crashIfDebug(String str, Object... objArr) {
        DebugApi debugApi = this.mDebugApi;
        if (debugApi != null) {
            debugApi.crashIfDebug(str, objArr);
        }
    }

    @Override // com.huya.mtp.api.DebugApi
    public void crashIfDebug(Throwable th, String str, Object... objArr) {
        DebugApi debugApi = this.mDebugApi;
        if (debugApi != null) {
            debugApi.crashIfDebug(th, str, objArr);
        }
    }

    @Override // com.huya.mtp.api.DebugApi
    public void crashIfDebug(boolean z, String str, Object... objArr) {
        DebugApi debugApi = this.mDebugApi;
        if (debugApi != null) {
            debugApi.crashIfDebug(z, str, objArr);
        }
    }
}
