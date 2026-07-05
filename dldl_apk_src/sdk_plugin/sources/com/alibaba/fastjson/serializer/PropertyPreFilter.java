package com.alibaba.fastjson.serializer;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public interface PropertyPreFilter extends SerializeFilter {
    boolean apply(JSONSerializer jSONSerializer, Object obj, String str);
}
