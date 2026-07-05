package com.huya.mtp.api;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public interface DebugApi {
    void crashIfDebug(String str, Object... objArr);

    void crashIfDebug(Throwable th, String str, Object... objArr);

    void crashIfDebug(boolean z, String str, Object... objArr);
}
