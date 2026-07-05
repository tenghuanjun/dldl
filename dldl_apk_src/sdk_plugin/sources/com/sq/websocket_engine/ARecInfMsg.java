package com.sq.websocket_engine;

import com.google.sqgson.Gson;
import com.google.sqgson.internal.C$Gson$Types;
import com.sq.websocket_engine.parse.ResponseDataParse;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public abstract class ARecInfMsg<T> {
    protected String TAG = getClass().getSimpleName();
    protected T inf;
    public ResponseDataParse responseDataParse;

    public abstract boolean filter();

    public abstract T getInf();

    public void init(ResponseDataParse responseDataParse) {
        this.responseDataParse = responseDataParse;
        this.inf = handle();
    }

    public T handle() {
        if (filter()) {
            return null;
        }
        return (T) new Gson().fromJson(this.responseDataParse.body.msg, getSuperclassTypeParameter(getClass()));
    }

    public Type getSuperclassTypeParameter(Class<?> cls) {
        Type genericSuperclass = cls.getGenericSuperclass();
        if (genericSuperclass instanceof Class) {
            throw new RuntimeException("Missing type parameter.");
        }
        return C$Gson$Types.canonicalize(((ParameterizedType) genericSuperclass).getActualTypeArguments()[0]);
    }
}
