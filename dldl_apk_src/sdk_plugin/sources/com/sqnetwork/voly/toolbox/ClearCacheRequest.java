package com.sqnetwork.voly.toolbox;

import android.os.Handler;
import android.os.Looper;
import com.sqnetwork.voly.Cache;
import com.sqnetwork.voly.NetworkResponse;
import com.sqnetwork.voly.Request;
import com.sqnetwork.voly.Response;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class ClearCacheRequest extends Request<Object> {
    private final Cache mCache;
    private final Runnable mCallback;

    @Override // com.sqnetwork.voly.Request
    protected void deliverResponse(Response<Object> raw, Object response) {
    }

    @Override // com.sqnetwork.voly.Request
    protected Response<Object> parseNetworkResponse(NetworkResponse response) {
        return null;
    }

    public ClearCacheRequest(Cache cache, Runnable callback) {
        super(0, null, null);
        this.mCache = cache;
        this.mCallback = callback;
    }

    @Override // com.sqnetwork.voly.Request
    public boolean isCanceled() {
        this.mCache.clear();
        if (this.mCallback == null) {
            return true;
        }
        new Handler(Looper.getMainLooper()).postAtFrontOfQueue(this.mCallback);
        return true;
    }

    @Override // com.sqnetwork.voly.Request
    public Request.Priority getPriority() {
        return Request.Priority.IMMEDIATE;
    }
}
