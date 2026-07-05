package com.duowan.auk.http;

import com.duowan.auk.http.JsonModel;
import com.google.gson.Gson;
import com.google.gson.internal.C$Gson$Types;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import master.flame.danmaku.danmaku.parser.IDataSource;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public abstract class JsonEasyHandler<T extends JsonModel> extends StringEasyHandler {
    public abstract void onFailure(FailReason failReason);

    public abstract void onSuccess(T t);

    public enum FailReason {
        Http(IDataSource.SCHEME_HTTP_TAG),
        JsonParse("json_pare");

        public final String name;

        FailReason(String str) {
            this.name = str;
        }
    }

    @Override // com.duowan.auk.http.StringEasyHandler
    public void onSuccess(String str) {
        try {
            JsonModel jsonModel = (JsonModel) new Gson().fromJson(str, getSuperclassTypeParameter(getClass()));
            if (jsonModel != null && jsonModel.valid()) {
                onSuccess(jsonModel);
            } else {
                onFailure(FailReason.JsonParse);
            }
        } catch (Exception unused) {
            onFailure(FailReason.JsonParse);
        }
    }

    @Override // com.duowan.auk.http.StringEasyHandler
    public void onFailure() {
        onFailure(FailReason.Http);
    }

    private Type getSuperclassTypeParameter(Class<?> cls) {
        Type genericSuperclass = cls.getGenericSuperclass();
        if (genericSuperclass instanceof Class) {
            throw new RuntimeException("Missing type parameter.");
        }
        ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
        if (parameterizedType.getRawType().equals(JsonEasyHandler.class)) {
            return C$Gson$Types.canonicalize(parameterizedType.getActualTypeArguments()[0]);
        }
        return getSuperclassTypeParameter(cls.getSuperclass());
    }
}
