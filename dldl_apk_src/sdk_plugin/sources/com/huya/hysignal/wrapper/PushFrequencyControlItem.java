package com.huya.hysignal.wrapper;

import android.os.SystemClock;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
class PushFrequencyControlItem {
    private int currentCount;
    private long lastTimeStamp;
    private int maxCount;

    PushFrequencyControlItem(int i, long j) {
        this.maxCount = i;
        this.lastTimeStamp = j;
    }

    boolean isOverFrequency() {
        if (this.maxCount < 0) {
            return false;
        }
        long jUptimeMillis = SystemClock.uptimeMillis();
        if (jUptimeMillis - this.lastTimeStamp > 1000) {
            this.currentCount = 0;
            this.lastTimeStamp = jUptimeMillis;
        }
        return this.currentCount >= this.maxCount;
    }

    void increase() {
        if (isOverFrequency()) {
            return;
        }
        this.currentCount++;
    }

    int getMaxCount() {
        return this.maxCount;
    }

    void setMaxCount(int i) {
        this.maxCount = i;
    }

    int getCurrentCount() {
        return this.currentCount;
    }
}
