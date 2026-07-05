package com.huya.component.login.api;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public interface OnThirdAuthCallback {
    void onAuthFailed();

    void onAuthSuccess(String str, String str2, String str3, String str4, String str5);
}
