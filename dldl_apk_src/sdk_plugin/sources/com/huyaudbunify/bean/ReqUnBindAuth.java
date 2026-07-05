package com.huyaudbunify.bean;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class ReqUnBindAuth {
    String openid;
    int opentype;
    int type;
    String unionId;

    public int getType() {
        return this.type;
    }

    public void setType(int i) {
        this.type = i;
    }

    public String getUnionId() {
        return this.unionId;
    }

    public void setUnionId(String str) {
        this.unionId = str;
    }

    public String getOpenid() {
        return this.openid;
    }

    public void setOpenid(String str) {
        this.openid = str;
    }

    public int getOpentype() {
        return this.opentype;
    }

    public void setOpentype(int i) {
        this.opentype = i;
    }
}
