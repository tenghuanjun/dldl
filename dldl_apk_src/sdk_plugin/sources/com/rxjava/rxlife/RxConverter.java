package com.rxjava.rxlife;

import io.reactivex.CompletableConverter;
import io.reactivex.FlowableConverter;
import io.reactivex.MaybeConverter;
import io.reactivex.ObservableConverter;
import io.reactivex.SingleConverter;
import io.reactivex.parallel.ParallelFlowableConverter;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public interface RxConverter<T> extends ObservableConverter<T, ObservableLife<T>>, FlowableConverter<T, FlowableLife<T>>, ParallelFlowableConverter<T, ParallelFlowableLife<T>>, MaybeConverter<T, MaybeLife<T>>, SingleConverter<T, SingleLife<T>>, CompletableConverter<CompletableLife> {
}
