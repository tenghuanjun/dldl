package com.sy37sdk.account.device;

import com.sq.tool.network.SqHttpClient;
import com.sq.tool.sqtools.net.DHttpClient;
import com.sq.tool.sqtools.net.DevicesHttpCallback;
import com.sqnetwork.voly.DefaultRetryPolicy;
import com.sqnetwork.voly.Response;
import com.sqnetwork.voly.VolleyError;
import com.sqnetwork.voly.toolbox.StringRequest;
import java.util.Collections;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class DevicesHttpClient implements DHttpClient {
    @Override // com.sq.tool.sqtools.net.DHttpClient
    public void postString(String str, final String str2, final Map<String, String> map, final DevicesHttpCallback devicesHttpCallback) {
        if (str2 == null) {
            return;
        }
        StringRequest stringRequest = new StringRequest(1, str, new Response.Listener() { // from class: com.sy37sdk.account.device.-$$Lambda$DevicesHttpClient$aX8UMIEUVdApQyxcv4SD7slqB2c
            @Override // com.sqnetwork.voly.Response.Listener
            public final void onResponse(Response response, Object obj) {
                devicesHttpCallback.onSuccess((String) obj);
            }
        }, new Response.ErrorListener() { // from class: com.sy37sdk.account.device.-$$Lambda$DevicesHttpClient$7jmjA0G4Fm9RuHREk512T7_BeJ8
            @Override // com.sqnetwork.voly.Response.ErrorListener
            public final void onErrorResponse(VolleyError volleyError) {
                devicesHttpCallback.onFail(-1, volleyError.getMessage());
            }
        }) { // from class: com.sy37sdk.account.device.DevicesHttpClient.1
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
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(2000, 1, 1.0f));
        SqHttpClient.getInstance().enqueue(stringRequest);
    }
}
