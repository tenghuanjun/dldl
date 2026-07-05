package com.huya.live.rxutils;

import io.reactivex.Observable;
import io.reactivex.Observer;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class CommonObservable<T> extends Observable<T> {
    private final Observable<T> upstream;

    public CommonObservable(Observable<T> observable) {
        this.upstream = observable;
    }

    @Override // io.reactivex.Observable
    protected void subscribeActual(Observer<? super T> observer) {
        this.upstream.subscribe(new CommonObserver(observer));
    }
}
