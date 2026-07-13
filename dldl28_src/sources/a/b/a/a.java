package a.b.a;

import java.io.IOException;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;
import java.util.LinkedHashMap;
import java.util.Map;
import okhttp3.internal.ws.WebSocketProtocol;

/* JADX INFO: compiled from: ApkUtil.java */
/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final int[] f137a = {1897018457, 1897018458, 1897018459};

    public static c<ByteBuffer, Long> a(FileChannel fileChannel) throws e, IOException {
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
            byteBufferAllocate.order(ByteOrder.LITTLE_ENDIAN);
            if (byteBufferAllocate.getInt(0) == 101010256) {
                ByteBuffer byteBufferAllocate2 = ByteBuffer.allocate(2);
                fileChannel.position(j3 + 20);
                fileChannel.read(byteBufferAllocate2);
                byteBufferAllocate2.order(ByteOrder.LITTLE_ENDIAN);
                short s = byteBufferAllocate2.getShort(0);
                if (s == i) {
                    ByteBuffer byteBufferAllocate3 = ByteBuffer.allocate(4);
                    byteBufferAllocate3.order(ByteOrder.LITTLE_ENDIAN);
                    fileChannel.position((fileChannel.size() - ((long) s)) - 6);
                    fileChannel.read(byteBufferAllocate3);
                    long j4 = byteBufferAllocate3.getInt(0);
                    if (j4 <= 0) {
                        j4 &= 4294967295L;
                    }
                    if (j4 < 32) {
                        throw new e("APK too small for APK Signing Block. ZIP Central Directory offset: " + j4);
                    }
                    fileChannel.position(j4 - 24);
                    ByteBuffer byteBufferAllocate4 = ByteBuffer.allocate(24);
                    fileChannel.read(byteBufferAllocate4);
                    byteBufferAllocate4.order(ByteOrder.LITTLE_ENDIAN);
                    if (byteBufferAllocate4.getLong(8) != 2334950737559900225L || byteBufferAllocate4.getLong(16) != 3617552046287187010L) {
                        throw new e("No APK Signing Block before ZIP Central Directory");
                    }
                    long j5 = byteBufferAllocate4.getLong(0);
                    if (j5 < byteBufferAllocate4.capacity() || j5 > 2147483639) {
                        throw new e("APK Signing Block size out of range: " + j5);
                    }
                    int i2 = (int) (8 + j5);
                    long j6 = j4 - ((long) i2);
                    if (j6 < 0) {
                        throw new e("APK Signing Block offset out of range: " + j6);
                    }
                    fileChannel.position(j6);
                    ByteBuffer byteBufferAllocate5 = ByteBuffer.allocate(i2);
                    fileChannel.read(byteBufferAllocate5);
                    byteBufferAllocate5.order(ByteOrder.LITTLE_ENDIAN);
                    long j7 = byteBufferAllocate5.getLong(0);
                    if (j7 == j5) {
                        return new c<>(byteBufferAllocate5, Long.valueOf(j6));
                    }
                    throw new e("APK Signing Block sizes in header and footer do not match: " + j7 + " vs " + j5);
                }
            }
            i++;
        }
    }

    public static ByteBuffer a(ByteBuffer byteBuffer, int i, int i2) {
        if (i < 0) {
            throw new IllegalArgumentException("start: " + i);
        }
        if (i2 >= i) {
            int iCapacity = byteBuffer.capacity();
            if (i2 <= byteBuffer.capacity()) {
                int iLimit = byteBuffer.limit();
                int iPosition = byteBuffer.position();
                try {
                    ByteBuffer byteBufferSlice = byteBuffer.slice();
                    byteBufferSlice.order(byteBuffer.order());
                    return byteBufferSlice;
                } finally {
                }
            }
            throw new IllegalArgumentException("end > capacity: " + i2 + " > " + iCapacity);
        }
        throw new IllegalArgumentException("end < start: " + i2 + " < " + i);
    }

    public static ByteBuffer a(ByteBuffer byteBuffer, int i) {
        if (i >= 0) {
            int iLimit = byteBuffer.limit();
            int iPosition = byteBuffer.position();
            int i2 = i + iPosition;
            if (i2 >= iPosition && i2 <= iLimit) {
                try {
                    ByteBuffer byteBufferSlice = byteBuffer.slice();
                    byteBufferSlice.order(byteBuffer.order());
                    return byteBufferSlice;
                } finally {
                }
            }
            throw new BufferUnderflowException();
        }
        throw new IllegalArgumentException("size: " + i);
    }

    public static Map<Integer, ByteBuffer> a(ByteBuffer byteBuffer) throws e {
        if (byteBuffer.order() == ByteOrder.LITTLE_ENDIAN) {
            ByteBuffer byteBufferA = a(byteBuffer, 8, byteBuffer.capacity() - 24);
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            int i = 0;
            while (byteBufferA.hasRemaining()) {
                i++;
                if (byteBufferA.remaining() >= 8) {
                    long j = byteBufferA.getLong();
                    if (j >= 4 && j <= 2147483647L) {
                        int i2 = (int) j;
                        int iPosition = byteBufferA.position() + i2;
                        if (i2 <= byteBufferA.remaining()) {
                            linkedHashMap.put(Integer.valueOf(byteBufferA.getInt()), a(byteBufferA, i2 - 4));
                        } else {
                            throw new e("APK Signing Block entry #" + i + " size out of range: " + i2 + ", available: " + byteBufferA.remaining());
                        }
                    } else {
                        throw new e("APK Signing Block entry #" + i + " size out of range: " + j);
                    }
                } else {
                    throw new e("Insufficient data to read size of APK Signing Block entry #" + i);
                }
            }
            return linkedHashMap;
        }
        throw new IllegalArgumentException("ByteBuffer byte order must be little endian");
    }
}
