package com.huya.mtp.multithreaddownload.core;

import android.os.Process;
import android.text.TextUtils;
import android.util.Log;
import com.huya.mtp.multithreaddownload.BufferedRandomAccessFile;
import com.huya.mtp.multithreaddownload.DownloadException;
import com.huya.mtp.multithreaddownload.DownloadInfo;
import com.huya.mtp.multithreaddownload.architecture.DownloadTask;
import com.huya.mtp.multithreaddownload.db.ThreadInfo;
import com.huya.mtp.multithreaddownload.speedlimit.SpeedLimitManager;
import com.huya.mtp.multithreaddownload.util.IOCloseUtils;
import com.huya.mtp.multithreaddownload.util.L;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import javax.net.ssl.X509TrustManager;
import master.flame.danmaku.danmaku.parser.IDataSource;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
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

    @Override // com.huya.mtp.multithreaddownload.architecture.DownloadTask
    public void cancel() {
        this.mCommend = 107;
    }

    @Override // com.huya.mtp.multithreaddownload.architecture.DownloadTask
    public void pause() {
        this.mCommend = 106;
        handleDownloadException(new DownloadException(106, "Download paused!"));
    }

    @Override // com.huya.mtp.multithreaddownload.architecture.DownloadTask
    public boolean isDownloading() {
        return this.mStatus == 104;
    }

    @Override // com.huya.mtp.multithreaddownload.architecture.DownloadTask
    public boolean isComplete() {
        return this.mStatus == 105;
    }

    @Override // com.huya.mtp.multithreaddownload.architecture.DownloadTask
    public boolean isPaused() {
        return this.mStatus == 106;
    }

    @Override // com.huya.mtp.multithreaddownload.architecture.DownloadTask
    public boolean isCanceled() {
        return this.mStatus == 107;
    }

    @Override // com.huya.mtp.multithreaddownload.architecture.DownloadTask
    public boolean isFailed() {
        return this.mStatus == 108;
    }

    @Override // com.huya.mtp.multithreaddownload.architecture.DownloadTask, java.lang.Runnable
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

    private void executeDownload() throws Throwable {
        HttpURLConnection httpURLConnection;
        try {
            final URL url = new URL(this.mThreadInfo.getUri());
            HttpURLConnection httpURLConnection2 = null;
            try {
                try {
                    if (!TextUtils.isEmpty(this.mThreadInfo.getUri()) && this.mThreadInfo.getUri().startsWith(IDataSource.SCHEME_HTTPS_TAG)) {
                        HttpURLConnection httpURLConnection3 = (HttpsURLConnection) url.openConnection();
                        try {
                            ((HttpsURLConnection) httpURLConnection3).setSSLSocketFactory(new SSLSocketFactoryCompat(new X509TrustManager() { // from class: com.huya.mtp.multithreaddownload.core.DownloadTaskImpl.1
                                @Override // javax.net.ssl.X509TrustManager
                                public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
                                }

                                @Override // javax.net.ssl.X509TrustManager
                                public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
                                }

                                @Override // javax.net.ssl.X509TrustManager
                                public X509Certificate[] getAcceptedIssuers() {
                                    return new X509Certificate[0];
                                }
                            }));
                            ((HttpsURLConnection) httpURLConnection3).setHostnameVerifier(new HostnameVerifier() { // from class: com.huya.mtp.multithreaddownload.core.DownloadTaskImpl.2
                                @Override // javax.net.ssl.HostnameVerifier
                                public boolean verify(String str, SSLSession sSLSession) {
                                    if (str.equals(url.getHost())) {
                                        return true;
                                    }
                                    return HttpsURLConnection.getDefaultHostnameVerifier().verify(str, sSLSession);
                                }
                            });
                            httpURLConnection = httpURLConnection3;
                        } catch (ProtocolException e) {
                            e = e;
                            throw new DownloadException(108, "Protocol error", e);
                        } catch (IOException e2) {
                            e = e2;
                            throw new DownloadException(108, "executeDownload - IO error", e);
                        } catch (Throwable th) {
                            th = th;
                            httpURLConnection2 = httpURLConnection3;
                            if (httpURLConnection2 != null) {
                                try {
                                    httpURLConnection2.disconnect();
                                } catch (Exception e3) {
                                    e3.printStackTrace();
                                }
                            }
                            throw th;
                        }
                    } else {
                        httpURLConnection = (HttpURLConnection) url.openConnection();
                    }
                    httpURLConnection.setConnectTimeout(10000);
                    httpURLConnection.setReadTimeout(10000);
                    httpURLConnection.setRequestMethod("GET");
                    httpURLConnection.setDoInput(true);
                    setHttpHeader(getHttpHeaders(this.mThreadInfo), httpURLConnection);
                    int responseCode = httpURLConnection.getResponseCode();
                    if (responseCode == getResponseCode()) {
                        transferData(httpURLConnection);
                        if (httpURLConnection != null) {
                            try {
                                httpURLConnection.disconnect();
                                return;
                            } catch (Exception e4) {
                                e4.printStackTrace();
                                return;
                            }
                        }
                        return;
                    }
                    throw new DownloadException(108, "UnSupported response code:" + responseCode);
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (ProtocolException e5) {
                e = e5;
            } catch (IOException e6) {
                e = e6;
            }
        } catch (MalformedURLException e7) {
            throw new DownloadException(108, "Bad url.", e7);
        }
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
                    this.mDBExecutorService.execute(new Runnable() { // from class: com.huya.mtp.multithreaddownload.core.DownloadTaskImpl.3
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
