package com.parameters.utils;

import com.sqwan.common.util.LogUtil;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class ClassCheckUtils {
    static final String TAG = "ClassCheckUtils";
    static String packageName = "com.parameters.performfeatureconfig.";
    static String PerformFeature = packageName + "PerformFeature";
    static String PerformFeatureKey = packageName + "PerformFeatureKey";
    static String PerformFeatureType = packageName + "PerformFeatureType";

    public static boolean isExistPerformFeatureConfig() {
        StringBuilder sb;
        boolean z = false;
        try {
            try {
                if (Class.forName(PerformFeature) != null && Class.forName(PerformFeatureKey) != null) {
                    if (Class.forName(PerformFeatureType) != null) {
                        z = true;
                    }
                }
                sb = new StringBuilder();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                sb = new StringBuilder();
            }
        } catch (Throwable unused) {
            sb = new StringBuilder();
        }
        sb.append("exist:");
        sb.append(z);
        LogUtil.i(TAG, sb.toString());
        return z;
    }

    public static boolean isExist(String str) {
        try {
            return Class.forName(str) != null;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
}
