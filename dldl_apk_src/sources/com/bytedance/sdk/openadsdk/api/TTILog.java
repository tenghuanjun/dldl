package com.bytedance.sdk.openadsdk.api;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public interface TTILog {
    void d(String str, String str2);

    void e(String str, String str2);

    void e(String str, String str2, Throwable th);

    void e(String str, Throwable th);

    void flush();

    void forceLogSharding();

    void i(String str, String str2);

    void v(String str, String str2);

    void w(String str, String str2);

    void w(String str, String str2, Throwable th);

    void w(String str, Throwable th);
}
