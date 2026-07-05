package com.huya.hyhttpdns.dns;

import android.content.Context;
import com.duowan.taf.jce.JceInputStream;
import com.huya.hyhttpdns.jce.HttpDnsItem;
import com.huya.hyhttpdns.jce.QueryHttpDnsRsp;
import java.io.BufferedInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
class HttpDnsDiskCache {
    private static final int CACHE_MAGIC = 538247942;
    private static final int DEFAULT_DISK_USAGE_BYTES = 5242880;
    private static final float HYSTERESIS_FACTOR = 0.9f;
    private static final String TAG = "HttpDnsDiskCache";
    private static boolean sHasLoadKeys;
    private final Map<String, CacheHeader> mEntries;
    private final int mMaxCacheSizeInBytes;
    private final File mRootDirectory;
    private long mTotalSize;

    public HttpDnsDiskCache(String str, Context context) {
        this(str, DEFAULT_DISK_USAGE_BYTES, context);
    }

    public HttpDnsDiskCache(String str, int i, Context context) {
        this.mEntries = new LinkedHashMap(16, 0.75f, true);
        this.mTotalSize = 0L;
        File file = new File(getRootCacheDir(str, context));
        if (!file.exists()) {
            if (!file.mkdirs()) {
                HttpDnsLogProxy.getInstance().error(TAG, "Unable to create cache dir =%s", file.getAbsolutePath());
            }
        } else {
            HttpDnsLogProxy.getInstance().info(TAG, "cache dir exists");
        }
        this.mRootDirectory = file;
        this.mMaxCacheSizeInBytes = i;
        HttpDnsLogProxy.getInstance().info(TAG, "init root=%s, maxSize=%s", this.mRootDirectory, Integer.valueOf(this.mMaxCacheSizeInBytes));
    }

    private String getRootCacheDir(String str, Context context) {
        String str2 = context.getFilesDir() + "/HttpDnsCache";
        if (str == null || "".equals(str)) {
            HttpDnsLogProxy.getInstance().info("use default dir=%s", str2);
            return str2;
        }
        File file = new File(str);
        int iCheckCallingOrSelfPermission = context.checkCallingOrSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE");
        if (file.exists() && file.isDirectory() && file.canWrite() && iCheckCallingOrSelfPermission == 0) {
            HttpDnsLogProxy.getInstance().info(TAG, "use user set dir=%s", str);
            return str;
        }
        HttpDnsLogProxy.getInstance().info("use default dir=%s", str2);
        return str2;
    }

    public synchronized void loadKeysFromDisk() {
        BufferedInputStream bufferedInputStream;
        if (sHasLoadKeys) {
            return;
        }
        File[] fileArrListFiles = this.mRootDirectory.listFiles();
        if (fileArrListFiles == null) {
            return;
        }
        for (File file : fileArrListFiles) {
            BufferedInputStream bufferedInputStream2 = null;
            try {
                try {
                    bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
                } catch (Throwable th) {
                    th = th;
                }
            } catch (IOException unused) {
            }
            try {
                CacheHeader header = CacheHeader.readHeader(bufferedInputStream);
                header.size = file.length();
                pruneIfNeeded((int) header.size);
                HttpDnsLogProxy.getInstance().info(TAG, "initlize entry.key = %s", header.key);
                putEntry(header.key, header);
                try {
                    bufferedInputStream.close();
                } catch (IOException unused2) {
                }
            } catch (IOException unused3) {
                bufferedInputStream2 = bufferedInputStream;
                if (file != null) {
                    file.delete();
                }
                if (bufferedInputStream2 != null) {
                    bufferedInputStream2.close();
                }
            } catch (Throwable th2) {
                th = th2;
                bufferedInputStream2 = bufferedInputStream;
                if (bufferedInputStream2 != null) {
                    try {
                        bufferedInputStream2.close();
                    } catch (IOException unused4) {
                    }
                }
                throw th;
            }
        }
        sHasLoadKeys = true;
    }

