package com.ss.android.socialbase.downloader.reader;

import com.ss.android.socialbase.downloader.exception.BaseException;
import com.ss.android.socialbase.downloader.segment.Buffer;
import java.io.IOException;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes.dex */
public interface IStreamReader {
    void close();

    Buffer read() throws InterruptedException, IOException, BaseException;

    void recycle(Buffer buffer);
}
