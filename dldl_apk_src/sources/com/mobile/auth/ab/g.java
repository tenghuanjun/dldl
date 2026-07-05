package com.mobile.auth.ab;

import android.content.Context;
import android.content.SharedPreferences;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes2.dex */
public class g {
    public static String a(Context context) {
        try {
            return b(context, "auth02");
        } catch (Throwable th) {
            try {
                com.mobile.auth.gatewayauth.a.a(th);
                return null;
            } catch (Throwable th2) {
                com.mobile.auth.gatewayauth.a.a(th2);
                return null;
            }
        }
    }

    public static void a(Context context, String str) {
        try {
            a(context, "auth02", str);
        } catch (Throwable th) {
            try {
                com.mobile.auth.gatewayauth.a.a(th);
            } catch (Throwable th2) {
                com.mobile.auth.gatewayauth.a.a(th2);
            }
        }
    }

    public static void a(Context context, String str, String str2) {
        try {
            try {
                SharedPreferences.Editor editorEdit = context.getSharedPreferences("cu_auth", 0).edit();
                editorEdit.putString(str, str2);
                editorEdit.commit();
            } catch (Exception unused) {
                d.b("save sp " + str + " error!");
            }
        } catch (Throwable th) {
            try {
                com.mobile.auth.gatewayauth.a.a(th);
            } catch (Throwable th2) {
                com.mobile.auth.gatewayauth.a.a(th2);
            }
        }
    }

    public static String b(Context context, String str) {
        try {
            try {
                return context.getSharedPreferences("cu_auth", 0).getString(str, "");
            } catch (Exception unused) {
                d.b("get sp " + str + " error!");
                a(context, str, "");
                return "";
            }
        } catch (Throwable th) {
            try {
                com.mobile.auth.gatewayauth.a.a(th);
                return null;
            } catch (Throwable th2) {
                com.mobile.auth.gatewayauth.a.a(th2);
                return null;
            }
        }
    }
}
