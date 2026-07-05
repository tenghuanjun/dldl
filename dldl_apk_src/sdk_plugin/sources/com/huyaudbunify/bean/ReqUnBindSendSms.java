package com.huyaudbunify.bean;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class ReqUnBindSendSms {
    int deliverType;
    String otp;
    long uid;

    public long getUid() {
        return this.uid;
    }

    public void setUid(long j) {
        this.uid = j;
    }

    public String getOtp() {
        return this.otp;
    }

    public void setOtp(String str) {
        this.otp = str;
    }

    public int getDeliverType() {
        return this.deliverType;
    }

    public void setDeliverType(int i) {
        this.deliverType = i;
    }
}
