package com.duowan.auk.http.v2.cachestrategy;

import com.duowan.auk.http.v2.RspCache;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public interface CacheResponseListener<T> {
    void onResponse(RspCache<T> rspCache);
}
