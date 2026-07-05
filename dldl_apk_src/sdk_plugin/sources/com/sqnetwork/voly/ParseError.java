package com.sqnetwork.voly;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class ParseError extends VolleyError {
    public ParseError() {
    }

    public ParseError(NetworkResponse networkResponse) {
        super(networkResponse);
    }

    public ParseError(Throwable cause) {
        super(cause);
    }

    public ParseError(String msg, Throwable cause) {
        super(msg, cause);
    }
}
