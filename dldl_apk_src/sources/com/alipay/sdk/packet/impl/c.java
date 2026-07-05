package com.alipay.sdk.packet.impl;

import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class c extends com.alipay.sdk.packet.e {
    @Override // com.alipay.sdk.packet.e
    protected String b() {
        return "5.0.0";
    }

    @Override // com.alipay.sdk.packet.e
    protected JSONObject a() throws JSONException {
        return com.alipay.sdk.packet.e.a("cashier", "gentid");
    }
}
