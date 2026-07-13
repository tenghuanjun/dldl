package com.danikula.videocache;

import android.text.TextUtils;
import com.danikula.videocache.file.FileCache;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: loaded from: classes3.dex */
class HttpProxyCache extends ProxyCache {
    private static final float NO_CACHE_BARRIER = 0.2f;
    private final FileCache cache;
    private CacheListener listener;
    private final HttpUrlSource source;

    public HttpProxyCache(HttpUrlSource httpUrlSource, FileCache fileCache) {
        super(httpUrlSource, fileCache);
        this.cache = fileCache;
        this.source = httpUrlSource;
    }

    public void registerCacheListener(CacheListener cacheListener) {
        this.listener = cacheListener;
    }

    public void processRequest(GetRequest getRequest, Socket socket) throws IOException, ProxyCacheException {
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(socket.getOutputStream());
        bufferedOutputStream.write(newResponseHeaders(getRequest).getBytes(StandardCharsets.UTF_8));
        long j = getRequest.rangeOffset;
        if (isUseCache(getRequest)) {
            responseWithCache(bufferedOutputStream, j);
        } else {
            responseWithoutCache(bufferedOutputStream, j);
        }
    }

    private boolean isUseCache(GetRequest getRequest) throws ProxyCacheException {
        long length = this.source.length();
        return (((length > 0L ? 1 : (length == 0L ? 0 : -1)) > 0) && getRequest.partial && ((float) getRequest.rangeOffset) > ((float) this.cache.available()) + (((float) length) * 0.2f)) ? false : true;
    }

    private String newResponseHeaders(GetRequest getRequest) throws IOException, ProxyCacheException {
        String str;
        String mime = this.source.getMime();
        boolean zIsEmpty = TextUtils.isEmpty(mime);
        long jAvailable = this.cache.isCompleted() ? this.cache.available() : this.source.length();
        boolean z = jAvailable >= 0;
        long j = getRequest.partial ? jAvailable - getRequest.rangeOffset : jAvailable;
        boolean z2 = z && getRequest.partial;
        StringBuilder sb = new StringBuilder();
        sb.append(getRequest.partial ? "HTTP/1.1 206 PARTIAL CONTENT\n" : "HTTP/1.1 200 OK\n");
        sb.append("Accept-Ranges: bytes\n");
        String str2 = "";
        sb.append(z ? format("Content-Length: %d\n", Long.valueOf(j)) : "");
        if (!z2) {
            str = "";
        } else {
            str = format("Content-Range: bytes %d-%d/%d\n", Long.valueOf(getRequest.rangeOffset), Long.valueOf(jAvailable - 1), Long.valueOf(jAvailable));
        }
        sb.append(str);
        if (!zIsEmpty) {
            str2 = format("Content-Type: %s\n", mime);
        }
        sb.append(str2);
        sb.append(StringUtils.LF);
        return sb.toString();
    }

    private void responseWithCache(OutputStream outputStream, long j) throws IOException, ProxyCacheException {
        byte[] bArr = new byte[8192];
        while (true) {
            int i = read(bArr, j, 8192);
            if (i != -1) {
                outputStream.write(bArr, 0, i);
                j += (long) i;
            } else {
                outputStream.flush();
                return;
            }
        }
    }

    private void responseWithoutCache(OutputStream outputStream, long j) throws IOException, ProxyCacheException {
        HttpUrlSource httpUrlSource = new HttpUrlSource(this.source);
        try {
            httpUrlSource.open((int) j);
            byte[] bArr = new byte[8192];
            while (true) {
                int i = httpUrlSource.read(bArr);
                if (i != -1) {
                    outputStream.write(bArr, 0, i);
                } else {
                    outputStream.flush();
                    return;
                }
            }
        } finally {
            httpUrlSource.close();
        }
    }

    private String format(String str, Object... objArr) {
        return String.format(Locale.US, str, objArr);
    }

    @Override // com.danikula.videocache.ProxyCache
    protected void onCachePercentsAvailableChanged(int i) {
        CacheListener cacheListener = this.listener;
        if (cacheListener != null) {
            cacheListener.onCacheAvailable(this.cache.file, this.source.getUrl(), i);
        }
    }
}
