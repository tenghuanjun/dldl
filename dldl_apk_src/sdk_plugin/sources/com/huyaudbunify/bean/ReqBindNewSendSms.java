package com.huyaudbunify.bean;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class ReqBindNewSendSms {
    int deliverType;
    String mobile;
    long uid;

    public long getUid() {
        return this.uid;
    }

    public void setUid(long j) {
        this.uid = j;
    }

    public String getMobile() {
        return this.mobile;
    }

    public void setMobile(String str) {
        this.mobile = str;
    }

    public int getDeliverType() {
        return this.deliverType;
    }

    public void setDeliverType(int i) {
        this.deliverType = i;
    }
}
