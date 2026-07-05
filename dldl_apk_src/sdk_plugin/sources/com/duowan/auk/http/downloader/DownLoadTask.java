package com.duowan.auk.http.downloader;

import android.os.AsyncTask;
import com.duowan.auk.http.downloader.DownLoader;
import java.io.File;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class DownLoadTask extends AsyncTask<String, Integer, String> {
    private static final int BufferSize = 4096;
    private static final String TAG = "DownLoadTask";
    private static final int TIMEOUT_CONNECT = 60000;
    private static final int TIMEOUT_READ = 60000;
    private int mConnectTimeOut;
    private File mFile;
    private DownLoader.DownLoaderListener mListener;
    private int mReadTimeOut;

    public DownLoadTask(File file, DownLoader.DownLoaderListener downLoaderListener) {
        this(file, downLoaderListener, 60000, 60000);
    }

    public DownLoadTask(File file, DownLoader.DownLoaderListener downLoaderListener, int i, int i2) {
        this.mListener = downLoaderListener;
        this.mFile = file;
        this.mReadTimeOut = i == 0 ? 60000 : i;
        this.mConnectTimeOut = i2 == 0 ? 60000 : i2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x008d, code lost:
    
        throw new java.lang.Exception("down load canceled");
     */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0147  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x010d A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:96:0x012a A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @Override // android.os.AsyncTask
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.String doInBackground(java.lang.String... r15) {
        /*
            Method dump skipped, instruction units count: 396
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.duowan.auk.http.downloader.DownLoadTask.doInBackground(java.lang.String[]):java.lang.String");
    }
}
