package com.sdk.sq.net;

import com.google.gson.Gson;
import com.sdk.sq.net.RequestBuilder;
import com.sqnetwork.voly.NetworkResponse;
import com.sqnetwork.voly.VolleyError;
import com.sqnetwork.voly.toolbox.HttpHeaderParser;
import com.sqwan.common.route.FunctionRouter;
import java.lang.reflect.Constructor;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public abstract class SqRequestCallback<Data> implements RequestBuilder.RequestCallback {
    private final Class<?> mDataClass;
    private final Gson mGson;
    private JSONObject mResponse;

    public interface JsonTransformer {
        void transform(JSONObject dataJson);
    }

    protected String getDataKey() {
        return FunctionRouter.KEY_DATA;
    }

    protected String getMsgKey() {
        return "msg";
    }

    protected int getOkState() {
        return 1;
    }

    protected String getStateKey() {
        return "state";
    }

    public abstract void onRequestError(int code, VolleyError error);

    public abstract void onRequestSuccess(int httpStatus, Data data);

    public abstract void onResponseStateError(int httpStatus, int state, String msg, String data);

    public SqRequestCallback() {
        this(null);
    }

    public SqRequestCallback(Class<?> dataClass) {
        this(dataClass, null);
    }

    public SqRequestCallback(Class<?> dataClass, Gson gson) {
        this.mDataClass = dataClass;
        this.mGson = gson;
    }

    @Override // com.sdk.sq.net.RequestBuilder.RequestCallback
    public void onSuccess(int httpStatus, Map<String, String> headers, JSONObject response) {
        this.mResponse = response;
        try {
            int i = response.getInt(getStateKey());
            if (getOkState() == i) {
                try {
                    onRequestSuccess(httpStatus, parse(response.optString(getDataKey())));
                    return;
                } catch (Exception e) {
                    onRequestError(RequestErrorCode.ERROR_37_PARSE, new SqParseError("data解析异常(" + e.getMessage() + ")", e));
                    return;
                }
            }
            onResponseStateError(httpStatus, i, response.optString(getMsgKey(), "Unknown Request Error"), response.optString(getDataKey()));
        } catch (Exception e2) {
            onRequestError(RequestErrorCode.ERROR_37_PARSE, new SqParseError("state获取异常", e2));
        }
    }

    @Override // com.sdk.sq.net.RequestBuilder.RequestCallback
    public void onError(int code, Map<String, String> headers, VolleyError error) {
        if (error instanceof SqVerifyError) {
            onRequestError(RequestErrorCode.ERROR_37_VERIFY, error);
            return;
        }
        JSONObject networkResponse = parseNetworkResponse(error.networkResponse);
        if (networkResponse != null) {
            onSuccess(code, headers, networkResponse);
        } else {
            onRequestError(code, error);
        }
    }

    private JSONObject parseNetworkResponse(NetworkResponse response) {
        if (response != null && response.data != null) {
            try {
                String str = new String(response.data, HttpHeaderParser.parseCharset(response.headers, "utf-8"));
                if (str.isEmpty()) {
                    return null;
                }
                return new JSONObject(str);
            } catch (Exception unused) {
            }
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private Data parse(String str) throws Exception {
        Class targetClass = this.mDataClass;
        if (targetClass == null) {
            targetClass = getTargetClass();
        }
        if (Void.TYPE.equals(targetClass) || Void.class.equals(targetClass)) {
            return null;
        }
        if (String.class.equals(targetClass)) {
            return str;
        }
        if (str == 0 || str.isEmpty() || AbstractJsonLexerKt.NULL.equals(str)) {
            throw new JSONException("Empty json string");
        }
        if (JsonTransformer.class.isAssignableFrom(targetClass)) {
            Constructor declaredConstructor = targetClass.getDeclaredConstructor(new Class[0]);
            declaredConstructor.setAccessible(true);
            Data data = (Data) declaredConstructor.newInstance(new Object[0]);
            ((JsonTransformer) data).transform(new JSONObject(str));
            return data;
        }
        if (JSONObject.class.equals(targetClass)) {
            return (Data) new JSONObject(str);
        }
        try {
            Data data2 = (Data) (this.mGson != null ? this.mGson : new Gson()).fromJson(str, targetClass);
            if (data2 != null) {
                return data2;
            }
            throw new NullPointerException("Expect " + targetClass.getName() + " but null");
        } catch (RuntimeException e) {
            throw new JSONException(e.getMessage());
        }
    }

    private Class<Data> getTargetClass() {
        Type genericSuperclass = getClass().getGenericSuperclass();
        if (genericSuperclass instanceof ParameterizedType) {
            Type type = ((ParameterizedType) genericSuperclass).getActualTypeArguments()[0];
            if (type instanceof Class) {
                return (Class) type;
            }
        }
        throw new IllegalStateException("无法获取回调的data类型, 请明确指定data数据类型, 或者检查子类泛型");
    }

    public JSONObject getResponse() {
        return this.mResponse;
    }

    public String getResponseStr() {
        JSONObject jSONObject = this.mResponse;
        return jSONObject == null ? "" : jSONObject.toString();
    }
}
