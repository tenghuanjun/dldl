package com.google.gson.internal;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes6.dex */
public abstract class GsonInternalAccess {
    public static GsonInternalAccess INSTANCE;

    public abstract <T> TypeAdapter<T> getNextAdapter(Gson gson, TypeAdapterFactory typeAdapterFactory, TypeToken<T> typeToken);
}
