package com.huya.berry.gamesdk.utils;

import android.content.Context;
import android.os.Build;
import android.view.WindowManager;
import com.duowan.auk.ArkValue;
import com.duowan.auk.util.L;
import com.huya.berry.gamesdk.SdkProperties;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class SystemUtil {
    public static boolean isApkInDebug(Context context) {
        try {
            return (context.getApplicationInfo().flags & 2) != 0;
        } catch (Exception unused) {
            return false;
        }
    }

    public static String getVersionName() {
        try {
            return ArkValue.gContext.getPackageManager().getPackageInfo(ArkValue.gContext.getPackageName(), 0).versionName;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static WindowManager.LayoutParams getDefaultSystemWindowParams() {
        return getDefaultSystemWindowParams(-2, -2, true);
    }

    public static WindowManager.LayoutParams getDefaultSystemWindowParams(int i, int i2) {
        return getDefaultSystemWindowParams(i, i2, true);
    }

    public static WindowManager.LayoutParams getDefaultSystemWindowParams(int i, int i2, boolean z) {
        int i3;
        int i4;
        if (Build.VERSION.SDK_INT < 23) {
            i3 = 2005;
        } else {
            i3 = Build.VERSION.SDK_INT < 26 ? 2010 : 2038;
        }
        L.info("livetool", "overlay_type:" + i3);
        if (z) {
            SdkProperties.sdkMode.get();
            SdkProperties.SDKMode sDKMode = SdkProperties.SDKMode.CAPTURE_BY_SCREEN;
            i4 = 1320;
        } else {
            i4 = 1336;
        }
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(i, i2, i3, i4, -3);
        layoutParams.format = -3;
        layoutParams.gravity = 51;
        return layoutParams;
    }
}
