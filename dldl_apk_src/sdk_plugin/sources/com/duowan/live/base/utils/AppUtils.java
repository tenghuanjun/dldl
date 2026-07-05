package com.duowan.live.base.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.net.Uri;
import android.os.Build;
import android.os.Process;
import android.text.TextUtils;
import android.util.Log;
import com.duowan.auk.ArkValue;
import com.duowan.auk.util.L;
import com.huya.live.common.api.BaseApi;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class AppUtils {
    private static final String GET_EMPTY_ERROR_MSG = "get empty error msg";
    private static final String TAG = "AppUtils";

    public static String signatureSHA1(Signature[] signatureArr) {
        return "";
    }

    public static String getVolleyErrorMsg(Exception exc) {
        String stackTraceString = Log.getStackTraceString(exc);
        if (!isStringValid(stackTraceString)) {
            stackTraceString = GET_EMPTY_ERROR_MSG;
        }
        L.debug("Utils", "[getVolleyErrorMsg] errorMsg=%s", stackTraceString);
        return stackTraceString;
    }

    private static boolean isStringValid(String str) {
        return str != null && str.trim().length() > 0;
    }

    public static boolean isTestEnv() {
        return (ArkValue.gIsSnapshot && "official".equals(ArkValue.channelName())) || ArkValue.debuggable();
    }

    public static void executePendingTransactionsSafely(String str, FragmentManager fragmentManager) {
        if (fragmentManager == null) {
            BaseApi.crashIfDebug("executePendingTransactionsSafely fragmentManager == null", new Object[0]);
            return;
        }
        try {
            fragmentManager.executePendingTransactions();
        } catch (Exception e) {
            L.error(str, (Throwable) e);
            try {
                Field declaredField = fragmentManager.getClass().getDeclaredField("mExecutingActions");
                declaredField.setAccessible(true);
                declaredField.set(fragmentManager, false);
            } catch (Exception e2) {
                L.error(str, "set field value fail", e2);
            }
            BaseApi.crashIfDebug(e, "executePendingTransactionsSafely", new Object[0]);
        }
    }

    public static FragmentManager getFragmentManagerSafely(Fragment fragment) {
        if (fragment != null) {
            return Build.VERSION.SDK_INT > 16 ? fragment.getChildFragmentManager() : fragment.getFragmentManager();
        }
        BaseApi.crashIfDebug("getFragmentManagerSafely fragment == null", new Object[0]);
        return null;
    }

    public static Map<String, String> getApps(Context context) {
        HashMap map = new HashMap();
        try {
            PackageManager packageManager = context.getPackageManager();
            List<PackageInfo> installedPackages = packageManager.getInstalledPackages(0);
            for (int i = 0; i < installedPackages.size(); i++) {
                PackageInfo packageInfo = installedPackages.get(i);
                map.put(packageInfo.applicationInfo.packageName, packageManager.getApplicationLabel(packageInfo.applicationInfo).toString());
            }
        } catch (Exception e) {
            L.error(TAG, "Exception:", e);
        }
        return map;
    }

    public static Activity getActivity(Context context) {
        int i = 0;
        while (true) {
            int i2 = i + 1;
            if (i >= 10 || context == null) {
                return null;
            }
            if (context instanceof Activity) {
                return (Activity) context;
            }
            if (context instanceof ContextWrapper) {
                context = ((ContextWrapper) context).getBaseContext();
            }
            i = i2;
        }
    }

    public static boolean isActivityRunning(String... strArr) {
        String runningActivity = getRunningActivity();
        if (runningActivity != null) {
            for (String str : strArr) {
                if (runningActivity.contains(str)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static String getRunningActivity() {
        List<ActivityManager.RunningTaskInfo> runningTasks;
        ActivityManager.RunningTaskInfo runningTaskInfo;
        ComponentName componentName;
        try {
            ActivityManager activityManager = (ActivityManager) ArkValue.gContext.getSystemService("activity");
            if (activityManager == null || (runningTasks = activityManager.getRunningTasks(1)) == null || runningTasks.isEmpty() || (runningTaskInfo = runningTasks.get(0)) == null || (componentName = runningTaskInfo.topActivity) == null) {
                return null;
            }
            return componentName.getClassName();
        } catch (Exception e) {
            L.info("getRunningActivity", "Exception:" + e);
            return null;
        }
    }

    public static boolean isActivityRunning(Class... clsArr) {
        String runningActivity = getRunningActivity();
        if (runningActivity != null) {
            for (Class cls : clsArr) {
                if (runningActivity.equals(cls.getName())) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isThirdLaunch(Activity activity, String str) {
        String packageName;
        return (activity == null || (packageName = activity.getPackageName()) == null || str == null || packageName.compareTo(str) == 0) ? false : true;
    }

    public static boolean isThirdLaunch(Activity activity) {
        if (activity == null) {
            return false;
        }
        return isThirdLaunch(activity, activity.getCallingPackage());
    }

    public static boolean validateSignature(Context context, String str, String str2) {
        try {
            String signature = getSignature(context, str);
            String signature2 = getSignature(context, str2);
            Log.e("ljq", "leftSignature[" + signature + "],rightSignature[" + signature2 + "]");
            return signature.equals(signature2);
        } catch (PackageManager.NameNotFoundException e) {
            L.error(TAG, "NameNotFoundException:" + e);
            return false;
        }
    }

    public static String getSignature(Context context, String str) throws PackageManager.NameNotFoundException {
        return SignaturesMsg.signatureSHA1(context.getPackageManager().getPackageInfo(str, 64).signatures);
    }

    public static boolean isAppInstalled(Context context, String str) {
        try {
            context.getPackageManager().getPackageInfo(str, 1);
            return true;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        } catch (Exception e) {
            e.fillInStackTrace();
            return false;
        }
    }

    public static void downloadUrl(Context context, String str) {
        try {
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str));
            intent.setFlags(268435456);
            context.startActivity(intent);
        } catch (Exception e) {
            L.error(TAG, "Exception:" + e);
        }
    }

    public static void openApp(Context context, String str) {
        try {
            Intent launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage(str);
            launchIntentForPackage.setFlags(335544320);
            context.startActivity(launchIntentForPackage);
        } catch (Exception e) {
            L.error(TAG, "NameNotFoundException:" + e);
        }
    }

    public static String getAppMetaData(Context context, String str) {
        PackageManager packageManager;
        if (context == null || (packageManager = context.getPackageManager()) == null) {
            return "";
        }
        try {
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo(context.getPackageName(), 128);
            return (applicationInfo == null || applicationInfo.metaData == null) ? "" : applicationInfo.metaData.getString(str);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static boolean isMainProcess(Context context) {
        String mainProcessName = getMainProcessName(context);
        return !TextUtils.isEmpty(mainProcessName) && TextUtils.equals(mainProcessName, context.getPackageName());
    }

    public static String getMainProcessName(Context context) {
        return getProcessName(context, Process.myPid());
    }

    public static String getProcessName(Context context, int i) {
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses();
        if (runningAppProcesses == null) {
            return null;
        }
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
            if (runningAppProcessInfo.pid == i) {
                return runningAppProcessInfo.processName;
            }
        }
        return null;
    }

    public static int getVersionCode() {
        try {
            return ArkValue.gContext.getPackageManager().getPackageInfo(ArkValue.gContext.getPackageName(), 0).versionCode;
        } catch (Exception unused) {
            return 0;
        }
    }
}
