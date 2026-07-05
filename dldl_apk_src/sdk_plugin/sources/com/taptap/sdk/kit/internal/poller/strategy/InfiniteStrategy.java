package com.taptap.sdk.kit.internal.poller.strategy;

import kotlin.Metadata;

/* JADX INFO: compiled from: InfiniteStrategy.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0016¨\u0006\b"}, d2 = {"Lcom/taptap/sdk/kit/internal/poller/strategy/InfiniteStrategy;", "Lcom/taptap/sdk/kit/internal/poller/strategy/IPollerStrategy;", "()V", "canPoll", "", "interval", "", "elapsedPollTime", "tap-common_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class InfiniteStrategy implements IPollerStrategy {
    @Override // com.taptap.sdk.kit.internal.poller.strategy.IPollerStrategy
    public boolean canPoll(long interval, long elapsedPollTime) {
        return true;
    }
}
