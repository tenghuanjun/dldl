package com.sqnetwork.voly.toolbox;

import android.os.SystemClock;
import com.sqnetwork.voly.Cache;
import com.sqnetwork.voly.Header;
import com.sqnetwork.voly.Network;
import com.sqnetwork.voly.Request;
import com.sqnetwork.voly.RetryPolicy;
import com.sqnetwork.voly.ServerError;
import com.sqnetwork.voly.VolleyError;
import com.sqnetwork.voly.VolleyLog;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class BasicNetwork implements Network {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    protected static final boolean DEBUG = VolleyLog.DEBUG;
    private static final int DEFAULT_POOL_SIZE = 4096;
    private static final int SLOW_REQUEST_THRESHOLD_MS = 3000;
    private final BaseHttpStack mBaseHttpStack;
    protected final ByteArrayPool mPool;

    public BasicNetwork(BaseHttpStack httpStack) {
        this(httpStack, new ByteArrayPool(4096));
    }

    public BasicNetwork(BaseHttpStack httpStack, ByteArrayPool pool) {
        this.mBaseHttpStack = httpStack;
        this.mPool = pool;
    }

    /* JADX WARN: Code restructure failed: missing block: B:106:0x0233, code lost:
    
        throw new com.sqnetwork.voly.ServerError(r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:108:0x023e, code lost:
    
        r15.error = new com.sqnetwork.voly.ServerError(r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:109:0x024a, code lost:
    
        throw new com.sqnetwork.voly.ServerError(r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:125:0x02a1, code lost:
    
        throw new com.sqnetwork.voly.SSLError(r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:138:0x02cb, code lost:
    
        throw new com.sqnetwork.voly.NoConnectionError(r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:142:0x02d7, code lost:
    
        throw new com.sqnetwork.voly.HostError(r0);
     */
    /* JADX WARN: Removed duplicated region for block: B:160:0x0278 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:174:0x01a9 A[SYNTHETIC] */
    @Override // com.sqnetwork.voly.Network
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.sqnetwork.voly.NetworkResponse performRequest(com.sqnetwork.voly.Request<?> r31) throws com.sqnetwork.voly.VolleyError {
        /*
            Method dump skipped, instruction units count: 773
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sqnetwork.voly.toolbox.BasicNetwork.performRequest(com.sqnetwork.voly.Request):com.sqnetwork.voly.NetworkResponse");
    }

    private void logSlowRequests(long requestLifetime, Request<?> request, byte[] responseContents, int statusCode) {
        if (requestLifetime > 3000) {
            Object[] objArr = new Object[5];
            objArr[0] = request;
            objArr[1] = Long.valueOf(requestLifetime);
            objArr[2] = responseContents != null ? Integer.valueOf(responseContents.length) : AbstractJsonLexerKt.NULL;
            objArr[3] = Integer.valueOf(statusCode);
            objArr[4] = Integer.valueOf(request.getRetryPolicy().getCurrentRetryCount());
            VolleyLog.d("HTTP response for request=<%s> [lifetime=%d], [size=%s], [rc=%d], [retryCount=%s]", objArr);
        }
    }

    private static void attemptRetryOnException(String logPrefix, Request<?> request, VolleyError exception) throws VolleyError {
        RetryPolicy retryPolicy = request.getRetryPolicy();
        int timeoutMs = request.getTimeoutMs();
        try {
            retryPolicy.retry(request, exception);
            request.addMarker(String.format("%s-retry [timeout=%s]", logPrefix, Integer.valueOf(timeoutMs)));
        } catch (VolleyError e) {
            request.addMarker(String.format("%s-timeout-giveup [timeout=%s]", logPrefix, Integer.valueOf(timeoutMs)));
            throw e;
        }
    }

    private Map<String, String> getCacheHeaders(Cache.Entry entry) {
        if (entry == null) {
            return Collections.emptyMap();
        }
        HashMap map = new HashMap();
        if (entry.etag != null) {
            map.put("If-None-Match", entry.etag);
        }
        if (entry.lastModified > 0) {
            map.put("If-Modified-Since", HttpHeaderParser.formatEpochAsRfc1123(entry.lastModified));
        }
        return map;
    }

    protected void logError(String what, String url, long start) {
        VolleyLog.v("HTTP ERROR(%s) %d ms to fetch %s", what, Long.valueOf(SystemClock.elapsedRealtime() - start), url);
    }

    private byte[] inputStreamToBytes(InputStream in, int contentLength) throws IOException, ServerError {
        PoolingByteArrayOutputStream poolingByteArrayOutputStream = new PoolingByteArrayOutputStream(this.mPool, contentLength);
        try {
            if (in == null) {
                throw new ServerError();
            }
            byte[] buf = this.mPool.getBuf(1024);
            while (true) {
                int i = in.read(buf);
                if (i == -1) {
                    break;
                }
                poolingByteArrayOutputStream.write(buf, 0, i);
            }
            byte[] byteArray = poolingByteArrayOutputStream.toByteArray();
            if (in != null) {
                try {
                    in.close();
                } catch (IOException unused) {
                    VolleyLog.v("Error occurred when closing InputStream", new Object[0]);
                }
            }
            this.mPool.returnBuf(buf);
            poolingByteArrayOutputStream.close();
            return byteArray;
        } catch (Throwable th) {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException unused2) {
                    VolleyLog.v("Error occurred when closing InputStream", new Object[0]);
                }
            }
            this.mPool.returnBuf(null);
            poolingByteArrayOutputStream.close();
            throw th;
        }
    }

    @Deprecated
    protected static Map<String, String> convertHeaders(Header[] headers) {
        TreeMap treeMap = new TreeMap(String.CASE_INSENSITIVE_ORDER);
        for (int i = 0; i < headers.length; i++) {
            treeMap.put(headers[i].getName(), headers[i].getValue());
        }
        return treeMap;
    }

    private static List<Header> combineHeaders(List<Header> responseHeaders, Cache.Entry entry) {
        TreeSet treeSet = new TreeSet(String.CASE_INSENSITIVE_ORDER);
        if (!responseHeaders.isEmpty()) {
            Iterator<Header> it = responseHeaders.iterator();
            while (it.hasNext()) {
                treeSet.add(it.next().getName());
            }
        }
        ArrayList arrayList = new ArrayList(responseHeaders);
        if (entry.allResponseHeaders != null) {
            if (!entry.allResponseHeaders.isEmpty()) {
                for (Header header : entry.allResponseHeaders) {
                    if (!treeSet.contains(header.getName())) {
                        arrayList.add(header);
                    }
                }
            }
        } else if (!entry.responseHeaders.isEmpty()) {
            for (Map.Entry<String, String> entry2 : entry.responseHeaders.entrySet()) {
                if (!treeSet.contains(entry2.getKey())) {
                    arrayList.add(new Header(entry2.getKey(), entry2.getValue()));
                }
            }
        }
        return arrayList;
    }
}
