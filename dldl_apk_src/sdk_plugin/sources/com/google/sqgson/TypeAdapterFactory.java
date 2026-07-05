package com.google.sqgson;

import com.google.sqgson.reflect.TypeToken;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public interface TypeAdapterFactory {
    <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken);
}
