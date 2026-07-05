package com.huyaudbunify.dialog.msg;

import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class AuthLoginStrategyBean {
    private String data;
    private String source;

    public String getSource() {
        return this.source;
    }

    public void setSource(String str) {
        this.source = str;
    }

    public String getData() {
        return this.data;
    }

    public void setData(String str) {
        this.data = str;
    }

    public String toString() {
        return "AuthLoginStrategyBean{source='" + this.source + "', data='" + this.data + '\'' + AbstractJsonLexerKt.END_OBJ;
    }
}
