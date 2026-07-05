package com.sq.diagnostic.assistant.log;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public interface ILogCallBack<T> {
    void onFailed(int i, String str);

    void onSuccess(T t);
}
