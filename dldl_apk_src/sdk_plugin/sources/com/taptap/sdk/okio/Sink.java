package com.taptap.sdk.okio;

import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public interface Sink extends Closeable, Flushable {
    @Override // java.io.Closeable, java.lang.AutoCloseable
    void close() throws IOException;

    void flush() throws IOException;

    Timeout timeout();

    void write(Buffer buffer, long j) throws IOException;
}
