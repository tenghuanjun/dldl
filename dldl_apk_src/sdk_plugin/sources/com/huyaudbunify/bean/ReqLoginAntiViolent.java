package com.huyaudbunify.bean;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class ReqLoginAntiViolent {
    List<String> bizAppids = new ArrayList();
    long uid;
    String violentToken;

    public long getUid() {
        return this.uid;
    }

    public void setUid(long j) {
        this.uid = j;
    }

    public String getViolentToken() {
        return this.violentToken;
    }

    public void setViolentToken(String str) {
        this.violentToken = str;
    }

    public List<String> getBizAppids() {
        return this.bizAppids;
    }

    public void setBizAppids(List<String> list) {
        this.bizAppids = list;
    }
}
