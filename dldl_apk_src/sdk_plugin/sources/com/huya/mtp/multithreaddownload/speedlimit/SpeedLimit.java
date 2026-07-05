package com.huya.mtp.multithreaddownload.speedlimit;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class SpeedLimit {
    private static final String TAG = "SpeedLimit";
    private long mLastTime;
    private long mMaxSpeed;
    private float mSpeed;
    private long mTotalFinished;

    public long getSleepTime(long j) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (this.mLastTime == 0 || this.mTotalFinished == 0) {
            this.mLastTime = jCurrentTimeMillis;
        }
        long j2 = this.mTotalFinished + j;
        this.mTotalFinished = j2;
        long j3 = jCurrentTimeMillis - this.mLastTime;
        float f = j2 / 1024.0f;
        float transformMaxSpeed = getTransformMaxSpeed();
        long j4 = (f < transformMaxSpeed || j3 >= 1000) ? 0L : 1000 - j3;
        if (f >= transformMaxSpeed || j3 >= 1000) {
            this.mTotalFinished = 0L;
            this.mSpeed = f / 1.0f;
        }
        return j4;
    }

    private float getTransformMaxSpeed() {
        return this.mMaxSpeed * 1.0f;
    }

    public float getSpeed() {
        return this.mSpeed;
    }

    public void setMaxSpped(long j) {
        this.mMaxSpeed = j;
    }
}
