package com.alipay.sdk.util;

import android.content.Context;
import android.text.TextUtils;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class i {
    public static final String a = "pref_trade_token";
    public static final String b = ";";
    public static final String c = "result={";
    public static final String d = "}";
    public static final String e = "trade_token=\"";
    public static final String f = "\"";
    public static final String g = "trade_token=";

    public static void a(Context context, String str) {
        try {
            String strA = a(str);
            c.b("", "PayResultUtil::saveTradeToken > tradeToken:" + strA);
            if (TextUtils.isEmpty(strA)) {
                return;
            }
            j.a(context, a, strA);
        } catch (Throwable th) {
            com.alipay.sdk.app.statistic.a.a(com.alipay.sdk.app.statistic.c.b, com.alipay.sdk.app.statistic.c.B, th);
            c.a(th);
        }
    }

    public static String a(String str) {
        String strSubstring = null;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        String[] strArrSplit = str.split(b);
        for (int i = 0; i < strArrSplit.length; i++) {
            if (strArrSplit[i].startsWith(c) && strArrSplit[i].endsWith(d)) {
                String[] strArrSplit2 = strArrSplit[i].substring(8, strArrSplit[i].length() - 1).split(com.alipay.sdk.sys.a.b);
                int i2 = 0;
                while (true) {
                    if (i2 >= strArrSplit2.length) {
                        break;
                    }
                    if (strArrSplit2[i2].startsWith(e) && strArrSplit2[i2].endsWith("\"")) {
                        strSubstring = strArrSplit2[i2].substring(13, strArrSplit2[i2].length() - 1);
                        break;
                    }
                    if (strArrSplit2[i2].startsWith(g)) {
                        strSubstring = strArrSplit2[i2].substring(12);
                        break;
                    }
                    i2++;
                }
            }
        }
        return strSubstring;
    }

    public static String a(Context context) {
        String strB = j.b(context, a, "");
        c.b("", "PayResultUtil::fetchTradeToken > tradeToken:" + strB);
        return strB;
    }
}
