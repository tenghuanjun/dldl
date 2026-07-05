package com.google.gson;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public interface ExclusionStrategy {
    boolean shouldSkipClass(Class<?> cls);

    boolean shouldSkipField(FieldAttributes fieldAttributes);
}
