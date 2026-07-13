package io.reactivex.rxjava3.internal.subscribers;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public interface InnerQueuedSubscriberSupport<T> {
    void drain();

    void innerComplete(InnerQueuedSubscriber<T> inner);

    void innerError(InnerQueuedSubscriber<T> inner, Throwable e);

    void innerNext(InnerQueuedSubscriber<T> inner, T value);
}
