package com.sqwan.msdk.utils;

import android.content.Context;
import com.sqwan.msdk.api.MultiSDKUtils;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class MUtil {
    public static String mapToStr(LinkedHashMap<String, String> linkedHashMap) {
        if (linkedHashMap == null) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (Map.Entry<String, String> entry : linkedHashMap.entrySet()) {
            entry.getKey();
            stringBuffer.append(entry.getValue());
        }
        return stringBuffer.toString();
    }

    public static String encodingtoStr(String str) {
        Matcher matcher = Pattern.compile("(\\\\u(\\p{XDigit}{4}))").matcher(str);
        while (matcher.find()) {
            char c = (char) Integer.parseInt(matcher.group(2), 16);
            str = str.replace(matcher.group(1), c + "");
        }
        return str;
    }

    public static String enZip(String str, String str2) {
        try {
            return EncodeUtil.encode(str, str2);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String unZip(String str, String str2) {
        try {
            return EncodeUtil.decode(str, str2);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String sqEnZip(Context context, String str) {
        return enZip(MultiSDKUtils.getPID(context) + MultiSDKUtils.getGID(context) + ZipString.zipString2Json(MultiSDKUtils.getKey(context)), str);
    }

    public static String sqUnZip(Context context, String str) {
        return unZip(MultiSDKUtils.getPID(context) + MultiSDKUtils.getGID(context) + ZipString.zipString2Json(MultiSDKUtils.getKey(context)), str);
    }
}
