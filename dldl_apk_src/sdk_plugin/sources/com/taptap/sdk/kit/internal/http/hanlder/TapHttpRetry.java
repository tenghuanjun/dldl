package com.taptap.sdk.kit.internal.http.hanlder;

import com.taptap.sdk.kit.internal.http.TapErrorConstants;
import com.taptap.sdk.kit.internal.http.TapHttpException;
import com.taptap.sdk.kit.internal.http.TapTime;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TapHttpRetry.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0002\u0003\u0004B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0005"}, d2 = {"Lcom/taptap/sdk/kit/internal/http/hanlder/TapHttpRetry;", "", "()V", "NoneRetry", "SdkDefault", "tap-common_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class TapHttpRetry {

    /* JADX INFO: compiled from: TapHttpRetry.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0003\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\u0007"}, d2 = {"Lcom/taptap/sdk/kit/internal/http/hanlder/TapHttpRetry$NoneRetry;", "Lcom/taptap/sdk/kit/internal/http/hanlder/ITapHttpRetry;", "()V", "nextRetryMillis", "", "e", "", "tap-common_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class NoneRetry implements ITapHttpRetry {
        @Override // com.taptap.sdk.kit.internal.http.hanlder.ITapHttpRetry
        public long nextRetryMillis(Throwable e) {
            Intrinsics.checkNotNullParameter(e, "e");
            return -1L;
        }
    }

    /* JADX INFO: compiled from: TapHttpRetry.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0003\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/taptap/sdk/kit/internal/http/hanlder/TapHttpRetry$SdkDefault;", "Lcom/taptap/sdk/kit/internal/http/hanlder/ITapHttpRetry;", "backoff", "Lcom/taptap/sdk/kit/internal/http/hanlder/AbsTapHttpBackoff;", "(Lcom/taptap/sdk/kit/internal/http/hanlder/AbsTapHttpBackoff;)V", "nextRetryMillis", "", "e", "", "tap-common_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class SdkDefault implements ITapHttpRetry {
        private final AbsTapHttpBackoff backoff;

        public SdkDefault(AbsTapHttpBackoff backoff) {
            Intrinsics.checkNotNullParameter(backoff, "backoff");
            this.backoff = backoff;
        }

        @Override // com.taptap.sdk.kit.internal.http.hanlder.ITapHttpRetry
        public long nextRetryMillis(Throwable e) {
            Intrinsics.checkNotNullParameter(e, "e");
            if (e instanceof TapHttpException.ServerError) {
                TapHttpException.ServerError serverError = (TapHttpException.ServerError) e;
                int httpCode = serverError.getHttpCode();
                if (500 <= httpCode && httpCode < 600) {
                    return this.backoff.nextBackoffMillis();
                }
                if (Intrinsics.areEqual(serverError.getError().getError(), TapErrorConstants.ERROR_SERVER_ERROR)) {
                    return this.backoff.nextBackoffMillis();
                }
                if (Intrinsics.areEqual(serverError.getError().getError(), TapErrorConstants.ERROR_INVALID_TIME)) {
                    TapTime.INSTANCE.calibrateServerTime(serverError.getResponse().getNow());
                    if (this.backoff.canInvalidTimeRetry()) {
                        return 0L;
                    }
                }
            } else if (e instanceof TapHttpException.NoServerError) {
                int httpCode2 = ((TapHttpException.NoServerError) e).getHttpCode();
                if (500 <= httpCode2 && httpCode2 < 600) {
                    return this.backoff.nextBackoffMillis();
                }
            } else {
                if (e instanceof UnknownHostException ? true : e instanceof SocketTimeoutException) {
                    return this.backoff.nextBackoffMillis();
                }
            }
            return -1L;
        }
    }
}
