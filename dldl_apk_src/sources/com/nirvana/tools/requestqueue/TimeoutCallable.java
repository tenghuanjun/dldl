package com.nirvana.tools.requestqueue;

import java.util.concurrent.Callable;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes6.dex */
public interface TimeoutCallable<T> extends Callable<T> {
    T onTimeout();
}
