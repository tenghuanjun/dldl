package io.reactivex;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public interface SingleOperator<Downstream, Upstream> {
    SingleObserver<? super Upstream> apply(SingleObserver<? super Downstream> singleObserver) throws Exception;
}
