package com.duowan.auk.http.v2.executor;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.duowan.auk.http.HttpClient;
import com.duowan.auk.http.v2.HttpRequestDelegate;
import com.duowan.auk.http.v2.HttpResponseDelegate;
import com.duowan.auk.util.L;
import java.util.AbstractMap;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
class AccurateTimeOutHttpRequest<Rsp> extends Request<Rsp> {
    private static final String TAG = "AccurateTimeOutHttpRequest";
    private static Map<Map.Entry<HttpRequestDelegate, HttpResponseDelegate<?>>, AccurateTimeOutHttpRequest<?>> mRequestMap = new HashMap();
    private static Handler sMainHandler = new Handler(Looper.getMainLooper());
    private Map.Entry<HttpRequestDelegate, HttpResponseDelegate<Rsp>> mEntry;
    private HttpRequestDelegate mRequestDelegate;
    private HttpResponseDelegate<Rsp> mResponseDelegate;
    private Runnable mTimeOutRunnable;

    public static <Rsp> AccurateTimeOutHttpRequest<Rsp> getInstance(HttpRequestDelegate httpRequestDelegate, HttpResponseDelegate<Rsp> httpResponseDelegate) {
        AccurateTimeOutHttpRequest<Rsp> accurateTimeOutHttpRequest;
        AbstractMap.SimpleEntry simpleEntry = new AbstractMap.SimpleEntry(httpRequestDelegate, httpResponseDelegate);
        synchronized (mRequestMap) {
            accurateTimeOutHttpRequest = (AccurateTimeOutHttpRequest) mRequestMap.get(simpleEntry);
            if (accurateTimeOutHttpRequest == null) {
                accurateTimeOutHttpRequest = new AccurateTimeOutHttpRequest<>(simpleEntry);
                mRequestMap.put(simpleEntry, accurateTimeOutHttpRequest);
            }
        }
        return accurateTimeOutHttpRequest;
    }

    public AccurateTimeOutHttpRequest(Map.Entry<HttpRequestDelegate, HttpResponseDelegate<Rsp>> entry) {
        super(entry.getKey().getMethod(), entry.getKey().getUrl(), null);
        this.mTimeOutRunnable = new Runnable() { // from class: com.duowan.auk.http.v2.executor.AccurateTimeOutHttpRequest.1
            @Override // java.lang.Runnable
            public void run() {
                AccurateTimeOutHttpRequest.this.cancel();
                synchronized (AccurateTimeOutHttpRequest.mRequestMap) {
                    AccurateTimeOutHttpRequest.mRequestMap.remove(AccurateTimeOutHttpRequest.this.mEntry);
                }
                L.info(AccurateTimeOutHttpRequest.TAG, "timeOut for cacheKey:%s", ((HttpRequestDelegate) AccurateTimeOutHttpRequest.this.mEntry.getKey()).getCacheKey());
                AccurateTimeOutHttpRequest.this.deliverError(new TimeoutError());
            }
        };
        this.mEntry = entry;
        this.mRequestDelegate = entry.getKey();
        this.mResponseDelegate = entry.getValue();
        setRetryPolicy(new DefaultRetryPolicy(getTimeOut(), getMaxRetryTimes(), getBackoffMultiplier()));
        setShouldCache(false);
    }

    @Override // com.android.volley.Request
    protected Response<Rsp> parseNetworkResponse(NetworkResponse networkResponse) {
        try {
            return Response.success(parseResponse(networkResponse), null);
        } catch (VolleyError e) {
            return Response.error(e);
        }
    }

    @Override // com.android.volley.Request
    public String getBodyContentType() {
        String bodyContentType = this.mRequestDelegate.getBodyContentType();
        return TextUtils.isEmpty(bodyContentType) ? super.getBodyContentType() : bodyContentType;
    }

    @Override // com.android.volley.Request
    protected Map<String, String> getParams() throws AuthFailureError {
        Map<String, String> params = this.mRequestDelegate.getParams();
        return params == null ? Collections.emptyMap() : params;
    }

    public int getMaxRetryTimes() {
        return this.mRequestDelegate.getMaxRetryTimes();
    }

    public int getBackoffMultiplier() {
        return this.mRequestDelegate.getBackoffMultiplier();
    }

    public int getTimeOut() {
        return this.mRequestDelegate.getTimeout();
    }

    @Override // com.android.volley.Request
    public Map<String, String> getHeaders() throws AuthFailureError {
        Map<String, String> headers = this.mRequestDelegate.getHeaders();
        return headers == null ? Collections.emptyMap() : headers;
    }

    @Override // com.android.volley.Request
    public Request.Priority getPriority() {
        Request.Priority priority = this.mRequestDelegate.getPriority();
        return priority == null ? Request.Priority.NORMAL : priority;
    }

    @Override // com.android.volley.Request
    public String getCacheKey() {
        return this.mRequestDelegate.getCacheKey();
    }

    protected Rsp parseResponse(NetworkResponse networkResponse) throws VolleyError {
        return this.mResponseDelegate.parseResponse(networkResponse);
    }

    @Override // com.android.volley.Request
    public byte[] getBody() throws AuthFailureError {
        byte[] body = this.mRequestDelegate.getBody();
        return body == null ? super.getBody() : body;
    }

    @Override // com.android.volley.Request
    protected void deliverResponse(Rsp rsp) {
        requestFinish();
        this.mResponseDelegate.deliverResponse(rsp);
    }

    @Override // com.android.volley.Request
    public void deliverError(VolleyError volleyError) {
        requestFinish();
        this.mResponseDelegate.deliverError(volleyError);
    }

    public void execute() {
        scheduleTimeOut();
        HttpClient.execute(this);
    }

    @Override // com.android.volley.Request
    public void cancel() {
        requestFinish();
        super.cancel();
    }

    private void requestFinish() {
        synchronized (mRequestMap) {
            mRequestMap.remove(this.mEntry);
        }
        L.info("MultiFunctionExecutor", "requestFinish for cacheKey:%s", this.mEntry.getKey().getCacheKey());
        sMainHandler.removeCallbacks(this.mTimeOutRunnable);
    }

    private void scheduleTimeOut() {
        int maxRetryTimes = this.mEntry.getKey().getMaxRetryTimes() + 1;
        sMainHandler.postDelayed(this.mTimeOutRunnable, (r0.getTimeout() * maxRetryTimes) + (((maxRetryTimes * (maxRetryTimes - 1)) * r0.getBackoffMultiplier()) / 2));
    }
}
