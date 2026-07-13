package io.reactivex.rxjava3.internal.util;

import org.reactivestreams.Subscriber;

/* JADX INFO: loaded from: classes3.dex */
public interface QueueDrain<T, U> {
    boolean accept(Subscriber<? super U> a2, T v);

    boolean cancelled();

    boolean done();

    boolean enter();

    Throwable error();

    int leave(int m);

    long produced(long n);

    long requested();
}
