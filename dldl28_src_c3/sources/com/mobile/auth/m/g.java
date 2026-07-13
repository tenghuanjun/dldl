package com.mobile.auth.m;

import android.content.Context;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public class g {
    public static boolean a(Context context, String str) {
        return context.getPackageManager().checkPermission(str, context.getPackageName()) == 0;
    }
}
