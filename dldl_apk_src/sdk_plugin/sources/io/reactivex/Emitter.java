package io.reactivex;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public interface Emitter<T> {
    void onComplete();

    void onError(Throwable th);

    void onNext(T t);
}
