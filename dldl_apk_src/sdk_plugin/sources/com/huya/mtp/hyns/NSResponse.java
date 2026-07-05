package com.huya.mtp.hyns;

import com.huya.mtp.http.NetworkResponse;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class NSResponse<T> {
    private final int mCode;
    private final Object mExtraObject;
    private boolean mFromCache;
    private final NetworkResponse mNetworkResponse;
    private final T mRsp;

    public NSResponse(T t, NetworkResponse networkResponse, int i, Object obj) {
        this.mRsp = t;
        this.mNetworkResponse = networkResponse;
        this.mCode = i;
        this.mExtraObject = obj;
    }

    void setFromCache(boolean z) {
        this.mFromCache = z;
    }

    public T getData() {
        return this.mRsp;
    }

    public NetworkResponse getNetworkResponse() {
        return this.mNetworkResponse;
    }

    public boolean isFromCache() {
        return this.mFromCache;
    }

    public int getCode() {
        return this.mCode;
    }

    public Object getExtraObject() {
        return this.mExtraObject;
    }
}
