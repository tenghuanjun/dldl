package com.huya.live.ns.rxjava;

import com.duowan.auk.util.L;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class WupObserver<T> implements Observer<T> {
    @Override // io.reactivex.Observer
    public void onComplete() {
    }

    @Override // io.reactivex.Observer
    public void onNext(T t) {
    }

    @Override // io.reactivex.Observer
    public void onSubscribe(Disposable disposable) {
    }

    @Override // io.reactivex.Observer
    public void onError(Throwable th) {
        L.error(th.toString());
    }
}
