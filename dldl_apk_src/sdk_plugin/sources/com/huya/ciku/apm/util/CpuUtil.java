package com.huya.ciku.apm.util;

import android.os.Build;
import android.os.Process;
import android.util.Log;
import com.duowan.auk.util.L;
import com.duowan.monitor.utility.MonitorLog;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class CpuUtil {
    private static final int DEVICEINFO_UNKNOWN = -1;
    private static final String TAG = CpuUtil.class.getSimpleName();
    private static final FileFilter CPU_FILTER = new FileFilter() { // from class: com.huya.ciku.apm.util.CpuUtil.1
        @Override // java.io.FileFilter
        public boolean accept(File file) {
            String name = file.getName();
            if (!name.startsWith("cpu")) {
                return false;
            }
            for (int i = 3; i < name.length(); i++) {
                if (name.charAt(i) < '0' || name.charAt(i) > '9') {
                    return false;
                }
            }
            return true;
        }
    };

    public static long[] readCpuInfo() {
        BufferedReader bufferedReader;
        Throwable th;
        BufferedReader bufferedReader2;
        BufferedReader bufferedReader3;
        try {
            try {
                bufferedReader2 = new BufferedReader(new InputStreamReader(new FileInputStream("/proc/stat")), 1000);
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (IOException e) {
            e = e;
            bufferedReader2 = null;
            bufferedReader3 = null;
        } catch (Exception e2) {
            e = e2;
            bufferedReader2 = null;
            bufferedReader3 = null;
        } catch (Throwable th3) {
            bufferedReader = null;
            th = th3;
            bufferedReader2 = null;
        }
        try {
            String line = bufferedReader2.readLine();
            if (line == null) {
                try {
                    bufferedReader2.close();
                } catch (IOException e3) {
                    MonitorLog.e(TAG, "readCpuInfo: ", e3);
                }
                return null;
            }
            bufferedReader3 = new BufferedReader(new InputStreamReader(new FileInputStream("/proc/" + DeviceInfo.PID + "/stat")), 1000);
            try {
                String line2 = bufferedReader3.readLine();
                if (line2 == null) {
                    try {
                        bufferedReader2.close();
                        bufferedReader3.close();
                    } catch (IOException e4) {
                        MonitorLog.e(TAG, "readCpuInfo: ", e4);
                    }
                    return (long[]) null;
                }
                long[] jArr = parse(line, line2);
                try {
                    bufferedReader2.close();
                    bufferedReader3.close();
                } catch (IOException e5) {
                    MonitorLog.e(TAG, "readCpuInfo: ", e5);
                }
                return jArr;
            } catch (IOException e6) {
                e = e6;
            } catch (Exception e7) {
                e = e7;
                MonitorLog.e(TAG, "readCpuInfo: ", e);
                if (bufferedReader2 != null) {
                    try {
                        bufferedReader2.close();
                    } catch (IOException e8) {
                        MonitorLog.e(TAG, "readCpuInfo: ", e8);
                        return null;
                    }
                }
                if (bufferedReader3 != null) {
                    bufferedReader3.close();
                }
                return null;
            }
        } catch (IOException e9) {
            e = e9;
            bufferedReader3 = null;
        } catch (Exception e10) {
            e = e10;
            bufferedReader3 = null;
        } catch (Throwable th4) {
            bufferedReader = null;
            th = th4;
            if (bufferedReader2 != null) {
                try {
                    bufferedReader2.close();
                } catch (IOException e11) {
                    MonitorLog.e(TAG, "readCpuInfo: ", e11);
                    throw th;
                }
            }
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            throw th;
        }
        MonitorLog.e(TAG, "readCpuInfo: ", e);
        if (bufferedReader2 != null) {
            try {
                bufferedReader2.close();
            } catch (IOException e12) {
                MonitorLog.e(TAG, "readCpuInfo: ", e12);
            }
        }
        if (bufferedReader3 != null) {
            bufferedReader3.close();
        }
        return null;
    }

    private static long[] parse(String str, String str2) {
        L.debug(TAG, "parse() called with: cpuRate = [" + str + "], pidCpuRate = [" + str2 + "]");
        String[] strArrSplit = str.split(" ");
        if (strArrSplit.length < 9) {
            return null;
        }
        long j = Long.parseLong(strArrSplit[2]);
        long j2 = Long.parseLong(strArrSplit[3]);
        long j3 = Long.parseLong(strArrSplit[4]);
        long j4 = Long.parseLong(strArrSplit[5]);
        long j5 = Long.parseLong(strArrSplit[6]);
        long j6 = j2 + j + j3 + j4 + j5 + Long.parseLong(strArrSplit[7]) + Long.parseLong(strArrSplit[8]);
        String[] strArrSplit2 = str2.split(" ");
        if (strArrSplit2.length < 17) {
            return null;
        }
        return new long[]{j, j3, j4, j5, j6, Long.parseLong(strArrSplit2[13]) + Long.parseLong(strArrSplit2[14]) + Long.parseLong(strArrSplit2[15]) + Long.parseLong(strArrSplit2[16])};
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x0052, code lost:
    
        r1 = r4.floatValue();
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0056, code lost:
    
        if (r0 == null) goto L19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0058, code lost:
    
        r0.destroy();
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x005b, code lost:
    
        return r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0067, code lost:
    
        r0.destroy();
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x006a, code lost:
    
        return 0.0f;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static float getSelfCpuByTop() {
        /*
            r0 = 0
            java.lang.Runtime r1 = java.lang.Runtime.getRuntime()     // Catch: java.lang.Throwable -> L5f java.io.IOException -> L61
            java.lang.String r2 = "top -n 1"
            java.lang.Process r0 = r1.exec(r2)     // Catch: java.lang.Throwable -> L5f java.io.IOException -> L61
            java.io.BufferedReader r1 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L5f java.io.IOException -> L61
            java.io.InputStreamReader r2 = new java.io.InputStreamReader     // Catch: java.lang.Throwable -> L5f java.io.IOException -> L61
            java.io.InputStream r3 = r0.getInputStream()     // Catch: java.lang.Throwable -> L5f java.io.IOException -> L61
            r2.<init>(r3)     // Catch: java.lang.Throwable -> L5f java.io.IOException -> L61
            r1.<init>(r2)     // Catch: java.lang.Throwable -> L5f java.io.IOException -> L61
            r2 = -1
            r3 = -1
        L1b:
            java.lang.String r4 = r1.readLine()     // Catch: java.lang.Throwable -> L5f java.io.IOException -> L61
            if (r4 == 0) goto L5c
            java.lang.String r4 = r4.trim()     // Catch: java.lang.Throwable -> L5f java.io.IOException -> L61
            boolean r5 = android.text.TextUtils.isEmpty(r4)     // Catch: java.lang.Throwable -> L5f java.io.IOException -> L61
            if (r5 == 0) goto L2c
            goto L1b
        L2c:
            java.lang.String r5 = com.huya.ciku.apm.util.CpuUtil.TAG     // Catch: java.lang.Throwable -> L5f java.io.IOException -> L61
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L5f java.io.IOException -> L61
            r6.<init>()     // Catch: java.lang.Throwable -> L5f java.io.IOException -> L61
            java.lang.String r7 = "getSelfCpuByTop() called "
            r6.append(r7)     // Catch: java.lang.Throwable -> L5f java.io.IOException -> L61
            r6.append(r4)     // Catch: java.lang.Throwable -> L5f java.io.IOException -> L61
            java.lang.String r6 = r6.toString()     // Catch: java.lang.Throwable -> L5f java.io.IOException -> L61
            android.util.Log.d(r5, r6)     // Catch: java.lang.Throwable -> L5f java.io.IOException -> L61
            if (r3 != r2) goto L4c
            int r4 = getCPUIndex(r4)     // Catch: java.lang.Throwable -> L5f java.io.IOException -> L61
            if (r4 == r2) goto L1b
            r3 = r4
            goto L1b
        L4c:
            java.lang.Float r4 = getProcessCpu(r4, r3)     // Catch: java.lang.Throwable -> L5f java.io.IOException -> L61
            if (r4 == 0) goto L1b
            float r1 = r4.floatValue()     // Catch: java.lang.Throwable -> L5f java.io.IOException -> L61
            if (r0 == 0) goto L5b
            r0.destroy()
        L5b:
            return r1
        L5c:
            if (r0 == 0) goto L6a
            goto L67
        L5f:
            r1 = move-exception
            goto L6c
        L61:
            r1 = move-exception
            r1.printStackTrace()     // Catch: java.lang.Throwable -> L5f
            if (r0 == 0) goto L6a
        L67:
            r0.destroy()
        L6a:
            r0 = 0
            return r0
        L6c:
            if (r0 == 0) goto L71
            r0.destroy()
        L71:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huya.ciku.apm.util.CpuUtil.getSelfCpuByTop():float");
    }

    public static Float getProcessCpu(String str, int i) {
        if (!str.startsWith(String.valueOf(Process.myPid())) || i == -1) {
            return null;
        }
        String[] strArrSplit = str.split("\\s+");
        if (strArrSplit.length <= i) {
            return null;
        }
        String strSubstring = strArrSplit[i];
        if (strSubstring.endsWith("%")) {
            strSubstring = strSubstring.substring(0, strSubstring.lastIndexOf("%"));
        }
        float f = 0.0f;
        try {
            f = Float.parseFloat(strSubstring) / Runtime.getRuntime().availableProcessors();
        } catch (Exception unused) {
        }
        return Float.valueOf(f);
    }

    private static int getCPUIndex(String str) {
        if (!str.contains("CPU")) {
            return -1;
        }
        String[] strArrSplit = str.split("\\s+");
        for (int i = 0; i < strArrSplit.length; i++) {
            if (strArrSplit[i].contains("CPU")) {
                return i;
            }
        }
        return -1;
    }

    @Deprecated
    public static int getTotalCpuByTop(String str) {
        if (str == null || str.length() == 0) {
            return -1;
        }
        int i = 1;
        int i2 = 0;
        for (String str2 : str.split(" ")) {
            int iIndexOf = str2.indexOf("%cpu");
            if (iIndexOf != -1) {
                i = Integer.parseInt(str2.substring(0, iIndexOf));
                System.out.println("cpu " + i);
            }
            int iIndexOf2 = str2.indexOf("%idle");
            if (iIndexOf2 != -1) {
                i2 = Integer.parseInt(str2.substring(0, iIndexOf2));
                System.out.println("idle " + i2);
            }
        }
        if (i == 0) {
            return -1;
        }
        return ((i - i2) * 100) / i;
    }

    public static int getCpuFreqPercent() {
        int numberOfCPUCores = getNumberOfCPUCores();
        int iTakeCurrentCpuFreq = 0;
        int iTakeMinCpuFreq = 0;
        int iTakeMaxCpuFreq = 0;
        for (int i = 0; i < numberOfCPUCores; i++) {
            iTakeMinCpuFreq += takeMinCpuFreq(i);
            iTakeMaxCpuFreq += takeMaxCpuFreq(i);
            iTakeCurrentCpuFreq += takeCurrentCpuFreq(i);
        }
        int clockPercent = getClockPercent(iTakeCurrentCpuFreq, iTakeMinCpuFreq, iTakeMaxCpuFreq);
        Log.d(TAG, "getCpuFreqPercent() called :" + clockPercent);
        return clockPercent;
    }

    public static int getClockPercent(int i, int i2, int i3) {
        int i4 = i3 - i2;
        if (i4 <= 0 || i3 <= 0) {
            return 0;
        }
        return ((i - i2) * 100) / i4;
    }

    public static int getNumberOfCPUCores() {
        if (Build.VERSION.SDK_INT <= 10) {
            return 1;
        }
        try {
            return new File("/sys/devices/system/cpu/").listFiles(CPU_FILTER).length;
        } catch (NullPointerException | SecurityException unused) {
            return -1;
        }
    }

    private static int takeCurrentCpuFreq(int i) {
        return readIntegerFile("/sys/devices/system/cpu/cpu" + i + "/cpufreq/scaling_cur_freq");
    }

    private static int takeMinCpuFreq(int i) {
        return readIntegerFile("/sys/devices/system/cpu/cpu" + i + "/cpufreq/cpuinfo_min_freq");
    }

    private static int takeMaxCpuFreq(int i) {
        return readIntegerFile("/sys/devices/system/cpu/cpu" + i + "/cpufreq/cpuinfo_max_freq");
    }

    private static int readIntegerFile(String str) {
        try {
            return Integer.parseInt(new BufferedReader(new InputStreamReader(new FileInputStream(str)), 1000).readLine());
        } catch (Exception unused) {
            return 0;
        }
    }
}
