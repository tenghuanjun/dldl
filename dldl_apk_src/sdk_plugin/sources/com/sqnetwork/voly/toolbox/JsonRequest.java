package com.sqnetwork.voly.toolbox;

import com.sqnetwork.voly.NetworkResponse;
import com.sqnetwork.voly.Request;
import com.sqnetwork.voly.Response;
import com.sqnetwork.voly.VolleyLog;
import java.io.UnsupportedEncodingException;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public abstract class JsonRequest<T> extends Request<T> {
    protected static final String PROTOCOL_CHARSET = "utf-8";
    private static final String PROTOCOL_CONTENT_TYPE = String.format("application/json; charset=%s", PROTOCOL_CHARSET);
    private Response.Listener<T> mListener;
    private final Object mLock;
    private final String mRequestBody;

    @Override // com.sqnetwork.voly.Request
    protected abstract Response<T> parseNetworkResponse(NetworkResponse response);

    @Deprecated
    public JsonRequest(String url, String requestBody, Response.Listener<T> listener, Response.ErrorListener errorListener) {
        this(-1, url, requestBody, listener, errorListener);
    }

    public JsonRequest(int method, String url, String requestBody, Response.Listener<T> listener, Response.ErrorListener errorListener) {
        super(method, url, errorListener);
        this.mLock = new Object();
        this.mListener = listener;
        this.mRequestBody = requestBody;
    }

    @Override // com.sqnetwork.voly.Request
    public void cancel() {
        super.cancel();
        synchronized (this.mLock) {
            this.mListener = null;
        }
    }

    @Override // com.sqnetwork.voly.Request
    protected void deliverResponse(Response<T> raw, T response) {
        Response.Listener<T> listener;
        synchronized (this.mLock) {
            listener = this.mListener;
        }
        if (listener != null) {
            listener.onResponse(raw, response);
        }
    }

    @Override // com.sqnetwork.voly.Request
    @Deprecated
    public String getPostBodyContentType() {
        return getBodyContentType();
    }

    @Override // com.sqnetwork.voly.Request
    @Deprecated
    public byte[] getPostBody() {
        return getBody();
    }

    @Override // com.sqnetwork.voly.Request
    public String getBodyContentType() {
        return PROTOCOL_CONTENT_TYPE;
    }

    @Override // com.sqnetwork.voly.Request
    public byte[] getBody() {
        try {
            if (this.mRequestBody == null) {
                return null;
            }
            return this.mRequestBody.getBytes(PROTOCOL_CHARSET);
        } catch (UnsupportedEncodingException unused) {
            VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", this.mRequestBody, PROTOCOL_CHARSET);
            return null;
        }
    }
}
