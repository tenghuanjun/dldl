package org.reactivestreams;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public interface Subscription {
    void cancel();

    void request(long j);
}
