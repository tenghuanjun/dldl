package com.duowan.ark.preference;

import com.duowan.ark.ArkValue;
import com.duowan.ark.asignal.notify.PropertySet;
import com.duowan.ark.preference.file.InnerFile;
import com.duowan.ark.util.Config;
import com.duowan.ark.util.FP;
import com.duowan.ark.util.KLog;
import com.duowan.ark.util.json.JsonUtils;
import com.google.gson.internal.C$Gson$Types;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public abstract class JsonPreference<T> extends Preference<T> {
    private final String TAG;
    private InnerFile mInnerFile;

    public JsonPreference(T t, String str) {
        super(t, str);
        this.TAG = getClass().getName();
    }

    public JsonPreference(T t, String str, PropertySet<T> propertySet) {
        super(t, str, propertySet);
        this.TAG = getClass().getName();
    }

    @Override // com.duowan.ark.preference.Preference
    protected T getConfigValue(Config config, String str, T t) {
        InnerFile innerFileCreateInnerFileIfNotExist = createInnerFileIfNotExist(str);
        this.mInnerFile = innerFileCreateInnerFileIfNotExist;
        String str2 = null;
        if (innerFileCreateInnerFileIfNotExist != null) {
            synchronized (innerFileCreateInnerFileIfNotExist) {
                try {
                    str2 = this.mInnerFile.get();
                } catch (Exception e) {
                    e.printStackTrace();
                    KLog.error(this.TAG, "method->getConfigValue,error reason: " + e.getMessage());
                }
            }
        } else {
            KLog.error(this.TAG, "fail to create innerFile");
        }
        KLog.debug(this.TAG, "method->getConfigValue,json content: " + str2);
        return FP.empty(str2) ? t : (T) JsonUtils.parseJson(str2, getSuperclassTypeParameter(getClass()));
    }

    @Override // com.duowan.ark.preference.Preference
    protected void updateConfig(Config config, String str, T t) {
        InnerFile innerFileCreateInnerFileIfNotExist = createInnerFileIfNotExist(str);
        this.mInnerFile = innerFileCreateInnerFileIfNotExist;
        if (innerFileCreateInnerFileIfNotExist != null) {
            synchronized (innerFileCreateInnerFileIfNotExist) {
                try {
                    this.mInnerFile.save(JsonUtils.toJson(t));
                    KLog.debug(this.TAG, "method->getConfigValue,json content: " + JsonUtils.toJson(t));
                } catch (Exception e) {
                    e.printStackTrace();
                    KLog.error(this.TAG, "method->updateConfig,error reason: " + e.getMessage());
                }
            }
            return;
        }
        KLog.error(this.TAG, "fail to create innerFile");
    }

    private synchronized InnerFile createInnerFileIfNotExist(String str) {
        if (str != null) {
            if (ArkValue.gContext != null) {
                if (this.mInnerFile == null) {
                    this.mInnerFile = new InnerFile(ArkValue.gContext, str);
                }
                return this.mInnerFile;
            }
        }
        return null;
    }

    private Type getSuperclassTypeParameter(Class<?> cls) {
        Type genericSuperclass = cls.getGenericSuperclass();
        if (genericSuperclass instanceof Class) {
            throw new RuntimeException("Missing type parameter.");
        }
        ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
        if (parameterizedType.getRawType().equals(JsonPreference.class)) {
            return C$Gson$Types.canonicalize(parameterizedType.getActualTypeArguments()[0]);
        }
        return getSuperclassTypeParameter(cls.getSuperclass());
    }
}
