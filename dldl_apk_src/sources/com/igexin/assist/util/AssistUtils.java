package com.igexin.assist.util;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.igexin.push.core.p;
import com.igexin.push.f.b;
import com.igexin.sdk.PushManager;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class AssistUtils {
    static String a = "";
    static final String b = "oppo";
    static final String c = "xiaomi";
    static final String d = "meizu";
    static final String e = "vivo";
    static final String f = "huawei";
    static final String g = "stp";

    public static String getDeviceBrand() {
        if (!TextUtils.isEmpty(a)) {
            return a;
        }
        a = b.a(p.b.getApplicationContext(), f) ? f : b.a(p.b.getApplicationContext(), c) ? c : b.a(p.b.getApplicationContext(), b) ? b : b.a(p.b.getApplicationContext(), d) ? d : b.a(p.b.getApplicationContext(), e) ? e : b.a(p.b) ? g : Build.BRAND;
        return a.toLowerCase();
    }

    public static void startGetuiService(Context context) {
        if (context != null) {
            try {
                PushManager.getInstance().initialize(context);
            } catch (Throwable unused) {
            }
        }
    }
}
