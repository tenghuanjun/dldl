package com.social.sdk.common.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class AppInstallUtils {
    public static boolean checkAppInstalled(Context context, String str) {
        List<PackageInfo> installedPackages = context.getPackageManager().getInstalledPackages(0);
        for (int i = 0; i < installedPackages.size(); i++) {
            if (installedPackages.get(i).packageName.equalsIgnoreCase(str)) {
                return true;
            }
        }
        return false;
    }
}
