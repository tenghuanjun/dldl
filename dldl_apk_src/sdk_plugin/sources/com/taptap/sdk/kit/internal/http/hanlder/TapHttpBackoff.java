package com.taptap.sdk.kit.internal.http.hanlder;

import com.duowan.kiwi.base.smile.SmileConst;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: TapHttpBackoff.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\u0018\u00002\u00020\u0001:\u0003\u0003\u0004\u0005B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0006"}, d2 = {"Lcom/taptap/sdk/kit/internal/http/hanlder/TapHttpBackoff;", "", "()V", "Exponential", "Fixed", "None", "tap-common_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class TapHttpBackoff {

    /* JADX INFO: compiled from: TapHttpBackoff.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0007\u001a\u00020\bH\u0016J\b\u0010\t\u001a\u00020\nH\u0016R\u0014\u0010\u0003\u001a\u00020\u0004X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u000b"}, d2 = {"Lcom/taptap/sdk/kit/internal/http/hanlder/TapHttpBackoff$None;", "Lcom/taptap/sdk/kit/internal/http/hanlder/AbsTapHttpBackoff;", "()V", SmileConst.KEY_ATTNAME, "", "getTag", "()Ljava/lang/String;", "nextBackoffMillis", "", "reset", "", "tap-common_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class None extends AbsTapHttpBackoff {
        private final String tag = "None";

        @Override // com.taptap.sdk.kit.internal.http.hanlder.AbsTapHttpBackoff
        public long nextBackoffMillis() {
            return -1L;
        }

        @Override // com.taptap.sdk.kit.internal.http.hanlder.AbsTapHttpBackoff
        public String getTag() {
            return this.tag;
        }

        @Override // com.taptap.sdk.kit.internal.http.hanlder.AbsTapHttpBackoff
        public void reset() {
            getCanTimeDeltaRetry().set(true);
        }
    }

    /* JADX INFO: compiled from: TapHttpBackoff.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\n\u001a\u00020\u000bH\u0016J\b\u0010\f\u001a\u00020\rH\u0016R\u000e\u0010\u0005\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\u00020\u0007X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\u000e"}, d2 = {"Lcom/taptap/sdk/kit/internal/http/hanlder/TapHttpBackoff$Fixed;", "Lcom/taptap/sdk/kit/internal/http/hanlder/AbsTapHttpBackoff;", "maxCount", "", "(I)V", "currentCount", SmileConst.KEY_ATTNAME, "", "getTag", "()Ljava/lang/String;", "nextBackoffMillis", "", "reset", "", "tap-common_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class Fixed extends AbsTapHttpBackoff {
        private int currentCount;
        private final int maxCount;
        private final String tag;

        public Fixed() {
            this(0, 1, null);
        }

        public Fixed(int i) {
            this.maxCount = i;
            this.tag = "Fixed";
        }

        public /* synthetic */ Fixed(int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
            this((i2 & 1) != 0 ? 3 : i);
        }

        @Override // com.taptap.sdk.kit.internal.http.hanlder.AbsTapHttpBackoff
        public String getTag() {
            return this.tag;
        }

        @Override // com.taptap.sdk.kit.internal.http.hanlder.AbsTapHttpBackoff
        public long nextBackoffMillis() {
            int i = this.currentCount + 1;
            this.currentCount = i;
            return i < this.maxCount ? 100L : -1L;
        }

        @Override // com.taptap.sdk.kit.internal.http.hanlder.AbsTapHttpBackoff
        public void reset() {
            this.currentCount = 0;
            getCanTimeDeltaRetry().set(true);
        }
    }

    /* JADX INFO: compiled from: TapHttpBackoff.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u0000 \r2\u00020\u0001:\u0001\rB\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\t\u001a\u00020\u0004H\u0016J\b\u0010\n\u001a\u00020\u000bH\u0016J\b\u0010\f\u001a\u00020\u0006H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\u00020\u0006X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u000e"}, d2 = {"Lcom/taptap/sdk/kit/internal/http/hanlder/TapHttpBackoff$Exponential;", "Lcom/taptap/sdk/kit/internal/http/hanlder/AbsTapHttpBackoff;", "()V", "mCurrentIntervalMillis", "", SmileConst.KEY_ATTNAME, "", "getTag", "()Ljava/lang/String;", "nextBackoffMillis", "reset", "", "toString", "Companion", "tap-common_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class Exponential extends AbsTapHttpBackoff {
        private static final long INIT_INTERVAL_MILLIS = 2000;
        private static final long MAX_INTERVAL_MILLIS = 600000;
        private static final int MULTIPLIER = 2;
        private final String tag = "Exponential";
        private long mCurrentIntervalMillis = 1000;

        public String toString() {
            return "Exponential";
        }

        @Override // com.taptap.sdk.kit.internal.http.hanlder.AbsTapHttpBackoff
        public String getTag() {
            return this.tag;
        }

        @Override // com.taptap.sdk.kit.internal.http.hanlder.AbsTapHttpBackoff
        public long nextBackoffMillis() {
            long j = this.mCurrentIntervalMillis;
            long j2 = 2;
            if (j * j2 > MAX_INTERVAL_MILLIS) {
                return MAX_INTERVAL_MILLIS;
            }
            long j3 = j * j2;
            this.mCurrentIntervalMillis = j3;
            return j3;
        }

        @Override // com.taptap.sdk.kit.internal.http.hanlder.AbsTapHttpBackoff
        public void reset() {
            this.mCurrentIntervalMillis = 1000L;
            getCanTimeDeltaRetry().set(true);
        }
    }
}
