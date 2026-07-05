package com.mobile.auth.gatewayauth;

import com.mobile.auth.gatewayauth.model.LoginPhoneInfo;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes6.dex */
public interface OnLoginPhoneListener {
    void onGetFailed(String str);

    void onGetLoginPhone(LoginPhoneInfo loginPhoneInfo);
}
