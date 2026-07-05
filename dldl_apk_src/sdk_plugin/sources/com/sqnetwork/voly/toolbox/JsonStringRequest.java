package com.sqnetwork.voly.toolbox;

import com.sqnetwork.voly.AuthFailureError;
import com.sqnetwork.voly.NetworkResponse;
import com.sqnetwork.voly.Response;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class JsonStringRequest extends JsonRequest<String> {
    private final Map<String, String> mHeaders;

    public JsonStringRequest(int method, String url, Map<String, String> headers, JSONObject jsonRequest, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(method, url, jsonRequest == null ? null : jsonRequest.toString(), listener, errorListener);
        this.mHeaders = headers;
    }

    public JsonStringRequest(String url, Map<String, String> headers, JSONObject jsonRequest, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        this(jsonRequest == null ? 0 : 1, url, headers, jsonRequest, listener, errorListener);
    }

    public JsonStringRequest(int method, String url, Map<String, Object> params, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        this(method, url, (Map<String, String>) null, map2Json(params), listener, errorListener);
    }

    public JsonStringRequest(String url, Map<String, Object> params, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        this(url, (Map<String, String>) null, map2Json(params), listener, errorListener);
    }

    public JsonStringRequest(String url, Map<String, String> headers, Map<String, Object> params, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        this(url, headers, map2Json(params), listener, errorListener);
    }

    public JsonStringRequest(int method, String url, Map<String, String> headers, Map<String, Object> params, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        this(method, url, headers, map2Json(params), listener, errorListener);
    }

    @Override // com.sqnetwork.voly.Request
    public Map<String, String> getHeaders() throws AuthFailureError {
        Map<String, String> map = this.mHeaders;
        if (map != null && map.size() > 0) {
            return this.mHeaders;
        }
        return super.getHeaders();
    }

    @Override // com.sqnetwork.voly.toolbox.JsonRequest, com.sqnetwork.voly.Request
    protected Response<String> parseNetworkResponse(NetworkResponse response) {
        String str;
        try {
            str = new String(response.data, HttpHeaderParser.parseCharset(response.headers, "utf-8"));
        } catch (UnsupportedEncodingException unused) {
            str = new String(response.data);
        }
        return Response.success(str, HttpHeaderParser.parseCacheHeaders(response));
    }

    private static JSONObject map2Json(Map<String, Object> map) {
        if (map == null || map.isEmpty()) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        for (String str : map.keySet()) {
            try {
                jSONObject.put(str, map.get(str));
            } catch (JSONException unused) {
            }
        }
        return jSONObject;
    }
}
