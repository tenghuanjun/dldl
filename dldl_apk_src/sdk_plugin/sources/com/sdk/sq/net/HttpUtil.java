package com.sdk.sq.net;

import android.content.Context;
import com.sdk.sq.net.HttpClient;
import com.sq.tools.network.ContentType;
import com.sqnetwork.voly.AuthFailureError;
import com.sqnetwork.voly.DefaultRetryPolicy;
import com.sqnetwork.voly.Request;
import com.sqnetwork.voly.RequestQueue;
import com.sqnetwork.voly.Response;
import com.sqnetwork.voly.RetryPolicy;
import com.sqnetwork.voly.VolleyError;
import com.sqnetwork.voly.toolbox.BaseHttpStack;
import com.sqnetwork.voly.toolbox.JsonObjectRequestEx;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
@Deprecated
public class HttpUtil {
    private VolyConfig mConfig = new VolyConfig();
    private RetryPolicyFactory mRetryPolicyFactory = new DefaultRetryPolicyFactory();
    private final HttpClient mSqHttpClient;

    public interface RetryPolicyFactory {
        RetryPolicy create(VolyConfig config);
    }

    @Deprecated
    public void setSSLIgnore(boolean ignore) {
    }

    public HttpUtil(Context context) {
        this.mSqHttpClient = new HttpClient.Builder().build(context);
    }

    public HttpUtil(Context context, BaseHttpStack stack) {
        this.mSqHttpClient = new HttpClient.Builder().setHttpStack(stack).build(context);
    }

    public HttpUtil(RequestQueue queue) {
        this.mSqHttpClient = new HttpClient(queue);
    }

    public HttpUtil(HttpClient client) {
        this.mSqHttpClient = client;
    }

    public void get(String url, IHttpRequestCallBack callBack) {
        get(url, null, callBack);
    }

    public void get(String url, Map<String, String> params, IHttpRequestCallBack callBack) {
        get(url, params, null, callBack);
    }

    public void get(String url, Map<String, String> params, final Map<String, String> headers, final IHttpRequestCallBack callBack) {
        get(url, params, headers, null, callBack);
    }

    public void get(String url, Map<String, String> params, final Map<String, String> headers, RetryPolicy policy, final IHttpRequestCallBack callBack) {
        Request<JSONObject> requestBuild = this.mSqHttpClient.newBuilder().get(url, params).headers(headers).callback(callBack).build();
        handleRetryPolicy(requestBuild, policy);
        this.mSqHttpClient.enqueue(requestBuild);
    }

    public void post(String url, final Map<String, String> params, final IHttpRequestCallBack callBack) {
        post(url, params, null, callBack);
    }

    public void post(String url, final Map<String, String> params, final Map<String, String> headers, final IHttpRequestCallBack callBack) {
        post(url, params, headers, null, callBack);
    }

    public void post(String url, final Map<String, String> params, final Map<String, String> headers, RetryPolicy policy, final IHttpRequestCallBack callBack) {
        Request<JSONObject> requestBuild = this.mSqHttpClient.newBuilder().post(url).headers(headers).formBody(params).callback(callBack).build();
        handleRetryPolicy(requestBuild, policy);
        this.mSqHttpClient.enqueue(requestBuild);
    }

    public void postJson(String url, final JSONObject jsonObject, final Map<String, String> headers, final IHttpRequestCallBack callBack) {
        postJson(url, jsonObject, headers, null, callBack);
    }

    public void postJson(String url, final JSONObject jsonObject, final Map<String, String> headers, RetryPolicy policy, final IHttpRequestCallBack callBack) {
        Response.Listener listener = new Response.Listener() { // from class: com.sdk.sq.net.-$$Lambda$HttpUtil$0Mi8EFf0I2CUaIAyCFkf8JhWA90
            @Override // com.sqnetwork.voly.Response.Listener
            public final void onResponse(Response response, Object obj) {
                callBack.onRequestSuccess(((JSONObject) obj).toString());
            }
        };
        Objects.requireNonNull(callBack);
        JsonObjectRequestEx jsonObjectRequestEx = new JsonObjectRequestEx(1, url, headers, jsonObject, (Response.Listener<JSONObject>) listener, new Response.ErrorListener() { // from class: com.sdk.sq.net.-$$Lambda$HURpYTHTUSt4ofEE5uTkx1_LBtk
            @Override // com.sqnetwork.voly.Response.ErrorListener
            public final void onErrorResponse(VolleyError volleyError) {
                callBack.onRequestError(volleyError);
            }
        });
        handleRetryPolicy(jsonObjectRequestEx, policy);
        this.mSqHttpClient.enqueue(jsonObjectRequestEx);
    }

    public void postJsonWithRawResponse(String url, JSONObject jsonObject, final Map<String, String> headers, final IHttpRequestCallBack callBack) {
        postJsonWithRawResponse(url, jsonObject, headers, null, callBack);
    }

    public void postJsonWithRawResponse(String url, JSONObject jsonObject, final Map<String, String> headers, RetryPolicy policy, final IHttpRequestCallBack callBack) {
        RawJsonRequest rawJsonRequest = new RawJsonRequest(1, url, jsonObject, new Response.Listener<JSONObject>() { // from class: com.sdk.sq.net.HttpUtil.1
            @Override // com.sqnetwork.voly.Response.Listener
            public void onResponse(Response<JSONObject> raw, JSONObject response) {
                callBack.onRequestSuccess(response.toString());
            }
        }, new Response.ErrorListener() { // from class: com.sdk.sq.net.HttpUtil.2
            @Override // com.sqnetwork.voly.Response.ErrorListener
            public void onErrorResponse(VolleyError error) {
                callBack.onRequestError(error);
            }
        }) { // from class: com.sdk.sq.net.HttpUtil.3
            @Override // com.sqnetwork.voly.Request
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map map = headers;
                if (map == null || map.size() <= 0) {
                    HashMap map2 = new HashMap();
                    map2.put("Content-Type", ContentType.JSON);
                    return map2;
                }
                headers.put("Content-Type", ContentType.JSON);
                return headers;
            }
        };
        handleRetryPolicy(rawJsonRequest, policy);
        this.mSqHttpClient.enqueue(rawJsonRequest);
    }

    public <T> void post(Request<T> request) {
        this.mSqHttpClient.enqueue(request);
    }

    public void setVolyConfig(VolyConfig config) {
        this.mConfig = config;
    }

    public VolyConfig getVolyConfig() {
        return this.mConfig;
    }

    private void handleRetryPolicy(Request<?> request, RetryPolicy policy) {
        if (policy != null) {
            request.setRetryPolicy(policy);
            return;
        }
        RetryPolicy retryPolicyCreate = this.mRetryPolicyFactory.create(this.mConfig);
        if (retryPolicyCreate != null) {
            request.setRetryPolicy(retryPolicyCreate);
        }
    }

    public void setRetryPolicyFactory(RetryPolicyFactory retryPolicyFactory) {
        this.mRetryPolicyFactory = retryPolicyFactory;
    }

    public static class DefaultRetryPolicyFactory implements RetryPolicyFactory {
        @Override // com.sdk.sq.net.HttpUtil.RetryPolicyFactory
        public RetryPolicy create(VolyConfig config) {
            if (config != null) {
                return new DefaultRetryPolicy(config.getTimeOut(), 1, 1.0f);
            }
            return null;
        }
    }
}
