package layaair.game.utility;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.os.Build;
import android.os.Process;
import android.util.Log;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class ProcessInfo {
    static String TAG = "PROCESSINFO";
    static long mLastWorkCpuTm;
    static long mWorkCpuTm;
    static long mnLastAppCpu;
    static long mnLastTotalCpu;
    static ActivityManager sAM;

    public static int GetFPS() {
        return 0;
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

    public static float getAvalidMem() {
        if (sAM == null) {
            return 0.0f;
        }
        sAM.getMemoryInfo(new ActivityManager.MemoryInfo());
        return r0.availMem / 1024.0f;
    }

    public static float getProcessCpuRate() {
        long totalCpuTime = getTotalCpuTime();
        float f = (float) ((r2 - mLastWorkCpuTm) / (totalCpuTime - mnLastTotalCpu));
        mLastWorkCpuTm = mWorkCpuTm;
        mnLastTotalCpu = totalCpuTime;
        return f;
    }

    public static float getProcessCpuRate1() {
        long totalCpuTime = getTotalCpuTime();
        long appCpuTime = getAppCpuTime();
        long j = appCpuTime - mnLastAppCpu;
        long j2 = totalCpuTime - mnLastTotalCpu;
        float f = (float) (j / j2);
        if (f > 1.0f) {
            Log.e("", "app:" + j + ",total:" + j2);
        }
        mnLastAppCpu = appCpuTime;
        mnLastTotalCpu = totalCpuTime;
        return f;
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
        long j = Long.parseLong(strArrSplit[2]) + Long.parseLong(strArrSplit[3]) + Long.parseLong(strArrSplit[4]) + Long.parseLong(strArrSplit[7]) + Long.parseLong(strArrSplit[8]);
        long j2 = Long.parseLong(strArrSplit[6]) + Long.parseLong(strArrSplit[5]);
        mWorkCpuTm = j;
        return j + j2;
    }

    @SuppressLint({"NewApi"})
    public static float getTotalMem() {
        if (Build.VERSION.SDK_INT < 16) {
            return getTotalMemoryOld();
        }
        if (sAM == null) {
            return 0.0f;
        }
        sAM.getMemoryInfo(new ActivityManager.MemoryInfo());
        float f = r0.totalMem / 1024.0f;
        Log.i("", "total:" + f);
        return f;
    }

    public static long getTotalMemoryOld() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("/proc/meminfo"), 8192);
            String line = bufferedReader.readLine();
            String[] strArrSplit = line.split("\\s+");
            for (String str : strArrSplit) {
                Log.i(line, str + "\t");
            }
            long jIntValue = Integer.valueOf(strArrSplit[1]).intValue();
            bufferedReader.close();
            return jIntValue;
        } catch (IOException unused) {
            return -1L;
        }
    }

    public static float getUsedMem() {
        if (sAM == null) {
            return 0.0f;
        }
        float totalPrivateDirty = sAM.getProcessMemoryInfo(new int[]{Process.myPid()})[0].getTotalPrivateDirty();
        Log.d(TAG, "占用内存：" + totalPrivateDirty);
        return totalPrivateDirty;
    }

    public static void init(ActivityManager activityManager) {
        sAM = activityManager;
    }

    public static boolean supportNeon() {
        BufferedReader bufferedReader;
        String line;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("/proc/cpuinfo")), 1000);
        } catch (IOException e) {
            e.printStackTrace();
        }
        do {
            line = bufferedReader.readLine();
            if (line == null) {
                bufferedReader.close();
                return false;
            }
        } while (!line.startsWith("Features"));
        return line.contains("neon");
    }

    public static void uninit() {
        sAM = null;
    }
}
