package io.reactivex.rxjava3.core;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
@FunctionalInterface
public interface MaybeOnSubscribe<T> {
    void subscribe(MaybeEmitter<T> emitter) throws Throwable;
}
