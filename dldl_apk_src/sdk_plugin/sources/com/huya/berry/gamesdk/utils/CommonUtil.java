package com.huya.berry.gamesdk.utils;

import android.app.ActivityManager;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Process;
import android.text.TextPaint;
import android.text.TextUtils;
import android.view.WindowManager;
import com.duowan.auk.ArkValue;
import com.huya.berry.gamesdk.SdkProperties;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class CommonUtil {
    private static final String TAG = "CommonUtil";

    private static boolean checkPackInfo(Context context, String str) {
        PackageInfo packageInfo;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(str, 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            packageInfo = null;
        }
        return packageInfo != null;
    }

    public static boolean isScreenLandScape() {
        return SdkProperties.isLandscape.get().booleanValue();
    }

    public static int getDisplayRotation(WindowManager windowManager) {
        if (windowManager == null) {
            return 0;
        }
        int rotation = windowManager.getDefaultDisplay().getRotation();
        if (rotation == 1) {
            return 90;
        }
        if (rotation != 2) {
            return rotation != 3 ? 0 : 270;
        }
        return 180;
    }

    public static boolean isMainProcess(Context context) {
        return context.getPackageName().equals(getProcessName(context));
    }

    public static String getProcessName(Context context) {
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses();
        if (runningAppProcesses == null) {
            return null;
        }
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
            if (runningAppProcessInfo.pid == Process.myPid() && runningAppProcessInfo.processName != null) {
                return runningAppProcessInfo.processName;
            }
        }
        return null;
    }

    public static boolean isEmulator() {
        String lowerCase = Build.MODEL.toLowerCase();
        return Build.FINGERPRINT.startsWith("generic") || Build.FINGERPRINT.startsWith("unknown") || lowerCase.contains("google_sdk") || lowerCase.contains("emulator") || lowerCase.contains("simulator") || lowerCase.contains("android sdk built for x86") || Build.MANUFACTURER.contains("genymotion") || (Build.BRAND.startsWith("generic") && Build.DEVICE.startsWith("generic")) || "google_sdk".equals(Build.PRODUCT);
    }

    public static boolean isAvailableCPU() {
        return TextUtils.isEmpty(getCPU()) || !getCPU().equals("x86");
    }

    public static String getCPU() {
        for (String str : Build.VERSION.SDK_INT >= 21 ? Build.SUPPORTED_ABIS : new String[]{Build.CPU_ABI, Build.CPU_ABI2}) {
            if (!TextUtils.isEmpty(str)) {
                if (str.contains("x86")) {
                    return "x86";
                }
                if (str.contains("arm64")) {
                    return "arm64";
                }
                if (str.contains("armeabi")) {
                    return "armeabi";
                }
            }
        }
        return "";
    }

    public static void copyToClipboard(String str) {
        ((ClipboardManager) ArkValue.gContext.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("text", str));
    }

    public static boolean hasAlipay() {
        PackageManager packageManager = ArkValue.gContext.getPackageManager();
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse("alipays://"));
        List<ResolveInfo> listQueryIntentActivities = packageManager.queryIntentActivities(intent, 64);
        return listQueryIntentActivities != null && listQueryIntentActivities.size() > 0;
    }

    public static String getShortText(String str, int i) {
        return str.length() > i ? getLenString(str, i) : str;
    }

    private static String getLenString(String str, int i) {
        Matcher matcher = Pattern.compile("[0-9]*").matcher(str);
        if (i > str.length()) {
            i = str.length();
        }
        if (matcher.matches()) {
            int i2 = (i / 2) + i;
            if (i2 <= i) {
                i = i2;
            }
            return str.substring(0, i) + "...";
        }
        if (Pattern.compile("[a-zA-Z]").matcher(str).matches()) {
            int i3 = (i / 2) + i;
            if (i3 <= i) {
                i = i3;
            }
            return str.substring(0, i) + "...";
        }
        if (Pattern.compile("[一-龥]").matcher(str).matches()) {
            return str.substring(0, i) + "...";
        }
        return str.substring(0, i) + "...";
    }

    public static int getStringLen(String str) {
        Matcher matcher = Pattern.compile("[0-9]*").matcher(str);
        int length = str.length();
        if (matcher.matches()) {
            return length / 2;
        }
        return Pattern.compile("[a-zA-Z]").matcher(str).matches() ? length / 2 : length;
    }

    public static float getTextWidth(String str, float f) {
        TextPaint textPaint = new TextPaint();
        textPaint.setTextSize(f);
        return textPaint.measureText(str);
    }

    public static boolean isNumeric(String str) {
        int length = str.length();
        do {
            length--;
            if (length < 0) {
                return true;
            }
        } while (Character.isDigit(str.charAt(length)));
        return false;
    }
}
