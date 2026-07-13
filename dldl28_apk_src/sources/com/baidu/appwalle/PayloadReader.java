package com.baidu.appwalle;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public final class PayloadReader {
    private PayloadReader() {
    }

    public static String getString(File apkFile, int id) throws Throwable {
        byte[] bArr = get(apkFile, id);
        if (bArr == null) {
            return null;
        }
        try {
            return new String(bArr, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static byte[] get(File apkFile, int id) throws Throwable {
        ByteBuffer byteBuffer;
        Map<Integer, ByteBuffer> all = getAll(apkFile);
        if (all == null || (byteBuffer = all.get(Integer.valueOf(id))) == null) {
            return null;
        }
        return getBytes(byteBuffer);
    }

    private static byte[] getBytes(ByteBuffer byteBuffer) {
        byte[] bArrArray = byteBuffer.array();
        int iArrayOffset = byteBuffer.arrayOffset();
        return Arrays.copyOfRange(bArrArray, byteBuffer.position() + iArrayOffset, iArrayOffset + byteBuffer.limit());
    }

    /* JADX WARN: Removed duplicated region for block: B:59:0x006b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:69:0x007b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:74:? A[Catch: SignatureNotFoundException -> 0x006f, SYNTHETIC, TRY_LEAVE, TryCatch #5 {SignatureNotFoundException -> 0x006f, blocks: (B:7:0x001e, B:11:0x002a, B:14:0x0030, B:15:0x0034, B:10:0x0023, B:29:0x0050, B:34:0x005e, B:37:0x0063, B:32:0x0055, B:41:0x006b, B:48:0x007b, B:52:0x0087, B:51:0x0080, B:46:0x0072), top: B:62:0x0003, inners: #2, #3, #6, #8, #9, #10 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static java.util.Map<java.lang.Integer, java.nio.ByteBuffer> getAll(java.io.File r4) throws java.lang.Throwable {
        /*
            java.lang.String r0 = "异常"
            r1 = 0
            java.io.RandomAccessFile r2 = new java.io.RandomAccessFile     // Catch: java.lang.Throwable -> L40 java.io.IOException -> L44
            java.lang.String r3 = "r"
            r2.<init>(r4, r3)     // Catch: java.lang.Throwable -> L40 java.io.IOException -> L44
            java.nio.channels.FileChannel r4 = r2.getChannel()     // Catch: java.lang.Throwable -> L3a java.io.IOException -> L3d
            com.baidu.appwalle.Pair r3 = com.baidu.appwalle.ApkUtil.findApkSigningBlock(r4)     // Catch: java.io.IOException -> L38 java.lang.Throwable -> L68
            java.lang.Object r3 = r3.getFirst()     // Catch: java.io.IOException -> L38 java.lang.Throwable -> L68
            java.nio.ByteBuffer r3 = (java.nio.ByteBuffer) r3     // Catch: java.io.IOException -> L38 java.lang.Throwable -> L68
            java.util.Map r1 = com.baidu.appwalle.ApkUtil.findIdValues(r3)     // Catch: java.io.IOException -> L38 java.lang.Throwable -> L68
            if (r4 == 0) goto L2a
            r4.close()     // Catch: java.io.IOException -> L22 com.baidu.appwalle.SignatureNotFoundException -> L6f
            goto L2a
        L22:
            r4 = move-exception
            java.lang.String r4 = r4.getMessage()     // Catch: com.baidu.appwalle.SignatureNotFoundException -> L6f
            android.util.Log.d(r0, r4)     // Catch: com.baidu.appwalle.SignatureNotFoundException -> L6f
        L2a:
            r2.close()     // Catch: java.io.IOException -> L2f com.baidu.appwalle.SignatureNotFoundException -> L6f
            goto L8f
        L2f:
            r4 = move-exception
            java.lang.String r4 = r4.getMessage()     // Catch: com.baidu.appwalle.SignatureNotFoundException -> L6f
        L34:
            android.util.Log.d(r0, r4)     // Catch: com.baidu.appwalle.SignatureNotFoundException -> L6f
            goto L8f
        L38:
            r3 = move-exception
            goto L47
        L3a:
            r3 = move-exception
            r4 = r1
            goto L69
        L3d:
            r3 = move-exception
            r4 = r1
            goto L47
        L40:
            r3 = move-exception
            r4 = r1
            r2 = r4
            goto L69
        L44:
            r3 = move-exception
            r4 = r1
            r2 = r4
        L47:
            java.lang.String r3 = r3.getMessage()     // Catch: java.lang.Throwable -> L68
            android.util.Log.d(r0, r3)     // Catch: java.lang.Throwable -> L68
            if (r4 == 0) goto L5c
            r4.close()     // Catch: java.io.IOException -> L54 com.baidu.appwalle.SignatureNotFoundException -> L6f
            goto L5c
        L54:
            r4 = move-exception
            java.lang.String r4 = r4.getMessage()     // Catch: com.baidu.appwalle.SignatureNotFoundException -> L6f
            android.util.Log.d(r0, r4)     // Catch: com.baidu.appwalle.SignatureNotFoundException -> L6f
        L5c:
            if (r2 == 0) goto L8f
            r2.close()     // Catch: java.io.IOException -> L62 com.baidu.appwalle.SignatureNotFoundException -> L6f
            goto L8f
        L62:
            r4 = move-exception
            java.lang.String r4 = r4.getMessage()     // Catch: com.baidu.appwalle.SignatureNotFoundException -> L6f
            goto L34
        L68:
            r3 = move-exception
        L69:
            if (r4 == 0) goto L79
            r4.close()     // Catch: com.baidu.appwalle.SignatureNotFoundException -> L6f java.io.IOException -> L71
            goto L79
        L6f:
            r4 = move-exception
            goto L88
        L71:
            r4 = move-exception
            java.lang.String r4 = r4.getMessage()     // Catch: com.baidu.appwalle.SignatureNotFoundException -> L6f
            android.util.Log.d(r0, r4)     // Catch: com.baidu.appwalle.SignatureNotFoundException -> L6f
        L79:
            if (r2 == 0) goto L87
            r2.close()     // Catch: com.baidu.appwalle.SignatureNotFoundException -> L6f java.io.IOException -> L7f
            goto L87
        L7f:
            r4 = move-exception
            java.lang.String r4 = r4.getMessage()     // Catch: com.baidu.appwalle.SignatureNotFoundException -> L6f
            android.util.Log.d(r0, r4)     // Catch: com.baidu.appwalle.SignatureNotFoundException -> L6f
        L87:
            throw r3     // Catch: com.baidu.appwalle.SignatureNotFoundException -> L6f
        L88:
            java.lang.String r4 = r4.getMessage()
            android.util.Log.d(r0, r4)
        L8f:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.appwalle.PayloadReader.getAll(java.io.File):java.util.Map");
    }
}
