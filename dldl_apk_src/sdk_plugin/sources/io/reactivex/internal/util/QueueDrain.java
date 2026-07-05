package io.reactivex.internal.util;

import org.reactivestreams.Subscriber;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public interface QueueDrain<T, U> {
    boolean accept(Subscriber<? super U> subscriber, T t);

    boolean cancelled();

    boolean done();

    boolean enter();

    Throwable error();

    int leave(int i);

    long produced(long j);

    long requested();
}
