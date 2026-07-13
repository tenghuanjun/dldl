package com.danikula.videocache;

import android.text.TextUtils;
import com.danikula.videocache.headers.EmptyHeadersInjector;
import com.danikula.videocache.headers.HeaderInjector;
import com.danikula.videocache.sourcestorage.SourceInfoStorage;
import com.danikula.videocache.sourcestorage.SourceInfoStorageFactory;
import com.lzy.okgo.model.HttpHeaders;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Map;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;

/* JADX INFO: loaded from: classes3.dex */
public class HttpUrlSource implements Source {
    private static final int MAX_REDIRECTS = 5;
    private HttpURLConnection connection;
    private final HeaderInjector headerInjector;
    private InputStream inputStream;
    private SourceInfo sourceInfo;
    private final SourceInfoStorage sourceInfoStorage;
    private final TrustManager[] trustAllCerts;
    private final HostnameVerifier v;

    public HttpUrlSource(String str, HostnameVerifier hostnameVerifier, TrustManager[] trustManagerArr) {
        this(str, SourceInfoStorageFactory.newEmptySourceInfoStorage(), hostnameVerifier, trustManagerArr);
    }

    public HttpUrlSource(String str, SourceInfoStorage sourceInfoStorage, HostnameVerifier hostnameVerifier, TrustManager[] trustManagerArr) {
        this(str, sourceInfoStorage, new EmptyHeadersInjector(), hostnameVerifier, trustManagerArr);
    }

    public HttpUrlSource(String str, SourceInfoStorage sourceInfoStorage, HeaderInjector headerInjector, HostnameVerifier hostnameVerifier, TrustManager[] trustManagerArr) {
        this.sourceInfoStorage = (SourceInfoStorage) Preconditions.checkNotNull(sourceInfoStorage);
        this.headerInjector = (HeaderInjector) Preconditions.checkNotNull(headerInjector);
        this.v = hostnameVerifier;
        this.trustAllCerts = trustManagerArr;
        SourceInfo sourceInfo = sourceInfoStorage.get(str);
        this.sourceInfo = sourceInfo == null ? new SourceInfo(str, -2147483648L, ProxyCacheUtils.getSupposablyMime(str)) : sourceInfo;
    }

    public HttpUrlSource(HttpUrlSource httpUrlSource) {
        this.sourceInfo = httpUrlSource.sourceInfo;
        this.sourceInfoStorage = httpUrlSource.sourceInfoStorage;
        this.headerInjector = httpUrlSource.headerInjector;
        this.trustAllCerts = httpUrlSource.trustAllCerts;
        this.v = httpUrlSource.v;
    }

    @Override // com.danikula.videocache.Source
    public synchronized long length() throws ProxyCacheException {
        if (this.sourceInfo.length == -2147483648L) {
            fetchContentInfo();
        }
        return this.sourceInfo.length;
    }

    @Override // com.danikula.videocache.Source
    public void open(long j) throws ProxyCacheException {
        try {
            HttpURLConnection httpURLConnectionOpenConnection = openConnection(j, -1);
            this.connection = httpURLConnectionOpenConnection;
            String contentType = httpURLConnectionOpenConnection.getContentType();
            this.inputStream = new BufferedInputStream(this.connection.getInputStream(), 8192);
            HttpURLConnection httpURLConnection = this.connection;
            SourceInfo sourceInfo = new SourceInfo(this.sourceInfo.url, readSourceAvailableBytes(httpURLConnection, j, httpURLConnection.getResponseCode()), contentType);
            this.sourceInfo = sourceInfo;
            this.sourceInfoStorage.put(sourceInfo.url, this.sourceInfo);
        } catch (IOException e) {
            throw new ProxyCacheException("Error opening connection for " + this.sourceInfo.url + " with offset " + j, e);
        }
    }

    private long readSourceAvailableBytes(HttpURLConnection httpURLConnection, long j, int i) throws IOException {
        long contentLength = getContentLength(httpURLConnection);
        return i == 200 ? contentLength : i == 206 ? contentLength + j : this.sourceInfo.length;
    }

    private long getContentLength(HttpURLConnection httpURLConnection) {
        String headerField = httpURLConnection.getHeaderField(HttpHeaders.HEAD_KEY_CONTENT_LENGTH);
        if (headerField == null) {
            return -1L;
        }
        return Long.parseLong(headerField);
    }

