package com.taptap.sdk.initializer.api.model;

import com.huya.statistics.core.StatisticsContent;
import java.util.LinkedHashMap;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: RegionType.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0086\u0001\u0018\u0000 \r2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\rB\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0012\u0010\u0003\u001a\u00020\u0004X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0012\u0010\u0007\u001a\u00020\bX¦\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\nj\u0002\b\u000bj\u0002\b\f¨\u0006\u000e"}, d2 = {"Lcom/taptap/sdk/initializer/api/model/RegionType;", "", "(Ljava/lang/String;I)V", "code", "", "getCode", "()I", "value", "", "getValue", "()Ljava/lang/String;", "CN", "GLOBAL", "Companion", "tap-initializer-api_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public enum RegionType {
    CN { // from class: com.taptap.sdk.initializer.api.model.RegionType.CN
        @Override // com.taptap.sdk.initializer.api.model.RegionType
        public int getCode() {
            return 0;
        }

        @Override // com.taptap.sdk.initializer.api.model.RegionType
        public String getValue() {
            return "tap_cn";
        }
    },
    GLOBAL { // from class: com.taptap.sdk.initializer.api.model.RegionType.GLOBAL
        @Override // com.taptap.sdk.initializer.api.model.RegionType
        public int getCode() {
            return 1;
        }

        @Override // com.taptap.sdk.initializer.api.model.RegionType
        public String getValue() {
            return "tap_intl";
        }
    };


    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    /* synthetic */ RegionType(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public abstract int getCode();

    public abstract String getValue();

    /* JADX INFO: compiled from: RegionType.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lcom/taptap/sdk/initializer/api/model/RegionType$Companion;", "", "()V", StatisticsContent.FROM, "Lcom/taptap/sdk/initializer/api/model/RegionType;", "code", "", "tap-initializer-api_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final RegionType from(int code) {
            RegionType[] regionTypeArrValues = RegionType.values();
            LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(regionTypeArrValues.length), 16));
            for (RegionType regionType : regionTypeArrValues) {
                linkedHashMap.put(Integer.valueOf(regionType.getCode()), regionType);
            }
            RegionType regionType2 = (RegionType) linkedHashMap.get(Integer.valueOf(code));
            return regionType2 == null ? RegionType.CN : regionType2;
        }
    }
}
