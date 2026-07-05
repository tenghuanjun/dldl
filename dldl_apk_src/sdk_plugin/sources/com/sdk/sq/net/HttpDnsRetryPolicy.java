package com.sdk.sq.net;

import com.sqnetwork.voly.Request;
import com.sqnetwork.voly.RetryPolicy;
import com.sqnetwork.voly.VolleyError;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class HttpDnsRetryPolicy implements RetryPolicy {
    public static final int DEFAULT_RETRY_COUNT = 2;
    private int mCurrentRetryCount;
    private final int mHostTimeoutMs;
    private final int mIpTimeoutMs;
    private final int mMaxNumRetries;
    private final Request<?> mRequest;

    public HttpDnsRetryPolicy(int ipTimeoutMs, int hostTimeoutMs, int maxNumRetries, Request<?> request) {
        this.mIpTimeoutMs = ipTimeoutMs;
        this.mHostTimeoutMs = hostTimeoutMs;
        this.mMaxNumRetries = maxNumRetries;
        this.mRequest = request;
    }

    @Override // com.sqnetwork.voly.RetryPolicy
    public int getCurrentTimeout() {
        Request<?> request = this.mRequest;
        if (request == null) {
            return this.mIpTimeoutMs;
        }
        if (this.mCurrentRetryCount == 0 || (request.hasLocalDNSTriggered() && request.hasHostNameChangeToIp())) {
            return this.mIpTimeoutMs;
        }
        if (request.hasLocalDNSTriggered()) {
            return this.mHostTimeoutMs;
        }
        return this.mIpTimeoutMs;
    }

    @Override // com.sqnetwork.voly.RetryPolicy
    public int getReadTimeout() {
        return this.mIpTimeoutMs;
    }

    @Override // com.sqnetwork.voly.RetryPolicy
    public int getWriteTimeout() {
        return this.mIpTimeoutMs;
    }

    @Override // com.sqnetwork.voly.RetryPolicy
    public int getCurrentRetryCount() {
        return this.mCurrentRetryCount;
    }

    @Override // com.sqnetwork.voly.RetryPolicy
    public int getRemainingRetryCount() {
        return this.mMaxNumRetries - this.mCurrentRetryCount;
    }

    @Override // com.sqnetwork.voly.RetryPolicy
    public void retry(Request<?> request, VolleyError error) throws VolleyError {
        this.mCurrentRetryCount++;
        if (request.hasLocalDNSTriggered() && !request.hasHostNameChangeToIp()) {
            if (!hasAttemptRemaining() || this.mCurrentRetryCount > 1) {
                throw error;
            }
        } else if (!hasAttemptRemaining()) {
            throw error;
        }
    }

    protected boolean hasAttemptRemaining() {
        return this.mCurrentRetryCount <= this.mMaxNumRetries;
    }
}
