package net.security.device.api.id;

import android.os.Build;
import android.text.TextUtils;
import com.ss.android.download.api.constant.BaseConstants;
import com.ss.android.socialbase.downloader.constants.MonitorConstants;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public final class SystemUtils {
    public static String sysProperty(String str, String str2) {
        String str3;
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            str3 = (String) cls.getMethod(MonitorConstants.CONNECT_TYPE_GET, String.class, String.class).invoke(cls, str, str2);
        } catch (Exception unused) {
            str3 = null;
        }
        return str3 == null ? "" : str3;
    }

    public static boolean isASUS() {
        return Build.MANUFACTURER.toUpperCase().equals("ASUS");
    }

    public static boolean isHuawei() {
        return Build.MANUFACTURER.toUpperCase().equals("HUAWEI");
    }

    public static boolean isZTE() {
        return Build.MANUFACTURER.toUpperCase().equals("ZTE");
    }

    public static boolean isXiaomi() {
        return Build.MANUFACTURER.toUpperCase().equals("XIAOMI");
    }

    public static boolean isOppo() {
        return Build.MANUFACTURER.toUpperCase().equals(BaseConstants.ROM_OPPO_UPPER_CONSTANT);
    }

    public static boolean isVivo() {
        return Build.MANUFACTURER.toUpperCase().equals("VIVO");
    }

    public static boolean isOnePlus() {
        return Build.MANUFACTURER.toUpperCase().equals("ONEPLUS");
    }

    public static boolean isBlackShark() {
        return Build.MANUFACTURER.toUpperCase().equals("BLACKSHARK");
    }

    public static boolean isSamsung() {
        return Build.MANUFACTURER.toUpperCase().equals("SAMSUNG");
    }

    public static boolean isMotolora() {
        return Build.MANUFACTURER.toUpperCase().equals("MOTOLORA");
    }

    public static boolean isNubia() {
        return Build.MANUFACTURER.toUpperCase().equals("NUBIA");
    }

    public static boolean isMeizu() {
        return Build.MANUFACTURER.toUpperCase().equals("MEIZU");
    }

    public static boolean isLenovo() {
        return Build.MANUFACTURER.toUpperCase().equals("LENOVO");
    }

    public static boolean isFreeme() {
        if (Build.MANUFACTURER.toUpperCase().equals("FREEMEOS")) {
            return true;
        }
        String strSysProperty = sysProperty("ro.build.freeme.label", "");
        return !TextUtils.isEmpty(strSysProperty) && strSysProperty.toUpperCase().equals("FREEMEOS");
    }

    public static boolean isSSUI() {
        if (Build.MANUFACTURER.toUpperCase().equals("SSUI")) {
            return true;
        }
        String strSysProperty = sysProperty("ro.ssui.product", "unknown");
        return (TextUtils.isEmpty(strSysProperty) || strSysProperty.toUpperCase().equals("UNKNOWN")) ? false : true;
    }
}
