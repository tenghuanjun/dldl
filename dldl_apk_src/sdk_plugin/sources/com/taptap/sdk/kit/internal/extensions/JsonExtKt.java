package com.taptap.sdk.kit.internal.extensions;

import com.taptap.sdk.kit.internal.TapLogger;
import com.taptap.sdk.kit.internal.json.TapJson;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KType;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerializersKt;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.modules.SerializersModule;

/* JADX INFO: compiled from: JsonExt.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\u001a\u001a\u0010\u0000\u001a\u00020\u0001\"\u0006\b\u0000\u0010\u0002\u0018\u0001*\u0002H\u0002H\u0086\b¢\u0006\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"toJson", "", "T", "(Ljava/lang/Object;)Ljava/lang/String;", "tap-common_release"}, k = 2, mv = {1, 5, 1}, xi = 48)
public final class JsonExtKt {
    public static final /* synthetic */ <T> String toJson(T t) {
        String strEncodeToString = null;
        try {
            Json json = TapJson.INSTANCE.getJson();
            SerializersModule serializersModule = json.getSerializersModule();
            Intrinsics.reifiedOperationMarker(6, "T");
            KSerializer<Object> kSerializerSerializer = SerializersKt.serializer(serializersModule, (KType) null);
            Intrinsics.checkNotNull(kSerializerSerializer, "null cannot be cast to non-null type kotlinx.serialization.KSerializer<T of kotlinx.serialization.internal.Platform_commonKt.cast>");
            strEncodeToString = json.encodeToString(kSerializerSerializer, t);
        } catch (Exception e) {
            TapLogger.loge$default(TapJson.TAG, null, e, 2, null);
        }
        return strEncodeToString == null ? "json convert error" : strEncodeToString;
    }
}
