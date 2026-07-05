package com.alibaba.fastjson.serializer;

import java.lang.reflect.Type;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
@Deprecated
public class JSONSerializerMap extends SerializeConfig {
    public final boolean put(Class<?> cls, ObjectSerializer objectSerializer) {
        return super.put((Type) cls, objectSerializer);
    }
}
