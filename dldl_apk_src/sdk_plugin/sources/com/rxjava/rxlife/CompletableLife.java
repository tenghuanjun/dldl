package com.rxjava.rxlife;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.observers.CallbackCompletableObserver;
import io.reactivex.internal.observers.EmptyCompletableObserver;
import io.reactivex.plugins.RxJavaPlugins;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class CompletableLife extends RxSource<CompletableObserver> {
    private Completable upStream;

    CompletableLife(Completable completable, Scope scope, boolean z) {
        super(scope, z);
        this.upStream = completable;
    }

    @Override // com.rxjava.rxlife.RxSource
    public final Disposable subscribe() {
        EmptyCompletableObserver emptyCompletableObserver = new EmptyCompletableObserver();
        subscribe((CompletableObserver) emptyCompletableObserver);
        return emptyCompletableObserver;
    }

    public final Disposable subscribe(Action action) {
        ObjectHelper.requireNonNull(action, "onComplete is null");
        CallbackCompletableObserver callbackCompletableObserver = new CallbackCompletableObserver(action);
        subscribe((CompletableObserver) callbackCompletableObserver);
        return callbackCompletableObserver;
    }

    public final Disposable subscribe(Action action, Consumer<? super Throwable> consumer) {
        ObjectHelper.requireNonNull(consumer, "onError is null");
        ObjectHelper.requireNonNull(action, "onComplete is null");
        CallbackCompletableObserver callbackCompletableObserver = new CallbackCompletableObserver(consumer, action);
        subscribe((CompletableObserver) callbackCompletableObserver);
        return callbackCompletableObserver;
    }

    @Override // com.rxjava.rxlife.RxSource
    public final void subscribe(CompletableObserver completableObserver) {
        ObjectHelper.requireNonNull(completableObserver, "observer is null");
        try {
            CompletableObserver completableObserverOnSubscribe = RxJavaPlugins.onSubscribe(this.upStream, completableObserver);
            ObjectHelper.requireNonNull(completableObserverOnSubscribe, "The RxJavaPlugins.onSubscribe hook returned a null CompletableObserver. Please check the handler provided to RxJavaPlugins.setOnCompletableSubscribe for invalid null returns. Further reading: https://github.com/ReactiveX/RxJava/wiki/Plugins");
            subscribeActual(completableObserverOnSubscribe);
        } catch (NullPointerException e) {
            throw e;
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            RxJavaPlugins.onError(th);
            NullPointerException nullPointerException = new NullPointerException("Actually not, but can't pass out an exception otherwise...");
            nullPointerException.initCause(th);
            throw nullPointerException;
        }
    }

    private void subscribeActual(CompletableObserver completableObserver) {
        Completable completableObserveOn = this.upStream;
        if (this.onMain) {
            completableObserveOn = completableObserveOn.observeOn(AndroidSchedulers.mainThread());
        }
        completableObserveOn.onTerminateDetach().subscribe(new LifeCompletableObserver(completableObserver, this.scope));
    }
}
