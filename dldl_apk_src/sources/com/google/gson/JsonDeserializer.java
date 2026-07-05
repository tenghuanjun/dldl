package com.google.gson;

import java.lang.reflect.Type;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public interface JsonDeserializer<T> {
    T deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException;
}
