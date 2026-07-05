package com.unicom.online.account.kernel;

import android.content.Context;
import android.content.SharedPreferences;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes2.dex */
public final class g {
    public static String a(Context context, String str) {
        try {
            return context.getSharedPreferences("cu_auth", 0).getString(str, "");
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static void a(Context context, String str, String str2) {
        try {
            SharedPreferences.Editor editorEdit = context.getSharedPreferences("cu_auth", 0).edit();
            editorEdit.putString(str, str2);
            editorEdit.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
