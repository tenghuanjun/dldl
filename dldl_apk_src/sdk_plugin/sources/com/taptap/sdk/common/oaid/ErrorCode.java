package com.taptap.sdk.common.oaid;

import com.bun.miitmdid.core.InfoCode;
import com.huya.statistics.core.StatisticsContent;
import java.util.LinkedHashMap;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: ErrorCode.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\f\b\u0086\u0001\u0018\u0000 \u000f2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u000fB\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0012\u0010\u0003\u001a\u00020\u0004X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000e¨\u0006\u0010"}, d2 = {"Lcom/taptap/sdk/common/oaid/ErrorCode;", "", "(Ljava/lang/String;I)V", "code", "", "getCode", "()I", "INIT_INFO_RESULT_OK", "INIT_INFO_RESULT_DELAY", "INIT_ERROR_CERT_ERROR", "INIT_ERROR_MANUFACTURER_NO_SUPPORT", "INIT_ERROR_DEVICE_NO_SUPPORT", "INIT_ERROR_LOAD_CONFIG_FILE", "INIT_ERROR_SDK_CALL_ERROR", "INIT_ERROR_UNKNOWN", "Companion", "tap-common_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public enum ErrorCode {
    INIT_INFO_RESULT_OK { // from class: com.taptap.sdk.common.oaid.ErrorCode.INIT_INFO_RESULT_OK
        @Override // com.taptap.sdk.common.oaid.ErrorCode
        public int getCode() {
            return 1008610;
        }
    },
    INIT_INFO_RESULT_DELAY { // from class: com.taptap.sdk.common.oaid.ErrorCode.INIT_INFO_RESULT_DELAY
        @Override // com.taptap.sdk.common.oaid.ErrorCode
        public int getCode() {
            return 1008614;
        }
    },
    INIT_ERROR_CERT_ERROR { // from class: com.taptap.sdk.common.oaid.ErrorCode.INIT_ERROR_CERT_ERROR
        @Override // com.taptap.sdk.common.oaid.ErrorCode
        public int getCode() {
            return InfoCode.INIT_ERROR_CERT_ERROR;
        }
    },
    INIT_ERROR_MANUFACTURER_NO_SUPPORT { // from class: com.taptap.sdk.common.oaid.ErrorCode.INIT_ERROR_MANUFACTURER_NO_SUPPORT
        @Override // com.taptap.sdk.common.oaid.ErrorCode
        public int getCode() {
            return 1008611;
        }
    },
    INIT_ERROR_DEVICE_NO_SUPPORT { // from class: com.taptap.sdk.common.oaid.ErrorCode.INIT_ERROR_DEVICE_NO_SUPPORT
        @Override // com.taptap.sdk.common.oaid.ErrorCode
        public int getCode() {
            return 1008612;
        }
    },
    INIT_ERROR_LOAD_CONFIG_FILE { // from class: com.taptap.sdk.common.oaid.ErrorCode.INIT_ERROR_LOAD_CONFIG_FILE
        @Override // com.taptap.sdk.common.oaid.ErrorCode
        public int getCode() {
            return 1008613;
        }
    },
    INIT_ERROR_SDK_CALL_ERROR { // from class: com.taptap.sdk.common.oaid.ErrorCode.INIT_ERROR_SDK_CALL_ERROR
        @Override // com.taptap.sdk.common.oaid.ErrorCode
        public int getCode() {
            return 1008615;
        }
    },
    INIT_ERROR_UNKNOWN { // from class: com.taptap.sdk.common.oaid.ErrorCode.INIT_ERROR_UNKNOWN
        @Override // com.taptap.sdk.common.oaid.ErrorCode
        public int getCode() {
            return -1;
        }
    };


    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    /* synthetic */ ErrorCode(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public abstract int getCode();

    /* JADX INFO: compiled from: ErrorCode.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lcom/taptap/sdk/common/oaid/ErrorCode$Companion;", "", "()V", StatisticsContent.FROM, "Lcom/taptap/sdk/common/oaid/ErrorCode;", "code", "", "tap-common_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final ErrorCode from(int code) {
            ErrorCode[] errorCodeArrValues = ErrorCode.values();
            LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(errorCodeArrValues.length), 16));
            for (ErrorCode errorCode : errorCodeArrValues) {
                linkedHashMap.put(Integer.valueOf(errorCode.getCode()), errorCode);
            }
            ErrorCode errorCode2 = (ErrorCode) linkedHashMap.get(Integer.valueOf(code));
            return errorCode2 == null ? ErrorCode.INIT_ERROR_UNKNOWN : errorCode2;
        }
    }
}
