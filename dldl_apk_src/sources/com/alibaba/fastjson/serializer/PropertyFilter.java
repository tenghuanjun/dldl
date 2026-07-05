package com.alibaba.fastjson.serializer;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public interface PropertyFilter extends SerializeFilter {
    boolean apply(Object obj, String str, Object obj2);
}
