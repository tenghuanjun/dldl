package com.bytedance.hume.readapk;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Arrays;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public final class d {
    private d() {
    }

    private static Map<Integer, ByteBuffer> a(File file) {
        FileChannel channel;
        RandomAccessFile randomAccessFile;
        Map<Integer, ByteBuffer> mapA = null;
        try {
            try {
                randomAccessFile = new RandomAccessFile(file, "r");
                try {
                    channel = randomAccessFile.getChannel();
                } catch (IOException unused) {
                    channel = null;
                } catch (Throwable th) {
                    th = th;
                    channel = null;
                }
            } catch (e | IOException unused2) {
            }
            try {
                mapA = a.a(a.c(channel).a());
                if (channel != null) {
                    try {
                        channel.close();
                    } catch (IOException unused3) {
                    }
                }
            } catch (IOException unused4) {
                if (channel != null) {
                    try {
                        channel.close();
                    } catch (IOException unused5) {
                    }
                }
                if (randomAccessFile != null) {
                }
                return mapA;
            } catch (Throwable th2) {
                th = th2;
                if (channel != null) {
                    try {
                        channel.close();
                    } catch (IOException unused6) {
                    }
                }
                if (randomAccessFile == null) {
                    throw th;
                }
                try {
                    randomAccessFile.close();
                    throw th;
                } catch (IOException unused7) {
                    throw th;
                }
            }
        } catch (IOException unused8) {
            channel = null;
            randomAccessFile = null;
        } catch (Throwable th3) {
            th = th3;
            channel = null;
            randomAccessFile = null;
        }
        randomAccessFile.close();
        return mapA;
    }

    private static byte[] a(ByteBuffer byteBuffer) {
        byte[] bArrArray = byteBuffer.array();
        int iArrayOffset = byteBuffer.arrayOffset();
        return Arrays.copyOfRange(bArrArray, byteBuffer.position() + iArrayOffset, iArrayOffset + byteBuffer.limit());
    }

    public static String[] a(File file, int[] iArr) {
        byte[][] bArrB = b(file, iArr);
        if (bArrB == null) {
            return null;
        }
        String[] strArr = new String[iArr.length];
        for (int i = 0; i < iArr.length; i++) {
            try {
                byte[] bArr = bArrB[i];
                if (bArr != null) {
                    strArr[i] = new String(bArr, "UTF-8");
                } else {
                    strArr[i] = "";
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return strArr;
    }

    public static byte[][] b(File file, int[] iArr) {
        Map<Integer, ByteBuffer> mapA = a(file);
        if (mapA == null || iArr.length <= 0) {
            return null;
        }
        byte[][] bArr = new byte[iArr.length][];
        for (int i = 0; i < iArr.length; i++) {
            ByteBuffer byteBuffer = mapA.get(Integer.valueOf(iArr[i]));
            if (byteBuffer != null) {
                bArr[i] = a(byteBuffer);
            }
        }
        return bArr;
    }
}
