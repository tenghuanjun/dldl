package com.sq.webview.local;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class Md5NoMatchException extends Exception {
    private final String mMsg;

    public Md5NoMatchException(String msg) {
        this.mMsg = msg;
    }

    public String getMsg() {
        return this.mMsg;
    }
}
