package io.reactivex.rxjava3.internal.subscribers;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public final class BlockingLastSubscriber<T> extends BlockingBaseSubscriber<T> {
    public void onNext(T t) {
        this.value = t;
    }

    public void onError(Throwable t) {
        this.value = null;
        this.error = t;
        countDown();
    }
}
