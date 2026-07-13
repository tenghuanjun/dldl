package com.mobile.auth.gatewayauth;

/* JADX INFO: loaded from: classes3.dex */
public interface TokenResultListener {
    void onTokenFailed(String str);

    void onTokenSuccess(String str);
}
