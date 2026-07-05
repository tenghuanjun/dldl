package com.bytedance.android.livehostapi.platform;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public interface TokenRefreshCallback {
    void onFailed(Throwable th);

    void onSuccess(TokenInfo tokenInfo);
}
