package io.reactivex.rxjava3.internal.subscribers;

import io.reactivex.rxjava3.plugins.RxJavaPlugins;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public final class BlockingFirstSubscriber<T> extends BlockingBaseSubscriber<T> {
    public void onNext(T t) {
        if (this.value == null) {
            this.value = t;
            this.upstream.cancel();
            countDown();
        }
    }

    public void onError(Throwable t) {
        if (this.value == null) {
            this.error = t;
        } else {
            RxJavaPlugins.onError(t);
        }
        countDown();
    }
}
