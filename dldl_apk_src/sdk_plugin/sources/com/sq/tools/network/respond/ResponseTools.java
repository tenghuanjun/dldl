package com.sq.tools.network.respond;

import android.text.TextUtils;
import com.sq.tools.Logger;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public abstract class ResponseTools {
    protected void initSelfByString(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            initSelfByJson(new JSONObject(str));
        } catch (JSONException e) {
            Logger.warning("Passed String can not convert to Json in class: %s", getClass().getName(), e);
        }
    }

    protected void initSelfByJson(JSONObject jSONObject) {
        String[] strArrValue;
        if (jSONObject == null) {
            return;
        }
        ArrayList<Field> arrayList = new ArrayList();
        arrayList.addAll(Arrays.asList(getClass().getDeclaredFields()));
        arrayList.addAll(Arrays.asList(getClass().getFields()));
        arrayList.addAll(Arrays.asList(getClass().getInterfaces().getClass().getDeclaredFields()));
        for (Field field : arrayList) {
            JsonResponse jsonResponse = (JsonResponse) field.getAnnotation(JsonResponse.class);
            if (jsonResponse != null && (strArrValue = jsonResponse.value()) != null && strArrValue.length != 0) {
                JSONObject jSONObjectOptJSONObject = jSONObject;
                for (int i = 0; i < strArrValue.length - 1; i++) {
                    if (jSONObjectOptJSONObject != null && jSONObjectOptJSONObject.has(strArrValue[i])) {
                        jSONObjectOptJSONObject = jSONObjectOptJSONObject.optJSONObject(strArrValue[i]);
                    } else {
                        Logger.warning("Can not parse field: %s under json name: %s in class: %s", field.getName(), strArrValue[i], getClass().getName());
                        if (i != strArrValue.length - 1 && jSONObjectOptJSONObject != null) {
                            String str = strArrValue[i];
                            if (!jSONObjectOptJSONObject.has(str)) {
                                String[] strArrReplacement = jsonResponse.replacement();
                                if (strArrReplacement.length > 0 && !strArrReplacement[0].isEmpty()) {
                                    int length = strArrReplacement.length;
                                    int i2 = 0;
                                    while (true) {
                                        if (i2 < length) {
                                            String str2 = strArrReplacement[i2];
                                            if (jSONObjectOptJSONObject.has(str2)) {
                                                str = str2;
                                            } else {
                                                i2++;
                                            }
                                        }
                                    }
                                }
                            }
                            if (jSONObjectOptJSONObject.has(str)) {
                                String string = field.getGenericType().toString();
                                field.setAccessible(true);
                                try {
                                    switch (string) {
                                        case "class java.lang.String":
                                            String string2 = jSONObjectOptJSONObject.getString(str);
                                            if (TextUtils.isEmpty(string2)) {
                                                break;
                                            } else {
                                                field.set(this, string2);
                                                break;
                                            }
                                            break;
                                        case "int":
                                        case "class java.lang.Integer":
                                            field.set(this, Integer.valueOf(jSONObjectOptJSONObject.getInt(str)));
                                            break;
                                        case "float":
                                        case "class java.lang.Float":
                                            field.set(this, Float.valueOf((float) jSONObjectOptJSONObject.getDouble(str)));
                                            break;
                                        case "double":
                                        case "class java.lang.Double":
                                            field.set(this, Double.valueOf(jSONObjectOptJSONObject.getDouble(str)));
                                            break;
                                        case "boolean":
                                        case "class java.lang.Boolean":
                                            field.set(this, Boolean.valueOf(jSONObjectOptJSONObject.getBoolean(str)));
                                            break;
                                        case "class org.json.JSONArray":
                                            field.set(this, jSONObjectOptJSONObject.getJSONArray(str));
                                            break;
                                        default:
                                            Logger.warning("Do not support type %s of JsonResponse", string);
                                            break;
                                    }
                                } catch (IllegalAccessException e) {
                                    Logger.warning("Exception happen when set filed %s", field.getName(), e);
                                } catch (JSONException e2) {
                                    Logger.warning("Exception happen when parse field: %s under json name: %s in class: %s", field.getName(), str, getClass().getName(), e2);
                                }
                            }
                        }
                    }
                }
                if (i != strArrValue.length - 1) {
                }
            }
        }
    }
}
