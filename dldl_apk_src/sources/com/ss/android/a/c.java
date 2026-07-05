package com.ss.android.a;

import com.alipay.sdk.util.i;
import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import kotlin.UByte;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes.dex */
public class c {
    private static final char[] a = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    public static String a(File file) {
        return a(file, 9, 8192L);
    }

    public static String a(File file, int i, long j) {
        if (file != null) {
            try {
                if (file.exists()) {
                    return b(file, i, j);
                }
            } catch (Throwable th) {
                th.printStackTrace();
                return "";
            }
        }
        return "";
    }

    public static int a(String str, File file) {
        return a(str, file, (b) null);
    }

    public static int a(String str, File file, b bVar) {
        String strB;
        if (str == null || str.length() == 0) {
            return 2;
        }
        try {
            if (bVar != null) {
                if (bVar.a() <= 0) {
                    try {
                        bVar.b();
                    } catch (Throwable unused) {
                    }
                    return 5;
                }
            } else if (file == null || !file.exists()) {
                return 5;
            }
            int i = -1;
            long j = -1;
            try {
                a aVarA = a(str);
                if (aVarA != null) {
                    if (aVarA.a > 1) {
                        return 3;
                    }
                    i = aVarA.c;
                    j = aVarA.d;
                }
                a aVarA2 = null;
                try {
                    if (bVar != null) {
                        strB = a(bVar, i, j);
                    } else {
                        strB = b(file, i, j);
                    }
                } catch (Throwable th) {
                    th.printStackTrace();
                    strB = null;
                }
                if (strB != null && strB.length() != 0) {
                    if (aVarA == null || (aVarA.a == 1 && aVarA.b == 1)) {
                        if (strB.equals(str)) {
                            return 0;
                        }
                    } else if (aVarA.e != null) {
                        try {
                            aVarA2 = a(strB);
                        } catch (Throwable unused2) {
                        }
                        if (aVarA2 != null && aVarA.c == aVarA2.c && aVarA.d == aVarA2.d && aVarA.e.equals(aVarA2.e)) {
                            return 0;
                        }
                    }
                    return 1;
                }
                return 6;
            } catch (Throwable unused3) {
                return 4;
            }
        } catch (Throwable unused4) {
            return 99;
        }
    }

    private static String b(File file, int i, long j) throws Exception {
        return a(new com.ss.android.a.a(file), i, j);
    }

    private static String a(b bVar, int i, long j) throws Exception {
        long j2;
        int i2 = i;
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        if (messageDigest == null) {
            return "";
        }
        try {
            long jA = bVar.a();
            if (i2 <= 0 || j <= 0 || ((long) i2) * j > (8 * jA) / 10) {
                j2 = jA;
                i2 = 1;
            } else {
                j2 = j;
            }
            byte[] bArr = new byte[8192];
            long j3 = 0;
            a(bVar, messageDigest, bArr, 0L, j2);
            if (i2 > 2) {
                int i3 = i2 - 1;
                long j4 = (jA - (((long) i2) * j2)) / ((long) i3);
                int i4 = 1;
                while (i4 < i3) {
                    j3 += j2 + j4;
                    a(bVar, messageDigest, bArr, j3, j2);
                    i4++;
                    i3 = i3;
                }
            }
            if (i2 > 1) {
                a(bVar, messageDigest, bArr, jA - j2, j2);
            }
            String strA = a(messageDigest.digest());
            if (i2 == 1 && j2 == jA) {
                return strA;
            }
            String str = a(i2, j2) + i.b + strA;
            try {
                bVar.b();
            } catch (Throwable unused) {
            }
            return str;
        } finally {
            try {
                bVar.b();
            } catch (Throwable unused2) {
            }
        }
    }

    private static void a(b bVar, MessageDigest messageDigest, byte[] bArr, long j, long j2) throws IOException {
        bVar.a(j, j2);
        long j3 = 0;
        while (j3 < j2) {
            int iA = bVar.a(bArr, 0, (int) Math.min(j2 - j3, bArr.length));
            if (iA <= 0) {
                throw new IOException("updateSample unexpected readCount <= 0, readCount = " + iA + ", readTotalCount = " + j3 + ", sampleSize = " + j2);
            }
            messageDigest.update(bArr, 0, iA);
            j3 += (long) iA;
        }
    }

    private static String a(byte[] bArr) {
        if (bArr == null) {
            throw new NullPointerException("bytes is null");
        }
        int length = bArr.length;
        int i = length * 2;
        char[] cArr = new char[i];
        int i2 = 0;
        for (int i3 = 0; i3 < length; i3++) {
            int i4 = bArr[i3 + 0] & UByte.MAX_VALUE;
            int i5 = i2 + 1;
            char[] cArr2 = a;
            cArr[i2] = cArr2[i4 >> 4];
            i2 = i5 + 1;
            cArr[i5] = cArr2[i4 & 15];
        }
        return new String(cArr, 0, i);
    }

    private static String a(int i, long j) {
        return "ttmd5:1:1:" + a(i) + "g" + a(j);
    }

    private static a a(String str) throws Exception {
        if (!str.startsWith("ttmd5:")) {
            return null;
        }
        String[] strArrSplit = str.split(i.b);
        String[] strArrSplit2 = strArrSplit[0].split(":");
        a aVar = new a();
        aVar.a = Integer.parseInt(strArrSplit2[1]);
        if (aVar.a > 1) {
            return aVar;
        }
        aVar.b = Integer.parseInt(strArrSplit2[2]);
        String[] strArrSplit3 = strArrSplit2[3].split("g");
        aVar.c = (int) b(strArrSplit3[0]);
        aVar.d = b(strArrSplit3[1]);
        aVar.e = strArrSplit[1];
        return aVar;
    }

    private static String a(long j) {
        return Long.toHexString((j << 4) + 31);
    }

    private static long b(String str) throws RuntimeException {
        return (Long.parseLong(str, 16) - 31) >> 4;
    }

    private static class a {
        private int a;
        private int b;
        private int c;
        private long d;
        private String e;

        private a() {
        }
    }
}
