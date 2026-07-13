package com.mt.w;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public class ChannelManager {
    private ChannelManager() {
    }

    private static String get(Context context, String str) {
        Map<String, String> channelInfoMap = getChannelInfoMap(context);
        if (channelInfoMap == null) {
            return null;
        }
        return channelInfoMap.get(str);
    }

    private static String getApkPath(Context context) {
        try {
            ApplicationInfo applicationInfo = context.getApplicationInfo();
            if (applicationInfo == null) {
                return null;
            }
            return applicationInfo.sourceDir;
        } catch (Throwable unused) {
            return null;
        }
    }

    private static String getChannel(Context context) {
        return getChannel(context, null);
    }

    private static String getChannel(Context context, String str) {
        a channelInfo = getChannelInfo(context);
        return channelInfo == null ? str : channelInfo.a;
    }

    private static a getChannelInfo(Context context) {
        Map<String, String> mapA;
        String apkPath = getApkPath(context);
        if (TextUtils.isEmpty(apkPath) || (mapA = d.a(new File(apkPath))) == null) {
            return null;
        }
        String str = mapA.get("channel");
        mapA.remove("channel");
        return new a(str, mapA);
    }

    private static Map<String, String> getChannelInfoMap(Context context) {
        String apkPath = getApkPath(context);
        if (TextUtils.isEmpty(apkPath)) {
            return null;
        }
        return d.a(new File(apkPath));
    }

    private static String getChannelV1(Context context) {
        return getStringV1(context, "gamechannel");
    }

    public static String getExtra(Context context) {
        return getExtra(context, "channelkey", "");
    }

    public static String getExtra(Context context, String str) {
        return getExtra(context, "channelkey", str);
    }

    public static String getExtra(Context context, String str, String str2) throws Throwable {
        String stringV1 = get(context, str);
        if (TextUtils.isEmpty(stringV1)) {
            stringV1 = getStringV1(context, str);
        }
        return TextUtils.isEmpty(stringV1) ? str2 : stringV1;
    }

    public static String getInfo(Context context) {
        return getInfo(context, "");
    }

    public static String getInfo(Context context, String str) {
        String channel = getChannel(context);
        if (TextUtils.isEmpty(channel)) {
            channel = getChannelV1(context);
        }
        return TextUtils.isEmpty(channel) ? str : channel;
    }

    public static List<String> getMetaData(Context context, String... strArr) {
        ArrayList arrayList = new ArrayList();
        try {
            Bundle bundle = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData;
            if (bundle != null) {
                for (String str : strArr) {
                    arrayList.add(TextUtils.isEmpty(bundle.getString(str)) ? bundle.getInt(str) + "" : bundle.getString(str));
                }
            }
            return arrayList;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return arrayList;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x003b, code lost:
    
        r2.close();
        r1 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x003f, code lost:
    
        r5 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0040, code lost:
    
        r5.printStackTrace();
        r1 = r1;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0 */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v17 */
    /* JADX WARN: Type inference failed for: r1v18 */
    /* JADX WARN: Type inference failed for: r1v2, types: [java.util.zip.ZipFile] */
    /* JADX WARN: Type inference failed for: r1v4 */
    /* JADX WARN: Type inference failed for: r1v5, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v7 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String getStringV1(android.content.Context r5, java.lang.String r6) throws java.lang.Throwable {
        /*
            android.content.pm.ApplicationInfo r5 = r5.getApplicationInfo()
            java.lang.String r5 = r5.sourceDir
            java.lang.String r0 = ""
            r1 = 0
            java.util.zip.ZipFile r2 = new java.util.zip.ZipFile     // Catch: java.lang.Throwable -> L4a java.io.IOException -> L4c
            r2.<init>(r5)     // Catch: java.lang.Throwable -> L4a java.io.IOException -> L4c
            java.util.Enumeration r5 = r2.entries()     // Catch: java.lang.Throwable -> L44 java.io.IOException -> L47
        L12:
            boolean r1 = r5.hasMoreElements()     // Catch: java.lang.Throwable -> L44 java.io.IOException -> L47
            if (r1 == 0) goto L3a
            java.lang.Object r1 = r5.nextElement()     // Catch: java.lang.Throwable -> L44 java.io.IOException -> L47
            java.util.zip.ZipEntry r1 = (java.util.zip.ZipEntry) r1     // Catch: java.lang.Throwable -> L44 java.io.IOException -> L47
            java.lang.String r1 = r1.getName()     // Catch: java.lang.Throwable -> L44 java.io.IOException -> L47
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L44 java.io.IOException -> L47
            r3.<init>()     // Catch: java.lang.Throwable -> L44 java.io.IOException -> L47
            java.lang.String r4 = "META-INF/"
            r3.append(r4)     // Catch: java.lang.Throwable -> L44 java.io.IOException -> L47
            r3.append(r6)     // Catch: java.lang.Throwable -> L44 java.io.IOException -> L47
            java.lang.String r3 = r3.toString()     // Catch: java.lang.Throwable -> L44 java.io.IOException -> L47
            boolean r3 = r1.startsWith(r3)     // Catch: java.lang.Throwable -> L44 java.io.IOException -> L47
            if (r3 == 0) goto L12
            goto L3b
        L3a:
            r1 = r0
        L3b:
            r2.close()     // Catch: java.io.IOException -> L3f
            goto L5b
        L3f:
            r5 = move-exception
            r5.printStackTrace()
            goto L5b
        L44:
            r5 = move-exception
            r1 = r2
            goto L6c
        L47:
            r5 = move-exception
            r1 = r2
            goto L4d
        L4a:
            r5 = move-exception
            goto L6c
        L4c:
            r5 = move-exception
        L4d:
            r5.printStackTrace()     // Catch: java.lang.Throwable -> L4a
            if (r1 == 0) goto L5a
            r1.close()     // Catch: java.io.IOException -> L56
            goto L5a
        L56:
            r5 = move-exception
            r5.printStackTrace()
        L5a:
            r1 = r0
        L5b:
            java.lang.String r5 = "_"
            java.lang.String[] r5 = r1.split(r5)
            if (r5 == 0) goto L6b
            int r6 = r5.length
            r1 = 2
            if (r6 < r1) goto L6b
            r6 = 1
            r5 = r5[r6]
            return r5
        L6b:
            return r0
        L6c:
            if (r1 == 0) goto L76
            r1.close()     // Catch: java.io.IOException -> L72
            goto L76
        L72:
            r6 = move-exception
            r6.printStackTrace()
        L76:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mt.w.ChannelManager.getStringV1(android.content.Context, java.lang.String):java.lang.String");
    }
}
