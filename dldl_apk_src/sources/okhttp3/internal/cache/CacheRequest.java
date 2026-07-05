package okhttp3.internal.cache;

import java.io.IOException;
import okio.Sink;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes.dex */
public interface CacheRequest {
    void abort();

    Sink body() throws IOException;
}
