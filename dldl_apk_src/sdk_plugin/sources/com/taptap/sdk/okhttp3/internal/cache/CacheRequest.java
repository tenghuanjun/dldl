package com.taptap.sdk.okhttp3.internal.cache;

import com.taptap.sdk.okio.Sink;
import java.io.IOException;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public interface CacheRequest {
    void abort();

    Sink body() throws IOException;
}
