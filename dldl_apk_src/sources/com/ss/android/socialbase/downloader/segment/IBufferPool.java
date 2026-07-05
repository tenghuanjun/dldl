package com.ss.android.socialbase.downloader.segment;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes.dex */
public interface IBufferPool {
    Buffer obtain() throws InterruptedException, StreamClosedException;

    void recycle(Buffer buffer);
}
