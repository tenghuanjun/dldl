package com.sqnetwork.voly.toolbox;

import com.sqnetwork.voly.NetworkResponse;
import com.sqnetwork.voly.ParseError;
import com.sqnetwork.voly.Request;
import com.sqnetwork.voly.Response;
import java.io.UnsupportedEncodingException;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class ParamJsonRequest extends Request<JSONObject> {
    protected static final String PROTOCOL_CHARSET = "utf-8";
    private Response.Listener<JSONObject> mListener;
    private final Object mLock;

    public ParamJsonRequest(int method, String url, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        super(method, url, errorListener);
        this.mLock = new Object();
        this.mListener = listener;
    }

    public ParamJsonRequest(String url, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
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
    public void deliverResponse(Response<JSONObject> raw, JSONObject response) {
        Response.Listener<JSONObject> listener;
        synchronized (this.mLock) {
            listener = this.mListener;
        }
        if (listener != null) {
            listener.onResponse(raw, response);
        }
    }

    @Override // com.sqnetwork.voly.Request
    protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
        try {
            return Response.success(new JSONObject(new String(response.data, HttpHeaderParser.parseCharset(response.headers, PROTOCOL_CHARSET))), HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException | JSONException e) {
            return Response.error(new ParseError(e));
        }
    }
}
