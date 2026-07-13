package io.reactivex.rxjava3.parallel;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
@FunctionalInterface
public interface ParallelFlowableConverter<T, R> {
    R apply(ParallelFlowable<T> upstream);
}
