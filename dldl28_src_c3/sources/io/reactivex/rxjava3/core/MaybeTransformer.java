package io.reactivex.rxjava3.core;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
@FunctionalInterface
public interface MaybeTransformer<Upstream, Downstream> {
    MaybeSource<Downstream> apply(Maybe<Upstream> upstream);
}
