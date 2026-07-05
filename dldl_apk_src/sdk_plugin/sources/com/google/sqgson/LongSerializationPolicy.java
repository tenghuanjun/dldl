package com.google.sqgson;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public enum LongSerializationPolicy {
    DEFAULT { // from class: com.google.sqgson.LongSerializationPolicy.1
        @Override // com.google.sqgson.LongSerializationPolicy
        public JsonElement serialize(Long l) {
            return new JsonPrimitive((Number) l);
        }
    },
    STRING { // from class: com.google.sqgson.LongSerializationPolicy.2
        @Override // com.google.sqgson.LongSerializationPolicy
        public JsonElement serialize(Long l) {
            return new JsonPrimitive(String.valueOf(l));
        }
    };

    public abstract JsonElement serialize(Long l);
}
