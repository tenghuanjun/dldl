package com.baidu.appwalle;

import java.io.IOException;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;
import java.util.LinkedHashMap;
import java.util.Map;
import okhttp3.internal.ws.WebSocketProtocol;

/* JADX INFO: loaded from: classes2.dex */
final class ApkUtil {
    private ApkUtil() {
    }

    public static long getCommentLength(FileChannel fileChannel) throws IOException {
        long size = fileChannel.size();
        if (size < 22) {
            throw new IOException("APK too small for ZIP End of Central Directory (EOCD) record");
        }
        long j = size - 22;
        long jMin = Math.min(j, WebSocketProtocol.PAYLOAD_SHORT_MAX);
        int i = 0;
        while (true) {
            long j2 = i;
            if (j2 <= jMin) {
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
                        return s;
                    }
                }
                i++;
            } else {
                throw new IOException("ZIP End of Central Directory (EOCD) record not found");
            }
        }
    }

    public static long findCentralDirStartOffset(FileChannel fileChannel) throws IOException {
        return findCentralDirStartOffset(fileChannel, getCommentLength(fileChannel));
    }

    public static long findCentralDirStartOffset(FileChannel fileChannel, long commentLength) throws IOException {
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(4);
        byteBufferAllocate.order(ByteOrder.LITTLE_ENDIAN);
        fileChannel.position((fileChannel.size() - commentLength) - 6);
        fileChannel.read(byteBufferAllocate);
        return byteBufferAllocate.getInt(0);
    }

    public static Pair<ByteBuffer, Long> findApkSigningBlock(FileChannel fileChannel) throws SignatureNotFoundException, IOException {
        return findApkSigningBlock(fileChannel, findCentralDirStartOffset(fileChannel));
    }

    public static Pair<ByteBuffer, Long> findApkSigningBlock(FileChannel fileChannel, long centralDirOffset) throws SignatureNotFoundException, IOException {
        if (centralDirOffset < 32) {
            throw new SignatureNotFoundException("APK too small for APK Signing Block. ZIP Central Directory offset: " + centralDirOffset);
        }
        fileChannel.position(centralDirOffset - 24);
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(24);
        fileChannel.read(byteBufferAllocate);
        byteBufferAllocate.order(ByteOrder.LITTLE_ENDIAN);
        if (byteBufferAllocate.getLong(8) == 2334950737559900225L && byteBufferAllocate.getLong(16) == 3617552046287187010L) {
            long j = byteBufferAllocate.getLong(0);
            if (j < byteBufferAllocate.capacity() || j > 2147483639) {
                throw new SignatureNotFoundException("APK Signing Block size out of range: " + j);
            }
            int i = (int) (8 + j);
            long j2 = centralDirOffset - ((long) i);
            if (j2 < 0) {
                throw new SignatureNotFoundException("APK Signing Block offset out of range: " + j2);
            }
            fileChannel.position(j2);
            ByteBuffer byteBufferAllocate2 = ByteBuffer.allocate(i);
            fileChannel.read(byteBufferAllocate2);
            byteBufferAllocate2.order(ByteOrder.LITTLE_ENDIAN);
            long j3 = byteBufferAllocate2.getLong(0);
            if (j3 != j) {
                throw new SignatureNotFoundException("APK Signing Block sizes in header and footer do not match: " + j3 + " vs " + j);
            }
            return Pair.of(byteBufferAllocate2, Long.valueOf(j2));
        }
        throw new SignatureNotFoundException("No APK Signing Block before ZIP Central Directory");
    }

    public static Map<Integer, ByteBuffer> findIdValues(ByteBuffer apkSigningBlock) throws SignatureNotFoundException {
        checkByteOrderLittleEndian(apkSigningBlock);
        ByteBuffer byteBufferSliceFromTo = sliceFromTo(apkSigningBlock, 8, apkSigningBlock.capacity() - 24);
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        int i = 0;
        while (byteBufferSliceFromTo.hasRemaining()) {
            i++;
            if (byteBufferSliceFromTo.remaining() < 8) {
                throw new SignatureNotFoundException("Insufficient data to read size of APK Signing Block entry #" + i);
            }
            long j = byteBufferSliceFromTo.getLong();
            if (j < 4 || j > 2147483647L) {
                throw new SignatureNotFoundException("APK Signing Block entry #" + i + " size out of range: " + j);
            }
            int i2 = (int) j;
            int iPosition = byteBufferSliceFromTo.position() + i2;
            if (i2 > byteBufferSliceFromTo.remaining()) {
                throw new SignatureNotFoundException("APK Signing Block entry #" + i + " size out of range: " + i2 + ", available: " + byteBufferSliceFromTo.remaining());
            }
            linkedHashMap.put(Integer.valueOf(byteBufferSliceFromTo.getInt()), getByteBuffer(byteBufferSliceFromTo, i2 - 4));
            byteBufferSliceFromTo.position(iPosition);
        }
        return linkedHashMap;
    }

    private static ByteBuffer sliceFromTo(ByteBuffer source, int start, int end) {
        if (start < 0) {
            throw new IllegalArgumentException("start: " + start);
        }
        if (end < start) {
            throw new IllegalArgumentException("end < start: " + end + " < " + start);
        }
        int iCapacity = source.capacity();
        if (end > source.capacity()) {
            throw new IllegalArgumentException("end > capacity: " + end + " > " + iCapacity);
        }
        int iLimit = source.limit();
        int iPosition = source.position();
        try {
            source.position(0);
            source.limit(end);
            source.position(start);
            ByteBuffer byteBufferSlice = source.slice();
            byteBufferSlice.order(source.order());
            return byteBufferSlice;
        } finally {
            source.position(0);
            source.limit(iLimit);
            source.position(iPosition);
        }
    }

    private static ByteBuffer getByteBuffer(ByteBuffer source, int size) throws BufferUnderflowException {
        if (size < 0) {
            throw new IllegalArgumentException("size: " + size);
        }
        int iLimit = source.limit();
        int iPosition = source.position();
        int i = size + iPosition;
        if (i >= iPosition && i <= iLimit) {
            source.limit(i);
            try {
                ByteBuffer byteBufferSlice = source.slice();
                byteBufferSlice.order(source.order());
                source.position(i);
                return byteBufferSlice;
            } finally {
                source.limit(iLimit);
            }
        }
        throw new BufferUnderflowException();
    }

    private static void checkByteOrderLittleEndian(ByteBuffer buffer) {
        if (buffer.order() != ByteOrder.LITTLE_ENDIAN) {
            throw new IllegalArgumentException("ByteBuffer byte order must be little endian");
        }
    }
}
