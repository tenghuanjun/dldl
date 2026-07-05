package com.huyaudbunify.bean;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class ReqRegisterPhoneCode {
    List<String> bizAppids = new ArrayList();
    String mobile;
    String password;
    String smscode;

    public List<String> getBizAppids() {
        return this.bizAppids;
    }

    public void setBizAppids(List<String> list) {
        this.bizAppids = list;
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

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String str) {
        this.password = str;
    }
}
