package com.alibaba.sdk.android.oss.model;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class GetBucketInfoRequest extends OSSRequest {
    private String bucketName;

    public String getBucketName() {
        return this.bucketName;
    }

    public void setBucketName(String str) {
        this.bucketName = str;
    }

    public GetBucketInfoRequest(String str) {
        this.bucketName = str;
    }
}
