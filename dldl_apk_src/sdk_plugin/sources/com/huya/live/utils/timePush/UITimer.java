package com.huya.live.utils.timePush;

import com.duowan.auk.ArkValue;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class UITimer {
    private static final long DEFAULT_TIME_INTERVAL = 5000;
    private OnTimeoutListener mOnTimeoutListener;
    private final Runnable mRunnable;
    private long mTimeInterval;

    public interface OnTimeoutListener {
        void onTimeout();
    }

    public UITimer() {
        this(5000L);
    }

    public UITimer(long j) {
        this.mTimeInterval = 5000L;
        this.mRunnable = new Runnable() { // from class: com.huya.live.utils.timePush.UITimer.1
            @Override // java.lang.Runnable
            public void run() {
                if (UITimer.this.mOnTimeoutListener != null) {
                    UITimer.this.mOnTimeoutListener.onTimeout();
                }
                ArkValue.gMainHandler.postDelayed(this, UITimer.this.mTimeInterval);
            }
        };
        this.mTimeInterval = j;
    }

    public void start() {
        ArkValue.gMainHandler.post(this.mRunnable);
    }

    public void stop() {
        ArkValue.gMainHandler.removeCallbacks(this.mRunnable);
    }

    public void setTimeInterval(long j) {
        this.mTimeInterval = j;
    }

    public long timeInterval() {
        return this.mTimeInterval;
    }

    public void setOnTimeoutListener(OnTimeoutListener onTimeoutListener) {
        this.mOnTimeoutListener = onTimeoutListener;
    }
}
