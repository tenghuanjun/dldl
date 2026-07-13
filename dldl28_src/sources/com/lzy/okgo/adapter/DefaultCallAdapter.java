package com.lzy.okgo.adapter;

/* JADX INFO: loaded from: classes3.dex */
public class DefaultCallAdapter<T> implements CallAdapter<T, Call<T>> {
    @Override // com.lzy.okgo.adapter.CallAdapter
    public Call<T> adapt(Call<T> call, AdapterParam adapterParam) {
        return call;
    }
}
