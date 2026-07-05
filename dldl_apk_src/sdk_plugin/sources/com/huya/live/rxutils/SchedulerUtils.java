package com.huya.live.rxutils;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class SchedulerUtils {
    public static <T> ObservableTransformer<T, T> net() {
        return new ObservableTransformer<T, T>() { // from class: com.huya.live.rxutils.SchedulerUtils.1
            @Override // io.reactivex.ObservableTransformer
            public ObservableSource<T> apply(Observable<T> observable) {
                return observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    public static <T> ObservableTransformer<T, T> io2main() {
        return new ObservableTransformer<T, T>() { // from class: com.huya.live.rxutils.SchedulerUtils.2
            @Override // io.reactivex.ObservableTransformer
            public ObservableSource<T> apply(Observable<T> observable) {
                return observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    public static <T> ObservableTransformer<T, T> ioio() {
        return new ObservableTransformer<T, T>() { // from class: com.huya.live.rxutils.SchedulerUtils.3
            @Override // io.reactivex.ObservableTransformer
            public ObservableSource<T> apply(Observable<T> observable) {
                return observable.subscribeOn(Schedulers.io());
            }
        };
    }

    public static <T> ObservableTransformer<T, T> io() {
        return new ObservableTransformer<T, T>() { // from class: com.huya.live.rxutils.SchedulerUtils.4
            @Override // io.reactivex.ObservableTransformer
            public ObservableSource<T> apply(Observable<T> observable) {
                return observable.subscribeOn(Schedulers.io()).observeOn(Schedulers.io());
            }
        };
    }
}
