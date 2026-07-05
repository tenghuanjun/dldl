package com.taptap.sdk.kit.internal.poller;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PollerState.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00012\u00020\u0002:\u0004\u0004\u0005\u0006\u0007B\u0007\b\u0004¢\u0006\u0002\u0010\u0003\u0082\u0001\u0004\b\t\n\u000b¨\u0006\f"}, d2 = {"Lcom/taptap/sdk/kit/internal/poller/PollerState;", "T", "", "()V", "Cancelled", "Completed", "InProgress", "Initial", "Lcom/taptap/sdk/kit/internal/poller/PollerState$Cancelled;", "Lcom/taptap/sdk/kit/internal/poller/PollerState$Completed;", "Lcom/taptap/sdk/kit/internal/poller/PollerState$InProgress;", "Lcom/taptap/sdk/kit/internal/poller/PollerState$Initial;", "tap-common_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public abstract class PollerState<T> {
    public /* synthetic */ PollerState(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    /* JADX INFO: compiled from: PollerState.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/taptap/sdk/kit/internal/poller/PollerState$Initial;", "Lcom/taptap/sdk/kit/internal/poller/PollerState;", "", "()V", "tap-common_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class Initial extends PollerState {
        public static final Initial INSTANCE = new Initial();

        private Initial() {
            super(null);
        }
    }

    private PollerState() {
    }

    /* JADX INFO: compiled from: PollerState.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/taptap/sdk/kit/internal/poller/PollerState$Completed;", "Lcom/taptap/sdk/kit/internal/poller/PollerState;", "", "()V", "tap-common_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class Completed extends PollerState {
        public static final Completed INSTANCE = new Completed();

        private Completed() {
            super(null);
        }
    }

    /* JADX INFO: compiled from: PollerState.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/taptap/sdk/kit/internal/poller/PollerState$Cancelled;", "Lcom/taptap/sdk/kit/internal/poller/PollerState;", "", "()V", "tap-common_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class Cancelled extends PollerState {
        public static final Cancelled INSTANCE = new Cancelled();

        private Cancelled() {
            super(null);
        }
    }

    /* JADX INFO: compiled from: PollerState.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u0000*\u0004\b\u0001\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00028\u0001¢\u0006\u0002\u0010\u0004J\u000e\u0010\b\u001a\u00028\u0001HÆ\u0003¢\u0006\u0002\u0010\u0006J\u001e\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00010\u00002\b\b\u0002\u0010\u0003\u001a\u00028\u0001HÆ\u0001¢\u0006\u0002\u0010\nJ\u0013\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eHÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001R\u0013\u0010\u0003\u001a\u00028\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0013"}, d2 = {"Lcom/taptap/sdk/kit/internal/poller/PollerState$InProgress;", "R", "Lcom/taptap/sdk/kit/internal/poller/PollerState;", "result", "(Ljava/lang/Object;)V", "getResult", "()Ljava/lang/Object;", "Ljava/lang/Object;", "component1", "copy", "(Ljava/lang/Object;)Lcom/taptap/sdk/kit/internal/poller/PollerState$InProgress;", "equals", "", "other", "", "hashCode", "", "toString", "", "tap-common_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final /* data */ class InProgress<R> extends PollerState<R> {
        private final R result;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ InProgress copy$default(InProgress inProgress, Object obj, int i, Object obj2) {
            if ((i & 1) != 0) {
                obj = inProgress.result;
            }
            return inProgress.copy(obj);
        }

        public final R component1() {
            return this.result;
        }

        public final InProgress<R> copy(R result) {
            return new InProgress<>(result);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof InProgress) && Intrinsics.areEqual(this.result, ((InProgress) other).result);
        }

        public int hashCode() {
            R r = this.result;
            if (r == null) {
                return 0;
            }
            return r.hashCode();
        }

        public String toString() {
            return "InProgress(result=" + this.result + ')';
        }

        public InProgress(R r) {
            super(null);
            this.result = r;
        }

        public final R getResult() {
            return this.result;
        }
    }
}
