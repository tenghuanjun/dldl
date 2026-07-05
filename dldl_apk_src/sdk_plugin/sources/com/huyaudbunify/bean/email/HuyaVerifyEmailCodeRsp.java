package com.huyaudbunify.bean.email;

import com.huyaudbunify.bean.ResponseHeander;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class HuyaVerifyEmailCodeRsp {
    ResponseHeander header;
    String sessionData;

    public ResponseHeander getHeader() {
        return this.header;
    }

    public void setHeader(ResponseHeander responseHeander) {
        this.header = responseHeander;
    }

    public String getSessionData() {
        return this.sessionData;
    }

    public void setSessionData(String str) {
        this.sessionData = str;
    }
}
