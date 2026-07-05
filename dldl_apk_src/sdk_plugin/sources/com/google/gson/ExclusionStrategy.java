package com.google.gson;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public interface ExclusionStrategy {
    boolean shouldSkipClass(Class<?> cls);

    boolean shouldSkipField(FieldAttributes fieldAttributes);
}
