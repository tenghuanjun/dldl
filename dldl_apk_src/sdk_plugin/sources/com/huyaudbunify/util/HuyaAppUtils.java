package com.huyaudbunify.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import java.security.MessageDigest;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class HuyaAppUtils {
    public static boolean isInstallHuya(Context context) {
        List<PackageInfo> installedPackages = context.getPackageManager().getInstalledPackages(0);
        if (installedPackages != null) {
            for (byte b = 0; b < installedPackages.size(); b = (byte) (b + 1)) {
                if (installedPackages.get(b).packageName.equals("com.duowan.kiwi")) {
                    return true;
                }
            }
        }
        return false;
    }

    public static String getAppVersion(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String getPackageName(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).packageName;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String getSignatureString(Context context) {
        try {
            return md5(context.getPackageManager().getPackageInfo(context.getPackageName(), 64).signatures[0].toByteArray());
        } catch (Throwable unused) {
            return "";
        }
    }

    public static String getAuthEncryptedData(String str, String str2, String str3) {
        return md5(String.format("%s_%s_%s", str, str2, str3).getBytes());
    }

    private static String md5(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer();
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(bArr);
            byte[] bArrDigest = messageDigest.digest();
            for (int i = 0; i < bArrDigest.length; i++) {
                if (Integer.toHexString(bArrDigest[i] & 255).length() == 1) {
                    stringBuffer.append("0");
                    stringBuffer.append(Integer.toHexString(bArrDigest[i] & 255));
                } else {
                    stringBuffer.append(Integer.toHexString(bArrDigest[i] & 255));
                }
            }
        } catch (Throwable unused) {
        }
        return stringBuffer.toString();
    }
}
