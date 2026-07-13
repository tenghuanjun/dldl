package com.nirvana.tools.requestqueue;

import java.util.concurrent.Callable;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public interface TimeoutCallable<T> extends Callable<T> {
    T onTimeout();
}
