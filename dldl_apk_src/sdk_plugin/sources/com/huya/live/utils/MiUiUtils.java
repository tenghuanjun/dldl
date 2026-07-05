package com.huya.live.utils;

import android.os.Build;
import com.huya.mtp.utils.ShellCmd;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class MiUiUtils {
    public static boolean isXiaoMiMobile() {
        return Build.MANUFACTURER.equalsIgnoreCase("xiaomi");
    }

    public static boolean isMiui() throws Throwable {
        String systemProperty = ShellCmd.getSystemProperty("ro.miui.ui.version.name");
        return systemProperty != null && systemProperty.length() > 0;
    }

    public static int miuiVer() {
        try {
            String strSubstring = ShellCmd.getSystemProperty("ro.miui.ui.version.name").substring(1);
            if (strSubstring != null) {
                return Integer.parseInt(strSubstring);
            }
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static boolean isEmui() throws Throwable {
        String systemProperty = ShellCmd.getSystemProperty("ro.build.version.emui");
        if (systemProperty != null && systemProperty.length() != 0) {
            String[] strArrSplit = systemProperty.split("_");
            if (strArrSplit.length > 1 && Float.parseFloat(strArrSplit[1]) >= 2.3f) {
                return true;
            }
        }
        return false;
    }
}
