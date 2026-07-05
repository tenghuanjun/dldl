package com.sdk.sq.net;

import com.sqnetwork.voly.NetworkResponse;
import com.sqnetwork.voly.Request;
import com.sqnetwork.voly.RequestInterceptor;
import com.sqnetwork.voly.Response;
import com.sqnetwork.voly.RetryPolicy;
import com.sqnetwork.voly.RetryPolicyFactory;
import com.sqnetwork.voly.VolleyError;
import com.sqnetwork.voly.toolbox.JsonObjectRequestEx;
import com.sqnetwork.voly.toolbox.ParamJsonRequestEx;
import com.sqnetwork.voly.toolbox.RawFormBodyRequest;
import com.sqwan.liveshow.huya.SqR;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class RequestBuilder {
    private RequestCallback mCallback;
    private boolean mFallbackToHttp;
    private Map<String, String> mHeaders;
    private List<HeadersTransformer> mHeadersTransformers;
    private HttpClient mHttpClient;
    private Map<String, ?> mParams;
    private List<ParamsTransformer> mParamsTransformers;
    private String mReqId;
    private List<RequestInterceptor> mRequestInterceptors;
    private RetryPolicy mRetryPolicy;
    private RetryPolicyFactory mRetryPolicyFactory;
    private String mUrl;
    private int mMethod = 1;
    private int mBodyType = 1;
    private Map<Class<?>, Object> mTags = Collections.emptyMap();
    private boolean mEnableHttpDns = true;

    public interface Body {
        public static final int FORM = 1;
        public static final int JSON = 2;
        public static final int RAW_FORM = 3;
    }

    public interface HeadersTransformer {
        Map<String, String> transform(Map<String, String> headers);
    }

    public interface Method {
        public static final int DELETE = 3;
        public static final int GET = 0;
        public static final int HEAD = 4;
        public static final int OPTIONS = 5;
        public static final int PATCH = 7;
        public static final int POST = 1;
        public static final int PUT = 2;
        public static final int TRACE = 6;
    }

    public interface ParamsTransformer {
        Map<String, Object> transform(Map<String, Object> params);
    }

    public interface RequestCallback {
        void onError(int code, Map<String, String> headers, VolleyError error);

        void onSuccess(int httpStatus, Map<String, String> headers, JSONObject response);
    }

    public RequestBuilder id(String id) {
        this.mReqId = id;
        return this;
    }

    public RequestBuilder url(String url) {
        this.mUrl = url;
        return this;
    }

    public RequestBuilder get(String url) {
        return method(0).url(url);
    }

    public RequestBuilder get(String url, Map<String, String> params) {
        return method(0).formBody(params).url(url);
    }

    public RequestBuilder post(String url) {
        return method(1).url(url);
    }

    public RequestBuilder method(int method) {
        this.mMethod = method;
        return this;
    }

    public RequestBuilder bodyType(int type) {
        this.mBodyType = type;
        return this;
    }

    public RequestBuilder headers(Map<String, String> headers) {
        this.mHeaders = headers;
        return this;
    }

    public RequestBuilder addHeader(String key, String value) {
        if (this.mHeaders == null) {
            this.mHeaders = new HashMap();
        }
        this.mHeaders.put(key, value);
        return this;
    }

    public RequestBuilder jsonBody(Map<String, Object> params) {
        return bodyType(2).params(params);
    }

    public RequestBuilder formBody(Map<String, String> params) {
        return bodyType(1).params(params);
    }

    public RequestBuilder params(Map<String, ?> params) {
        this.mParams = params;
        return this;
    }

    public RequestBuilder addParam(String key, Object value) {
        if (this.mParams == null) {
            this.mParams = new HashMap();
        }
        if (this.mBodyType == 1) {
            Map<String, ?> map = this.mParams;
            if (value == null) {
                map.put(key, "");
            } else {
                map.put(key, String.valueOf(value));
            }
        } else {
            this.mParams.put(key, value);
        }
        return this;
    }

    public RequestBuilder retryPolicy(RetryPolicy retryPolicy) {
        this.mRetryPolicy = retryPolicy;
        return this;
    }

    public RequestBuilder retryPolicyFactory(RetryPolicyFactory retryPolicyFactory) {
        this.mRetryPolicyFactory = retryPolicyFactory;
        return this;
    }

    @Deprecated
    public RequestBuilder callback(final IHttpRequestCallBack callback) {
        this.mCallback = new RequestCallback() { // from class: com.sdk.sq.net.RequestBuilder.1
            @Override // com.sdk.sq.net.RequestBuilder.RequestCallback
            public void onSuccess(int httpStatus, Map<String, String> headers, JSONObject response) {
                IHttpRequestCallBack iHttpRequestCallBack = callback;
                if (iHttpRequestCallBack != null) {
                    iHttpRequestCallBack.onRequestSuccess(response.toString());
                }
            }

            @Override // com.sdk.sq.net.RequestBuilder.RequestCallback
            public void onError(int code, Map<String, String> headers, VolleyError error) {
                IHttpRequestCallBack iHttpRequestCallBack = callback;
                if (iHttpRequestCallBack != null) {
                    iHttpRequestCallBack.onRequestError(error);
                }
            }
        };
        return this;
    }

    public RequestBuilder callback(RequestCallback callback) {
        this.mCallback = callback;
        return this;
    }

    public RequestBuilder httpClient(HttpClient client) {
        this.mHttpClient = client;
        return this;
    }

    public <T> RequestBuilder tag(Class<? super T> type, T tag) {
        if (type == null) {
            throw new NullPointerException("type == null");
        }
        if (tag == null) {
            this.mTags.remove(type);
        } else {
            if (this.mTags.isEmpty()) {
                this.mTags = new LinkedHashMap();
            }
            this.mTags.put(type, type.cast(tag));
        }
        return this;
    }

    public RequestBuilder setHttpDnsEnable(boolean enable) {
        this.mEnableHttpDns = enable;
        return this;
    }

    public RequestBuilder setFallbackToHttp(boolean enable) {
        this.mFallbackToHttp = enable;
        return this;
    }

    public RequestBuilder addRequestInterceptor(RequestInterceptor interceptor) {
        if (interceptor == null) {
            return this;
        }
        if (this.mRequestInterceptors == null) {
            this.mRequestInterceptors = new ArrayList();
        }
        this.mRequestInterceptors.add(interceptor);
        return this;
    }

    public RequestBuilder addParamsTransformer(ParamsTransformer transformer) {
        if (transformer == null) {
            return this;
        }
        if (this.mParamsTransformers == null) {
            this.mParamsTransformers = new ArrayList();
        }
        this.mParamsTransformers.add(transformer);
        return this;
    }

    public RequestBuilder addHeadersTransformer(HeadersTransformer transformer) {
        if (transformer == null) {
            return this;
        }
        if (this.mHeadersTransformers == null) {
            this.mHeadersTransformers = new ArrayList();
        }
        this.mHeadersTransformers.add(transformer);
        return this;
    }

    public Request<JSONObject> build() {
        Map<String, ?> map;
        Request<JSONObject> paramJsonRequestEx;
        RetryPolicy retryPolicyCreate;
        List<ParamsTransformer> list = this.mParamsTransformers;
        if (list != null) {
            Iterator<ParamsTransformer> it = list.iterator();
            while (it.hasNext()) {
                this.mParams = it.next().transform(this.mParams);
            }
        }
        int i = this.mMethod;
        if ((i == 1 || i == 2 || i == 7) && ((map = this.mParams) == null || map.isEmpty())) {
            throw new IllegalArgumentException("POST/PUT/PATCH方法必须指定请求参数");
        }
        List<HeadersTransformer> list2 = this.mHeadersTransformers;
        if (list2 != null) {
            Iterator<HeadersTransformer> it2 = list2.iterator();
            while (it2.hasNext()) {
                this.mHeaders = it2.next().transform(this.mHeaders);
            }
        }
        Callback callback = new Callback(this.mCallback);
        int i2 = this.mBodyType;
        if (i2 == 1) {
            paramJsonRequestEx = new ParamJsonRequestEx(this.mMethod, this.mUrl, this.mHeaders, stringMap(this.mParams), callback, callback);
        } else if (i2 == 2) {
            paramJsonRequestEx = new JsonObjectRequestEx(this.mMethod, this.mUrl, this.mHeaders, this.mParams, callback, callback);
        } else if (i2 == 3) {
            paramJsonRequestEx = new RawFormBodyRequest(this.mMethod, this.mUrl, this.mHeaders, stringMap(this.mParams), callback, callback);
        } else {
            throw new IllegalArgumentException("请求Body不支持类型" + this.mBodyType);
        }
        String str = this.mReqId;
        if (str != null && !str.isEmpty()) {
            paramJsonRequestEx.setRequestId(this.mReqId);
        }
        if (!this.mTags.isEmpty()) {
            Iterator<Class<?>> it3 = this.mTags.keySet().iterator();
            while (it3.hasNext()) {
                Class<? super E> cls = (Class) it3.next();
                paramJsonRequestEx.setTag(cls, this.mTags.get(cls));
            }
        }
        if (this.mEnableHttpDns) {
            paramJsonRequestEx.setLocalDNS(HttpDns.DEFAULT);
        }
        paramJsonRequestEx.setFallbackHttpsToHttp(this.mFallbackToHttp);
        paramJsonRequestEx.setShouldRetryAuthErrors(false);
        List<RequestInterceptor> list3 = this.mRequestInterceptors;
        if (list3 != null) {
            Iterator<RequestInterceptor> it4 = list3.iterator();
            while (it4.hasNext()) {
                paramJsonRequestEx.addRequestInterceptor(it4.next());
            }
        }
        RetryPolicy retryPolicy = this.mRetryPolicy;
        if (retryPolicy != null) {
            paramJsonRequestEx.setRetryPolicy(retryPolicy);
        } else {
            RetryPolicyFactory retryPolicyFactory = this.mRetryPolicyFactory;
            if (retryPolicyFactory != null && (retryPolicyCreate = retryPolicyFactory.create(paramJsonRequestEx)) != null) {
                paramJsonRequestEx.setRetryPolicy(retryPolicyCreate);
            }
        }
        return paramJsonRequestEx;
    }

    public Request<JSONObject> enqueue() {
        return enqueue(this.mHttpClient);
    }

    public Request<JSONObject> enqueue(HttpClient client) {
        this.mHttpClient = client;
        if (client == null) {
            throw new IllegalArgumentException("请求的Client不能为空");
        }
        return client.enqueue(build());
    }

    private static Map<String, String> stringMap(Map<String, ?> map) {
        if (map == null) {
            return null;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (String str : map.keySet()) {
            Object obj = map.get(str);
            if (obj == null) {
                linkedHashMap.put(str, null);
            } else if (obj instanceof String) {
                linkedHashMap.put(str, (String) obj);
            } else {
                linkedHashMap.put(str, String.valueOf(obj));
            }
        }
        return linkedHashMap;
    }

    private static class Callback implements Response.Listener<JSONObject>, Response.ErrorListener {
        private final RequestCallback mCallback;

        public Callback(RequestCallback callback) {
            this.mCallback = callback;
        }

        @Override // com.sqnetwork.voly.Response.Listener
        public void onResponse(Response<JSONObject> raw, JSONObject response) {
            if (this.mCallback != null) {
                if (raw.getNetworkResponse() != null) {
                    this.mCallback.onSuccess(raw.getNetworkResponse().statusCode, headers(raw.getNetworkResponse()), response);
                } else {
                    onErrorResponse(new VolleyError("NetworkResponse invalid."));
                }
            }
        }

        private Map<String, String> headers(NetworkResponse response) {
            return (response == null || response.headers == null) ? Collections.emptyMap() : response.headers;
        }

        @Override // com.sqnetwork.voly.Response.ErrorListener
        public void onErrorResponse(VolleyError error) {
            RequestCallback requestCallback = this.mCallback;
            if (requestCallback != null) {
                requestCallback.onError(RequestErrorCode.of(error), headers(error.networkResponse), error);
            }
        }
    }

    public String getUrl() {
        return this.mUrl;
    }

    public int getMethod() {
        return this.mMethod;
    }

    public String getMethodStr() {
        switch (this.mMethod) {
            case 0:
                return "get";
            case 1:
                return "post";
            case 2:
                return "put";
            case 3:
                return SqR.string.delete;
            case 4:
                return "head";
            case 5:
                return "options";
            case 6:
                return "trace";
            case 7:
                return "patch";
            default:
                return "unknown(" + this.mMethod + ")";
        }
    }

    public int getBodyType() {
        return this.mBodyType;
    }

    public String getBodyTypeStr() {
        int i = this.mBodyType;
        if (i == 1) {
            return "form";
        }
        if (i == 2) {
            return "json";
        }
        return "unknown(" + this.mBodyType + ")";
    }
}
