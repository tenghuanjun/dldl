package com.huya.hysignal.util;

import android.content.SharedPreferences;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public final class HySignalFileUtil {
    private static final String CA_VERSION_CONFIG = "CA_VERSION";
    private static final String TAG = HySignalFileUtil.class.getName();
    private static final String VERSION = "2019-05-15";
    private static SharedPreferences sPreferences;

    public static String getCurrentVersion() {
        return VERSION;
    }

    /* JADX WARN: Removed duplicated region for block: B:61:0x00aa A[EXC_TOP_SPLITTER, PHI: r11
  0x00aa: PHI (r11v5 java.io.InputStream) = (r11v4 java.io.InputStream), (r11v7 java.io.InputStream) binds: [B:51:0x00a8, B:23:0x0071] A[DONT_GENERATE, DONT_INLINE], SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void copyCaFile(android.app.Application r11) throws java.lang.Throwable {
        /*
            java.io.File r0 = r11.getFilesDir()
            java.io.File r1 = new java.io.File
            java.lang.String r2 = "cacert.pem"
            r1.<init>(r0, r2)
            android.content.SharedPreferences r3 = com.huya.hysignal.util.HySignalFileUtil.sPreferences
            r4 = 0
            if (r3 != 0) goto L18
            java.lang.String r3 = com.huya.hysignal.util.HySignalFileUtil.TAG
            android.content.SharedPreferences r3 = r11.getSharedPreferences(r3, r4)
            com.huya.hysignal.util.HySignalFileUtil.sPreferences = r3
        L18:
            android.content.SharedPreferences r3 = com.huya.hysignal.util.HySignalFileUtil.sPreferences
            java.lang.String r5 = "CA_VERSION"
            java.lang.String r6 = ""
            java.lang.String r3 = r3.getString(r5, r6)
            boolean r6 = r1.exists()
            java.lang.String r7 = "2019-05-15"
            if (r6 == 0) goto L38
            boolean r3 = r7.equals(r3)
            if (r3 == 0) goto L38
            java.lang.String r11 = com.huya.hysignal.util.HySignalFileUtil.TAG
            java.lang.String r0 = "caFile exists and is newest"
            com.huya.hysignal.util.HySignalLog.info(r11, r0)
            return
        L38:
            java.io.File r3 = new java.io.File
            java.lang.String r6 = "cacert.pem.tmp"
            r3.<init>(r0, r6)
            r0 = 0
            android.content.res.AssetManager r11 = r11.getAssets()     // Catch: java.lang.Throwable -> L8e java.io.IOException -> La0
            java.io.InputStream r11 = r11.open(r2)     // Catch: java.lang.Throwable -> L8e java.io.IOException -> La0
            java.io.BufferedOutputStream r2 = new java.io.BufferedOutputStream     // Catch: java.lang.Throwable -> L8a java.io.IOException -> L8c
            java.io.FileOutputStream r6 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L8a java.io.IOException -> L8c
            r6.<init>(r3)     // Catch: java.lang.Throwable -> L8a java.io.IOException -> L8c
            r2.<init>(r6)     // Catch: java.lang.Throwable -> L8a java.io.IOException -> L8c
            r6 = 4096(0x1000, float:5.74E-42)
            byte[] r6 = new byte[r6]     // Catch: java.lang.Throwable -> L85 java.io.IOException -> L88
        L56:
            int r8 = r11.read(r6)     // Catch: java.lang.Throwable -> L85 java.io.IOException -> L88
            r9 = -1
            if (r8 != r9) goto L74
            r2.close()     // Catch: java.lang.Throwable -> L85 java.io.IOException -> L88
            boolean r1 = r3.renameTo(r1)     // Catch: java.lang.Throwable -> L8a java.io.IOException -> L8c
            if (r1 == 0) goto L6a
            setString(r5, r7)     // Catch: java.lang.Throwable -> L8a java.io.IOException -> L8c
            goto L71
        L6a:
            java.lang.String r1 = com.huya.hysignal.util.HySignalFileUtil.TAG     // Catch: java.lang.Throwable -> L8a java.io.IOException -> L8c
            java.lang.String r2 = "temporary caFile rename failed"
            com.huya.hysignal.util.HySignalLog.error(r1, r2)     // Catch: java.lang.Throwable -> L8a java.io.IOException -> L8c
        L71:
            if (r11 == 0) goto Lad
            goto Laa
        L74:
            r9 = 0
        L75:
            if (r9 >= r8) goto L81
            r10 = r6[r9]     // Catch: java.lang.Throwable -> L85 java.io.IOException -> L88
            r10 = r10 ^ 2
            byte r10 = (byte) r10     // Catch: java.lang.Throwable -> L85 java.io.IOException -> L88
            r6[r9] = r10     // Catch: java.lang.Throwable -> L85 java.io.IOException -> L88
            int r9 = r9 + 1
            goto L75
        L81:
            r2.write(r6, r4, r8)     // Catch: java.lang.Throwable -> L85 java.io.IOException -> L88
            goto L56
        L85:
            r1 = move-exception
            r0 = r2
            goto L90
        L88:
            r0 = r2
            goto La1
        L8a:
            r1 = move-exception
            goto L90
        L8c:
            goto La1
        L8e:
            r1 = move-exception
            r11 = r0
        L90:
            if (r0 == 0) goto L97
            r0.close()     // Catch: java.io.IOException -> L96
            goto L97
        L96:
        L97:
            if (r11 == 0) goto L9c
            r11.close()     // Catch: java.io.IOException -> L9c
        L9c:
            r3.delete()
            throw r1
        La0:
            r11 = r0
        La1:
            if (r0 == 0) goto La8
            r0.close()     // Catch: java.io.IOException -> La7
            goto La8
        La7:
        La8:
            if (r11 == 0) goto Lad
        Laa:
            r11.close()     // Catch: java.io.IOException -> Lad
        Lad:
            r3.delete()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huya.hysignal.util.HySignalFileUtil.copyCaFile(android.app.Application):void");
    }

    static synchronized void setString(String str, String str2) {
        SharedPreferences.Editor editorPutString;
        if (sPreferences == null) {
            return;
        }
        if (str2 == null) {
            editorPutString = sPreferences.edit().remove(str);
        } else {
            editorPutString = sPreferences.edit().putString(str, str2);
        }
        try {
            editorPutString.apply();
        } catch (Throwable th) {
            HySignalLog.error("Config", th.getMessage());
        }
    }
}
