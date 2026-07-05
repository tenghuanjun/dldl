package com.sq.sdk.tool.util;

import android.content.Context;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class SqFileUtil {
    public static Properties readProperties(Context context, String str) {
        try {
            InputStream inputStreamOpen = context.getResources().getAssets().open(str);
            Properties properties = new Properties();
            properties.load(inputStreamOpen);
            return properties;
        } catch (IOException unused) {
            return null;
        }
    }

    public static void saveFile(Context context, String str, String str2) throws Throwable {
        FileOutputStream fileOutputStream;
        File file = new File(str);
        FileOutputStream fileOutputStream2 = null;
        try {
            try {
                try {
                    fileOutputStream = new FileOutputStream(file);
                } catch (IOException e) {
                    e = e;
                }
            } catch (Throwable th) {
                th = th;
            }
            try {
                fileOutputStream.write(str2.getBytes());
                fileOutputStream.flush();
                fileOutputStream.close();
            } catch (IOException e2) {
                e = e2;
                fileOutputStream2 = fileOutputStream;
                e.printStackTrace();
                if (fileOutputStream2 == null) {
                } else {
                    fileOutputStream2.close();
                }
            } catch (Throwable th2) {
                th = th2;
                fileOutputStream2 = fileOutputStream;
                if (fileOutputStream2 != null) {
                    try {
                        fileOutputStream2.close();
                    } catch (IOException e3) {
                        e3.printStackTrace();
                    }
                }
                throw th;
            }
        } catch (IOException e4) {
            e4.printStackTrace();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:40:0x0042 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String readFile(android.content.Context r3, java.lang.String r4) throws java.lang.Throwable {
        /*
            java.io.File r3 = new java.io.File
            r3.<init>(r4)
            r4 = 0
            java.io.FileInputStream r0 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L2c java.io.IOException -> L2e
            r0.<init>(r3)     // Catch: java.lang.Throwable -> L2c java.io.IOException -> L2e
            int r3 = r0.available()     // Catch: java.io.IOException -> L2a java.lang.Throwable -> L3e
            byte[] r3 = new byte[r3]     // Catch: java.io.IOException -> L2a java.lang.Throwable -> L3e
            int r1 = r0.read(r3)     // Catch: java.io.IOException -> L2a java.lang.Throwable -> L3e
            r2 = -1
            if (r1 == r2) goto L26
            java.lang.String r1 = new java.lang.String     // Catch: java.io.IOException -> L2a java.lang.Throwable -> L3e
            r1.<init>(r3)     // Catch: java.io.IOException -> L2a java.lang.Throwable -> L3e
            r0.close()     // Catch: java.io.IOException -> L21
            goto L25
        L21:
            r3 = move-exception
            r3.printStackTrace()
        L25:
            return r1
        L26:
            r0.close()     // Catch: java.io.IOException -> L39
            goto L3d
        L2a:
            r3 = move-exception
            goto L30
        L2c:
            r3 = move-exception
            goto L40
        L2e:
            r3 = move-exception
            r0 = r4
        L30:
            r3.printStackTrace()     // Catch: java.lang.Throwable -> L3e
            if (r0 == 0) goto L3d
            r0.close()     // Catch: java.io.IOException -> L39
            goto L3d
        L39:
            r3 = move-exception
            r3.printStackTrace()
        L3d:
            return r4
        L3e:
            r3 = move-exception
            r4 = r0
        L40:
            if (r4 == 0) goto L4a
            r4.close()     // Catch: java.io.IOException -> L46
            goto L4a
        L46:
            r4 = move-exception
            r4.printStackTrace()
        L4a:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sq.sdk.tool.util.SqFileUtil.readFile(android.content.Context, java.lang.String):java.lang.String");
    }
}
