package com.sdk.sq.net;

import com.sqnetwork.voly.NetworkResponse;
import com.sqnetwork.voly.ParseError;
import com.sqnetwork.voly.Response;
import com.sqnetwork.voly.toolbox.HttpHeaderParser;
import com.sqnetwork.voly.toolbox.JsonRequest;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
@Deprecated
public class RawJsonRequest extends JsonRequest<JSONObject> {
    public RawJsonRequest(int method, String url, JSONObject jsonRequest, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        super(method, url, jsonRequest == null ? null : jsonRequest.toString(), listener, errorListener);
    }

    public RawJsonRequest(String url, JSONObject jsonRequest, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        this(jsonRequest == null ? 0 : 1, url, jsonRequest, listener, errorListener);
    }

    @Override // com.sqnetwork.voly.toolbox.JsonRequest, com.sqnetwork.voly.Request
    protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
        try {
            String str = new String(response.data, HttpHeaderParser.parseCharset(response.headers, "utf-8"));
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("body", new JSONObject(str));
            jSONObject.put("header", mapToJson(response.headers));
            jSONObject.put("status", response.statusCode);
            return Response.success(jSONObject, HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JSONException e2) {
            return Response.error(new ParseError(e2));
        }
    }

    private JSONObject mapToJson(Map<String, ? extends Object> map) {
        if (map != null && !map.isEmpty()) {
            JSONObject jSONObject = new JSONObject();
            for (String str : map.keySet()) {
                try {
                    jSONObject.put(str, map.get(str));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            return jSONObject;
        }
        return new JSONObject();
    }
}
