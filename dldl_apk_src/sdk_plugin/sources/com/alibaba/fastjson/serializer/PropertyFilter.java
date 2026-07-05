package com.alibaba.fastjson.serializer;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public interface PropertyFilter extends SerializeFilter {
    boolean apply(Object obj, String str, Object obj2);
}
