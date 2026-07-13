package com.mobile.auth.gatewayauth;

/* JADX INFO: loaded from: classes3.dex */
public interface PreLoginResultListener {
    void onTokenFailed(String str, String str2);

    void onTokenSuccess(String str);
}
