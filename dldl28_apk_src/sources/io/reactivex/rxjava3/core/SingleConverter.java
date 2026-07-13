package io.reactivex.rxjava3.core;

/* JADX INFO: loaded from: classes3.dex */
@FunctionalInterface
public interface SingleConverter<T, R> {
    R apply(Single<T> upstream);
}
