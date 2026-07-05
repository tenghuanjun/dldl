package io.reactivex;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public interface FlowableConverter<T, R> {
    R apply(Flowable<T> flowable);
}
