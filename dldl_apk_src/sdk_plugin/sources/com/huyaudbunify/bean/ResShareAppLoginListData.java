package com.huyaudbunify.bean;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class ResShareAppLoginListData {
    List<ResShareAppLoginData> loginDataList = new ArrayList();
    String version;

    public String getVersion() {
        return this.version;
    }

    public void setVersion(String str) {
        this.version = str;
    }

    public List<ResShareAppLoginData> getLoginDataList() {
        return this.loginDataList;
    }

    public void setLoginDataList(List<ResShareAppLoginData> list) {
        this.loginDataList = list;
    }
}
