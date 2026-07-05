package com.huyaudbunify.bean;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class ReqLoginMobileSendSms {
    int codeDigit;
    int deliverType;
    String mobile;
    String userAction;

    public int getDeliverType() {
        return this.deliverType;
    }

    public void setDeliverType(int i) {
        this.deliverType = i;
    }

    public int getCodeDigit() {
        return this.codeDigit;
    }

    public void setCodeDigit(int i) {
        this.codeDigit = i;
    }

    public String getUserAction() {
        return this.userAction;
    }

    public void setUserAction(String str) {
        this.userAction = str;
    }

    public String getMobile() {
        return this.mobile;
    }

    public void setMobile(String str) {
        this.mobile = str;
    }
}
