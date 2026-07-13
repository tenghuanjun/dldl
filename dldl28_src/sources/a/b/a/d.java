package a.b.a;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX INFO: compiled from: PayloadReader.java */
/* JADX INFO: loaded from: classes.dex */
public final class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Logger f140a = Logger.getLogger("PayloadReader");

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
                        if (!str.isEmpty() && str.contains("kwaiCallback")) {
                            return str;
                        }
                    } catch (UnsupportedEncodingException e) {
                        f140a.log(Level.SEVERE, e.getMessage());
                    }
                }
            }
            return null;
        } catch (e | IOException e2) {
            Logger logger = f140a;
            logger.log(Level.WARNING, "read v2/v3 payload failed");
            logger.log(Level.WARNING, e2.getMessage());
            return null;
        }
    }

    public static Map<Integer, ByteBuffer> a(File file) throws Throwable {
        RandomAccessFile randomAccessFile;
        try {
            randomAccessFile = new RandomAccessFile(file, "r");
            try {
                Map<Integer, ByteBuffer> mapA = a.a(a.a(randomAccessFile.getChannel()).f139a);
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
}
