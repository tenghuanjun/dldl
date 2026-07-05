package com.taptap.sdk.initializer.data.request;

import com.sqwan.common.constants.SqConstants;
import com.taptap.sdk.db.biz.iap.lib2plus.BillingClientConstants;
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
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;

/* JADX INFO: compiled from: GateKeeperRequest.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0087\b\u0018\u0000 %2\u00020\u0001:\u0002$%B=\b\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\nB\u001f\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u000bJ\t\u0010\u0014\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u0016\u001a\u0004\u0018\u00010\u0005HÆ\u0003J)\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0013\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001b\u001a\u00020\u0003HÖ\u0001J\t\u0010\u001c\u001a\u00020\u0005HÖ\u0001J!\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u00002\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#HÇ\u0001R\u001c\u0010\u0006\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000fR\u001e\u0010\u0007\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0010\u0010\r\u001a\u0004\b\u0011\u0010\u000fR\u001c\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0012\u0010\r\u001a\u0004\b\u0013\u0010\u000f¨\u0006&"}, d2 = {"Lcom/taptap/sdk/initializer/data/request/GateKeeperRequest;", "", "seen1", "", "platform", "", BillingClientConstants.PACKAGE_NAME, "packageSign", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getPackageName$annotations", "()V", "getPackageName", "()Ljava/lang/String;", "getPackageSign$annotations", "getPackageSign", "getPlatform$annotations", "getPlatform", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "write$Self", "", SqConstants.OAUTH_SQ, "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "$serializer", "Companion", "tap-initializer_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
@Serializable
public final /* data */ class GateKeeperRequest {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final String packageName;
    private final String packageSign;
    private final String platform;

    public static /* synthetic */ GateKeeperRequest copy$default(GateKeeperRequest gateKeeperRequest, String str, String str2, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = gateKeeperRequest.platform;
        }
        if ((i & 2) != 0) {
            str2 = gateKeeperRequest.packageName;
        }
        if ((i & 4) != 0) {
            str3 = gateKeeperRequest.packageSign;
        }
        return gateKeeperRequest.copy(str, str2, str3);
    }

    @SerialName("package_name")
    public static /* synthetic */ void getPackageName$annotations() {
    }

    @SerialName("package_sign")
    public static /* synthetic */ void getPackageSign$annotations() {
    }

    @SerialName("platform")
    public static /* synthetic */ void getPlatform$annotations() {
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getPlatform() {
        return this.platform;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getPackageName() {
        return this.packageName;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getPackageSign() {
        return this.packageSign;
    }

    public final GateKeeperRequest copy(String platform, String packageName, String packageSign) {
        Intrinsics.checkNotNullParameter(platform, "platform");
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        return new GateKeeperRequest(platform, packageName, packageSign);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof GateKeeperRequest)) {
            return false;
        }
        GateKeeperRequest gateKeeperRequest = (GateKeeperRequest) other;
        return Intrinsics.areEqual(this.platform, gateKeeperRequest.platform) && Intrinsics.areEqual(this.packageName, gateKeeperRequest.packageName) && Intrinsics.areEqual(this.packageSign, gateKeeperRequest.packageSign);
    }

    public int hashCode() {
        int iHashCode = ((this.platform.hashCode() * 31) + this.packageName.hashCode()) * 31;
        String str = this.packageSign;
        return iHashCode + (str == null ? 0 : str.hashCode());
    }

    public String toString() {
        return "GateKeeperRequest(platform=" + this.platform + ", packageName=" + this.packageName + ", packageSign=" + this.packageSign + ')';
    }

    /* JADX INFO: compiled from: GateKeeperRequest.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¨\u0006\u0006"}, d2 = {"Lcom/taptap/sdk/initializer/data/request/GateKeeperRequest$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/taptap/sdk/initializer/data/request/GateKeeperRequest;", "tap-initializer_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final KSerializer<GateKeeperRequest> serializer() {
            return GateKeeperRequest$$serializer.INSTANCE;
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ GateKeeperRequest(int i, @SerialName("platform") String str, @SerialName("package_name") String str2, @SerialName("package_sign") String str3, SerializationConstructorMarker serializationConstructorMarker) {
        if (7 != (i & 7)) {
            PluginExceptionsKt.throwMissingFieldException(i, 7, GateKeeperRequest$$serializer.INSTANCE.getDescriptor());
        }
        this.platform = str;
        this.packageName = str2;
        this.packageSign = str3;
    }

    public GateKeeperRequest(String platform, String packageName, String str) {
        Intrinsics.checkNotNullParameter(platform, "platform");
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        this.platform = platform;
        this.packageName = packageName;
        this.packageSign = str;
    }

    @JvmStatic
    public static final void write$Self(GateKeeperRequest self, CompositeEncoder output, SerialDescriptor serialDesc) {
        Intrinsics.checkNotNullParameter(self, "self");
        Intrinsics.checkNotNullParameter(output, "output");
        Intrinsics.checkNotNullParameter(serialDesc, "serialDesc");
        output.encodeStringElement(serialDesc, 0, self.platform);
        output.encodeStringElement(serialDesc, 1, self.packageName);
        output.encodeNullableSerializableElement(serialDesc, 2, StringSerializer.INSTANCE, self.packageSign);
    }

    public final String getPlatform() {
        return this.platform;
    }

    public final String getPackageName() {
        return this.packageName;
    }

    public final String getPackageSign() {
        return this.packageSign;
    }
}
