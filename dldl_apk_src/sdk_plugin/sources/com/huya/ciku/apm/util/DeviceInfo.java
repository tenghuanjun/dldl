package com.huya.ciku.apm.util;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.os.Debug;
import android.os.Process;
import android.provider.Settings;
import com.duowan.monitor.utility.MonitorThread;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class DeviceInfo {
    public static final int PID = Process.myPid();
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
        public float app;
        public long cpu;
        public long ioWait;
        public long system;
        public long user;
    }

    private DeviceInfo(Context context) {
        this.mContext = context;
    }

    public static synchronized void init(Context context) {
        if (sInstance == null) {
            if (context == null) {
                throw new NullPointerException("context can't be null");
            }
            sInstance = new DeviceInfo(context);
        }
    }

    public static DeviceInfo getInstance() {
        return sInstance;
    }

    public void collectCpu(long j, final CollectCpuCallback collectCpuCallback) {
        if (j <= 0 || collectCpuCallback == null) {
            return;
        }
        MonitorThread.execute(new CpuCollectTask(j, new CollectCpuCallback() { // from class: com.huya.ciku.apm.util.DeviceInfo.1
            @Override // com.huya.ciku.apm.util.DeviceInfo.CollectCpuCallback
            public void onComplete(final CpuInfo cpuInfo) {
                MonitorThread.runOnMonitorThread(new Runnable() { // from class: com.huya.ciku.apm.util.DeviceInfo.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        collectCpuCallback.onComplete(cpuInfo);
                    }
                });
            }
        }));
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
        throw new UnsupportedOperationException("Method not decompiled: com.huya.ciku.apm.util.DeviceInfo.collectPhoneTotalMemory():long");
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
        public void run() {
            if (Build.VERSION.SDK_INT < 26) {
                long[] cpuInfo = CpuUtil.readCpuInfo();
                if (cpuInfo != null) {
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
                    if (cpuInfo2.cpu < 0 || cpuInfo2.app < 0.0f || cpuInfo2.user < 0 || cpuInfo2.system < 0 || cpuInfo2.ioWait < 0) {
                        return;
                    }
                    this.mCallback.onComplete(cpuInfo2);
                    return;
                }
                return;
            }
            CpuInfo cpuInfo3 = new CpuInfo();
            cpuInfo3.app = CpuUtil.getSelfCpuByTop();
            this.mCallback.onComplete(cpuInfo3);
        }
    }
}
