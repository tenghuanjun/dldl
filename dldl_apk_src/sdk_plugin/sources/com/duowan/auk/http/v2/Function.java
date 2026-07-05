package com.duowan.auk.http.v2;

import com.android.volley.VolleyError;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public abstract class Function<Rsp> implements ResponseListener<Rsp> {
    public abstract void execute();

    @Override // com.duowan.auk.http.v2.ResponseListener
    public abstract void onError(VolleyError volleyError);

    @Override // com.duowan.auk.http.v2.ResponseListener
    public abstract void onResponse(Rsp rsp, boolean z);
}
