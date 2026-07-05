package com.android.volley;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class NetworkError extends VolleyError {
    public NetworkError() {
    }

    public NetworkError(Throwable th) {
        super(th);
    }

    public NetworkError(NetworkResponse networkResponse) {
        super(networkResponse);
    }
}
