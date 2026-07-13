package com.volcengine.j;

import android.text.TextUtils;
import android.util.Log;
import com.volcengine.common.SDKContext;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

/* JADX INFO: loaded from: classes3.dex */
public final class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static int f1147a = 524288;

    public interface a {
        void a(double d);
    }

    public static String a(File file, String str) {
        byte[] bArrA = a(file);
        if (bArrA == null) {
            return null;
        }
        if (TextUtils.isEmpty(str)) {
            return new String(bArrA);
        }
        try {
            return new String(bArrA, str);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String a(String str) {
        StringBuilder sb = new StringBuilder();
        try {
            InputStream inputStreamOpen = SDKContext.getContext().getAssets().open(str);
            if (inputStreamOpen != null) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStreamOpen));
                while (true) {
                    String line = bufferedReader.readLine();
                    if (line == null) {
                        break;
                    }
                    sb.append(line);
                }
            } else {
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public static void a(Closeable... closeableArr) {
        for (Closeable closeable : closeableArr) {
            if (closeable != null) {
                try {
                    closeable.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static boolean a(File file, String str, boolean z) throws Throwable {
        BufferedWriter bufferedWriter;
        if (file == null || str == null) {
            return false;
        }
        if (!i.b(file)) {
            Log.e("FileIOUtils", "create file <" + file + "> failed.");
            return false;
        }
        BufferedWriter bufferedWriter2 = null;
        try {
            try {
                bufferedWriter = new BufferedWriter(new FileWriter(file, z));
            } catch (Throwable th) {
                th = th;
                bufferedWriter = bufferedWriter2;
            }
        } catch (IOException e) {
            e = e;
        }
        try {
            bufferedWriter.write(str);
            try {
                bufferedWriter.close();
                return true;
            } catch (IOException e2) {
                e2.printStackTrace();
                return true;
            }
        } catch (IOException e3) {
            e = e3;
            bufferedWriter2 = bufferedWriter;
            e.printStackTrace();
            if (bufferedWriter2 != null) {
                try {
                    bufferedWriter2.close();
                } catch (IOException e4) {
                    e4.printStackTrace();
                }
            }
            return false;
        } catch (Throwable th2) {
            th = th2;
            if (bufferedWriter != null) {
                try {
                    bufferedWriter.close();
                } catch (IOException e5) {
                    e5.printStackTrace();
                }
            }
            throw th;
        }
    }

    public static boolean a(String str, String str2) {
        return a(i.c(str), str2, false);
    }

    public static byte[] a(File file) {
        return a(file, (a) null);
    }

    /* JADX WARN: Removed duplicated region for block: B:71:0x0089 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:78:? A[Catch: FileNotFoundException -> 0x0092, SYNTHETIC, TRY_LEAVE, TryCatch #5 {FileNotFoundException -> 0x0092, blocks: (B:5:0x0008, B:24:0x005b, B:20:0x0053, B:54:0x0091, B:53:0x008e, B:48:0x0084, B:43:0x007b, B:38:0x0071, B:40:0x0076, B:21:0x0056, B:45:0x007f, B:17:0x004e, B:35:0x006c, B:50:0x0089), top: B:64:0x0008, inners: #0, #1, #4, #7, #8, #9 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static byte[] a(java.io.File r10, com.volcengine.j.h.a r11) throws java.lang.Throwable {
        /*
            boolean r0 = com.volcengine.j.i.f(r10)
            r1 = 0
            if (r0 != 0) goto L8
            return r1
        L8:
            java.io.BufferedInputStream r0 = new java.io.BufferedInputStream     // Catch: java.io.FileNotFoundException -> L92
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch: java.io.FileNotFoundException -> L92
            r2.<init>(r10)     // Catch: java.io.FileNotFoundException -> L92
            int r10 = com.volcengine.j.h.f1147a     // Catch: java.io.FileNotFoundException -> L92
            r0.<init>(r2, r10)     // Catch: java.io.FileNotFoundException -> L92
            java.io.ByteArrayOutputStream r10 = new java.io.ByteArrayOutputStream     // Catch: java.lang.Throwable -> L63 java.io.IOException -> L67
            r10.<init>()     // Catch: java.lang.Throwable -> L63 java.io.IOException -> L67
            int r2 = com.volcengine.j.h.f1147a     // Catch: java.lang.Throwable -> L5f java.io.IOException -> L61
            byte[] r2 = new byte[r2]     // Catch: java.lang.Throwable -> L5f java.io.IOException -> L61
            r3 = -1
            r4 = 0
            if (r11 != 0) goto L2d
        L21:
            int r11 = com.volcengine.j.h.f1147a     // Catch: java.lang.Throwable -> L5f java.io.IOException -> L61
            int r11 = r0.read(r2, r4, r11)     // Catch: java.lang.Throwable -> L5f java.io.IOException -> L61
            if (r11 == r3) goto L4a
            r10.write(r2, r4, r11)     // Catch: java.lang.Throwable -> L5f java.io.IOException -> L61
            goto L21
        L2d:
            int r5 = r0.available()     // Catch: java.lang.Throwable -> L5f java.io.IOException -> L61
            double r5 = (double) r5     // Catch: java.lang.Throwable -> L5f java.io.IOException -> L61
            r7 = 0
            r11.a(r7)     // Catch: java.lang.Throwable -> L5f java.io.IOException -> L61
            r7 = 0
        L38:
            int r8 = com.volcengine.j.h.f1147a     // Catch: java.lang.Throwable -> L5f java.io.IOException -> L61
            int r8 = r0.read(r2, r4, r8)     // Catch: java.lang.Throwable -> L5f java.io.IOException -> L61
            if (r8 == r3) goto L4a
            r10.write(r2, r4, r8)     // Catch: java.lang.Throwable -> L5f java.io.IOException -> L61
            int r7 = r7 + r8
            double r8 = (double) r7     // Catch: java.lang.Throwable -> L5f java.io.IOException -> L61
            double r8 = r8 / r5
            r11.a(r8)     // Catch: java.lang.Throwable -> L5f java.io.IOException -> L61
            goto L38
        L4a:
            byte[] r11 = r10.toByteArray()     // Catch: java.lang.Throwable -> L5f java.io.IOException -> L61
            r0.close()     // Catch: java.io.IOException -> L52
            goto L56
        L52:
            r0 = move-exception
            r0.printStackTrace()     // Catch: java.io.FileNotFoundException -> L92
        L56:
            r10.close()     // Catch: java.io.IOException -> L5a
            goto L5e
        L5a:
            r10 = move-exception
            r10.printStackTrace()     // Catch: java.io.FileNotFoundException -> L92
        L5e:
            return r11
        L5f:
            r11 = move-exception
            goto L7f
        L61:
            r11 = move-exception
            goto L69
        L63:
            r10 = move-exception
            r11 = r10
            r10 = r1
            goto L7f
        L67:
            r11 = move-exception
            r10 = r1
        L69:
            r11.printStackTrace()     // Catch: java.lang.Throwable -> L5f
            r0.close()     // Catch: java.io.IOException -> L70
            goto L74
        L70:
            r11 = move-exception
            r11.printStackTrace()     // Catch: java.io.FileNotFoundException -> L92
        L74:
            if (r10 == 0) goto L7e
            r10.close()     // Catch: java.io.IOException -> L7a
            goto L7e
        L7a:
            r10 = move-exception
            r10.printStackTrace()     // Catch: java.io.FileNotFoundException -> L92
        L7e:
            return r1
        L7f:
            r0.close()     // Catch: java.io.IOException -> L83
            goto L87
        L83:
            r0 = move-exception
            r0.printStackTrace()     // Catch: java.io.FileNotFoundException -> L92
        L87:
            if (r10 == 0) goto L91
            r10.close()     // Catch: java.io.IOException -> L8d
            goto L91
        L8d:
            r10 = move-exception
            r10.printStackTrace()     // Catch: java.io.FileNotFoundException -> L92
        L91:
            throw r11     // Catch: java.io.FileNotFoundException -> L92
        L92:
            r10 = move-exception
            r10.printStackTrace()
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.volcengine.j.h.a(java.io.File, com.volcengine.j.h$a):byte[]");
    }

    public static String b(String str) {
        return a(i.c(str), (String) null);
    }
}
