package com.taptap.sdk.base.network.transform;

import java.io.IOException;
import java.io.InputStream;
import kotlin.Metadata;

/* JADX INFO: compiled from: BizTransformer.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003H&¨\u0006\u0005"}, d2 = {"Lcom/taptap/sdk/base/network/transform/BizTransformer;", "", "transform", "Ljava/io/InputStream;", "origin", "tap-base_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public interface BizTransformer {
    InputStream transform(InputStream origin) throws IOException;
}
