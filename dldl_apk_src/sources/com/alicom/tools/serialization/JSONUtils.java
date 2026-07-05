package com.alicom.tools.serialization;

import android.text.TextUtils;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes2.dex */
public class JSONUtils {
    private static JSONCache mJsonCache = new JSONCache();

    public static <T> T fromJson(JSONObject jSONObject, JSONType<T> jSONType, List<Field> list) {
        T tNewInstance = jSONType.newInstance();
        if (!(tNewInstance instanceof JSONer)) {
            return (T) fromJson(jSONObject, tNewInstance, list);
        }
        ((JSONer) tNewInstance).fromJson(jSONObject);
        return tNewInstance;
    }

    public static <T> T fromJson(JSONObject jSONObject, T t, List<Field> list) {
        if (jSONObject != null && t != null) {
            Class<?> cls = t.getClass();
            JSONClass jsonClass = mJsonCache.getJsonClass(cls);
            if (jsonClass == null) {
                jsonClass = new JSONClass(cls);
                mJsonCache.putJsonClass(cls, jsonClass);
            }
            List<Field> fields = jsonClass.getFields();
            if (fields != null && fields.size() > 0) {
                for (Field field : fields) {
                    JSONField jsonField = jsonClass.getJsonField(field.getName());
                    if (jsonField == null) {
                        jsonField = new JSONField(field);
                        jsonClass.putJsonField(field.getName(), jsonField);
                    }
                    if (!jsonField.isExcluded()) {
                        if (jsonField.isOriginalType()) {
                            try {
                                field.setAccessible(true);
                                if (jSONObject.has(jsonField.getKeyName())) {
                                    field.set(t, jSONObject.opt(jsonField.getKeyName()));
                                }
                                field = null;
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        if (field != null && list != null) {
                            list.add(field);
                        }
                    }
                }
            }
        }
        return t;
    }

    public static boolean isOriginalBoolean(Class cls) {
        return Boolean.TYPE.equals(cls) || Boolean.class.equals(cls) || boolean[].class.equals(cls) || Boolean[].class.equals(cls);
    }

    public static boolean isOriginalChar(Class cls) {
        return Byte.TYPE.equals(cls) || Byte.class.equals(cls) || byte[].class.equals(cls) || Byte[].class.equals(cls) || Character.TYPE.equals(cls) || Character.class.equals(cls) || char[].class.equals(cls) || Character[].class.equals(cls);
    }

    public static boolean isOriginalNumber(Class cls) {
        return Integer.TYPE.equals(cls) || Integer.class.equals(cls) || int[].class.equals(cls) || Integer[].class.equals(cls) || Short.TYPE.equals(cls) || Short.class.equals(cls) || short[].class.equals(cls) || Short[].class.equals(cls) || Long.TYPE.equals(cls) || Long.class.equals(cls) || long[].class.equals(cls) || Long[].class.equals(cls) || Float.TYPE.equals(cls) || Float.class.equals(cls) || float[].class.equals(cls) || Float[].class.equals(cls) || Double.TYPE.equals(cls) || Double.class.equals(cls) || double[].class.equals(cls) || Double[].class.equals(cls);
    }

    public static boolean isOriginalString(Class cls) {
        return String.class.equals(cls) || StringBuilder.class.equals(cls) || String[].class.equals(cls) || StringBuilder[].class.equals(cls) || StringBuffer.class.equals(cls) || CharSequence.class.equals(cls) || StringBuffer[].class.equals(cls) || CharSequence[].class.equals(cls);
    }

    public static Map<String, Integer> json2MapForStringInteger(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.length() <= 0) {
                return null;
            }
            HashMap map = new HashMap(jSONObject.length());
            Iterator<String> itKeys = jSONObject.keys();
            while (itKeys.hasNext()) {
                String next = itKeys.next();
                map.put(next, Integer.valueOf(jSONObject.getInt(next)));
            }
            return map;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Map<String, String> json2MapForStringString(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            return json2MapForStringString(new JSONObject(str));
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Map<String, String> json2MapForStringString(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        try {
            if (jSONObject.length() <= 0) {
                return null;
            }
            HashMap map = new HashMap(jSONObject.length());
            Iterator<String> itKeys = jSONObject.keys();
            while (itKeys.hasNext()) {
                String next = itKeys.next();
                map.put(next, jSONObject.getString(next));
            }
            return map;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T extends JSONer> JSONArray jsonerList2JsonArray(List<T> list) {
        JSONArray jSONArray = new JSONArray();
        if (list != null && list.size() > 0) {
            Iterator<T> it = list.iterator();
            while (it.hasNext()) {
                jSONArray.put(it.next().toJson());
            }
        }
        return jSONArray;
    }

    public static <T extends JSONer> List<T> parseJsonArray2JsonerList(String str, JSONType<T> jSONType) {
        try {
            return parseJsonArray2JsonerList(new JSONArray(str), jSONType);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T extends JSONer> List<T> parseJsonArray2JsonerList(JSONArray jSONArray, JSONType<T> jSONType) {
        if (jSONArray == null) {
            return null;
        }
        try {
            int length = jSONArray.length();
            if (length <= 0) {
                return null;
            }
            ArrayList arrayList = new ArrayList(length);
            for (int i = 0; i < length; i++) {
                String string = jSONArray.getString(i);
                if (!TextUtils.isEmpty(string)) {
                    JSONObject jSONObject = new JSONObject(string);
                    T tNewInstance = jSONType.newInstance();
                    tNewInstance.fromJson(jSONObject);
                    arrayList.add(tNewInstance);
                }
            }
            return arrayList;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static JSONObject toJson(Object obj, List<Field> list) {
        return toJson(obj, list, false);
    }

    public static JSONObject toJson(Object obj, List<Field> list, boolean z) {
        JSONObject jSONObject = new JSONObject();
        Class<?> cls = obj.getClass();
        JSONClass jsonClass = mJsonCache.getJsonClass(cls);
        if (jsonClass == null) {
            jsonClass = new JSONClass(cls);
            mJsonCache.putJsonClass(cls, jsonClass);
        }
        List<Field> fields = jsonClass.getFields();
        if (fields != null && fields.size() > 0) {
            for (Field field : fields) {
                JSONField jsonField = jsonClass.getJsonField(field.getName());
                if (jsonField == null) {
                    jsonField = new JSONField(field);
                    jsonClass.putJsonField(field.getName(), jsonField);
                }
                if (!jsonField.isExcluded()) {
                    if (jsonField.isOriginalType()) {
                        try {
                            field.setAccessible(true);
                            Object obj2 = field.get(obj);
                            if (!z || obj2 != null) {
                                jSONObject.put(jsonField.getKeyName(), obj2);
                            }
                            field = null;
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    if (field != null && list != null) {
                        list.add(field);
                    }
                }
            }
        }
        return jSONObject;
    }
}
