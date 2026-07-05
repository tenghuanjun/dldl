package io.reactivex.internal.operators.flowable;

import org.reactivestreams.Publisher;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public interface FlowablePublishClassic<T> {
    int publishBufferSize();

    Publisher<T> publishSource();
}
