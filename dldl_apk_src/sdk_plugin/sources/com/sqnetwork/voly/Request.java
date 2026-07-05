package com.sqnetwork.voly;

import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.sqnetwork.voly.Cache;
import com.sqnetwork.voly.Response;
import com.sqnetwork.voly.VolleyLog;
import com.sqnetwork.voly.toolbox.LocalDNS;
import com.sqnetwork.voly.toolbox.Util;
import com.sqwan.liveshow.huya.SqR;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import kotlin.text.Typography;
import okhttp3.HttpUrl;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public abstract class Request<T> implements Comparable<Request<T>> {
    private static final String DEFAULT_PARAMS_ENCODING = "UTF-8";
    private Cache.Entry mCacheEntry;
    private boolean mCanceled;
    private final int mDefaultTrafficStatsTag;
    private Response.ErrorListener mErrorListener;
    private final VolleyLog.MarkerLog mEventLog;
    private boolean mFallbackHttpsToHttp;
    private boolean mIgnoreSSL;
    private IpController mIpController;
    private LocalDNS mLocalDNS;
    private final Object mLock;
    private final int mMethod;
    private String mRealUrl;
    private NetworkRequestCompleteListener mRequestCompleteListener;
    private String mRequestId;
    private List<RequestInterceptor> mRequestInterceptors;
    private RequestQueue mRequestQueue;
    private final RequestStatus mRequestStatus;
    private boolean mResponseDelivered;
    private RetryPolicy mRetryPolicy;
    private Integer mSequence;
    private boolean mShouldCache;
    private boolean mShouldRetryAuthErrors;
    private boolean mShouldRetryServerErrors;
    private Object mTag;
    private Map<Class<?>, Object> mTags;
    private final String mUrl;
    private Map<String, Boolean> mUserConsent;

    public interface Method {
        public static final int DELETE = 3;
        public static final int DEPRECATED_GET_OR_POST = -1;
        public static final int GET = 0;
        public static final int HEAD = 4;
        public static final int OPTIONS = 5;
        public static final int PATCH = 7;
        public static final int POST = 1;
        public static final int PUT = 2;
        public static final int TRACE = 6;
    }

    interface NetworkRequestCompleteListener {
        void onNoUsableResponseReceived(Request<?> request);

        void onResponseReceived(Request<?> request, Response<?> response);
    }

    public enum Priority {
        LOW,
        NORMAL,
        HIGH,
        IMMEDIATE
    }

    protected abstract void deliverResponse(Response<T> raw, T response);

    public Map<String, String> getParams() throws AuthFailureError {
        return null;
    }

    protected String getParamsEncoding() {
        return "UTF-8";
    }

    protected VolleyError parseNetworkError(VolleyError volleyError) {
        return volleyError;
    }

    protected abstract Response<T> parseNetworkResponse(NetworkResponse response);

    @Deprecated
    public Request(String url, Response.ErrorListener listener) {
        this(-1, url, listener);
    }

    public Request(int method, String url, Response.ErrorListener listener) {
        this.mEventLog = VolleyLog.MarkerLog.ENABLED ? new VolleyLog.MarkerLog() : null;
        this.mLock = new Object();
        this.mShouldCache = true;
        this.mCanceled = false;
        this.mResponseDelivered = false;
        this.mShouldRetryServerErrors = false;
        this.mShouldRetryAuthErrors = true;
        this.mIgnoreSSL = false;
        this.mCacheEntry = null;
        this.mTags = Collections.emptyMap();
        this.mMethod = method;
        this.mUrl = url;
        this.mErrorListener = listener;
        setRetryPolicy(new DefaultRetryPolicy());
        this.mDefaultTrafficStatsTag = findDefaultTrafficStatsTag(url);
        this.mRequestStatus = new RequestStatus(this);
        this.mRequestId = UUID.randomUUID().toString();
    }

    public int getMethod() {
        return this.mMethod;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public Request<?> setTag(Object tag) {
        this.mTag = tag;
        return this;
    }

    public Object getTag() {
        return this.mTag;
    }

    public Response.ErrorListener getErrorListener() {
        Response.ErrorListener errorListener;
        synchronized (this.mLock) {
            errorListener = this.mErrorListener;
        }
        return errorListener;
    }

    public int getTrafficStatsTag() {
        return this.mDefaultTrafficStatsTag;
    }

    private static int findDefaultTrafficStatsTag(String url) {
        Uri uri;
        String host;
        if (TextUtils.isEmpty(url) || (uri = Uri.parse(url)) == null || (host = uri.getHost()) == null) {
            return 0;
        }
        return host.hashCode();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public Request<?> setRetryPolicy(RetryPolicy retryPolicy) {
        this.mRetryPolicy = retryPolicy;
        return this;
    }

    public void addMarker(String tag) {
        if (VolleyLog.MarkerLog.ENABLED) {
            this.mEventLog.add(tag, Thread.currentThread().getId());
        }
    }

    void finish(final String tag) {
        this.mRequestStatus.finish();
        if (VolleyLog.MarkerLog.ENABLED) {
            final long id = Thread.currentThread().getId();
            if (Looper.myLooper() != Looper.getMainLooper()) {
                new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.sqnetwork.voly.Request.1
                    @Override // java.lang.Runnable
                    public void run() {
                        Request.this.mEventLog.add(tag, id);
                        Request.this.mEventLog.finish(Request.this.toString());
                    }
                });
                return;
            } else {
                this.mEventLog.add(tag, id);
                this.mEventLog.finish(toString());
            }
        }
        RequestQueue requestQueue = this.mRequestQueue;
        if (requestQueue != null) {
            requestQueue.finish(this);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public Request<?> setRequestQueue(RequestQueue requestQueue) {
        this.mRequestQueue = requestQueue;
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final Request<?> setSequence(int sequence) {
        this.mSequence = Integer.valueOf(sequence);
        return this;
    }

    public final int getSequence() {
        Integer num = this.mSequence;
        if (num == null) {
            throw new IllegalStateException("getSequence called before setSequence");
        }
        return num.intValue();
    }

    public String getUrl() {
        return this.mUrl;
    }

    public String getCacheKey() {
        String url = getUrl();
        int method = getMethod();
        if (method == 0 || method == -1) {
            return url;
        }
        return Integer.toString(method) + '-' + url;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public Request<?> setCacheEntry(Cache.Entry entry) {
        this.mCacheEntry = entry;
        return this;
    }

    public Cache.Entry getCacheEntry() {
        return this.mCacheEntry;
    }

    public void cancel() {
        synchronized (this.mLock) {
            this.mCanceled = true;
            this.mErrorListener = null;
        }
    }

    public boolean isCanceled() {
        boolean z;
        synchronized (this.mLock) {
            z = this.mCanceled;
        }
        return z;
    }

    public Map<String, String> getHeaders() throws AuthFailureError {
        return Collections.emptyMap();
    }

    @Deprecated
    protected Map<String, String> getPostParams() throws AuthFailureError {
        return getParams();
    }

    @Deprecated
    protected String getPostParamsEncoding() {
        return getParamsEncoding();
    }

    @Deprecated
    public String getPostBodyContentType() {
        return getBodyContentType();
    }

    @Deprecated
    public byte[] getPostBody() throws AuthFailureError {
        Map<String, String> postParams = getPostParams();
        if (postParams == null || postParams.size() <= 0) {
            return null;
        }
        return encodeParameters(postParams, getPostParamsEncoding());
    }

    public String getBodyContentType() {
        return "application/x-www-form-urlencoded; charset=" + getParamsEncoding();
    }

    public byte[] getBody() throws AuthFailureError {
        Map<String, String> params = getParams();
        if (params == null || params.size() <= 0) {
            return null;
        }
        return encodeParameters(params, getParamsEncoding());
    }

    private byte[] encodeParameters(Map<String, String> params, String paramsEncoding) {
        StringBuilder sb = new StringBuilder();
        try {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                if (entry.getKey() == null || entry.getValue() == null) {
                    throw new IllegalArgumentException(String.format("Request#getParams() or Request#getPostParams() returned a map containing a null key or value: (%s, %s). All keys and values must be non-null.", entry.getKey(), entry.getValue()));
                }
                sb.append(URLEncoder.encode(entry.getKey(), paramsEncoding));
                sb.append('=');
                sb.append(URLEncoder.encode(entry.getValue(), paramsEncoding));
                sb.append(Typography.amp);
            }
            return sb.toString().getBytes(paramsEncoding);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Encoding not supported: " + paramsEncoding, e);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final Request<?> setShouldCache(boolean shouldCache) {
        this.mShouldCache = shouldCache;
        return this;
    }

    public final boolean shouldCache() {
        return getMethod() == 0 && this.mShouldCache;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final Request<?> setShouldRetryServerErrors(boolean shouldRetryServerErrors) {
        this.mShouldRetryServerErrors = shouldRetryServerErrors;
        return this;
    }

    public final boolean shouldRetryServerErrors() {
        return this.mShouldRetryServerErrors;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final Request<?> setShouldRetryAuthErrors(boolean shouldRetryAuthErrors) {
        this.mShouldRetryAuthErrors = shouldRetryAuthErrors;
        return this;
    }

    public final boolean shouldRetryAuthErrors() {
        return this.mShouldRetryAuthErrors;
    }

    public Priority getPriority() {
        return Priority.NORMAL;
    }

    public final int getTimeoutMs() {
        return getRetryPolicy().getCurrentTimeout();
    }

    public final int getReadTimeoutMs() {
        return getRetryPolicy().getReadTimeout();
    }

    public final int getWriteTimeoutMs() {
        return getRetryPolicy().getWriteTimeout();
    }

    public RetryPolicy getRetryPolicy() {
        return this.mRetryPolicy;
    }

    public void markDelivered() {
        synchronized (this.mLock) {
            this.mResponseDelivered = true;
        }
    }

    public boolean hasHadResponseDelivered() {
        boolean z;
        synchronized (this.mLock) {
            z = this.mResponseDelivered;
        }
        return z;
    }

    public void deliverError(VolleyError error) {
        Response.ErrorListener errorListener;
        synchronized (this.mLock) {
            errorListener = this.mErrorListener;
        }
        if (errorListener != null) {
            errorListener.onErrorResponse(error);
        }
    }

    void setNetworkRequestCompleteListener(NetworkRequestCompleteListener requestCompleteListener) {
        synchronized (this.mLock) {
            this.mRequestCompleteListener = requestCompleteListener;
        }
    }

    void notifyListenerResponseReceived(Response<?> response) {
        NetworkRequestCompleteListener networkRequestCompleteListener;
        synchronized (this.mLock) {
            networkRequestCompleteListener = this.mRequestCompleteListener;
        }
        if (networkRequestCompleteListener != null) {
            networkRequestCompleteListener.onResponseReceived(this, response);
        }
    }

    void notifyListenerResponseNotUsable() {
        NetworkRequestCompleteListener networkRequestCompleteListener;
        synchronized (this.mLock) {
            networkRequestCompleteListener = this.mRequestCompleteListener;
        }
        if (networkRequestCompleteListener != null) {
            networkRequestCompleteListener.onNoUsableResponseReceived(this);
        }
    }

    public boolean isIgnoreSSL() {
        return this.mIgnoreSSL;
    }

    public void setIgnoreSSL(boolean ignoreSSL) {
        this.mIgnoreSSL = ignoreSSL;
    }

    @Override // java.lang.Comparable
    public int compareTo(Request<T> other) {
        Priority priority = getPriority();
        Priority priority2 = other.getPriority();
        return priority == priority2 ? this.mSequence.intValue() - other.mSequence.intValue() : priority2.ordinal() - priority.ordinal();
    }

    public String toString() {
        String str = "0x" + Integer.toHexString(getTrafficStatsTag());
        StringBuilder sb = new StringBuilder();
        sb.append(isCanceled() ? "[X] " : "[ ] ");
        sb.append(getUrl());
        sb.append(" ");
        sb.append(str);
        sb.append(" ");
        sb.append(getPriority());
        sb.append(" ");
        sb.append(this.mSequence);
        return sb.toString();
    }

    public <E> Request<T> setTag(Class<? super E> type, E tag) {
        if (type == null) {
            throw new NullPointerException("type == null");
        }
        if (tag == null) {
            this.mTags.remove(type);
        } else {
            if (this.mTags.isEmpty()) {
                this.mTags = new LinkedHashMap();
            }
            this.mTags.put(type, type.cast(tag));
        }
        return this;
    }

    public <E> E getTag(Class<? extends E> type) {
        return type.cast(this.mTags.get(type));
    }

    public RequestStatus getRequestStatus() {
        return this.mRequestStatus;
    }

    public String getRequestId() {
        return this.mRequestId;
    }

    public void setRequestId(String requestId) {
        if (requestId == null || requestId.isEmpty()) {
            return;
        }
        this.mRequestId = requestId;
    }

    public boolean canFallbackHttpsToHttp() {
        return this.mFallbackHttpsToHttp;
    }

    public void setFallbackHttpsToHttp(boolean fallbackHttpsToHttp) {
        this.mFallbackHttpsToHttp = fallbackHttpsToHttp;
    }

    public String getRealUrl() {
        String str = this.mRealUrl;
        return str == null ? getUrl() : str;
    }

    public void setRealUrl(String realUrl, String reason) {
        if (HttpUrl.parse(realUrl) == null || realUrl.equals(getRealUrl())) {
            return;
        }
        addMarker(String.format("change url for %s[%s]", reason, realUrl));
        this.mRealUrl = realUrl;
    }

    public void resetRealUrl(String reason) {
        if (this.mRealUrl == null) {
            return;
        }
        addMarker(String.format("reset to origin url for %s", reason));
        this.mRealUrl = null;
    }

    public LocalDNS getLocalDNS() {
        return this.mLocalDNS;
    }

    public void setLocalDNS(LocalDNS localDNS) {
        this.mLocalDNS = localDNS;
    }

    public boolean canTriggerLocalDNS() {
        String str = this.mUrl;
        if (str == null) {
            return false;
        }
        HttpUrl httpUrl = HttpUrl.parse(str);
        LocalDNS localDNS = this.mLocalDNS;
        return (localDNS == null || !localDNS.isEnable() || httpUrl == null || Util.verifyAsIpAddress(httpUrl.host())) ? false : true;
    }

    public boolean hasLocalDNSTriggered() {
        return ((LocalDNS.LocalDNSIps) getTag(LocalDNS.LocalDNSIps.class)) != null;
    }

    public boolean hasHostNameChangeToIp() {
        String str = this.mUrl;
        if (str == null || this.mRealUrl == null) {
            return false;
        }
        HttpUrl httpUrl = HttpUrl.parse(str);
        HttpUrl httpUrl2 = HttpUrl.parse(this.mRealUrl);
        return (httpUrl2 == null || httpUrl == null || Util.verifyAsIpAddress(httpUrl.host()) || !Util.verifyAsIpAddress(httpUrl2.host())) ? false : true;
    }

    public List<RequestInterceptor> getRequestInterceptors() {
        return this.mRequestInterceptors;
    }

    public void addRequestInterceptor(RequestInterceptor requestInterceptor) {
        if (requestInterceptor == null) {
            return;
        }
        if (this.mRequestInterceptors == null) {
            this.mRequestInterceptors = new ArrayList();
        }
        this.mRequestInterceptors.add(requestInterceptor);
    }

    public String getMethodStr() {
        switch (this.mMethod) {
            case -1:
            case 0:
                return "get";
            case 1:
                return "post";
            case 2:
                return "put";
            case 3:
                return SqR.string.delete;
            case 4:
                return "head";
            case 5:
                return "options";
            case 6:
                return "trace";
            case 7:
                return "patch";
            default:
                return "unknown(" + this.mMethod + ")";
        }
    }

    public Map<String, Boolean> getUserConsent() {
        return this.mUserConsent;
    }

    public void setUserConsent(Map<String, Boolean> userConsent) {
        this.mUserConsent = userConsent;
    }

    public void setIpController(IpController controller) {
        this.mIpController = controller;
    }

    public IpController getIpController() {
        return this.mIpController;
    }
}
