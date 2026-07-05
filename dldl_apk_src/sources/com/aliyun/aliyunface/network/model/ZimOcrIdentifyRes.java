package com.aliyun.aliyunface.network.model;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class ZimOcrIdentifyRes extends ZimResBase {
    public ResultObjectInfo ResultObject;

    public static class ResultObjectInfo {
        public OCRInfo ocrInfo;
        public String retCode;
        public String retCodeSub;
        public String retMessageSub;
    }

    public boolean isValid() {
        ResultObjectInfo resultObjectInfo = this.ResultObject;
        return (resultObjectInfo == null || resultObjectInfo.ocrInfo == null) ? false : true;
    }
}
