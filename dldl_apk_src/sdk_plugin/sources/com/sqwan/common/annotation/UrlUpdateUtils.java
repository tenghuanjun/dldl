package com.sqwan.common.annotation;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.sq.tool.logger.SQLog;
import com.sq.tool.network.GateWayManager;
import com.sqwan.common.url.UrlMod;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class UrlUpdateUtils {
    private static final String SQ_URL_UPDATE = "sq_url_update";
    private static Map<String, String> urlMap = new HashMap();

    public static ArrayList<String> getUrls() {
        ArrayList<String> arrayList = new ArrayList<>();
        Iterator<Map.Entry<String, String>> it = UrlMod.getUrlMod().entrySet().iterator();
        while (it.hasNext()) {
            try {
                Class<?> cls = Class.forName(it.next().getValue());
                for (Field field : cls.getDeclaredFields()) {
                    field.setAccessible(true);
                    if (((UrlUpdate) field.getAnnotation(UrlUpdate.class)) != null && Modifier.isStatic(field.getModifiers())) {
                        Object obj = field.get(cls);
                        if (obj instanceof String) {
                            String str = (String) obj;
                            if (!TextUtils.isEmpty(str)) {
                                arrayList.add(str);
                            }
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return arrayList;
    }

    public static HashMap<String, String> getProgramUrlMap() {
        HashMap<String, String> map = new HashMap<>();
        Iterator<Map.Entry<String, String>> it = UrlMod.getUrlMod().entrySet().iterator();
        while (it.hasNext()) {
            try {
                Class<?> cls = Class.forName(it.next().getValue());
                for (Field field : cls.getDeclaredFields()) {
                    field.setAccessible(true);
                    UrlUpdate urlUpdate = (UrlUpdate) field.getAnnotation(UrlUpdate.class);
                    if (urlUpdate != null) {
                        String strValue = urlUpdate.value();
                        if (Modifier.isStatic(field.getModifiers())) {
                            Object obj = field.get(cls);
                            if (obj instanceof String) {
                                map.put(strValue, (String) obj);
                            }
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return map;
    }

    public static void urlUpdate(Context context) {
        String strValue;
        Iterator<Map.Entry<String, String>> it = UrlMod.getUrlMod().entrySet().iterator();
        while (it.hasNext()) {
            try {
                Class<?> cls = Class.forName(it.next().getValue());
                for (Field field : cls.getDeclaredFields()) {
                    field.setAccessible(true);
                    UrlUpdate urlUpdate = (UrlUpdate) field.getAnnotation(UrlUpdate.class);
                    if (urlUpdate != null) {
                        if (urlMap.get(urlUpdate.xValue()) != null) {
                            strValue = urlUpdate.xValue();
                        } else {
                            strValue = urlUpdate.value();
                        }
                        if (Modifier.isStatic(field.getModifiers())) {
                            Object obj = field.get(cls);
                            if (obj instanceof String) {
                                GateWayManager.addWhiteListUrl((String) obj);
                                if (urlMap.get(strValue) != null) {
                                    String urlFromSpOrLocal = getUrlFromSpOrLocal(strValue, context);
                                    if (!TextUtils.isEmpty(urlFromSpOrLocal)) {
                                        SQLog.v("originalUrl: " + obj + " targetUrl: " + urlFromSpOrLocal + " " + obj.equals(urlFromSpOrLocal));
                                        field.set(null, urlFromSpOrLocal);
                                    }
                                }
                            }
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void setUrlData(Map<String, String> map, Context context) {
        if (map == null) {
            return;
        }
        urlMap.putAll(map);
        for (Map.Entry<String, String> entry : urlMap.entrySet()) {
            setUrlUpdateFromSp(entry.getKey(), entry.getValue(), context);
        }
    }

    private static void setUrlUpdateFromSp(String str, String str2, Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SQ_URL_UPDATE, 0);
        SharedPreferences.Editor editorEdit = sharedPreferences.edit();
        String string = sharedPreferences.getString(str, "");
        if (string != null && !string.equals(str2)) {
            SQLog.w("原先url：" + string + "更新为：" + str2);
        }
        editorEdit.putString(str, str2);
        editorEdit.commit();
    }

    private static String getUrlFromSpOrLocal(String str, Context context) {
        String string = context.getSharedPreferences(SQ_URL_UPDATE, 0).getString(str, "");
        return !TextUtils.isEmpty(string) ? string : "";
    }
}
