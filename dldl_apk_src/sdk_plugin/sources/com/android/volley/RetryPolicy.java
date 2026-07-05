package com.android.volley;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public interface RetryPolicy {
    int getCurrentRetryCount();

    int getCurrentTimeout();

    void retry(VolleyError volleyError) throws VolleyError;
}
