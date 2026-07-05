package com.huya.berry.gamesdk.utils;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Process;
import android.support.v4.media.session.PlaybackStateCompat;
import com.duowan.auk.ArkValue;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class AppStatusReportUtil {
    public static long getUnusedMemory(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        activityManager.getMemoryInfo(memoryInfo);
        return memoryInfo.availMem / PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0 */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v10 */
    /* JADX WARN: Type inference failed for: r1v11 */
    /* JADX WARN: Type inference failed for: r1v12 */
    /* JADX WARN: Type inference failed for: r1v13 */
    /* JADX WARN: Type inference failed for: r1v15 */
    /* JADX WARN: Type inference failed for: r1v16 */
    /* JADX WARN: Type inference failed for: r1v2, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r1v4 */
    /* JADX WARN: Type inference failed for: r1v5, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v6 */
    /* JADX WARN: Type inference failed for: r1v9 */
    /* JADX WARN: Type inference failed for: r2v6, types: [int] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:10:0x001b -> B:38:0x003a). Please report as a decompilation issue!!! */
    public static long getTotalMemory() throws Throwable {
        BufferedReader bufferedReader;
        ?? IndexOf;
        ?? r1 = 0;
        r1 = 0;
        r1 = 0;
        r1 = 0;
        r1 = 0;
        ?? r12 = 0;
        try {
        } catch (Throwable th) {
            th = th;
            r12 = IndexOf;
        }
        try {
            try {
                bufferedReader = new BufferedReader(new FileReader("/proc/meminfo"), 8);
            } catch (IOException e) {
                e.printStackTrace();
                r1 = r1;
            }
            try {
                String line = bufferedReader.readLine();
                String str = line != null ? line : null;
                bufferedReader.close();
                r1 = str;
            } catch (FileNotFoundException e2) {
                e = e2;
                e.printStackTrace();
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                int iIndexOf = r1.indexOf(58);
                IndexOf = r1.indexOf(107);
                return Integer.parseInt(r1.substring(iIndexOf + 1, IndexOf).trim());
            } catch (IOException e3) {
                e = e3;
                e.printStackTrace();
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                int iIndexOf2 = r1.indexOf(58);
                IndexOf = r1.indexOf(107);
                return Integer.parseInt(r1.substring(iIndexOf2 + 1, IndexOf).trim());
            }
        } catch (FileNotFoundException e4) {
            e = e4;
            bufferedReader = null;
        } catch (IOException e5) {
            e = e5;
            bufferedReader = null;
        } catch (Throwable th2) {
            th = th2;
            if (r12 != 0) {
                try {
                    r12.close();
                } catch (IOException e6) {
                    e6.printStackTrace();
                }
            }
            throw th;
        }
        int iIndexOf22 = r1.indexOf(58);
        IndexOf = r1.indexOf(107);
        return Integer.parseInt(r1.substring(iIndexOf22 + 1, IndexOf).trim());
    }

    public static String getProcessMemoryInfo(int i) throws Throwable {
        long totalMemory = getTotalMemory();
        return String.format("%.2f%%,总内存%d", Double.valueOf(((totalMemory - getUnusedMemory(ArkValue.gContext)) / totalMemory) * 100.0d), Long.valueOf(totalMemory));
    }

    public static long getProcessMemory() {
        return getTotalMemory() - getUnusedMemory(ArkValue.gContext);
    }

    public static String getMemoryUsage() {
        return getProcessMemoryInfo(Process.myPid());
    }

    public static String getCpuUsage() {
        return String.format("%f%%", Float.valueOf(getProcessCpuRate()));
    }

    public static float getProcessCpuRate() {
        float totalCpuTime = getTotalCpuTime();
        float appCpuTime = getAppCpuTime();
        try {
            Thread.sleep(360L);
        } catch (Exception unused) {
        }
        return ((getAppCpuTime() - appCpuTime) * 100.0f) / (getTotalCpuTime() - totalCpuTime);
    }

    public static long getTotalCpuTime() {
        String[] strArrSplit;
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("/proc/stat")), 1000);
            String line = bufferedReader.readLine();
            bufferedReader.close();
            strArrSplit = line.split(" ");
        } catch (IOException e) {
            e.printStackTrace();
            strArrSplit = null;
        }
        if (strArrSplit == null || strArrSplit.length < 9) {
            return -1L;
        }
        return Long.parseLong(strArrSplit[2]) + Long.parseLong(strArrSplit[3]) + Long.parseLong(strArrSplit[4]) + Long.parseLong(strArrSplit[6]) + Long.parseLong(strArrSplit[5]) + Long.parseLong(strArrSplit[7]) + Long.parseLong(strArrSplit[8]);
    }

    public static long getAppCpuTime() {
        String[] strArrSplit;
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("/proc/" + Process.myPid() + "/stat")), 1000);
            String line = bufferedReader.readLine();
            bufferedReader.close();
            strArrSplit = line.split(" ");
        } catch (IOException e) {
            e.printStackTrace();
            strArrSplit = null;
        }
        return Long.parseLong(strArrSplit[13]) + Long.parseLong(strArrSplit[14]) + Long.parseLong(strArrSplit[15]) + Long.parseLong(strArrSplit[16]);
    }

    public static String getNetworkConnectionName() {
        Application application = ArkValue.gContext;
        if (com.duowan.live.one.util.NetworkUtil.isNetworkAvailable(application)) {
            return com.duowan.live.one.util.NetworkUtil.is2GOr3GActive(application) ? com.duowan.live.one.util.NetworkUtil.getNetWorkSubType(application) : com.duowan.live.one.util.NetworkUtil.isWifiActive(application) ? "wifi" : "unknown";
        }
        return "disconnected";
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
