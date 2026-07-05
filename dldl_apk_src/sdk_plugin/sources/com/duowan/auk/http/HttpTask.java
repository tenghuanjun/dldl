package com.duowan.auk.http;

import android.text.TextUtils;
import android.util.Log;
import com.android.volley.AuthFailureError;
import com.android.volley.Cache;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.duowan.auk.http.HttpClient;
import com.duowan.auk.util.L;
import com.j256.ormlite.stmt.query.SimpleComparison;
import java.lang.ref.WeakReference;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.jvm.internal.LongCompanionObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class HttpTask extends Request<byte[]> {
    private WeakReference<Cache> mCache;
    private String mCacheKey;
    private HttpClient.RequestParams.CacheType mCacheType;
    private byte[] mData;
    private HttpClient.HttpHandler mHandler;
    private Map<String, List<String>> mHeaders;
    private Map<String, String> mInputHeaders;
    private long mNetworkTimeMs;
    private long mSoftTtl;
    private int mStatusCode;
    private long mTtl;

    private static String initUrl(String str, HttpClient.RequestParams requestParams) {
        if (requestParams == null) {
            return str;
        }
        try {
            StringBuilder sb = new StringBuilder(str);
            boolean z = !str.contains("?");
            Map<String, String> urlParams = requestParams.getUrlParams();
            for (String str2 : urlParams.keySet()) {
                if (z) {
                    sb.append("?");
                    z = false;
                } else {
                    sb.append("&");
                }
                String str3 = urlParams.get(str2);
                sb.append(str2);
                sb.append(SimpleComparison.EQUAL_TO_OPERATION);
                sb.append(TextUtils.isEmpty(str3) ? "" : URLEncoder.encode(str3, "UTF-8"));
            }
            return sb.toString();
        } catch (Exception unused) {
            return str;
        }
    }

    public HttpTask(int i, String str, HttpClient.RequestParams requestParams, HttpClient.HttpHandler httpHandler) {
        super(i, initUrl(str, requestParams), null);
        this.mCacheType = null;
        this.mCache = null;
        this.mTtl = 0L;
        this.mSoftTtl = 0L;
        this.mInputHeaders = requestParams.getHeaders();
        this.mHandler = httpHandler;
        this.mCacheKey = requestParams.getCacheKey();
        setShouldCache(!TextUtils.isEmpty(r2));
        setRetryPolicy(new DefaultRetryPolicy(requestParams.getTimeout(), 0, 0.0f));
        this.mCacheType = requestParams.getCacheType();
        this.mTtl = requestParams.getTtl();
        this.mSoftTtl = requestParams.getSoftTtl();
    }

    public void updateCacheType(Cache cache) {
        if (cache == null || this.mCacheKey == null || this.mCacheType == null) {
            return;
        }
        this.mCache = new WeakReference<>(cache);
        Cache.Entry entry = cache.get(this.mCacheKey);
        if (entry != null) {
            if (this.mTtl <= 0) {
                this.mTtl = entry.ttl;
            }
            if (this.mSoftTtl <= 0) {
                this.mSoftTtl = entry.softTtl;
            }
            int i = AnonymousClass3.$SwitchMap$com$duowan$auk$http$HttpClient$RequestParams$CacheType[this.mCacheType.ordinal()];
            if (i == 1) {
                entry.softTtl = LongCompanionObject.MAX_VALUE;
                entry.ttl = LongCompanionObject.MAX_VALUE;
            } else if (i == 2) {
                entry.softTtl = 0L;
                entry.ttl = LongCompanionObject.MAX_VALUE;
            } else if (i == 3) {
                entry.softTtl = 0L;
                entry.ttl = 0L;
            }
            cache.put(this.mCacheKey, entry);
        }
    }

    /* JADX INFO: renamed from: com.duowan.auk.http.HttpTask$3, reason: invalid class name */
    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] $SwitchMap$com$duowan$auk$http$HttpClient$RequestParams$CacheType;

        static {
            int[] iArr = new int[HttpClient.RequestParams.CacheType.values().length];
            $SwitchMap$com$duowan$auk$http$HttpClient$RequestParams$CacheType = iArr;
            try {
                iArr[HttpClient.RequestParams.CacheType.EXE_TYPE_CACHE_ONLY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$duowan$auk$http$HttpClient$RequestParams$CacheType[HttpClient.RequestParams.CacheType.EXE_TYPE_CACHE_THEN_NET.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$duowan$auk$http$HttpClient$RequestParams$CacheType[HttpClient.RequestParams.CacheType.EXE_TYPE_NET_ONLY.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$duowan$auk$http$HttpClient$RequestParams$CacheType[HttpClient.RequestParams.CacheType.EXE_TYPE_AS_CONFIG.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    @Override // com.android.volley.Request
    public Map<String, String> getHeaders() throws AuthFailureError {
        return this.mInputHeaders;
    }

    @Override // com.android.volley.Request
    public void deliverError(final VolleyError volleyError) {
        HttpClient.msHandlerExecutor.execute(new Runnable() { // from class: com.duowan.auk.http.HttpTask.1
            @Override // java.lang.Runnable
            public void run() {
                HttpTask.this.parse(volleyError.networkResponse);
                HttpTask.this.onFail(volleyError);
            }
        });
    }

    @Override // com.android.volley.Request
    public String getCacheKey() {
        return this.mCacheKey;
    }

    @Override // com.android.volley.Request
    protected Response<byte[]> parseNetworkResponse(NetworkResponse networkResponse) {
        parse(networkResponse);
        return Response.success(networkResponse.data, getCache(networkResponse));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.volley.Request
    public void deliverResponse(final byte[] bArr) {
        HttpClient.msHandlerExecutor.execute(new Runnable() { // from class: com.duowan.auk.http.HttpTask.2
            @Override // java.lang.Runnable
            public void run() {
                Cache cache;
                if (HttpTask.this.mCache != null && (cache = (Cache) HttpTask.this.mCache.get()) != null && HttpTask.this.mCacheKey != null && cache.get(HttpTask.this.mCacheKey) != null) {
                    Cache.Entry entry = cache.get(HttpTask.this.mCacheKey);
                    if (HttpTask.this.mSoftTtl > 0) {
                        entry.softTtl = HttpTask.this.mSoftTtl;
                    } else {
                        entry.softTtl = 1000L;
                    }
                    if (HttpTask.this.mTtl > 0) {
                        entry.ttl = HttpTask.this.mTtl;
                    }
                    cache.put(HttpTask.this.mCacheKey, entry);
                }
                HttpTask.this.onSuccess(bArr);
            }
        });
    }

    private Cache.Entry getCache(NetworkResponse networkResponse) {
        if (!shouldCache()) {
            return null;
        }
        Cache.Entry entry = new Cache.Entry();
        entry.data = networkResponse.data;
        entry.responseHeaders = networkResponse.headers;
        entry.softTtl = 1000L;
        return entry;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onFail(VolleyError volleyError) {
        try {
            if (this.mHandler != null) {
                this.mHandler.onFailure(this.mStatusCode, this.mHeaders, this.mData, volleyError);
            }
            Iterator<HttpClient.GlobalListener> it = HttpClient.msGlobalListenerList.iterator();
            while (it.hasNext()) {
                it.next().onFinished(getUrl(), this.mStatusCode, this.mNetworkTimeMs);
            }
        } catch (Throwable th) {
            if (HttpClient.msCatchThrowable) {
                L.error(this, Log.getStackTraceString(th));
                return;
            }
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onSuccess(byte[] bArr) {
        try {
            if (this.mHandler != null) {
                this.mHandler.onSuccess(this.mStatusCode, this.mHeaders, this.mData);
            }
            Iterator<HttpClient.GlobalListener> it = HttpClient.msGlobalListenerList.iterator();
            while (it.hasNext()) {
                it.next().onFinished(getUrl(), this.mStatusCode, this.mNetworkTimeMs);
            }
        } catch (Throwable th) {
            if (HttpClient.msCatchThrowable) {
                L.error(this, Log.getStackTraceString(th));
                return;
            }
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void parse(NetworkResponse networkResponse) {
        if (networkResponse == null) {
            return;
        }
        this.mHeaders = formatHeaders(networkResponse.headers);
        this.mStatusCode = networkResponse.statusCode;
        this.mData = networkResponse.data;
        this.mNetworkTimeMs = networkResponse.networkTimeMs;
    }

    private Map<String, List<String>> formatHeaders(Map<String, String> map) {
        HashMap map2 = new HashMap(map.size());
        for (Map.Entry<String, String> entry : map.entrySet()) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(entry.getValue());
            map2.put(entry.getKey(), arrayList);
        }
        return map2;
    }
}
