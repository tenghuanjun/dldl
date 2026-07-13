package io.reactivex.rxjava3.flowables;

import io.reactivex.rxjava3.core.Flowable;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public abstract class GroupedFlowable<K, T> extends Flowable<T> {
    final K key;

    protected GroupedFlowable(K key) {
        this.key = key;
    }

    public K getKey() {
        return this.key;
    }
}
