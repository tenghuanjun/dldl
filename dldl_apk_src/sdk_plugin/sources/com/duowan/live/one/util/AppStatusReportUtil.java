package com.duowan.live.one.util;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Process;
import android.support.v4.media.session.PlaybackStateCompat;
import com.duowan.auk.ArkValue;
import com.duowan.auk.util.VersionUtil;
import java.util.Locale;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class AppStatusReportUtil {
    private static boolean mCpuNotRead;

    public static long getUnusedMemory(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        activityManager.getMemoryInfo(memoryInfo);
        return memoryInfo.availMem / PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0042  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x005e A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0065 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r0v2 */
    /* JADX WARN: Type inference failed for: r0v3, types: [boolean] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:10:0x001d -> B:46:0x003c). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static long getTotalMemory() throws java.lang.Throwable {
        /*
            java.lang.String r0 = "getTotalMemory"
            java.lang.String r1 = "/proc/meminfo"
            r2 = 0
            java.io.BufferedReader r3 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L25 java.io.IOException -> L27 java.io.FileNotFoundException -> L32
            java.io.FileReader r4 = new java.io.FileReader     // Catch: java.lang.Throwable -> L25 java.io.IOException -> L27 java.io.FileNotFoundException -> L32
            r4.<init>(r1)     // Catch: java.lang.Throwable -> L25 java.io.IOException -> L27 java.io.FileNotFoundException -> L32
            r1 = 8
            r3.<init>(r4, r1)     // Catch: java.lang.Throwable -> L25 java.io.IOException -> L27 java.io.FileNotFoundException -> L32
            java.lang.String r1 = r3.readLine()     // Catch: java.io.IOException -> L21 java.io.FileNotFoundException -> L23 java.lang.Throwable -> L61
            if (r1 == 0) goto L18
            r2 = r1
        L18:
            r3.close()     // Catch: java.io.IOException -> L1c
            goto L3c
        L1c:
            r1 = move-exception
            com.duowan.auk.util.L.error(r0, r1)
            goto L3c
        L21:
            r1 = move-exception
            goto L29
        L23:
            r1 = move-exception
            goto L34
        L25:
            r1 = move-exception
            goto L63
        L27:
            r1 = move-exception
            r3 = r2
        L29:
            r1.printStackTrace()     // Catch: java.lang.Throwable -> L61
            if (r3 == 0) goto L3c
            r3.close()     // Catch: java.io.IOException -> L1c
            goto L3c
        L32:
            r1 = move-exception
            r3 = r2
        L34:
            r1.printStackTrace()     // Catch: java.lang.Throwable -> L61
            if (r3 == 0) goto L3c
            r3.close()     // Catch: java.io.IOException -> L1c
        L3c:
            boolean r0 = android.text.TextUtils.isEmpty(r2)
            if (r0 != 0) goto L5e
            r0 = 58
            int r0 = r2.indexOf(r0)
            r1 = 107(0x6b, float:1.5E-43)
            int r1 = r2.indexOf(r1)
            int r0 = r0 + 1
            java.lang.String r0 = r2.substring(r0, r1)
            java.lang.String r0 = r0.trim()
            int r0 = java.lang.Integer.parseInt(r0)
            long r0 = (long) r0
            return r0
        L5e:
            r0 = 0
            return r0
        L61:
            r1 = move-exception
            r2 = r3
        L63:
            if (r2 == 0) goto L6d
            r2.close()     // Catch: java.io.IOException -> L69
            goto L6d
        L69:
            r2 = move-exception
            com.duowan.auk.util.L.error(r0, r2)
        L6d:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.duowan.live.one.util.AppStatusReportUtil.getTotalMemory():long");
    }

    public static String getProcessMemoryInfo(int i) throws Throwable {
        long totalMemory = getTotalMemory();
        long unusedMemory = getUnusedMemory(ArkValue.gContext);
        Locale locale = Locale.CHINA;
        Object[] objArr = new Object[2];
        objArr[0] = Double.valueOf(totalMemory == 0 ? 0.0d : ((totalMemory - unusedMemory) / totalMemory) * 100.0d);
        objArr[1] = Long.valueOf(totalMemory);
        return String.format(locale, "%.2f%%,总内存%d", objArr);
    }

    public static long getProcessMemory() {
        return getTotalMemory() - getUnusedMemory(ArkValue.gContext);
    }

    public static String getMemoryUsage() {
        return getProcessMemoryInfo(Process.myPid());
    }

    public static String getCpuUsage() {
        return String.format(Locale.CHINA, "%.2f%%", Float.valueOf(getProcessCpuRate()));
    }

    public static float getProcessCpuRate() {
        float totalCpuTime = getTotalCpuTime();
        float appCpuTime = getAppCpuTime();
        try {
            Thread.sleep(360L);
        } catch (Exception e) {
            e.fillInStackTrace();
        }
        float totalCpuTime2 = getTotalCpuTime() - totalCpuTime;
        float appCpuTime2 = totalCpuTime2 != 0.0f ? ((getAppCpuTime() - appCpuTime) * 100.0f) / totalCpuTime2 : 0.0f;
        if (appCpuTime2 > 100.0f) {
            return 100.0f;
        }
        return appCpuTime2;
    }

    /* JADX WARN: Removed duplicated region for block: B:54:0x009f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static long getTotalCpuTime() throws java.lang.Throwable {
        /*
            boolean r0 = com.duowan.live.one.util.AppStatusReportUtil.mCpuNotRead
            r1 = 0
            if (r0 == 0) goto L7
            return r1
        L7:
            r0 = 0
            r3 = 1
            java.io.BufferedReader r4 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L6f java.lang.OutOfMemoryError -> L73 java.io.IOException -> L88
            java.io.InputStreamReader r5 = new java.io.InputStreamReader     // Catch: java.lang.Throwable -> L6f java.lang.OutOfMemoryError -> L73 java.io.IOException -> L88
            java.io.FileInputStream r6 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L6f java.lang.OutOfMemoryError -> L73 java.io.IOException -> L88
            java.lang.String r7 = "/proc/stat"
            r6.<init>(r7)     // Catch: java.lang.Throwable -> L6f java.lang.OutOfMemoryError -> L73 java.io.IOException -> L88
            r5.<init>(r6)     // Catch: java.lang.Throwable -> L6f java.lang.OutOfMemoryError -> L73 java.io.IOException -> L88
            r6 = 1000(0x3e8, float:1.401E-42)
            r4.<init>(r5, r6)     // Catch: java.lang.Throwable -> L6f java.lang.OutOfMemoryError -> L73 java.io.IOException -> L88
            java.lang.String r0 = r4.readLine()     // Catch: java.lang.OutOfMemoryError -> L6b java.io.IOException -> L6d java.lang.Throwable -> L9c
            java.lang.String r5 = " "
            java.lang.String[] r0 = r0.split(r5)     // Catch: java.lang.OutOfMemoryError -> L6b java.io.IOException -> L6d java.lang.Throwable -> L9c
            r5 = 2
            r5 = r0[r5]     // Catch: java.lang.Exception -> L60 java.lang.OutOfMemoryError -> L6b java.io.IOException -> L6d java.lang.Throwable -> L9c
            long r5 = java.lang.Long.parseLong(r5)     // Catch: java.lang.Exception -> L60 java.lang.OutOfMemoryError -> L6b java.io.IOException -> L6d java.lang.Throwable -> L9c
            r7 = 3
            r7 = r0[r7]     // Catch: java.lang.Exception -> L60 java.lang.OutOfMemoryError -> L6b java.io.IOException -> L6d java.lang.Throwable -> L9c
            long r7 = java.lang.Long.parseLong(r7)     // Catch: java.lang.Exception -> L60 java.lang.OutOfMemoryError -> L6b java.io.IOException -> L6d java.lang.Throwable -> L9c
            long r5 = r5 + r7
            r7 = 4
            r7 = r0[r7]     // Catch: java.lang.Exception -> L60 java.lang.OutOfMemoryError -> L6b java.io.IOException -> L6d java.lang.Throwable -> L9c
            long r7 = java.lang.Long.parseLong(r7)     // Catch: java.lang.Exception -> L60 java.lang.OutOfMemoryError -> L6b java.io.IOException -> L6d java.lang.Throwable -> L9c
            long r5 = r5 + r7
            r7 = 6
            r7 = r0[r7]     // Catch: java.lang.Exception -> L60 java.lang.OutOfMemoryError -> L6b java.io.IOException -> L6d java.lang.Throwable -> L9c
            long r7 = java.lang.Long.parseLong(r7)     // Catch: java.lang.Exception -> L60 java.lang.OutOfMemoryError -> L6b java.io.IOException -> L6d java.lang.Throwable -> L9c
            long r5 = r5 + r7
            r7 = 5
            r7 = r0[r7]     // Catch: java.lang.Exception -> L60 java.lang.OutOfMemoryError -> L6b java.io.IOException -> L6d java.lang.Throwable -> L9c
            long r7 = java.lang.Long.parseLong(r7)     // Catch: java.lang.Exception -> L60 java.lang.OutOfMemoryError -> L6b java.io.IOException -> L6d java.lang.Throwable -> L9c
            long r5 = r5 + r7
            r7 = 7
            r7 = r0[r7]     // Catch: java.lang.Exception -> L60 java.lang.OutOfMemoryError -> L6b java.io.IOException -> L6d java.lang.Throwable -> L9c
            long r7 = java.lang.Long.parseLong(r7)     // Catch: java.lang.Exception -> L60 java.lang.OutOfMemoryError -> L6b java.io.IOException -> L6d java.lang.Throwable -> L9c
            long r5 = r5 + r7
            r7 = 8
            r0 = r0[r7]     // Catch: java.lang.Exception -> L60 java.lang.OutOfMemoryError -> L6b java.io.IOException -> L6d java.lang.Throwable -> L9c
            long r0 = java.lang.Long.parseLong(r0)     // Catch: java.lang.Exception -> L60 java.lang.OutOfMemoryError -> L6b java.io.IOException -> L6d java.lang.Throwable -> L9c
            long r1 = r5 + r0
            goto L62
        L60:
            com.duowan.live.one.util.AppStatusReportUtil.mCpuNotRead = r3     // Catch: java.lang.OutOfMemoryError -> L6b java.io.IOException -> L6d java.lang.Throwable -> L9c
        L62:
            r4.close()     // Catch: java.io.IOException -> L66
            goto L6a
        L66:
            r0 = move-exception
            r0.printStackTrace()
        L6a:
            return r1
        L6b:
            r0 = move-exception
            goto L76
        L6d:
            r0 = move-exception
            goto L8c
        L6f:
            r1 = move-exception
            r4 = r0
            r0 = r1
            goto L9d
        L73:
            r3 = move-exception
            r4 = r0
            r0 = r3
        L76:
            java.lang.String r0 = r0.toString()     // Catch: java.lang.Throwable -> L9c
            com.duowan.auk.util.L.error(r0)     // Catch: java.lang.Throwable -> L9c
            if (r4 == 0) goto L87
            r4.close()     // Catch: java.io.IOException -> L83
            goto L87
        L83:
            r0 = move-exception
            r0.printStackTrace()
        L87:
            return r1
        L88:
            r4 = move-exception
            r9 = r4
            r4 = r0
            r0 = r9
        L8c:
            com.duowan.live.one.util.AppStatusReportUtil.mCpuNotRead = r3     // Catch: java.lang.Throwable -> L9c
            r0.printStackTrace()     // Catch: java.lang.Throwable -> L9c
            if (r4 == 0) goto L9b
            r4.close()     // Catch: java.io.IOException -> L97
            goto L9b
        L97:
            r0 = move-exception
            r0.printStackTrace()
        L9b:
            return r1
        L9c:
            r0 = move-exception
        L9d:
            if (r4 == 0) goto La7
            r4.close()     // Catch: java.io.IOException -> La3
            goto La7
        La3:
            r1 = move-exception
            r1.printStackTrace()
        La7:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.duowan.live.one.util.AppStatusReportUtil.getTotalCpuTime():long");
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0056  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0085 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:45:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static long getAppCpuTime() throws java.lang.Throwable {
        /*
            r0 = 0
            int r1 = android.os.Process.myPid()     // Catch: java.lang.Throwable -> L44 java.io.IOException -> L48
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L44 java.io.IOException -> L48
            java.io.InputStreamReader r3 = new java.io.InputStreamReader     // Catch: java.lang.Throwable -> L44 java.io.IOException -> L48
            java.io.FileInputStream r4 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L44 java.io.IOException -> L48
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L44 java.io.IOException -> L48
            r5.<init>()     // Catch: java.lang.Throwable -> L44 java.io.IOException -> L48
            java.lang.String r6 = "/proc/"
            r5.append(r6)     // Catch: java.lang.Throwable -> L44 java.io.IOException -> L48
            r5.append(r1)     // Catch: java.lang.Throwable -> L44 java.io.IOException -> L48
            java.lang.String r1 = "/stat"
            r5.append(r1)     // Catch: java.lang.Throwable -> L44 java.io.IOException -> L48
            java.lang.String r1 = r5.toString()     // Catch: java.lang.Throwable -> L44 java.io.IOException -> L48
            r4.<init>(r1)     // Catch: java.lang.Throwable -> L44 java.io.IOException -> L48
            r3.<init>(r4)     // Catch: java.lang.Throwable -> L44 java.io.IOException -> L48
            r1 = 1000(0x3e8, float:1.401E-42)
            r2.<init>(r3, r1)     // Catch: java.lang.Throwable -> L44 java.io.IOException -> L48
            java.lang.String r1 = r2.readLine()     // Catch: java.io.IOException -> L42 java.lang.Throwable -> L82
            r2.close()     // Catch: java.io.IOException -> L42 java.lang.Throwable -> L82
            java.lang.String r3 = " "
            java.lang.String[] r0 = r1.split(r3)     // Catch: java.io.IOException -> L42 java.lang.Throwable -> L82
            r2.close()     // Catch: java.io.IOException -> L3d
            goto L52
        L3d:
            r1 = move-exception
            r1.printStackTrace()
            goto L52
        L42:
            r1 = move-exception
            goto L4a
        L44:
            r1 = move-exception
            r2 = r0
            r0 = r1
            goto L83
        L48:
            r1 = move-exception
            r2 = r0
        L4a:
            r1.printStackTrace()     // Catch: java.lang.Throwable -> L82
            if (r2 == 0) goto L52
            r2.close()     // Catch: java.io.IOException -> L3d
        L52:
            r1 = 0
            if (r0 == 0) goto L81
            r3 = 13
            r3 = r0[r3]     // Catch: java.lang.Exception -> L7b
            long r3 = java.lang.Long.parseLong(r3)     // Catch: java.lang.Exception -> L7b
            r5 = 14
            r5 = r0[r5]     // Catch: java.lang.Exception -> L7b
            long r5 = java.lang.Long.parseLong(r5)     // Catch: java.lang.Exception -> L7b
            long r3 = r3 + r5
            r5 = 15
            r5 = r0[r5]     // Catch: java.lang.Exception -> L7b
            long r5 = java.lang.Long.parseLong(r5)     // Catch: java.lang.Exception -> L7b
            long r3 = r3 + r5
            r5 = 16
            r0 = r0[r5]     // Catch: java.lang.Exception -> L7b
            long r0 = java.lang.Long.parseLong(r0)     // Catch: java.lang.Exception -> L7b
            long r1 = r3 + r0
            goto L81
        L7b:
            r0 = move-exception
            java.lang.String r3 = "getAppCpuTime exception"
            com.duowan.auk.util.L.error(r3, r0)
        L81:
            return r1
        L82:
            r0 = move-exception
        L83:
            if (r2 == 0) goto L8d
            r2.close()     // Catch: java.io.IOException -> L89
            goto L8d
        L89:
            r1 = move-exception
            r1.printStackTrace()
        L8d:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.duowan.live.one.util.AppStatusReportUtil.getAppCpuTime():long");
    }

    public static String getNetworkConnectionName() {
        Application application = ArkValue.gContext;
        if (NetworkUtil.isNetworkAvailable(application)) {
            return NetworkUtil.is2GOr3GActive(application) ? NetworkUtil.getNetWorkSubType(application) : NetworkUtil.isWifiActive(application) ? "wifi" : "unknown";
        }
        return "disconnected";
    }

    public static String getVersion() {
        return ArkValue.debuggable() ? String.format("%s-%s", VersionUtil.getLocalName(ArkValue.gContext), Integer.valueOf(ArkValue.versionCode())) : String.format("%s", VersionUtil.getLocalName(ArkValue.gContext));
    }

    public static String getYYSDKVersion() {
        try {
            return ArkValue.gContext.getPackageManager().getApplicationInfo(ArkValue.gContext.getPackageName(), 128).metaData.getString("YYSDK_VERSION");
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "";
        }
    }
}
