package com.sy37sdk.account.floatview.request.bean;

import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class FetchFloatWindowRedDotRspBean {
    private int number;
    private int priority;
    private String title;
    private String warning_category;
    private String warning_msg;
    private String warning_type;

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public int getNumber() {
        return this.number;
    }

    public void setNumber(int i) {
        this.number = i;
    }

    public String toString() {
        return "FetchFloatViewRedRspBean{number=" + this.number + ", title='" + this.title + '\'' + AbstractJsonLexerKt.END_OBJ;
    }

    public String getWarningCategory() {
        return this.warning_category;
    }

    public String getWarningMsg() {
        return this.warning_msg;
    }

    public String getWarningType() {
        return this.warning_type;
    }

    public int getPriority() {
        return this.priority;
    }
}
