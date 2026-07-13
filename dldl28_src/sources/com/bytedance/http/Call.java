package com.bytedance.http;

/* JADX INFO: loaded from: classes2.dex */
public interface Call {
    Callback callback();

    void cancel();

    void enqueue(Callback callback);

    boolean isCancelled();

    boolean isExecuted();

    HttpRequest request();
}
