package com.duowan.auk.http.v2;

import com.android.volley.VolleyError;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public interface ResponseListener<T> {
    void onError(VolleyError volleyError);

    void onResponse(T t, boolean z);
}
