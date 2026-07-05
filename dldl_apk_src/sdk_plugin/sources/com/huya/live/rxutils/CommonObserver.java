package com.huya.live.rxutils;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class CommonObserver<T> implements Observer<T> {
    private Observer downStream;

    public CommonObserver(Observer observer) {
        this.downStream = observer;
    }

    @Override // io.reactivex.Observer
    public void onSubscribe(Disposable disposable) {
        this.downStream.onSubscribe(disposable);
    }

    @Override // io.reactivex.Observer
    public void onNext(T t) {
        this.downStream.onNext(t);
    }

    @Override // io.reactivex.Observer
    public void onError(Throwable th) {
        this.downStream.onError(th);
    }

    @Override // io.reactivex.Observer
    public void onComplete() {
        this.downStream.onComplete();
    }
}
