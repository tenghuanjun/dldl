package io.reactivex.parallel;

import io.reactivex.functions.BiFunction;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public enum ParallelFailureHandling implements BiFunction<Long, Throwable, ParallelFailureHandling> {
    STOP,
    ERROR,
    SKIP,
    RETRY;

    @Override // io.reactivex.functions.BiFunction
    public ParallelFailureHandling apply(Long l, Throwable th) {
        return this;
    }
}
