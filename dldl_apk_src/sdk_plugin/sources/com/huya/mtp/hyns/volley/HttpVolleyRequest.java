package com.huya.mtp.hyns.volley;

import android.os.Handler;
import android.text.TextUtils;
import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.huya.mtp.data.exception.DataException;
import com.huya.mtp.data.exception.DataNetworkException;
import com.huya.mtp.data.transporter.TransportRequestListener;
import com.huya.mtp.data.transporter.Transporter;
import com.huya.mtp.data.transporter.param.HttpParams;
import com.huya.mtp.data.transporter.param.HttpResult;
import com.huya.mtp.http.HttpFunctionEntry;
import com.huya.mtp.hyns.NSFunction;
import com.huya.mtp.hyns.volley.LinearRetryPolicy;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
class HttpVolleyRequest extends Request<HttpResult> {
    private boolean isNeedTimeoutCheck;
    private byte[] mBody;
    private Map<String, String> mHeaders;
    private HttpParams mHttpParams;
    private TransportRequestListener<HttpResult> mListener;
    private Runnable mTimeOutRunnable;
    private Transporter<?, ?> mTransporter;
    private static final Map<HttpParams, HttpVolleyRequest> sRequestMap = new HashMap();
    private static Handler sDispatcherHandler = HttpFunctionEntry.sDispatchHandlerExecutor.getHandler();

    public void setNeedTimeoutCheck(boolean z) {
        this.isNeedTimeoutCheck = z;
    }

    public static HttpVolleyRequest getInstance(HttpParams httpParams, boolean z) {
        HttpVolleyRequest httpVolleyRequest;
        synchronized (sRequestMap) {
            httpVolleyRequest = sRequestMap.get(httpParams);
            if (httpVolleyRequest == null && z) {
                httpVolleyRequest = new HttpVolleyRequest(httpParams);
                sRequestMap.put(httpParams, httpVolleyRequest);
            }
        }
        return httpVolleyRequest;
    }

    @Override // com.android.volley.Request
    public String getUrl() {
        return this.mHttpParams.getUrl();
    }

    public HttpVolleyRequest(HttpParams httpParams) {
        super(httpParams.getMethod(), null, null);
        this.isNeedTimeoutCheck = false;
        this.mTimeOutRunnable = new Runnable() { // from class: com.huya.mtp.hyns.volley.HttpVolleyRequest.1
            @Override // java.lang.Runnable
            public void run() {
                HttpVolleyRequest.super.cancel();
                HttpVolleyRequest.this.deliverError(new TimeoutError());
            }
        };
        this.mHttpParams = httpParams;
        setRetryPolicy(new LinearRetryPolicy(httpParams.getTimeout(), this.mHttpParams.getTimeoutIncrement(), this.mHttpParams.getMaxRetryTimes(), new LinearRetryPolicy.CurrentRetryTimesUpdateListener() { // from class: com.huya.mtp.hyns.volley.HttpVolleyRequest.2
            @Override // com.huya.mtp.hyns.volley.LinearRetryPolicy.CurrentRetryTimesUpdateListener
            public void onCurrentRetryTimesUpdated(int i) {
                HttpVolleyRequest.this.mHttpParams.updateCurrentRetryTimes(i);
            }
        }));
        setShouldCache(false);
    }

    public HttpVolleyRequest setListener(TransportRequestListener<HttpResult> transportRequestListener) {
        this.mListener = transportRequestListener;
        return this;
    }

    public HttpVolleyRequest setTransporter(Transporter<?, ?> transporter) {
        this.mTransporter = transporter;
        return this;
    }

    @Override // com.android.volley.Request
    public Map<String, String> getHeaders() throws AuthFailureError {
        ensureHeadersAndBody();
        return this.mHeaders;
    }

    private void ensureHeadersAndBody() throws AuthFailureError {
        if (this.mHeaders == null) {
            Map<String, String> headers = this.mHttpParams.getHeaders();
            if (headers == null) {
                headers = Collections.emptyMap();
            }
            this.mHeaders = headers;
        }
        if (this.mBody == null) {
            byte[] body = this.mHttpParams.getBody();
            if (body == null) {
                body = super.getBody();
            }
            Map<String, String> map = this.mHeaders;
            if (map != null && NSFunction.GZIP.equals(map.get(NSFunction.CONTENT_ENCODING))) {
                if (body != null && body.length > 512) {
                    try {
                        body = ZipUtils.gzip(body);
                    } catch (Exception unused) {
                        this.mHeaders.remove(NSFunction.CONTENT_ENCODING);
                    }
                } else {
                    this.mHeaders.remove(NSFunction.CONTENT_ENCODING);
                }
            }
            this.mBody = body;
        }
    }

