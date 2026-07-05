package com.sdk.sq.net;

import com.sqnetwork.voly.VolleyError;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
@Deprecated
public interface IHttpRequestCallBack {
    void onRequestError(VolleyError error);

    void onRequestSuccess(String content);
}
