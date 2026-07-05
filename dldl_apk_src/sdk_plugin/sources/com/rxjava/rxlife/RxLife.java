package com.rxjava.rxlife;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.view.View;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.parallel.ParallelFlowable;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public final class RxLife {
    public static <T> RxConverter<T> as(LifecycleOwner lifecycleOwner) {
        return as(lifecycleOwner, Lifecycle.Event.ON_DESTROY, false);
    }

    public static <T> RxConverter<T> as(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
        return as(lifecycleOwner, event, false);
    }

    public static <T> RxConverter<T> asOnMain(LifecycleOwner lifecycleOwner) {
        return as(lifecycleOwner, Lifecycle.Event.ON_DESTROY, true);
    }

    public static <T> RxConverter<T> asOnMain(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
        return as(lifecycleOwner, event, true);
    }

    private static <T> RxConverter<T> as(LifecycleOwner lifecycleOwner, Lifecycle.Event event, boolean z) {
        return as(LifecycleScope.from(lifecycleOwner, event), z);
    }

    public static <T> RxConverter<T> as(View view) {
        return as((Scope) ViewScope.from(view, false), false);
    }

    public static <T> RxConverter<T> as(View view, boolean z) {
        return as((Scope) ViewScope.from(view, z), false);
    }

    public static <T> RxConverter<T> asOnMain(View view) {
        return as((Scope) ViewScope.from(view, false), true);
    }

    public static <T> RxConverter<T> asOnMain(View view, boolean z) {
        return as((Scope) ViewScope.from(view, z), true);
    }

    public static <T> RxConverter<T> as(Scope scope) {
        return as(scope, false);
    }

    public static <T> RxConverter<T> asOnMain(Scope scope) {
        return as(scope, true);
    }

    private static <T> RxConverter<T> as(final Scope scope, final boolean z) {
        return new RxConverter<T>() { // from class: com.rxjava.rxlife.RxLife.1
            @Override // io.reactivex.ObservableConverter
            public ObservableLife<T> apply(Observable<T> observable) {
                return new ObservableLife<>(observable, scope, z);
            }

            @Override // io.reactivex.FlowableConverter
            public FlowableLife<T> apply(Flowable<T> flowable) {
                return new FlowableLife<>(flowable, scope, z);
            }

            @Override // io.reactivex.parallel.ParallelFlowableConverter
            public ParallelFlowableLife<T> apply(ParallelFlowable<T> parallelFlowable) {
                return new ParallelFlowableLife<>(parallelFlowable, scope, z);
            }

            @Override // io.reactivex.MaybeConverter
            public MaybeLife<T> apply(Maybe<T> maybe) {
                return new MaybeLife<>(maybe, scope, z);
            }

            @Override // io.reactivex.SingleConverter
            public SingleLife<T> apply(Single<T> single) {
                return new SingleLife<>(single, scope, z);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // io.reactivex.CompletableConverter
            public CompletableLife apply(Completable completable) {
                return new CompletableLife(completable, scope, z);
            }
        };
    }
}
