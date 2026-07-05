package com.huyaudbunify.bean;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class ReqBindNewVerifySms {
    int deliverType;
    String newmobile;
    String smscode;
    long uid;

    public long getUid() {
        return this.uid;
    }

    public void setUid(long j) {
        this.uid = j;
    }

    public String getNewmobile() {
        return this.newmobile;
    }

    public void setNewmobile(String str) {
        this.newmobile = str;
    }

    public String getSmscode() {
        return this.smscode;
    }

    public void setSmscode(String str) {
        this.smscode = str;
    }

    public int getDeliverType() {
        return this.deliverType;
    }

    public void setDeliverType(int i) {
        this.deliverType = i;
    }
}
