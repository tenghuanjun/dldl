package com.sqwan.bugless.anr;

import android.os.Process;
import android.util.Log;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class CpuSampler extends AbstractSampler {
    private static final int BUFFER_SIZE = 1000;
    private static final int MAX_ENTRY_COUNT = 10;
    private static final String TAG = "CpuSampler";
    private final int BUSY_TIME;
    private long mAppCpuTimeLast;
    private final LinkedHashMap<Long, String> mCpuInfoEntries;
    private long mIdleLast;
    private long mIoWaitLast;
    private int mPid;
    private long mSystemLast;
    private long mTotalLast;
    private long mUserLast;

    public CpuSampler(long sampleInterval) {
        super(sampleInterval);
        this.mCpuInfoEntries = new LinkedHashMap<>();
        this.mPid = 0;
        this.mUserLast = 0L;
        this.mSystemLast = 0L;
        this.mIdleLast = 0L;
        this.mIoWaitLast = 0L;
        this.mTotalLast = 0L;
        this.mAppCpuTimeLast = 0L;
        this.BUSY_TIME = (int) (this.mSampleInterval * 1.2f);
    }

    @Override // com.sqwan.bugless.anr.AbstractSampler
    protected void doSample() {
        BufferedReader bufferedReader;
        BufferedReader bufferedReader2 = null;
        try {
            try {
                BufferedReader bufferedReader3 = new BufferedReader(new InputStreamReader(new FileInputStream("/proc/stat")), 1000);
                try {
                    String line = bufferedReader3.readLine();
                    String str = "";
                    if (line == null) {
                        line = "";
                    }
                    if (this.mPid == 0) {
                        this.mPid = Process.myPid();
                    }
                    bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("/proc/" + this.mPid + "/stat")), 1000);
                    try {
                        String line2 = bufferedReader.readLine();
                        if (line2 != null) {
                            str = line2;
                        }
                        parse(line, str);
                        bufferedReader3.close();
                        bufferedReader.close();
                    } catch (Throwable th) {
                        th = th;
                        bufferedReader2 = bufferedReader3;
                        try {
                            Log.e(TAG, "doSample: ", th);
                            if (bufferedReader2 != null) {
                                bufferedReader2.close();
                            }
                            if (bufferedReader != null) {
                                bufferedReader.close();
                            }
                        } catch (Throwable th2) {
                            if (bufferedReader2 != null) {
                                try {
                                    bufferedReader2.close();
                                } catch (IOException e) {
                                    Log.e(TAG, "doSample: ", e);
                                    throw th2;
                                }
                            }
                            if (bufferedReader != null) {
                                bufferedReader.close();
                            }
                            throw th2;
                        }
                    }
                } catch (Throwable th3) {
                    th = th3;
                    bufferedReader = null;
                }
            } catch (IOException e2) {
                Log.e(TAG, "doSample: ", e2);
            }
        } catch (Throwable th4) {
            th = th4;
            bufferedReader = null;
        }
    }

    private void parse(String cpuRate, String pidCpuRate) {
        long j;
        long j2;
        String[] strArrSplit = cpuRate.split(" ");
        if (strArrSplit.length < 9) {
            return;
        }
        long j3 = Long.parseLong(strArrSplit[2]);
        long j4 = Long.parseLong(strArrSplit[3]);
        long j5 = Long.parseLong(strArrSplit[4]);
        long j6 = Long.parseLong(strArrSplit[5]);
        long j7 = Long.parseLong(strArrSplit[6]);
        long j8 = j4 + j3 + j5 + j6 + j7 + Long.parseLong(strArrSplit[7]) + Long.parseLong(strArrSplit[8]);
        String[] strArrSplit2 = pidCpuRate.split(" ");
        if (strArrSplit2.length < 17) {
            return;
        }
        long j9 = Long.parseLong(strArrSplit2[13]) + Long.parseLong(strArrSplit2[14]) + Long.parseLong(strArrSplit2[15]) + Long.parseLong(strArrSplit2[16]);
        if (this.mTotalLast != 0) {
            StringBuilder sb = new StringBuilder();
            long j10 = j6 - this.mIdleLast;
            j2 = j6;
            long j11 = j8 - this.mTotalLast;
            j = j8;
            sb.append("cpu:");
            sb.append(((j11 - j10) * 100) / j11);
            sb.append("% ");
            sb.append("app:");
            sb.append(((j9 - this.mAppCpuTimeLast) * 100) / j11);
            sb.append("% ");
            sb.append("[");
            sb.append("user:");
            sb.append(((j3 - this.mUserLast) * 100) / j11);
            sb.append("% ");
            sb.append("system:");
            sb.append(((j5 - this.mSystemLast) * 100) / j11);
            sb.append("% ");
            sb.append("ioWait:");
            sb.append(((j7 - this.mIoWaitLast) * 100) / j11);
            sb.append("% ]");
            synchronized (this.mCpuInfoEntries) {
                this.mCpuInfoEntries.put(Long.valueOf(System.currentTimeMillis()), sb.toString());
                if (this.mCpuInfoEntries.size() > 10) {
                    Iterator<Map.Entry<Long, String>> it = this.mCpuInfoEntries.entrySet().iterator();
                    if (it.hasNext()) {
                        this.mCpuInfoEntries.remove(it.next().getKey());
                    }
                }
            }
        } else {
            j = j8;
            j2 = j6;
        }
        this.mUserLast = j3;
        this.mSystemLast = j5;
        this.mIdleLast = j2;
        this.mIoWaitLast = j7;
        this.mTotalLast = j;
        this.mAppCpuTimeLast = j9;
    }
}
