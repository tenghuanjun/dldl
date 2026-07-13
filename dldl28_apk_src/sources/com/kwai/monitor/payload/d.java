package com.kwai.monitor.payload;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX INFO: compiled from: PayloadReader.java */
/* JADX INFO: loaded from: classes3.dex */
public final class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Logger f497a = Logger.getLogger("PayloadReader");

    public static String a(File file, int[] iArr) {
        try {
            Map<Integer, ByteBuffer> mapA = a(file);
            for (int i : iArr) {
                ByteBuffer byteBuffer = mapA.get(Integer.valueOf(i));
                if (byteBuffer != null) {
                    try {
                        byte[] bArrArray = byteBuffer.array();
                        int iArrayOffset = byteBuffer.arrayOffset();
                        String str = new String(Arrays.copyOfRange(bArrArray, byteBuffer.position() + iArrayOffset, iArrayOffset + byteBuffer.limit()), "UTF-8");
                        if (!str.isEmpty() && str.contains("kwaiChannel")) {
                            return str;
                        }
                    } catch (UnsupportedEncodingException e) {
                        f497a.log(Level.SEVERE, e.getMessage());
                    }
                }
            }
            return null;
        } catch (e | IOException e2) {
            Logger logger = f497a;
            logger.log(Level.WARNING, "read v2/v3 payload failed, try read comment");
            logger.log(Level.WARNING, e2.getMessage());
            try {
                return b(file);
            } catch (IOException unused) {
                f497a.log(Level.SEVERE, "read v1 payload failed");
                f497a.log(Level.SEVERE, e2.getMessage());
                return null;
            }
        }
    }

    public static String b(File file) throws Throwable {
        RandomAccessFile randomAccessFile = null;
        try {
            RandomAccessFile randomAccessFile2 = new RandomAccessFile(file, "r");
            try {
                long length = randomAccessFile2.length();
                byte[] bArr = new byte[2];
                long j = length - 2;
                randomAccessFile2.seek(j);
                randomAccessFile2.readFully(bArr);
                short sA = a(bArr);
                if (sA == 0) {
                    randomAccessFile2.close();
                    return null;
                }
                long j2 = sA;
                randomAccessFile2.seek(j - j2);
                randomAccessFile2.read(bArr);
                if (a(bArr) != sA) {
                    randomAccessFile2.close();
                    return null;
                }
                byte[] bArr2 = new byte[sA - 2];
                randomAccessFile2.seek(length - j2);
                randomAccessFile2.readFully(bArr2);
                try {
                    String str = new String(bArr2, "UTF-8");
                    randomAccessFile2.close();
                    return str;
                } catch (UnsupportedEncodingException e) {
                    f497a.log(Level.SEVERE, e.getMessage());
                    randomAccessFile2.close();
                    return null;
                }
            } catch (Throwable th) {
                th = th;
                randomAccessFile = randomAccessFile2;
                if (randomAccessFile != null) {
                    randomAccessFile.close();
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static Map<Integer, ByteBuffer> a(File file) throws Throwable {
        RandomAccessFile randomAccessFile;
        try {
            randomAccessFile = new RandomAccessFile(file, "r");
            try {
                Map<Integer, ByteBuffer> mapA = a.a(a.a(randomAccessFile.getChannel()).f496a);
                LinkedHashMap linkedHashMap = (LinkedHashMap) mapA;
                ByteBuffer byteBuffer = (ByteBuffer) linkedHashMap.get(1896449818);
                ByteBuffer byteBuffer2 = (ByteBuffer) linkedHashMap.get(-262969152);
                if (byteBuffer == null && byteBuffer2 == null) {
                    throw new e("No APK Signature Scheme v2/v3 block in APK Signing Block");
                }
                randomAccessFile.close();
                return mapA;
            } catch (Throwable th) {
                th = th;
                if (randomAccessFile != null) {
                    randomAccessFile.close();
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            randomAccessFile = null;
        }
    }

    public static short a(byte[] bArr) {
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(2);
        byteBufferAllocate.order(ByteOrder.LITTLE_ENDIAN);
        byteBufferAllocate.put(bArr[0]);
        byteBufferAllocate.put(bArr[1]);
        return byteBufferAllocate.getShort(0);
    }
}
