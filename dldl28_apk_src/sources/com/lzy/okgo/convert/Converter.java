package com.lzy.okgo.convert;

import okhttp3.Response;

/* JADX INFO: loaded from: classes3.dex */
public interface Converter<T> {
    T convertResponse(Response response) throws Throwable;
}
