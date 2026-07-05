package com.huya.mtp.hyns.rx;

import com.huya.mtp.hyns.NSCall;
import com.huya.mtp.hyns.NSResponse;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.plugins.RxJavaPlugins;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class CallExecuteObservable<T> extends NSObservable<NSResponse<T>> {
    private final NSCall<T> originalCall;

    public CallExecuteObservable(NSCall<T> nSCall) {
        this.originalCall = nSCall;
    }

    @Override // io.reactivex.Observable
    protected void subscribeActual(Observer<? super NSResponse<T>> observer) {
        boolean z;
        NSCall<T> nSCallM18clone = this.originalCall.m18clone();
        CallDisposable callDisposable = new CallDisposable(nSCallM18clone);
        observer.onSubscribe(callDisposable);
        try {
            NSResponse<T> nSResponseExecute = nSCallM18clone.execute(getNSSettings());
            if (!callDisposable.isDisposed()) {
                observer.onNext(nSResponseExecute);
            }
            if (callDisposable.isDisposed()) {
                return;
            }
            try {
                observer.onComplete();
            } catch (Throwable th) {
                th = th;
                z = true;
                Exceptions.throwIfFatal(th);
                if (z) {
                    RxJavaPlugins.onError(th);
                    return;
                }
                if (callDisposable.isDisposed()) {
                    return;
                }
                try {
                    observer.onError(th);
                } catch (Throwable th2) {
                    Exceptions.throwIfFatal(th2);
                    RxJavaPlugins.onError(new CompositeException(th, th2));
                }
            }
        } catch (Throwable th3) {
            th = th3;
            z = false;
        }
    }

    private static final class CallDisposable implements Disposable {
        private final NSCall<?> call;
        private volatile boolean disposed;

        CallDisposable(NSCall<?> nSCall) {
            this.call = nSCall;
        }

        @Override // io.reactivex.disposables.Disposable
        public void dispose() {
            this.disposed = true;
            this.call.cancel();
        }

        @Override // io.reactivex.disposables.Disposable
        public boolean isDisposed() {
            return this.disposed;
        }
    }
}
