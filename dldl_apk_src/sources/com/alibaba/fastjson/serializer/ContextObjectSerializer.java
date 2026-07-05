package com.alibaba.fastjson.serializer;

import java.io.IOException;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes6.dex */
public interface ContextObjectSerializer extends ObjectSerializer {
    void write(JSONSerializer jSONSerializer, Object obj, BeanContext beanContext) throws IOException;
}
