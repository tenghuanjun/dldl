package com.huya.mtp.hyns.rx;

import com.huya.mtp.hyns.NSResponse;
import com.huya.mtp.hyns.NSSettings;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.plugins.RxJavaPlugins;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class BodyObservable<T> extends NSObservable<T> {
    private final NSObservable<NSResponse<T>> upstream;

    BodyObservable(NSObservable<NSResponse<T>> nSObservable) {
        this.upstream = nSObservable;
        setNSSettings(nSObservable.getNSSettings());
    }

    @Override // com.huya.mtp.hyns.rx.NSObservable
    public NSObservable<T> setNSSettings(NSSettings nSSettings) {
        this.upstream.setNSSettings(nSSettings);
        return super.setNSSettings(nSSettings);
    }

    @Override // io.reactivex.Observable
    protected void subscribeActual(Observer<? super T> observer) {
        this.upstream.subscribe(new BodyObserver(observer));
    }

    private static class BodyObserver<R> implements Observer<NSResponse<R>> {
        private final Observer<? super R> observer;
        private boolean terminated;

        BodyObserver(Observer<? super R> observer) {
            this.observer = observer;
        }

        @Override // io.reactivex.Observer
        public void onSubscribe(Disposable disposable) {
            this.observer.onSubscribe(disposable);
        }

        @Override // io.reactivex.Observer
        public void onNext(NSResponse<R> nSResponse) {
            this.observer.onNext(nSResponse.getData());
        }

        @Override // io.reactivex.Observer
        public void onComplete() {
            if (this.terminated) {
                return;
            }
            this.observer.onComplete();
        }

        @Override // io.reactivex.Observer
        public void onError(Throwable th) {
            if (!this.terminated) {
                this.observer.onError(th);
                return;
            }
            AssertionError assertionError = new AssertionError("This should never happen! Report as a bug with the full stacktrace.");
            assertionError.initCause(th);
            RxJavaPlugins.onError(assertionError);
        }
    }
}
