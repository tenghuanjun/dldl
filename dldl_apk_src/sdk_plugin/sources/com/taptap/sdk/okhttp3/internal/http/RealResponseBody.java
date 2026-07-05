package com.taptap.sdk.okhttp3.internal.http;

import com.taptap.sdk.okhttp3.MediaType;
import com.taptap.sdk.okhttp3.ResponseBody;
import com.taptap.sdk.okio.BufferedSource;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public final class RealResponseBody extends ResponseBody {
    private final long contentLength;

    @Nullable
    private final String contentTypeString;
    private final BufferedSource source;

    public RealResponseBody(@Nullable String str, long j, BufferedSource bufferedSource) {
        this.contentTypeString = str;
        this.contentLength = j;
        this.source = bufferedSource;
    }

    @Override // com.taptap.sdk.okhttp3.ResponseBody
    public MediaType contentType() {
        String str = this.contentTypeString;
        if (str != null) {
            return MediaType.parse(str);
        }
        return null;
    }

    @Override // com.taptap.sdk.okhttp3.ResponseBody
    public long contentLength() {
        return this.contentLength;
    }

    @Override // com.taptap.sdk.okhttp3.ResponseBody
    public BufferedSource source() {
        return this.source;
    }
}
