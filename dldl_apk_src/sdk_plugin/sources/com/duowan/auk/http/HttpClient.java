package com.duowan.auk.http;

import android.content.Context;
import com.android.volley.Cache;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.duowan.auk.util.L;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class HttpClient {
    private static List<HttpFilter> mFilters = new ArrayList();
    private static List<UrlPrepare> mPrepares = new ArrayList();
    static boolean msCatchThrowable;
    static List<GlobalListener> msGlobalListenerList;
    static Executor msHandlerExecutor;
    private static RequestQueue msQueue;

    public interface GlobalListener {
        void onFinished(String str, int i, long j);
    }

    public interface HttpFilter {
        boolean filter(Request request);
    }

    public interface HttpHandler {
        void onFailure(int i, Map<String, List<String>> map, byte[] bArr, Exception exc);

        void onSuccess(int i, Map<String, List<String>> map, byte[] bArr);
    }

    interface ProgressHandler {
        void onProgress(int i, int i2);
    }

    public interface UrlPrepare {
        String prepare(String str);
    }

    public static void init(Context context) {
        init(context, false);
    }

    public static void init(Context context, boolean z) {
        msCatchThrowable = z;
        msQueue = Volley.newRequestQueue(context);
        msGlobalListenerList = new ArrayList();
        msHandlerExecutor = new Executor() { // from class: com.duowan.auk.http.HttpClient.1
            @Override // java.util.concurrent.Executor
            public void execute(Runnable runnable) {
                runnable.run();
            }
        };
        registerGlobalListener(new DefaultGlobalListener());
    }

    public static void addUrlPrepare(UrlPrepare urlPrepare) {
        if (urlPrepare != null) {
            mPrepares.add(urlPrepare);
        }
    }

    public static void removeUrlPrepare(UrlPrepare urlPrepare) {
        if (urlPrepare != null) {
            mPrepares.remove(urlPrepare);
        }
    }

    public static void addHttpFilter(HttpFilter httpFilter) {
        if (httpFilter != null) {
            mFilters.add(httpFilter);
        }
    }

    public static void removeHttpFilter(HttpFilter httpFilter) {
        if (httpFilter != null) {
            mFilters.remove(httpFilter);
        }
    }

    public static void setHandlerExecutor(Executor executor) {
        msHandlerExecutor = executor;
    }

    public static void registerGlobalListener(GlobalListener globalListener) {
        msGlobalListenerList.add(globalListener);
    }

    public static void unregisterGlobalListener(GlobalListener globalListener) {
        msGlobalListenerList.remove(globalListener);
    }

    public static HttpTask get(String str, HttpHandler httpHandler) {
        return get(prepareUrl(str), new RequestParams(), httpHandler);
    }

    public static HttpTask get(String str, RequestParams requestParams, HttpHandler httpHandler) {
        GetTask getTask = new GetTask(prepareUrl(str), requestParams, httpHandler);
        getTask.updateCacheType(msQueue.getCache());
        execute(getTask);
        return getTask;
    }

    public static HttpTask post(String str, RequestParams requestParams, HttpHandler httpHandler) {
        PostTask postTask = new PostTask(prepareUrl(str), requestParams, httpHandler);
        postTask.updateCacheType(msQueue.getCache());
        execute(postTask);
        return postTask;
    }

    public static HttpTask put(String str, RequestParams requestParams, HttpHandler httpHandler) {
        PutTask putTask = new PutTask(prepareUrl(str), requestParams, httpHandler);
        putTask.updateCacheType(msQueue.getCache());
        execute(putTask);
        return putTask;
    }

    private static String prepareUrl(String str) {
        Iterator<UrlPrepare> it = mPrepares.iterator();
        while (it.hasNext()) {
            str = it.next().prepare(str);
        }
        return str;
    }

    public static <T> void execute(Request<T> request) {
        Iterator<HttpFilter> it = mFilters.iterator();
        while (it.hasNext()) {
            if (!it.next().filter(request)) {
                return;
            }
        }
        msQueue.add(request);
    }

    public static Cache.Entry getCache(String str) {
        return msQueue.getCache().get(str);
    }

    public static void setCache(String str, Cache.Entry entry) {
        Cache cache = msQueue.getCache();
        if (entry != null) {
            cache.put(str, entry);
        } else {
            cache.remove(str);
        }
    }

    public static class RequestParams {
        private byte[] mBody;
        private String mHostUrl = "";
        private String mHostIp = "";
        private Map<String, String> mHeaders = new HashMap();
        private Map<String, String> mUrlParams = new HashMap();
        private String mBodyContentType = "";
        private Map<String, String> mBodyParams = new HashMap();
        private int mTimeout = (int) TimeUnit.SECONDS.toMillis(60);
        private String mCacheKey = "";
        private Request.Priority mPriority = null;
        private CacheType mCacheType = null;
        private long mTtl = 0;
        private long mSoftTtl = 0;

        public enum CacheType {
            EXE_TYPE_CACHE_ONLY,
            EXE_TYPE_NET_ONLY,
            EXE_TYPE_CACHE_THEN_NET,
            EXE_TYPE_AS_CONFIG
        }

        public void setTtl(long j) {
            this.mTtl = j;
        }

        public void setSoftTtl(long j) {
            this.mSoftTtl = j;
        }

        public long getTtl() {
            return this.mTtl;
        }

        public long getSoftTtl() {
            return this.mSoftTtl;
        }

        public void setCacheType(CacheType cacheType) {
            this.mCacheType = cacheType;
        }

        public CacheType getCacheType() {
            return this.mCacheType;
        }

        public int getTimeout() {
            return this.mTimeout;
        }

        public void setTimeout(int i) {
            this.mTimeout = i;
        }

        public void setHost(String str, String str2) {
            this.mHostUrl = str;
            this.mHostIp = str2;
        }

        public String getHostUrl() {
            return this.mHostUrl;
        }

        public String getHostIp() {
            return this.mHostIp;
        }

        public Map<String, String> getHeaders() {
            return this.mHeaders;
        }

        public Map<String, String> getUrlParams() {
            return this.mUrlParams;
        }

        public String getBodyContentType() {
            return this.mBodyContentType;
        }

        public Map<String, String> getBodyParams() {
            return this.mBodyParams;
        }

        public byte[] getBody() {
            return this.mBody;
        }

        public String getCacheKey() {
            return this.mCacheKey;
        }

        public void putHeader(String str, String str2) {
            this.mHeaders.put(str, str2);
        }

        public void putUrlParam(String str, String str2) {
            this.mUrlParams.put(str, str2);
        }

        public void setBodyContentType(String str) {
            this.mBodyContentType = str;
        }

        public void putBody(String str, String str2) {
            this.mBodyParams.put(str, str2);
        }

        public void putBody(byte[] bArr) {
            this.mBody = bArr;
        }

        public void setCacheKey(String str) {
            this.mCacheKey = str;
        }

        public void setPriority(Request.Priority priority) {
            this.mPriority = priority;
        }

        public Request.Priority getPriority() {
            return this.mPriority;
        }
    }

    public static class DefaultGlobalListener implements GlobalListener {
        @Override // com.duowan.auk.http.HttpClient.GlobalListener
        public void onFinished(String str, int i, long j) {
            L.info(HttpClient.class, "http: %s %d %d", str, Integer.valueOf(i), Long.valueOf(j));
        }
    }
}
