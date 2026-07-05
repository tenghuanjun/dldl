package com.sqnetwork.voly.toolbox;

import com.sqnetwork.voly.NetworkResponse;
import com.sqnetwork.voly.Request;
import com.sqnetwork.voly.Response;
import java.io.UnsupportedEncodingException;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class StringRequest extends Request<String> {
    private Response.Listener<String> mListener;
    private final Object mLock;

    public StringRequest(int method, String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(method, url, errorListener);
        this.mLock = new Object();
        this.mListener = listener;
    }

    public StringRequest(String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        this(0, url, listener, errorListener);
    }

    @Override // com.sqnetwork.voly.Request
    public void cancel() {
        super.cancel();
        synchronized (this.mLock) {
            this.mListener = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sqnetwork.voly.Request
    public void deliverResponse(Response<String> raw, String response) {
        Response.Listener<String> listener;
        synchronized (this.mLock) {
            listener = this.mListener;
        }
        if (listener != null) {
            listener.onResponse(raw, response);
        }
    }

    @Override // com.sqnetwork.voly.Request
    protected Response<String> parseNetworkResponse(NetworkResponse response) {
        String str;
        try {
            str = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
        } catch (UnsupportedEncodingException unused) {
            str = new String(response.data);
        }
        return Response.success(str, HttpHeaderParser.parseCacheHeaders(response));
    }
}
