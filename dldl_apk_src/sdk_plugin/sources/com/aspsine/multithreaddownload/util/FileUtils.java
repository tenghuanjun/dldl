package com.aspsine.multithreaddownload.util;

import android.content.Context;
import android.os.Environment;
import com.sqwan.bugless.util.FileUtil;
import java.io.File;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class FileUtils {
    private static final String DOWNLOAD_DIR = "download";

    public static final File getDefaultDownloadDir(Context context) {
        if (Environment.getExternalStorageState().equals("mounted")) {
            return new File(context.getExternalCacheDir(), DOWNLOAD_DIR);
        }
        return new File(context.getCacheDir(), DOWNLOAD_DIR);
    }

    public static boolean isSDMounted() {
        return Environment.getExternalStorageState().equals("mounted");
    }

    public static final String getPrefix(String str) {
        return str.substring(0, str.lastIndexOf(FileUtil.FILE_EXTENSION_SEPARATOR));
    }

    public static final String getSuffix(String str) {
        return str.substring(str.lastIndexOf(FileUtil.FILE_EXTENSION_SEPARATOR) + 1);
    }

    /* JADX WARN: Code restructure failed: missing block: B:27:0x0045, code lost:
    
        if (r0 == 0) goto L50;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0056, code lost:
    
        if (r0 == 0) goto L50;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0 */
    /* JADX WARN: Type inference failed for: r0v1 */
    /* JADX WARN: Type inference failed for: r0v10 */
    /* JADX WARN: Type inference failed for: r0v11 */
    /* JADX WARN: Type inference failed for: r0v12 */
    /* JADX WARN: Type inference failed for: r0v15 */
    /* JADX WARN: Type inference failed for: r0v16 */
    /* JADX WARN: Type inference failed for: r0v17 */
    /* JADX WARN: Type inference failed for: r0v18 */
    /* JADX WARN: Type inference failed for: r0v19 */
    /* JADX WARN: Type inference failed for: r0v2, types: [java.io.FileInputStream] */
    /* JADX WARN: Type inference failed for: r0v20 */
    /* JADX WARN: Type inference failed for: r0v21 */
    /* JADX WARN: Type inference failed for: r0v22 */
    /* JADX WARN: Type inference failed for: r0v3 */
    /* JADX WARN: Type inference failed for: r0v4 */
    /* JADX WARN: Type inference failed for: r0v5, types: [java.io.FileInputStream] */
    /* JADX WARN: Type inference failed for: r0v6 */
    /* JADX WARN: Type inference failed for: r0v7 */
    /* JADX WARN: Type inference failed for: r0v8 */
    /* JADX WARN: Type inference failed for: r0v9 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String fileMd5(java.io.File r5) throws java.lang.Throwable {
        /*
            r0 = 0
            if (r5 != 0) goto L4
            return r0
        L4:
            java.lang.StringBuffer r1 = new java.lang.StringBuffer
            r1.<init>()
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L3f java.io.IOException -> L41 java.security.NoSuchAlgorithmException -> L4b java.io.FileNotFoundException -> L52
            r2.<init>(r5)     // Catch: java.lang.Throwable -> L3f java.io.IOException -> L41 java.security.NoSuchAlgorithmException -> L4b java.io.FileNotFoundException -> L52
            r5 = 8192(0x2000, float:1.148E-41)
            byte[] r5 = new byte[r5]     // Catch: java.lang.Throwable -> L33 java.io.IOException -> L36 java.security.NoSuchAlgorithmException -> L39 java.io.FileNotFoundException -> L3c
            java.lang.String r0 = "MD5"
            java.security.MessageDigest r0 = java.security.MessageDigest.getInstance(r0)     // Catch: java.lang.Throwable -> L33 java.io.IOException -> L36 java.security.NoSuchAlgorithmException -> L39 java.io.FileNotFoundException -> L3c
        L18:
            int r3 = r2.read(r5)     // Catch: java.lang.Throwable -> L33 java.io.IOException -> L36 java.security.NoSuchAlgorithmException -> L39 java.io.FileNotFoundException -> L3c
            r4 = -1
            if (r3 == r4) goto L24
            r4 = 0
            r0.update(r5, r4, r3)     // Catch: java.lang.Throwable -> L33 java.io.IOException -> L36 java.security.NoSuchAlgorithmException -> L39 java.io.FileNotFoundException -> L3c
            goto L18
        L24:
            byte[] r5 = r0.digest()     // Catch: java.lang.Throwable -> L33 java.io.IOException -> L36 java.security.NoSuchAlgorithmException -> L39 java.io.FileNotFoundException -> L3c
            java.lang.String r5 = bytesToHexString(r5)     // Catch: java.lang.Throwable -> L33 java.io.IOException -> L36 java.security.NoSuchAlgorithmException -> L39 java.io.FileNotFoundException -> L3c
            r1.append(r5)     // Catch: java.lang.Throwable -> L33 java.io.IOException -> L36 java.security.NoSuchAlgorithmException -> L39 java.io.FileNotFoundException -> L3c
            r2.close()     // Catch: java.io.IOException -> L59
            goto L59
        L33:
            r5 = move-exception
            r0 = r2
            goto L5e
        L36:
            r5 = move-exception
            r0 = r2
            goto L42
        L39:
            r5 = move-exception
            r0 = r2
            goto L4c
        L3c:
            r5 = move-exception
            r0 = r2
            goto L53
        L3f:
            r5 = move-exception
            goto L5e
        L41:
            r5 = move-exception
        L42:
            r5.printStackTrace()     // Catch: java.lang.Throwable -> L3f
            if (r0 == 0) goto L59
        L47:
            r0.close()     // Catch: java.io.IOException -> L59
            goto L59
        L4b:
            r5 = move-exception
        L4c:
            r5.printStackTrace()     // Catch: java.lang.Throwable -> L3f
            if (r0 == 0) goto L59
            goto L47
        L52:
            r5 = move-exception
        L53:
            r5.printStackTrace()     // Catch: java.lang.Throwable -> L3f
            if (r0 == 0) goto L59
            goto L47
        L59:
            java.lang.String r5 = r1.toString()
            return r5
        L5e:
            if (r0 == 0) goto L63
            r0.close()     // Catch: java.io.IOException -> L63
        L63:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.aspsine.multithreaddownload.util.FileUtils.fileMd5(java.io.File):java.lang.String");
    }

    public static String bytesToHexString(byte[] bArr) {
        if (bArr == null) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (byte b : bArr) {
            int i = b & 255;
            if (i < 16) {
                stringBuffer.append("0");
            }
            stringBuffer.append(Integer.toHexString(i));
        }
        return stringBuffer.toString();
    }
}
