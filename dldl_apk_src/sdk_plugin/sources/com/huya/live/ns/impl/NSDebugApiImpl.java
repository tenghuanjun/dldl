package com.huya.live.ns.impl;

import com.huya.mtp.api.DebugApi;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class NSDebugApiImpl implements DebugApi {
    private INSDebugCrashListener mListener;

    public NSDebugApiImpl(INSDebugCrashListener iNSDebugCrashListener) {
        this.mListener = iNSDebugCrashListener;
    }

    @Override // com.huya.mtp.api.DebugApi
    public void crashIfDebug(String str, Object... objArr) {
        INSDebugCrashListener iNSDebugCrashListener = this.mListener;
        if (iNSDebugCrashListener != null) {
            iNSDebugCrashListener.crashIfDebug(false, null, str, objArr);
        }
    }

    @Override // com.huya.mtp.api.DebugApi
    public void crashIfDebug(Throwable th, String str, Object... objArr) {
        INSDebugCrashListener iNSDebugCrashListener = this.mListener;
        if (iNSDebugCrashListener != null) {
            iNSDebugCrashListener.crashIfDebug(false, th, str, objArr);
        }
    }

    @Override // com.huya.mtp.api.DebugApi
    public void crashIfDebug(boolean z, String str, Object... objArr) {
        INSDebugCrashListener iNSDebugCrashListener = this.mListener;
        if (iNSDebugCrashListener != null) {
            iNSDebugCrashListener.crashIfDebug(false, null, str, objArr);
        }
    }
}
