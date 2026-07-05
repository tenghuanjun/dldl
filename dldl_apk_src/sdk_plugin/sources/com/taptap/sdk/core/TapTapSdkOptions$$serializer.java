package com.taptap.sdk.core;

import com.sqwan.common.net.risk.RiskWebActivity;
import com.taptap.sdk.core.serialize.JSONObjectSerializer;
import com.taptap.sdk.db.constant.Common;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.UnknownFieldException;
import kotlinx.serialization.builtins.BuiltinSerializersKt;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeDecoder;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
import kotlinx.serialization.internal.BooleanSerializer;
import kotlinx.serialization.internal.GeneratedSerializer;
import kotlinx.serialization.internal.IntSerializer;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;
import org.json.JSONObject;

/* JADX INFO: compiled from: TapTapSdkOptions.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0018\u0010\b\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\n0\tHÖ\u0001¢\u0006\u0002\u0010\u000bJ\u0011\u0010\f\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u000eHÖ\u0001J\u0019\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0002HÖ\u0001R\u0014\u0010\u0004\u001a\u00020\u00058VXÖ\u0005¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0014"}, d2 = {"com/taptap/sdk/core/TapTapSdkOptions.$serializer", "Lkotlinx/serialization/internal/GeneratedSerializer;", "Lcom/taptap/sdk/core/TapTapSdkOptions;", "()V", "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/descriptors/SerialDescriptor;", "childSerializers", "", "Lkotlinx/serialization/KSerializer;", "()[Lkotlinx/serialization/KSerializer;", "deserialize", "decoder", "Lkotlinx/serialization/encoding/Decoder;", "serialize", "", "encoder", "Lkotlinx/serialization/encoding/Encoder;", "value", "tap-core_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
@Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
public final class TapTapSdkOptions$$serializer implements GeneratedSerializer<TapTapSdkOptions> {
    public static final TapTapSdkOptions$$serializer INSTANCE;
    public static final /* synthetic */ SerialDescriptor descriptor;

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    static {
        TapTapSdkOptions$$serializer tapTapSdkOptions$$serializer = new TapTapSdkOptions$$serializer();
        INSTANCE = tapTapSdkOptions$$serializer;
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.taptap.sdk.core.TapTapSdkOptions", tapTapSdkOptions$$serializer, 14);
        pluginGeneratedSerialDescriptor.addElement("clientId", false);
        pluginGeneratedSerialDescriptor.addElement("clientToken", false);
        pluginGeneratedSerialDescriptor.addElement("region", false);
        pluginGeneratedSerialDescriptor.addElement("channel", true);
        pluginGeneratedSerialDescriptor.addElement("gameVersion", true);
        pluginGeneratedSerialDescriptor.addElement("autoIAPEventEnabled", true);
        pluginGeneratedSerialDescriptor.addElement("overrideBuiltInParameters", true);
        pluginGeneratedSerialDescriptor.addElement(Common.Predefined.PROPERTIES, true);
        pluginGeneratedSerialDescriptor.addElement("oaidCert", true);
        pluginGeneratedSerialDescriptor.addElement("enableLog", true);
        pluginGeneratedSerialDescriptor.addElement("preferredLanguage", true);
        pluginGeneratedSerialDescriptor.addElement("disableReflectionOAID", true);
        pluginGeneratedSerialDescriptor.addElement("disableAutoLogDeviceLogin", true);
        pluginGeneratedSerialDescriptor.addElement(RiskWebActivity.INTENT_KEY_IN_SCREEN_ORIENTATION, true);
        descriptor = pluginGeneratedSerialDescriptor;
    }

    private TapTapSdkOptions$$serializer() {
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public KSerializer<?>[] childSerializers() {
        return new KSerializer[]{StringSerializer.INSTANCE, StringSerializer.INSTANCE, IntSerializer.INSTANCE, BuiltinSerializersKt.getNullable(StringSerializer.INSTANCE), BuiltinSerializersKt.getNullable(StringSerializer.INSTANCE), BooleanSerializer.INSTANCE, BooleanSerializer.INSTANCE, BuiltinSerializersKt.getNullable(JSONObjectSerializer.INSTANCE), BuiltinSerializersKt.getNullable(StringSerializer.INSTANCE), BooleanSerializer.INSTANCE, TapTapLanguage.INSTANCE.serializer(), BooleanSerializer.INSTANCE, BooleanSerializer.INSTANCE, IntSerializer.INSTANCE};
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    public TapTapSdkOptions deserialize(Decoder decoder) {
        Object objDecodeNullableSerializableElement;
        String str;
        Object obj;
        Object objDecodeSerializableElement;
        Object objDecodeNullableSerializableElement2;
        Object obj2;
        String str2;
        boolean z;
        boolean z2;
        boolean z3;
        int iDecodeIntElement;
        int iDecodeIntElement2;
        int i;
        boolean zDecodeBooleanElement;
        boolean zDecodeBooleanElement2;
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        SerialDescriptor descriptor2 = getDescriptor();
        CompositeDecoder compositeDecoderBeginStructure = decoder.beginStructure(descriptor2);
        int i2 = 11;
        String strDecodeStringElement = null;
        if (compositeDecoderBeginStructure.decodeSequentially()) {
            String strDecodeStringElement2 = compositeDecoderBeginStructure.decodeStringElement(descriptor2, 0);
            String strDecodeStringElement3 = compositeDecoderBeginStructure.decodeStringElement(descriptor2, 1);
            iDecodeIntElement = compositeDecoderBeginStructure.decodeIntElement(descriptor2, 2);
            Object objDecodeNullableSerializableElement3 = compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 3, StringSerializer.INSTANCE, null);
            Object objDecodeNullableSerializableElement4 = compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 4, StringSerializer.INSTANCE, null);
            boolean zDecodeBooleanElement3 = compositeDecoderBeginStructure.decodeBooleanElement(descriptor2, 5);
            boolean zDecodeBooleanElement4 = compositeDecoderBeginStructure.decodeBooleanElement(descriptor2, 6);
            objDecodeNullableSerializableElement2 = compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 7, JSONObjectSerializer.INSTANCE, null);
            Object objDecodeNullableSerializableElement5 = compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 8, StringSerializer.INSTANCE, null);
            boolean zDecodeBooleanElement5 = compositeDecoderBeginStructure.decodeBooleanElement(descriptor2, 9);
            objDecodeSerializableElement = compositeDecoderBeginStructure.decodeSerializableElement(descriptor2, 10, TapTapLanguage.INSTANCE.serializer(), null);
            zDecodeBooleanElement2 = compositeDecoderBeginStructure.decodeBooleanElement(descriptor2, 11);
            zDecodeBooleanElement = compositeDecoderBeginStructure.decodeBooleanElement(descriptor2, 12);
            z = zDecodeBooleanElement5;
            z2 = zDecodeBooleanElement4;
            z3 = zDecodeBooleanElement3;
            obj2 = objDecodeNullableSerializableElement4;
            iDecodeIntElement2 = compositeDecoderBeginStructure.decodeIntElement(descriptor2, 13);
            str2 = strDecodeStringElement3;
            objDecodeNullableSerializableElement = objDecodeNullableSerializableElement3;
            i = 16383;
            str = strDecodeStringElement2;
            obj = objDecodeNullableSerializableElement5;
        } else {
            int i3 = 13;
            objDecodeNullableSerializableElement = null;
            Object objDecodeNullableSerializableElement6 = null;
            Object objDecodeSerializableElement2 = null;
            Object objDecodeNullableSerializableElement7 = null;
            Object objDecodeNullableSerializableElement8 = null;
            String strDecodeStringElement4 = null;
            int i4 = 0;
            boolean zDecodeBooleanElement6 = false;
            boolean zDecodeBooleanElement7 = false;
            boolean zDecodeBooleanElement8 = false;
            boolean zDecodeBooleanElement9 = false;
            boolean zDecodeBooleanElement10 = false;
            int iDecodeIntElement3 = 0;
            int iDecodeIntElement4 = 0;
            boolean z4 = true;
            while (z4) {
                int iDecodeElementIndex = compositeDecoderBeginStructure.decodeElementIndex(descriptor2);
                switch (iDecodeElementIndex) {
                    case -1:
                        i3 = 13;
                        i2 = 11;
                        z4 = false;
                        break;
                    case 0:
                        i4 |= 1;
                        strDecodeStringElement = compositeDecoderBeginStructure.decodeStringElement(descriptor2, 0);
                        i3 = 13;
                        i2 = 11;
                        break;
                    case 1:
                        strDecodeStringElement4 = compositeDecoderBeginStructure.decodeStringElement(descriptor2, 1);
                        i4 |= 2;
                        i3 = 13;
                        i2 = 11;
                        break;
                    case 2:
                        iDecodeIntElement3 = compositeDecoderBeginStructure.decodeIntElement(descriptor2, 2);
                        i4 |= 4;
                        i3 = 13;
                        i2 = 11;
                        break;
                    case 3:
                        objDecodeNullableSerializableElement = compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 3, StringSerializer.INSTANCE, objDecodeNullableSerializableElement);
                        i4 |= 8;
                        i3 = 13;
                        i2 = 11;
                        break;
                    case 4:
                        objDecodeNullableSerializableElement8 = compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 4, StringSerializer.INSTANCE, objDecodeNullableSerializableElement8);
                        i4 |= 16;
                        i3 = 13;
                        i2 = 11;
                        break;
                    case 5:
                        zDecodeBooleanElement10 = compositeDecoderBeginStructure.decodeBooleanElement(descriptor2, 5);
                        i4 |= 32;
                        i3 = 13;
                        break;
                    case 6:
                        zDecodeBooleanElement9 = compositeDecoderBeginStructure.decodeBooleanElement(descriptor2, 6);
                        i4 |= 64;
                        i3 = 13;
                        break;
                    case 7:
                        objDecodeNullableSerializableElement7 = compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 7, JSONObjectSerializer.INSTANCE, objDecodeNullableSerializableElement7);
                        i4 |= 128;
                        i3 = 13;
                        break;
                    case 8:
                        objDecodeNullableSerializableElement6 = compositeDecoderBeginStructure.decodeNullableSerializableElement(descriptor2, 8, StringSerializer.INSTANCE, objDecodeNullableSerializableElement6);
                        i4 |= 256;
                        i3 = 13;
                        break;
                    case 9:
                        zDecodeBooleanElement8 = compositeDecoderBeginStructure.decodeBooleanElement(descriptor2, 9);
                        i4 |= 512;
                        i3 = 13;
                        break;
                    case 10:
                        objDecodeSerializableElement2 = compositeDecoderBeginStructure.decodeSerializableElement(descriptor2, 10, TapTapLanguage.INSTANCE.serializer(), objDecodeSerializableElement2);
                        i4 |= 1024;
                        i3 = 13;
                        break;
                    case 11:
                        zDecodeBooleanElement6 = compositeDecoderBeginStructure.decodeBooleanElement(descriptor2, i2);
                        i4 |= 2048;
                        break;
                    case 12:
                        zDecodeBooleanElement7 = compositeDecoderBeginStructure.decodeBooleanElement(descriptor2, 12);
                        i4 |= 4096;
                        break;
                    case 13:
                        iDecodeIntElement4 = compositeDecoderBeginStructure.decodeIntElement(descriptor2, i3);
                        i4 |= 8192;
                        break;
                    default:
                        throw new UnknownFieldException(iDecodeElementIndex);
                }
            }
            str = strDecodeStringElement;
            obj = objDecodeNullableSerializableElement6;
            objDecodeSerializableElement = objDecodeSerializableElement2;
            objDecodeNullableSerializableElement2 = objDecodeNullableSerializableElement7;
            obj2 = objDecodeNullableSerializableElement8;
            str2 = strDecodeStringElement4;
            z = zDecodeBooleanElement8;
            z2 = zDecodeBooleanElement9;
            z3 = zDecodeBooleanElement10;
            iDecodeIntElement = iDecodeIntElement3;
            iDecodeIntElement2 = iDecodeIntElement4;
            i = i4;
            zDecodeBooleanElement = zDecodeBooleanElement7;
            zDecodeBooleanElement2 = zDecodeBooleanElement6;
        }
        compositeDecoderBeginStructure.endStructure(descriptor2);
        return new TapTapSdkOptions(i, str, str2, iDecodeIntElement, (String) objDecodeNullableSerializableElement, (String) obj2, z3, z2, (JSONObject) objDecodeNullableSerializableElement2, (String) obj, z, (TapTapLanguage) objDecodeSerializableElement, zDecodeBooleanElement2, zDecodeBooleanElement, iDecodeIntElement2, (SerializationConstructorMarker) null);
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public void serialize(Encoder encoder, TapTapSdkOptions value) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(value, "value");
        SerialDescriptor descriptor2 = getDescriptor();
        CompositeEncoder compositeEncoderBeginStructure = encoder.beginStructure(descriptor2);
        TapTapSdkOptions.write$Self(value, compositeEncoderBeginStructure, descriptor2);
        compositeEncoderBeginStructure.endStructure(descriptor2);
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public KSerializer<?>[] typeParametersSerializers() {
        return GeneratedSerializer.DefaultImpls.typeParametersSerializers(this);
    }
}
