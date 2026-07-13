package com.bytedance.hume.readapk;

import java.io.IOException;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;
import java.util.LinkedHashMap;
import java.util.Map;
import okhttp3.internal.ws.WebSocketProtocol;

/* JADX INFO: loaded from: classes2.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final long f399a = 3617552046287187010L;
    public static final long b = 2334950737559900225L;
    public static final int c = 1896449818;
    public static final int d = 1114793335;
    public static final int e = 4096;
    public static final String f = "UTF-8";
    private static final int g = 32;
    private static final int h = 22;
    private static final int i = 101010256;
    private static final int j = 65535;
    private static final int k = 20;

    private a() {
    }

    public static long a(FileChannel fileChannel) throws IOException {
        long size = fileChannel.size();
        if (size < 22) {
            throw new IOException("APK too small for ZIP End of Central Directory (EOCD) record");
        }
        long j2 = size - 22;
        long jMin = Math.min(j2, WebSocketProtocol.PAYLOAD_SHORT_MAX);
        int i2 = 0;
        while (true) {
            long j3 = i2;
            if (j3 > jMin) {
                throw new IOException("ZIP End of Central Directory (EOCD) record not found");
            }
            long j4 = j2 - j3;
            ByteBuffer byteBufferAllocate = ByteBuffer.allocate(4);
            fileChannel.position(j4);
            fileChannel.read(byteBufferAllocate);
            byteBufferAllocate.order(ByteOrder.LITTLE_ENDIAN);
            if (byteBufferAllocate.getInt(0) == i) {
                ByteBuffer byteBufferAllocate2 = ByteBuffer.allocate(2);
                fileChannel.position(j4 + 20);
                fileChannel.read(byteBufferAllocate2);
                byteBufferAllocate2.order(ByteOrder.LITTLE_ENDIAN);
                short s = byteBufferAllocate2.getShort(0);
                if (s == i2) {
                    return s;
                }
            }
            i2++;
        }
    }

    public static long a(FileChannel fileChannel, long j2) throws IOException {
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(4);
        byteBufferAllocate.order(ByteOrder.LITTLE_ENDIAN);
        fileChannel.position((fileChannel.size() - j2) - 6);
        fileChannel.read(byteBufferAllocate);
        long j3 = byteBufferAllocate.getInt(0);
        if (j3 >= 0) {
            return j3;
        }
        fileChannel.read(byteBufferAllocate);
        byte[] bArr = new byte[8];
        for (int i2 = 4; i2 < 8; i2++) {
            bArr[i2] = byteBufferAllocate.get(7 - i2);
        }
        return ByteBuffer.wrap(bArr).getLong();
    }

    private static ByteBuffer a(ByteBuffer byteBuffer, int i2) {
        if (i2 < 0) {
            throw new IllegalArgumentException("size: " + i2);
        }
        int iLimit = byteBuffer.limit();
        int iPosition = byteBuffer.position();
        int i3 = i2 + iPosition;
        if (i3 < iPosition || i3 > iLimit) {
            throw new BufferUnderflowException();
        }
        byteBuffer.limit(i3);
        try {
            ByteBuffer byteBufferSlice = byteBuffer.slice();
            byteBufferSlice.order(byteBuffer.order());
            byteBuffer.position(i3);
            return byteBufferSlice;
        } finally {
            byteBuffer.limit(iLimit);
        }
    }

    private static ByteBuffer a(ByteBuffer byteBuffer, int i2, int i3) {
        if (i2 < 0) {
            throw new IllegalArgumentException("start: " + i2);
        }
        if (i3 < i2) {
            throw new IllegalArgumentException("end < start: " + i3 + " < " + i2);
        }
        int iCapacity = byteBuffer.capacity();
        if (i3 > byteBuffer.capacity()) {
            throw new IllegalArgumentException("end > capacity: " + i3 + " > " + iCapacity);
        }
        int iLimit = byteBuffer.limit();
        int iPosition = byteBuffer.position();
        try {
            byteBuffer.position(0);
            byteBuffer.limit(i3);
            byteBuffer.position(i2);
            ByteBuffer byteBufferSlice = byteBuffer.slice();
            byteBufferSlice.order(byteBuffer.order());
            return byteBufferSlice;
        } finally {
            byteBuffer.position(0);
            byteBuffer.limit(iLimit);
            byteBuffer.position(iPosition);
        }
    }

    public static Map<Integer, ByteBuffer> a(ByteBuffer byteBuffer) throws e {
        b(byteBuffer);
        ByteBuffer byteBufferA = a(byteBuffer, 8, byteBuffer.capacity() - 24);
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        int i2 = 0;
        while (byteBufferA.hasRemaining()) {
            i2++;
            if (byteBufferA.remaining() < 8) {
                throw new e("Insufficient data to read size of APK Signing Block entry #" + i2);
            }
            long j2 = byteBufferA.getLong();
            if (j2 < 4 || j2 > 2147483647L) {
                throw new e("APK Signing Block entry #" + i2 + " size out of range: " + j2);
            }
            int i3 = (int) j2;
            int iPosition = byteBufferA.position() + i3;
            if (i3 > byteBufferA.remaining()) {
                throw new e("APK Signing Block entry #" + i2 + " size out of range: " + i3 + ", available: " + byteBufferA.remaining());
            }
            linkedHashMap.put(Integer.valueOf(byteBufferA.getInt()), a(byteBufferA, i3 - 4));
            byteBufferA.position(iPosition);
        }
        return linkedHashMap;
    }

    public static long b(FileChannel fileChannel) {
        return a(fileChannel, a(fileChannel));
    }

    public static c<ByteBuffer, Long> b(FileChannel fileChannel, long j2) throws e, IOException {
        if (j2 < 32) {
            throw new e("APK too small for APK Signing Block. ZIP Central Directory offset: " + j2);
        }
        fileChannel.position(j2 - 24);
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(24);
        fileChannel.read(byteBufferAllocate);
        byteBufferAllocate.order(ByteOrder.LITTLE_ENDIAN);
        if (byteBufferAllocate.getLong(8) != 2334950737559900225L || byteBufferAllocate.getLong(16) != 3617552046287187010L) {
            throw new e("No APK Signing Block before ZIP Central Directory");
        }
        long j3 = byteBufferAllocate.getLong(0);
        if (j3 < byteBufferAllocate.capacity() || j3 > 2147483639) {
            throw new e("APK Signing Block size out of range: " + j3);
        }
        int i2 = (int) (8 + j3);
        long j4 = j2 - ((long) i2);
        if (j4 < 0) {
            throw new e("APK Signing Block offset out of range: " + j4);
        }
        fileChannel.position(j4);
        ByteBuffer byteBufferAllocate2 = ByteBuffer.allocate(i2);
        fileChannel.read(byteBufferAllocate2);
        byteBufferAllocate2.order(ByteOrder.LITTLE_ENDIAN);
        long j5 = byteBufferAllocate2.getLong(0);
        if (j5 == j3) {
            return c.a(byteBufferAllocate2, Long.valueOf(j4));
        }
        throw new e("APK Signing Block sizes in header and footer do not match: " + j5 + " vs " + j3);
    }

    private static void b(ByteBuffer byteBuffer) {
        if (byteBuffer.order() != ByteOrder.LITTLE_ENDIAN) {
            throw new IllegalArgumentException("ByteBuffer byte order must be little endian");
        }
    }

    public static c<ByteBuffer, Long> c(FileChannel fileChannel) {
        return b(fileChannel, b(fileChannel));
    }
}
