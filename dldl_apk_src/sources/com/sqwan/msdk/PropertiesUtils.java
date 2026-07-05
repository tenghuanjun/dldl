package com.sqwan.msdk;

import android.content.Context;
import java.util.Properties;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class PropertiesUtils {
    public static Properties readPropertites(Context context, String str) {
        return readPropertites(context.getResources().getAssets(), str);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0033 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r3v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v10 */
    /* JADX WARN: Type inference failed for: r3v11, types: [java.util.Properties] */
    /* JADX WARN: Type inference failed for: r3v12 */
    /* JADX WARN: Type inference failed for: r3v13 */
    /* JADX WARN: Type inference failed for: r3v2 */
    /* JADX WARN: Type inference failed for: r3v3 */
    /* JADX WARN: Type inference failed for: r3v6 */
    /* JADX WARN: Type inference failed for: r3v7, types: [java.util.Properties] */
    /* JADX WARN: Type inference failed for: r3v8 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.util.Properties readPropertites(android.content.res.AssetManager r2, java.lang.String r3) throws java.lang.Throwable {
        /*
            r0 = 0
            java.io.InputStream r2 = r2.open(r3)     // Catch: java.lang.Throwable -> L22 java.io.IOException -> L24
            java.util.Properties r3 = new java.util.Properties     // Catch: java.lang.Throwable -> L1a java.io.IOException -> L1d
            r3.<init>()     // Catch: java.lang.Throwable -> L1a java.io.IOException -> L1d
            r3.load(r2)     // Catch: java.io.IOException -> L18 java.lang.Throwable -> L1a
            if (r2 == 0) goto L30
            r2.close()     // Catch: java.io.IOException -> L13
            goto L30
        L13:
            r2 = move-exception
            r2.printStackTrace()
            goto L30
        L18:
            r0 = move-exception
            goto L28
        L1a:
            r3 = move-exception
            r0 = r2
            goto L31
        L1d:
            r3 = move-exception
            r1 = r0
            r0 = r3
            r3 = r1
            goto L28
        L22:
            r3 = move-exception
            goto L31
        L24:
            r2 = move-exception
            r3 = r0
            r0 = r2
            r2 = r3
        L28:
            r0.printStackTrace()     // Catch: java.lang.Throwable -> L1a
            if (r2 == 0) goto L30
            r2.close()     // Catch: java.io.IOException -> L13
        L30:
            return r3
        L31:
            if (r0 == 0) goto L3b
            r0.close()     // Catch: java.io.IOException -> L37
            goto L3b
        L37:
            r2 = move-exception
            r2.printStackTrace()
        L3b:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sqwan.msdk.PropertiesUtils.readPropertites(android.content.res.AssetManager, java.lang.String):java.util.Properties");
    }
}
