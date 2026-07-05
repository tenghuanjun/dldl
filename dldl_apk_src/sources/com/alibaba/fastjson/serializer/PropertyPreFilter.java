package com.alibaba.fastjson.serializer;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes6.dex */
public interface PropertyPreFilter extends SerializeFilter {
    boolean apply(JSONSerializer jSONSerializer, Object obj, String str);
}
