package com.bytedance.applog.convert.hume.readapk;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Arrays;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public final class PayloadReader {
    private PayloadReader() {
    }

    public static String[] getString(File file, int[] iArr) {
        byte[][] bArr = get(file, iArr);
        if (bArr == null) {
            return null;
        }
        String[] strArr = new String[iArr.length];
        for (int i = 0; i < iArr.length; i++) {
            try {
                byte[] bArr2 = bArr[i];
                if (bArr2 != null) {
                    strArr[i] = new String(bArr2, "UTF-8");
                } else {
                    strArr[i] = "";
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return strArr;
    }

    public static byte[][] get(File file, int[] iArr) {
        Map<Integer, ByteBuffer> all = getAll(file);
        if (all == null || iArr.length <= 0) {
            return null;
        }
        byte[][] bArr = new byte[iArr.length][];
        for (int i = 0; i < iArr.length; i++) {
            ByteBuffer byteBuffer = all.get(Integer.valueOf(iArr[i]));
            if (byteBuffer != null) {
                bArr[i] = getBytes(byteBuffer);
            }
        }
        return bArr;
    }

    private static byte[] getBytes(ByteBuffer byteBuffer) {
        byte[] bArrArray = byteBuffer.array();
        int iArrayOffset = byteBuffer.arrayOffset();
        return Arrays.copyOfRange(bArrArray, byteBuffer.position() + iArrayOffset, iArrayOffset + byteBuffer.limit());
    }

    private static Map<Integer, ByteBuffer> getAll(File file) {
        FileChannel channel;
        RandomAccessFile randomAccessFile;
        Map<Integer, ByteBuffer> mapFindIdValues = null;
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
            } catch (SignatureNotFoundException | IOException unused2) {
            }
            try {
                mapFindIdValues = ApkUtil.findIdValues(ApkUtil.findApkSigningBlock(channel).getFirst());
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
                return mapFindIdValues;
            } catch (Throwable th2) {
                th = th2;
                if (channel != null) {
                    try {
                        channel.close();
                    } catch (IOException unused6) {
                    }
                }
                if (randomAccessFile != null) {
                    try {
                        randomAccessFile.close();
                        throw th;
                    } catch (IOException unused7) {
                        throw th;
                    }
                }
                throw th;
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
        return mapFindIdValues;
    }
}
