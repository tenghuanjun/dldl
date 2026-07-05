package com.taptap.sdk.common.network.throwable;

import com.huya.statistics.core.StatisticsContent;
import com.taptap.sdk.kit.internal.http.TapErrorConstants;
import java.util.LinkedHashMap;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: ApiErr.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\b\u0086\u0001\u0018\u0000 \u000b2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u000bB\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0012\u0010\u0003\u001a\u00020\u0004X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\n¨\u0006\f"}, d2 = {"Lcom/taptap/sdk/common/network/throwable/ApiErr;", "", "(Ljava/lang/String;I)V", "err", "", "getErr", "()Ljava/lang/String;", "JSON_PARSE_ERROR", "INVALID_TIME", "INVALID_CLIENT", "UNKNOWN_ERROR", "Companion", "tap-common_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public enum ApiErr {
    JSON_PARSE_ERROR { // from class: com.taptap.sdk.common.network.throwable.ApiErr.JSON_PARSE_ERROR
        @Override // com.taptap.sdk.common.network.throwable.ApiErr
        public String getErr() {
            return "json parse error";
        }
    },
    INVALID_TIME { // from class: com.taptap.sdk.common.network.throwable.ApiErr.INVALID_TIME
        @Override // com.taptap.sdk.common.network.throwable.ApiErr
        public String getErr() {
            return TapErrorConstants.ERROR_INVALID_TIME;
        }
    },
    INVALID_CLIENT { // from class: com.taptap.sdk.common.network.throwable.ApiErr.INVALID_CLIENT
        @Override // com.taptap.sdk.common.network.throwable.ApiErr
        public String getErr() {
            return TapErrorConstants.ERROR_INVALID_CLIENT;
        }
    },
    UNKNOWN_ERROR { // from class: com.taptap.sdk.common.network.throwable.ApiErr.UNKNOWN_ERROR
        @Override // com.taptap.sdk.common.network.throwable.ApiErr
        public String getErr() {
            return "unknown";
        }
    };


    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    /* synthetic */ ApiErr(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public abstract String getErr();

    /* JADX INFO: compiled from: ApiErr.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lcom/taptap/sdk/common/network/throwable/ApiErr$Companion;", "", "()V", StatisticsContent.FROM, "Lcom/taptap/sdk/common/network/throwable/ApiErr;", "error", "", "tap-common_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final ApiErr from(String error) {
            Intrinsics.checkNotNullParameter(error, "error");
            ApiErr[] apiErrArrValues = ApiErr.values();
            LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(apiErrArrValues.length), 16));
            for (ApiErr apiErr : apiErrArrValues) {
                linkedHashMap.put(apiErr.getErr(), apiErr);
            }
            ApiErr apiErr2 = (ApiErr) linkedHashMap.get(error);
            return apiErr2 == null ? ApiErr.UNKNOWN_ERROR : apiErr2;
        }
    }
}
