package com.duowan.auk.util;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class VersionUtil {
    private static final String DOT = ".";
    static String sLocalName;
    static int[] sLocalVer;

    public static Ver getVerFromStr(String str) {
        if (!str.matches("\\d{1,}\\.\\d{1,}\\.\\d{1,}")) {
            return null;
        }
        Ver ver = new Ver();
        int iIndexOf = str.indexOf(".");
        ver.mMajor = Integer.valueOf(str.substring(0, iIndexOf)).intValue();
        int i = iIndexOf + 1;
        int iIndexOf2 = str.indexOf(".", i);
        ver.mMinor = Integer.valueOf(str.substring(i, iIndexOf2)).intValue();
        ver.mBuild = Integer.valueOf(str.substring(iIndexOf2 + 1)).intValue();
        return ver;
    }

    public static Ver getLocalVer(Context context) {
        Ver ver = new Ver();
        int[] local = getLocal(context);
        ver.mMajor = local[0];
        ver.mMinor = local[1];
        ver.mBuild = local[2];
        return ver;
    }

    public static String getLocalName(Context context) {
        String str = sLocalName;
        if (str != null) {
            return str;
        }
        loadLocalVer(context);
        return sLocalName;
    }

    public static int[] getLocal(Context context) {
        int[] iArr = sLocalVer;
        if (iArr != null) {
            return iArr;
        }
        loadLocalVer(context);
        return sLocalVer;
    }

    static void loadLocalVer(Context context) {
        try {
            String str = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
            sLocalName = str;
            if (str == null) {
                throw new RuntimeException("Local Ver VersionName Not Exist");
            }
            int iIndexOf = str.indexOf(45);
            if (iIndexOf != -1) {
                sLocalName = sLocalName.substring(0, iIndexOf);
            }
            String[] strArrSplit = sLocalName.split("\\.");
            int length = strArrSplit.length;
            sLocalVer = new int[length];
            for (int i = 0; i < length; i++) {
                try {
                    sLocalVer[i] = Integer.parseInt(strArrSplit[i]);
                } catch (NumberFormatException unused) {
                    throw new RuntimeException("Local Ver VersionName Error");
                }
            }
        } catch (PackageManager.NameNotFoundException unused2) {
            throw new RuntimeException("Local Ver Package Error");
        }
    }

    public static boolean before(int i) {
        return Build.VERSION.SDK_INT < i;
    }

    public static boolean after(int i) {
        return Build.VERSION.SDK_INT > i;
    }

    public static boolean isSupportNestedFragment() {
        return after(16);
    }
}
