package com.sq.tools.event;

import android.text.TextUtils;
import com.sq.tools.Logger;
import java.io.Serializable;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public abstract class FiledTools implements Serializable {
    protected String data;
    protected int maxLength = 512;

    @Target({ElementType.FIELD})
    @Retention(RetentionPolicy.RUNTIME)
    public @interface EventField {
        String value();
    }

    public JSONObject toJsonObject() {
        ArrayList<Field> arrayList = new ArrayList();
        arrayList.addAll(Arrays.asList(getClass().getFields()));
        arrayList.addAll(Arrays.asList(getClass().getDeclaredFields()));
        arrayList.addAll(Arrays.asList(getClass().getInterfaces().getClass().getDeclaredFields()));
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject = new JSONObject(this.data != null ? this.data : "{}");
        } catch (JSONException unused) {
        }
        for (Field field : arrayList) {
            EventField eventField = (EventField) field.getAnnotation(EventField.class);
            if (eventField != null) {
                String strValue = eventField.value();
                if (!TextUtils.isEmpty(strValue)) {
                    field.setAccessible(true);
                    try {
                        jSONObject.put(strValue, cropFiled(field.get(this)));
                    } catch (IllegalAccessException e) {
                        Logger.error(Logger.tag(EventsTracker.TAG), "Get Field value from Event with refection has Exception", e);
                    } catch (JSONException e2) {
                        Logger.error(Logger.tag(EventsTracker.TAG), "Put Event value to Json has Exception", e2);
                    }
                }
            }
        }
        return jSONObject;
    }

    protected Object cropFiled(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj.toString().length() <= this.maxLength) {
            return obj;
        }
        JSONObject jSONObjectCastToJsonObject = castToJsonObject(obj);
        if (jSONObjectCastToJsonObject == null) {
            return obj instanceof String ? ((String) obj).substring(0, this.maxLength) : obj;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            Iterator<String> itKeys = jSONObjectCastToJsonObject.keys();
            while (itKeys.hasNext()) {
                String next = itKeys.next();
                Object obj2 = jSONObjectCastToJsonObject.get(next);
                if ((obj2 instanceof String) && obj2.toString().length() >= this.maxLength / jSONObjectCastToJsonObject.length()) {
                    obj2 = "Cut:" + jSONObjectCastToJsonObject.optString(next).substring(0, (this.maxLength / jSONObjectCastToJsonObject.length()) - 5);
                }
                jSONObject.put(next, obj2);
            }
            return jSONObject;
        } catch (JSONException e) {
            Logger.error(Logger.tag(EventsTracker.TAG), "Put cut data to json exception", e);
            return obj;
        }
    }

    private JSONObject castToJsonObject(Object obj) {
        if (obj instanceof JSONObject) {
            return (JSONObject) obj;
        }
        try {
            if (obj instanceof String) {
                return new JSONObject((String) obj);
            }
            return null;
        } catch (Exception unused) {
            return null;
        }
    }

    public String toString() {
        return toJsonObject().toString();
    }
}
