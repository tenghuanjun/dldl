package com.duowan.auk.http.v2.cachestrategy;

import com.android.volley.VolleyError;
import com.duowan.auk.http.v2.HttpFunction;
import com.duowan.auk.http.v2.RspCache;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class NetFirst<T> extends BaseCacheStrategy<T> {
    private VolleyError mNetworkError;

    public NetFirst(HttpFunction<T> httpFunction) {
        super(httpFunction);
    }

    @Override // com.duowan.auk.http.v2.cachestrategy.BaseCacheStrategy
    public void execute() {
        executeFromNet(true);
    }

    @Override // com.duowan.auk.http.v2.cachestrategy.CacheResponseListener
    public void onResponse(RspCache<T> rspCache) {
        T t = rspCache.isExpired() ? null : rspCache.data;
        if (t != null) {
            this.mRequest.deliverResponseFromCache(t);
            if (rspCache.refreshNeeded()) {
                executeFromNet(false);
                return;
            }
            return;
        }
        this.mRequest.deliverErrorFromCache(this.mNetworkError);
    }

    @Override // com.duowan.auk.http.v2.cachestrategy.BaseCacheStrategy
    public boolean doHandleNetworkError(VolleyError volleyError) {
        readCache();
        this.mNetworkError = volleyError;
        return true;
    }
}
