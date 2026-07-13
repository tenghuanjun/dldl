package io.reactivex.rxjava3.internal.fuseable;

import io.reactivex.rxjava3.functions.Supplier;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
@FunctionalInterface
public interface ScalarSupplier<T> extends Supplier<T> {
    @Override // io.reactivex.rxjava3.functions.Supplier
    T get();
}
