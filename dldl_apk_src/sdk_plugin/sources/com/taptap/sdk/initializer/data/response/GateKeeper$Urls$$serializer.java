package com.taptap.sdk.initializer.data.response;

import com.taptap.sdk.initializer.data.response.GateKeeper;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.UnknownFieldException;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeDecoder;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
import kotlinx.serialization.internal.GeneratedSerializer;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import kotlinx.serialization.internal.SerializationConstructorMarker;

/* JADX INFO: compiled from: GateKeeper.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0018\u0010\b\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\n0\tHÖ\u0001¢\u0006\u0002\u0010\u000bJ\u0011\u0010\f\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u000eHÖ\u0001J\u0019\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0002HÖ\u0001R\u0014\u0010\u0004\u001a\u00020\u00058VXÖ\u0005¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0014"}, d2 = {"com/taptap/sdk/initializer/data/response/GateKeeper.Urls.$serializer", "Lkotlinx/serialization/internal/GeneratedSerializer;", "Lcom/taptap/sdk/initializer/data/response/GateKeeper$Urls;", "()V", "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/descriptors/SerialDescriptor;", "childSerializers", "", "Lkotlinx/serialization/KSerializer;", "()[Lkotlinx/serialization/KSerializer;", "deserialize", "decoder", "Lkotlinx/serialization/encoding/Decoder;", "serialize", "", "encoder", "Lkotlinx/serialization/encoding/Encoder;", "value", "tap-initializer_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
@Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
public final class GateKeeper$Urls$$serializer implements GeneratedSerializer<GateKeeper.Urls> {
    public static final GateKeeper$Urls$$serializer INSTANCE;
    public static final /* synthetic */ SerialDescriptor descriptor;

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    static {
        GateKeeper$Urls$$serializer gateKeeper$Urls$$serializer = new GateKeeper$Urls$$serializer();
        INSTANCE = gateKeeper$Urls$$serializer;
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.taptap.sdk.initializer.data.response.GateKeeper.Urls", gateKeeper$Urls$$serializer, 6);
        pluginGeneratedSerialDescriptor.addElement("achievement_my_list_url", true);
        pluginGeneratedSerialDescriptor.addElement("rep_share_url", true);
        pluginGeneratedSerialDescriptor.addElement("add_review_url", true);
        pluginGeneratedSerialDescriptor.addElement("relation_url", true);
        pluginGeneratedSerialDescriptor.addElement("relation_add_friend_url", true);
        pluginGeneratedSerialDescriptor.addElement("relation_notifications_url", true);
        descriptor = pluginGeneratedSerialDescriptor;
    }

    private GateKeeper$Urls$$serializer() {
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public KSerializer<?>[] childSerializers() {
        return new KSerializer[]{GateKeeper$Urls$Url$$serializer.INSTANCE, GateKeeper$Urls$Url$$serializer.INSTANCE, GateKeeper$Urls$Url$$serializer.INSTANCE, GateKeeper$Urls$Url$$serializer.INSTANCE, GateKeeper$Urls$Url$$serializer.INSTANCE, GateKeeper$Urls$Url$$serializer.INSTANCE};
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    public GateKeeper.Urls deserialize(Decoder decoder) {
        Object objDecodeSerializableElement;
        Object objDecodeSerializableElement2;
        Object objDecodeSerializableElement3;
        Object objDecodeSerializableElement4;
        Object objDecodeSerializableElement5;
        int i;
        Object objDecodeSerializableElement6;
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        SerialDescriptor descriptor2 = getDescriptor();
        CompositeDecoder compositeDecoderBeginStructure = decoder.beginStructure(descriptor2);
        int i2 = 5;
        Object objDecodeSerializableElement7 = null;
        if (compositeDecoderBeginStructure.decodeSequentially()) {
            objDecodeSerializableElement6 = compositeDecoderBeginStructure.decodeSerializableElement(descriptor2, 0, GateKeeper$Urls$Url$$serializer.INSTANCE, null);
            objDecodeSerializableElement = compositeDecoderBeginStructure.decodeSerializableElement(descriptor2, 1, GateKeeper$Urls$Url$$serializer.INSTANCE, null);
            objDecodeSerializableElement2 = compositeDecoderBeginStructure.decodeSerializableElement(descriptor2, 2, GateKeeper$Urls$Url$$serializer.INSTANCE, null);
            objDecodeSerializableElement3 = compositeDecoderBeginStructure.decodeSerializableElement(descriptor2, 3, GateKeeper$Urls$Url$$serializer.INSTANCE, null);
            objDecodeSerializableElement4 = compositeDecoderBeginStructure.decodeSerializableElement(descriptor2, 4, GateKeeper$Urls$Url$$serializer.INSTANCE, null);
            objDecodeSerializableElement5 = compositeDecoderBeginStructure.decodeSerializableElement(descriptor2, 5, GateKeeper$Urls$Url$$serializer.INSTANCE, null);
            i = 63;
        } else {
            Object objDecodeSerializableElement8 = null;
            Object objDecodeSerializableElement9 = null;
            Object objDecodeSerializableElement10 = null;
            Object objDecodeSerializableElement11 = null;
            Object objDecodeSerializableElement12 = null;
            int i3 = 0;
            boolean z = true;
            while (z) {
                int iDecodeElementIndex = compositeDecoderBeginStructure.decodeElementIndex(descriptor2);
                switch (iDecodeElementIndex) {
                    case -1:
                        i2 = 5;
                        z = false;
                        continue;
                    case 0:
                        objDecodeSerializableElement7 = compositeDecoderBeginStructure.decodeSerializableElement(descriptor2, 0, GateKeeper$Urls$Url$$serializer.INSTANCE, objDecodeSerializableElement7);
                        i3 |= 1;
                        i2 = 5;
                        break;
                    case 1:
                        objDecodeSerializableElement8 = compositeDecoderBeginStructure.decodeSerializableElement(descriptor2, 1, GateKeeper$Urls$Url$$serializer.INSTANCE, objDecodeSerializableElement8);
                        i3 |= 2;
                        break;
                    case 2:
                        objDecodeSerializableElement9 = compositeDecoderBeginStructure.decodeSerializableElement(descriptor2, 2, GateKeeper$Urls$Url$$serializer.INSTANCE, objDecodeSerializableElement9);
                        i3 |= 4;
                        break;
                    case 3:
                        objDecodeSerializableElement10 = compositeDecoderBeginStructure.decodeSerializableElement(descriptor2, 3, GateKeeper$Urls$Url$$serializer.INSTANCE, objDecodeSerializableElement10);
                        i3 |= 8;
                        break;
                    case 4:
                        objDecodeSerializableElement11 = compositeDecoderBeginStructure.decodeSerializableElement(descriptor2, 4, GateKeeper$Urls$Url$$serializer.INSTANCE, objDecodeSerializableElement11);
                        i3 |= 16;
                        break;
                    case 5:
                        objDecodeSerializableElement12 = compositeDecoderBeginStructure.decodeSerializableElement(descriptor2, i2, GateKeeper$Urls$Url$$serializer.INSTANCE, objDecodeSerializableElement12);
                        i3 |= 32;
                        break;
                    default:
                        throw new UnknownFieldException(iDecodeElementIndex);
                }
            }
            objDecodeSerializableElement = objDecodeSerializableElement8;
            objDecodeSerializableElement2 = objDecodeSerializableElement9;
            objDecodeSerializableElement3 = objDecodeSerializableElement10;
            objDecodeSerializableElement4 = objDecodeSerializableElement11;
            objDecodeSerializableElement5 = objDecodeSerializableElement12;
            Object obj = objDecodeSerializableElement7;
            i = i3;
            objDecodeSerializableElement6 = obj;
        }
        compositeDecoderBeginStructure.endStructure(descriptor2);
        return new GateKeeper.Urls(i, (GateKeeper.Urls.Url) objDecodeSerializableElement6, (GateKeeper.Urls.Url) objDecodeSerializableElement, (GateKeeper.Urls.Url) objDecodeSerializableElement2, (GateKeeper.Urls.Url) objDecodeSerializableElement3, (GateKeeper.Urls.Url) objDecodeSerializableElement4, (GateKeeper.Urls.Url) objDecodeSerializableElement5, (SerializationConstructorMarker) null);
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public void serialize(Encoder encoder, GateKeeper.Urls value) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(value, "value");
        SerialDescriptor descriptor2 = getDescriptor();
        CompositeEncoder compositeEncoderBeginStructure = encoder.beginStructure(descriptor2);
        GateKeeper.Urls.write$Self(value, compositeEncoderBeginStructure, descriptor2);
        compositeEncoderBeginStructure.endStructure(descriptor2);
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public KSerializer<?>[] typeParametersSerializers() {
        return GeneratedSerializer.DefaultImpls.typeParametersSerializers(this);
    }
}
