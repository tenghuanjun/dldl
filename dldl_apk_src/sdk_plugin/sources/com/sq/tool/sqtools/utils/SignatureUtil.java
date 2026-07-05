package com.sq.tool.sqtools.utils;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.Signature;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class SignatureUtil {
    public static String getSignature(Context context) {
        try {
            Signature[] signatureArr = context.getPackageManager().getPackageInfo(context.getPackageName(), 64).signatures;
            if (signatureArr != null && signatureArr.length != 0) {
                StringBuilder sb = new StringBuilder();
                for (Signature signature : signatureArr) {
                    sb.append(signature.toCharsString());
                }
                return sb.toString();
            }
            return "";
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "";
        }
    }
}
