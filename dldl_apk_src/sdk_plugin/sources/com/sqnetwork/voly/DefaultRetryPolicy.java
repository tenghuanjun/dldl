package com.sqnetwork.voly;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class DefaultRetryPolicy implements RetryPolicy {
    public static final float DEFAULT_BACKOFF_MULT = 1.0f;
    public static final int DEFAULT_MAX_RETRIES = 1;
    public static final int DEFAULT_TIMEOUT_MS = 2500;
    private final float mBackoffMultiplier;
    private int mCurrentRetryCount;
    private int mCurrentTimeoutMs;
    private final int mInitTimeoutMs;
    private final int mMaxNumRetries;

    public DefaultRetryPolicy() {
        this(2500, 1, 1.0f);
    }

    public DefaultRetryPolicy(int initialTimeoutMs, int maxNumRetries, float backoffMultiplier) {
        this.mInitTimeoutMs = initialTimeoutMs;
        this.mCurrentTimeoutMs = initialTimeoutMs;
        this.mMaxNumRetries = maxNumRetries;
        this.mBackoffMultiplier = backoffMultiplier;
    }

    @Override // com.sqnetwork.voly.RetryPolicy
    public int getCurrentTimeout() {
        return this.mCurrentTimeoutMs;
    }

    @Override // com.sqnetwork.voly.RetryPolicy
    public int getReadTimeout() {
        return this.mInitTimeoutMs;
    }

    @Override // com.sqnetwork.voly.RetryPolicy
    public int getWriteTimeout() {
        return this.mInitTimeoutMs;
    }

    @Override // com.sqnetwork.voly.RetryPolicy
    public int getCurrentRetryCount() {
        return this.mCurrentRetryCount;
    }

    @Override // com.sqnetwork.voly.RetryPolicy
    public int getRemainingRetryCount() {
        return this.mMaxNumRetries - this.mCurrentRetryCount;
    }

    public float getBackoffMultiplier() {
        return this.mBackoffMultiplier;
    }

    public void retry(VolleyError error) throws VolleyError {
        this.mCurrentRetryCount++;
        int i = this.mCurrentTimeoutMs;
        this.mCurrentTimeoutMs = i + ((int) (i * this.mBackoffMultiplier));
        if (!hasAttemptRemaining()) {
            throw error;
        }
    }

    @Override // com.sqnetwork.voly.RetryPolicy
    public void retry(Request<?> request, VolleyError error) throws VolleyError {
        retry(error);
    }

    protected boolean hasAttemptRemaining() {
        return this.mCurrentRetryCount <= this.mMaxNumRetries;
    }
}