    @Override // com.danikula.videocache.Source
    public void close() throws ProxyCacheException {
        HttpURLConnection httpURLConnection = this.connection;
        if (httpURLConnection != null) {
            try {
                httpURLConnection.disconnect();
            } catch (ArrayIndexOutOfBoundsException e) {
                HttpProxyCacheDebuger.printfError("Error closing connection correctly. Should happen only on Android L. If anybody know how to fix it, please visit https://github.com/danikula/AndroidVideoCache/issues/88. Until good solution is not know, just ignore this issue :(", e);
            } catch (IllegalArgumentException e2) {
                e = e2;
                throw new RuntimeException("Wait... but why? WTF!? Really shouldn't happen any more after fixing https://github.com/danikula/AndroidVideoCache/issues/43. If you read it on your device log, please, notify me danikula@gmail.com or create issue here https://github.com/danikula/AndroidVideoCache/issues.", e);
            } catch (NullPointerException e3) {
                e = e3;
                throw new RuntimeException("Wait... but why? WTF!? Really shouldn't happen any more after fixing https://github.com/danikula/AndroidVideoCache/issues/43. If you read it on your device log, please, notify me danikula@gmail.com or create issue here https://github.com/danikula/AndroidVideoCache/issues.", e);
            }
        }
    }

    @Override // com.danikula.videocache.Source
    public int read(byte[] bArr) throws ProxyCacheException {
        InputStream inputStream = this.inputStream;
        if (inputStream == null) {
            throw new ProxyCacheException("Error reading data from " + this.sourceInfo.url + ": connection is absent!");
        }
        try {
            return inputStream.read(bArr, 0, bArr.length);
        } catch (InterruptedIOException e) {
            throw new InterruptedProxyCacheException("Reading source " + this.sourceInfo.url + " is interrupted", e);
        } catch (IOException e2) {
            throw new ProxyCacheException("Error reading data from " + this.sourceInfo.url, e2);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:23:0x005b  */
    /* JADX WARN: Type inference failed for: r1v0 */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v11 */
    /* JADX WARN: Type inference failed for: r1v12 */
    /* JADX WARN: Type inference failed for: r1v13 */
    /* JADX WARN: Type inference failed for: r1v3, types: [java.net.HttpURLConnection] */
    /* JADX WARN: Type inference failed for: r1v4 */
    /* JADX WARN: Type inference failed for: r1v6 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void fetchContentInfo() throws java.lang.Throwable {
        /*
            r8 = this;
            java.lang.String r0 = "Error fetching info from "
            r1 = 0
            r3 = 10000(0x2710, float:1.4013E-41)
            r4 = 0
            java.net.HttpURLConnection r1 = r8.openConnection(r1, r3)     // Catch: java.lang.Throwable -> L35 java.io.IOException -> L38
            long r2 = r8.getContentLength(r1)     // Catch: java.lang.Throwable -> L31 java.io.IOException -> L33
            java.lang.String r5 = r1.getContentType()     // Catch: java.lang.Throwable -> L31 java.io.IOException -> L33
            java.io.InputStream r4 = r1.getInputStream()     // Catch: java.lang.Throwable -> L31 java.io.IOException -> L33
            com.danikula.videocache.SourceInfo r6 = new com.danikula.videocache.SourceInfo     // Catch: java.lang.Throwable -> L31 java.io.IOException -> L33
            com.danikula.videocache.SourceInfo r7 = r8.sourceInfo     // Catch: java.lang.Throwable -> L31 java.io.IOException -> L33
            java.lang.String r7 = r7.url     // Catch: java.lang.Throwable -> L31 java.io.IOException -> L33
            r6.<init>(r7, r2, r5)     // Catch: java.lang.Throwable -> L31 java.io.IOException -> L33
            r8.sourceInfo = r6     // Catch: java.lang.Throwable -> L31 java.io.IOException -> L33
            com.danikula.videocache.sourcestorage.SourceInfoStorage r2 = r8.sourceInfoStorage     // Catch: java.lang.Throwable -> L31 java.io.IOException -> L33
            java.lang.String r3 = r6.url     // Catch: java.lang.Throwable -> L31 java.io.IOException -> L33
            com.danikula.videocache.SourceInfo r5 = r8.sourceInfo     // Catch: java.lang.Throwable -> L31 java.io.IOException -> L33
            r2.put(r3, r5)     // Catch: java.lang.Throwable -> L31 java.io.IOException -> L33
            com.danikula.videocache.ProxyCacheUtils.close(r4)
            if (r1 == 0) goto L55
            goto L52
        L31:
            r0 = move-exception
            goto L56
        L33:
            r2 = move-exception
            goto L3a
        L35:
            r0 = move-exception
            r1 = r4
            goto L56
        L38:
            r2 = move-exception
            r1 = r4
        L3a:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L31
            r3.<init>(r0)     // Catch: java.lang.Throwable -> L31
            com.danikula.videocache.SourceInfo r0 = r8.sourceInfo     // Catch: java.lang.Throwable -> L31
            java.lang.String r0 = r0.url     // Catch: java.lang.Throwable -> L31
            r3.append(r0)     // Catch: java.lang.Throwable -> L31
            java.lang.String r0 = r3.toString()     // Catch: java.lang.Throwable -> L31
            com.danikula.videocache.HttpProxyCacheDebuger.printfError(r0, r2)     // Catch: java.lang.Throwable -> L31
            com.danikula.videocache.ProxyCacheUtils.close(r4)
            if (r1 == 0) goto L55
        L52:
            r1.disconnect()
        L55:
            return
        L56:
            com.danikula.videocache.ProxyCacheUtils.close(r4)
            if (r1 == 0) goto L5e
            r1.disconnect()
        L5e:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.danikula.videocache.HttpUrlSource.fetchContentInfo():void");
    }

    private HttpURLConnection openConnection(long j, int i) throws IOException, ProxyCacheException {
        HttpURLConnection httpURLConnection;
        boolean z;
        String headerField = this.sourceInfo.url;
        int i2 = 0;
        do {
            if (headerField.startsWith("https") && this.v != null && this.trustAllCerts != null) {
                httpURLConnection = (HttpURLConnection) new URL(headerField).openConnection();
                ((HttpsURLConnection) httpURLConnection).setHostnameVerifier(this.v);
                try {
                    SSLContext sSLContext = SSLContext.getInstance("SSL");
                    sSLContext.init(null, this.trustAllCerts, new SecureRandom());
                    ((HttpsURLConnection) httpURLConnection).setSSLSocketFactory(sSLContext.getSocketFactory());
                    ((HttpsURLConnection) httpURLConnection).setHostnameVerifier(this.v);
                } catch (KeyManagementException | NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
            } else {
                httpURLConnection = (HttpURLConnection) new URL(headerField).openConnection();
            }
            injectCustomHeaders(httpURLConnection, headerField);
            if (j > 0) {
                httpURLConnection.setRequestProperty(HttpHeaders.HEAD_KEY_RANGE, "bytes=" + j + "-");
            }
            if (i > 0) {
                httpURLConnection.setConnectTimeout(i);
                httpURLConnection.setReadTimeout(i);
            }
            int responseCode = httpURLConnection.getResponseCode();
            z = responseCode == 301 || responseCode == 302 || responseCode == 303;
            if (z) {
                headerField = httpURLConnection.getHeaderField(HttpHeaders.HEAD_KEY_LOCATION);
                i2++;
                httpURLConnection.disconnect();
            }
            if (i2 > 5) {
                throw new ProxyCacheException("Too many redirects: " + i2);
            }
        } while (z);
        return httpURLConnection;
    }

    private void injectCustomHeaders(HttpURLConnection httpURLConnection, String str) {
        Map<String, String> mapAddHeaders = this.headerInjector.addHeaders(str);
        if (mapAddHeaders == null) {
            return;
        }
        HttpProxyCacheDebuger.printfError("****** injectCustomHeaders ****** :" + mapAddHeaders.size());
        for (Map.Entry<String, String> entry : mapAddHeaders.entrySet()) {
            httpURLConnection.setRequestProperty(entry.getKey(), entry.getValue());
        }
    }

    public synchronized String getMime() throws ProxyCacheException {
        if (TextUtils.isEmpty(this.sourceInfo.mime)) {
            fetchContentInfo();
        }
        return this.sourceInfo.mime;
    }

    public String getUrl() {
        return this.sourceInfo.url;
    }

    public String toString() {
        return "HttpUrlSource{sourceInfo='" + this.sourceInfo + "}";
    }
}
