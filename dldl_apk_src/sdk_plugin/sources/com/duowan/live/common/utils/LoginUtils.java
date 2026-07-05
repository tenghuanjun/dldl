package com.duowan.live.common.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Intent;
import android.text.TextUtils;
import com.duowan.auk.ArkValue;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class LoginUtils {
    public static final String AUTO_LOGIN = "AUTO_LOGIN";
    public static final String GO_LIVE = "GO_LIVE";
    public static final int LOGIN = 1;
    public static final int OTHER_APP_LOGIN = 2;
    public static final String PASSWORD = "password";
    public static final String USER_NAME = "userName";
    private static final Map<String, String> OTHER_APP_NAME_MAP = new HashMap();
    private static final List<String> OTHER_APP_NAME = new ArrayList();

    static {
        OTHER_APP_NAME_MAP.put("com.duowan.webapp", "com.duowan.webapp.ui.UserLoginActivity");
        OTHER_APP_NAME.add("com.duowan.test");
        OTHER_APP_NAME.add("com.duowan.lolbox");
        OTHER_APP_NAME.add("com.duowan.mobile");
    }

    public static void login(boolean z) {
        try {
            Intent intent = new Intent();
            intent.setClassName(ArkValue.gContext, "com.duowan.live.user.LoginActivity");
            intent.putExtra(AUTO_LOGIN, z);
            intent.addFlags(268435456);
            intent.addFlags(67108864);
            intent.addFlags(536870912);
            ArkValue.gContext.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void login(Activity activity, boolean z) {
        try {
            Intent intent = new Intent();
            intent.setClassName(activity, "com.duowan.live.user.LoginActivity");
            intent.addFlags(268435456);
            intent.addFlags(67108864);
            intent.addFlags(536870912);
            intent.putExtra(AUTO_LOGIN, z);
            activity.startActivityForResult(intent, 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void login(Activity activity, boolean z, boolean z2) {
        Intent intent = new Intent();
        intent.setClassName(activity, "com.duowan.live.user.LoginActivity");
        intent.putExtra(AUTO_LOGIN, z);
        intent.putExtra(GO_LIVE, z2);
        activity.startActivityForResult(intent, 1);
    }

    public static void login(Activity activity) {
        String callingPackage = getCallingPackage(activity);
        if (!TextUtils.isEmpty(callingPackage)) {
            if (OTHER_APP_NAME_MAP.containsKey(callingPackage)) {
                loginFromOtherApp(activity, callingPackage);
                return;
            } else if (OTHER_APP_NAME.contains(callingPackage)) {
                loginFromOwn(activity);
                return;
            }
        }
        loginFromOwn(activity);
    }

    public static void login(Activity activity, String str, String str2, boolean z) {
        try {
            Intent intent = new Intent();
            intent.setClassName(ArkValue.gContext, "com.duowan.live.user.LoginActivity");
            intent.putExtra("userName", str);
            intent.putExtra(PASSWORD, str2);
            if (z) {
                intent.setFlags(67108864);
            }
            activity.startActivityForResult(intent, 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String getCallingPackage(Activity activity) {
        try {
            return ((ActivityManager) activity.getSystemService("activity")).getRunningTasks(1).get(0).baseActivity.getPackageName();
        } catch (Exception unused) {
            return "";
        }
    }

    private static void loginFromOtherApp(Activity activity, String str) {
        try {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName(str, OTHER_APP_NAME_MAP.get(str)));
            activity.startActivityForResult(intent, 2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void loginFromOwn(Activity activity) {
        try {
            Intent intent = new Intent();
            intent.setClassName(activity, "com.duowan.live.user.LoginActivity");
            activity.startActivityForResult(intent, 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
