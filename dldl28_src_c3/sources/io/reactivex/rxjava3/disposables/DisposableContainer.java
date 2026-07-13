package io.reactivex.rxjava3.disposables;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public interface DisposableContainer {
    boolean add(Disposable d);

    boolean delete(Disposable d);

    boolean remove(Disposable d);
}
