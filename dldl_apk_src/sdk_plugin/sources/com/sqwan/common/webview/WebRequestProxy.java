package com.sqwan.common.webview;

import com.sq.tool.network.SqHttpCallback;
import com.sq.tool.network.SqRequest;
import com.sq.webview.net.IRequest;
import com.sqnetwork.voly.VolleyError;
import java.util.Map;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class WebRequestProxy implements IRequest {
    @Override // com.sq.webview.net.IRequest
    public void getRequest(String str, Map<String, String> map, final IRequest.RequestCallback<JSONObject> requestCallback) {
        SqRequest.of(str).params(map).get(new SqHttpCallback<JSONObject>() { // from class: com.sqwan.common.webview.WebRequestProxy.1
            @Override // com.sq.tool.network.SqHttpCallback, com.sdk.sq.net.SqRequestCallback
            protected String getMsgKey() {
                return "message";
            }

            @Override // com.sq.tool.network.SqHttpCallback, com.sdk.sq.net.SqRequestCallback
            protected int getOkState() {
                return 0;
            }

            @Override // com.sq.tool.network.SqHttpCallback, com.sdk.sq.net.SqRequestCallback
            protected String getStateKey() {
                return "code";
            }

            @Override // com.sdk.sq.net.SqRequestCallback
            public void onResponseStateError(int i, int i2, String str2, String str3) {
                requestCallback.onError(i, str3);
            }

            @Override // com.sq.tool.network.SqHttpCallback
            public void onSuccess(JSONObject jSONObject) {
                requestCallback.onSuccess(jSONObject);
            }

            @Override // com.sq.tool.network.SqHttpCallback
            public void onFailure(int i, String str2, VolleyError volleyError) {
                requestCallback.onError(i, volleyError.getMessage());
            }
        });
    }
}
