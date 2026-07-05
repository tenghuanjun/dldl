package com.huyaudbunify.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class ReqLogin {
    Boolean isAuthLogin;
    String name;
    String password;
    String userAction;
    Map<String, String> lgnExtParam = new HashMap();
    List<String> bizAppids = new ArrayList();

    public String getUserAction() {
        return this.userAction;
    }

    public void setUserAction(String str) {
        this.userAction = str;
    }

    public Map<String, String> getLgnExtParam() {
        return this.lgnExtParam;
    }

    public void setLgnExtParam(Map<String, String> map) {
        this.lgnExtParam = map;
    }

    public List<String> getBizAppids() {
        return this.bizAppids;
    }

    public void setBizAppids(List<String> list) {
        this.bizAppids = list;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String str) {
        this.password = str;
    }

    public Boolean getAuthLogin() {
        return this.isAuthLogin;
    }

    public void setAuthLogin(Boolean bool) {
        this.isAuthLogin = bool;
    }
}
