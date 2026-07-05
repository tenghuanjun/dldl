package com.sqwan.msdk;

import android.content.Context;
import android.util.Log;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class SDKDataUtils {
    private static final String DEV_LOGIC_CLASS = "com.sqwan.common.dev.DevLogic";
    private static final String GID = "gid";
    private static final String IMEI_LOGIC_CLASS = "com.sqwan.common.dev.ImeiLogic";
    private static final String MAC_LOGIC_CLASS = "com.sqwan.common.dev.MacLogic";
    private static final String PID = "pid";
    private static final String REFER = "refer";
    private static final String SQ_PREFS = "sq_prefs";

    public static String getIMEI(Context context) {
        try {
            Class clsLoadClass = PluginLoader.getInstance().get().mClassLoader.loadClass(IMEI_LOGIC_CLASS);
            if (clsLoadClass != null) {
                return getValue(clsLoadClass, clsLoadClass.getMethod("getInstance", Context.class).invoke(null, context));
            }
            Log.e("sqsdk_m", "imei加载失败");
            return "";
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String getMac(Context context) {
        try {
            Class clsLoadClass = PluginLoader.getInstance().get().mClassLoader.loadClass(MAC_LOGIC_CLASS);
            if (clsLoadClass != null) {
                return getValue(clsLoadClass, clsLoadClass.getMethod("getInstance", Context.class).invoke(null, context));
            }
            Log.e("sqsdk_m", "mac加载失败");
            return "";
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String getDev(Context context) {
        try {
            Class clsLoadClass = PluginLoader.getInstance().get().mClassLoader.loadClass(DEV_LOGIC_CLASS);
            if (clsLoadClass != null) {
                return getValue(clsLoadClass, clsLoadClass.getMethod("getInstance", Context.class).invoke(null, context));
            }
            Log.e("sqsdk_m", "dev加载失败");
            return "";
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String getGID(Context context) {
        return SQwanCore.getInstance().getAppConfig().getGameid();
    }

    public static String getPID(Context context) {
        return SQwanCore.getInstance().getAppConfig().getPartner();
    }

    public static String getRefer(Context context) {
        return SQwanCore.getInstance().getAppConfig().getRefer();
    }

    private static String getValue(Class cls, Object obj) {
        try {
            return (String) cls.getMethod("getValue", new Class[0]).invoke(obj, new Object[0]);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
