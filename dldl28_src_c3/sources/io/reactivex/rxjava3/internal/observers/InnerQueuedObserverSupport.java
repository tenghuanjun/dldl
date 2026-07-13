package io.reactivex.rxjava3.internal.observers;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public interface InnerQueuedObserverSupport<T> {
    void drain();

    void innerComplete(InnerQueuedObserver<T> inner);

    void innerError(InnerQueuedObserver<T> inner, Throwable e);

    void innerNext(InnerQueuedObserver<T> inner, T value);
}
