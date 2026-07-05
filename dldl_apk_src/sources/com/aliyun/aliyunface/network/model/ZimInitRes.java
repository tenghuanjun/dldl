package com.aliyun.aliyunface.network.model;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class ZimInitRes extends ZimResBase {
    public ResultObject resultObject;

    public static class ResultObject {
        public String accessKeyId;
        public String accessKeySecret;
        public String bucketName;
        public String certifyId;
        public String extParams;
        public String fileNamePrefix;
        public String message;
        public String ossEndPoint;
        public String protocol;
        public String retCode;
        public String retCodeSub;
        public String retMessageSub;
        public String securityToken;
    }

    public boolean isValid() {
        return this.resultObject != null;
    }

    public String getCertifyId() {
        return this.resultObject.certifyId;
    }

    public String getProtocol() {
        return this.resultObject.protocol;
    }

    public String getExtParams() {
        return this.resultObject.extParams;
    }

    public String getRetMessageSub() {
        return this.resultObject.retMessageSub;
    }

    public String getMessage() {
        return this.resultObject.message;
    }

    public String getRetCodeSub() {
        return this.resultObject.retCodeSub;
    }

    public String getRetCode() {
        return this.resultObject.retCode;
    }

    public String getOssEndPoint() {
        return this.resultObject.ossEndPoint;
    }

    public String getAccessKeyId() {
        return this.resultObject.accessKeyId;
    }

    public String getAccessKeySecret() {
        return this.resultObject.accessKeySecret;
    }

    public String getSecurityToken() {
        return this.resultObject.securityToken;
    }

    public String getBucketName() {
        return this.resultObject.bucketName;
    }

    public String getFileName() {
        return this.resultObject.fileNamePrefix;
    }
}
