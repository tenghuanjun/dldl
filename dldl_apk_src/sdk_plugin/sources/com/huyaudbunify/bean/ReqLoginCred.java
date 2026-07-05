package com.huyaudbunify.bean;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class ReqLoginCred {
    List<String> bizAppids = new ArrayList();
    String hyCred;
    boolean isAuthLogin;
    boolean isStillRequest;
    long uid;
    String yyCred;

    public List<String> getBizAppids() {
        return this.bizAppids;
    }

    public void setBizAppids(List<String> list) {
        this.bizAppids = list;
    }

    public long getUid() {
        return this.uid;
    }

    public void setUid(long j) {
        this.uid = j;
    }

    public String getYyCred() {
        return this.yyCred;
    }

    public void setYyCred(String str) {
        this.yyCred = str;
    }

    public String getHyCred() {
        return this.hyCred;
    }

    public void setHyCred(String str) {
        this.hyCred = str;
    }

    public boolean isAuthLogin() {
        return this.isAuthLogin;
    }

    public void setAuthLogin(boolean z) {
        this.isAuthLogin = z;
    }

    public boolean isStillRequest() {
        return this.isStillRequest;
    }

    public void setStillRequest(boolean z) {
        this.isStillRequest = z;
    }
}
