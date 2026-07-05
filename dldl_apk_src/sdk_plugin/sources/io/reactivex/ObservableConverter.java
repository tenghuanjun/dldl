package io.reactivex;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public interface ObservableConverter<T, R> {
    R apply(Observable<T> observable);
}
