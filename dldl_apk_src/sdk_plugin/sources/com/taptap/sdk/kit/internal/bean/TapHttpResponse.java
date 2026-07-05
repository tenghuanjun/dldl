package com.taptap.sdk.kit.internal.bean;

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
import kotlinx.serialization.internal.IntSerializer;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import kotlinx.serialization.internal.SerializationConstructorMarker;

/* JADX INFO: compiled from: TapHttpResponse.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0087\b\u0018\u0000 .*\u0004\b\u0000\u0010\u00012\u00020\u0002:\u0002-.B;\b\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\b\u0001\u0010\u0005\u001a\u00020\u0006\u0012\n\b\u0001\u0010\u0007\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0001\u0010\b\u001a\u0004\u0018\u00018\u0000\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0002\u0010\u000bB'\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00018\u0000¢\u0006\u0002\u0010\fJ\t\u0010\u0019\u001a\u00020\u0006HÆ\u0003J\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u0004HÆ\u0003¢\u0006\u0002\u0010\u0014J\u0010\u0010\u001b\u001a\u0004\u0018\u00018\u0000HÆ\u0003¢\u0006\u0002\u0010\u0010J6\u0010\u001c\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\b\b\u0002\u0010\u0005\u001a\u00020\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\b\u001a\u0004\u0018\u00018\u0000HÆ\u0001¢\u0006\u0002\u0010\u001dJ\u0013\u0010\u001e\u001a\u00020\u00062\b\u0010\u001f\u001a\u0004\u0018\u00010\u0002HÖ\u0003J\t\u0010 \u001a\u00020\u0004HÖ\u0001J\t\u0010!\u001a\u00020\"HÖ\u0001J;\u0010#\u001a\u00020$\"\u0004\b\u0001\u0010%2\f\u0010&\u001a\b\u0012\u0004\u0012\u0002H%0\u00002\u0006\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020*2\f\u0010+\u001a\b\u0012\u0004\u0012\u0002H%0,HÇ\u0001R \u0010\b\u001a\u0004\u0018\u00018\u00008\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010\u0011\u0012\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010R \u0010\u0007\u001a\u0004\u0018\u00010\u00048\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010\u0015\u0012\u0004\b\u0012\u0010\u000e\u001a\u0004\b\u0013\u0010\u0014R\u001c\u0010\u0005\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0016\u0010\u000e\u001a\u0004\b\u0017\u0010\u0018¨\u0006/"}, d2 = {"Lcom/taptap/sdk/kit/internal/bean/TapHttpResponse;", "T", "", "seen1", "", "success", "", "now", FunctionRouter.KEY_DATA, "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(IZLjava/lang/Integer;Ljava/lang/Object;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "(ZLjava/lang/Integer;Ljava/lang/Object;)V", "getData$annotations", "()V", "getData", "()Ljava/lang/Object;", "Ljava/lang/Object;", "getNow$annotations", "getNow", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getSuccess$annotations", "getSuccess", "()Z", "component1", "component2", "component3", "copy", "(ZLjava/lang/Integer;Ljava/lang/Object;)Lcom/taptap/sdk/kit/internal/bean/TapHttpResponse;", "equals", "other", "hashCode", "toString", "", "write$Self", "", "T0", SqConstants.OAUTH_SQ, "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "typeSerial0", "Lkotlinx/serialization/KSerializer;", "$serializer", "Companion", "tap-common_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
@Serializable
public final /* data */ class TapHttpResponse<T> {
    private static final SerialDescriptor $cachedDescriptor;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final T data;
    private final Integer now;
    private final boolean success;

    public TapHttpResponse() {
        this(false, (Integer) null, (Object) null, 7, (DefaultConstructorMarker) null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ TapHttpResponse copy$default(TapHttpResponse tapHttpResponse, boolean z, Integer num, Object obj, int i, Object obj2) {
        if ((i & 1) != 0) {
            z = tapHttpResponse.success;
        }
        if ((i & 2) != 0) {
            num = tapHttpResponse.now;
        }
        if ((i & 4) != 0) {
            obj = tapHttpResponse.data;
        }
        return tapHttpResponse.copy(z, num, obj);
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
    public final Integer getNow() {
        return this.now;
    }

    public final T component3() {
        return this.data;
    }

    public final TapHttpResponse<T> copy(boolean success, Integer now, T data) {
        return new TapHttpResponse<>(success, now, data);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TapHttpResponse)) {
            return false;
        }
        TapHttpResponse tapHttpResponse = (TapHttpResponse) other;
        return this.success == tapHttpResponse.success && Intrinsics.areEqual(this.now, tapHttpResponse.now) && Intrinsics.areEqual(this.data, tapHttpResponse.data);
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
        int i = r0 * 31;
        Integer num = this.now;
        int iHashCode = (i + (num == null ? 0 : num.hashCode())) * 31;
        T t = this.data;
        return iHashCode + (t != null ? t.hashCode() : 0);
    }

    public String toString() {
        return "TapHttpResponse(success=" + this.success + ", now=" + this.now + ", data=" + this.data + ')';
    }

    /* JADX INFO: compiled from: TapHttpResponse.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J)\u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00060\u00050\u0004\"\u0004\b\u0001\u0010\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u0002H\u00060\u0004HÆ\u0001¨\u0006\b"}, d2 = {"Lcom/taptap/sdk/kit/internal/bean/TapHttpResponse$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/taptap/sdk/kit/internal/bean/TapHttpResponse;", "T0", "typeSerial0", "tap-common_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final <T0> KSerializer<TapHttpResponse<T0>> serializer(KSerializer<T0> typeSerial0) {
            Intrinsics.checkNotNullParameter(typeSerial0, "typeSerial0");
            return new TapHttpResponse$$serializer(typeSerial0);
        }
    }

    static {
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.taptap.sdk.kit.internal.bean.TapHttpResponse", null, 3);
        pluginGeneratedSerialDescriptor.addElement("success", true);
        pluginGeneratedSerialDescriptor.addElement("now", true);
        pluginGeneratedSerialDescriptor.addElement(FunctionRouter.KEY_DATA, true);
        $cachedDescriptor = pluginGeneratedSerialDescriptor;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ TapHttpResponse(int i, @SerialName("success") boolean z, @SerialName("now") Integer num, @SerialName(FunctionRouter.KEY_DATA) Object obj, SerializationConstructorMarker serializationConstructorMarker) {
        if ((i & 0) != 0) {
            PluginExceptionsKt.throwMissingFieldException(i, 0, $cachedDescriptor);
        }
        if ((i & 1) == 0) {
            this.success = false;
        } else {
            this.success = z;
        }
        if ((i & 2) == 0) {
            this.now = null;
        } else {
            this.now = num;
        }
        if ((i & 4) == 0) {
            this.data = null;
        } else {
            this.data = obj;
        }
    }

    public TapHttpResponse(boolean z, Integer num, T t) {
        this.success = z;
        this.now = num;
        this.data = t;
    }

    @JvmStatic
    public static final <T0> void write$Self(TapHttpResponse<T0> self, CompositeEncoder output, SerialDescriptor serialDesc, KSerializer<T0> typeSerial0) {
        Intrinsics.checkNotNullParameter(self, "self");
        Intrinsics.checkNotNullParameter(output, "output");
        Intrinsics.checkNotNullParameter(serialDesc, "serialDesc");
        Intrinsics.checkNotNullParameter(typeSerial0, "typeSerial0");
        if (output.shouldEncodeElementDefault(serialDesc, 0) || ((TapHttpResponse) self).success) {
            output.encodeBooleanElement(serialDesc, 0, ((TapHttpResponse) self).success);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 1) || ((TapHttpResponse) self).now != null) {
            output.encodeNullableSerializableElement(serialDesc, 1, IntSerializer.INSTANCE, ((TapHttpResponse) self).now);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 2) || ((TapHttpResponse) self).data != null) {
            output.encodeNullableSerializableElement(serialDesc, 2, typeSerial0, ((TapHttpResponse) self).data);
        }
    }

    public /* synthetic */ TapHttpResponse(boolean z, Integer num, Object obj, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? false : z, (i & 2) != 0 ? null : num, (i & 4) != 0 ? null : obj);
    }

    public final boolean getSuccess() {
        return this.success;
    }

    public final Integer getNow() {
        return this.now;
    }

    public final T getData() {
        return this.data;
    }
}
