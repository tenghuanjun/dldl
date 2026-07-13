package io.reactivex.rxjava3.core;

import org.reactivestreams.Publisher;

/* JADX INFO: loaded from: classes3.dex */
@FunctionalInterface
public interface FlowableTransformer<Upstream, Downstream> {
    Publisher<Downstream> apply(Flowable<Upstream> upstream);
}
