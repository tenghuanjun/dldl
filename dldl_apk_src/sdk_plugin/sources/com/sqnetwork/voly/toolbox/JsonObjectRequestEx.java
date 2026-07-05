package com.sqnetwork.voly.toolbox;

import com.sqnetwork.voly.AuthFailureError;
import com.sqnetwork.voly.Response;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class JsonObjectRequestEx extends JsonObjectRequest {
    private final Map<String, String> mHeaders;

    public JsonObjectRequestEx(int method, String url, JSONObject jsonRequest, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        this(method, url, (Map<String, String>) null, jsonRequest, listener, errorListener);
    }

    public JsonObjectRequestEx(String url, JSONObject jsonRequest, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        this(url, (Map<String, String>) null, jsonRequest, listener, errorListener);
    }

    public JsonObjectRequestEx(int method, String url, Map<String, String> headers, JSONObject jsonRequest, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        super(method, url, jsonRequest, listener, errorListener);
        this.mHeaders = headers;
    }

    public JsonObjectRequestEx(String url, Map<String, String> headers, JSONObject jsonRequest, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        super(url, jsonRequest, listener, errorListener);
        this.mHeaders = headers;
    }

    public JsonObjectRequestEx(int method, String url, Map<String, String> headers, Map<String, Object> params, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
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
