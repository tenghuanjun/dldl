package com.duowan.kiwi.barrage.render;

import android.os.SystemClock;
import java.util.LinkedList;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class SmoothDeltaTime {
    private static final long CORDON_TIME = 30;
    private static final long CORDON_TIME_2 = 60;
    private static final long FRAME_UPDATE_RATE = 16;
    private static final int MAX_RECORD_SIZE = 500;
    private boolean mInSyncAction;
    private long mLastDeltaTime;
    private long mTimeBase;
    private final Timer timer = new Timer();
    private LinkedList<Long> mDrawTimes = new LinkedList<>();

    public float getSmoothDelta() {
        return this.timer.lastInterval;
    }

    public long getCurrentTime() {
        return this.timer.currMillisecond;
    }

    public long calcSmoothDelta() {
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        if (this.mInSyncAction) {
            return 0L;
        }
        this.mInSyncAction = true;
        long jMin = (jElapsedRealtime - this.mTimeBase) - this.timer.currMillisecond;
        long jMax = Math.max(16L, getAverageRenderingTime());
        if (jMin <= 2000 && jMax <= CORDON_TIME) {
            jMin = Math.min(CORDON_TIME, Math.max(16L, jMax + (jMin / 16)));
            long j = this.mLastDeltaTime;
            long j2 = jMin - j;
            if (j2 > 3 && j2 < 8 && j >= 16 && j <= CORDON_TIME) {
                jMin = j;
            }
            this.mLastDeltaTime = jMin;
        }
        this.timer.add(jMin);
        this.mInSyncAction = false;
        return jMin;
    }

    public synchronized void reset() {
        this.mDrawTimes.clear();
        this.mTimeBase = SystemClock.elapsedRealtime();
    }

    public void start() {
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        this.mTimeBase = jElapsedRealtime;
        this.timer.currMillisecond = jElapsedRealtime;
    }

    private synchronized long getAverageRenderingTime() {
        int size = this.mDrawTimes.size();
        if (size <= 0) {
            return 0L;
        }
        Long lPeekFirst = this.mDrawTimes.peekFirst();
        Long lPeekLast = this.mDrawTimes.peekLast();
        if (lPeekFirst != null && lPeekLast != null) {
            return (lPeekLast.longValue() - lPeekFirst.longValue()) / ((long) size);
        }
        return 0L;
    }

    public synchronized void onDrawCost(long j) {
        if (j > 60) {
            this.timer.add(j);
            this.mDrawTimes.clear();
        }
    }

    public synchronized void recordRenderingTime() {
        this.mDrawTimes.addLast(Long.valueOf(SystemClock.elapsedRealtime()));
        if (this.mDrawTimes.size() > MAX_RECORD_SIZE) {
            this.mDrawTimes.removeFirst();
        }
    }

    public static class Timer {
        public long currMillisecond;
        private long lastInterval;

        public Timer() {
        }

        public Timer(long j) {
            update(j);
        }

        public long update(long j) {
            long j2 = j - this.currMillisecond;
            this.lastInterval = j2;
            this.currMillisecond = j;
            return j2;
        }

        public long add(long j) {
            return update(this.currMillisecond + j);
        }

        public long lastInterval() {
            return this.lastInterval;
        }
    }
}
