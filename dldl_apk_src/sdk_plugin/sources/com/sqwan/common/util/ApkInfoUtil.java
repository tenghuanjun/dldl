package com.sqwan.common.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Build;
import android.util.Log;
import java.lang.reflect.Field;
import java.util.Arrays;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class ApkInfoUtil {
    private static final String ARM64_V8A = "arm64-v8a";
    private static final String CPU_ARMEABI = "armeabi";
    private static final String CPU_ARMEABI_V7 = "armeabi-v7a";
    private static final String CPU_ARMEABI_V8 = "arm64-v8a";
    private static final String CPU_X86 = "x86";
    private static final String CPU_X86_64 = "x86_64";
    private static final String TAG = "ApkInfoUtil";

    public static String[] getCpuABIs() {
        return Build.VERSION.SDK_INT >= 21 ? Build.SUPPORTED_ABIS : new String[]{Build.CPU_ABI, Build.CPU_ABI2};
    }

    public static String getCpuArch(String[] strArr) {
        String[] cpuABIs = getCpuABIs();
        Log.d(TAG, Arrays.toString(cpuABIs));
        if (cpuABIs == null || strArr == null) {
            return CPU_ARMEABI;
        }
        for (String str : cpuABIs) {
            for (String str2 : strArr) {
                if (str.equalsIgnoreCase(str2)) {
                    return str2;
                }
            }
        }
        return CPU_ARMEABI;
    }

    public static String chooseByX86andArm(Context context) {
        String cpuArchByAppInfo = getCpuArchByAppInfo(context);
        String[] strArr = {CPU_ARMEABI, "x86", "arm64-v8a", CPU_X86_64, CPU_ARMEABI_V7, "arm64-v8a"};
        for (int i = 0; i < 6; i++) {
            if (cpuArchByAppInfo.equalsIgnoreCase(strArr[i])) {
                return cpuArchByAppInfo;
            }
        }
        for (int i2 = 0; i2 < 6; i2++) {
            String str = strArr[i2];
            if (cpuArchByAppInfo.startsWith(str)) {
                return str;
            }
        }
        return getCpuArch(strArr);
    }

    public static String getCpuArchByAppInfo(Context context) {
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        String string = "";
        try {
            Field declaredField = ApplicationInfo.class.getDeclaredField("primaryCpuAbi");
            declaredField.setAccessible(true);
            Object obj = declaredField.get(applicationInfo);
            if (obj == null) {
                return "";
            }
            string = obj.toString();
            Log.d(TAG, "application" + string);
            return string;
        } catch (Exception e) {
            Log.d(TAG, "getCpuArchByAppInfo" + e.getMessage());
            return string;
        }
    }
}
