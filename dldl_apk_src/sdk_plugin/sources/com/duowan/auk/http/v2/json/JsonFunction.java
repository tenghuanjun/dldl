package com.duowan.auk.http.v2.json;

import android.text.TextUtils;
import com.android.volley.NetworkResponse;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.duowan.auk.http.v2.HttpFunction;
import com.duowan.auk.util.L;
import com.google.gson.Gson;
import com.google.gson.internal.C$Gson$Types;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Set;
import kotlin.text.Typography;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public abstract class JsonFunction<T> extends HttpFunction<T> {
    protected static final String PROTOCOL_CHARSET = "utf-8";
    private static final String PROTOCOL_CONTENT_TYPE = String.format("application/json; charset=%s", PROTOCOL_CHARSET);
    private static final String TAG = "JsonFunction";
    private Map<String, String> mRequestParamsMap;

    protected abstract String getFuncPath();

    protected abstract String getServerUrl();

    public JsonFunction(Map<String, String> map) {
        this.mRequestParamsMap = map;
    }

    @Override // com.duowan.auk.http.v2.HttpFunction
    public T onReadResponse(NetworkResponse networkResponse) throws VolleyError {
        return (T) parseJson(readJsonFromResponse(networkResponse), getSuperclassTypeParameter(getClass(), getTargetType()));
    }

    public Type getTargetType() {
        return JsonFunction.class;
    }

    protected String readJsonFromResponse(NetworkResponse networkResponse) throws VolleyError {
        try {
            return new String(networkResponse.data, HttpHeaderParser.parseCharset(networkResponse.headers, PROTOCOL_CHARSET));
        } catch (UnsupportedEncodingException e) {
            throw new VolleyError(e);
        }
    }

    private <E> E parseJson(String str, Type type) {
        try {
            return (E) JsonNullTerminator.terminate(new Gson().fromJson(str, type));
        } catch (Throwable th) {
            L.error(TAG, "parse json fail: type %s", th);
            return null;
        }
    }

    public static Type getSuperclassTypeParameter(Class<?> cls, Type type) {
        while (cls != null) {
            Type genericSuperclass = cls.getGenericSuperclass();
            if (genericSuperclass instanceof Class) {
                cls = cls.getSuperclass();
            } else {
                ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
                if (parameterizedType.getRawType().equals(type)) {
                    return C$Gson$Types.canonicalize(parameterizedType.getActualTypeArguments()[0]);
                }
                return getSuperclassTypeParameter(cls.getSuperclass(), type);
            }
        }
        throw new RuntimeException("Missing type parameter.");
    }

    @Override // com.duowan.auk.http.v2.HttpRequestDelegate
    public String getBodyContentType() {
        return PROTOCOL_CONTENT_TYPE;
    }

    @Override // com.duowan.auk.http.v2.HttpRequestDelegate
    public final String getUrl() {
        StringBuilder sb = new StringBuilder(getServerUrl());
        String funcPath = getFuncPath();
        if (!TextUtils.isEmpty(funcPath)) {
            sb.append("/");
            sb.append(funcPath);
        }
        if (getMethod() == 0) {
            if (sb.indexOf("?") < 0) {
                sb.append('?');
            } else {
                sb.append(Typography.amp);
            }
            sb.append(getParamsString());
        }
        return sb.toString();
    }

    @Override // com.duowan.auk.http.v2.HttpRequestDelegate
    public final byte[] getBody() {
        try {
            return getParamsString().getBytes(PROTOCOL_CHARSET);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    private String encodeParameters(Map<String, String> map, String str) {
        StringBuilder sb = new StringBuilder();
        try {
            Set<Map.Entry<String, String>> setEntrySet = map.entrySet();
            for (Map.Entry<String, String> entry : setEntrySet) {
                String key = entry.getKey();
                String value = entry.getValue();
                if (key == null) {
                    L.info(TAG, "key is empty, entrySet = " + setEntrySet);
                } else {
                    if (value == null) {
                        L.info(TAG, "value is empty, key = " + key);
                        value = "";
                    }
                    sb.append(URLEncoder.encode(key, str));
                    sb.append('=');
                    sb.append(URLEncoder.encode(value, str));
                    sb.append(Typography.amp);
                }
            }
            return sb.toString();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
    }

    private String getParamsString() {
        return encodeParameters(getParams(), PROTOCOL_CHARSET);
    }

    @Override // com.duowan.auk.http.v2.HttpFunction, com.duowan.auk.http.v2.HttpRequestDelegate
    public Map<String, String> getParams() {
        return this.mRequestParamsMap;
    }
}
