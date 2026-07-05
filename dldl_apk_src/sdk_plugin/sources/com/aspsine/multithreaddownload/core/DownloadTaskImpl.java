package com.aspsine.multithreaddownload.core;

import android.os.Process;
import android.text.TextUtils;
import android.util.Log;
import com.aspsine.multithreaddownload.BufferedRandomAccessFile;
import com.aspsine.multithreaddownload.DownloadException;
import com.aspsine.multithreaddownload.DownloadInfo;
import com.aspsine.multithreaddownload.architecture.DownloadTask;
import com.aspsine.multithreaddownload.db.ThreadInfo;
import com.aspsine.multithreaddownload.speedlimit.SpeedLimitManager;
import com.aspsine.multithreaddownload.util.IOCloseUtils;
import com.aspsine.multithreaddownload.util.L;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URLConnection;
import java.util.Map;
import java.util.concurrent.ExecutorService;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public abstract class DownloadTaskImpl implements DownloadTask {
    private volatile int mCommend = 0;
    private ExecutorService mDBExecutorService;
    private final DownloadInfo mDownloadInfo;
    private final DownloadTask.OnDownloadListener mOnDownloadListener;
    private volatile int mStatus;
    private String mTag;
    private final ThreadInfo mThreadInfo;

    protected abstract BufferedRandomAccessFile getFile(File file, String str, long j) throws IOException;

    protected abstract Map<String, String> getHttpHeaders(ThreadInfo threadInfo);

    protected abstract int getResponseCode();

    protected abstract String getTag();

    protected abstract void insertIntoDB(ThreadInfo threadInfo);

    protected abstract void updateDB(ThreadInfo threadInfo);

    public DownloadTaskImpl(DownloadInfo downloadInfo, ThreadInfo threadInfo, ExecutorService executorService, DownloadTask.OnDownloadListener onDownloadListener) {
        this.mDownloadInfo = downloadInfo;
        this.mThreadInfo = threadInfo;
        this.mDBExecutorService = executorService;
        this.mOnDownloadListener = onDownloadListener;
        String tag = getTag();
        this.mTag = tag;
        if (TextUtils.isEmpty(tag)) {
            this.mTag = getClass().getSimpleName();
        }
    }

    @Override // com.aspsine.multithreaddownload.architecture.DownloadTask
    public void cancel() {
        this.mCommend = 107;
    }

    @Override // com.aspsine.multithreaddownload.architecture.DownloadTask
    public void pause() {
        this.mCommend = 106;
        handleDownloadException(new DownloadException(106, "Download paused!"));
    }

    @Override // com.aspsine.multithreaddownload.architecture.DownloadTask
    public boolean isDownloading() {
        return this.mStatus == 104;
    }

    @Override // com.aspsine.multithreaddownload.architecture.DownloadTask
    public boolean isComplete() {
        return this.mStatus == 105;
    }

    @Override // com.aspsine.multithreaddownload.architecture.DownloadTask
    public boolean isPaused() {
        return this.mStatus == 106;
    }

    @Override // com.aspsine.multithreaddownload.architecture.DownloadTask
    public boolean isCanceled() {
        return this.mStatus == 107;
    }

    @Override // com.aspsine.multithreaddownload.architecture.DownloadTask
    public boolean isFailed() {
        return this.mStatus == 108;
    }

    @Override // com.aspsine.multithreaddownload.architecture.DownloadTask, java.lang.Runnable
    public void run() throws Throwable {
        Process.setThreadPriority(10);
        insertIntoDB(this.mThreadInfo);
        try {
            this.mStatus = 104;
            executeDownload();
            synchronized (this.mOnDownloadListener) {
                this.mStatus = 105;
                this.mOnDownloadListener.onDownloadCompleted();
            }
        } catch (DownloadException e) {
            handleDownloadException(e);
        }
    }

    private void handleDownloadException(DownloadException downloadException) {
        switch (downloadException.getErrorCode()) {
            case 106:
                synchronized (this.mOnDownloadListener) {
                    this.mStatus = 106;
                    this.mOnDownloadListener.onDownloadPaused();
                    break;
                }
                return;
            case 107:
                synchronized (this.mOnDownloadListener) {
                    this.mStatus = 107;
                    this.mOnDownloadListener.onDownloadCanceled();
                    break;
                }
                return;
            case 108:
                synchronized (this.mOnDownloadListener) {
                    this.mStatus = 108;
                    this.mOnDownloadListener.onDownloadFailed(downloadException);
                    break;
                }
                return;
            default:
                Log.e("Downloader", "Unknown state");
                return;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:46:0x007f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r2v3 */
    /* JADX WARN: Type inference failed for: r2v6, types: [java.net.HttpURLConnection] */
    /* JADX WARN: Type inference failed for: r2v7 */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.aspsine.multithreaddownload.core.DownloadTaskImpl] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void executeDownload() throws java.lang.Throwable {
        /*
            r7 = this;
            r0 = 108(0x6c, float:1.51E-43)
            java.net.URL r1 = new java.net.URL     // Catch: java.net.MalformedURLException -> L88
            com.aspsine.multithreaddownload.db.ThreadInfo r2 = r7.mThreadInfo     // Catch: java.net.MalformedURLException -> L88
            java.lang.String r2 = r2.getUri()     // Catch: java.net.MalformedURLException -> L88
            r1.<init>(r2)     // Catch: java.net.MalformedURLException -> L88
            r2 = 0
            java.net.URLConnection r1 = r1.openConnection()     // Catch: java.lang.Throwable -> L61 java.io.IOException -> L63 java.net.ProtocolException -> L6f
            java.net.HttpURLConnection r1 = (java.net.HttpURLConnection) r1     // Catch: java.lang.Throwable -> L61 java.io.IOException -> L63 java.net.ProtocolException -> L6f
            r2 = 10000(0x2710, float:1.4013E-41)
            r1.setConnectTimeout(r2)     // Catch: java.io.IOException -> L5d java.net.ProtocolException -> L5f java.lang.Throwable -> L7b
            r1.setReadTimeout(r2)     // Catch: java.io.IOException -> L5d java.net.ProtocolException -> L5f java.lang.Throwable -> L7b
            java.lang.String r2 = "GET"
            r1.setRequestMethod(r2)     // Catch: java.io.IOException -> L5d java.net.ProtocolException -> L5f java.lang.Throwable -> L7b
            r2 = 1
            r1.setDoInput(r2)     // Catch: java.io.IOException -> L5d java.net.ProtocolException -> L5f java.lang.Throwable -> L7b
            com.aspsine.multithreaddownload.db.ThreadInfo r2 = r7.mThreadInfo     // Catch: java.io.IOException -> L5d java.net.ProtocolException -> L5f java.lang.Throwable -> L7b
            java.util.Map r2 = r7.getHttpHeaders(r2)     // Catch: java.io.IOException -> L5d java.net.ProtocolException -> L5f java.lang.Throwable -> L7b
            r7.setHttpHeader(r2, r1)     // Catch: java.io.IOException -> L5d java.net.ProtocolException -> L5f java.lang.Throwable -> L7b
            int r2 = r1.getResponseCode()     // Catch: java.io.IOException -> L5d java.net.ProtocolException -> L5f java.lang.Throwable -> L7b
            int r3 = r7.getResponseCode()     // Catch: java.io.IOException -> L5d java.net.ProtocolException -> L5f java.lang.Throwable -> L7b
            if (r2 != r3) goto L46
            r7.transferData(r1)     // Catch: java.io.IOException -> L5d java.net.ProtocolException -> L5f java.lang.Throwable -> L7b
            if (r1 == 0) goto L45
            r1.disconnect()     // Catch: java.lang.Exception -> L41
            goto L45
        L41:
            r0 = move-exception
            r0.printStackTrace()
        L45:
            return
        L46:
            com.aspsine.multithreaddownload.DownloadException r3 = new com.aspsine.multithreaddownload.DownloadException     // Catch: java.io.IOException -> L5d java.net.ProtocolException -> L5f java.lang.Throwable -> L7b
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.io.IOException -> L5d java.net.ProtocolException -> L5f java.lang.Throwable -> L7b
            r4.<init>()     // Catch: java.io.IOException -> L5d java.net.ProtocolException -> L5f java.lang.Throwable -> L7b
            java.lang.String r5 = "UnSupported response code:"
            r4.append(r5)     // Catch: java.io.IOException -> L5d java.net.ProtocolException -> L5f java.lang.Throwable -> L7b
            r4.append(r2)     // Catch: java.io.IOException -> L5d java.net.ProtocolException -> L5f java.lang.Throwable -> L7b
            java.lang.String r2 = r4.toString()     // Catch: java.io.IOException -> L5d java.net.ProtocolException -> L5f java.lang.Throwable -> L7b
            r3.<init>(r0, r2)     // Catch: java.io.IOException -> L5d java.net.ProtocolException -> L5f java.lang.Throwable -> L7b
            throw r3     // Catch: java.io.IOException -> L5d java.net.ProtocolException -> L5f java.lang.Throwable -> L7b
        L5d:
            r2 = move-exception
            goto L67
        L5f:
            r2 = move-exception
            goto L73
        L61:
            r0 = move-exception
            goto L7d
        L63:
            r1 = move-exception
            r6 = r2
            r2 = r1
            r1 = r6
        L67:
            com.aspsine.multithreaddownload.DownloadException r3 = new com.aspsine.multithreaddownload.DownloadException     // Catch: java.lang.Throwable -> L7b
            java.lang.String r4 = "IO error"
            r3.<init>(r0, r4, r2)     // Catch: java.lang.Throwable -> L7b
            throw r3     // Catch: java.lang.Throwable -> L7b
        L6f:
            r1 = move-exception
            r6 = r2
            r2 = r1
            r1 = r6
        L73:
            com.aspsine.multithreaddownload.DownloadException r3 = new com.aspsine.multithreaddownload.DownloadException     // Catch: java.lang.Throwable -> L7b
            java.lang.String r4 = "Protocol error"
            r3.<init>(r0, r4, r2)     // Catch: java.lang.Throwable -> L7b
            throw r3     // Catch: java.lang.Throwable -> L7b
        L7b:
            r0 = move-exception
            r2 = r1
        L7d:
            if (r2 == 0) goto L87
            r2.disconnect()     // Catch: java.lang.Exception -> L83
            goto L87
        L83:
            r1 = move-exception
            r1.printStackTrace()
        L87:
            throw r0
        L88:
            r1 = move-exception
            com.aspsine.multithreaddownload.DownloadException r2 = new com.aspsine.multithreaddownload.DownloadException
            java.lang.String r3 = "Bad url."
            r2.<init>(r0, r3, r1)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.aspsine.multithreaddownload.core.DownloadTaskImpl.executeDownload():void");
    }

    private void setHttpHeader(Map<String, String> map, URLConnection uRLConnection) {
        if (map != null) {
            for (String str : map.keySet()) {
                uRLConnection.setRequestProperty(str, map.get(str));
            }
        }
    }

    private void transferData(HttpURLConnection httpURLConnection) throws Throwable {
        Closeable closeable;
        InputStream inputStream;
        InputStream inputStream2 = null;
        try {
            try {
                inputStream = httpURLConnection.getInputStream();
            } catch (Throwable th) {
                th = th;
                closeable = null;
            }
            try {
                try {
                    BufferedRandomAccessFile file = getFile(this.mDownloadInfo.getDir(), this.mDownloadInfo.getName(), this.mThreadInfo.getStart() + this.mThreadInfo.getFinished());
                    transferData(inputStream, file);
                    try {
                        IOCloseUtils.close(inputStream);
                        IOCloseUtils.close(file);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } catch (IOException e2) {
                    throw new DownloadException(108, "File error", e2);
                }
            } catch (Throwable th2) {
                th = th2;
                inputStream2 = inputStream;
                closeable = null;
                try {
                    IOCloseUtils.close(inputStream2);
                    IOCloseUtils.close(closeable);
                } catch (IOException e3) {
                    e3.printStackTrace();
                }
                throw th;
            }
        } catch (IOException e4) {
            throw new DownloadException(108, "http get inputStream error", e4);
        }
    }

    private void transferData(InputStream inputStream, BufferedRandomAccessFile bufferedRandomAccessFile) throws DownloadException {
        byte[] bArr = new byte[8192];
        while (true) {
            checkPausedOrCanceled();
            try {
                int i = inputStream.read(bArr);
                if (i == -1) {
                    try {
                        try {
                            bufferedRandomAccessFile.flush();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } catch (Exception e2) {
                        throw new DownloadException(108, "Fail write buffer to file", e2);
                    }
                } else {
                    bufferedRandomAccessFile.write(bArr, 0, i);
                }
                if (bufferedRandomAccessFile.isLastWriteDisk()) {
                    this.mThreadInfo.setFinished(this.mThreadInfo.getFinished() + bufferedRandomAccessFile.getLastWriteDiskLength());
                    this.mDBExecutorService.execute(new Runnable() { // from class: com.aspsine.multithreaddownload.core.DownloadTaskImpl.1
                        @Override // java.lang.Runnable
                        public void run() {
                            try {
                                DownloadTaskImpl.this.updateDB(DownloadTaskImpl.this.mThreadInfo);
                            } catch (Exception e3) {
                                e3.printStackTrace();
                                L.e("Downloader", "upate database download break point error");
                            }
                        }
                    });
                }
                if (this.mCommend != 106) {
                    synchronized (this.mOnDownloadListener) {
                        this.mDownloadInfo.setFinished(this.mDownloadInfo.getFinished() + ((long) i));
                        this.mOnDownloadListener.onDownloadProgress(this.mDownloadInfo.getFinished(), this.mDownloadInfo.getLength());
                    }
                }
                if (i == -1) {
                    return;
                }
                long sleepTime = SpeedLimitManager.getInstance().getSleepTime(this.mDownloadInfo.getUri(), i);
                if (SpeedLimitManager.getInstance().isLimit(sleepTime)) {
                    L.i("Downloader", "over speed, thread sleep time:" + sleepTime);
                    try {
                        Thread.sleep(sleepTime);
                    } catch (InterruptedException e3) {
                        e3.printStackTrace();
                    }
                }
            } catch (IOException e4) {
                throw new DownloadException(108, "Http inputStream read error", e4);
            }
        }
    }

    private void checkPausedOrCanceled() throws DownloadException {
        if (this.mCommend == 107) {
            Log.i("Downloader", "checkPausedOrCanceled mCommend:" + this.mCommend);
            throw new DownloadException(107, "Download canceled!");
        }
        if (this.mCommend == 106) {
            throw new DownloadException(-1, "Download paused!");
        }
    }
}
