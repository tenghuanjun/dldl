package com.tencent.open.utils;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.Signature;
import android.os.Build;
import android.os.Environment;
import com.tencent.connect.common.Constants;
import com.tencent.open.log.SLog;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.security.MessageDigest;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public class k {
    private static ConcurrentHashMap<String, com.tencent.a.a.a> a = new ConcurrentHashMap<>();

    public static void a() {
        a.clear();
    }

    public static void a(String str) {
        if (str == null) {
            return;
        }
        a.remove(str);
    }

    public static String a(Context context, String str) {
        String strA = h.a(context, c(), str);
        if (strA != null && !Constants.APP_VERSION_UNKNOWN.equals(strA)) {
            return strA;
        }
        PackageInfo packageInfoE = e(context, str);
        if (packageInfoE == null) {
            SLog.e("openSDK_LOG.SystemUtils", "getAppVersionName return null. package= " + str);
            return null;
        }
        return packageInfoE.versionName;
    }

    private static PackageInfo e(Context context, String str) {
        if (context == null || str == null) {
            return null;
        }
        synchronized (k.class) {
            if (a.containsKey(str)) {
                com.tencent.a.a.a aVar = a.get(str);
                if (aVar == null) {
                    SLog.e("openSDK_LOG.SystemUtils", "getTargetPackageInfo wrapper is null");
                    return null;
                }
                PackageInfo packageInfo = aVar.b;
                if (packageInfo == null) {
                    SLog.e("openSDK_LOG.SystemUtils", "getTargetPackageInfo wrapper packageInfo is null");
                }
                return packageInfo;
            }
            PackageInfo packageInfoF = f(context, str);
            a.put(str, new com.tencent.a.a.a(str, packageInfoF));
            return packageInfoF;
        }
    }

    private static PackageInfo f(Context context, String str) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(str, 0);
            if (packageInfo == null) {
                SLog.e("openSDK_LOG.SystemUtils", "realGetPackageInfo null. packageName= " + str);
            }
            return packageInfo;
        } catch (Exception e) {
            SLog.e("openSDK_LOG.SystemUtils", "realGetPackageInfo exception", e);
            return null;
        }
    }

    public static int a(String str, String str2) {
        if (str == null && str2 == null) {
            return 0;
        }
        if (str != null && str2 == null) {
            return 1;
        }
        if (str == null && str2 != null) {
            return -1;
        }
        String[] strArrSplit = str.split("\\.");
        String[] strArrSplit2 = str2.split("\\.");
        int i = 0;
        while (i < strArrSplit.length && i < strArrSplit2.length) {
            try {
                int i2 = Integer.parseInt(strArrSplit[i]);
                int i3 = Integer.parseInt(strArrSplit2[i]);
                if (i2 < i3) {
                    return -1;
                }
                if (i2 > i3) {
                    return 1;
                }
                i++;
            } catch (NumberFormatException unused) {
                return str.compareTo(str2);
            }
        }
        if (strArrSplit.length > i) {
            return 1;
        }
        return strArrSplit2.length > i ? -1 : 0;
    }

    public static boolean a(Context context, String str, String str2) {
        SLog.v("openSDK_LOG.SystemUtils", "OpenUi, validateAppSignatureForPackage");
        try {
            for (Signature signature : context.getPackageManager().getPackageInfo(str, 64).signatures) {
                if (m.g(signature.toCharsString()).equals(str2)) {
                    return true;
                }
            }
        } catch (PackageManager.NameNotFoundException unused) {
        }
        return false;
    }

    public static String b(Context context, String str) {
        SLog.v("openSDK_LOG.SystemUtils", "OpenUi, getSignValidString");
        String strA = "";
        try {
            String packageName = context.getPackageName();
            Signature[] signatureArr = context.getPackageManager().getPackageInfo(packageName, 64).signatures;
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(signatureArr[0].toByteArray());
            String strA2 = m.a(messageDigest.digest());
            messageDigest.reset();
            SLog.v("openSDK_LOG.SystemUtils", "-->sign: " + strA2);
            messageDigest.update(m.j(packageName + "_" + strA2 + "_" + str + ""));
            strA = m.a(messageDigest.digest());
            messageDigest.reset();
            StringBuilder sb = new StringBuilder("-->signEncryped: ");
            sb.append(strA);
            SLog.v("openSDK_LOG.SystemUtils", sb.toString());
            return strA;
        } catch (Exception e) {
            SLog.e("openSDK_LOG.SystemUtils", "OpenUi, getSignValidString error", e);
            return strA;
        }
    }

    public static String a(Activity activity, String str) {
        if (activity == null) {
            SLog.e("openSDK_LOG.SystemUtils", "getEncryptPkgName activity==null !!!!!!");
            return "";
        }
        try {
            byte[] bArrA = e.a(str);
            if (bArrA == null) {
                SLog.e("openSDK_LOG.SystemUtils", "getEncryptPkgName shaBytes==null !!!!!!");
                return "";
            }
            byte[] bArr = new byte[8];
            System.arraycopy(bArrA, 5, bArr, 0, 8);
            byte[] bArr2 = new byte[16];
            System.arraycopy(bArrA, 8, bArr2, 0, 16);
            return e.a(activity.getPackageName(), e.a(bArr2), bArr);
        } catch (Exception e) {
            SLog.e("openSDK_LOG.SystemUtils", "getEncryptPkgName", e);
            return "";
        }
    }

    public static boolean a(Context context, Intent intent) {
        if (context == null || intent == null) {
            StringBuilder sb = new StringBuilder("isActivityExist params error! [");
            sb.append(context == null);
            sb.append(",");
            sb.append(intent == null);
            sb.append("]");
            SLog.e("openSDK_LOG.SystemUtils", sb.toString());
            return false;
        }
        List<ResolveInfo> listQueryIntentActivities = context.getPackageManager().queryIntentActivities(intent, 0);
        boolean z = listQueryIntentActivities != null && listQueryIntentActivities.size() > 0;
        if (!z) {
            StringBuilder sb2 = new StringBuilder("isActivityExist false. result=");
            sb2.append(listQueryIntentActivities == null ? "null" : Integer.valueOf(listQueryIntentActivities.size()));
            sb2.append(" Intent= ");
            sb2.append(intent);
            SLog.e("openSDK_LOG.SystemUtils", sb2.toString());
        }
        return z;
    }

    public static boolean b(Context context, Intent intent) {
        boolean z = false;
        if (context != null && intent != null) {
            ComponentName component = intent.getComponent();
            if (component == null) {
                SLog.i("openSDK_LOG.SystemUtils", "isAgentActivityExist? component null");
                return false;
            }
            String packageName = component.getPackageName();
            String strA = a(context, packageName);
            if (strA != null && !strA.isEmpty()) {
                z = true;
            }
            SLog.i("openSDK_LOG.SystemUtils", "isAgentActivityExist? packageName = " + packageName + ", appVersionName= " + strA);
        }
        return z;
    }

    public static String a(Context context) {
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        try {
            return applicationInfo.loadLabel(context.getPackageManager()).toString();
        } catch (Throwable th) {
            SLog.e("openSDK_LOG.SystemUtils", "getAppName exception", th);
            try {
                int i = applicationInfo.labelRes;
                return i <= 0 ? applicationInfo.nonLocalizedLabel.toString() : context.getString(i);
            } catch (Throwable th2) {
                SLog.e("openSDK_LOG.SystemUtils", "getAppName getLabel exception", th2);
                return "";
            }
        }
    }

    public static int c(Context context, String str) {
        return a(a(context, "com.tencent.mobileqq"), str);
    }

    public static int d(Context context, String str) {
        return a(a(context, Constants.PACKAGE_TIM), str);
    }

    /* JADX WARN: Removed duplicated region for block: B:64:0x00bf A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00b8 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:76:? A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean a(java.lang.String r10, java.lang.String r11, int r12) {
        /*
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "-->extractSecureLib, libName: "
            r0.<init>(r1)
            r0.append(r10)
            java.lang.String r0 = r0.toString()
            java.lang.String r1 = "openSDK_LOG.SystemUtils"
            com.tencent.open.log.SLog.i(r1, r0)
            android.content.Context r0 = com.tencent.open.utils.g.a()
            r2 = 0
            if (r0 != 0) goto L20
            java.lang.String r10 = "-->extractSecureLib, global context is null. "
            com.tencent.open.log.SLog.i(r1, r10)
            return r2
        L20:
            java.lang.String r3 = "secure_lib"
            android.content.SharedPreferences r3 = r0.getSharedPreferences(r3, r2)
            java.io.File r4 = new java.io.File
            java.io.File r5 = r0.getFilesDir()
            r4.<init>(r5, r11)
            boolean r5 = r4.exists()
            r6 = 1
            java.lang.String r7 = "version"
            if (r5 != 0) goto L4d
            java.io.File r5 = r4.getParentFile()
            if (r5 == 0) goto L6d
            boolean r5 = r5.mkdirs()
            if (r5 == 0) goto L6d
            r4.createNewFile()     // Catch: java.io.IOException -> L48
            goto L6d
        L48:
            r4 = move-exception
            r4.printStackTrace()
            goto L6d
        L4d:
            int r4 = r3.getInt(r7, r2)
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            java.lang.String r8 = "-->extractSecureLib, libVersion: "
            r5.<init>(r8)
            r5.append(r12)
            java.lang.String r8 = " | oldVersion: "
            r5.append(r8)
            r5.append(r4)
            java.lang.String r5 = r5.toString()
            com.tencent.open.log.SLog.i(r1, r5)
            if (r12 != r4) goto L6d
            return r6
        L6d:
            r4 = 0
            android.content.res.AssetManager r5 = r0.getAssets()     // Catch: java.lang.Throwable -> L9e java.lang.Exception -> La1
            java.io.InputStream r10 = r5.open(r10)     // Catch: java.lang.Throwable -> L9e java.lang.Exception -> La1
            java.io.FileOutputStream r4 = r0.openFileOutput(r11, r2)     // Catch: java.lang.Throwable -> L94 java.lang.Exception -> L99
            a(r10, r4)     // Catch: java.lang.Throwable -> L94 java.lang.Exception -> L99
            android.content.SharedPreferences$Editor r11 = r3.edit()     // Catch: java.lang.Throwable -> L94 java.lang.Exception -> L99
            r11.putInt(r7, r12)     // Catch: java.lang.Throwable -> L94 java.lang.Exception -> L99
            r11.commit()     // Catch: java.lang.Throwable -> L94 java.lang.Exception -> L99
            if (r10 == 0) goto L8e
            r10.close()     // Catch: java.io.IOException -> L8d
            goto L8e
        L8d:
        L8e:
            if (r4 == 0) goto L93
            r4.close()     // Catch: java.io.IOException -> L93
        L93:
            return r6
        L94:
            r11 = move-exception
            r9 = r4
            r4 = r10
            r10 = r9
            goto Lb6
        L99:
            r11 = move-exception
            r9 = r4
            r4 = r10
            r10 = r9
            goto La3
        L9e:
            r11 = move-exception
            r10 = r4
            goto Lb6
        La1:
            r11 = move-exception
            r10 = r4
        La3:
            java.lang.String r12 = "-->extractSecureLib, when copy lib execption."
            com.tencent.open.log.SLog.e(r1, r12, r11)     // Catch: java.lang.Throwable -> Lb5
            if (r4 == 0) goto Laf
            r4.close()     // Catch: java.io.IOException -> Lae
            goto Laf
        Lae:
        Laf:
            if (r10 == 0) goto Lb4
            r10.close()     // Catch: java.io.IOException -> Lb4
        Lb4:
            return r2
        Lb5:
            r11 = move-exception
        Lb6:
            if (r4 == 0) goto Lbd
            r4.close()     // Catch: java.io.IOException -> Lbc
            goto Lbd
        Lbc:
        Lbd:
            if (r10 == 0) goto Lc2
            r10.close()     // Catch: java.io.IOException -> Lc2
        Lc2:
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.open.utils.k.a(java.lang.String, java.lang.String, int):boolean");
    }

    private static long a(InputStream inputStream, OutputStream outputStream) throws IOException {
        byte[] bArr = new byte[8192];
        long j = 0;
        while (true) {
            int i = inputStream.read(bArr, 0, 8192);
            if (i != -1) {
                outputStream.write(bArr, 0, i);
                j += (long) i;
            } else {
                SLog.i("openSDK_LOG.SystemUtils", "-->copy, copyed size is: " + j);
                return j;
            }
        }
    }

    public static int b(String str) {
        if ("shareToQQ".equals(str)) {
            return Constants.REQUEST_QQ_SHARE;
        }
        if ("shareToQzone".equals(str)) {
            return Constants.REQUEST_QZONE_SHARE;
        }
        if ("addToQQFavorites".equals(str)) {
            return Constants.REQUEST_QQ_FAVORITES;
        }
        if ("sendToMyComputer".equals(str)) {
            return Constants.REQUEST_SEND_TO_MY_COMPUTER;
        }
        if ("shareToTroopBar".equals(str)) {
            return Constants.REQUEST_SHARE_TO_TROOP_BAR;
        }
        if ("action_login".equals(str)) {
            return Constants.REQUEST_LOGIN;
        }
        if ("action_request".equals(str)) {
            return Constants.REQUEST_API;
        }
        return -1;
    }

    public static String a(int i) {
        if (i == 10103) {
            return "shareToQQ";
        }
        if (i == 10104) {
            return "shareToQzone";
        }
        if (i == 10105) {
            return "addToQQFavorites";
        }
        if (i == 10106) {
            return "sendToMyComputer";
        }
        if (i == 10107) {
            return "shareToTroopBar";
        }
        if (i == 11101) {
            return "action_login";
        }
        if (i == 10100) {
            return "action_request";
        }
        if (i != 10114) {
            return null;
        }
        return "action_common_channel";
    }

    public static boolean b(Context context) {
        boolean zG = g(context, "com.tencent.mobileqq");
        SLog.i("openSDK_LOG.SystemUtils", "isQQInstalled " + zG);
        return zG;
    }

    public static boolean c(Context context) {
        if (g(context, "com.tencent.mobileqq")) {
            SLog.i("openSDK_LOG.SystemUtils", "isQQBranchInstalled: qq");
            return true;
        }
        if (g(context, Constants.PACKAGE_TIM)) {
            SLog.i("openSDK_LOG.SystemUtils", "isQQBranchInstalled: tim");
            return true;
        }
        if (g(context, Constants.PACKAGE_QQ_PAD)) {
            SLog.i("openSDK_LOG.SystemUtils", "isQQBranchInstalled: pad");
            return true;
        }
        SLog.i("openSDK_LOG.SystemUtils", "isQQBranchInstalled: disable speed");
        return false;
    }

    private static boolean g(Context context, String str) {
        return (h.a(context, c(), str) == null && e(context, str) == null) ? false : true;
    }

    public static String a(Activity activity) {
        try {
            ApplicationInfo applicationInfo = activity.getPackageManager().getApplicationInfo(activity.getApplicationContext().getPackageName(), 128);
            SLog.i("openSDK_LOG.SystemUtils", "apkPath=" + applicationInfo.sourceDir);
            return applicationInfo.sourceDir;
        } catch (PackageManager.NameNotFoundException e) {
            SLog.e("openSDK_LOG.SystemUtils", "NameNotFoundException", e);
            return null;
        } catch (Exception e2) {
            SLog.e("openSDK_LOG.SystemUtils", "Exception", e2);
            return null;
        }
    }

    public static boolean d(Context context) {
        return context != null && context.getApplicationInfo().targetSdkVersion >= 29 && Build.VERSION.SDK_INT >= 29 && !b();
    }

    private static boolean b() {
        try {
            return ((Boolean) Environment.class.getMethod("isExternalStorageLegacy", null).invoke(Environment.class, null)).booleanValue();
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException unused) {
            return false;
        }
    }

    private static String c() {
        String strB = com.tencent.open.b.b.b();
        if (strB == null || strB.isEmpty()) {
            SLog.e("openSDK_LOG.SystemUtils", "getAppId error: " + strB);
        }
        return strB;
    }
}
