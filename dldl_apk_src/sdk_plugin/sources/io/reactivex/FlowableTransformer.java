package io.reactivex;

import org.reactivestreams.Publisher;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public interface FlowableTransformer<Upstream, Downstream> {
    Publisher<Downstream> apply(Flowable<Upstream> flowable);
}
