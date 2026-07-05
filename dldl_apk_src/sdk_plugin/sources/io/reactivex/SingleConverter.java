package io.reactivex;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public interface SingleConverter<T, R> {
    R apply(Single<T> single);
}
