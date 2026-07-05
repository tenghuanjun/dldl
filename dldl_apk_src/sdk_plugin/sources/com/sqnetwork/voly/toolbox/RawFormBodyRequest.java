package com.sqnetwork.voly.toolbox;

import com.sqnetwork.voly.Response;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import kotlin.text.Typography;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class RawFormBodyRequest extends ParamJsonRequestEx {
    @Override // com.sqnetwork.voly.toolbox.ParamJsonRequestEx, com.sqnetwork.voly.Request
    public Map<String, String> getParams() {
        return null;
    }

    public RawFormBodyRequest(int method, String url, Map<String, String> params, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        this(method, url, null, params, listener, errorListener);
    }

    public RawFormBodyRequest(String url, Map<String, String> params, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        this(url, (Map<String, String>) null, params, listener, errorListener);
    }

    public RawFormBodyRequest(int method, String url, Map<String, String> headers, Map<String, String> params, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        super(method, url, headers, params, listener, errorListener);
    }

    public RawFormBodyRequest(String url, Map<String, String> headers, Map<String, String> params, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        super(url, headers, params, listener, errorListener);
    }

    @Override // com.sqnetwork.voly.Request
    public byte[] getBody() {
        Map<String, String> params = super.getParams();
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
}
