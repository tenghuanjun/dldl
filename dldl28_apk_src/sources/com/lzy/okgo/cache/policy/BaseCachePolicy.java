package com.lzy.okgo.cache.policy;

import android.graphics.Bitmap;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.Callback;
import com.lzy.okgo.db.CacheManager;
import com.lzy.okgo.exception.HttpException;
import com.lzy.okgo.request.base.Request;
import com.lzy.okgo.utils.HeaderParser;
import com.lzy.okgo.utils.HttpUtils;
import java.io.IOException;
import java.net.SocketTimeoutException;
import okhttp3.Call;
import okhttp3.Headers;
import okhttp3.Response;

/* JADX INFO: loaded from: classes3.dex */
public abstract class BaseCachePolicy<T> implements CachePolicy<T> {
    protected CacheEntity<T> cacheEntity;
    protected volatile boolean canceled;
    protected volatile int currentRetryCount = 0;
    protected boolean executed;
    protected Callback<T> mCallback;
    protected Call rawCall;
    protected Request<T, ? extends Request> request;

    @Override // com.lzy.okgo.cache.policy.CachePolicy
    public boolean onAnalysisResponse(Call call, Response response) {
        return false;
    }

    public BaseCachePolicy(Request<T, ? extends Request> request) {
        this.request = request;
    }

    @Override // com.lzy.okgo.cache.policy.CachePolicy
    public CacheEntity<T> prepareCache() {
        if (this.request.getCacheKey() == null) {
            Request<T, ? extends Request> request = this.request;
            request.cacheKey(HttpUtils.createUrlFromParams(request.getBaseUrl(), this.request.getParams().urlParamsMap));
        }
        if (this.request.getCacheMode() == null) {
            this.request.cacheMode(CacheMode.NO_CACHE);
        }
        CacheMode cacheMode = this.request.getCacheMode();
        if (cacheMode != CacheMode.NO_CACHE) {
            CacheEntity<T> cacheEntity = (CacheEntity<T>) CacheManager.getInstance().get(this.request.getCacheKey());
            this.cacheEntity = cacheEntity;
            HeaderParser.addCacheHeaders(this.request, cacheEntity, cacheMode);
            CacheEntity<T> cacheEntity2 = this.cacheEntity;
            if (cacheEntity2 != null && cacheEntity2.checkExpire(cacheMode, this.request.getCacheTime(), System.currentTimeMillis())) {
                this.cacheEntity.setExpire(true);
            }
        }
        CacheEntity<T> cacheEntity3 = this.cacheEntity;
        if (cacheEntity3 == null || cacheEntity3.isExpire() || this.cacheEntity.getData() == null || this.cacheEntity.getResponseHeaders() == null) {
            this.cacheEntity = null;
        }
        return this.cacheEntity;
    }

    @Override // com.lzy.okgo.cache.policy.CachePolicy
    public synchronized Call prepareRawCall() throws Throwable {
        if (this.executed) {
            throw HttpException.COMMON("Already executed!");
        }
        this.executed = true;
        this.rawCall = this.request.getRawCall();
        if (this.canceled) {
            this.rawCall.cancel();
        }
        return this.rawCall;
    }

    protected com.lzy.okgo.model.Response<T> requestNetworkSync() {
        try {
            Response responseExecute = this.rawCall.execute();
            int iCode = responseExecute.code();
            if (iCode != 404 && iCode < 500) {
                T tConvertResponse = this.request.getConverter().convertResponse(responseExecute);
                saveCache(responseExecute.headers(), tConvertResponse);
                return com.lzy.okgo.model.Response.success(false, tConvertResponse, this.rawCall, responseExecute);
            }
            return com.lzy.okgo.model.Response.error(false, this.rawCall, responseExecute, HttpException.NET_ERROR());
        } catch (Throwable th) {
            if ((th instanceof SocketTimeoutException) && this.currentRetryCount < this.request.getRetryCount()) {
                this.currentRetryCount++;
                this.rawCall = this.request.getRawCall();
                if (this.canceled) {
                    this.rawCall.cancel();
                } else {
                    requestNetworkSync();
                }
            }
            return com.lzy.okgo.model.Response.error(false, this.rawCall, null, th);
        }
    }

    protected void requestNetworkAsync() {
        this.rawCall.enqueue(new okhttp3.Callback() { // from class: com.lzy.okgo.cache.policy.BaseCachePolicy.1
            @Override // okhttp3.Callback
            public void onFailure(Call call, IOException iOException) {
                if ((iOException instanceof SocketTimeoutException) && BaseCachePolicy.this.currentRetryCount < BaseCachePolicy.this.request.getRetryCount()) {
                    BaseCachePolicy.this.currentRetryCount++;
                    BaseCachePolicy baseCachePolicy = BaseCachePolicy.this;
                    baseCachePolicy.rawCall = baseCachePolicy.request.getRawCall();
                    if (BaseCachePolicy.this.canceled) {
                        BaseCachePolicy.this.rawCall.cancel();
                        return;
                    } else {
                        BaseCachePolicy.this.rawCall.enqueue(this);
                        return;
                    }
                }
                if (call.getCanceled()) {
                    return;
                }
                BaseCachePolicy.this.onError(com.lzy.okgo.model.Response.error(false, call, null, iOException));
            }

            @Override // okhttp3.Callback
            public void onResponse(Call call, Response response) throws IOException {
                int iCode = response.code();
                if (iCode == 404 || iCode >= 500) {
                    BaseCachePolicy.this.onError(com.lzy.okgo.model.Response.error(false, call, response, HttpException.NET_ERROR()));
                } else {
                    if (BaseCachePolicy.this.onAnalysisResponse(call, response)) {
                        return;
                    }
                    try {
                        T tConvertResponse = BaseCachePolicy.this.request.getConverter().convertResponse(response);
                        BaseCachePolicy.this.saveCache(response.headers(), tConvertResponse);
                        BaseCachePolicy.this.onSuccess(com.lzy.okgo.model.Response.success(false, tConvertResponse, call, response));
                    } catch (Throwable th) {
                        BaseCachePolicy.this.onError(com.lzy.okgo.model.Response.error(false, call, response, th));
                    }
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void saveCache(Headers headers, T t) {
        if (this.request.getCacheMode() == CacheMode.NO_CACHE || (t instanceof Bitmap)) {
            return;
        }
        CacheEntity<T> cacheEntityCreateCacheEntity = HeaderParser.createCacheEntity(headers, t, this.request.getCacheMode(), this.request.getCacheKey());
        if (cacheEntityCreateCacheEntity == null) {
            CacheManager.getInstance().remove(this.request.getCacheKey());
        } else {
            CacheManager.getInstance().replace(this.request.getCacheKey(), cacheEntityCreateCacheEntity);
        }
    }

    protected void runOnUiThread(Runnable runnable) {
        OkGo.getInstance().getDelivery().post(runnable);
    }

    @Override // com.lzy.okgo.cache.policy.CachePolicy
    public boolean isExecuted() {
        return this.executed;
    }

    @Override // com.lzy.okgo.cache.policy.CachePolicy
    public void cancel() {
        this.canceled = true;
        Call call = this.rawCall;
        if (call != null) {
            call.cancel();
        }
    }

    @Override // com.lzy.okgo.cache.policy.CachePolicy
    public boolean isCanceled() {
        boolean z = true;
        if (this.canceled) {
            return true;
        }
        synchronized (this) {
            Call call = this.rawCall;
            if (call == null || !call.getCanceled()) {
                z = false;
            }
        }
        return z;
    }
}
