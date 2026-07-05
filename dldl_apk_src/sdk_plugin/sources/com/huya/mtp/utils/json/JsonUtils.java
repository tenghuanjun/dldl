package com.huya.mtp.utils.json;

import com.google.gson.Gson;
import com.huya.mtp.api.MTPApi;
import com.huya.mtp.utils.Reflect;
import java.lang.reflect.Type;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class JsonUtils {
    private static Gson sGson = new Gson();

    public static <T> T parseJson(String str, Class<T> cls) {
        try {
            return (T) Reflect.on(cls, sGson.fromJson(str, (Class) cls)).fillNull().get();
        } catch (Exception e) {
            MTPApi.LOGGER.error(JsonUtils.class, "parse json fail: %s %s", cls.getName(), e);
            return null;
        }
    }

    public static <T> T parseJson(String str, Type type) {
        try {
            return (T) Reflect.on(type, sGson.fromJson(str, type)).fillNull().get();
        } catch (Exception e) {
            MTPApi.LOGGER.error(JsonUtils.class, "parse json fail: type %s", e);
            return null;
        }
    }

    public static String toJson(Object obj) {
        return sGson.toJson(obj);
    }

    public static <T> T parseJsonWithThrowable(String str, Class<? extends T> cls) throws Throwable {
        return (T) Reflect.on(cls, sGson.fromJson(str, (Class) cls)).fillNull().get();
    }
}
