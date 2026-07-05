package com.sq.tool.network;

import com.sdk.sq.net.RequestBuilder;
import com.sq.tool.network.SignInterceptor;
import com.sq.tool.network.SignV2Interceptor;
import com.sqnetwork.voly.AuthFailureError;
import com.sqnetwork.voly.Request;
import com.sqnetwork.voly.VolleyError;
import com.sqnetwork.voly.VolleyLog;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class SqRequest {
    private Request<?> mRequest;
    private final RequestBuilder mRequestBuilder;
    private List<ResponseStateInterceptor> mResponseStateInterceptors;

    public interface ResponseStateInterceptor {
        boolean onInterceptResponseState(RequestBuilder requestBuilder, int i, int i2, String str, String str2, SqHttpCallback<?> sqHttpCallback);
    }

    public static String getTransInfo() {
        return "";
    }

    private SqRequest(String str) {
        this.mRequestBuilder = SqHttpClient.getInstance().newBuilder().url(str);
    }

    public static SqRequest of(String str) {
        return new SqRequest(str).addResponseStateInterceptor(new RiskInterceptor());
    }

    public SqRequest bodyType(int i) {
        this.mRequestBuilder.bodyType(i);
        return this;
    }

    public SqRequest signV1() {
        this.mRequestBuilder.tag(SignInterceptor.SignVersion.class, SignInterceptor.SignVersion.V1);
        return this;
    }

    public SqRequest signV2(SignV2Interceptor.SignExt signExt) {
        this.mRequestBuilder.tag(SignInterceptor.SignVersion.class, SignInterceptor.SignVersion.V2).tag(SignV2Interceptor.SignExt.class, signExt);
        return this;
    }

    public SqRequest signV3() {
        this.mRequestBuilder.tag(SignInterceptor.SignVersion.class, SignInterceptor.SignVersion.V3);
        return this;
    }

    public SqRequest signV5() {
        this.mRequestBuilder.tag(SignInterceptor.SignVersion.class, SignInterceptor.SignVersion.V5);
        return this;
    }

    public SqRequest sign(SignInterceptor.SignVersion signVersion, SignV2Interceptor.SignExt signExt) {
        this.mRequestBuilder.tag(SignInterceptor.SignVersion.class, signVersion).tag(SignV2Interceptor.SignExt.class, signExt);
        return this;
    }

    public SqRequest addParamsTransformer(RequestBuilder.ParamsTransformer paramsTransformer) {
        this.mRequestBuilder.addParamsTransformer(paramsTransformer);
        return this;
    }

    public SqRequest params(Map<String, String> map) {
        this.mRequestBuilder.params(map);
        return this;
    }

    public SqRequest formParams(Map<String, String> map) {
        this.mRequestBuilder.formBody(map);
        return this;
    }

    public SqRequest addParam(String str, Object obj) {
        this.mRequestBuilder.addParam(str, obj);
        return this;
    }

    public SqRequest addHeader(String str, String str2) {
        this.mRequestBuilder.addHeader(str, str2);
        return this;
    }

    public SqRequest jsonParams(Map<String, Object> map) {
        this.mRequestBuilder.jsonBody(map);
        return this;
    }

    public SqRequest addResponseStateInterceptor(ResponseStateInterceptor responseStateInterceptor) {
        if (responseStateInterceptor == null) {
            return this;
        }
        if (this.mResponseStateInterceptors == null) {
            this.mResponseStateInterceptors = new ArrayList();
        }
        this.mResponseStateInterceptors.add(responseStateInterceptor);
        return this;
    }

    public RequestBuilder builder() {
        return this.mRequestBuilder;
    }

    public void get(SqHttpCallback<JSONObject> sqHttpCallback) {
        get(sqHttpCallback, JSONObject.class);
    }

    public <Data> void get(SqHttpCallback<Data> sqHttpCallback, Class<Data> cls) {
        enqueue(0, sqHttpCallback, cls);
    }

    public void post(SqHttpCallback<JSONObject> sqHttpCallback) {
        post(sqHttpCallback, JSONObject.class);
    }

    public <Data> void post(SqHttpCallback<Data> sqHttpCallback, Class<Data> cls) {
        enqueue(1, sqHttpCallback, cls);
    }

    private <Data> void enqueue(int i, final SqHttpCallback<Data> sqHttpCallback, Class<Data> cls) {
        this.mRequestBuilder.method(i);
        this.mRequestBuilder.callback(new SqHttpCallback<Data>(cls) { // from class: com.sq.tool.network.SqRequest.1
            @Override // com.sq.tool.network.SqHttpCallback, com.sdk.sq.net.SqRequestCallback
            protected int getOkState() {
                SqHttpCallback sqHttpCallback2 = sqHttpCallback;
                return sqHttpCallback2 != null ? sqHttpCallback2.getOkState() : super.getOkState();
            }

            @Override // com.sq.tool.network.SqHttpCallback, com.sdk.sq.net.SqRequestCallback
            protected String getStateKey() {
                SqHttpCallback sqHttpCallback2 = sqHttpCallback;
                return sqHttpCallback2 != null ? sqHttpCallback2.getStateKey() : super.getStateKey();
            }

            @Override // com.sq.tool.network.SqHttpCallback, com.sdk.sq.net.SqRequestCallback
            protected String getMsgKey() {
                SqHttpCallback sqHttpCallback2 = sqHttpCallback;
                return sqHttpCallback2 != null ? sqHttpCallback2.getMsgKey() : super.getMsgKey();
            }

            @Override // com.sq.tool.network.SqHttpCallback, com.sdk.sq.net.SqRequestCallback
            protected String getDataKey() {
                SqHttpCallback sqHttpCallback2 = sqHttpCallback;
                return sqHttpCallback2 != null ? sqHttpCallback2.getDataKey() : super.getDataKey();
            }

            @Override // com.sq.tool.network.SqHttpCallback, com.sdk.sq.net.SqRequestCallback, com.sdk.sq.net.RequestBuilder.RequestCallback
            public void onSuccess(int i2, Map<String, String> map, JSONObject jSONObject) {
                SqHttpCallback sqHttpCallback2 = sqHttpCallback;
                if (sqHttpCallback2 != null) {
                    sqHttpCallback2.setResp(jSONObject);
                }
                super.onSuccess(i2, map, jSONObject);
            }

            @Override // com.sdk.sq.net.SqRequestCallback
            public void onResponseStateError(int i2, int i3, String str, String str2) {
                boolean zOnInterceptResponseState;
                if (SqRequest.this.mResponseStateInterceptors != null) {
                    Iterator it = SqRequest.this.mResponseStateInterceptors.iterator();
                    zOnInterceptResponseState = false;
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        ResponseStateInterceptor responseStateInterceptor = (ResponseStateInterceptor) it.next();
                        zOnInterceptResponseState = responseStateInterceptor.onInterceptResponseState(SqRequest.this.mRequestBuilder, i2, i3, str, str2, sqHttpCallback);
                        if (zOnInterceptResponseState) {
                            VolleyLog.w("请求回调被%s拦截, state=%d, msg=%s", responseStateInterceptor.getClass().getName(), Integer.valueOf(i3), str);
                            break;
                        }
                    }
                } else {
                    zOnInterceptResponseState = false;
                }
                if (zOnInterceptResponseState) {
                    return;
                }
                if (VolleyLog.DEBUG) {
                    VolleyLog.w("%s 请求失败, state=%d, msg=%s", SqRequest.this.mRequestBuilder.getUrl(), Integer.valueOf(i3), str);
                }
                SqHttpCallback sqHttpCallback2 = sqHttpCallback;
                if (sqHttpCallback2 != null) {
                    sqHttpCallback2.onResponseStateError(i2, i3, str, str2);
                }
            }

            @Override // com.sq.tool.network.SqHttpCallback
            public void onSuccess(Data data) {
                if (VolleyLog.DEBUG) {
                    VolleyLog.i("%s 请求成功 %s", SqRequest.this.mRequestBuilder.getUrl(), getResponseStr());
                }
                SqHttpCallback sqHttpCallback2 = sqHttpCallback;
                if (sqHttpCallback2 != null) {
                    sqHttpCallback2.onSuccess(data);
                }
            }

            @Override // com.sq.tool.network.SqHttpCallback
            public void onFailure(int i2, String str, VolleyError volleyError) {
                if (VolleyLog.DEBUG) {
                    VolleyLog.e("%s 请求异常, code=%d, errorMsg=%s", SqRequest.this.mRequestBuilder.getUrl(), Integer.valueOf(i2), str);
                }
                SqHttpCallback sqHttpCallback2 = sqHttpCallback;
                if (sqHttpCallback2 != null) {
                    sqHttpCallback2.onFailure(i2, str, volleyError);
                }
            }
        });
        if (this.mRequestBuilder.getBodyType() == 1 || this.mRequestBuilder.getBodyType() == 3) {
            this.mRequestBuilder.addParamsTransformer(new StringifyTransformer());
        }
        this.mRequestBuilder.addHeadersTransformer(new IpHeaderTransformer());
        Request<JSONObject> requestBuild = this.mRequestBuilder.build();
        this.mRequest = requestBuild;
        SqHttpClient.getInstance().enqueue(requestBuild);
    }

    public Map<String, String> getRequestParams() {
        try {
            if (this.mRequest != null) {
                return this.mRequest.getParams();
            }
            return null;
        } catch (AuthFailureError unused) {
            return null;
        }
    }

    public static Map<String, String> stringMap(Map<String, ?> map) {
        if (map == null) {
            return null;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (String str : map.keySet()) {
            Object obj = map.get(str);
            if (obj == null) {
                linkedHashMap.put(str, "");
            } else if (obj instanceof String) {
                linkedHashMap.put(str, (String) obj);
            } else {
                linkedHashMap.put(str, String.valueOf(obj));
            }
        }
        return linkedHashMap;
    }
}
