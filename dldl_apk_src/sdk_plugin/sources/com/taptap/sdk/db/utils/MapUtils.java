package com.taptap.sdk.db.utils;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlinx.serialization.json.JsonArrayBuilder;
import kotlinx.serialization.json.JsonElement;
import kotlinx.serialization.json.JsonElementKt;
import kotlinx.serialization.json.JsonNull;
import kotlinx.serialization.json.JsonObjectBuilder;

/* JADX INFO: compiled from: MapUtils.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u0004*\u0004\u0018\u00010\u0001H\u0002J\u0018\u0010\u0003\u001a\u00020\u0004*\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0005¨\u0006\u0007"}, d2 = {"Lcom/taptap/sdk/db/utils/MapUtils;", "", "()V", "toJsonElement", "Lkotlinx/serialization/json/JsonElement;", "", "", "tap-db_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class MapUtils {
    public static final MapUtils INSTANCE = new MapUtils();

    private MapUtils() {
    }

    private final JsonElement toJsonElement(Object obj) {
        if (obj == null) {
            return JsonNull.INSTANCE;
        }
        if (obj instanceof Number) {
            return JsonElementKt.JsonPrimitive((Number) obj);
        }
        if (obj instanceof Boolean) {
            return JsonElementKt.JsonPrimitive((Boolean) obj);
        }
        if (obj instanceof String) {
            return JsonElementKt.JsonPrimitive((String) obj);
        }
        if (obj instanceof Map) {
            Set<Map.Entry> setEntrySet = ((Map) obj).entrySet();
            LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(setEntrySet, 10)), 16));
            for (Map.Entry entry : setEntrySet) {
                Pair pair = TuplesKt.to(String.valueOf(entry.getKey()), entry.getValue());
                linkedHashMap.put(pair.getFirst(), pair.getSecond());
            }
            return toJsonElement((Map<String, ? extends Object>) linkedHashMap);
        }
        if (!(obj instanceof List)) {
            if (!(obj instanceof Object[])) {
                if (!(obj instanceof Set)) {
                    if (obj instanceof JsonElement) {
                        return (JsonElement) obj;
                    }
                    return JsonElementKt.JsonPrimitive(obj.toString());
                }
                JsonArrayBuilder jsonArrayBuilder = new JsonArrayBuilder();
                Iterator it = ((Iterable) obj).iterator();
                while (it.hasNext()) {
                    jsonArrayBuilder.add(INSTANCE.toJsonElement(it.next()));
                }
                return jsonArrayBuilder.build();
            }
            JsonArrayBuilder jsonArrayBuilder2 = new JsonArrayBuilder();
            for (Object obj2 : (Object[]) obj) {
                jsonArrayBuilder2.add(INSTANCE.toJsonElement(obj2));
            }
            return jsonArrayBuilder2.build();
        }
        JsonArrayBuilder jsonArrayBuilder3 = new JsonArrayBuilder();
        Iterator it2 = ((Iterable) obj).iterator();
        while (it2.hasNext()) {
            jsonArrayBuilder3.add(INSTANCE.toJsonElement(it2.next()));
        }
        return jsonArrayBuilder3.build();
    }

    public final JsonElement toJsonElement(Map<String, ? extends Object> map) {
        Intrinsics.checkNotNullParameter(map, "<this>");
        JsonObjectBuilder jsonObjectBuilder = new JsonObjectBuilder();
        for (Map.Entry<String, ? extends Object> entry : map.entrySet()) {
            jsonObjectBuilder.put(entry.getKey(), INSTANCE.toJsonElement(entry.getValue()));
        }
        return jsonObjectBuilder.build();
    }
}
