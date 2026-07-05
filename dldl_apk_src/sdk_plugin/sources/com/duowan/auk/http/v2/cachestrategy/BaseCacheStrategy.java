package com.duowan.auk.http.v2.cachestrategy;

import android.os.Handler;
import android.os.Looper;
import com.android.volley.VolleyError;
import com.duowan.auk.http.v2.CacheType;
import com.duowan.auk.http.v2.HttpFunction;
import com.duowan.auk.http.v2.RspCache;
import com.huya.mtp.utils.HandlerPoolExecutor;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public abstract class BaseCacheStrategy<T> implements CacheResponseListener<T> {
    private static HandlerPoolExecutor mHandlerPoolExecutor = new HandlerPoolExecutor();
    private static Handler sMainHandler = new Handler(Looper.getMainLooper());
    private CacheType mCacheType;
    private boolean mNeedResponseForNet = true;
    protected HttpFunction<T> mRequest;

    protected boolean doHandleNetworkError(VolleyError volleyError) {
        return false;
    }

    protected boolean doHandleNetworkResponse(T t) {
        return false;
    }

    public abstract void execute();

    public BaseCacheStrategy(HttpFunction<T> httpFunction) {
        this.mRequest = httpFunction;
    }

    private static class CacheReadRunnable<Rsp> implements Runnable {
        private HttpFunction<Rsp> mHttpRequest;
        private CacheResponseListener<Rsp> mListener;

        public CacheReadRunnable(HttpFunction<Rsp> httpFunction, CacheResponseListener<Rsp> cacheResponseListener) {
            this.mListener = cacheResponseListener;
            this.mHttpRequest = httpFunction;
        }

        @Override // java.lang.Runnable
        public void run() {
            BaseCacheStrategy.sMainHandler.post(new CacheDispatchRunnable(this.mListener, this.mHttpRequest.getCache()));
        }
    }

    private static class CacheDispatchRunnable<Rsp> implements Runnable {
        private CacheResponseListener<Rsp> mListener;
        private RspCache<Rsp> mRsp;

        public CacheDispatchRunnable(CacheResponseListener<Rsp> cacheResponseListener, RspCache<Rsp> rspCache) {
            this.mRsp = rspCache;
            this.mListener = cacheResponseListener;
        }

        @Override // java.lang.Runnable
        public void run() {
            CacheResponseListener<Rsp> cacheResponseListener = this.mListener;
            if (cacheResponseListener != null) {
                cacheResponseListener.onResponse(this.mRsp);
            }
        }
    }

    public void readCache() {
        readCache(this);
    }

    private boolean isMainThread() {
        Looper looperMyLooper = Looper.myLooper();
        return looperMyLooper != null && looperMyLooper == Looper.getMainLooper();
    }

    private void readCache(CacheResponseListener<T> cacheResponseListener) {
        if (isMainThread()) {
            mHandlerPoolExecutor.execute(new CacheReadRunnable(this.mRequest, cacheResponseListener));
        } else if (cacheResponseListener != null) {
            cacheResponseListener.onResponse(this.mRequest.getCache());
        }
    }

    public void executeFromNet(boolean z) {
        this.mNeedResponseForNet = z;
        this.mRequest.executeFromNet();
    }

    public final boolean handleNetworkResponse(T t) {
        if (this.mNeedResponseForNet) {
            return doHandleNetworkResponse(t);
        }
        return true;
    }

    public final boolean handleNetworkError(VolleyError volleyError) {
        if (this.mNeedResponseForNet) {
            return doHandleNetworkError(volleyError);
        }
        return true;
    }
}
