package com.huya.mtp.multithreaddownload.core;

import android.os.Process;
import android.text.TextUtils;
import android.util.Log;
import com.huya.mtp.multithreaddownload.DownloadException;
import com.huya.mtp.multithreaddownload.architecture.ConnectTask;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import master.flame.danmaku.danmaku.parser.IDataSource;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class ConnectTaskImpl implements ConnectTask {
    private static final TrustManager[] trustAllCerts = {new X509TrustManager() { // from class: com.huya.mtp.multithreaddownload.core.ConnectTaskImpl.1
        @Override // javax.net.ssl.X509TrustManager
        public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) {
        }

        @Override // javax.net.ssl.X509TrustManager
        public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) {
        }

        @Override // javax.net.ssl.X509TrustManager
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }
    }};
    private final ConnectTask.OnConnectListener mOnConnectListener;
    private volatile long mStartTime;
    private volatile int mStatus;
    private String mUri;

    public ConnectTaskImpl(String str, ConnectTask.OnConnectListener onConnectListener) {
        this.mUri = str;
        this.mOnConnectListener = onConnectListener;
    }

    @Override // com.huya.mtp.multithreaddownload.architecture.ConnectTask
    public void cancel() {
        this.mStatus = 107;
    }

    @Override // com.huya.mtp.multithreaddownload.architecture.ConnectTask
    public void pause() {
        this.mStatus = 106;
    }

    @Override // com.huya.mtp.multithreaddownload.architecture.ConnectTask
    public boolean isConnecting() {
        return this.mStatus == 102;
    }

    @Override // com.huya.mtp.multithreaddownload.architecture.ConnectTask
    public boolean isConnected() {
        return this.mStatus == 103;
    }

    @Override // com.huya.mtp.multithreaddownload.architecture.ConnectTask
    public boolean isPaused() {
        return this.mStatus == 106;
    }

    @Override // com.huya.mtp.multithreaddownload.architecture.ConnectTask
    public boolean isCanceled() {
        return this.mStatus == 107;
    }

    @Override // com.huya.mtp.multithreaddownload.architecture.ConnectTask
    public boolean isFailed() {
        return this.mStatus == 108;
    }

    @Override // com.huya.mtp.multithreaddownload.architecture.ConnectTask, java.lang.Runnable
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

    private void executeConnection() throws Throwable {
        HttpURLConnection httpURLConnection;
        this.mStartTime = System.currentTimeMillis();
        try {
            final URL url = new URL(this.mUri);
            HttpsURLConnection httpsURLConnection = null;
            try {
                try {
                    try {
                        if (!TextUtils.isEmpty(this.mUri) && this.mUri.startsWith(IDataSource.SCHEME_HTTPS_TAG)) {
                            HttpsURLConnection httpsURLConnection2 = (HttpsURLConnection) url.openConnection();
                            try {
                                httpsURLConnection2.setSSLSocketFactory(new SSLSocketFactoryCompat(new X509TrustManager() { // from class: com.huya.mtp.multithreaddownload.core.ConnectTaskImpl.2
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
                                httpsURLConnection2.setHostnameVerifier(new HostnameVerifier() { // from class: com.huya.mtp.multithreaddownload.core.ConnectTaskImpl.3
                                    @Override // javax.net.ssl.HostnameVerifier
                                    public boolean verify(String str, SSLSession sSLSession) {
                                        if (str.equals(url.getHost())) {
                                            return true;
                                        }
                                        return HttpsURLConnection.getDefaultHostnameVerifier().verify(str, sSLSession);
                                    }
                                });
                                httpURLConnection = httpsURLConnection2;
                            } catch (IOException e) {
                                e = e;
                                throw new DownloadException(108, "executeConnection - IO error", e);
                            } catch (InterruptedException e2) {
                                e = e2;
                                throw new DownloadException(108, "executeConnection - IO error", e);
                            } catch (ProtocolException e3) {
                                e = e3;
                                throw new DownloadException(108, "Protocol error", e);
                            } catch (Throwable th) {
                                th = th;
                                httpsURLConnection = httpsURLConnection2;
                                if (httpsURLConnection != null) {
                                    httpsURLConnection.disconnect();
                                }
                                throw th;
                            }
                        } else {
                            httpURLConnection = (HttpURLConnection) url.openConnection();
                        }
                        httpURLConnection.setConnectTimeout(10000);
                        httpURLConnection.setReadTimeout(10000);
                        httpURLConnection.setRequestMethod("GET");
                        httpURLConnection.setRequestProperty("Range", "bytes=0-");
                        boolean z = false;
                        int responseCode = 0;
                        for (int i = 0; i < 3 && !z; i++) {
                            try {
                                responseCode = httpURLConnection.getResponseCode();
                                z = true;
                            } catch (IOException unused) {
                                Thread.sleep(500L);
                            }
                        }
                        if (responseCode == 200) {
                            parseResponse(httpURLConnection, false);
                        } else if (responseCode == 206) {
                            parseResponse(httpURLConnection, true);
                        } else if (responseCode == 302) {
                            redirect(httpURLConnection);
                        } else {
                            throw new DownloadException(108, "UnSupported response code:" + responseCode);
                        }
                        if (httpURLConnection != null) {
                            httpURLConnection.disconnect();
                        }
                    } catch (Throwable th2) {
                        th = th2;
                    }
                } catch (IOException e4) {
                    e = e4;
                }
            } catch (InterruptedException e5) {
                e = e5;
            } catch (ProtocolException e6) {
                e = e6;
            }
        } catch (MalformedURLException e7) {
            throw new DownloadException(108, "Bad url.", e7);
        }
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
