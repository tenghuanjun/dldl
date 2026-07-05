package com.taptap.sdk.kit.internal.poller.strategy;

import kotlin.Metadata;

/* JADX INFO: compiled from: MaxCountStrategy.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/taptap/sdk/kit/internal/poller/strategy/MaxCountStrategy;", "Lcom/taptap/sdk/kit/internal/poller/strategy/IPollerStrategy;", "maxPollCount", "", "(I)V", "canPoll", "", "interval", "", "elapsedPollTime", "tap-common_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class MaxCountStrategy implements IPollerStrategy {
    private final int maxPollCount;

    public MaxCountStrategy(int i) {
        this.maxPollCount = i;
    }

    @Override // com.taptap.sdk.kit.internal.poller.strategy.IPollerStrategy
    public boolean canPoll(long interval, long elapsedPollTime) {
        return elapsedPollTime / interval < ((long) this.maxPollCount);
    }
}
