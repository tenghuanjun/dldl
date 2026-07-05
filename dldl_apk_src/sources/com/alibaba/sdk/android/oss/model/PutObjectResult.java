package com.alibaba.sdk.android.oss.model;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class PutObjectResult extends OSSResult {
    private String eTag;
    private String serverCallbackReturnBody;

    public String getETag() {
        return this.eTag;
    }

    public void setETag(String str) {
        this.eTag = str;
    }

    public String getServerCallbackReturnBody() {
        return this.serverCallbackReturnBody;
    }

    public void setServerCallbackReturnBody(String str) {
        this.serverCallbackReturnBody = str;
    }
}
