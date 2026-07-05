package com.mobile.auth.k;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.aliyun.aliyunface.utils.MobileUtil;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes2.dex */
public class t {
    public static int a(Context context) {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getApplicationContext().getSystemService("connectivity");
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo != null && activeNetworkInfo.isAvailable()) {
                int type = activeNetworkInfo.getType();
                if (type == 1) {
                    f.b("TelephonyUtils", MobileUtil.NETWORK_WIFI);
                    boolean zA = k.a(context, "android.permission.CHANGE_NETWORK_STATE");
                    f.a("TelephonyUtils", "CHANGE_NETWORK_STATE=" + zA);
                    if (!zA || !a(context, connectivityManager)) {
                        return 2;
                    }
                    f.b("TelephonyUtils", "流量数据 WIFI 同开");
                    return 3;
                }
                if (type == 0) {
                    f.b("TelephonyUtils", "流量");
                    return 1;
                }
            }
        } catch (Exception unused) {
        }
        return 0;
    }

    public static String a() {
        return Build.BRAND;
    }

    private static boolean a(Context context, ConnectivityManager connectivityManager) {
        try {
            if (TextUtils.isEmpty(o.a(context).a(false))) {
                return false;
            }
            Method declaredMethod = ConnectivityManager.class.getDeclaredMethod("getMobileDataEnabled", new Class[0]);
            declaredMethod.setAccessible(true);
            boolean zBooleanValue = ((Boolean) declaredMethod.invoke(connectivityManager, new Object[0])).booleanValue();
            f.b("TelephonyUtils", "data is on ---------" + zBooleanValue);
            return zBooleanValue;
        } catch (Exception e) {
            f.a("TelephonyUtils", "data is on ----反射出错-----");
            e.printStackTrace();
            return false;
        }
    }

    public static String b() {
        return Build.MODEL;
    }

    public static boolean b(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        return telephonyManager == null || 1 != telephonyManager.getSimState();
    }

    public static String c() {
        return "android" + Build.VERSION.RELEASE;
    }

    public static String c(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        NetworkInfo activeNetworkInfo = connectivityManager != null ? connectivityManager.getActiveNetworkInfo() : null;
        return activeNetworkInfo != null ? activeNetworkInfo.getExtraInfo() : "";
    }

    public static boolean d() {
        String str = Build.MANUFACTURER;
        f.a("brand", str);
        return "HUAWEI".equalsIgnoreCase(str);
    }

    public static boolean e() {
        return Build.VERSION.SDK_INT <= 28;
    }
}
