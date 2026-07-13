package io.reactivex.rxjava3.internal.util;

import org.reactivestreams.Subscriber;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public interface QueueDrain<T, U> {
    boolean accept(Subscriber<? super U> a, T v);

    boolean cancelled();

    boolean done();

    boolean enter();

    Throwable error();

    int leave(int m);

    long produced(long n);

    long requested();
}
