package com.sqwan.common;

import com.sdk.sq.net.HttpDns;
import com.sdk.sq.net.ReportStrategy;
import com.sq.tool.network.SqHttpClient;
import com.sqnetwork.voly.Response;
import com.sqnetwork.voly.VolleyError;
import com.sqnetwork.voly.toolbox.StringRequest;
import com.sqwan.bugless.net.IHttpCallback;
import com.sqwan.bugless.net.IHttpClient;
import java.util.Collections;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class BuglessHttpClient implements IHttpClient {
    @Override // com.sqwan.bugless.net.IHttpClient
    public void postString(String str, final String str2, final Map<String, String> map, final IHttpCallback iHttpCallback) {
        if (str2 == null) {
            return;
        }
        StringRequest stringRequest = new StringRequest(1, str, new Response.Listener() { // from class: com.sqwan.common.-$$Lambda$BuglessHttpClient$xpkxOt71Bwu8Mn5lQNyjDbBBTwY
            @Override // com.sqnetwork.voly.Response.Listener
            public final void onResponse(Response response, Object obj) {
                iHttpCallback.onSuccess((String) obj);
            }
        }, new Response.ErrorListener() { // from class: com.sqwan.common.-$$Lambda$BuglessHttpClient$W6_W9U5OVXieOLXoZnCGMPFjJzI
            @Override // com.sqnetwork.voly.Response.ErrorListener
            public final void onErrorResponse(VolleyError volleyError) {
                iHttpCallback.onFail(-1, volleyError.getMessage());
            }
        }) { // from class: com.sqwan.common.BuglessHttpClient.1
            @Override // com.sqnetwork.voly.Request
            public Map<String, String> getHeaders() {
                Map<String, String> map2 = map;
                return map2 == null ? Collections.emptyMap() : map2;
            }

            @Override // com.sqnetwork.voly.Request
            public byte[] getBody() {
                return str2.getBytes();
            }

            @Override // com.sqnetwork.voly.Request
            public String getBodyContentType() {
                return "application/json; charset=" + getParamsEncoding();
            }
        };
        stringRequest.setTag(ReportStrategy.class, ReportStrategy.ONLY_EVENT);
        stringRequest.setLocalDNS(HttpDns.DEFAULT);
        SqHttpClient.getInstance().enqueue(stringRequest);
    }
}
