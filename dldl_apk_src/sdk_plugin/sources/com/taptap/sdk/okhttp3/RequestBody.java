package com.taptap.sdk.okhttp3;

import com.taptap.sdk.okhttp3.internal.Util;
import com.taptap.sdk.okio.BufferedSink;
import com.taptap.sdk.okio.ByteString;
import com.taptap.sdk.okio.Okio;
import com.taptap.sdk.okio.Source;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public abstract class RequestBody {
    public long contentLength() throws IOException {
        return -1L;
    }

    @Nullable
    public abstract MediaType contentType();

    public abstract void writeTo(BufferedSink bufferedSink) throws IOException;

    public static RequestBody create(@Nullable MediaType mediaType, String str) {
        Charset charset = Util.UTF_8;
        if (mediaType != null && (charset = mediaType.charset()) == null) {
            charset = Util.UTF_8;
            mediaType = MediaType.parse(mediaType + "; charset=utf-8");
        }
        return create(mediaType, str.getBytes(charset));
    }

    public static RequestBody create(@Nullable final MediaType mediaType, final ByteString byteString) {
        return new RequestBody() { // from class: com.taptap.sdk.okhttp3.RequestBody.1
            @Override // com.taptap.sdk.okhttp3.RequestBody
            @Nullable
            public MediaType contentType() {
                return mediaType;
            }

            @Override // com.taptap.sdk.okhttp3.RequestBody
            public long contentLength() throws IOException {
                return byteString.size();
            }

            @Override // com.taptap.sdk.okhttp3.RequestBody
            public void writeTo(BufferedSink bufferedSink) throws IOException {
                bufferedSink.write(byteString);
            }
        };
    }

    public static RequestBody create(@Nullable MediaType mediaType, byte[] bArr) {
        return create(mediaType, bArr, 0, bArr.length);
    }

    public static RequestBody create(@Nullable final MediaType mediaType, final byte[] bArr, final int i, final int i2) {
        if (bArr == null) {
            throw new NullPointerException("content == null");
        }
        Util.checkOffsetAndCount(bArr.length, i, i2);
        return new RequestBody() { // from class: com.taptap.sdk.okhttp3.RequestBody.2
            @Override // com.taptap.sdk.okhttp3.RequestBody
            @Nullable
            public MediaType contentType() {
                return mediaType;
            }

            @Override // com.taptap.sdk.okhttp3.RequestBody
            public long contentLength() {
                return i2;
            }

            @Override // com.taptap.sdk.okhttp3.RequestBody
            public void writeTo(BufferedSink bufferedSink) throws IOException {
                bufferedSink.write(bArr, i, i2);
            }
        };
    }

    public static RequestBody create(@Nullable final MediaType mediaType, final File file) {
        if (file == null) {
            throw new NullPointerException("file == null");
        }
        return new RequestBody() { // from class: com.taptap.sdk.okhttp3.RequestBody.3
            @Override // com.taptap.sdk.okhttp3.RequestBody
            @Nullable
            public MediaType contentType() {
                return mediaType;
            }

            @Override // com.taptap.sdk.okhttp3.RequestBody
            public long contentLength() {
                return file.length();
            }

            @Override // com.taptap.sdk.okhttp3.RequestBody
            public void writeTo(BufferedSink bufferedSink) throws IOException {
                Source source = null;
                try {
                    source = Okio.source(file);
                    bufferedSink.writeAll(source);
                } finally {
                    Util.closeQuietly(source);
                }
            }
        };
    }
}