    public synchronized void put(String str, byte[] bArr) {
        pruneIfNeeded(bArr.length);
        File fileForKey = getFileForKey(str);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(fileForKey);
            CacheHeader cacheHeader = new CacheHeader(str, bArr.length);
            if (!cacheHeader.writeHeader(fileOutputStream)) {
                fileOutputStream.close();
                throw new IOException();
            }
            fileOutputStream.write(bArr);
            fileOutputStream.close();
            putEntry(str, cacheHeader);
        } catch (IOException e) {
            HttpDnsLogProxy.getInstance().error(TAG, "put error:" + e.getMessage());
            if (fileForKey.delete()) {
                return;
            }
            HttpDnsLogProxy.getInstance().debug(TAG, "Could not clean up file" + fileForKey.getAbsolutePath());
        }
    }

    private void putEntry(String str, CacheHeader cacheHeader) {
        if (!this.mEntries.containsKey(str)) {
            this.mTotalSize += cacheHeader.size;
        } else {
            this.mTotalSize += cacheHeader.size - this.mEntries.get(str).size;
        }
        this.mEntries.put(str, cacheHeader);
    }

    private void pruneIfNeeded(int i) {
        if (this.mTotalSize + i < this.mMaxCacheSizeInBytes) {
            return;
        }
        Iterator<Map.Entry<String, CacheHeader>> it = this.mEntries.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, CacheHeader> next = it.next();
            if (getFileForKey(next.getKey()).delete()) {
                this.mTotalSize -= next.getValue().size;
            }
            it.remove();
            if (this.mTotalSize + r2 < this.mMaxCacheSizeInBytes * HYSTERESIS_FACTOR) {
                return;
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00bf A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.huya.hyhttpdns.jce.QueryHttpDnsRsp get(java.lang.String r10) throws java.lang.Throwable {
        /*
            r9 = this;
            java.lang.String r0 = " "
            com.huya.hyhttpdns.dns.HttpDnsLogProxy r1 = com.huya.hyhttpdns.dns.HttpDnsLogProxy.getInstance()
            r2 = 1
            java.lang.Object[] r2 = new java.lang.Object[r2]
            r3 = 0
            r2[r3] = r10
            java.lang.String r3 = "HttpDnsDiskCache"
            java.lang.String r4 = "get key =%s"
            r1.info(r3, r4, r2)
            java.util.Map<java.lang.String, com.huya.hyhttpdns.dns.HttpDnsDiskCache$CacheHeader> r1 = r9.mEntries
            java.lang.Object r1 = r1.get(r10)
            com.huya.hyhttpdns.dns.HttpDnsDiskCache$CacheHeader r1 = (com.huya.hyhttpdns.dns.HttpDnsDiskCache.CacheHeader) r1
            r2 = 0
            if (r1 != 0) goto L1f
            return r2
        L1f:
            java.io.File r1 = r9.getFileForKey(r10)
            com.huya.hyhttpdns.dns.HttpDnsDiskCache$CountingInputStream r4 = new com.huya.hyhttpdns.dns.HttpDnsDiskCache$CountingInputStream     // Catch: java.lang.Throwable -> L50 java.lang.Exception -> L52 java.io.IOException -> L85
            java.io.FileInputStream r5 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L50 java.lang.Exception -> L52 java.io.IOException -> L85
            r5.<init>(r1)     // Catch: java.lang.Throwable -> L50 java.lang.Exception -> L52 java.io.IOException -> L85
            r4.<init>(r5)     // Catch: java.lang.Throwable -> L50 java.lang.Exception -> L52 java.io.IOException -> L85
            com.huya.hyhttpdns.dns.HttpDnsDiskCache.CacheHeader.readHeader(r4)     // Catch: java.lang.Exception -> L4c java.io.IOException -> L4e java.lang.Throwable -> Lbb
            long r5 = r1.length()     // Catch: java.lang.Exception -> L4c java.io.IOException -> L4e java.lang.Throwable -> Lbb
            int r7 = com.huya.hyhttpdns.dns.HttpDnsDiskCache.CountingInputStream.access$100(r4)     // Catch: java.lang.Exception -> L4c java.io.IOException -> L4e java.lang.Throwable -> Lbb
            long r7 = (long) r7     // Catch: java.lang.Exception -> L4c java.io.IOException -> L4e java.lang.Throwable -> Lbb
            long r5 = r5 - r7
            int r6 = (int) r5     // Catch: java.lang.Exception -> L4c java.io.IOException -> L4e java.lang.Throwable -> Lbb
            byte[] r5 = streamToBytes(r4, r6)     // Catch: java.lang.Exception -> L4c java.io.IOException -> L4e java.lang.Throwable -> Lbb
            com.huya.hyhttpdns.jce.QueryHttpDnsRsp r10 = r9.getQueryHttpDnsRsp(r5)     // Catch: java.lang.Exception -> L4c java.io.IOException -> L4e java.lang.Throwable -> Lbb
            r4.close()     // Catch: java.io.IOException -> L47
            goto L4b
        L47:
            r0 = move-exception
            r0.printStackTrace()
        L4b:
            return r10
        L4c:
            r10 = move-exception
            goto L54
        L4e:
            r5 = move-exception
            goto L87
        L50:
            r10 = move-exception
            goto Lbd
        L52:
            r10 = move-exception
            r4 = r2
        L54:
            com.huya.hyhttpdns.dns.HttpDnsLogProxy r5 = com.huya.hyhttpdns.dns.HttpDnsLogProxy.getInstance()     // Catch: java.lang.Throwable -> Lbb
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Lbb
            r6.<init>()     // Catch: java.lang.Throwable -> Lbb
            java.lang.String r7 = "get"
            r6.append(r7)     // Catch: java.lang.Throwable -> Lbb
            java.lang.String r1 = r1.getAbsolutePath()     // Catch: java.lang.Throwable -> Lbb
            r6.append(r1)     // Catch: java.lang.Throwable -> Lbb
            r6.append(r0)     // Catch: java.lang.Throwable -> Lbb
            java.lang.String r10 = r10.toString()     // Catch: java.lang.Throwable -> Lbb
            r6.append(r10)     // Catch: java.lang.Throwable -> Lbb
            java.lang.String r10 = r6.toString()     // Catch: java.lang.Throwable -> Lbb
            r5.error(r3, r10)     // Catch: java.lang.Throwable -> Lbb
            if (r4 == 0) goto L84
            r4.close()     // Catch: java.io.IOException -> L80
            goto L84
        L80:
            r10 = move-exception
            r10.printStackTrace()
        L84:
            return r2
        L85:
            r5 = move-exception
            r4 = r2
        L87:
            com.huya.hyhttpdns.dns.HttpDnsLogProxy r6 = com.huya.hyhttpdns.dns.HttpDnsLogProxy.getInstance()     // Catch: java.lang.Throwable -> Lbb
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Lbb
            r7.<init>()     // Catch: java.lang.Throwable -> Lbb
            java.lang.String r8 = "get "
            r7.append(r8)     // Catch: java.lang.Throwable -> Lbb
            java.lang.String r1 = r1.getAbsolutePath()     // Catch: java.lang.Throwable -> Lbb
            r7.append(r1)     // Catch: java.lang.Throwable -> Lbb
            r7.append(r0)     // Catch: java.lang.Throwable -> Lbb
            java.lang.String r0 = r5.toString()     // Catch: java.lang.Throwable -> Lbb
            r7.append(r0)     // Catch: java.lang.Throwable -> Lbb
            java.lang.String r0 = r7.toString()     // Catch: java.lang.Throwable -> Lbb
            r6.error(r3, r0)     // Catch: java.lang.Throwable -> Lbb
            r9.remove(r10)     // Catch: java.lang.Throwable -> Lbb
            if (r4 == 0) goto Lba
            r4.close()     // Catch: java.io.IOException -> Lb6
            goto Lba
        Lb6:
            r10 = move-exception
            r10.printStackTrace()
        Lba:
            return r2
        Lbb:
            r10 = move-exception
            r2 = r4
        Lbd:
            if (r2 == 0) goto Lc7
            r2.close()     // Catch: java.io.IOException -> Lc3
            goto Lc7
        Lc3:
            r0 = move-exception
            r0.printStackTrace()
        Lc7:
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huya.hyhttpdns.dns.HttpDnsDiskCache.get(java.lang.String):com.huya.hyhttpdns.jce.QueryHttpDnsRsp");
    }

    private synchronized QueryHttpDnsRsp getQueryHttpDnsRsp(byte[] bArr) {
        QueryHttpDnsRsp queryHttpDnsRsp;
        queryHttpDnsRsp = new QueryHttpDnsRsp();
        queryHttpDnsRsp.readFrom(new JceInputStream(bArr));
        HttpDnsLogProxy.getInstance().debug(TAG, "from disk queryHttpDnsRsp = " + queryHttpDnsRsp);
        return queryHttpDnsRsp;
    }

    public synchronized void remove(String str) {
        boolean zDelete = getFileForKey(str).delete();
        removeEntry(str);
        if (!zDelete) {
            HttpDnsLogProxy.getInstance().debug(TAG, "Could not delete cache entry for key = %s  filename=", str, getFilenameForKey(str));
        }
    }

    private void removeEntry(String str) {
        CacheHeader cacheHeader = this.mEntries.get(str);
        if (cacheHeader != null) {
            this.mTotalSize -= cacheHeader.size;
            this.mEntries.remove(str);
        }
    }

    private File getFileForKey(String str) {
        return new File(this.mRootDirectory, getFilenameForKey(str));
    }

    private String getFilenameForKey(String str) {
        int length = str.length() / 2;
        return String.valueOf(str.substring(0, length).hashCode()) + String.valueOf(str.substring(length).hashCode());
    }

    private static byte[] streamToBytes(InputStream inputStream, int i) throws IOException {
        byte[] bArr = new byte[i];
        int i2 = 0;
        while (i2 < i) {
            int i3 = inputStream.read(bArr, i2, i - i2);
            if (i3 == -1) {
                break;
            }
            i2 += i3;
        }
        if (i2 == i) {
            return bArr;
        }
        throw new IOException("Expected " + i + " bytes, read " + i2 + " bytes");
    }

    public void removeIpsFromDisk(final List<String> list) {
        final File[] fileArrListFiles = this.mRootDirectory.listFiles();
        if (fileArrListFiles == null) {
            return;
        }
        HttpDnsThreadPool.execute(new Runnable() { // from class: com.huya.hyhttpdns.dns.HttpDnsDiskCache.1
            @Override // java.lang.Runnable
            public void run() {
                QueryHttpDnsRsp queryHttpDnsRsp;
                for (File file : fileArrListFiles) {
                    try {
                        CacheHeader header = CacheHeader.readHeader(new BufferedInputStream(new FileInputStream(file)));
                        if (header.key != null && (queryHttpDnsRsp = HttpDnsDiskCache.this.get(header.key)) != null) {
                            for (Map.Entry<String, HttpDnsItem> entry : queryHttpDnsRsp.getMDomain2Ip().entrySet()) {
                                HttpDnsItem value = entry.getValue();
                                ArrayList<String> vIp = entry.getValue().getVIp();
                                if (entry.getValue().getVIpv6() != null) {
                                    vIp.addAll(entry.getValue().getVIpv6());
                                }
                                if (vIp != null && !vIp.isEmpty()) {
                                    Iterator<String> it = vIp.iterator();
                                    while (it.hasNext()) {
                                        String next = it.next();
                                        Iterator it2 = list.iterator();
                                        while (true) {
                                            if (it2.hasNext()) {
                                                String strSubstring = (String) it2.next();
                                                if (strSubstring.contains(":")) {
                                                    strSubstring = strSubstring.substring(0, strSubstring.indexOf(":"));
                                                }
                                                if (next != null && next.equals(strSubstring)) {
                                                    it.remove();
                                                    break;
                                                }
                                            }
                                        }
                                    }
                                    HttpDnsLogProxy.getInstance().debug(HttpDnsDiskCache.TAG, "after reomve ip, entry.key = %s, httpDnsItem = %s", header.key, value);
                                }
                            }
                            HttpDnsDiskCache.this.put(header.key, queryHttpDnsRsp.toByteArray());
                        }
                    } catch (Exception e) {
                        HttpDnsLogProxy.getInstance().error(HttpDnsDiskCache.TAG, "removeIpsFromDisk" + e.getMessage());
                    }
                }
            }
        });
    }

    static class CacheHeader {
        String key;
        long size;

        private CacheHeader() {
        }

        CacheHeader(String str, int i) {
            this.key = str;
            this.size = i;
        }

        static CacheHeader readHeader(InputStream inputStream) throws IOException {
            CacheHeader cacheHeader = new CacheHeader();
            if (HttpDnsDiskCache.readInt(inputStream) != HttpDnsDiskCache.CACHE_MAGIC) {
                throw new IOException();
            }
            cacheHeader.key = HttpDnsDiskCache.readString(inputStream);
            return cacheHeader;
        }

        boolean writeHeader(OutputStream outputStream) {
            try {
                HttpDnsDiskCache.writeInt(outputStream, HttpDnsDiskCache.CACHE_MAGIC);
                HttpDnsDiskCache.writeString(outputStream, this.key);
                outputStream.flush();
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
    }

    private static class CountingInputStream extends FilterInputStream {
        private int bytesRead;

        private CountingInputStream(InputStream inputStream) {
            super(inputStream);
            this.bytesRead = 0;
        }

        @Override // java.io.FilterInputStream, java.io.InputStream
        public int read() throws IOException {
            int i = super.read();
            if (i != -1) {
                this.bytesRead++;
            }
            return i;
        }

        @Override // java.io.FilterInputStream, java.io.InputStream
        public int read(byte[] bArr, int i, int i2) throws IOException {
            int i3 = super.read(bArr, i, i2);
            if (i3 != -1) {
                this.bytesRead += i3;
            }
            return i3;
        }
    }

    private static int read(InputStream inputStream) throws IOException {
        int i = inputStream.read();
        if (i != -1) {
            return i;
        }
        throw new EOFException();
    }

    static void writeInt(OutputStream outputStream, int i) throws IOException {
        outputStream.write((i >> 0) & 255);
        outputStream.write((i >> 8) & 255);
        outputStream.write((i >> 16) & 255);
        outputStream.write((i >> 24) & 255);
    }

    static int readInt(InputStream inputStream) throws IOException {
        return (read(inputStream) << 24) | (read(inputStream) << 0) | 0 | (read(inputStream) << 8) | (read(inputStream) << 16);
    }

    static void writeLong(OutputStream outputStream, long j) throws IOException {
        outputStream.write((byte) (j >>> 0));
        outputStream.write((byte) (j >>> 8));
        outputStream.write((byte) (j >>> 16));
        outputStream.write((byte) (j >>> 24));
        outputStream.write((byte) (j >>> 32));
        outputStream.write((byte) (j >>> 40));
        outputStream.write((byte) (j >>> 48));
        outputStream.write((byte) (j >>> 56));
    }

    static long readLong(InputStream inputStream) throws IOException {
        return ((((long) read(inputStream)) & 255) << 0) | 0 | ((((long) read(inputStream)) & 255) << 8) | ((((long) read(inputStream)) & 255) << 16) | ((((long) read(inputStream)) & 255) << 24) | ((((long) read(inputStream)) & 255) << 32) | ((((long) read(inputStream)) & 255) << 40) | ((((long) read(inputStream)) & 255) << 48) | ((255 & ((long) read(inputStream))) << 56);
    }

    static void writeString(OutputStream outputStream, String str) throws IOException {
        byte[] bytes = str.getBytes("UTF-8");
        writeLong(outputStream, bytes.length);
        outputStream.write(bytes, 0, bytes.length);
    }

    static String readString(InputStream inputStream) throws IOException {
        return new String(streamToBytes(inputStream, (int) readLong(inputStream)), "UTF-8");
    }
}
