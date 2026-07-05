package io.reactivex;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public interface MaybeTransformer<Upstream, Downstream> {
    MaybeSource<Downstream> apply(Maybe<Upstream> maybe);
}
