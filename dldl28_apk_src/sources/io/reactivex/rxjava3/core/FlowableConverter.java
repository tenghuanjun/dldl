package io.reactivex.rxjava3.core;

/* JADX INFO: loaded from: classes3.dex */
@FunctionalInterface
public interface FlowableConverter<T, R> {
    R apply(Flowable<T> upstream);
}
