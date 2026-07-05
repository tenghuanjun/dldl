package com.taptap.sdk.common.network.data.response;

import com.sqwan.common.constants.SqConstants;
import com.sqwan.common.route.FunctionRouter;
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
import kotlinx.serialization.json.JsonElement;
import kotlinx.serialization.json.JsonElementSerializer;

/* JADX INFO: compiled from: BaseResponse.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0087\b\u0018\u0000 (2\u00020\u0001:\u0002'(B9\b\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0003\u0012\n\b\u0001\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0002\u0010\u000bB\u001d\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\fJ\t\u0010\u0017\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\bHÆ\u0003J'\u0010\u001a\u001a\u00020\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\bHÆ\u0001J\u0013\u0010\u001b\u001a\u00020\u00052\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001d\u001a\u00020\u0003HÖ\u0001J\t\u0010\u001e\u001a\u00020\u001fHÖ\u0001J!\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020\u00002\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020&HÇ\u0001R\u001c\u0010\u0007\u001a\u00020\b8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010R\u001c\u0010\u0006\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0011\u0010\u000e\u001a\u0004\b\u0012\u0010\u0013R\u001c\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0014\u0010\u000e\u001a\u0004\b\u0015\u0010\u0016¨\u0006)"}, d2 = {"Lcom/taptap/sdk/common/network/data/response/BaseResponse;", "", "seen1", "", "success", "", "now", FunctionRouter.KEY_DATA, "Lkotlinx/serialization/json/JsonElement;", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(IZILkotlinx/serialization/json/JsonElement;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "(ZILkotlinx/serialization/json/JsonElement;)V", "getData$annotations", "()V", "getData", "()Lkotlinx/serialization/json/JsonElement;", "getNow$annotations", "getNow", "()I", "getSuccess$annotations", "getSuccess", "()Z", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", "toString", "", "write$Self", "", SqConstants.OAUTH_SQ, "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "$serializer", "Companion", "tap-common_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
@Serializable
public final /* data */ class BaseResponse {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final JsonElement data;
    private final int now;
    private final boolean success;

    public static /* synthetic */ BaseResponse copy$default(BaseResponse baseResponse, boolean z, int i, JsonElement jsonElement, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            z = baseResponse.success;
        }
        if ((i2 & 2) != 0) {
            i = baseResponse.now;
        }
        if ((i2 & 4) != 0) {
            jsonElement = baseResponse.data;
        }
        return baseResponse.copy(z, i, jsonElement);
    }

    @SerialName(FunctionRouter.KEY_DATA)
    public static /* synthetic */ void getData$annotations() {
    }

    @SerialName("now")
    public static /* synthetic */ void getNow$annotations() {
    }

    @SerialName("success")
    public static /* synthetic */ void getSuccess$annotations() {
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final boolean getSuccess() {
        return this.success;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final int getNow() {
        return this.now;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final JsonElement getData() {
        return this.data;
    }

    public final BaseResponse copy(boolean success, int now, JsonElement data) {
        Intrinsics.checkNotNullParameter(data, "data");
        return new BaseResponse(success, now, data);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof BaseResponse)) {
            return false;
        }
        BaseResponse baseResponse = (BaseResponse) other;
        return this.success == baseResponse.success && this.now == baseResponse.now && Intrinsics.areEqual(this.data, baseResponse.data);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [int] */
    /* JADX WARN: Type inference failed for: r0v6 */
    /* JADX WARN: Type inference failed for: r0v7 */
    public int hashCode() {
        boolean z = this.success;
        ?? r0 = z;
        if (z) {
            r0 = 1;
        }
        return (((r0 * 31) + this.now) * 31) + this.data.hashCode();
    }

    public String toString() {
        return "BaseResponse(success=" + this.success + ", now=" + this.now + ", data=" + this.data + ')';
    }

    /* JADX INFO: compiled from: BaseResponse.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¨\u0006\u0006"}, d2 = {"Lcom/taptap/sdk/common/network/data/response/BaseResponse$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/taptap/sdk/common/network/data/response/BaseResponse;", "tap-common_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final KSerializer<BaseResponse> serializer() {
            return BaseResponse$$serializer.INSTANCE;
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ BaseResponse(int i, @SerialName("success") boolean z, @SerialName("now") int i2, @SerialName(FunctionRouter.KEY_DATA) JsonElement jsonElement, SerializationConstructorMarker serializationConstructorMarker) {
        if (7 != (i & 7)) {
            PluginExceptionsKt.throwMissingFieldException(i, 7, BaseResponse$$serializer.INSTANCE.getDescriptor());
        }
        this.success = z;
        this.now = i2;
        this.data = jsonElement;
    }

    public BaseResponse(boolean z, int i, JsonElement data) {
        Intrinsics.checkNotNullParameter(data, "data");
        this.success = z;
        this.now = i;
        this.data = data;
    }

    @JvmStatic
    public static final void write$Self(BaseResponse self, CompositeEncoder output, SerialDescriptor serialDesc) {
        Intrinsics.checkNotNullParameter(self, "self");
        Intrinsics.checkNotNullParameter(output, "output");
        Intrinsics.checkNotNullParameter(serialDesc, "serialDesc");
        output.encodeBooleanElement(serialDesc, 0, self.success);
        output.encodeIntElement(serialDesc, 1, self.now);
        output.encodeSerializableElement(serialDesc, 2, JsonElementSerializer.INSTANCE, self.data);
    }

    public final boolean getSuccess() {
        return this.success;
    }

    public final int getNow() {
        return this.now;
    }

    public final JsonElement getData() {
        return this.data;
    }
}
