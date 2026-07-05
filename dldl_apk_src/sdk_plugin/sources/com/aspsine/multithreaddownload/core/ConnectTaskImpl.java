package com.aspsine.multithreaddownload.core;

import android.os.Process;
import android.text.TextUtils;
import android.util.Log;
import com.aspsine.multithreaddownload.DownloadException;
import com.aspsine.multithreaddownload.architecture.ConnectTask;
import java.net.HttpURLConnection;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class ConnectTaskImpl implements ConnectTask {
    private final ConnectTask.OnConnectListener mOnConnectListener;
    private volatile long mStartTime;
    private volatile int mStatus;
    private String mUri;

    public ConnectTaskImpl(String str, ConnectTask.OnConnectListener onConnectListener) {
        this.mUri = str;
        this.mOnConnectListener = onConnectListener;
    }

    @Override // com.aspsine.multithreaddownload.architecture.ConnectTask
    public void cancel() {
        this.mStatus = 107;
    }

    @Override // com.aspsine.multithreaddownload.architecture.ConnectTask
    public void pause() {
        this.mStatus = 106;
    }

    @Override // com.aspsine.multithreaddownload.architecture.ConnectTask
    public boolean isConnecting() {
        return this.mStatus == 102;
    }

    @Override // com.aspsine.multithreaddownload.architecture.ConnectTask
    public boolean isConnected() {
        return this.mStatus == 103;
    }

    @Override // com.aspsine.multithreaddownload.architecture.ConnectTask
    public boolean isPaused() {
        return this.mStatus == 106;
    }

    @Override // com.aspsine.multithreaddownload.architecture.ConnectTask
    public boolean isCanceled() {
        return this.mStatus == 107;
    }

    @Override // com.aspsine.multithreaddownload.architecture.ConnectTask
    public boolean isFailed() {
        return this.mStatus == 108;
    }

    @Override // com.aspsine.multithreaddownload.architecture.ConnectTask, java.lang.Runnable
    public void run() throws Throwable {
        Process.setThreadPriority(10);
        this.mStatus = 102;
        this.mOnConnectListener.onConnecting();
        try {
            executeConnection();
        } catch (DownloadException e) {
            handleDownloadException(e);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0086  */
    /* JADX WARN: Type inference failed for: r2v2 */
    /* JADX WARN: Type inference failed for: r2v5, types: [java.net.HttpURLConnection] */
    /* JADX WARN: Type inference failed for: r2v6 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void executeConnection() throws java.lang.Throwable {
        /*
            r7 = this;
            long r0 = java.lang.System.currentTimeMillis()
            r7.mStartTime = r0
            r0 = 108(0x6c, float:1.51E-43)
            java.net.URL r1 = new java.net.URL     // Catch: java.net.MalformedURLException -> L8a
            java.lang.String r2 = r7.mUri     // Catch: java.net.MalformedURLException -> L8a
            r1.<init>(r2)     // Catch: java.net.MalformedURLException -> L8a
            r2 = 0
            java.net.URLConnection r1 = r1.openConnection()     // Catch: java.lang.Throwable -> L68 java.io.IOException -> L6a java.net.ProtocolException -> L76
            java.net.HttpURLConnection r1 = (java.net.HttpURLConnection) r1     // Catch: java.lang.Throwable -> L68 java.io.IOException -> L6a java.net.ProtocolException -> L76
            r2 = 10000(0x2710, float:1.4013E-41)
            r1.setConnectTimeout(r2)     // Catch: java.io.IOException -> L64 java.net.ProtocolException -> L66 java.lang.Throwable -> L82
            r1.setReadTimeout(r2)     // Catch: java.io.IOException -> L64 java.net.ProtocolException -> L66 java.lang.Throwable -> L82
            java.lang.String r2 = "GET"
            r1.setRequestMethod(r2)     // Catch: java.io.IOException -> L64 java.net.ProtocolException -> L66 java.lang.Throwable -> L82
            java.lang.String r2 = "Range"
            java.lang.String r3 = "bytes=0-"
            r1.setRequestProperty(r2, r3)     // Catch: java.io.IOException -> L64 java.net.ProtocolException -> L66 java.lang.Throwable -> L82
            int r2 = r1.getResponseCode()     // Catch: java.io.IOException -> L64 java.net.ProtocolException -> L66 java.lang.Throwable -> L82
            r3 = 200(0xc8, float:2.8E-43)
            if (r2 != r3) goto L37
            r2 = 0
            r7.parseResponse(r1, r2)     // Catch: java.io.IOException -> L64 java.net.ProtocolException -> L66 java.lang.Throwable -> L82
            goto L47
        L37:
            r3 = 206(0xce, float:2.89E-43)
            if (r2 != r3) goto L40
            r2 = 1
            r7.parseResponse(r1, r2)     // Catch: java.io.IOException -> L64 java.net.ProtocolException -> L66 java.lang.Throwable -> L82
            goto L47
        L40:
            r3 = 302(0x12e, float:4.23E-43)
            if (r2 != r3) goto L4d
            r7.redirect(r1)     // Catch: java.io.IOException -> L64 java.net.ProtocolException -> L66 java.lang.Throwable -> L82
        L47:
            if (r1 == 0) goto L4c
            r1.disconnect()
        L4c:
            return
        L4d:
            com.aspsine.multithreaddownload.DownloadException r3 = new com.aspsine.multithreaddownload.DownloadException     // Catch: java.io.IOException -> L64 java.net.ProtocolException -> L66 java.lang.Throwable -> L82
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.io.IOException -> L64 java.net.ProtocolException -> L66 java.lang.Throwable -> L82
            r4.<init>()     // Catch: java.io.IOException -> L64 java.net.ProtocolException -> L66 java.lang.Throwable -> L82
            java.lang.String r5 = "UnSupported response code:"
            r4.append(r5)     // Catch: java.io.IOException -> L64 java.net.ProtocolException -> L66 java.lang.Throwable -> L82
            r4.append(r2)     // Catch: java.io.IOException -> L64 java.net.ProtocolException -> L66 java.lang.Throwable -> L82
            java.lang.String r2 = r4.toString()     // Catch: java.io.IOException -> L64 java.net.ProtocolException -> L66 java.lang.Throwable -> L82
            r3.<init>(r0, r2)     // Catch: java.io.IOException -> L64 java.net.ProtocolException -> L66 java.lang.Throwable -> L82
            throw r3     // Catch: java.io.IOException -> L64 java.net.ProtocolException -> L66 java.lang.Throwable -> L82
        L64:
            r2 = move-exception
            goto L6e
        L66:
            r2 = move-exception
            goto L7a
        L68:
            r0 = move-exception
            goto L84
        L6a:
            r1 = move-exception
            r6 = r2
            r2 = r1
            r1 = r6
        L6e:
            com.aspsine.multithreaddownload.DownloadException r3 = new com.aspsine.multithreaddownload.DownloadException     // Catch: java.lang.Throwable -> L82
            java.lang.String r4 = "IO error"
            r3.<init>(r0, r4, r2)     // Catch: java.lang.Throwable -> L82
            throw r3     // Catch: java.lang.Throwable -> L82
        L76:
            r1 = move-exception
            r6 = r2
            r2 = r1
            r1 = r6
        L7a:
            com.aspsine.multithreaddownload.DownloadException r3 = new com.aspsine.multithreaddownload.DownloadException     // Catch: java.lang.Throwable -> L82
            java.lang.String r4 = "Protocol error"
            r3.<init>(r0, r4, r2)     // Catch: java.lang.Throwable -> L82
            throw r3     // Catch: java.lang.Throwable -> L82
        L82:
            r0 = move-exception
            r2 = r1
        L84:
            if (r2 == 0) goto L89
            r2.disconnect()
        L89:
            throw r0
        L8a:
            r1 = move-exception
            com.aspsine.multithreaddownload.DownloadException r2 = new com.aspsine.multithreaddownload.DownloadException
            java.lang.String r3 = "Bad url."
            r2.<init>(r0, r3, r1)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.aspsine.multithreaddownload.core.ConnectTaskImpl.executeConnection():void");
    }

    private void redirect(HttpURLConnection httpURLConnection) throws Throwable {
        String headerField = httpURLConnection.getHeaderField("Location");
        if (headerField == null || headerField.isEmpty()) {
            Log.e("Downloader", "redirect url is empty");
            throw new DownloadException(108, "redirect url is empty");
        }
        Log.d("Downloader", "redirect url:" + headerField);
        this.mUri = headerField;
        this.mOnConnectListener.onConnectRedirect(headerField);
        executeConnection();
    }

    private void parseResponse(HttpURLConnection httpURLConnection, boolean z) throws DownloadException {
        long contentLength;
        String headerField = httpURLConnection.getHeaderField("Content-Length");
        if (TextUtils.isEmpty(headerField) || headerField.equals("0") || headerField.equals("-1")) {
            contentLength = httpURLConnection.getContentLength();
        } else {
            contentLength = Long.parseLong(headerField);
        }
        long j = contentLength;
        if (j <= 0) {
            Log.i("Downloader", "contentLength:" + headerField);
            throw new DownloadException(108, "length <= 0");
        }
        checkCanceledOrPaused();
        this.mStatus = 103;
        this.mOnConnectListener.onConnected(System.currentTimeMillis() - this.mStartTime, j, z);
    }

    private void checkCanceledOrPaused() throws DownloadException {
        if (isCanceled()) {
            throw new DownloadException(107, "Download cancel!");
        }
        if (isPaused()) {
            throw new DownloadException(106, "Download paused!");
        }
    }

    private void handleDownloadException(DownloadException downloadException) {
        Log.i("Downloader", "ConnectTaskImpl handleDownloadException DownloadException getErrorCode:" + downloadException.getErrorCode());
        switch (downloadException.getErrorCode()) {
            case 106:
                synchronized (this.mOnConnectListener) {
                    this.mStatus = 106;
                    this.mOnConnectListener.onConnectPaused();
                    break;
                }
                return;
            case 107:
                synchronized (this.mOnConnectListener) {
                    this.mStatus = 107;
                    this.mOnConnectListener.onConnectCanceled();
                    break;
                }
                return;
            case 108:
                synchronized (this.mOnConnectListener) {
                    this.mStatus = 108;
                    this.mOnConnectListener.onConnectFailed(downloadException);
                    break;
                }
                return;
            default:
                throw new IllegalArgumentException("Unknown state");
        }
    }
}
