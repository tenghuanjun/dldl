package io.reactivex.rxjava3.disposables;

/* JADX INFO: loaded from: classes3.dex */
public interface DisposableContainer {
    boolean add(Disposable d);

    boolean delete(Disposable d);

    boolean remove(Disposable d);
}
