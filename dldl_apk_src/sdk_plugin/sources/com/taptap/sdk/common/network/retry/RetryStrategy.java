package com.taptap.sdk.common.network.retry;

import defpackage.C$r8$backportedMethods$utility$Long$1$hashCode;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: RetryStrategy.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0003\u0003\u0004\u0005B\u0007\b\u0004¢\u0006\u0002\u0010\u0002\u0082\u0001\u0003\u0006\u0007\b¨\u0006\t"}, d2 = {"Lcom/taptap/sdk/common/network/retry/RetryStrategy;", "", "()V", "Exponential", "Fixed", "NoRetry", "Lcom/taptap/sdk/common/network/retry/RetryStrategy$Exponential;", "Lcom/taptap/sdk/common/network/retry/RetryStrategy$Fixed;", "Lcom/taptap/sdk/common/network/retry/RetryStrategy$NoRetry;", "tap-common_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public abstract class RetryStrategy {
    public /* synthetic */ RetryStrategy(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    private RetryStrategy() {
    }

    /* JADX INFO: compiled from: RetryStrategy.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/taptap/sdk/common/network/retry/RetryStrategy$NoRetry;", "Lcom/taptap/sdk/common/network/retry/RetryStrategy;", "()V", "tap-common_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class NoRetry extends RetryStrategy {
        public static final NoRetry INSTANCE = new NoRetry();

        private NoRetry() {
            super(null);
        }
    }

    /* JADX INFO: compiled from: RetryStrategy.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0019\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0015"}, d2 = {"Lcom/taptap/sdk/common/network/retry/RetryStrategy$Fixed;", "Lcom/taptap/sdk/common/network/retry/RetryStrategy;", "maxCount", "", "intervalMillis", "", "(IJ)V", "getIntervalMillis", "()J", "getMaxCount", "()I", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "toString", "", "tap-common_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final /* data */ class Fixed extends RetryStrategy {
        private final long intervalMillis;
        private final int maxCount;

        public Fixed() {
            this(0, 0L, 3, null);
        }

        public static /* synthetic */ Fixed copy$default(Fixed fixed, int i, long j, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                i = fixed.maxCount;
            }
            if ((i2 & 2) != 0) {
                j = fixed.intervalMillis;
            }
            return fixed.copy(i, j);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final int getMaxCount() {
            return this.maxCount;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final long getIntervalMillis() {
            return this.intervalMillis;
        }

        public final Fixed copy(int maxCount, long intervalMillis) {
            return new Fixed(maxCount, intervalMillis);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Fixed)) {
                return false;
            }
            Fixed fixed = (Fixed) other;
            return this.maxCount == fixed.maxCount && this.intervalMillis == fixed.intervalMillis;
        }

        public int hashCode() {
            return (this.maxCount * 31) + C$r8$backportedMethods$utility$Long$1$hashCode.hashCode(this.intervalMillis);
        }

        public String toString() {
            return "Fixed(maxCount=" + this.maxCount + ", intervalMillis=" + this.intervalMillis + ')';
        }

        public /* synthetic */ Fixed(int i, long j, int i2, DefaultConstructorMarker defaultConstructorMarker) {
            this((i2 & 1) != 0 ? 2 : i, (i2 & 2) != 0 ? 100L : j);
        }

        public final int getMaxCount() {
            return this.maxCount;
        }

        public final long getIntervalMillis() {
            return this.intervalMillis;
        }

        public Fixed(int i, long j) {
            super(null);
            this.maxCount = i;
            this.intervalMillis = j;
        }
    }

    /* JADX INFO: compiled from: RetryStrategy.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0019\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fHÖ\u0003J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0014"}, d2 = {"Lcom/taptap/sdk/common/network/retry/RetryStrategy$Exponential;", "Lcom/taptap/sdk/common/network/retry/RetryStrategy;", "initialIntervalMillis", "", "maxIntervalMillis", "(JJ)V", "getInitialIntervalMillis", "()J", "getMaxIntervalMillis", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "tap-common_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final /* data */ class Exponential extends RetryStrategy {
        private final long initialIntervalMillis;
        private final long maxIntervalMillis;

        public Exponential() {
            this(0L, 0L, 3, null);
        }

        public static /* synthetic */ Exponential copy$default(Exponential exponential, long j, long j2, int i, Object obj) {
            if ((i & 1) != 0) {
                j = exponential.initialIntervalMillis;
            }
            if ((i & 2) != 0) {
                j2 = exponential.maxIntervalMillis;
            }
            return exponential.copy(j, j2);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final long getInitialIntervalMillis() {
            return this.initialIntervalMillis;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final long getMaxIntervalMillis() {
            return this.maxIntervalMillis;
        }

        public final Exponential copy(long initialIntervalMillis, long maxIntervalMillis) {
            return new Exponential(initialIntervalMillis, maxIntervalMillis);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Exponential)) {
                return false;
            }
            Exponential exponential = (Exponential) other;
            return this.initialIntervalMillis == exponential.initialIntervalMillis && this.maxIntervalMillis == exponential.maxIntervalMillis;
        }

        public int hashCode() {
            return (C$r8$backportedMethods$utility$Long$1$hashCode.hashCode(this.initialIntervalMillis) * 31) + C$r8$backportedMethods$utility$Long$1$hashCode.hashCode(this.maxIntervalMillis);
        }

        public String toString() {
            return "Exponential(initialIntervalMillis=" + this.initialIntervalMillis + ", maxIntervalMillis=" + this.maxIntervalMillis + ')';
        }

        public /* synthetic */ Exponential(long j, long j2, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? 2000L : j, (i & 2) != 0 ? 600000L : j2);
        }

        public final long getInitialIntervalMillis() {
            return this.initialIntervalMillis;
        }

        public final long getMaxIntervalMillis() {
            return this.maxIntervalMillis;
        }

        public Exponential(long j, long j2) {
            super(null);
            this.initialIntervalMillis = j;
            this.maxIntervalMillis = j2;
        }
    }
}
