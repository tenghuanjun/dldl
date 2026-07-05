package com.huya.berry.gamesdk.utils;

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
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Process;
import android.text.TextUtils;
import android.util.Log;
import com.bumptech.glide.Registry;
import com.duowan.auk.ArkUtils;
import com.duowan.auk.ArkValue;
import com.duowan.auk.util.L;
import com.duowan.live.one.module.uploadLog.FeedBackConstants;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class AppUtils {
    private static final String GET_EMPTY_ERROR_MSG = "get empty error msg";
    private static final String PASSWORD = "password";
    private static final String USER_NAME = "userName";

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
        return (ArkValue.gIsSnapshot && ArkValue.channelName().equals("official")) || ArkValue.debuggable();
    }

    public static void executePendingTransactionsSafely(String str, FragmentManager fragmentManager) {
        if (fragmentManager == null) {
            ArkUtils.crashIfDebug("executePendingTransactionsSafely fragmentManager == null", new Object[0]);
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
            ArkUtils.crashIfDebug(e, "executePendingTransactionsSafely", new Object[0]);
        }
    }

    public static FragmentManager getFragmentManagerSafely(Fragment fragment) {
        if (fragment != null) {
            return Build.VERSION.SDK_INT > 16 ? fragment.getChildFragmentManager() : fragment.getFragmentManager();
        }
        ArkUtils.crashIfDebug("getFragmentManagerSafely fragment == null", new Object[0]);
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
        } catch (Exception unused) {
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
        } catch (Exception unused) {
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

    public static String getProcessName(int i) {
        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new FileReader("/proc/" + i + "/cmdline"));
            try {
                String line = bufferedReader.readLine();
                if (!TextUtils.isEmpty(line)) {
                    line = line.trim();
                }
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return line;
            } catch (Throwable th) {
                th = th;
                try {
                    th.printStackTrace();
                    return null;
                } finally {
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (IOException e2) {
                            e2.printStackTrace();
                        }
                    }
                }
            }
        } catch (Throwable th2) {
            th = th2;
            bufferedReader = null;
        }
    }

    public static boolean isAppInstalled(Context context, String str) {
        try {
            context.getPackageManager().getPackageInfo(str, 1);
            return true;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    public static void downloadUrl(Context context, String str) {
        try {
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str));
            intent.setFlags(268435456);
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void openApp(Context context, String str) {
        try {
            Intent launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage(str);
            launchIntentForPackage.setFlags(335544320);
            context.startActivity(launchIntentForPackage);
        } catch (Exception e) {
            e.printStackTrace();
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
        } catch (Throwable th) {
            L.error("AppUtils", "getAppMetaData exception:" + str + ",exception:" + th);
            th.printStackTrace();
            return "";
        }
    }

    public static Bitmap compressImage(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i = 100;
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        L.error(Registry.BUCKET_BITMAP, "compressImage,baos.toByteArray().length: " + byteArrayOutputStream.toByteArray().length);
        while (((double) byteArrayOutputStream.toByteArray().length) / 1024.0d > 1024.0d) {
            L.error(Registry.BUCKET_BITMAP, "compressImage,baos.toByteArray().length / 1024.0: " + (((double) byteArrayOutputStream.toByteArray().length) / 1024.0d) + ";options:" + i);
            byteArrayOutputStream.reset();
            bitmap.compress(Bitmap.CompressFormat.JPEG, i, byteArrayOutputStream);
            i += -10;
        }
        return BitmapFactory.decodeStream(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()), null, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0033  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static android.graphics.Bitmap getImage(java.lang.String r6) {
        /*
            android.graphics.BitmapFactory$Options r0 = new android.graphics.BitmapFactory$Options
            r0.<init>()
            r1 = 1
            r0.inJustDecodeBounds = r1
            android.graphics.BitmapFactory.decodeFile(r6, r0)
            r2 = 0
            r0.inJustDecodeBounds = r2
            int r2 = r0.outWidth
            int r3 = r0.outHeight
            if (r2 <= r3) goto L21
            float r4 = (float) r2
            r5 = 1139802112(0x43f00000, float:480.0)
            int r4 = (r4 > r5 ? 1 : (r4 == r5 ? 0 : -1))
            if (r4 <= 0) goto L21
            int r2 = r0.outWidth
            float r2 = (float) r2
            float r2 = r2 / r5
        L1f:
            int r2 = (int) r2
            goto L30
        L21:
            if (r2 >= r3) goto L2f
            float r2 = (float) r3
            r3 = 1145569280(0x44480000, float:800.0)
            int r2 = (r2 > r3 ? 1 : (r2 == r3 ? 0 : -1))
            if (r2 <= 0) goto L2f
            int r2 = r0.outHeight
            float r2 = (float) r2
            float r2 = r2 / r3
            goto L1f
        L2f:
            r2 = 1
        L30:
            if (r2 > 0) goto L33
            goto L34
        L33:
            r1 = r2
        L34:
            r0.inSampleSize = r1
            android.graphics.Bitmap r6 = android.graphics.BitmapFactory.decodeFile(r6, r0)
            android.graphics.Bitmap r6 = compressImage(r6)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huya.berry.gamesdk.utils.AppUtils.getImage(java.lang.String):android.graphics.Bitmap");
    }

    public static Bitmap getImage(Uri uri) throws FileNotFoundException {
        return getImage(uri, 960, 960);
    }

    public static Bitmap getImage(Uri uri, int i, int i2) throws FileNotFoundException {
        float f;
        float f2;
        int i3 = i > i2 ? i : i2;
        if (i > i2) {
            i = i2;
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(ArkValue.gContext.getContentResolver().openInputStream(uri), null, options);
        options.inJustDecodeBounds = false;
        int i4 = options.outWidth;
        int i5 = options.outHeight;
        if (i4 > i5) {
            f2 = i;
            f = i3;
        } else {
            float f3 = i3;
            f = i;
            f2 = f3;
        }
        float f4 = i4 / f;
        float f5 = i5 / f2;
        int iCeil = (int) Math.ceil(f4 > f5 ? f4 : f5);
        L.error(Registry.BUCKET_BITMAP, "getImage,beW " + f4 + ";beH " + f5 + ";be:" + iCeil);
        options.inSampleSize = iCeil;
        return BitmapFactory.decodeStream(ArkValue.gContext.getContentResolver().openInputStream(uri), null, options);
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x005d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static android.graphics.Bitmap comp(android.graphics.Bitmap r8) {
        /*
            java.io.ByteArrayOutputStream r0 = new java.io.ByteArrayOutputStream
            r0.<init>()
            android.graphics.Bitmap$CompressFormat r1 = android.graphics.Bitmap.CompressFormat.JPEG
            r2 = 100
            r8.compress(r1, r2, r0)
            byte[] r1 = r0.toByteArray()
            int r1 = r1.length
            r2 = 1024(0x400, float:1.435E-42)
            int r1 = r1 / r2
            if (r1 <= r2) goto L20
            r0.reset()
            android.graphics.Bitmap$CompressFormat r1 = android.graphics.Bitmap.CompressFormat.JPEG
            r2 = 50
            r8.compress(r1, r2, r0)
        L20:
            java.io.ByteArrayInputStream r8 = new java.io.ByteArrayInputStream
            byte[] r1 = r0.toByteArray()
            r8.<init>(r1)
            android.graphics.BitmapFactory$Options r1 = new android.graphics.BitmapFactory$Options
            r1.<init>()
            r2 = 1
            r1.inJustDecodeBounds = r2
            r3 = 0
            android.graphics.BitmapFactory.decodeStream(r8, r3, r1)
            r8 = 0
            r1.inJustDecodeBounds = r8
            int r8 = r1.outWidth
            int r4 = r1.outHeight
            r5 = 1145569280(0x44480000, float:800.0)
            r6 = 1139802112(0x43f00000, float:480.0)
            if (r8 <= r4) goto L4d
            float r7 = (float) r8
            int r7 = (r7 > r6 ? 1 : (r7 == r6 ? 0 : -1))
            if (r7 <= 0) goto L4d
            int r8 = r1.outWidth
            float r8 = (float) r8
            float r8 = r8 / r6
        L4b:
            int r8 = (int) r8
            goto L5a
        L4d:
            if (r8 >= r4) goto L59
            float r8 = (float) r4
            int r8 = (r8 > r5 ? 1 : (r8 == r5 ? 0 : -1))
            if (r8 <= 0) goto L59
            int r8 = r1.outHeight
            float r8 = (float) r8
            float r8 = r8 / r5
            goto L4b
        L59:
            r8 = 1
        L5a:
            if (r8 > 0) goto L5d
            goto L5e
        L5d:
            r2 = r8
        L5e:
            r1.inSampleSize = r2
            java.io.ByteArrayInputStream r8 = new java.io.ByteArrayInputStream
            byte[] r0 = r0.toByteArray()
            r8.<init>(r0)
            android.graphics.Bitmap r8 = android.graphics.BitmapFactory.decodeStream(r8, r3, r1)
            android.graphics.Bitmap r8 = compressImage(r8)
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huya.berry.gamesdk.utils.AppUtils.comp(android.graphics.Bitmap):android.graphics.Bitmap");
    }

    public static String md5(String str) {
        try {
            return encryptMD5(str);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String encryptMD5(String str) throws Exception {
        return bytesToHexString(MessageDigest.getInstance(FeedBackConstants.KEY_LOG_MD5).digest(StringToBytes(str)));
    }

    public static String bytesToHexString(byte[] bArr) throws UnsupportedEncodingException {
        StringBuilder sb = new StringBuilder();
        if (bArr != null) {
            for (byte b : bArr) {
                sb.append(Integer.toString((b & 255) + 256, 16).substring(1));
            }
        }
        return sb.toString();
    }

    public static byte[] StringToBytes(String str) throws UnsupportedEncodingException {
        return str.getBytes("UTF-8");
    }

    public static String getVersion() {
        String appMetaData = getAppMetaData(ArkValue.gContext, "HY_VERSION");
        return TextUtils.isEmpty(appMetaData) ? "2.5.5" : appMetaData;
    }

    public static String getChannel() {
        String appMetaData = getAppMetaData(ArkValue.gContext, "HY_CHANNEL");
        return TextUtils.isEmpty(appMetaData) ? "dev" : appMetaData;
    }

    public static boolean isSnapshot() {
        return getVersion().endsWith("SNAPSHOT");
    }

    public static String getDeviceData() {
        return Build.MODEL + "|" + Build.BRAND + "|" + Build.VERSION.RELEASE;
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
}
