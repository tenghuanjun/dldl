package com.taptap.sdk.kit.internal.json;

import com.sqwan.common.route.FunctionRouter;
import com.taptap.sdk.kit.internal.TapLogger;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KType;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerializersKt;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonBuilder;
import kotlinx.serialization.json.JsonElement;
import kotlinx.serialization.json.JsonKt;
import kotlinx.serialization.modules.SerializersModule;
import org.json.JSONObject;

/* JADX INFO: compiled from: TapJson.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\"\u0010\n\u001a\u0004\u0018\u0001H\u000b\"\u0006\b\u0000\u0010\u000b\u0018\u00012\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0086\b¢\u0006\u0002\u0010\u000eJ\"\u0010\u000f\u001a\u0004\u0018\u0001H\u000b\"\u0006\b\u0000\u0010\u000b\u0018\u00012\b\u0010\u0010\u001a\u0004\u0018\u00010\u0004H\u0086\b¢\u0006\u0002\u0010\u0011J \u0010\u0012\u001a\u0004\u0018\u00010\u0013\"\u0006\b\u0000\u0010\u000b\u0018\u00012\u0006\u0010\u0014\u001a\u0002H\u000bH\u0086\b¢\u0006\u0002\u0010\u0015J \u0010\u0016\u001a\u0004\u0018\u00010\r\"\u0006\b\u0000\u0010\u000b\u0018\u00012\u0006\u0010\u0014\u001a\u0002H\u000bH\u0086\b¢\u0006\u0002\u0010\u0017J \u0010\u0018\u001a\u0004\u0018\u00010\u0004\"\u0006\b\u0000\u0010\u000b\u0018\u00012\u0006\u0010\u0014\u001a\u0002H\u000bH\u0086\b¢\u0006\u0002\u0010\u0019R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u001c\u0010\u0005\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0007\u0010\u0002\u001a\u0004\b\b\u0010\t¨\u0006\u001a"}, d2 = {"Lcom/taptap/sdk/kit/internal/json/TapJson;", "", "()V", "TAG", "", "json", "Lkotlinx/serialization/json/Json;", "getJson$annotations", "getJson", "()Lkotlinx/serialization/json/Json;", "decodeFromJsonElement", "T", FunctionRouter.KEY_DATA, "Lkotlinx/serialization/json/JsonElement;", "(Lkotlinx/serialization/json/JsonElement;)Ljava/lang/Object;", "decodeFromString", "jsonString", "(Ljava/lang/String;)Ljava/lang/Object;", "encodeToJSONObject", "Lorg/json/JSONObject;", "obj", "(Ljava/lang/Object;)Lorg/json/JSONObject;", "encodeToJsonElement", "(Ljava/lang/Object;)Lkotlinx/serialization/json/JsonElement;", "encodeToString", "(Ljava/lang/Object;)Ljava/lang/String;", "tap-common_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class TapJson {
    public static final String TAG = "TapJson";
    public static final TapJson INSTANCE = new TapJson();
    private static final Json json = JsonKt.Json$default(null, new Function1<JsonBuilder, Unit>() { // from class: com.taptap.sdk.kit.internal.json.TapJson$json$1
        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(JsonBuilder jsonBuilder) {
            invoke2(jsonBuilder);
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(JsonBuilder Json) {
            Intrinsics.checkNotNullParameter(Json, "$this$Json");
            Json.setIgnoreUnknownKeys(true);
            Json.setEncodeDefaults(true);
        }
    }, 1, null);

    @Deprecated(message = "Do not use directly")
    public static /* synthetic */ void getJson$annotations() {
    }

    private TapJson() {
    }

    public final Json getJson() {
        return json;
    }

    public final /* synthetic */ <T> T decodeFromString(String jsonString) {
        String str = jsonString;
        if (str == null || str.length() == 0) {
            return null;
        }
        try {
            Json json2 = getJson();
            SerializersModule serializersModule = json2.getSerializersModule();
            Intrinsics.reifiedOperationMarker(6, "T");
            KSerializer<Object> kSerializerSerializer = SerializersKt.serializer(serializersModule, (KType) null);
            Intrinsics.checkNotNull(kSerializerSerializer, "null cannot be cast to non-null type kotlinx.serialization.KSerializer<T of kotlinx.serialization.internal.Platform_commonKt.cast>");
            return (T) json2.decodeFromString(kSerializerSerializer, jsonString);
        } catch (Exception e) {
            TapLogger.loge$default(TAG, null, e, 2, null);
            return null;
        }
    }

    public final /* synthetic */ <T> String encodeToString(T obj) {
        try {
            Json json2 = getJson();
            SerializersModule serializersModule = json2.getSerializersModule();
            Intrinsics.reifiedOperationMarker(6, "T");
            KSerializer<Object> kSerializerSerializer = SerializersKt.serializer(serializersModule, (KType) null);
            Intrinsics.checkNotNull(kSerializerSerializer, "null cannot be cast to non-null type kotlinx.serialization.KSerializer<T of kotlinx.serialization.internal.Platform_commonKt.cast>");
            return json2.encodeToString(kSerializerSerializer, obj);
        } catch (Exception e) {
            TapLogger.loge$default(TAG, null, e, 2, null);
            return null;
        }
    }

    public final /* synthetic */ <T> T decodeFromJsonElement(JsonElement data) {
        if (data == null) {
            return null;
        }
        try {
            Json json2 = getJson();
            SerializersModule serializersModule = json2.getSerializersModule();
            Intrinsics.reifiedOperationMarker(6, "T");
            KSerializer<Object> kSerializerSerializer = SerializersKt.serializer(serializersModule, (KType) null);
            Intrinsics.checkNotNull(kSerializerSerializer, "null cannot be cast to non-null type kotlinx.serialization.KSerializer<T of kotlinx.serialization.internal.Platform_commonKt.cast>");
            return (T) json2.decodeFromJsonElement(kSerializerSerializer, data);
        } catch (Exception e) {
            TapLogger.loge$default(TAG, null, e, 2, null);
            return null;
        }
    }

    public final /* synthetic */ <T> JSONObject encodeToJSONObject(T obj) {
        try {
            Json json2 = getJson();
            SerializersModule serializersModule = json2.getSerializersModule();
            Intrinsics.reifiedOperationMarker(6, "T");
            KSerializer<Object> kSerializerSerializer = SerializersKt.serializer(serializersModule, (KType) null);
            Intrinsics.checkNotNull(kSerializerSerializer, "null cannot be cast to non-null type kotlinx.serialization.KSerializer<T of kotlinx.serialization.internal.Platform_commonKt.cast>");
            return new JSONObject(json2.encodeToString(kSerializerSerializer, obj));
        } catch (Exception e) {
            TapLogger.loge$default(TAG, null, e, 2, null);
            return null;
        }
    }

    public final /* synthetic */ <T> JsonElement encodeToJsonElement(T obj) {
        try {
            Json json2 = getJson();
            SerializersModule serializersModule = json2.getSerializersModule();
            Intrinsics.reifiedOperationMarker(6, "T");
            KSerializer<Object> kSerializerSerializer = SerializersKt.serializer(serializersModule, (KType) null);
            Intrinsics.checkNotNull(kSerializerSerializer, "null cannot be cast to non-null type kotlinx.serialization.KSerializer<T of kotlinx.serialization.internal.Platform_commonKt.cast>");
            return json2.encodeToJsonElement(kSerializerSerializer, obj);
        } catch (Exception e) {
            TapLogger.loge$default(TAG, null, e, 2, null);
            return null;
        }
    }
}
