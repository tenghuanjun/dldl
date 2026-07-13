package com.mt.w;

import java.io.File;
import java.io.IOException;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import okhttp3.internal.ws.WebSocketProtocol;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public final class d {
    public static b<ByteBuffer, Long> a(FileChannel fileChannel) throws c, IOException {
        long size = fileChannel.size();
        if (size < 22) {
            throw new IOException("APK too small for ZIP End of Central Directory (EOCD) record");
        }
        long j = size - 22;
        long jMin = Math.min(j, WebSocketProtocol.PAYLOAD_SHORT_MAX);
        int i = 0;
        while (true) {
            long j2 = i;
            if (j2 > jMin) {
                throw new IOException("ZIP End of Central Directory (EOCD) record not found");
            }
            long j3 = j - j2;
            ByteBuffer byteBufferAllocate = ByteBuffer.allocate(4);
            fileChannel.position(j3);
            fileChannel.read(byteBufferAllocate);
            ByteOrder byteOrder = ByteOrder.LITTLE_ENDIAN;
            byteBufferAllocate.order(byteOrder);
            if (byteBufferAllocate.getInt(0) == 101010256) {
                ByteBuffer byteBufferAllocate2 = ByteBuffer.allocate(2);
                fileChannel.position(j3 + 20);
                fileChannel.read(byteBufferAllocate2);
                byteBufferAllocate2.order(byteOrder);
                short s = byteBufferAllocate2.getShort(0);
                if (s == i) {
                    ByteBuffer byteBufferAllocate3 = ByteBuffer.allocate(4);
                    byteBufferAllocate3.order(byteOrder);
                    fileChannel.position((fileChannel.size() - ((long) s)) - 6);
                    fileChannel.read(byteBufferAllocate3);
                    long j4 = byteBufferAllocate3.getInt(0);
                    if (j4 < 32) {
                        throw new c("APK too small for APK Signing Block. ZIP Central Directory offset: " + j4);
                    }
                    fileChannel.position(j4 - 24);
                    ByteBuffer byteBufferAllocate4 = ByteBuffer.allocate(24);
                    fileChannel.read(byteBufferAllocate4);
                    byteBufferAllocate4.order(byteOrder);
                    if (byteBufferAllocate4.getLong(8) != 2334950737559900225L || byteBufferAllocate4.getLong(16) != 3617552046287187010L) {
                        throw new c("No APK Signing Block before ZIP Central Directory");
                    }
                    long j5 = byteBufferAllocate4.getLong(0);
                    if (j5 < byteBufferAllocate4.capacity() || j5 > 2147483639) {
                        throw new c("APK Signing Block size out of range: " + j5);
                    }
                    int i2 = (int) (8 + j5);
                    long j6 = j4 - ((long) i2);
                    if (j6 < 0) {
                        throw new c("APK Signing Block offset out of range: " + j6);
                    }
                    fileChannel.position(j6);
                    ByteBuffer byteBufferAllocate5 = ByteBuffer.allocate(i2);
                    fileChannel.read(byteBufferAllocate5);
                    byteBufferAllocate5.order(byteOrder);
                    long j7 = byteBufferAllocate5.getLong(0);
                    if (j7 == j5) {
                        return new b<>(byteBufferAllocate5, Long.valueOf(j6));
                    }
                    throw new c("APK Signing Block sizes in header and footer do not match: " + j7 + " vs " + j5);
                }
            }
            i++;
        }
    }

    public static Map<String, String> a(File file) throws Throwable {
        try {
            String strB = b(file);
            if (strB == null) {
                return null;
            }
            JSONObject jSONObject = new JSONObject(strB);
            Iterator<String> itKeys = jSONObject.keys();
            HashMap map = new HashMap();
            while (itKeys.hasNext()) {
                String string = itKeys.next().toString();
                map.put(string, jSONObject.getString(string));
            }
            return map;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x0050  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x005f  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x007a A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:70:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String b(java.io.File r4) throws java.lang.Throwable {
        /*
            r0 = 0
            java.io.RandomAccessFile r1 = new java.io.RandomAccessFile     // Catch: java.lang.Throwable -> L26 java.io.IOException -> L37
            java.lang.String r2 = "r"
            r1.<init>(r4, r2)     // Catch: java.lang.Throwable -> L26 java.io.IOException -> L37
            java.nio.channels.FileChannel r4 = r1.getChannel()     // Catch: java.lang.Throwable -> L20 java.io.IOException -> L23
            com.mt.w.b r2 = a(r4)     // Catch: java.lang.Throwable -> L1c java.io.IOException -> L1e
            A r2 = r2.f838a     // Catch: java.lang.Throwable -> L1c java.io.IOException -> L1e
            java.nio.ByteBuffer r2 = (java.nio.ByteBuffer) r2     // Catch: java.lang.Throwable -> L1c java.io.IOException -> L1e
            java.util.Map r2 = a(r2)     // Catch: java.lang.Throwable -> L1c java.io.IOException -> L1e
            r4.close()     // Catch: java.io.IOException -> L46 java.lang.Throwable -> L4a
            goto L46
        L1c:
            r2 = move-exception
            goto L2a
        L1e:
            goto L3a
        L20:
            r2 = move-exception
            r4 = r0
            goto L2a
        L23:
            r4 = r0
            goto L3a
        L26:
            r4 = move-exception
            r2 = r4
            r4 = r0
            r1 = r4
        L2a:
            if (r4 == 0) goto L31
            r4.close()     // Catch: java.io.IOException -> L30 com.mt.w.c -> L40
            goto L31
        L30:
        L31:
            if (r1 == 0) goto L36
            r1.close()     // Catch: java.io.IOException -> L36 com.mt.w.c -> L40
        L36:
            throw r2     // Catch: com.mt.w.c -> L40
        L37:
            r4 = r0
            r1 = r4
        L3a:
            if (r4 == 0) goto L43
            r4.close()     // Catch: com.mt.w.c -> L40 java.io.IOException -> L42
            goto L43
        L40:
            goto L4c
        L42:
        L43:
            if (r1 == 0) goto L4c
            r2 = r0
        L46:
            r1.close()     // Catch: java.lang.Throwable -> L4a
            goto L4d
        L4a:
            goto L4d
        L4c:
            r2 = r0
        L4d:
            if (r2 != 0) goto L50
            goto L5f
        L50:
            r4 = 1903654775(0x71777777, float:1.22539554E30)
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            java.lang.Object r4 = r2.get(r4)
            java.nio.ByteBuffer r4 = (java.nio.ByteBuffer) r4
            if (r4 != 0) goto L61
        L5f:
            r4 = r0
            goto L77
        L61:
            byte[] r1 = r4.array()
            int r2 = r4.arrayOffset()
            int r3 = r4.position()
            int r3 = r3 + r2
            int r4 = r4.limit()
            int r2 = r2 + r4
            byte[] r4 = java.util.Arrays.copyOfRange(r1, r3, r2)
        L77:
            if (r4 != 0) goto L7a
            goto L87
        L7a:
            java.lang.String r1 = new java.lang.String     // Catch: java.io.UnsupportedEncodingException -> L83
            java.lang.String r2 = "UTF-8"
            r1.<init>(r4, r2)     // Catch: java.io.UnsupportedEncodingException -> L83
            r0 = r1
            goto L87
        L83:
            r4 = move-exception
            r4.printStackTrace()
        L87:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mt.w.d.b(java.io.File):java.lang.String");
    }

    public static Map<Integer, ByteBuffer> a(ByteBuffer byteBuffer) throws c {
        if (byteBuffer.order() == ByteOrder.LITTLE_ENDIAN) {
            int iCapacity = byteBuffer.capacity() - 24;
            if (iCapacity < 8) {
                throw new IllegalArgumentException("end < start: " + iCapacity + " < 8");
            }
            int iCapacity2 = byteBuffer.capacity();
            if (iCapacity > byteBuffer.capacity()) {
                throw new IllegalArgumentException("end > capacity: " + iCapacity + " > " + iCapacity2);
            }
            int iLimit = byteBuffer.limit();
            int iPosition = byteBuffer.position();
            int i = 0;
            try {
                byteBuffer.position(0);
                byteBuffer.limit(iCapacity);
                byteBuffer.position(8);
                ByteBuffer byteBufferSlice = byteBuffer.slice();
                byteBufferSlice.order(byteBuffer.order());
                byteBuffer.position(0);
                byteBuffer.limit(iLimit);
                byteBuffer.position(iPosition);
                LinkedHashMap linkedHashMap = new LinkedHashMap();
                while (byteBufferSlice.hasRemaining()) {
                    i++;
                    if (byteBufferSlice.remaining() < 8) {
                        throw new c("Insufficient data to read size of APK Signing Block entry #" + i);
                    }
                    long j = byteBufferSlice.getLong();
                    if (j < 4 || j > 2147483647L) {
                        throw new c("APK Signing Block entry #" + i + " size out of range: " + j);
                    }
                    int i2 = (int) j;
                    int iPosition2 = byteBufferSlice.position() + i2;
                    if (i2 > byteBufferSlice.remaining()) {
                        throw new c("APK Signing Block entry #" + i + " size out of range: " + i2 + ", available: " + byteBufferSlice.remaining());
                    }
                    Integer numValueOf = Integer.valueOf(byteBufferSlice.getInt());
                    int i3 = i2 - 4;
                    if (i3 < 0) {
                        throw new IllegalArgumentException("size: " + i3);
                    }
                    int iLimit2 = byteBufferSlice.limit();
                    int iPosition3 = byteBufferSlice.position();
                    int i4 = i3 + iPosition3;
                    if (i4 < iPosition3 || i4 > iLimit2) {
                        throw new BufferUnderflowException();
                    }
                    byteBufferSlice.limit(i4);
                    try {
                        ByteBuffer byteBufferSlice2 = byteBufferSlice.slice();
                        byteBufferSlice2.order(byteBufferSlice.order());
                        byteBufferSlice.position(i4);
                        byteBufferSlice.limit(iLimit2);
                        linkedHashMap.put(numValueOf, byteBufferSlice2);
                        byteBufferSlice.position(iPosition2);
                    } catch (Throwable th) {
                        byteBufferSlice.limit(iLimit2);
                        throw th;
                    }
                }
                return linkedHashMap;
            } catch (Throwable th2) {
                byteBuffer.position(0);
                byteBuffer.limit(iLimit);
                byteBuffer.position(iPosition);
                throw th2;
            }
        }
        throw new IllegalArgumentException("ByteBuffer byte order must be little endian");
    }
}
