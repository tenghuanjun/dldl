package com.huya.hysignal.core;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Process;
import android.util.Base64;
import com.huya.hysignal.util.FieldsCache;
import com.huya.hysignal.util.HySignalFileUtil;
import com.huya.hysignal.util.HySignalLog;
import com.huya.hysignal.util.ThreadManager;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.List;
import javax.crypto.Cipher;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
class HySignalProxy {
    private static final String BACK_CA_FILE_NAME = "cacert.pem.bak";
    private static final String CA_FILE_NAME = "cacert.pem";
    private static final String KEY_ALGORITHM = "RSA";
    private static final int MAX_DECRYPT_BLOCK = 256;
    private static final String PROXY_CA_SERVER_URL = "aHR0cDovLzEwLjY5LjE4MC4zNjo4MDk5L2dldF9jYQ==\n";
    private static final String PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAmpHFny8+w2bygaHPBCw/GL/fuLwPXKUICVXVnrDcCodlawJBwl2prsLAAUMiDmw2q3HVluMTnObChczrNcO6kMnZ+utEtRRAgZIZ66CufNYXXrBxbY3I+zWgrUJjTyTee+uERUGrKfK3GaRHD7VG33T4cIBuNiXP3TZ+9bpO0W+5tLvSa4F0HwoxB3/69Ri+2UnyrLiBeBpbvIHJi1kCLIcKDhb+M+yuMX4UtncDZG+Tm7HAIe2WnPXUW4xB8e1kUoqptV+msnotQJFaH50zOEHcZCyRp9auS6TZBR/AWydfPne4ExJlw7BdrLLGFcuLM1q2vgoH2YksE+ubj2IYYQIDAQAB";
    private static final int RSA_KEY_SIZE = 2048;
    private static final String TAG = "HySignalProxy";

    HySignalProxy() {
    }

    static void init(final boolean z, final String str, final int i, final HyMars hyMars, final Application application) {
        ThreadManager.deliverOnRequestThread(new Runnable() { // from class: com.huya.hysignal.core.HySignalProxy.1
            @Override // java.lang.Runnable
            public void run() throws Throwable {
                if (z) {
                    HySignalProxy.enableProxy(str, i, hyMars, application);
                } else {
                    HySignalProxy.disableProxy(application);
                }
            }
        });
    }

    static boolean isProxyEnable() {
        return HySignalFileUtil.getCurrentVersion().equals(FieldsCache.getInstance().getCaVersion());
    }

