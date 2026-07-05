package com.ss.android.socialbase.downloader.network.connectionpool;

import com.ss.android.socialbase.downloader.model.HttpHeader;
import com.ss.android.socialbase.downloader.utils.DownloadUtils;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes.dex */
public class DownloadConnectionPool {
    public static final int MAX_HOLD_CONNECTION = 3;
    private static final String TAG = "DownloadConnectionPool";
    private final Map<String, FakeDownloadHttpConnection> mCachedDownloadConnections;
    private final Map<String, FakeDownloadHeadHttpConnection> mCachedHeadConnections;
    protected int maxCacheSize;

    void putCachedHeadConnections(String str, FakeDownloadHeadHttpConnection fakeDownloadHeadHttpConnection) {
        synchronized (this.mCachedHeadConnections) {
            this.mCachedHeadConnections.put(str, fakeDownloadHeadHttpConnection);
        }
    }

    void setMaxCachedDownloadConnectionSize(int i) {
        this.maxCacheSize = i;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0032  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    void putCachedDownloadConnections(java.lang.String r4, com.ss.android.socialbase.downloader.network.connectionpool.FakeDownloadHttpConnection r5) {
        /*
            r3 = this;
            java.util.Map<java.lang.String, com.ss.android.socialbase.downloader.network.connectionpool.FakeDownloadHttpConnection> r0 = r3.mCachedDownloadConnections
            monitor-enter(r0)
            java.util.Map<java.lang.String, com.ss.android.socialbase.downloader.network.connectionpool.FakeDownloadHttpConnection> r1 = r3.mCachedDownloadConnections     // Catch: java.lang.Throwable -> L65
            int r1 = r1.size()     // Catch: java.lang.Throwable -> L65
            int r2 = r3.maxCacheSize     // Catch: java.lang.Throwable -> L65
            if (r1 != r2) goto L32
            java.util.Map<java.lang.String, com.ss.android.socialbase.downloader.network.connectionpool.FakeDownloadHttpConnection> r1 = r3.mCachedDownloadConnections     // Catch: java.lang.Throwable -> L65
            java.util.Set r1 = r1.entrySet()     // Catch: java.lang.Throwable -> L65
            java.util.Iterator r1 = r1.iterator()     // Catch: java.lang.Throwable -> L65
            boolean r2 = r1.hasNext()     // Catch: java.lang.Throwable -> L65
            if (r2 == 0) goto L32
            java.lang.Object r1 = r1.next()     // Catch: java.lang.Throwable -> L65
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1     // Catch: java.lang.Throwable -> L65
            if (r1 == 0) goto L32
            java.util.Map<java.lang.String, com.ss.android.socialbase.downloader.network.connectionpool.FakeDownloadHttpConnection> r2 = r3.mCachedDownloadConnections     // Catch: java.lang.Throwable -> L65
            java.lang.Object r1 = r1.getKey()     // Catch: java.lang.Throwable -> L65
            java.lang.Object r1 = r2.remove(r1)     // Catch: java.lang.Throwable -> L65
            com.ss.android.socialbase.downloader.network.connectionpool.FakeDownloadHttpConnection r1 = (com.ss.android.socialbase.downloader.network.connectionpool.FakeDownloadHttpConnection) r1     // Catch: java.lang.Throwable -> L65
            goto L33
        L32:
            r1 = 0
        L33:
            java.util.Map<java.lang.String, com.ss.android.socialbase.downloader.network.connectionpool.FakeDownloadHttpConnection> r2 = r3.mCachedDownloadConnections     // Catch: java.lang.Throwable -> L65
            r2.put(r4, r5)     // Catch: java.lang.Throwable -> L65
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L65
            if (r1 == 0) goto L3e
            r1.end()     // Catch: java.lang.Throwable -> L3e
        L3e:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "mCachedConnections size = "
            r4.append(r5)
            java.util.Map<java.lang.String, com.ss.android.socialbase.downloader.network.connectionpool.FakeDownloadHttpConnection> r5 = r3.mCachedDownloadConnections
            int r5 = r5.size()
            r4.append(r5)
            java.lang.String r5 = ", max size = "
            r4.append(r5)
            int r5 = r3.maxCacheSize
            r4.append(r5)
            java.lang.String r4 = r4.toString()
            java.lang.String r5 = "DownloadConnectionPool"
            com.ss.android.socialbase.downloader.logger.Logger.i(r5, r4)
            return
        L65:
            r4 = move-exception
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L65
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ss.android.socialbase.downloader.network.connectionpool.DownloadConnectionPool.putCachedDownloadConnections(java.lang.String, com.ss.android.socialbase.downloader.network.connectionpool.FakeDownloadHttpConnection):void");
    }

    public void releaseDownloadConnection(String str) {
        FakeDownloadHttpConnection fakeDownloadHttpConnectionRemove;
        synchronized (this.mCachedDownloadConnections) {
            fakeDownloadHttpConnectionRemove = this.mCachedDownloadConnections.remove(str);
        }
        if (fakeDownloadHttpConnectionRemove != null) {
            try {
                fakeDownloadHttpConnectionRemove.end();
            } catch (Throwable unused) {
            }
        }
    }

    public void releaseHeadConnection(String str) {
        FakeDownloadHeadHttpConnection fakeDownloadHeadHttpConnectionRemove;
        synchronized (this.mCachedHeadConnections) {
            fakeDownloadHeadHttpConnectionRemove = this.mCachedHeadConnections.remove(str);
        }
        if (fakeDownloadHeadHttpConnectionRemove != null) {
            fakeDownloadHeadHttpConnectionRemove.cancel();
        }
    }

    public FakeDownloadHeadHttpConnection getCachedHeadConnection(String str, List<HttpHeader> list) {
        FakeDownloadHeadHttpConnection fakeDownloadHeadHttpConnectionRemove;
        synchronized (this.mCachedHeadConnections) {
            fakeDownloadHeadHttpConnectionRemove = this.mCachedHeadConnections.remove(str);
        }
        if (fakeDownloadHeadHttpConnectionRemove == null) {
            return null;
        }
        if (DownloadUtils.isHeaderEqual(fakeDownloadHeadHttpConnectionRemove.getRequestHeaders(), list)) {
            try {
                fakeDownloadHeadHttpConnectionRemove.joinExecute();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (fakeDownloadHeadHttpConnectionRemove.isValid() && fakeDownloadHeadHttpConnectionRemove.isSuccessful()) {
                return fakeDownloadHeadHttpConnectionRemove;
            }
        }
        try {
            fakeDownloadHeadHttpConnectionRemove.cancel();
            return null;
        } catch (Throwable unused) {
            return null;
        }
    }

    public boolean isHeadConnectionExist(String str) {
        FakeDownloadHeadHttpConnection fakeDownloadHeadHttpConnection = this.mCachedHeadConnections.get(str);
        if (fakeDownloadHeadHttpConnection == null) {
            return false;
        }
        if (fakeDownloadHeadHttpConnection.isRequesting()) {
            return true;
        }
        return fakeDownloadHeadHttpConnection.isValid() && fakeDownloadHeadHttpConnection.isSuccessful();
    }

    public FakeDownloadHttpConnection getCachedDownloadConnection(String str, List<HttpHeader> list) {
        FakeDownloadHttpConnection fakeDownloadHttpConnectionRemove;
        synchronized (this.mCachedDownloadConnections) {
            fakeDownloadHttpConnectionRemove = this.mCachedDownloadConnections.remove(str);
        }
        if (fakeDownloadHttpConnectionRemove == null) {
            return null;
        }
        if (DownloadUtils.isHeaderEqual(fakeDownloadHttpConnectionRemove.getRequestHeaders(), list)) {
            try {
                fakeDownloadHttpConnectionRemove.joinExecute();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (fakeDownloadHttpConnectionRemove.isValid() && fakeDownloadHttpConnectionRemove.isSuccessful()) {
                return fakeDownloadHttpConnectionRemove;
            }
        }
        try {
            fakeDownloadHttpConnectionRemove.end();
            return null;
        } catch (Throwable unused) {
            return null;
        }
    }

    public boolean isDownloadConnectionExist(String str) {
        FakeDownloadHttpConnection fakeDownloadHttpConnection = this.mCachedDownloadConnections.get(str);
        if (fakeDownloadHttpConnection == null) {
            return false;
        }
        if (fakeDownloadHttpConnection.isRequesting()) {
            return true;
        }
        return fakeDownloadHttpConnection.isValid() && fakeDownloadHttpConnection.isSuccessful();
    }

    private static final class InstanceHolder {
        private static final DownloadConnectionPool INSTANCE = new DownloadConnectionPool();

        private InstanceHolder() {
        }
    }

    public static DownloadConnectionPool getInstance() {
        return InstanceHolder.INSTANCE;
    }

    private DownloadConnectionPool() {
        this.mCachedHeadConnections = new HashMap();
        this.mCachedDownloadConnections = new LinkedHashMap(3);
        this.maxCacheSize = 3;
    }
}
