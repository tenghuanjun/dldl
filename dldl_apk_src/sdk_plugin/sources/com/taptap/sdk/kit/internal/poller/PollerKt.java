package com.taptap.sdk.kit.internal.poller;

import com.taptap.sdk.kit.internal.poller.Poller;
import com.taptap.sdk.kit.internal.poller.strategy.IPollerStrategy;
import com.taptap.sdk.kit.internal.poller.strategy.InfiniteStrategy;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: Poller.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a0\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t¨\u0006\n"}, d2 = {"create", "Lcom/taptap/sdk/kit/internal/poller/Poller;", "T", "Lcom/taptap/sdk/kit/internal/poller/Poller$Companion;", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "pollInterval", "", "pollStrategy", "Lcom/taptap/sdk/kit/internal/poller/strategy/IPollerStrategy;", "tap-common_release"}, k = 2, mv = {1, 5, 1}, xi = 48)
public final class PollerKt {
    public static /* synthetic */ Poller create$default(Poller.Companion companion, CoroutineScope coroutineScope, long j, IPollerStrategy iPollerStrategy, int i, Object obj) {
        if ((i & 4) != 0) {
            iPollerStrategy = new InfiniteStrategy();
        }
        return create(companion, coroutineScope, j, iPollerStrategy);
    }

    public static final <T> Poller<T> create(Poller.Companion companion, CoroutineScope coroutineScope, long j, IPollerStrategy pollStrategy) {
        Intrinsics.checkNotNullParameter(companion, "<this>");
        Intrinsics.checkNotNullParameter(coroutineScope, "coroutineScope");
        Intrinsics.checkNotNullParameter(pollStrategy, "pollStrategy");
        return new PollerImpl(coroutineScope, j, pollStrategy);
    }
}
