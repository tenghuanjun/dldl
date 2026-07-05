package com.huyaudbunify.bean;

import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class LoginData {
    String appCommonData;
    AppLoginData apploginData;
    int bypass;
    int defaultBypass;
    YYLoginData yyloginData;

    public int getBypass() {
        return this.bypass;
    }

    public void setBypass(int i) {
        this.bypass = i;
    }

    public int getDefaultBypass() {
        return this.defaultBypass;
    }

    public void setDefaultBypass(int i) {
        this.defaultBypass = i;
    }

    public AppLoginData getApploginData() {
        return this.apploginData;
    }

    public void setApploginData(AppLoginData appLoginData) {
        this.apploginData = appLoginData;
    }

    public YYLoginData getYyloginData() {
        return this.yyloginData;
    }

    public void setYyloginData(YYLoginData yYLoginData) {
        this.yyloginData = yYLoginData;
    }

    public String getAppCommonData() {
        return this.appCommonData;
    }

    public void setAppCommonData(String str) {
        this.appCommonData = str;
    }

    public String toString() {
        return "LoginData{apploginData=" + this.apploginData + ", yyloginData=" + this.yyloginData + ", bypass=" + this.bypass + ", defaultBypass=" + this.defaultBypass + ", appCommonData='" + this.appCommonData + '\'' + AbstractJsonLexerKt.END_OBJ;
    }
}
