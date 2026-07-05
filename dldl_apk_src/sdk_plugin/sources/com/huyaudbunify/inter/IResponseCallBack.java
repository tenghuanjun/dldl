package com.huyaudbunify.inter;

import com.google.gson.Gson;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public abstract class IResponseCallBack<T> {
    private Class modelClass;

    public abstract void onResponse(T t);

    public IResponseCallBack(Class cls) {
        this.modelClass = cls;
    }

    public T getFromDataString(String str) {
        return (T) parseJson(str, this.modelClass);
    }

    private static Object parseJson(String str, Class cls) {
        try {
            return new Gson().fromJson(str, cls);
        } catch (Error | Exception unused) {
            return null;
        }
    }
}
