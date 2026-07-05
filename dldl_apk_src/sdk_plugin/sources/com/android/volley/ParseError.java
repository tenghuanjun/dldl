package com.android.volley;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class ParseError extends VolleyError {
    public ParseError() {
    }

    public ParseError(NetworkResponse networkResponse) {
        super(networkResponse);
    }

    public ParseError(Throwable th) {
        super(th);
    }
}
