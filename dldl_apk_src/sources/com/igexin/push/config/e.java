package com.igexin.push.config;

import com.igexin.push.f.k;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public final class e {
    private static String a = "FileConfig";

    public static void a() {
        d.t = com.igexin.push.core.d.c.a().a(com.igexin.push.core.d.c.e, d.t);
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:67:0x0102  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static void a(java.io.InputStream r7) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 564
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.igexin.push.config.e.a(java.io.InputStream):void");
    }

    public static void a(Boolean bool) {
        try {
            if (new File(com.igexin.push.core.e.ae).exists()) {
                b(bool);
                return;
            }
            byte[] bytes = "sdk.debug=".concat(String.valueOf(bool)).getBytes();
            if (bytes != null) {
                k.a(bytes, com.igexin.push.core.e.ae, false);
            }
        } catch (Throwable unused) {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:74:0x0027 A[EXC_TOP_SPLITTER, PHI: r0
  0x0027: PHI (r0v6 java.io.InputStream) = (r0v5 java.io.InputStream), (r0v13 java.io.InputStream) binds: [B:16:0x0036, B:5:0x0025] A[DONT_GENERATE, DONT_INLINE], SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void b() {
        /*
            Method dump skipped, instruction units count: 206
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.igexin.push.config.e.b():void");
    }

    private static void b(Boolean bool) throws Throwable {
        FileInputStream fileInputStream;
        BufferedReader bufferedReader;
        String str;
        BufferedReader bufferedReader2 = null;
        try {
            fileInputStream = new FileInputStream(com.igexin.push.core.e.ae);
            try {
                bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream, "UTF-8"));
                try {
                    StringBuilder sb = new StringBuilder();
                    while (true) {
                        String line = bufferedReader.readLine();
                        if (line == null) {
                            break;
                        }
                        if (line.startsWith("#")) {
                            sb.append(line);
                            str = "\n";
                        } else {
                            String[] strArrSplit = line.split("=");
                            if (strArrSplit.length < 2) {
                                sb.append(line);
                                str = "\n";
                            } else {
                                String strTrim = strArrSplit[0].trim();
                                strArrSplit[1].trim();
                                if (!strTrim.equals("sdk.debug")) {
                                    sb.append(line);
                                    str = "\n";
                                }
                            }
                        }
                        sb.append(str);
                    }
                    sb.append("sdk.debug=".concat(String.valueOf(bool)));
                    byte[] bytes = sb.toString().getBytes();
                    if (bytes != null) {
                        k.a(bytes, com.igexin.push.core.e.ae, false);
                    }
                    try {
                        bufferedReader.close();
                    } catch (IOException unused) {
                    }
                    try {
                        fileInputStream.close();
                    } catch (Exception unused2) {
                    }
                } catch (Exception unused3) {
                    bufferedReader2 = bufferedReader;
                    if (bufferedReader2 != null) {
                        try {
                            bufferedReader2.close();
                        } catch (IOException unused4) {
                        }
                    }
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (Exception unused5) {
                        }
                    }
                } catch (Throwable th) {
                    th = th;
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (IOException unused6) {
                        }
                    }
                    if (fileInputStream == null) {
                        throw th;
                    }
                    try {
                        fileInputStream.close();
                        throw th;
                    } catch (Exception unused7) {
                        throw th;
                    }
                }
            } catch (Exception unused8) {
            } catch (Throwable th2) {
                th = th2;
                bufferedReader = null;
            }
        } catch (Exception unused9) {
            fileInputStream = null;
        } catch (Throwable th3) {
            th = th3;
            fileInputStream = null;
            bufferedReader = null;
        }
    }
}
