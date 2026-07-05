package com.mobile.auth.k;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes2.dex */
public class q {
    public static byte[] a(Context context) {
        PackageInfo packageInfo;
        Signature[] signatureArr;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 64);
        } catch (PackageManager.NameNotFoundException unused) {
            packageInfo = null;
        }
        if (packageInfo == null || (signatureArr = packageInfo.signatures) == null || signatureArr.length <= 0) {
            return null;
        }
        return signatureArr[0].toByteArray();
    }
}
