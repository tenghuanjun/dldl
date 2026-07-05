package com.huyaudbunify.bean;

import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class ResponseHeander {
    String description;
    String extParam;
    String message;
    int ret;

    public String getExtParam() {
        return this.extParam;
    }

    public void setExtParam(String str) {
        this.extParam = str;
    }

    public int getRet() {
        return this.ret;
    }

    public void setRet(int i) {
        this.ret = i;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public String toString() {
        return "ResponseHeander{ret=" + this.ret + ", message='" + this.message + "', description='" + this.description + '\'' + AbstractJsonLexerKt.END_OBJ;
    }
}
