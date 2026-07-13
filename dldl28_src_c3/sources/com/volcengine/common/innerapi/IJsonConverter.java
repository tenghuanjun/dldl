package com.volcengine.common.innerapi;

import java.lang.reflect.Type;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public interface IJsonConverter {
    <T> T fromJson(String str, Class<T> cls);

    <T> T fromJson(String str, Type type);

    <T> String toJson(T t);
}
