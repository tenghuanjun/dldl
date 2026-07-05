package com.sqwan.bugless.anr;

import android.os.Debug;
import android.os.SystemClock;
import android.util.Printer;
import com.sqwan.bugless.util.LogUtil;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class LooperMonitor implements Printer {
    private static final int DEFAULT_BLOCK_THRESHOLD_MILLIS = 3000;
    private BlockListener mBlockListener;
    private long mBlockThresholdMillis;
    private boolean mStopWhenDebugging;
    private long mStartTimestamp = 0;
    private long mStartThreadTimestamp = 0;
    private boolean mPrintingStarted = false;

    public LooperMonitor(BlockListener blockListener, long blockThresholdMillis, boolean stopWhenDebugging) {
        this.mBlockThresholdMillis = 3000L;
        this.mBlockListener = null;
        if (blockListener == null) {
            throw new IllegalArgumentException("blockListener should not be null.");
        }
        this.mBlockListener = blockListener;
        this.mBlockThresholdMillis = blockThresholdMillis;
        this.mStopWhenDebugging = stopWhenDebugging;
    }

    @Override // android.util.Printer
    public void println(String x) {
        LogUtil.i(x);
        if (this.mStopWhenDebugging && Debug.isDebuggerConnected()) {
            return;
        }
        if (!this.mPrintingStarted) {
            this.mStartTimestamp = System.currentTimeMillis();
            this.mStartThreadTimestamp = SystemClock.currentThreadTimeMillis();
            this.mPrintingStarted = true;
            startDump();
            return;
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        this.mPrintingStarted = false;
        if (isBlock(jCurrentTimeMillis)) {
            notifyBlockEvent(jCurrentTimeMillis);
        }
        stopDump();
    }

    private boolean isBlock(long endTime) {
        return endTime - this.mStartTimestamp > this.mBlockThresholdMillis;
    }

    private void notifyBlockEvent(final long endTime) {
        LogUtil.i("notifyBlockEvent endTime : " + endTime);
    }

    private void startDump() {
        LogUtil.i("startDump");
    }

    private void stopDump() {
        LogUtil.i("stopDump");
    }
}
