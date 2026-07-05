package com.alibaba.fastjson.serializer;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes6.dex */
public interface ContextValueFilter extends SerializeFilter {
    Object process(BeanContext beanContext, Object obj, String str, Object obj2);
}
