package com.duowan.ark.qa;

import android.os.Environment;
import com.duowan.ark.ArkValue;
import com.duowan.ark.util.Config;
import com.duowan.ark.util.ConfigWithTimeout;
import com.duowan.ark.util.KLog;
import com.sqwan.bugless.util.DateUtil;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class CrashGuard {
    private static final String CRASH_CNT_DIR = File.separator + "kiwi" + File.separator + "crash";
    private static final String LAST_CRASH_COUNT = "last_crash_count";
    private static final int STARTUP_CRASH_DANGER_CNT = 2;
    private static final int STARTUP_CRASH_TIME_MILLIS = 10000;
    private static final String TAG = "CrashGuard";

    public static void notifyNativeCrash() throws Throwable {
        notifyCrash();
    }

    public static void notifyJavaCrash() throws Throwable {
        notifyCrash();
    }

    public static void enterApp() {
        Config.getInstance(ArkValue.gContext).setInt(LAST_CRASH_COUNT, 0);
    }

    private static void notifyCrash() throws Throwable {
        if (ArkValue.gContext == null) {
            return;
        }
        saveCurrentCrashCount();
        int todayCrashCount = getTodayCrashCount();
        KLog.warn(TAG, "today crash count %d", Integer.valueOf(todayCrashCount));
        setToadyCrashCount(todayCrashCount + 1);
        if (ArkValue.uptime() > 10000) {
            return;
        }
        KLog.warn(TAG, "got startup crash, uptime: %d", Long.valueOf(ArkValue.uptime()));
        int integer = ConfigWithTimeout.instance(ArkValue.gContext).getInteger(todayKey(), 0) + 1;
        saveCrashCount(integer);
        if (integer < 2) {
            return;
        }
        KLog.warn(TAG, "too many start up crash(cnt = %d), clear all config!", Integer.valueOf(integer));
        Config.getInstance(ArkValue.gContext).clearAllSync();
        saveCrashCount(integer);
    }

    private static void saveCrashCount(int i) {
        ConfigWithTimeout.instance(ArkValue.gContext).setInteger(todayKey(), i, TimeUnit.DAYS.toMillis(1L));
    }

    private static void saveCurrentCrashCount() {
        Config.getInstance(ArkValue.gContext).setInt(LAST_CRASH_COUNT, Config.getInstance(ArkValue.gContext).getInt(LAST_CRASH_COUNT, 0) + 1);
    }

    private static String todayKey() {
        return "startup_crash_" + new SimpleDateFormat(DateUtil.DEFAULT_FORMAT_DATE).format(new Date());
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:37:0x007a A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r0v0 */
    /* JADX WARN: Type inference failed for: r0v2, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r0v3 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static int _getInt(java.lang.String r4, int r5) throws java.lang.Throwable {
        /*
            r0 = 0
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L67 java.lang.Exception -> L69
            r1.<init>()     // Catch: java.lang.Throwable -> L67 java.lang.Exception -> L69
            java.io.File r2 = android.os.Environment.getExternalStorageDirectory()     // Catch: java.lang.Throwable -> L67 java.lang.Exception -> L69
            java.lang.String r2 = r2.getAbsolutePath()     // Catch: java.lang.Throwable -> L67 java.lang.Exception -> L69
            r1.append(r2)     // Catch: java.lang.Throwable -> L67 java.lang.Exception -> L69
            java.lang.String r2 = com.duowan.ark.qa.CrashGuard.CRASH_CNT_DIR     // Catch: java.lang.Throwable -> L67 java.lang.Exception -> L69
            r1.append(r2)     // Catch: java.lang.Throwable -> L67 java.lang.Exception -> L69
            java.lang.String r1 = r1.toString()     // Catch: java.lang.Throwable -> L67 java.lang.Exception -> L69
            java.io.File r2 = new java.io.File     // Catch: java.lang.Throwable -> L67 java.lang.Exception -> L69
            r2.<init>(r1)     // Catch: java.lang.Throwable -> L67 java.lang.Exception -> L69
            r2.mkdirs()     // Catch: java.lang.Throwable -> L67 java.lang.Exception -> L69
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L67 java.lang.Exception -> L69
            r2.<init>()     // Catch: java.lang.Throwable -> L67 java.lang.Exception -> L69
            r2.append(r1)     // Catch: java.lang.Throwable -> L67 java.lang.Exception -> L69
            java.lang.String r1 = java.io.File.separator     // Catch: java.lang.Throwable -> L67 java.lang.Exception -> L69
            r2.append(r1)     // Catch: java.lang.Throwable -> L67 java.lang.Exception -> L69
            r2.append(r4)     // Catch: java.lang.Throwable -> L67 java.lang.Exception -> L69
            java.lang.String r4 = r2.toString()     // Catch: java.lang.Throwable -> L67 java.lang.Exception -> L69
            java.io.File r1 = new java.io.File     // Catch: java.lang.Throwable -> L67 java.lang.Exception -> L69
            r1.<init>(r4)     // Catch: java.lang.Throwable -> L67 java.lang.Exception -> L69
            boolean r1 = r1.exists()     // Catch: java.lang.Throwable -> L67 java.lang.Exception -> L69
            if (r1 == 0) goto L5c
            java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L67 java.lang.Exception -> L69
            r1.<init>(r4)     // Catch: java.lang.Throwable -> L67 java.lang.Exception -> L69
            java.io.BufferedReader r4 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L67 java.lang.Exception -> L69
            java.io.InputStreamReader r2 = new java.io.InputStreamReader     // Catch: java.lang.Throwable -> L67 java.lang.Exception -> L69
            r2.<init>(r1)     // Catch: java.lang.Throwable -> L67 java.lang.Exception -> L69
            r4.<init>(r2)     // Catch: java.lang.Throwable -> L67 java.lang.Exception -> L69
            java.lang.String r0 = r4.readLine()     // Catch: java.lang.Exception -> L5a java.lang.Throwable -> L76
            int r5 = java.lang.Integer.parseInt(r0)     // Catch: java.lang.Exception -> L5a java.lang.Throwable -> L76
            r0 = r4
            goto L5c
        L5a:
            r0 = move-exception
            goto L6d
        L5c:
            if (r0 == 0) goto L75
            r0.close()     // Catch: java.io.IOException -> L62
            goto L75
        L62:
            r4 = move-exception
            r4.printStackTrace()
            goto L75
        L67:
            r5 = move-exception
            goto L78
        L69:
            r4 = move-exception
            r3 = r0
            r0 = r4
            r4 = r3
        L6d:
            r0.printStackTrace()     // Catch: java.lang.Throwable -> L76
            if (r4 == 0) goto L75
            r4.close()     // Catch: java.io.IOException -> L62
        L75:
            return r5
        L76:
            r5 = move-exception
            r0 = r4
        L78:
            if (r0 == 0) goto L82
            r0.close()     // Catch: java.io.IOException -> L7e
            goto L82
        L7e:
            r4 = move-exception
            r4.printStackTrace()
        L82:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.duowan.ark.qa.CrashGuard._getInt(java.lang.String, int):int");
    }

    private static void _setInt(String str, int i) throws Throwable {
        BufferedWriter bufferedWriter;
        BufferedWriter bufferedWriter2 = null;
        try {
            try {
                try {
                    String str2 = Environment.getExternalStorageDirectory().getAbsolutePath() + CRASH_CNT_DIR;
                    new File(str2).mkdirs();
                    bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(str2 + File.separator + str)));
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
            } catch (Exception e2) {
                e = e2;
            }
        } catch (Throwable th) {
            th = th;
        }
        try {
            bufferedWriter.write(new Integer(i).toString());
            bufferedWriter.close();
        } catch (Exception e3) {
            e = e3;
            bufferedWriter2 = bufferedWriter;
            e.printStackTrace();
            if (bufferedWriter2 == null) {
            } else {
                bufferedWriter2.close();
            }
        } catch (Throwable th2) {
            th = th2;
            bufferedWriter2 = bufferedWriter;
            if (bufferedWriter2 != null) {
                try {
                    bufferedWriter2.close();
                } catch (IOException e4) {
                    e4.printStackTrace();
                }
            }
            throw th;
        }
    }

    public static int getTodayCrashCount() throws Throwable {
        int i_getInt = _getInt("not_clear" + todayKey(), 0);
        if (i_getInt > 0) {
            return i_getInt;
        }
        return Config.getInstance(ArkValue.gContext).getInt("not_clear" + todayKey(), 0);
    }

    private static void setToadyCrashCount(int i) throws Throwable {
        Config.getInstance(ArkValue.gContext).setIntSync("not_clear" + todayKey(), i);
        _setInt("not_clear" + todayKey(), i);
    }

    public int getTodayStartupCrashCount() {
        return ConfigWithTimeout.instance(ArkValue.gContext).getInteger(todayKey(), 0);
    }

    public int getLastCrashCount() {
        return Config.getInstance(ArkValue.gContext).getInt(LAST_CRASH_COUNT, 0);
    }
}
