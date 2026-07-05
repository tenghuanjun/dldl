package io.reactivex.internal.subscribers;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public final class BlockingLastSubscriber<T> extends BlockingBaseSubscriber<T> {
    @Override // org.reactivestreams.Subscriber
    public void onNext(T t) {
        this.value = t;
    }

    @Override // org.reactivestreams.Subscriber
    public void onError(Throwable th) {
        this.value = null;
        this.error = th;
        countDown();
    }
}
