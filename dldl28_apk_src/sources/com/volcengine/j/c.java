package com.volcengine.j;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import android.view.WindowManager;
import com.bytedance.framwork.core.sdkmonitor.MonitorConstants;
import com.tencent.connect.common.Constants;
import com.volcengine.androidcloud.common.BuildConfig;
import com.volcengine.androidcloud.common.pod.Rotation;
import com.volcengine.common.SDKContext;
import com.volcengine.common.contant.CommonConstants;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/* JADX INFO: loaded from: classes3.dex */
public final class c {
    public static int a(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            if (packageInfo == null) {
                return -1;
            }
            return packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static String a(String str, String str2) {
        return TextUtils.isEmpty(str) ? str2 : str;
    }

    public static Map<String, Object> a(int i, String str) {
        HashMap map = new HashMap();
        map.put(CommonConstants.KEY_ERROR_CODE, Integer.valueOf(i));
        map.put(CommonConstants.KEY_ERR_MSG, str);
        return map;
    }

    public static Map<String, Object> a(int i, String str, int i2, String str2) {
        HashMap map = new HashMap();
        map.put(CommonConstants.KEY_ERROR_CODE, Integer.valueOf(i));
        map.put(CommonConstants.KEY_ERR_MSG, str);
        map.put(CommonConstants.KEY_ORIGIN_ERR_CODE, Integer.valueOf(i2));
        map.put(CommonConstants.KEY_ORIGIN_ERR_MSG, str2);
        return map;
    }

    public static boolean a() {
        boolean z = n.a().getLong("build_ts", 0L) != BuildConfig.BUILD_TS;
        if (z) {
            n.a().edit().putLong("build_ts", BuildConfig.BUILD_TS).apply();
        }
        return z;
    }

    public static boolean a(String str) {
        try {
            Class.forName(str);
            return true;
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }

    public static Rotation b(Context context) {
        int rotation = ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getRotation();
        if (rotation != 0) {
            if (rotation != 1) {
                if (rotation != 2) {
                    if (rotation != 3) {
                        throw new IllegalStateException("never reach");
                    }
                }
            }
            return Rotation.LANDSCAPE;
        }
        return Rotation.PORTRAIT;
    }

    public static String b(String str) {
        Object obj;
        Context context = SDKContext.getContext();
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            if (applicationInfo != null && (obj = applicationInfo.metaData.get(str)) != null) {
                return String.valueOf(obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static boolean b() {
        String sdkVersion = SDKContext.getSdkVersion();
        if (TextUtils.isEmpty(sdkVersion)) {
            return false;
        }
        SharedPreferences sharedPreferencesA = n.a();
        String string = sharedPreferencesA.getString(Constants.JumpUrlConstants.URL_KEY_SDK_VERSION, null);
        if (TextUtils.isEmpty(string)) {
            sharedPreferencesA.edit().putString(Constants.JumpUrlConstants.URL_KEY_SDK_VERSION, sdkVersion).apply();
            return false;
        }
        boolean zEquals = TextUtils.equals(sdkVersion, string);
        boolean z = !zEquals;
        if (!zEquals) {
            sharedPreferencesA.edit().putString(Constants.JumpUrlConstants.URL_KEY_SDK_VERSION, sdkVersion).apply();
        }
        return z;
    }

    public static String c() {
        SecureRandom secureRandom = new SecureRandom();
        StringBuilder sb = new StringBuilder();
        sb.append(secureRandom.nextInt(9) + 1);
        for (int i = 1; i < 16; i++) {
            sb.append(secureRandom.nextInt(10));
        }
        return sb.toString();
    }

    public static String d() {
        return b("VOLC_ACCOUNT_ID");
    }

    public static String e() {
        return b("VOLC_CHANNEL");
    }

    public static String f() {
        SharedPreferences sharedPreferencesA = n.a("veCloud");
        String string = sharedPreferencesA.getString(MonitorConstants.KEY_DEVICE_ID, "");
        if (!TextUtils.isEmpty(string)) {
            return string;
        }
        String strC = c();
        sharedPreferencesA.edit().putString(MonitorConstants.KEY_DEVICE_ID, strC).apply();
        return strC;
    }

    public static String g() {
        SharedPreferences sharedPreferencesA = n.a("veCloud");
        String string = sharedPreferencesA.getString("install_id", null);
        if (!TextUtils.isEmpty(string)) {
            return string;
        }
        String strC = c();
        sharedPreferencesA.edit().putString("install_id", strC).apply();
        return strC;
    }

    public static String h() {
        SharedPreferences sharedPreferencesA = n.a("veCloud");
        String string = sharedPreferencesA.getString(CommonConstants.key_UUID, "");
        if (TextUtils.isEmpty(string)) {
            string = UUID.randomUUID().toString();
            sharedPreferencesA.edit().putString(CommonConstants.key_UUID, string).apply();
        }
        return "uuid_" + string;
    }

    public static boolean i() {
        return "boe".equalsIgnoreCase(b("VOLC_ENV"));
    }

    public static boolean j() {
        return !"demo".equalsIgnoreCase(b("VOLC_ClASS_LOADER"));
    }
}
