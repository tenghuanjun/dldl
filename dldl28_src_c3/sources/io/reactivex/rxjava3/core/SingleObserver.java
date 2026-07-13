package io.reactivex.rxjava3.core;

import io.reactivex.rxjava3.disposables.Disposable;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public interface SingleObserver<T> {
    void onError(Throwable e);

    void onSubscribe(Disposable d);

    void onSuccess(T t);
}
