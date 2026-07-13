package com.bytedance.http;

/* JADX INFO: loaded from: classes2.dex */
public interface Interceptor {

    public interface Chain {
        Call call();

        HttpResponse proceed(HttpRequest httpRequest);

        HttpRequest request();
    }

    HttpResponse intercept(Chain chain);

    String name();
}
