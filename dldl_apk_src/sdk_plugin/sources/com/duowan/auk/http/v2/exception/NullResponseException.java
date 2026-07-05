package com.duowan.auk.http.v2.exception;

import com.android.volley.NetworkResponse;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class NullResponseException extends HttpV2Error {
    public NullResponseException() {
    }

    public NullResponseException(NetworkResponse networkResponse) {
        super(networkResponse);
    }

    public NullResponseException(String str) {
        super(str);
    }

    public NullResponseException(String str, Throwable th) {
        super(str, th);
    }

    public NullResponseException(Throwable th) {
        super(th);
    }
}
