package com.bytedance.applog.convert.hume.readapk;

import java.io.IOException;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;
import java.util.LinkedHashMap;
import java.util.Map;
import okhttp3.internal.ws.WebSocketProtocol;

/* JADX INFO: loaded from: classes2.dex */
public final class ApkUtil {
    public static final int ANDROID_COMMON_PAGE_ALIGNMENT_BYTES = 4096;
    public static final int APK_SIGNATURE_SCHEME_V2_BLOCK_ID = 1896449818;
    public static final long APK_SIG_BLOCK_MAGIC_HI = 3617552046287187010L;
    public static final long APK_SIG_BLOCK_MAGIC_LO = 2334950737559900225L;
    private static final int APK_SIG_BLOCK_MIN_SIZE = 32;
    public static final String DEFAULT_CHARSET = "UTF-8";
    private static final int UINT16_MAX_VALUE = 65535;
    public static final int VERITY_PADDING_BLOCK_ID = 1114793335;
    private static final int ZIP_EOCD_COMMENT_LENGTH_FIELD_OFFSET = 20;
    private static final int ZIP_EOCD_REC_MIN_SIZE = 22;
    private static final int ZIP_EOCD_REC_SIG = 101010256;

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
                if (byteBufferAllocate.getInt(0) == ZIP_EOCD_REC_SIG) {
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

    public static long findCentralDirStartOffset(FileChannel fileChannel, long j) throws IOException {
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(4);
        byteBufferAllocate.order(ByteOrder.LITTLE_ENDIAN);
        fileChannel.position((fileChannel.size() - j) - 6);
        fileChannel.read(byteBufferAllocate);
        long j2 = byteBufferAllocate.getInt(0);
        if (j2 >= 0) {
            return j2;
        }
        fileChannel.read(byteBufferAllocate);
        byte[] bArr = new byte[8];
        for (int i = 4; i < 8; i++) {
            bArr[i] = byteBufferAllocate.get(7 - i);
        }
        return ByteBuffer.wrap(bArr).getLong();
    }

    public static Pair<ByteBuffer, Long> findApkSigningBlock(FileChannel fileChannel) throws SignatureNotFoundException, IOException {
        return findApkSigningBlock(fileChannel, findCentralDirStartOffset(fileChannel));
    }

    public static Pair<ByteBuffer, Long> findApkSigningBlock(FileChannel fileChannel, long j) throws SignatureNotFoundException, IOException {
        if (j < 32) {
            throw new SignatureNotFoundException("APK too small for APK Signing Block. ZIP Central Directory offset: " + j);
        }
        fileChannel.position(j - 24);
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(24);
        fileChannel.read(byteBufferAllocate);
        byteBufferAllocate.order(ByteOrder.LITTLE_ENDIAN);
        if (byteBufferAllocate.getLong(8) != 2334950737559900225L || byteBufferAllocate.getLong(16) != 3617552046287187010L) {
            throw new SignatureNotFoundException("No APK Signing Block before ZIP Central Directory");
        }
        long j2 = byteBufferAllocate.getLong(0);
        if (j2 < byteBufferAllocate.capacity() || j2 > 2147483639) {
            throw new SignatureNotFoundException("APK Signing Block size out of range: " + j2);
        }
        int i = (int) (8 + j2);
        long j3 = j - ((long) i);
        if (j3 < 0) {
            throw new SignatureNotFoundException("APK Signing Block offset out of range: " + j3);
        }
        fileChannel.position(j3);
        ByteBuffer byteBufferAllocate2 = ByteBuffer.allocate(i);
        fileChannel.read(byteBufferAllocate2);
        byteBufferAllocate2.order(ByteOrder.LITTLE_ENDIAN);
        long j4 = byteBufferAllocate2.getLong(0);
        if (j4 != j2) {
            throw new SignatureNotFoundException("APK Signing Block sizes in header and footer do not match: " + j4 + " vs " + j2);
        }
        return Pair.of(byteBufferAllocate2, Long.valueOf(j3));
    }

    public static Map<Integer, ByteBuffer> findIdValues(ByteBuffer byteBuffer) throws SignatureNotFoundException {
        checkByteOrderLittleEndian(byteBuffer);
        ByteBuffer byteBufferSliceFromTo = sliceFromTo(byteBuffer, 8, byteBuffer.capacity() - 24);
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

    private static ByteBuffer sliceFromTo(ByteBuffer byteBuffer, int i, int i2) {
        if (i < 0) {
            throw new IllegalArgumentException("start: " + i);
        }
        if (i2 < i) {
            throw new IllegalArgumentException("end < start: " + i2 + " < " + i);
        }
        int iCapacity = byteBuffer.capacity();
        if (i2 > byteBuffer.capacity()) {
            throw new IllegalArgumentException("end > capacity: " + i2 + " > " + iCapacity);
        }
        int iLimit = byteBuffer.limit();
        int iPosition = byteBuffer.position();
        try {
            byteBuffer.position(0);
            byteBuffer.limit(i2);
            byteBuffer.position(i);
            ByteBuffer byteBufferSlice = byteBuffer.slice();
            byteBufferSlice.order(byteBuffer.order());
            return byteBufferSlice;
        } finally {
            byteBuffer.position(0);
            byteBuffer.limit(iLimit);
            byteBuffer.position(iPosition);
        }
    }

    private static ByteBuffer getByteBuffer(ByteBuffer byteBuffer, int i) throws BufferUnderflowException {
        if (i < 0) {
            throw new IllegalArgumentException("size: " + i);
        }
        int iLimit = byteBuffer.limit();
        int iPosition = byteBuffer.position();
        int i2 = i + iPosition;
        if (i2 < iPosition || i2 > iLimit) {
            throw new BufferUnderflowException();
        }
        byteBuffer.limit(i2);
        try {
            ByteBuffer byteBufferSlice = byteBuffer.slice();
            byteBufferSlice.order(byteBuffer.order());
            byteBuffer.position(i2);
            return byteBufferSlice;
        } finally {
            byteBuffer.limit(iLimit);
        }
    }

    private static void checkByteOrderLittleEndian(ByteBuffer byteBuffer) {
        if (byteBuffer.order() != ByteOrder.LITTLE_ENDIAN) {
            throw new IllegalArgumentException("ByteBuffer byte order must be little endian");
        }
    }
}
