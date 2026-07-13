package io.reactivex.rxjava3.internal.operators.flowable;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.internal.subscriptions.EmptySubscription;
import org.reactivestreams.Subscriber;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public final class FlowableNever extends Flowable<Object> {
    public static final Flowable<Object> INSTANCE = new FlowableNever();

    private FlowableNever() {
    }

    @Override // io.reactivex.rxjava3.core.Flowable
    public void subscribeActual(Subscriber<? super Object> s) {
        s.onSubscribe(EmptySubscription.INSTANCE);
    }
}
