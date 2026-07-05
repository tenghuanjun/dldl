package com.huyaudbunify.bean;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class ReqLoginPhoneSms {
    List<String> bizAppids = new ArrayList();
    int improve;
    Boolean isAuthLogin;
    String mobile;
    String smscode;
    String userAction;

    public String getUserAction() {
        return this.userAction;
    }

    public void setUserAction(String str) {
        this.userAction = str;
    }

    public int getImprove() {
        return this.improve;
    }

    public void setImprove(int i) {
        this.improve = i;
    }

    public String getMobile() {
        return this.mobile;
    }

    public void setMobile(String str) {
        this.mobile = str;
    }

    public String getSmscode() {
        return this.smscode;
    }

    public void setSmscode(String str) {
        this.smscode = str;
    }

    public List<String> getBizAppids() {
        return this.bizAppids;
    }

    public void setBizAppids(List<String> list) {
        this.bizAppids = list;
    }

    public Boolean getAuthLogin() {
        return this.isAuthLogin;
    }

    public void setAuthLogin(Boolean bool) {
        this.isAuthLogin = bool;
    }
}
