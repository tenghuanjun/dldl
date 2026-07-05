package com.duowan.auk.http.v2.exception;

import com.android.volley.NetworkResponse;
import com.android.volley.VolleyError;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class HttpV2Error extends VolleyError {
    public HttpV2Error() {
    }

    public HttpV2Error(NetworkResponse networkResponse) {
        super(networkResponse);
    }

    public HttpV2Error(String str) {
        super(str);
    }

    public HttpV2Error(String str, Throwable th) {
        super(str, th);
    }

    public HttpV2Error(Throwable th) {
        super(th);
    }
}
