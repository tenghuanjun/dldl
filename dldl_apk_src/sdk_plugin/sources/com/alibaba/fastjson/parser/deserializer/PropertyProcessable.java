package com.alibaba.fastjson.parser.deserializer;

import java.lang.reflect.Type;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public interface PropertyProcessable extends ParseProcess {
    void apply(String str, Object obj);

    Type getType(String str);
}
