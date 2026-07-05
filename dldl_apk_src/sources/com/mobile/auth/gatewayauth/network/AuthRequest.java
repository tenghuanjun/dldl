package com.mobile.auth.gatewayauth.network;

import com.alicom.tools.networking.Request;
import com.alicom.tools.networking.SerializationName;
import com.alipay.sdk.packet.e;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes2.dex */
public class AuthRequest extends Request {

    @SerializationName(e.e)
    private String Version = "2017-05-25";

    @SerializationName("Format")
    private String Format = "JSON";
}
