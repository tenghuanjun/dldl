package io.reactivex.rxjava3.core;

/* JADX INFO: loaded from: classes3.dex */
public interface Emitter<T> {
    void onComplete();

    void onError(Throwable error);

    void onNext(T value);
}
