package com.taptap.sdk.initializer.data.response;

import com.huya.berry.gamesdk.report.SdkReportConst;
import com.huya.berry.module.live.ILiveConstants;
import com.sqwan.common.constants.SqConstants;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerialName;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.IntSerializer;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;

/* JADX INFO: compiled from: GateKeeper.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0087\b\u0018\u0000 12\u00020\u0001:\u000501234BI\b\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\n\b\u0001\u0010\t\u001a\u0004\u0018\u00010\n\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\u0002\u0010\rB-\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\b\b\u0002\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000eJ\t\u0010\u001d\u001a\u00020\u0005HÆ\u0003J\u0010\u0010\u001e\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0018J\t\u0010\u001f\u001a\u00020\bHÆ\u0003J\t\u0010 \u001a\u00020\nHÆ\u0003J8\u0010!\u001a\u00020\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\nHÆ\u0001¢\u0006\u0002\u0010\"J\u0013\u0010#\u001a\u00020$2\b\u0010%\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010&\u001a\u00020\u0003HÖ\u0001J\t\u0010'\u001a\u00020(HÖ\u0001J!\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020\u00002\u0006\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020/HÇ\u0001R\u001c\u0010\t\u001a\u00020\n8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012R\u001c\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0013\u0010\u0010\u001a\u0004\b\u0014\u0010\u0015R \u0010\u0006\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010\u0019\u0012\u0004\b\u0016\u0010\u0010\u001a\u0004\b\u0017\u0010\u0018R\u001c\u0010\u0007\u001a\u00020\b8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001a\u0010\u0010\u001a\u0004\b\u001b\u0010\u001c¨\u00065"}, d2 = {"Lcom/taptap/sdk/initializer/data/response/GateKeeper;", "", "seen1", "", "switch", "Lcom/taptap/sdk/initializer/data/response/GateKeeper$Switch;", "taptapAppId", "urls", "Lcom/taptap/sdk/initializer/data/response/GateKeeper$Urls;", "config", "Lcom/taptap/sdk/initializer/data/response/GateKeeper$SdkConfig;", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILcom/taptap/sdk/initializer/data/response/GateKeeper$Switch;Ljava/lang/Integer;Lcom/taptap/sdk/initializer/data/response/GateKeeper$Urls;Lcom/taptap/sdk/initializer/data/response/GateKeeper$SdkConfig;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "(Lcom/taptap/sdk/initializer/data/response/GateKeeper$Switch;Ljava/lang/Integer;Lcom/taptap/sdk/initializer/data/response/GateKeeper$Urls;Lcom/taptap/sdk/initializer/data/response/GateKeeper$SdkConfig;)V", "getConfig$annotations", "()V", ILiveConstants.FuncName.GETCONFIG, "()Lcom/taptap/sdk/initializer/data/response/GateKeeper$SdkConfig;", "getSwitch$annotations", "getSwitch", "()Lcom/taptap/sdk/initializer/data/response/GateKeeper$Switch;", "getTaptapAppId$annotations", "getTaptapAppId", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getUrls$annotations", "getUrls", "()Lcom/taptap/sdk/initializer/data/response/GateKeeper$Urls;", "component1", "component2", "component3", "component4", "copy", "(Lcom/taptap/sdk/initializer/data/response/GateKeeper$Switch;Ljava/lang/Integer;Lcom/taptap/sdk/initializer/data/response/GateKeeper$Urls;Lcom/taptap/sdk/initializer/data/response/GateKeeper$SdkConfig;)Lcom/taptap/sdk/initializer/data/response/GateKeeper;", "equals", "", "other", "hashCode", "toString", "", "write$Self", "", SqConstants.OAUTH_SQ, "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "$serializer", "Companion", "SdkConfig", "Switch", "Urls", "tap-initializer_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
@Serializable
public final /* data */ class GateKeeper {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final SdkConfig config;
    private final Switch switch;
    private final Integer taptapAppId;
    private final Urls urls;

    public static /* synthetic */ GateKeeper copy$default(GateKeeper gateKeeper, Switch r1, Integer num, Urls urls, SdkConfig sdkConfig, int i, Object obj) {
        if ((i & 1) != 0) {
            r1 = gateKeeper.switch;
        }
        if ((i & 2) != 0) {
            num = gateKeeper.taptapAppId;
        }
        if ((i & 4) != 0) {
            urls = gateKeeper.urls;
        }
        if ((i & 8) != 0) {
            sdkConfig = gateKeeper.config;
        }
        return gateKeeper.copy(r1, num, urls, sdkConfig);
    }

    @SerialName("config")
    public static /* synthetic */ void getConfig$annotations() {
    }

    @SerialName("switch")
    public static /* synthetic */ void getSwitch$annotations() {
    }

    @SerialName("taptap_app_id")
    public static /* synthetic */ void getTaptapAppId$annotations() {
    }

    @SerialName("urls")
    public static /* synthetic */ void getUrls$annotations() {
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final Switch getSwitch() {
        return this.switch;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final Integer getTaptapAppId() {
        return this.taptapAppId;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final Urls getUrls() {
        return this.urls;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final SdkConfig getConfig() {
        return this.config;
    }

    public final GateKeeper copy(Switch r2, Integer taptapAppId, Urls urls, SdkConfig config) {
        Intrinsics.checkNotNullParameter(r2, "switch");
        Intrinsics.checkNotNullParameter(urls, "urls");
        Intrinsics.checkNotNullParameter(config, "config");
        return new GateKeeper(r2, taptapAppId, urls, config);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof GateKeeper)) {
            return false;
        }
        GateKeeper gateKeeper = (GateKeeper) other;
        return Intrinsics.areEqual(this.switch, gateKeeper.switch) && Intrinsics.areEqual(this.taptapAppId, gateKeeper.taptapAppId) && Intrinsics.areEqual(this.urls, gateKeeper.urls) && Intrinsics.areEqual(this.config, gateKeeper.config);
    }

    public int hashCode() {
        int iHashCode = this.switch.hashCode() * 31;
        Integer num = this.taptapAppId;
        return ((((iHashCode + (num == null ? 0 : num.hashCode())) * 31) + this.urls.hashCode()) * 31) + this.config.hashCode();
    }

    public String toString() {
        return "GateKeeper(switch=" + this.switch + ", taptapAppId=" + this.taptapAppId + ", urls=" + this.urls + ", config=" + this.config + ')';
    }

    /* JADX INFO: compiled from: GateKeeper.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¨\u0006\u0006"}, d2 = {"Lcom/taptap/sdk/initializer/data/response/GateKeeper$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/taptap/sdk/initializer/data/response/GateKeeper;", "tap-initializer_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final KSerializer<GateKeeper> serializer() {
            return GateKeeper$$serializer.INSTANCE;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ GateKeeper(int i, @SerialName("switch") Switch r16, @SerialName("taptap_app_id") Integer num, @SerialName("urls") Urls urls, @SerialName("config") SdkConfig sdkConfig, SerializationConstructorMarker serializationConstructorMarker) {
        SdkConfig sdkConfig2;
        int i2 = 1;
        if (1 != (i & 1)) {
            PluginExceptionsKt.throwMissingFieldException(i, 1, GateKeeper$$serializer.INSTANCE.getDescriptor());
        }
        this.switch = r16;
        SdkConfig.RelationConfig relationConfig = null;
        Object[] objArr = 0;
        if ((i & 2) == 0) {
            this.taptapAppId = null;
        } else {
            this.taptapAppId = num;
        }
        this.urls = (i & 4) == 0 ? new Urls((Urls.Url) null, (Urls.Url) null, (Urls.Url) null, (Urls.Url) null, (Urls.Url) null, (Urls.Url) null, 63, (DefaultConstructorMarker) null) : urls;
        if ((i & 8) == 0) {
            sdkConfig2 = new SdkConfig(relationConfig, i2, (DefaultConstructorMarker) (objArr == true ? 1 : 0));
        } else {
            sdkConfig2 = sdkConfig;
        }
        this.config = sdkConfig2;
    }

    public GateKeeper(Switch r2, Integer num, Urls urls, SdkConfig config) {
        Intrinsics.checkNotNullParameter(r2, "switch");
        Intrinsics.checkNotNullParameter(urls, "urls");
        Intrinsics.checkNotNullParameter(config, "config");
        this.switch = r2;
        this.taptapAppId = num;
        this.urls = urls;
        this.config = config;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @JvmStatic
    public static final void write$Self(GateKeeper self, CompositeEncoder output, SerialDescriptor serialDesc) {
        Intrinsics.checkNotNullParameter(self, "self");
        Intrinsics.checkNotNullParameter(output, "output");
        Intrinsics.checkNotNullParameter(serialDesc, "serialDesc");
        output.encodeSerializableElement(serialDesc, 0, GateKeeper$Switch$$serializer.INSTANCE, self.switch);
        int i = 1;
        if (output.shouldEncodeElementDefault(serialDesc, 1) || self.taptapAppId != null) {
            output.encodeNullableSerializableElement(serialDesc, 1, IntSerializer.INSTANCE, self.taptapAppId);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 2) || !Intrinsics.areEqual(self.urls, new Urls((Urls.Url) null, (Urls.Url) null, (Urls.Url) null, (Urls.Url) null, (Urls.Url) null, (Urls.Url) null, 63, (DefaultConstructorMarker) null))) {
            output.encodeSerializableElement(serialDesc, 2, GateKeeper$Urls$$serializer.INSTANCE, self.urls);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 3) || !Intrinsics.areEqual(self.config, new SdkConfig((SdkConfig.RelationConfig) null, i, (DefaultConstructorMarker) (0 == true ? 1 : 0)))) {
            output.encodeSerializableElement(serialDesc, 3, GateKeeper$SdkConfig$$serializer.INSTANCE, self.config);
        }
    }

    public final Switch getSwitch() {
        return this.switch;
    }

    public final Integer getTaptapAppId() {
        return this.taptapAppId;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public /* synthetic */ GateKeeper(Switch r13, Integer num, Urls urls, SdkConfig sdkConfig, int i, DefaultConstructorMarker defaultConstructorMarker) {
        SdkConfig sdkConfig2;
        SdkConfig.RelationConfig relationConfig = null;
        Object[] objArr = 0;
        Integer num2 = (i & 2) != 0 ? null : num;
        Urls urls2 = (i & 4) != 0 ? new Urls((Urls.Url) null, (Urls.Url) null, (Urls.Url) null, (Urls.Url) null, (Urls.Url) null, (Urls.Url) null, 63, (DefaultConstructorMarker) null) : urls;
        if ((i & 8) != 0) {
            sdkConfig2 = new SdkConfig(relationConfig, 1, (DefaultConstructorMarker) (objArr == true ? 1 : 0));
        } else {
            sdkConfig2 = sdkConfig;
        }
        this(r13, num2, urls2, sdkConfig2);
    }

    public final Urls getUrls() {
        return this.urls;
    }

    public final SdkConfig getConfig() {
        return this.config;
    }

    /* JADX INFO: compiled from: GateKeeper.kt */
    @Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0087\b\u0018\u0000 !2\u00020\u0001:\u0002 !B-\b\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010\tB\u0015\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\nJ\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00052\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001J!\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u00002\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001fHÇ\u0001R\u001c\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000eR\u001c\u0010\u0006\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000f\u0010\f\u001a\u0004\b\u0010\u0010\u000e¨\u0006\""}, d2 = {"Lcom/taptap/sdk/initializer/data/response/GateKeeper$Switch;", "", "seen1", "", "autoEvent", "", SdkReportConst.HuyaHeartBeart, "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(IZZLkotlinx/serialization/internal/SerializationConstructorMarker;)V", "(ZZ)V", "getAutoEvent$annotations", "()V", "getAutoEvent", "()Z", "getHeartbeat$annotations", "getHeartbeat", "component1", "component2", "copy", "equals", "other", "hashCode", "toString", "", "write$Self", "", SqConstants.OAUTH_SQ, "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "$serializer", "Companion", "tap-initializer_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    @Serializable
    public static final /* data */ class Switch {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private final boolean autoEvent;
        private final boolean heartbeat;

        public static /* synthetic */ Switch copy$default(Switch r0, boolean z, boolean z2, int i, Object obj) {
            if ((i & 1) != 0) {
                z = r0.autoEvent;
            }
            if ((i & 2) != 0) {
                z2 = r0.heartbeat;
            }
            return r0.copy(z, z2);
        }

        @SerialName("auto_event")
        public static /* synthetic */ void getAutoEvent$annotations() {
        }

        @SerialName(SdkReportConst.HuyaHeartBeart)
        public static /* synthetic */ void getHeartbeat$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final boolean getAutoEvent() {
            return this.autoEvent;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final boolean getHeartbeat() {
            return this.heartbeat;
        }

        public final Switch copy(boolean autoEvent, boolean heartbeat) {
            return new Switch(autoEvent, heartbeat);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Switch)) {
                return false;
            }
            Switch r5 = (Switch) other;
            return this.autoEvent == r5.autoEvent && this.heartbeat == r5.heartbeat;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v1, types: [int] */
        /* JADX WARN: Type inference failed for: r0v4 */
        /* JADX WARN: Type inference failed for: r0v5 */
        /* JADX WARN: Type inference failed for: r1v0 */
        /* JADX WARN: Type inference failed for: r1v1, types: [int] */
        /* JADX WARN: Type inference failed for: r1v2 */
        public int hashCode() {
            boolean z = this.autoEvent;
            ?? r0 = z;
            if (z) {
                r0 = 1;
            }
            int i = r0 * 31;
            boolean z2 = this.heartbeat;
            return i + (z2 ? 1 : z2);
        }

        public String toString() {
            return "Switch(autoEvent=" + this.autoEvent + ", heartbeat=" + this.heartbeat + ')';
        }

        /* JADX INFO: compiled from: GateKeeper.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¨\u0006\u0006"}, d2 = {"Lcom/taptap/sdk/initializer/data/response/GateKeeper$Switch$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/taptap/sdk/initializer/data/response/GateKeeper$Switch;", "tap-initializer_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<Switch> serializer() {
                return GateKeeper$Switch$$serializer.INSTANCE;
            }
        }

        @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
        public /* synthetic */ Switch(int i, @SerialName("auto_event") boolean z, @SerialName(SdkReportConst.HuyaHeartBeart) boolean z2, SerializationConstructorMarker serializationConstructorMarker) {
            if (3 != (i & 3)) {
                PluginExceptionsKt.throwMissingFieldException(i, 3, GateKeeper$Switch$$serializer.INSTANCE.getDescriptor());
            }
            this.autoEvent = z;
            this.heartbeat = z2;
        }

        public Switch(boolean z, boolean z2) {
            this.autoEvent = z;
            this.heartbeat = z2;
        }

        @JvmStatic
        public static final void write$Self(Switch self, CompositeEncoder output, SerialDescriptor serialDesc) {
            Intrinsics.checkNotNullParameter(self, "self");
            Intrinsics.checkNotNullParameter(output, "output");
            Intrinsics.checkNotNullParameter(serialDesc, "serialDesc");
            output.encodeBooleanElement(serialDesc, 0, self.autoEvent);
            output.encodeBooleanElement(serialDesc, 1, self.heartbeat);
        }

        public final boolean getAutoEvent() {
            return this.autoEvent;
        }

        public final boolean getHeartbeat() {
            return this.heartbeat;
        }
    }

    /* JADX INFO: compiled from: GateKeeper.kt */
    @Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 22\u00020\u0001:\u0003123Ba\b\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\b\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\t\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\n\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\u0002\u0010\rBA\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0005\u0012\b\b\u0002\u0010\b\u001a\u00020\u0005\u0012\b\b\u0002\u0010\t\u001a\u00020\u0005\u0012\b\b\u0002\u0010\n\u001a\u00020\u0005¢\u0006\u0002\u0010\u000eJ\t\u0010\u001d\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0005HÆ\u0003J\t\u0010 \u001a\u00020\u0005HÆ\u0003J\t\u0010!\u001a\u00020\u0005HÆ\u0003J\t\u0010\"\u001a\u00020\u0005HÆ\u0003JE\u0010#\u001a\u00020\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\u00052\b\b\u0002\u0010\n\u001a\u00020\u0005HÆ\u0001J\u0013\u0010$\u001a\u00020%2\b\u0010&\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010'\u001a\u00020\u0003HÖ\u0001J\t\u0010(\u001a\u00020)HÖ\u0001J!\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020\u00002\u0006\u0010-\u001a\u00020.2\u0006\u0010/\u001a\u000200HÇ\u0001R\u001c\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012R\u001c\u0010\t\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0013\u0010\u0010\u001a\u0004\b\u0014\u0010\u0012R\u001c\u0010\n\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0015\u0010\u0010\u001a\u0004\b\u0016\u0010\u0012R\u001c\u0010\b\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0017\u0010\u0010\u001a\u0004\b\u0018\u0010\u0012R\u001c\u0010\u0007\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0019\u0010\u0010\u001a\u0004\b\u001a\u0010\u0012R\u001c\u0010\u0006\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001b\u0010\u0010\u001a\u0004\b\u001c\u0010\u0012¨\u00064"}, d2 = {"Lcom/taptap/sdk/initializer/data/response/GateKeeper$Urls;", "", "seen1", "", "achievementUrl", "Lcom/taptap/sdk/initializer/data/response/GateKeeper$Urls$Url;", "shareUrl", "reviewUrl", "relationUrl", "relationAddFriendUrl", "relationNotificationsUrl", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILcom/taptap/sdk/initializer/data/response/GateKeeper$Urls$Url;Lcom/taptap/sdk/initializer/data/response/GateKeeper$Urls$Url;Lcom/taptap/sdk/initializer/data/response/GateKeeper$Urls$Url;Lcom/taptap/sdk/initializer/data/response/GateKeeper$Urls$Url;Lcom/taptap/sdk/initializer/data/response/GateKeeper$Urls$Url;Lcom/taptap/sdk/initializer/data/response/GateKeeper$Urls$Url;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "(Lcom/taptap/sdk/initializer/data/response/GateKeeper$Urls$Url;Lcom/taptap/sdk/initializer/data/response/GateKeeper$Urls$Url;Lcom/taptap/sdk/initializer/data/response/GateKeeper$Urls$Url;Lcom/taptap/sdk/initializer/data/response/GateKeeper$Urls$Url;Lcom/taptap/sdk/initializer/data/response/GateKeeper$Urls$Url;Lcom/taptap/sdk/initializer/data/response/GateKeeper$Urls$Url;)V", "getAchievementUrl$annotations", "()V", "getAchievementUrl", "()Lcom/taptap/sdk/initializer/data/response/GateKeeper$Urls$Url;", "getRelationAddFriendUrl$annotations", "getRelationAddFriendUrl", "getRelationNotificationsUrl$annotations", "getRelationNotificationsUrl", "getRelationUrl$annotations", "getRelationUrl", "getReviewUrl$annotations", "getReviewUrl", "getShareUrl$annotations", "getShareUrl", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "toString", "", "write$Self", "", SqConstants.OAUTH_SQ, "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "$serializer", "Companion", "Url", "tap-initializer_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    @Serializable
    public static final /* data */ class Urls {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private final Url achievementUrl;
        private final Url relationAddFriendUrl;
        private final Url relationNotificationsUrl;
        private final Url relationUrl;
        private final Url reviewUrl;
        private final Url shareUrl;

        public Urls() {
            this((Url) null, (Url) null, (Url) null, (Url) null, (Url) null, (Url) null, 63, (DefaultConstructorMarker) null);
        }

        public static /* synthetic */ Urls copy$default(Urls urls, Url url, Url url2, Url url3, Url url4, Url url5, Url url6, int i, Object obj) {
            if ((i & 1) != 0) {
                url = urls.achievementUrl;
            }
            if ((i & 2) != 0) {
                url2 = urls.shareUrl;
            }
            Url url7 = url2;
            if ((i & 4) != 0) {
                url3 = urls.reviewUrl;
            }
            Url url8 = url3;
            if ((i & 8) != 0) {
                url4 = urls.relationUrl;
            }
            Url url9 = url4;
            if ((i & 16) != 0) {
                url5 = urls.relationAddFriendUrl;
            }
            Url url10 = url5;
            if ((i & 32) != 0) {
                url6 = urls.relationNotificationsUrl;
            }
            return urls.copy(url, url7, url8, url9, url10, url6);
        }

        @SerialName("achievement_my_list_url")
        public static /* synthetic */ void getAchievementUrl$annotations() {
        }

        @SerialName("relation_add_friend_url")
        public static /* synthetic */ void getRelationAddFriendUrl$annotations() {
        }

        @SerialName("relation_notifications_url")
        public static /* synthetic */ void getRelationNotificationsUrl$annotations() {
        }

        @SerialName("relation_url")
        public static /* synthetic */ void getRelationUrl$annotations() {
        }

        @SerialName("add_review_url")
        public static /* synthetic */ void getReviewUrl$annotations() {
        }

        @SerialName("rep_share_url")
        public static /* synthetic */ void getShareUrl$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final Url getAchievementUrl() {
            return this.achievementUrl;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final Url getShareUrl() {
            return this.shareUrl;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final Url getReviewUrl() {
            return this.reviewUrl;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final Url getRelationUrl() {
            return this.relationUrl;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final Url getRelationAddFriendUrl() {
            return this.relationAddFriendUrl;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final Url getRelationNotificationsUrl() {
            return this.relationNotificationsUrl;
        }

        public final Urls copy(Url achievementUrl, Url shareUrl, Url reviewUrl, Url relationUrl, Url relationAddFriendUrl, Url relationNotificationsUrl) {
            Intrinsics.checkNotNullParameter(achievementUrl, "achievementUrl");
            Intrinsics.checkNotNullParameter(shareUrl, "shareUrl");
            Intrinsics.checkNotNullParameter(reviewUrl, "reviewUrl");
            Intrinsics.checkNotNullParameter(relationUrl, "relationUrl");
            Intrinsics.checkNotNullParameter(relationAddFriendUrl, "relationAddFriendUrl");
            Intrinsics.checkNotNullParameter(relationNotificationsUrl, "relationNotificationsUrl");
            return new Urls(achievementUrl, shareUrl, reviewUrl, relationUrl, relationAddFriendUrl, relationNotificationsUrl);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Urls)) {
                return false;
            }
            Urls urls = (Urls) other;
            return Intrinsics.areEqual(this.achievementUrl, urls.achievementUrl) && Intrinsics.areEqual(this.shareUrl, urls.shareUrl) && Intrinsics.areEqual(this.reviewUrl, urls.reviewUrl) && Intrinsics.areEqual(this.relationUrl, urls.relationUrl) && Intrinsics.areEqual(this.relationAddFriendUrl, urls.relationAddFriendUrl) && Intrinsics.areEqual(this.relationNotificationsUrl, urls.relationNotificationsUrl);
        }

        public int hashCode() {
            return (((((((((this.achievementUrl.hashCode() * 31) + this.shareUrl.hashCode()) * 31) + this.reviewUrl.hashCode()) * 31) + this.relationUrl.hashCode()) * 31) + this.relationAddFriendUrl.hashCode()) * 31) + this.relationNotificationsUrl.hashCode();
        }

        public String toString() {
            return "Urls(achievementUrl=" + this.achievementUrl + ", shareUrl=" + this.shareUrl + ", reviewUrl=" + this.reviewUrl + ", relationUrl=" + this.relationUrl + ", relationAddFriendUrl=" + this.relationAddFriendUrl + ", relationNotificationsUrl=" + this.relationNotificationsUrl + ')';
        }

        /* JADX INFO: compiled from: GateKeeper.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¨\u0006\u0006"}, d2 = {"Lcom/taptap/sdk/initializer/data/response/GateKeeper$Urls$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/taptap/sdk/initializer/data/response/GateKeeper$Urls;", "tap-initializer_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<Urls> serializer() {
                return GateKeeper$Urls$$serializer.INSTANCE;
            }
        }

        @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
        public /* synthetic */ Urls(int i, @SerialName("achievement_my_list_url") Url url, @SerialName("rep_share_url") Url url2, @SerialName("add_review_url") Url url3, @SerialName("relation_url") Url url4, @SerialName("relation_add_friend_url") Url url5, @SerialName("relation_notifications_url") Url url6, SerializationConstructorMarker serializationConstructorMarker) {
            if ((i & 0) != 0) {
                PluginExceptionsKt.throwMissingFieldException(i, 0, GateKeeper$Urls$$serializer.INSTANCE.getDescriptor());
            }
            this.achievementUrl = (i & 1) == 0 ? new Url((String) null, (String) null, (String) null, 7, (DefaultConstructorMarker) null) : url;
            if ((i & 2) == 0) {
                this.shareUrl = new Url((String) null, (String) null, (String) null, 7, (DefaultConstructorMarker) null);
            } else {
                this.shareUrl = url2;
            }
            if ((i & 4) == 0) {
                this.reviewUrl = new Url((String) null, (String) null, (String) null, 7, (DefaultConstructorMarker) null);
            } else {
                this.reviewUrl = url3;
            }
            if ((i & 8) == 0) {
                this.relationUrl = new Url((String) null, (String) null, (String) null, 7, (DefaultConstructorMarker) null);
            } else {
                this.relationUrl = url4;
            }
            if ((i & 16) == 0) {
                this.relationAddFriendUrl = new Url((String) null, (String) null, (String) null, 7, (DefaultConstructorMarker) null);
            } else {
                this.relationAddFriendUrl = url5;
            }
            if ((i & 32) == 0) {
                this.relationNotificationsUrl = new Url((String) null, (String) null, (String) null, 7, (DefaultConstructorMarker) null);
            } else {
                this.relationNotificationsUrl = url6;
            }
        }

        public Urls(Url achievementUrl, Url shareUrl, Url reviewUrl, Url relationUrl, Url relationAddFriendUrl, Url relationNotificationsUrl) {
            Intrinsics.checkNotNullParameter(achievementUrl, "achievementUrl");
            Intrinsics.checkNotNullParameter(shareUrl, "shareUrl");
            Intrinsics.checkNotNullParameter(reviewUrl, "reviewUrl");
            Intrinsics.checkNotNullParameter(relationUrl, "relationUrl");
            Intrinsics.checkNotNullParameter(relationAddFriendUrl, "relationAddFriendUrl");
            Intrinsics.checkNotNullParameter(relationNotificationsUrl, "relationNotificationsUrl");
            this.achievementUrl = achievementUrl;
            this.shareUrl = shareUrl;
            this.reviewUrl = reviewUrl;
            this.relationUrl = relationUrl;
            this.relationAddFriendUrl = relationAddFriendUrl;
            this.relationNotificationsUrl = relationNotificationsUrl;
        }

        @JvmStatic
        public static final void write$Self(Urls self, CompositeEncoder output, SerialDescriptor serialDesc) {
            Intrinsics.checkNotNullParameter(self, "self");
            Intrinsics.checkNotNullParameter(output, "output");
            Intrinsics.checkNotNullParameter(serialDesc, "serialDesc");
            if (output.shouldEncodeElementDefault(serialDesc, 0) || !Intrinsics.areEqual(self.achievementUrl, new Url((String) null, (String) null, (String) null, 7, (DefaultConstructorMarker) null))) {
                output.encodeSerializableElement(serialDesc, 0, GateKeeper$Urls$Url$$serializer.INSTANCE, self.achievementUrl);
            }
            if (output.shouldEncodeElementDefault(serialDesc, 1) || !Intrinsics.areEqual(self.shareUrl, new Url((String) null, (String) null, (String) null, 7, (DefaultConstructorMarker) null))) {
                output.encodeSerializableElement(serialDesc, 1, GateKeeper$Urls$Url$$serializer.INSTANCE, self.shareUrl);
            }
            if (output.shouldEncodeElementDefault(serialDesc, 2) || !Intrinsics.areEqual(self.reviewUrl, new Url((String) null, (String) null, (String) null, 7, (DefaultConstructorMarker) null))) {
                output.encodeSerializableElement(serialDesc, 2, GateKeeper$Urls$Url$$serializer.INSTANCE, self.reviewUrl);
            }
            if (output.shouldEncodeElementDefault(serialDesc, 3) || !Intrinsics.areEqual(self.relationUrl, new Url((String) null, (String) null, (String) null, 7, (DefaultConstructorMarker) null))) {
                output.encodeSerializableElement(serialDesc, 3, GateKeeper$Urls$Url$$serializer.INSTANCE, self.relationUrl);
            }
            if (output.shouldEncodeElementDefault(serialDesc, 4) || !Intrinsics.areEqual(self.relationAddFriendUrl, new Url((String) null, (String) null, (String) null, 7, (DefaultConstructorMarker) null))) {
                output.encodeSerializableElement(serialDesc, 4, GateKeeper$Urls$Url$$serializer.INSTANCE, self.relationAddFriendUrl);
            }
            if (output.shouldEncodeElementDefault(serialDesc, 5) || !Intrinsics.areEqual(self.relationNotificationsUrl, new Url((String) null, (String) null, (String) null, 7, (DefaultConstructorMarker) null))) {
                output.encodeSerializableElement(serialDesc, 5, GateKeeper$Urls$Url$$serializer.INSTANCE, self.relationNotificationsUrl);
            }
        }

        public /* synthetic */ Urls(Url url, Url url2, Url url3, Url url4, Url url5, Url url6, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? new Url((String) null, (String) null, (String) null, 7, (DefaultConstructorMarker) null) : url, (i & 2) != 0 ? new Url((String) null, (String) null, (String) null, 7, (DefaultConstructorMarker) null) : url2, (i & 4) != 0 ? new Url((String) null, (String) null, (String) null, 7, (DefaultConstructorMarker) null) : url3, (i & 8) != 0 ? new Url((String) null, (String) null, (String) null, 7, (DefaultConstructorMarker) null) : url4, (i & 16) != 0 ? new Url((String) null, (String) null, (String) null, 7, (DefaultConstructorMarker) null) : url5, (i & 32) != 0 ? new Url((String) null, (String) null, (String) null, 7, (DefaultConstructorMarker) null) : url6);
        }

        public final Url getAchievementUrl() {
            return this.achievementUrl;
        }

        public final Url getShareUrl() {
            return this.shareUrl;
        }

        public final Url getReviewUrl() {
            return this.reviewUrl;
        }

        public final Url getRelationUrl() {
            return this.relationUrl;
        }

        public final Url getRelationAddFriendUrl() {
            return this.relationAddFriendUrl;
        }

        public final Url getRelationNotificationsUrl() {
            return this.relationNotificationsUrl;
        }

        /* JADX INFO: compiled from: GateKeeper.kt */
        @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0087\b\u0018\u0000 %2\u00020\u0001:\u0002$%B=\b\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\nB#\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0005¢\u0006\u0002\u0010\u000bJ\t\u0010\u0014\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0005HÆ\u0003J'\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001b\u001a\u00020\u0003HÖ\u0001J\t\u0010\u001c\u001a\u00020\u0005HÖ\u0001J!\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u00002\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#HÇ\u0001R\u001c\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000fR\u001c\u0010\u0006\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0010\u0010\r\u001a\u0004\b\u0011\u0010\u000fR\u001c\u0010\u0007\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0012\u0010\r\u001a\u0004\b\u0013\u0010\u000f¨\u0006&"}, d2 = {"Lcom/taptap/sdk/initializer/data/response/GateKeeper$Urls$Url;", "", "seen1", "", "browser", "", "uri", "webview", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getBrowser$annotations", "()V", "getBrowser", "()Ljava/lang/String;", "getUri$annotations", "getUri", "getWebview$annotations", "getWebview", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "write$Self", "", SqConstants.OAUTH_SQ, "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "$serializer", "Companion", "tap-initializer_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
        @Serializable
        public static final /* data */ class Url {

            /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
            public static final Companion INSTANCE = new Companion(null);
            private final String browser;
            private final String uri;
            private final String webview;

            public Url() {
                this((String) null, (String) null, (String) null, 7, (DefaultConstructorMarker) null);
            }

            public static /* synthetic */ Url copy$default(Url url, String str, String str2, String str3, int i, Object obj) {
                if ((i & 1) != 0) {
                    str = url.browser;
                }
                if ((i & 2) != 0) {
                    str2 = url.uri;
                }
                if ((i & 4) != 0) {
                    str3 = url.webview;
                }
                return url.copy(str, str2, str3);
            }

            @SerialName("browser")
            public static /* synthetic */ void getBrowser$annotations() {
            }

            @SerialName("uri")
            public static /* synthetic */ void getUri$annotations() {
            }

            @SerialName("webview")
            public static /* synthetic */ void getWebview$annotations() {
            }

            /* JADX INFO: renamed from: component1, reason: from getter */
            public final String getBrowser() {
                return this.browser;
            }

            /* JADX INFO: renamed from: component2, reason: from getter */
            public final String getUri() {
                return this.uri;
            }

            /* JADX INFO: renamed from: component3, reason: from getter */
            public final String getWebview() {
                return this.webview;
            }

            public final Url copy(String browser, String uri, String webview) {
                Intrinsics.checkNotNullParameter(browser, "browser");
                Intrinsics.checkNotNullParameter(uri, "uri");
                Intrinsics.checkNotNullParameter(webview, "webview");
                return new Url(browser, uri, webview);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof Url)) {
                    return false;
                }
                Url url = (Url) other;
                return Intrinsics.areEqual(this.browser, url.browser) && Intrinsics.areEqual(this.uri, url.uri) && Intrinsics.areEqual(this.webview, url.webview);
            }

            public int hashCode() {
                return (((this.browser.hashCode() * 31) + this.uri.hashCode()) * 31) + this.webview.hashCode();
            }

            public String toString() {
                return "Url(browser=" + this.browser + ", uri=" + this.uri + ", webview=" + this.webview + ')';
            }

            /* JADX INFO: compiled from: GateKeeper.kt */
            @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¨\u0006\u0006"}, d2 = {"Lcom/taptap/sdk/initializer/data/response/GateKeeper$Urls$Url$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/taptap/sdk/initializer/data/response/GateKeeper$Urls$Url;", "tap-initializer_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
            public static final class Companion {
                public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                    this();
                }

                private Companion() {
                }

                public final KSerializer<Url> serializer() {
                    return GateKeeper$Urls$Url$$serializer.INSTANCE;
                }
            }

            @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
            public /* synthetic */ Url(int i, @SerialName("browser") String str, @SerialName("uri") String str2, @SerialName("webview") String str3, SerializationConstructorMarker serializationConstructorMarker) {
                if ((i & 0) != 0) {
                    PluginExceptionsKt.throwMissingFieldException(i, 0, GateKeeper$Urls$Url$$serializer.INSTANCE.getDescriptor());
                }
                if ((i & 1) == 0) {
                    this.browser = "";
                } else {
                    this.browser = str;
                }
                if ((i & 2) == 0) {
                    this.uri = "";
                } else {
                    this.uri = str2;
                }
                if ((i & 4) == 0) {
                    this.webview = "";
                } else {
                    this.webview = str3;
                }
            }

            public Url(String browser, String uri, String webview) {
                Intrinsics.checkNotNullParameter(browser, "browser");
                Intrinsics.checkNotNullParameter(uri, "uri");
                Intrinsics.checkNotNullParameter(webview, "webview");
                this.browser = browser;
                this.uri = uri;
                this.webview = webview;
            }

            @JvmStatic
            public static final void write$Self(Url self, CompositeEncoder output, SerialDescriptor serialDesc) {
                Intrinsics.checkNotNullParameter(self, "self");
                Intrinsics.checkNotNullParameter(output, "output");
                Intrinsics.checkNotNullParameter(serialDesc, "serialDesc");
                if (output.shouldEncodeElementDefault(serialDesc, 0) || !Intrinsics.areEqual(self.browser, "")) {
                    output.encodeStringElement(serialDesc, 0, self.browser);
                }
                if (output.shouldEncodeElementDefault(serialDesc, 1) || !Intrinsics.areEqual(self.uri, "")) {
                    output.encodeStringElement(serialDesc, 1, self.uri);
                }
                if (output.shouldEncodeElementDefault(serialDesc, 2) || !Intrinsics.areEqual(self.webview, "")) {
                    output.encodeStringElement(serialDesc, 2, self.webview);
                }
            }

            public /* synthetic */ Url(String str, String str2, String str3, int i, DefaultConstructorMarker defaultConstructorMarker) {
                this((i & 1) != 0 ? "" : str, (i & 2) != 0 ? "" : str2, (i & 4) != 0 ? "" : str3);
            }

            public final String getBrowser() {
                return this.browser;
            }

            public final String getUri() {
                return this.uri;
            }

            public final String getWebview() {
                return this.webview;
            }
        }
    }

    /* JADX INFO: compiled from: GateKeeper.kt */
    @Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 \u001e2\u00020\u0001:\u0003\u001d\u001e\u001fB%\b\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bB\u000f\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\tJ\t\u0010\u000e\u001a\u00020\u0005HÆ\u0003J\u0013\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J!\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00002\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cHÇ\u0001R\u001c\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\r¨\u0006 "}, d2 = {"Lcom/taptap/sdk/initializer/data/response/GateKeeper$SdkConfig;", "", "seen1", "", "relation", "Lcom/taptap/sdk/initializer/data/response/GateKeeper$SdkConfig$RelationConfig;", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILcom/taptap/sdk/initializer/data/response/GateKeeper$SdkConfig$RelationConfig;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "(Lcom/taptap/sdk/initializer/data/response/GateKeeper$SdkConfig$RelationConfig;)V", "getRelation$annotations", "()V", ILiveConstants.FuncName.GET_RELATION, "()Lcom/taptap/sdk/initializer/data/response/GateKeeper$SdkConfig$RelationConfig;", "component1", "copy", "equals", "", "other", "hashCode", "toString", "", "write$Self", "", SqConstants.OAUTH_SQ, "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "$serializer", "Companion", "RelationConfig", "tap-initializer_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    @Serializable
    public static final /* data */ class SdkConfig {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private final RelationConfig relation;

        /* JADX WARN: Multi-variable type inference failed */
        public SdkConfig() {
            this((RelationConfig) null, 1, (DefaultConstructorMarker) (0 == true ? 1 : 0));
        }

        public static /* synthetic */ SdkConfig copy$default(SdkConfig sdkConfig, RelationConfig relationConfig, int i, Object obj) {
            if ((i & 1) != 0) {
                relationConfig = sdkConfig.relation;
            }
            return sdkConfig.copy(relationConfig);
        }

        @SerialName("relation")
        public static /* synthetic */ void getRelation$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final RelationConfig getRelation() {
            return this.relation;
        }

        public final SdkConfig copy(RelationConfig relation) {
            Intrinsics.checkNotNullParameter(relation, "relation");
            return new SdkConfig(relation);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof SdkConfig) && Intrinsics.areEqual(this.relation, ((SdkConfig) other).relation);
        }

        public int hashCode() {
            return this.relation.hashCode();
        }

        public String toString() {
            return "SdkConfig(relation=" + this.relation + ')';
        }

        /* JADX INFO: compiled from: GateKeeper.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¨\u0006\u0006"}, d2 = {"Lcom/taptap/sdk/initializer/data/response/GateKeeper$SdkConfig$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/taptap/sdk/initializer/data/response/GateKeeper$SdkConfig;", "tap-initializer_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<SdkConfig> serializer() {
                return GateKeeper$SdkConfig$$serializer.INSTANCE;
            }
        }

        @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
        public /* synthetic */ SdkConfig(int i, @SerialName("relation") RelationConfig relationConfig, SerializationConstructorMarker serializationConstructorMarker) {
            int i2 = 0;
            if ((i & 0) != 0) {
                PluginExceptionsKt.throwMissingFieldException(i, 0, GateKeeper$SdkConfig$$serializer.INSTANCE.getDescriptor());
            }
            int i3 = 1;
            if ((i & 1) == 0) {
                this.relation = new RelationConfig(i2, i3, (DefaultConstructorMarker) null);
            } else {
                this.relation = relationConfig;
            }
        }

        public SdkConfig(RelationConfig relation) {
            Intrinsics.checkNotNullParameter(relation, "relation");
            this.relation = relation;
        }

        @JvmStatic
        public static final void write$Self(SdkConfig self, CompositeEncoder output, SerialDescriptor serialDesc) {
            Intrinsics.checkNotNullParameter(self, "self");
            Intrinsics.checkNotNullParameter(output, "output");
            Intrinsics.checkNotNullParameter(serialDesc, "serialDesc");
            int i = 0;
            int i2 = 1;
            if (!output.shouldEncodeElementDefault(serialDesc, 0) && Intrinsics.areEqual(self.relation, new RelationConfig(i, i2, (DefaultConstructorMarker) null))) {
                i2 = 0;
            }
            if (i2 != 0) {
                output.encodeSerializableElement(serialDesc, 0, GateKeeper$SdkConfig$RelationConfig$$serializer.INSTANCE, self.relation);
            }
        }

        public /* synthetic */ SdkConfig(RelationConfig relationConfig, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? new RelationConfig(0, 1, (DefaultConstructorMarker) null) : relationConfig);
        }

        public final RelationConfig getRelation() {
            return this.relation;
        }

        /* JADX INFO: compiled from: GateKeeper.kt */
        @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0087\b\u0018\u0000 \u001d2\u00020\u0001:\u0002\u001c\u001dB#\b\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007B\u000f\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\bJ\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J!\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00002\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bHÇ\u0001R\u001c\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\f¨\u0006\u001e"}, d2 = {"Lcom/taptap/sdk/initializer/data/response/GateKeeper$SdkConfig$RelationConfig;", "", "seen1", "", "activeStatusPollingInterval", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(IILkotlinx/serialization/internal/SerializationConstructorMarker;)V", "(I)V", "getActiveStatusPollingInterval$annotations", "()V", "getActiveStatusPollingInterval", "()I", "component1", "copy", "equals", "", "other", "hashCode", "toString", "", "write$Self", "", SqConstants.OAUTH_SQ, "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "$serializer", "Companion", "tap-initializer_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
        @Serializable
        public static final /* data */ class RelationConfig {

            /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
            public static final Companion INSTANCE = new Companion(null);
            private final int activeStatusPollingInterval;

            public RelationConfig() {
                this(0, 1, (DefaultConstructorMarker) null);
            }

            public static /* synthetic */ RelationConfig copy$default(RelationConfig relationConfig, int i, int i2, Object obj) {
                if ((i2 & 1) != 0) {
                    i = relationConfig.activeStatusPollingInterval;
                }
                return relationConfig.copy(i);
            }

            @SerialName("active_status_polling_interval_sec")
            public static /* synthetic */ void getActiveStatusPollingInterval$annotations() {
            }

            /* JADX INFO: renamed from: component1, reason: from getter */
            public final int getActiveStatusPollingInterval() {
                return this.activeStatusPollingInterval;
            }

            public final RelationConfig copy(int activeStatusPollingInterval) {
                return new RelationConfig(activeStatusPollingInterval);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                return (other instanceof RelationConfig) && this.activeStatusPollingInterval == ((RelationConfig) other).activeStatusPollingInterval;
            }

            public int hashCode() {
                return this.activeStatusPollingInterval;
            }

            public String toString() {
                return "RelationConfig(activeStatusPollingInterval=" + this.activeStatusPollingInterval + ')';
            }

            /* JADX INFO: compiled from: GateKeeper.kt */
            @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¨\u0006\u0006"}, d2 = {"Lcom/taptap/sdk/initializer/data/response/GateKeeper$SdkConfig$RelationConfig$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/taptap/sdk/initializer/data/response/GateKeeper$SdkConfig$RelationConfig;", "tap-initializer_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
            public static final class Companion {
                public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                    this();
                }

                private Companion() {
                }

                public final KSerializer<RelationConfig> serializer() {
                    return GateKeeper$SdkConfig$RelationConfig$$serializer.INSTANCE;
                }
            }

            public RelationConfig(int i) {
                this.activeStatusPollingInterval = i;
            }

            @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
            public /* synthetic */ RelationConfig(int i, @SerialName("active_status_polling_interval_sec") int i2, SerializationConstructorMarker serializationConstructorMarker) {
                if ((i & 0) != 0) {
                    PluginExceptionsKt.throwMissingFieldException(i, 0, GateKeeper$SdkConfig$RelationConfig$$serializer.INSTANCE.getDescriptor());
                }
                if ((i & 1) == 0) {
                    this.activeStatusPollingInterval = 0;
                } else {
                    this.activeStatusPollingInterval = i2;
                }
            }

            @JvmStatic
            public static final void write$Self(RelationConfig self, CompositeEncoder output, SerialDescriptor serialDesc) {
                Intrinsics.checkNotNullParameter(self, "self");
                Intrinsics.checkNotNullParameter(output, "output");
                Intrinsics.checkNotNullParameter(serialDesc, "serialDesc");
                boolean z = true;
                if (!output.shouldEncodeElementDefault(serialDesc, 0) && self.activeStatusPollingInterval == 0) {
                    z = false;
                }
                if (z) {
                    output.encodeIntElement(serialDesc, 0, self.activeStatusPollingInterval);
                }
            }

            public /* synthetic */ RelationConfig(int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
                this((i2 & 1) != 0 ? 0 : i);
            }

            public final int getActiveStatusPollingInterval() {
                return this.activeStatusPollingInterval;
            }
        }
    }
}
