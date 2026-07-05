package com.duowan.live.one.util;

import com.duowan.live.one.module.uploadLog.FeedBackConstants;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class Md5Util {
    public static String encryptMD5(String str) throws Exception {
        return bytesToHexString(MessageDigest.getInstance(FeedBackConstants.KEY_LOG_MD5).digest(StringToBytes(str)));
    }

    public static String bytesToHexString(byte[] bArr) throws UnsupportedEncodingException {
        StringBuilder sb = new StringBuilder();
        if (bArr != null) {
            for (byte b : bArr) {
                sb.append(Integer.toString((b & 255) + 256, 16).substring(1));
            }
        }
        return sb.toString();
    }

    public static byte[] StringToBytes(String str) throws UnsupportedEncodingException {
        return str.getBytes("UTF-8");
    }

    public static String generateSession() {
        try {
            return encryptMD5(UUID.randomUUID().toString());
        } catch (Exception unused) {
            return null;
        }
    }

    /* JADX WARN: Not initialized variable reg: 4, insn: 0x0072: MOVE (r1 I:??[OBJECT, ARRAY]) = (r4 I:??[OBJECT, ARRAY]), block:B:37:0x0072 */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0075 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String getFileMD5(java.io.File r7) throws java.lang.Throwable {
        /*
            boolean r0 = r7.exists()
            r1 = 0
            if (r0 == 0) goto L7e
            boolean r0 = r7.isFile()
            if (r0 != 0) goto Lf
            goto L7e
        Lf:
            r0 = 1024(0x400, float:1.435E-42)
            byte[] r2 = new byte[r0]
            java.lang.String r3 = "MD5"
            java.security.MessageDigest r3 = java.security.MessageDigest.getInstance(r3)     // Catch: java.lang.Throwable -> L5f java.lang.Exception -> L61
            java.io.FileInputStream r4 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L5f java.lang.Exception -> L61
            r4.<init>(r7)     // Catch: java.lang.Throwable -> L5f java.lang.Exception -> L61
        L1e:
            r7 = 0
            int r5 = r4.read(r2, r7, r0)     // Catch: java.lang.Exception -> L5d java.lang.Throwable -> L71
            r6 = -1
            if (r5 == r6) goto L2a
            r3.update(r2, r7, r5)     // Catch: java.lang.Exception -> L5d java.lang.Throwable -> L71
            goto L1e
        L2a:
            r4.close()     // Catch: java.io.IOException -> L2e
            goto L32
        L2e:
            r7 = move-exception
            r7.printStackTrace()
        L32:
            java.math.BigInteger r7 = new java.math.BigInteger
            r0 = 1
            byte[] r1 = r3.digest()
            r7.<init>(r0, r1)
            r0 = 16
            java.lang.String r7 = r7.toString(r0)
        L42:
            int r0 = r7.length()
            r1 = 32
            if (r0 >= r1) goto L5c
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "0"
            r0.append(r1)
            r0.append(r7)
            java.lang.String r7 = r0.toString()
            goto L42
        L5c:
            return r7
        L5d:
            r7 = move-exception
            goto L63
        L5f:
            r7 = move-exception
            goto L73
        L61:
            r7 = move-exception
            r4 = r1
        L63:
            r7.printStackTrace()     // Catch: java.lang.Throwable -> L71
            if (r4 == 0) goto L70
            r4.close()     // Catch: java.io.IOException -> L6c
            goto L70
        L6c:
            r7 = move-exception
            r7.printStackTrace()
        L70:
            return r1
        L71:
            r7 = move-exception
            r1 = r4
        L73:
            if (r1 == 0) goto L7d
            r1.close()     // Catch: java.io.IOException -> L79
            goto L7d
        L79:
            r0 = move-exception
            r0.printStackTrace()
        L7d:
            throw r7
        L7e:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.duowan.live.one.util.Md5Util.getFileMD5(java.io.File):java.lang.String");
    }

    public static String getFileMD5(String str) {
        return getFileMD5(new File(str));
    }

    public static String encryptFileMD5(File file) throws Throwable {
        FileInputStream fileInputStream;
        Throwable th;
        DigestInputStream digestInputStream;
        DigestInputStream digestInputStream2 = null;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            fileInputStream = new FileInputStream(file);
            try {
                digestInputStream = new DigestInputStream(fileInputStream, messageDigest);
                try {
                    while (digestInputStream.read(new byte[262144]) > 0) {
                    }
                    String strBytesToHexString = bytesToHexString(digestInputStream.getMessageDigest().digest());
                    try {
                        digestInputStream.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    try {
                        fileInputStream.close();
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                    return strBytesToHexString;
                } catch (IOException | NoSuchAlgorithmException unused) {
                    digestInputStream2 = digestInputStream;
                    if (digestInputStream2 != null) {
                        try {
                            digestInputStream2.close();
                        } catch (Exception e3) {
                            e3.printStackTrace();
                        }
                    }
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (Exception e4) {
                            e4.printStackTrace();
                        }
                    }
                    return "";
                } catch (Throwable th2) {
                    th = th2;
                    if (digestInputStream != null) {
                        try {
                            digestInputStream.close();
                        } catch (Exception e5) {
                            e5.printStackTrace();
                        }
                    }
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                            throw th;
                        } catch (Exception e6) {
                            e6.printStackTrace();
                            throw th;
                        }
                    }
                    throw th;
                }
            } catch (IOException | NoSuchAlgorithmException unused2) {
            } catch (Throwable th3) {
                th = th3;
                digestInputStream = null;
            }
        } catch (IOException | NoSuchAlgorithmException unused3) {
            fileInputStream = null;
        } catch (Throwable th4) {
            fileInputStream = null;
            th = th4;
            digestInputStream = null;
        }
    }

    public static String md5(String str) {
        if (str == null) {
            return null;
        }
        try {
            return encryptMD5(str);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
