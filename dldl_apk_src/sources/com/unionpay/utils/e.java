package com.unionpay.utils;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import java.util.UUID;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes6.dex */
public final class e {
    public static String a() {
        return !TextUtils.isEmpty(Build.VERSION.RELEASE) ? Build.VERSION.RELEASE.trim() : "";
    }

    public static String a(Context context) {
        try {
            String packageName = context instanceof Activity ? ((Activity) context).getPackageName() : "";
            return packageName == null ? "" : packageName;
        } catch (Exception unused) {
            return "";
        }
    }

    public static String b() {
        if (!TextUtils.isEmpty(Build.MODEL)) {
            String strTrim = Build.MODEL.trim();
            if (!TextUtils.isEmpty(strTrim)) {
                return strTrim.replace(" ", "");
            }
        }
        return "";
    }

    public static String b(Context context) {
        if (context == null) {
            return "";
        }
        try {
            String strA = UPUtils.a(context, "merchant_id");
            if (TextUtils.isEmpty(strA)) {
                strA = UUID.randomUUID().toString();
                if (!TextUtils.isEmpty(strA)) {
                    strA = strA.replaceAll("-", "");
                    UPUtils.a(context, strA, "merchant_id");
                }
            }
            return strA;
        } catch (Exception unused) {
            return "";
        }
    }
}
