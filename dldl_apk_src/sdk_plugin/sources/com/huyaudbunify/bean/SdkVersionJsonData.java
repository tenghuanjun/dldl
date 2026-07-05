package com.huyaudbunify.bean;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class SdkVersionJsonData {
    int sdkVersionNum;
    String sdkVersionStr;

    public SdkVersionJsonData(String str, int i) {
        this.sdkVersionStr = str;
        this.sdkVersionNum = i;
    }

    public String getSdkVersionStr() {
        return this.sdkVersionStr;
    }

    public void setSdkVersionStr(String str) {
        this.sdkVersionStr = str;
    }

    public int getSdkVersionNum() {
        return this.sdkVersionNum;
    }

    public void setSdkVersionNum(int i) {
        this.sdkVersionNum = i;
    }
}
