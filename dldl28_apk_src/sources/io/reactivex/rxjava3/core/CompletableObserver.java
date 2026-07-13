package io.reactivex.rxjava3.core;

import io.reactivex.rxjava3.disposables.Disposable;

/* JADX INFO: loaded from: classes3.dex */
public interface CompletableObserver {
    void onComplete();

    void onError(Throwable e);

    void onSubscribe(Disposable d);
}
