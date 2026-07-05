package com.sqwan.msdk.utils;

import com.sq.push.service.IHttpRequester;
import com.sq.tool.logger.SQLog;
import com.sq.tool.network.SqHttpClient;
import com.sqnetwork.voly.Response;
import com.sqnetwork.voly.VolleyError;
import com.sqnetwork.voly.toolbox.StringRequest;
import java.util.Collections;
import java.util.Map;
import okhttp3.HttpUrl;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class PushHttpRequester implements IHttpRequester {
    @Override // com.sq.push.service.IHttpRequester
    public void get(String str, final Map<String, String> map, Map<String, String> map2, final IHttpRequester.IRequestCallback iRequestCallback) {
        try {
            HttpUrl.Builder builderNewBuilder = HttpUrl.get(str).newBuilder();
            if (map2 != null) {
                for (String str2 : map2.keySet()) {
                    builderNewBuilder.addQueryParameter(str2, map2.get(str2));
                }
            }
            str = builderNewBuilder.build().toString();
        } catch (Exception e) {
            SQLog.w("【Push】构建url失败: url=" + str + ", param=" + map2, e);
        }
        SqHttpClient.getInstance().enqueue(new StringRequest(0, str, new Response.Listener() { // from class: com.sqwan.msdk.utils.-$$Lambda$PushHttpRequester$XV-gk8-c_Q5QYhGWFRuJnLFVFpQ
            @Override // com.sqnetwork.voly.Response.Listener
            public final void onResponse(Response response, Object obj) {
                iRequestCallback.onSuccess(response.getNetworkResponse().statusCode, (String) obj);
            }
        }, new Response.ErrorListener() { // from class: com.sqwan.msdk.utils.-$$Lambda$PushHttpRequester$Dka_Yjo021NwgK5BPP9t7K9NZqo
            @Override // com.sqnetwork.voly.Response.ErrorListener
            public final void onErrorResponse(VolleyError volleyError) {
                iRequestCallback.onFailure(-1, volleyError.getMessage());
            }
        }) { // from class: com.sqwan.msdk.utils.PushHttpRequester.1
            @Override // com.sqnetwork.voly.Request
            public byte[] getBody() {
                return null;
            }

            @Override // com.sqnetwork.voly.Request
            public Map<String, String> getHeaders() {
                Map<String, String> map3 = map;
                return map3 == null ? Collections.emptyMap() : map3;
            }

            @Override // com.sqnetwork.voly.Request
            public String getBodyContentType() {
                return "application/json; charset=" + getParamsEncoding();
            }
        });
    }

    @Override // com.sq.push.service.IHttpRequester
    public void postJson(String str, final Map<String, String> map, Map<String, String> map2, final IHttpRequester.IRequestCallback iRequestCallback) {
        String string;
        if (map2 != null) {
            try {
                string = new JSONObject(map2).toString();
            } catch (Exception e) {
                SQLog.w("【Push】参数转json失败: " + map2, e);
                string = null;
            }
        } else {
            string = null;
        }
        final String str2 = string;
        SqHttpClient.getInstance().enqueue(new StringRequest(1, str, new Response.Listener() { // from class: com.sqwan.msdk.utils.-$$Lambda$PushHttpRequester$oxZZJuCeJdSoHGgaHCK_Ww3keho
            @Override // com.sqnetwork.voly.Response.Listener
            public final void onResponse(Response response, Object obj) {
                iRequestCallback.onSuccess(response.getNetworkResponse().statusCode, (String) obj);
            }
        }, new Response.ErrorListener() { // from class: com.sqwan.msdk.utils.-$$Lambda$PushHttpRequester$zqQy4dmAGD1HENPyj3rNTcfGLVU
            @Override // com.sqnetwork.voly.Response.ErrorListener
            public final void onErrorResponse(VolleyError volleyError) {
                iRequestCallback.onFailure(-1, volleyError.getMessage());
            }
        }) { // from class: com.sqwan.msdk.utils.PushHttpRequester.2
            @Override // com.sqnetwork.voly.Request
            public Map<String, String> getHeaders() {
                Map<String, String> map3 = map;
                return map3 == null ? Collections.emptyMap() : map3;
            }

            @Override // com.sqnetwork.voly.Request
            public byte[] getBody() {
                String str3 = str2;
                if (str3 != null) {
                    return str3.getBytes();
                }
                return null;
            }

            @Override // com.sqnetwork.voly.Request
            public String getBodyContentType() {
                return "application/json; charset=" + getParamsEncoding();
            }
        });
    }
}
