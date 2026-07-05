package com.sqwan.common.util;

import android.text.TextUtils;
import com.j256.ormlite.stmt.query.SimpleComparison;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class UrlUtils {

    public static class UrlEntity {
        public String baseUrl;
        public Map<String, String> params;
    }

    public static UrlEntity parse(String str) {
        UrlEntity urlEntity = new UrlEntity();
        if (str == null) {
            return urlEntity;
        }
        String strTrim = str.trim();
        if (strTrim.equals("")) {
            return urlEntity;
        }
        String[] strArrSplit = strTrim.split("\\?");
        urlEntity.baseUrl = strArrSplit[0];
        if (strArrSplit.length == 1) {
            return urlEntity;
        }
        String[] strArrSplit2 = strArrSplit[1].split("&");
        urlEntity.params = new HashMap();
        for (String str2 : strArrSplit2) {
            String[] strArrSplit3 = str2.split(SimpleComparison.EQUAL_TO_OPERATION);
            if (strArrSplit3[0] != null && strArrSplit3[1] != null) {
                urlEntity.params.put(strArrSplit3[0], strArrSplit3[1]);
            }
        }
        return urlEntity;
    }

    public static String readValueFromUrlStrByParamName(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        int iIndexOf = str.indexOf("?");
        if (iIndexOf >= 0) {
            str = str.substring(iIndexOf + 1);
        }
        String[] strArrSplit = str.split("&");
        String str3 = str2 + SimpleComparison.EQUAL_TO_OPERATION;
        for (String str4 : strArrSplit) {
            if (str4.indexOf(str3) == 0) {
                return str4.substring(str3.length());
            }
        }
        return "";
    }

    public static String appendUrlParams(String str, Map<String, String> map) {
        if (str == null) {
            return "";
        }
        if (map == null) {
            return str;
        }
        ArrayList arrayList = new ArrayList();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            arrayList.add(String.format("%s=%s", entry.getKey(), entry.getValue()));
        }
        String strJoin = join(arrayList, "&");
        if (str.indexOf("?") > 0) {
            return str + "&" + strJoin;
        }
        return str + "?" + strJoin;
    }

    private static String join(List<String> list, String str) {
        if (list == null || list.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(list.get(0));
        for (int i = 1; i < list.size(); i++) {
            if (!TextUtils.isEmpty(list.get(i))) {
                sb.append(str);
                sb.append(list.get(i));
            }
        }
        return sb.toString();
    }
}
