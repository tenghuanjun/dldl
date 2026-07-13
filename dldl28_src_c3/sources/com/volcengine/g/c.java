package com.volcengine.g;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.volcengine.androidcloud.common.log.AcLog;
import com.volcengine.common.innerapi.IJsonConverter;
import java.io.IOException;
import java.lang.reflect.Type;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public class c implements IJsonConverter {
    private static final ObjectMapper a = new ObjectMapper();

    public c() {
        ObjectMapper objectMapper = a;
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    }

    @Override // com.volcengine.common.innerapi.IJsonConverter
    public <T> T fromJson(String str, Class<T> cls) {
        try {
            return (T) a.readValue(str, cls);
        } catch (IOException e) {
            AcLog.e("Json error", "str is not format");
            e.printStackTrace();
            return null;
        }
    }

    @Override // com.volcengine.common.innerapi.IJsonConverter
    public <T> T fromJson(String str, Type type) {
        try {
            ObjectMapper objectMapper = a;
            return (T) objectMapper.readValue(str, objectMapper.getTypeFactory().constructType(type));
        } catch (IOException e) {
            AcLog.e("Json error", "str is not format");
            e.printStackTrace();
            return null;
        }
    }

    @Override // com.volcengine.common.innerapi.IJsonConverter
    public <T> String toJson(T t) {
        try {
            return a.writerWithDefaultPrettyPrinter().writeValueAsString(t);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "";
        }
    }
}
