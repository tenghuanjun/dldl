package com.alibaba.sdk.android.oss.model;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class GetSymlinkRequest extends OSSRequest {
    private String bucketName;
    private String objectKey;

    public String getBucketName() {
        return this.bucketName;
    }

    public void setBucketName(String str) {
        this.bucketName = str;
    }

    public String getObjectKey() {
        return this.objectKey;
    }

    public void setObjectKey(String str) {
        this.objectKey = str;
    }
}
