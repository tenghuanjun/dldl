package io.reactivex.rxjava3.core;

/* JADX INFO: loaded from: classes3.dex */
@FunctionalInterface
public interface CompletableConverter<R> {
    R apply(Completable upstream);
}
