package com.taptap.sdk.kit.internal.poller.strategy;

import kotlin.Metadata;

/* JADX INFO: compiled from: IPollerStrategy.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H&¨\u0006\u0007"}, d2 = {"Lcom/taptap/sdk/kit/internal/poller/strategy/IPollerStrategy;", "", "canPoll", "", "interval", "", "elapsedPollTime", "tap-common_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public interface IPollerStrategy {
    boolean canPoll(long interval, long elapsedPollTime);
}