    /* JADX WARN: Removed duplicated region for block: B:56:0x00df  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static boolean enableProxy(java.lang.String r9, int r10, com.huya.hysignal.core.HyMars r11, android.app.Application r12) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 277
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huya.hysignal.core.HySignalProxy.enableProxy(java.lang.String, int, com.huya.hysignal.core.HyMars, android.app.Application):boolean");
    }

    static boolean disableProxy(Application application) {
        HySignalLog.info(TAG, "disable proxy");
        boolean z = false;
        try {
            if (!isMainProcess(application)) {
                HySignalLog.info(TAG, "not main process, skip close proxy");
                return false;
            }
            if (application == null) {
                HySignalLog.error(TAG, "disable proxy context is null");
                return false;
            }
            if (!isProxyEnable()) {
                return true;
            }
            File filesDir = application.getFilesDir();
            File file = new File(filesDir, BACK_CA_FILE_NAME);
            File file2 = new File(filesDir, CA_FILE_NAME);
            if (!file.isFile() || !file.exists()) {
                HySignalLog.error(TAG, "disable proxy file not exists");
                return false;
            }
            if (copyFile(file, file2)) {
                FieldsCache.getInstance().saveCaVersion("");
                z = true;
            }
            file.delete();
            return z;
        } catch (Exception e) {
            HySignalLog.error(TAG, "pm not found");
            e.printStackTrace();
            return false;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:51:0x0064 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:59:0x005d A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:69:? A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static boolean copyFile(java.io.File r5, java.io.File r6) throws java.lang.Throwable {
        /*
            r0 = 1
            r1 = 0
            r2 = 0
            java.io.BufferedInputStream r3 = new java.io.BufferedInputStream     // Catch: java.lang.Throwable -> L39 java.lang.Exception -> L3c
            java.io.FileInputStream r4 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L39 java.lang.Exception -> L3c
            r4.<init>(r5)     // Catch: java.lang.Throwable -> L39 java.lang.Exception -> L3c
            r3.<init>(r4)     // Catch: java.lang.Throwable -> L39 java.lang.Exception -> L3c
            java.io.BufferedOutputStream r5 = new java.io.BufferedOutputStream     // Catch: java.lang.Throwable -> L31 java.lang.Exception -> L35
            java.io.FileOutputStream r4 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L31 java.lang.Exception -> L35
            r4.<init>(r6)     // Catch: java.lang.Throwable -> L31 java.lang.Exception -> L35
            r5.<init>(r4)     // Catch: java.lang.Throwable -> L31 java.lang.Exception -> L35
            r6 = 4096(0x1000, float:5.74E-42)
            byte[] r6 = new byte[r6]     // Catch: java.lang.Throwable -> L2d java.lang.Exception -> L2f
        L1b:
            int r1 = r3.read(r6)     // Catch: java.lang.Throwable -> L2d java.lang.Exception -> L2f
            r4 = -1
            if (r1 != r4) goto L29
            r3.close()     // Catch: java.io.IOException -> L25
        L25:
            r5.close()     // Catch: java.io.IOException -> L28
        L28:
            return r0
        L29:
            r5.write(r6, r2, r1)     // Catch: java.lang.Throwable -> L2d java.lang.Exception -> L2f
            goto L1b
        L2d:
            r6 = move-exception
            goto L33
        L2f:
            r6 = move-exception
            goto L37
        L31:
            r6 = move-exception
            r5 = r1
        L33:
            r1 = r3
            goto L5b
        L35:
            r6 = move-exception
            r5 = r1
        L37:
            r1 = r3
            goto L3e
        L39:
            r6 = move-exception
            r5 = r1
            goto L5b
        L3c:
            r6 = move-exception
            r5 = r1
        L3e:
            java.lang.String r3 = "HySignalProxy"
            java.lang.String r4 = "copy file failed, error=%s"
            java.lang.Object[] r0 = new java.lang.Object[r0]     // Catch: java.lang.Throwable -> L5a
            java.lang.String r6 = r6.getMessage()     // Catch: java.lang.Throwable -> L5a
            r0[r2] = r6     // Catch: java.lang.Throwable -> L5a
            com.huya.hysignal.util.HySignalLog.error(r3, r4, r0)     // Catch: java.lang.Throwable -> L5a
            if (r1 == 0) goto L54
            r1.close()     // Catch: java.io.IOException -> L53
            goto L54
        L53:
        L54:
            if (r5 == 0) goto L59
            r5.close()     // Catch: java.io.IOException -> L59
        L59:
            return r2
        L5a:
            r6 = move-exception
        L5b:
            if (r1 == 0) goto L62
            r1.close()     // Catch: java.io.IOException -> L61
            goto L62
        L61:
        L62:
            if (r5 == 0) goto L67
            r5.close()     // Catch: java.io.IOException -> L67
        L67:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huya.hysignal.core.HySignalProxy.copyFile(java.io.File, java.io.File):boolean");
    }

    private static void setIPAndPort(String str, int i, HyMars hyMars) {
        if (str == null || "".equals(str) || i <= 0) {
            HySignalLog.error(TAG, "set ip and port is empty");
        } else {
            hyMars.setProxy(str, i);
        }
    }

    private static byte[] decodeBase64(String str) {
        return Base64.decode(str.getBytes(), 0);
    }

    private static byte[] decryptByPublicKey(String str) throws Exception {
        byte[] bArrDoFinal;
        byte[] bArrDecodeBase64 = decodeBase64(PUBLIC_KEY);
        byte[] bArrDecodeBase642 = decodeBase64(str);
        PublicKey publicKeyGeneratePublic = KeyFactory.getInstance(KEY_ALGORITHM).generatePublic(new X509EncodedKeySpec(bArrDecodeBase64));
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(2, publicKeyGeneratePublic);
        int length = bArrDecodeBase642.length;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i = 0;
        int i2 = 0;
        while (true) {
            int i3 = length - i;
            if (i3 > 0) {
                if (i3 > 256) {
                    bArrDoFinal = cipher.doFinal(bArrDecodeBase642, i, 256);
                } else {
                    bArrDoFinal = cipher.doFinal(bArrDecodeBase642, i, i3);
                }
                byteArrayOutputStream.write(bArrDoFinal, 0, bArrDoFinal.length);
                i2++;
                i = i2 * 256;
            } else {
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                byteArrayOutputStream.close();
                return byteArray;
            }
        }
    }

    private static boolean isMainProcess(Context context) throws PackageManager.NameNotFoundException {
        return isPidOfProcessName(context, Process.myPid(), getMainProcessName(context));
    }

    private static String getMainProcessName(Context context) throws PackageManager.NameNotFoundException {
        return context.getPackageManager().getApplicationInfo(context.getPackageName(), 0).processName;
    }

    private static boolean isPidOfProcessName(Context context, int i, String str) {
        if (str == null) {
            return false;
        }
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = activityManager != null ? activityManager.getRunningAppProcesses() : null;
        if (runningAppProcesses == null || runningAppProcesses.isEmpty()) {
            return true;
        }
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
            if (runningAppProcessInfo.pid == i) {
                return runningAppProcessInfo.processName.equals(str);
            }
        }
        return false;
    }

    public static String encodeToString(String str) {
        try {
            return Base64.encodeToString(str.getBytes("UTF-8"), 0);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String decodeToString(String str) {
        try {
            return new String(Base64.decode(str.getBytes("UTF-8"), 0));
        } catch (UnsupportedEncodingException e) {
            HySignalLog.error(TAG, "decodeToString error: %s", e.toString());
            e.printStackTrace();
            return "";
        }
    }
}
