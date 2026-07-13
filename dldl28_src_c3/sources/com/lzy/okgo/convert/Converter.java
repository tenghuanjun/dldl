package com.lzy.okgo.convert;

import okhttp3.Response;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public interface Converter<T> {
    T convertResponse(Response response) throws Throwable;
}
