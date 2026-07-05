package com.alipay.sdk.packet;

import android.text.TextUtils;
import com.alipay.sdk.util.i;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class a {
    public static String a(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        String[] strArrSplit = str.split(com.alipay.sdk.sys.a.b);
        if (strArrSplit.length == 0) {
            return "";
        }
        String strB = null;
        String strC = null;
        String strD = null;
        String strF = null;
        for (String str2 : strArrSplit) {
            if (TextUtils.isEmpty(strB)) {
                strB = b(str2);
            }
            if (TextUtils.isEmpty(strC)) {
                strC = c(str2);
            }
            if (TextUtils.isEmpty(strD)) {
                strD = d(str2);
            }
            if (TextUtils.isEmpty(strF)) {
                strF = f(str2);
            }
        }
        StringBuilder sb = new StringBuilder();
        if (!TextUtils.isEmpty(strB)) {
            sb.append("biz_type=" + strB + i.b);
        }
        if (!TextUtils.isEmpty(strC)) {
            sb.append("biz_no=" + strC + i.b);
        }
        if (!TextUtils.isEmpty(strD)) {
            sb.append("trade_no=" + strD + i.b);
        }
        if (!TextUtils.isEmpty(strF)) {
            sb.append("app_userid=" + strF + i.b);
        }
        String string = sb.toString();
        return string.endsWith(i.b) ? string.substring(0, string.length() - 1) : string;
    }

    private static String b(String str) {
        if (str.contains("biz_type")) {
            return e(str);
        }
        return null;
    }

    private static String c(String str) {
        if (str.contains("biz_no")) {
            return e(str);
        }
        return null;
    }

    private static String d(String str) {
        if (!str.contains(com.alipay.sdk.app.statistic.c.ad) || str.startsWith(com.alipay.sdk.app.statistic.c.ac)) {
            return null;
        }
        return e(str);
    }

    private static String e(String str) {
        String[] strArrSplit = str.split("=");
        if (strArrSplit.length <= 1) {
            return null;
        }
        String str2 = strArrSplit[1];
        return str2.contains("\"") ? str2.replaceAll("\"", "") : str2;
    }

    private static String f(String str) {
        if (str.contains("app_userid")) {
            return e(str);
        }
        return null;
    }
}
