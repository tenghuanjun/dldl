package com.volcengine.g;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.volcengine.common.innerapi.IJsonConverter;
import java.lang.reflect.Type;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public class a implements IJsonConverter {
    @Override // com.volcengine.common.innerapi.IJsonConverter
    public <T> T fromJson(String str, Class<T> cls) {
        return (T) JSON.parseObject(str, cls);
    }

    @Override // com.volcengine.common.innerapi.IJsonConverter
    public <T> T fromJson(String str, Type type) {
        return (T) JSON.parseObject(str, type, new Feature[0]);
    }

    @Override // com.volcengine.common.innerapi.IJsonConverter
    public <T> String toJson(T t) {
        return JSON.toJSONString(t);
    }
}
