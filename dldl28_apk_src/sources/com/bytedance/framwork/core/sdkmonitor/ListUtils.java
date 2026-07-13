package com.bytedance.framwork.core.sdkmonitor;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class ListUtils {
    public static boolean isEmpty(List<?> list) {
        return list == null || list.size() == 0;
    }

    public static List<String> parseList(JSONObject jSONObject, String str) {
        try {
            JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray(str);
            if (jSONArrayOptJSONArray == null || jSONArrayOptJSONArray.length() <= 0) {
                return null;
            }
            int length = jSONArrayOptJSONArray.length();
            ArrayList arrayList = new ArrayList(length);
            for (int i = 0; i < length; i++) {
                String string = jSONArrayOptJSONArray.getString(i);
                if (!TextUtils.isEmpty(string)) {
                    arrayList.add(string);
                }
            }
            return arrayList;
        } catch (Exception unused) {
            return null;
        }
    }

    public static List<Pattern> parsePatterns(JSONObject jSONObject, String str) {
        try {
            JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray(str);
            if (jSONArrayOptJSONArray == null || jSONArrayOptJSONArray.length() <= 0) {
                return null;
            }
            int length = jSONArrayOptJSONArray.length();
            ArrayList arrayList = new ArrayList(length);
            for (int i = 0; i < length; i++) {
                String string = jSONArrayOptJSONArray.getString(i);
                if (!TextUtils.isEmpty(string)) {
                    arrayList.add(Pattern.compile(string));
                }
            }
            return arrayList;
        } catch (Exception unused) {
            return null;
        }
    }

    public static String[] toArray(Set<String> set) {
        String[] strArr = new String[set.size()];
        Iterator<String> it = set.iterator();
        int i = 0;
        while (it.hasNext()) {
            strArr[i] = it.next();
            i++;
        }
        return strArr;
    }
}
