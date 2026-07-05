package com.huya.mtp.hyns.volley;

import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class LinearRetryPolicy implements RetryPolicy {
    public static final int DEFAULT_MAX_RETRIES = 1;
    public static final int DEFAULT_TIMEOUT_INCREMENT_MS = 5000;
    public static final int DEFAULT_TIMEOUT_MS = 5000;
    public static final int MAX_TIMEOUT_INCREMENT_TIMES = 3;
    private int mBaseTimeoutMs;
    private int mCurrentRetryCount;
    private CurrentRetryTimesUpdateListener mCurrentRetryTimesUpdateListener;
    private int mCurrentTimeoutMs;
    private final int mMaxNumRetries;
    private int mTimeoutIncrementMs;

    public interface CurrentRetryTimesUpdateListener {
        void onCurrentRetryTimesUpdated(int i);
    }

    public LinearRetryPolicy() {
        this(5000, 5000, 1, null);
    }

    public LinearRetryPolicy(int i, int i2, int i3, CurrentRetryTimesUpdateListener currentRetryTimesUpdateListener) {
        this.mCurrentTimeoutMs = i;
        this.mBaseTimeoutMs = i;
        this.mTimeoutIncrementMs = i2;
        this.mMaxNumRetries = i3;
        this.mCurrentRetryTimesUpdateListener = currentRetryTimesUpdateListener;
    }

    @Override // com.android.volley.RetryPolicy
    public int getCurrentTimeout() {
        return this.mCurrentTimeoutMs;
    }

    @Override // com.android.volley.RetryPolicy
    public int getCurrentRetryCount() {
        return this.mCurrentRetryCount;
    }

    @Override // com.android.volley.RetryPolicy
    public void retry(VolleyError volleyError) throws VolleyError {
        int i = this.mCurrentRetryCount + 1;
        this.mCurrentRetryCount = i;
        if (i > 3) {
            i = 3;
        }
        this.mCurrentTimeoutMs = this.mBaseTimeoutMs + (i * this.mTimeoutIncrementMs);
        if (!hasAttemptRemaining()) {
            throw volleyError;
        }
        CurrentRetryTimesUpdateListener currentRetryTimesUpdateListener = this.mCurrentRetryTimesUpdateListener;
        if (currentRetryTimesUpdateListener != null) {
            currentRetryTimesUpdateListener.onCurrentRetryTimesUpdated(this.mCurrentRetryCount);
        }
    }

    protected boolean hasAttemptRemaining() {
        return this.mCurrentRetryCount <= this.mMaxNumRetries;
    }
}
