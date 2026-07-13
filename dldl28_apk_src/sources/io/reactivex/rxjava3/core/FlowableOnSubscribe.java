package io.reactivex.rxjava3.core;

/* JADX INFO: loaded from: classes3.dex */
@FunctionalInterface
public interface FlowableOnSubscribe<T> {
    void subscribe(FlowableEmitter<T> emitter) throws Throwable;
}
