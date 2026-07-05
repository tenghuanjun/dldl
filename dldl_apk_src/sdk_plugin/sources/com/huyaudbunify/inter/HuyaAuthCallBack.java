package com.huyaudbunify.inter;

import com.google.gson.Gson;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public abstract class HuyaAuthCallBack<T> {
    private Class modelClass;

    public abstract void hyCallBack(T t);

    public HuyaAuthCallBack(Class cls) {
        this.modelClass = cls;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void hyCallBackData(String str) {
        hyCallBack(parseJson(str, this.modelClass));
    }

    public static Object parseJson(String str, Class cls) {
        try {
            return new Gson().fromJson(str, cls);
        } catch (Error | Exception unused) {
            return null;
        }
    }
}
