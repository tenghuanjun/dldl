package io.reactivex.rxjava3.core;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public interface FlowableSubscriber<T> extends Subscriber<T> {
    void onSubscribe(Subscription s);
}
