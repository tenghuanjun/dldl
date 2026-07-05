package com.taptap.sdk.retrofit2;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public interface Callback<T> {
    void onFailure(Call<T> call, Throwable th);

    void onResponse(Call<T> call, Response<T> response);
}
