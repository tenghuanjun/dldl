package com.volcengine.common.innerapi;

import java.lang.reflect.Type;

/* JADX INFO: loaded from: classes3.dex */
public interface IJsonConverter {
    <T> T fromJson(String str, Class<T> cls);

    <T> T fromJson(String str, Type type);

    <T> String toJson(T t);
}
