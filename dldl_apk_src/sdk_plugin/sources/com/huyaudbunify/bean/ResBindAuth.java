package com.huyaudbunify.bean;

import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class ResBindAuth {
    ResponseHeander header;
    Map<String, String> thirdParams;

    public ResponseHeander getHeader() {
        return this.header;
    }

    public void setHeader(ResponseHeander responseHeander) {
        this.header = responseHeander;
    }

    public Map<String, String> getThirdParams() {
        return this.thirdParams;
    }

    public void setThirdParams(Map<String, String> map) {
        this.thirdParams = map;
    }
}
