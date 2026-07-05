package com.huyaudbunify.bean;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class ReqGetTicket {
    String bizAppid;
    long uid;
    String yytok_str;
    int yytok_type;

    public long getUid() {
        return this.uid;
    }

    public void setUid(long j) {
        this.uid = j;
    }

    public String getBizAppid() {
        return this.bizAppid;
    }

    public void setBizAppid(String str) {
        this.bizAppid = str;
    }

    public int getYytok_type() {
        return this.yytok_type;
    }

    public void setYytok_type(int i) {
        this.yytok_type = i;
    }

    public String getYytok_str() {
        return this.yytok_str;
    }

    public void setYytok_str(String str) {
        this.yytok_str = str;
    }
}
