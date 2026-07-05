package org.reactivestreams;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public interface Publisher<T> {
    void subscribe(Subscriber<? super T> subscriber);
}
