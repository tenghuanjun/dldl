package com.huyaudbunify.bean;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class ReqLoginMobileQuick {
    int OperatorType;
    List<String> bizAppids = new ArrayList();
    Boolean isAuthLogin;
    String operatorToken;
    String userAction;

    public String getUserAction() {
        return this.userAction;
    }

    public void setUserAction(String str) {
        this.userAction = str;
    }

    public String getOperatorToken() {
        return this.operatorToken;
    }

    public void setOperatorToken(String str) {
        this.operatorToken = str;
    }

    public int getOperatorType() {
        return this.OperatorType;
    }

    public void setOperatorType(int i) {
        this.OperatorType = i;
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
