package com.sqnetwork.voly.toolbox;

import com.sqnetwork.voly.AuthFailureError;
import com.sqnetwork.voly.Response;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class StringRequestEx extends StringRequest {
    private final Map<String, String> mHeaders;
    private final Map<String, String> mParams;

    public StringRequestEx(int method, String url, Map<String, String> params, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        this(method, url, null, params, listener, errorListener);
    }

    public StringRequestEx(String url, Map<String, String> params, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        this(url, (Map<String, String>) null, params, listener, errorListener);
    }

    public StringRequestEx(int method, String url, Map<String, String> headers, Map<String, String> params, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(method, url, listener, errorListener);
        this.mHeaders = headers;
        this.mParams = params;
    }

    public StringRequestEx(String url, Map<String, String> headers, Map<String, String> params, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(url, listener, errorListener);
        this.mHeaders = headers;
        this.mParams = params;
    }

    @Override // com.sqnetwork.voly.Request
    public Map<String, String> getParams() {
        return this.mParams;
    }

    @Override // com.sqnetwork.voly.Request
    public Map<String, String> getHeaders() throws AuthFailureError {
        Map<String, String> map = this.mHeaders;
        if (map != null && map.size() > 0) {
            return this.mHeaders;
        }
        return super.getHeaders();
    }
}
