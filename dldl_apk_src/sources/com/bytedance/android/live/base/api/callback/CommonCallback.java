package com.bytedance.android.live.base.api.callback;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public interface CommonCallback<T, D> {
    void onFail(D d);

    void onSuccess(T t);
}
