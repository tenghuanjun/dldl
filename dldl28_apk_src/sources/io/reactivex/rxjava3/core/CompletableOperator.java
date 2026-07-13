package io.reactivex.rxjava3.core;

/* JADX INFO: loaded from: classes3.dex */
@FunctionalInterface
public interface CompletableOperator {
    CompletableObserver apply(CompletableObserver observer) throws Throwable;
}
