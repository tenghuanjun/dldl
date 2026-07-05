package com.ss.android.socialbase.downloader.network;

import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes.dex */
public interface IDownloadHttpConnection extends IDownloadHeadHttpConnection {
    void end();

    InputStream getInputStream() throws IOException;
}
