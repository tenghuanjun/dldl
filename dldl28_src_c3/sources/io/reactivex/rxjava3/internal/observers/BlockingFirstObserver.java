package io.reactivex.rxjava3.internal.observers;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public final class BlockingFirstObserver<T> extends BlockingBaseObserver<T> {
    @Override // io.reactivex.rxjava3.core.Observer
    public void onNext(T t) {
        if (this.value == null) {
            this.value = t;
            this.upstream.dispose();
            countDown();
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public void onError(Throwable t) {
        if (this.value == null) {
            this.error = t;
        }
        countDown();
    }
}
