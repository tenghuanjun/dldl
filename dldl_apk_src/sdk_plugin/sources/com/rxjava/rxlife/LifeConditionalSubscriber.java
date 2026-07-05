package com.rxjava.rxlife;

import io.reactivex.exceptions.CompositeException;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.internal.fuseable.ConditionalSubscriber;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.plugins.RxJavaPlugins;
import org.reactivestreams.Subscription;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
final class LifeConditionalSubscriber<T> extends AbstractLifecycle<Subscription> implements ConditionalSubscriber<T> {
    private ConditionalSubscriber<? super T> downstream;

    LifeConditionalSubscriber(ConditionalSubscriber<? super T> conditionalSubscriber, Scope scope) {
        super(scope);
        this.downstream = conditionalSubscriber;
    }

    @Override // io.reactivex.FlowableSubscriber, org.reactivestreams.Subscriber
    public void onSubscribe(Subscription subscription) {
        if (SubscriptionHelper.setOnce(this, subscription)) {
            try {
                addObserver();
                this.downstream.onSubscribe(subscription);
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                subscription.cancel();
                onError(th);
            }
        }
    }

    @Override // org.reactivestreams.Subscriber
    public void onNext(T t) {
        if (isDisposed()) {
            return;
        }
        try {
            this.downstream.onNext(t);
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            ((Subscription) get()).cancel();
            onError(th);
        }
    }

    @Override // org.reactivestreams.Subscriber
    public void onError(Throwable th) {
        if (isDisposed()) {
            RxJavaPlugins.onError(th);
            return;
        }
        lazySet(SubscriptionHelper.CANCELLED);
        try {
            removeObserver();
            this.downstream.onError(th);
        } catch (Throwable th2) {
            Exceptions.throwIfFatal(th2);
            RxJavaPlugins.onError(new CompositeException(th, th2));
        }
    }

    @Override // org.reactivestreams.Subscriber
    public void onComplete() {
        if (isDisposed()) {
            return;
        }
        lazySet(SubscriptionHelper.CANCELLED);
        try {
            removeObserver();
            this.downstream.onComplete();
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            RxJavaPlugins.onError(th);
        }
    }

    @Override // io.reactivex.disposables.Disposable
    public boolean isDisposed() {
        return get() == SubscriptionHelper.CANCELLED;
    }

    @Override // io.reactivex.disposables.Disposable
    public void dispose() {
        SubscriptionHelper.cancel(this);
    }

    @Override // io.reactivex.internal.fuseable.ConditionalSubscriber
    public boolean tryOnNext(T t) {
        if (isDisposed()) {
            return false;
        }
        return this.downstream.tryOnNext(t);
    }
}
