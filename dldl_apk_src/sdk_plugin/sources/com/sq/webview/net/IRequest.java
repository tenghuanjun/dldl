package com.sq.webview.net;

import java.util.Map;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public interface IRequest {

    public interface RequestCallback<T> {
        void onError(int code, String msg);

        void onSuccess(T data);
    }

    void getRequest(String url, Map<String, String> params, RequestCallback<JSONObject> callback);
}
