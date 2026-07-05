package com.duowan.auk.http.v2;

import com.android.volley.NetworkResponse;
import com.android.volley.VolleyError;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public interface HttpResponseDelegate<Rsp> {
    void deliverError(VolleyError volleyError);

    void deliverResponse(Rsp rsp);

    Rsp parseResponse(NetworkResponse networkResponse) throws VolleyError;
}
