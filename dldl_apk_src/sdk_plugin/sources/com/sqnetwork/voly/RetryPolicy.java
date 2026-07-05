package com.sqnetwork.voly;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public interface RetryPolicy {
    int getCurrentRetryCount();

    int getCurrentTimeout();

    int getReadTimeout();

    int getRemainingRetryCount();

    int getWriteTimeout();

    void retry(Request<?> request, VolleyError error) throws VolleyError;
}
