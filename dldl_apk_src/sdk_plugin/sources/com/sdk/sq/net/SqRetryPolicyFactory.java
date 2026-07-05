package com.sdk.sq.net;

import com.sqnetwork.voly.DefaultRetryPolicy;
import com.sqnetwork.voly.Request;
import com.sqnetwork.voly.RetryPolicy;
import com.sqnetwork.voly.RetryPolicyFactory;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class SqRetryPolicyFactory implements RetryPolicyFactory {
    private final int mInitialTimeoutMs;
    private final int mMaxTimeoutMs;

    public SqRetryPolicyFactory(int initialTimeoutMs, int maxTimeoutMs) {
        if (initialTimeoutMs <= 0) {
            throw new IllegalArgumentException("超时时间必须大于0");
        }
        if (maxTimeoutMs < initialTimeoutMs) {
            throw new IllegalArgumentException("最大超时时间必须大于等于初始超时时间");
        }
        this.mInitialTimeoutMs = initialTimeoutMs;
        this.mMaxTimeoutMs = maxTimeoutMs;
    }

    @Override // com.sqnetwork.voly.RetryPolicyFactory
    public RetryPolicy create(Request<?> request) {
        if (request.canTriggerLocalDNS()) {
            return new HttpDnsRetryPolicy(this.mInitialTimeoutMs, this.mMaxTimeoutMs, 2, request);
        }
        int i = this.mMaxTimeoutMs;
        int i2 = this.mInitialTimeoutMs;
        return new DefaultRetryPolicy(i2, 1, (i - i2) / i2);
    }
}
