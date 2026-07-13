package com.lzy.okgo.adapter;

/* JADX INFO: loaded from: classes3.dex */
public interface CallAdapter<T, R> {
    R adapt(Call<T> call, AdapterParam adapterParam);
}
