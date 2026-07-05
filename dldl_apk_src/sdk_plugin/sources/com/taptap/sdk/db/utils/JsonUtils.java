package com.taptap.sdk.db.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.json.JsonArray;
import kotlinx.serialization.json.JsonElement;
import kotlinx.serialization.json.JsonElementKt;
import kotlinx.serialization.json.JsonNull;
import kotlinx.serialization.json.JsonObject;
import kotlinx.serialization.json.JsonPrimitive;

/* JADX INFO: compiled from: JsonUtils.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u0004\u0018\u00010\u0001*\u00020\u0004H\u0002J\u0018\u0010\u0005\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0006*\u00020\u0004J\u000e\u0010\b\u001a\u0004\u0018\u00010\u0001*\u00020\u0004H\u0002¨\u0006\t"}, d2 = {"Lcom/taptap/sdk/db/utils/JsonUtils;", "", "()V", "castAs", "Lkotlinx/serialization/json/JsonElement;", "toMap", "", "", "toSampleValue", "tap-db_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class JsonUtils {
    public static final JsonUtils INSTANCE = new JsonUtils();

    private JsonUtils() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final Map<String, Object> toMap(JsonElement jsonElement) {
        Intrinsics.checkNotNullParameter(jsonElement, "<this>");
        if (!(jsonElement instanceof JsonObject)) {
            throw new IllegalArgumentException("Unsupported JsonElement: " + jsonElement);
        }
        Map map = (Map) jsonElement;
        LinkedHashMap linkedHashMap = new LinkedHashMap(MapsKt.mapCapacity(map.size()));
        for (Map.Entry entry : map.entrySet()) {
            linkedHashMap.put(entry.getKey(), INSTANCE.castAs((JsonElement) entry.getValue()));
        }
        return linkedHashMap;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final Object castAs(JsonElement jsonElement) {
        if (jsonElement instanceof JsonPrimitive) {
            JsonPrimitive jsonPrimitive = (JsonPrimitive) jsonElement;
            return jsonPrimitive.getIsString() ? jsonPrimitive.getContent() : JsonElementKt.getBooleanOrNull(jsonPrimitive) != null ? Boolean.valueOf(JsonElementKt.getBoolean(jsonPrimitive)) : JsonElementKt.getLongOrNull(jsonPrimitive) != null ? Long.valueOf(JsonElementKt.getLong(jsonPrimitive)) : JsonElementKt.getDoubleOrNull(jsonPrimitive) != null ? Double.valueOf(JsonElementKt.getDouble(jsonPrimitive)) : JsonElementKt.getIntOrNull(jsonPrimitive) != null ? Integer.valueOf(JsonElementKt.getInt(jsonPrimitive)) : jsonPrimitive.getContent();
        }
        if (jsonElement instanceof JsonArray) {
            Iterable iterable = (Iterable) jsonElement;
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
            Iterator it = iterable.iterator();
            while (it.hasNext()) {
                arrayList.add(INSTANCE.toSampleValue((JsonElement) it.next()));
            }
            return arrayList;
        }
        if (jsonElement instanceof JsonObject) {
            return toMap(jsonElement);
        }
        if (jsonElement instanceof JsonNull) {
            return null;
        }
        throw new NoWhenBranchMatchedException();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final Object toSampleValue(JsonElement jsonElement) {
        if (jsonElement instanceof JsonPrimitive) {
            JsonPrimitive jsonPrimitive = (JsonPrimitive) jsonElement;
            return jsonPrimitive.getIsString() ? jsonPrimitive.getContent() : JsonElementKt.getBooleanOrNull(jsonPrimitive) != null ? Boolean.valueOf(JsonElementKt.getBoolean(jsonPrimitive)) : JsonElementKt.getLongOrNull(jsonPrimitive) != null ? Long.valueOf(JsonElementKt.getLong(jsonPrimitive)) : JsonElementKt.getDoubleOrNull(jsonPrimitive) != null ? Double.valueOf(JsonElementKt.getDouble(jsonPrimitive)) : JsonElementKt.getIntOrNull(jsonPrimitive) != null ? Integer.valueOf(JsonElementKt.getInt(jsonPrimitive)) : jsonPrimitive.getContent();
        }
        if (!(jsonElement instanceof JsonArray)) {
            if (jsonElement instanceof JsonObject) {
                return toMap(jsonElement);
            }
            if (jsonElement instanceof JsonNull) {
                return null;
            }
            throw new NoWhenBranchMatchedException();
        }
        Iterable iterable = (Iterable) jsonElement;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        Iterator it = iterable.iterator();
        while (it.hasNext()) {
            arrayList.add(INSTANCE.toSampleValue((JsonElement) it.next()));
        }
        return arrayList;
    }
}
