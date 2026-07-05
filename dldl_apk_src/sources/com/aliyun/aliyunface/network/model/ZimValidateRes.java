package com.aliyun.aliyunface.network.model;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class ZimValidateRes extends ZimResBase {
    public ResultObject ResultObject;

    public static class ResultObject {
        public String extParams;
        public boolean hasNext;
        public String productRetCode;
        public String retCodeSub;
        public String retMessageSub;
        public String validationRetCode;
    }

    public boolean isValid() {
        return this.ResultObject != null;
    }

    public String getValidationRetCode() {
        return this.ResultObject.validationRetCode;
    }

    public String getProductRetCode() {
        return this.ResultObject.productRetCode;
    }

    public String getRetCodeSub() {
        return this.ResultObject.retCodeSub;
    }

    public String getRetMessageSub() {
        return this.ResultObject.retMessageSub;
    }

    public boolean isHasNext() {
        return this.ResultObject.hasNext;
    }

    public String getExtParams() {
        return this.ResultObject.extParams;
    }
}
