package io.reactivex;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public interface ObservableOperator<Downstream, Upstream> {
    Observer<? super Upstream> apply(Observer<? super Downstream> observer) throws Exception;
}
