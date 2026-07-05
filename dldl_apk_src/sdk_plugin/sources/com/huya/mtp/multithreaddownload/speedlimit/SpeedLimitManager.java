package com.huya.mtp.multithreaddownload.speedlimit;

import java.util.HashMap;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class SpeedLimitManager {
    private static SpeedLimitManager mSpeedLimitManager;
    private SpeedLimit mGlobalSpeedLimit;
    private HashMap<String, SpeedLimit> mSpeedLimitMap = new HashMap<>();

    private SpeedLimitManager() {
    }

    public static synchronized SpeedLimitManager getInstance() {
        if (mSpeedLimitManager == null) {
            mSpeedLimitManager = new SpeedLimitManager();
        }
        return mSpeedLimitManager;
    }

    public synchronized void setGlobalSpeedLimit(long j) {
        if (this.mGlobalSpeedLimit == null) {
            this.mGlobalSpeedLimit = new SpeedLimit();
        }
        this.mGlobalSpeedLimit.setMaxSpped(j);
    }

    public synchronized void stopGlobalSpeedLimit() {
        this.mGlobalSpeedLimit = null;
    }

    public synchronized void setTaskSpeedLimit(String str, long j) {
        SpeedLimit speedLimit;
        String strTrim = str.trim();
        if (!this.mSpeedLimitMap.containsKey(strTrim)) {
            speedLimit = new SpeedLimit();
            this.mSpeedLimitMap.put(strTrim, speedLimit);
        } else {
            speedLimit = this.mSpeedLimitMap.get(strTrim);
        }
        speedLimit.setMaxSpped(j);
    }

    public synchronized void stopTaskSpeedLimit(String str) {
        if (this.mSpeedLimitMap.containsKey(str)) {
            this.mSpeedLimitMap.remove(str);
        }
    }

    public synchronized long getSleepTime(String str, long j) {
        long sleepTime;
        String strTrim = str.trim();
        sleepTime = this.mSpeedLimitMap.containsKey(strTrim) ? this.mSpeedLimitMap.get(strTrim).getSleepTime(j) : 0L;
        if (this.mGlobalSpeedLimit != null) {
            sleepTime = this.mGlobalSpeedLimit.getSleepTime(j);
        }
        return sleepTime;
    }

    public synchronized float getTaskSpeed(String str) {
        return this.mSpeedLimitMap.containsKey(str.trim()) ? this.mSpeedLimitMap.get(str.trim()).getSpeed() : 0.0f;
    }

    public synchronized boolean isTaskSpeedLimit(String str) {
        return this.mSpeedLimitMap.containsKey(str.trim());
    }

    public synchronized boolean isLimit(long j) {
        return j > 0;
    }
}
