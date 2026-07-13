package com.mobile.auth.gatewayauth;

import com.mobile.auth.gatewayauth.model.LoginPhoneInfo;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public interface OnLoginPhoneListener {
    void onGetFailed(String str);

    void onGetLoginPhone(LoginPhoneInfo loginPhoneInfo);
}
