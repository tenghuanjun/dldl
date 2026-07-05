package com.duowan.monitor.collector;

import android.os.Debug;
import android.os.Looper;
import android.util.Printer;
import java.util.ArrayList;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class LooperBlockCollector {
    private static final int DEFAULT_CPU_DURATION = 200;
    private static final int DEFAULT_THRESHOLD = 1000;
    private static final int MIN_THRESHOLD = 500;
    private final CpuSampler mCpuSampler;
    private final Looper mLooper;
    private final LooperMonitor mLooperMonitor;
    private final StackSampler mStackSampler;
    private boolean mStopped = true;

    public interface BlockListener {
        void onBlockEvent(long j, long j2);
    }

    public LooperBlockCollector(Looper looper, BlockListener blockListener) {
        if (looper == null) {
            throw new NullPointerException("looper can't be null");
        }
        if (blockListener == null) {
            throw new NullPointerException("blockListener can't be null");
        }
        this.mLooper = looper;
        StackSampler stackSampler = new StackSampler(looper.getThread());
        this.mStackSampler = stackSampler;
        stackSampler.setEnabled(true);
        CpuSampler cpuSampler = new CpuSampler();
        this.mCpuSampler = cpuSampler;
        cpuSampler.setEnabled(true);
        this.mLooperMonitor = new LooperMonitor(blockListener, this.mStackSampler, this.mCpuSampler);
    }

    public void start() {
        if (this.mStopped) {
            this.mStopped = false;
            update();
        }
    }

    public void stop() {
        if (this.mStopped) {
            return;
        }
        this.mStopped = true;
        update();
    }

    public void setThreshold(long j) {
        long realThreshold = getRealThreshold(j);
        long j2 = (long) (realThreshold * 0.8d);
        this.mStackSampler.setInterval(j2);
        this.mCpuSampler.setInterval(j2);
        this.mCpuSampler.setDuration(Math.min(200L, realThreshold - j2));
        this.mLooperMonitor.setThreshold(realThreshold);
        update();
    }

    public ArrayList<String> getThreadStackEntries(long j, long j2) {
        return this.mStackSampler.getThreadStackEntries(j, j2);
    }

    public String getCpuRateInfo(long j) {
        return this.mCpuSampler.getCpuRateInfo(j);
    }

    private void update() {
        if (!this.mStopped) {
            this.mLooper.setMessageLogging(this.mLooperMonitor);
            return;
        }
        this.mLooper.setMessageLogging(null);
        this.mStackSampler.onStop();
        this.mCpuSampler.onStop();
    }

    private long getRealThreshold(long j) {
        if (j == 0) {
            j = 1000;
        }
        return Math.max(j, 500L);
    }

    private static class LooperMonitor implements Printer {
        private final BlockListener mBlockListener;
        private final CpuSampler mCpuSampler;
        private final StackSampler mStackSampler;
        private long mStartTime;
        private long mThreshold = 1000;
        private boolean mPrintingStarted = false;

        LooperMonitor(BlockListener blockListener, StackSampler stackSampler, CpuSampler cpuSampler) {
            this.mBlockListener = blockListener;
            this.mStackSampler = stackSampler;
            this.mCpuSampler = cpuSampler;
        }

        void setThreshold(long j) {
            this.mThreshold = j;
        }

        @Override // android.util.Printer
        public void println(String str) {
            if (Debug.isDebuggerConnected()) {
                return;
            }
            if (!this.mPrintingStarted && str.charAt(0) == '>') {
                this.mStartTime = System.currentTimeMillis();
                this.mPrintingStarted = true;
                this.mStackSampler.onStart();
                this.mCpuSampler.onStart();
                return;
            }
            if (this.mPrintingStarted && str.charAt(0) == '<') {
                long jCurrentTimeMillis = System.currentTimeMillis();
                this.mPrintingStarted = false;
                long j = this.mStartTime;
                if (jCurrentTimeMillis - j > this.mThreshold) {
                    this.mBlockListener.onBlockEvent(j, jCurrentTimeMillis);
                }
                this.mStackSampler.onStop();
                this.mCpuSampler.onStop();
            }
        }
    }
}
