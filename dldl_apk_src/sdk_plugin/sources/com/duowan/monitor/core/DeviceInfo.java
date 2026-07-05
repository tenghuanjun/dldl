package com.duowan.monitor.core;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Debug;
import android.os.Process;
import android.provider.Settings;
import com.duowan.monitor.utility.MonitorLog;
import com.duowan.monitor.utility.MonitorThread;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class DeviceInfo {
    private static final int BUFFER_SIZE = 1000;
    private static final int PID = Process.myPid();
    private static final String TAG = "DeviceInfo";
    private static DeviceInfo sInstance;
    private Context mContext;
    private String mVersionName;
    private int mVersionCode = -1;
    private long mPhoneTotalMemory = 0;

    public interface CollectCpuCallback {
        void onComplete(CpuInfo cpuInfo);
    }

    public static class CpuInfo {
        public long app;
        public long cpu;
        public long ioWait;
        public long system;
        public long user;
    }

    private DeviceInfo(Context context) {
        this.mContext = context;
    }

    public static synchronized void init(Context context) {
        if (sInstance != null) {
            return;
        }
        if (context == null) {
            throw new NullPointerException("context can't be null");
        }
        sInstance = new DeviceInfo(context);
    }

    public static DeviceInfo getInstance() {
        return sInstance;
    }

    public void collectCpu(long j, final CollectCpuCallback collectCpuCallback) {
        if (j > 0 && collectCpuCallback != null) {
            MonitorThread.execute(new CpuCollectTask(j, new CollectCpuCallback() { // from class: com.duowan.monitor.core.DeviceInfo.1
                @Override // com.duowan.monitor.core.DeviceInfo.CollectCpuCallback
                public void onComplete(final CpuInfo cpuInfo) {
                    MonitorThread.runOnMonitorThread(new Runnable() { // from class: com.duowan.monitor.core.DeviceInfo.1.1
                        @Override // java.lang.Runnable
                        public void run() {
                            collectCpuCallback.onComplete(cpuInfo);
                        }
                    });
                }
            }));
        }
    }

    public long getFreeMemory() {
        return Runtime.getRuntime().freeMemory();
    }

    public long getAppMemory() {
        Debug.MemoryInfo[] processMemoryInfo;
        Context context = this.mContext;
        if (context == null || (processMemoryInfo = ((ActivityManager) context.getSystemService("activity")).getProcessMemoryInfo(new int[]{Process.myPid()})) == null || processMemoryInfo.length <= 0) {
            return 0L;
        }
        return processMemoryInfo[0].getTotalPss() * 1024;
    }

    public long getTotalMemory() {
        return Runtime.getRuntime().totalMemory();
    }

    public long getAllocateMemory() {
        return Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
    }

    public long getMaxMemory() {
        return Runtime.getRuntime().maxMemory();
    }

    public double getPssRatio() throws Throwable {
        long jCollectPhoneTotalMemory = collectPhoneTotalMemory();
        if (jCollectPhoneTotalMemory > 0) {
            return (getAppMemory() * 1.0d) / jCollectPhoneTotalMemory;
        }
        return 0.0d;
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x005b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private long collectPhoneTotalMemory() throws java.lang.Throwable {
        /*
            r9 = this;
            java.lang.String r0 = "collectPhoneTotalMemory close error"
            java.lang.String r1 = "DeviceInfo"
            long r2 = r9.mPhoneTotalMemory
            r4 = 0
            int r6 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r6 <= 0) goto Ld
            return r2
        Ld:
            r2 = 0
            java.io.FileReader r3 = new java.io.FileReader     // Catch: java.lang.Throwable -> L3d java.lang.Exception -> L42
            java.lang.String r4 = "/proc/meminfo"
            r3.<init>(r4)     // Catch: java.lang.Throwable -> L3d java.lang.Exception -> L42
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch: java.lang.Exception -> L3b java.lang.Throwable -> L58
            r4 = 8192(0x2000, float:1.148E-41)
            r2.<init>(r3, r4)     // Catch: java.lang.Exception -> L3b java.lang.Throwable -> L58
            java.lang.String r2 = r2.readLine()     // Catch: java.lang.Exception -> L3b java.lang.Throwable -> L58
            java.lang.String r4 = "\\s+"
            java.lang.String[] r2 = r2.split(r4)     // Catch: java.lang.Exception -> L3b java.lang.Throwable -> L58
            r4 = 1
            r2 = r2[r4]     // Catch: java.lang.Exception -> L3b java.lang.Throwable -> L58
            java.lang.Long r2 = java.lang.Long.valueOf(r2)     // Catch: java.lang.Exception -> L3b java.lang.Throwable -> L58
            long r4 = r2.longValue()     // Catch: java.lang.Exception -> L3b java.lang.Throwable -> L58
            r6 = 1024(0x400, double:5.06E-321)
            long r4 = r4 * r6
            r9.mPhoneTotalMemory = r4     // Catch: java.lang.Exception -> L3b java.lang.Throwable -> L58
            r3.close()     // Catch: java.io.IOException -> L51
            goto L55
        L3b:
            r2 = move-exception
            goto L46
        L3d:
            r3 = move-exception
            r8 = r3
            r3 = r2
            r2 = r8
            goto L59
        L42:
            r3 = move-exception
            r8 = r3
            r3 = r2
            r2 = r8
        L46:
            java.lang.String r4 = "collectPhoneTotalMemory"
            com.duowan.monitor.utility.MonitorLog.e(r1, r4, r2)     // Catch: java.lang.Throwable -> L58
            if (r3 == 0) goto L55
            r3.close()     // Catch: java.io.IOException -> L51
            goto L55
        L51:
            r2 = move-exception
            com.duowan.monitor.utility.MonitorLog.e(r1, r0, r2)
        L55:
            long r0 = r9.mPhoneTotalMemory
            return r0
        L58:
            r2 = move-exception
        L59:
            if (r3 == 0) goto L63
            r3.close()     // Catch: java.io.IOException -> L5f
            goto L63
        L5f:
            r3 = move-exception
            com.duowan.monitor.utility.MonitorLog.e(r1, r0, r3)
        L63:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.duowan.monitor.core.DeviceInfo.collectPhoneTotalMemory():long");
    }

    public int getVersionCode() {
        if (this.mVersionCode == -1) {
            try {
                this.mVersionCode = this.mContext.getPackageManager().getPackageInfo(this.mContext.getPackageName(), 0).versionCode;
            } catch (Exception unused) {
                this.mVersionCode = 0;
            }
        }
        return this.mVersionCode;
    }

    public String getVersionName() {
        if (this.mVersionName == null) {
            try {
                this.mVersionName = this.mContext.getPackageManager().getPackageInfo(this.mContext.getPackageName(), 0).versionName;
            } catch (Exception unused) {
                this.mVersionName = "none";
            }
        }
        return this.mVersionName;
    }

    public String getDeviceId() {
        return Settings.Secure.getString(this.mContext.getContentResolver(), "android_id");
    }

    private static class CpuCollectTask implements Runnable {
        private final CollectCpuCallback mCallback;
        private final long mDuration;
        private long[] mLastCpuInfo;

        public CpuCollectTask(long j, CollectCpuCallback collectCpuCallback) {
            this.mDuration = j;
            this.mCallback = collectCpuCallback;
        }

        @Override // java.lang.Runnable
        public void run() throws Throwable {
            long[] cpuInfo = readCpuInfo();
            if (cpuInfo == null) {
                return;
            }
            long[] jArr = this.mLastCpuInfo;
            if (jArr == null) {
                this.mLastCpuInfo = cpuInfo;
                MonitorThread.postDelayed(this, this.mDuration);
                return;
            }
            long j = cpuInfo[2] - jArr[2];
            long j2 = cpuInfo[4] - jArr[4];
            if (j < 0 || j2 <= 0) {
                return;
            }
            CpuInfo cpuInfo2 = new CpuInfo();
            cpuInfo2.cpu = ((j2 - j) * 100) / j2;
            cpuInfo2.app = ((cpuInfo[5] - this.mLastCpuInfo[5]) * 100) / j2;
            cpuInfo2.user = ((cpuInfo[0] - this.mLastCpuInfo[0]) * 100) / j2;
            cpuInfo2.system = ((cpuInfo[1] - this.mLastCpuInfo[1]) * 100) / j2;
            cpuInfo2.ioWait = ((cpuInfo[3] - this.mLastCpuInfo[3]) * 100) / j2;
            if (cpuInfo2.cpu < 0 || cpuInfo2.app < 0 || cpuInfo2.user < 0 || cpuInfo2.system < 0 || cpuInfo2.ioWait < 0) {
                return;
            }
            this.mCallback.onComplete(cpuInfo2);
        }

        private long[] parse(String str, String str2) {
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

        private long[] readCpuInfo() throws Throwable {
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
                try {
                    String line = bufferedReader2.readLine();
                    if (line == null) {
                        try {
                            bufferedReader2.close();
                        } catch (IOException e) {
                            MonitorLog.e(DeviceInfo.TAG, "readCpuInfo: ", e);
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
                            } catch (IOException e2) {
                                MonitorLog.e(DeviceInfo.TAG, "readCpuInfo: ", e2);
                            }
                            return null;
                        }
                        long[] jArr = parse(line, line2);
                        try {
                            bufferedReader2.close();
                            bufferedReader3.close();
                        } catch (IOException e3) {
                            MonitorLog.e(DeviceInfo.TAG, "readCpuInfo: ", e3);
                        }
                        return jArr;
                    } catch (IOException e4) {
                        e = e4;
                    } catch (Exception e5) {
                        e = e5;
                        MonitorLog.e(DeviceInfo.TAG, "readCpuInfo: ", e);
                        if (bufferedReader2 != null) {
                            try {
                                bufferedReader2.close();
                            } catch (IOException e6) {
                                MonitorLog.e(DeviceInfo.TAG, "readCpuInfo: ", e6);
                                return null;
                            }
                        }
                        if (bufferedReader3 != null) {
                            bufferedReader3.close();
                        }
                        return null;
                    }
                } catch (IOException e7) {
                    e = e7;
                    bufferedReader3 = null;
                } catch (Exception e8) {
                    e = e8;
                    bufferedReader3 = null;
                } catch (Throwable th3) {
                    bufferedReader = null;
                    th = th3;
                    if (bufferedReader2 != null) {
                        try {
                            bufferedReader2.close();
                        } catch (IOException e9) {
                            MonitorLog.e(DeviceInfo.TAG, "readCpuInfo: ", e9);
                            throw th;
                        }
                    }
                    if (bufferedReader != null) {
                        bufferedReader.close();
                    }
                    throw th;
                }
            } catch (IOException e10) {
                e = e10;
                bufferedReader2 = null;
                bufferedReader3 = null;
            } catch (Exception e11) {
                e = e11;
                bufferedReader2 = null;
                bufferedReader3 = null;
            } catch (Throwable th4) {
                bufferedReader = null;
                th = th4;
                bufferedReader2 = null;
            }
            MonitorLog.e(DeviceInfo.TAG, "readCpuInfo: ", e);
            if (bufferedReader2 != null) {
                try {
                    bufferedReader2.close();
                } catch (IOException e12) {
                    MonitorLog.e(DeviceInfo.TAG, "readCpuInfo: ", e12);
                }
            }
            if (bufferedReader3 != null) {
                bufferedReader3.close();
            }
            return null;
        }
    }
}
