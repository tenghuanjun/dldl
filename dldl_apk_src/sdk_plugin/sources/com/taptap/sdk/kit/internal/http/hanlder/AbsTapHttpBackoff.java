package com.taptap.sdk.kit.internal.http.hanlder;

import com.duowan.kiwi.base.smile.SmileConst;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TapHttpBackoff.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0000\b&\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\r\u001a\u00020\u000eJ\b\u0010\u000f\u001a\u00020\u0010H&J\b\u0010\u0011\u001a\u00020\u0012H&R\u001a\u0010\u0003\u001a\u00020\u0004X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u0012\u0010\t\u001a\u00020\nX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\f¨\u0006\u0013"}, d2 = {"Lcom/taptap/sdk/kit/internal/http/hanlder/AbsTapHttpBackoff;", "", "()V", "canTimeDeltaRetry", "Ljava/util/concurrent/atomic/AtomicBoolean;", "getCanTimeDeltaRetry", "()Ljava/util/concurrent/atomic/AtomicBoolean;", "setCanTimeDeltaRetry", "(Ljava/util/concurrent/atomic/AtomicBoolean;)V", SmileConst.KEY_ATTNAME, "", "getTag", "()Ljava/lang/String;", "canInvalidTimeRetry", "", "nextBackoffMillis", "", "reset", "", "tap-common_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public abstract class AbsTapHttpBackoff {
    private AtomicBoolean canTimeDeltaRetry = new AtomicBoolean(true);

    public abstract String getTag();

    public abstract long nextBackoffMillis();

    public abstract void reset();

    protected final AtomicBoolean getCanTimeDeltaRetry() {
        return this.canTimeDeltaRetry;
    }

    protected final void setCanTimeDeltaRetry(AtomicBoolean atomicBoolean) {
        Intrinsics.checkNotNullParameter(atomicBoolean, "<set-?>");
        this.canTimeDeltaRetry = atomicBoolean;
    }

    public final boolean canInvalidTimeRetry() {
        return this.canTimeDeltaRetry.compareAndSet(true, false);
    }
}
