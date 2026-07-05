package com.duowan.auk.http.v2.cachestrategy;

import com.duowan.auk.http.v2.HttpFunction;
import com.duowan.auk.http.v2.RspCache;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class NetOnly<T> extends BaseCacheStrategy<T> {
    @Override // com.duowan.auk.http.v2.cachestrategy.CacheResponseListener
    public void onResponse(RspCache<T> rspCache) {
    }

    public NetOnly(HttpFunction<T> httpFunction) {
        super(httpFunction);
    }

    @Override // com.duowan.auk.http.v2.cachestrategy.BaseCacheStrategy
    public void execute() {
        executeFromNet(true);
    }
}
