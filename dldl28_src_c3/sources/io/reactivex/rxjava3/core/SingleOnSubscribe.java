package io.reactivex.rxjava3.core;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
@FunctionalInterface
public interface SingleOnSubscribe<T> {
    void subscribe(SingleEmitter<T> emitter) throws Throwable;
}