    @Override // com.android.volley.Request
    public String getBodyContentType() {
        String bodyContentType = this.mHttpParams.getBodyContentType();
        return TextUtils.isEmpty(bodyContentType) ? super.getBodyContentType() : bodyContentType;
    }

    @Override // com.android.volley.Request
    protected Map<String, String> getParams() throws AuthFailureError {
        Map<String, String> params = this.mHttpParams.getParams();
        return params == null ? Collections.emptyMap() : params;
    }

    @Override // com.android.volley.Request
    public Request.Priority getPriority() {
        Request.Priority priority = Request.Priority.values()[this.mHttpParams.getPriority().ordinal()];
        return priority == null ? Request.Priority.NORMAL : priority;
    }

    @Override // com.android.volley.Request
    public byte[] getBody() throws AuthFailureError {
        ensureHeadersAndBody();
        return this.mBody;
    }

    @Override // com.android.volley.Request
    protected Response<HttpResult> parseNetworkResponse(NetworkResponse networkResponse) {
        int length = 0;
        try {
            byte[] bArrUngzip = networkResponse.data;
            if (networkResponse.data != null) {
                length = networkResponse.data.length;
                if (NSFunction.GZIP.equals(networkResponse.headers.get(NSFunction.CONTENT_ENCODING))) {
                    bArrUngzip = ZipUtils.ungzip(networkResponse.data);
                }
            }
            return Response.success(new HttpResult(new com.huya.mtp.http.NetworkResponse(networkResponse.statusCode, bArrUngzip, networkResponse.headers, networkResponse.notModified, networkResponse.networkTimeMs), length), null);
        } catch (Exception e) {
            return Response.error(new VolleyError(e));
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.volley.Request
    public void deliverResponse(HttpResult httpResult) {
        try {
            requestFinish();
            this.mListener.onResponse(httpResult, this.mTransporter);
        } catch (DataException e) {
            doDeliverError(e);
        }
    }

    @Override // com.android.volley.Request
    public void deliverError(VolleyError volleyError) {
        DataException dataNetworkException;
        Throwable cause = volleyError.getCause();
        if (cause instanceof DataException) {
            dataNetworkException = (DataException) cause;
        } else {
            NetworkResponse networkResponse = volleyError.networkResponse;
            if (networkResponse != null) {
                dataNetworkException = new DataNetworkException("status code = " + networkResponse.statusCode, volleyError);
            } else {
                dataNetworkException = new DataNetworkException(volleyError);
            }
        }
        doDeliverError(dataNetworkException);
    }

    private void doDeliverError(DataException dataException) {
        requestFinish();
        this.mListener.onError(dataException, this.mTransporter);
    }

    @Override // com.android.volley.Request
    public void cancel() {
        requestFinish();
        this.mListener.onCancelled();
        super.cancel();
    }

    private void requestFinish() {
        synchronized (sRequestMap) {
            sRequestMap.remove(this.mHttpParams);
        }
        if (this.isNeedTimeoutCheck) {
            sDispatcherHandler.removeCallbacks(this.mTimeOutRunnable);
        }
    }

    public void execute(RequestQueue requestQueue) {
        if (this.isNeedTimeoutCheck) {
            scheduleTimeOut();
        }
        requestQueue.add(this);
    }

    private void scheduleTimeOut() {
        sDispatcherHandler.postDelayed(this.mTimeOutRunnable, getTimeOut());
    }

    private int getTimeOut() {
        int i;
        int i2;
        HttpParams httpParams = this.mHttpParams;
        int maxRetryTimes = httpParams.getMaxRetryTimes() + 1;
        int timeoutIncrement = httpParams.getTimeoutIncrement();
        int timeout = httpParams.getTimeout();
        if (maxRetryTimes > 3) {
            i = (timeout * maxRetryTimes) + ((12 * timeoutIncrement) / 2);
            i2 = ((maxRetryTimes - 3) - 1) * 3 * timeoutIncrement;
        } else {
            i = timeout * maxRetryTimes;
            i2 = ((maxRetryTimes * (maxRetryTimes - 1)) * timeoutIncrement) / 2;
        }
        return i + i2;
    }
}
