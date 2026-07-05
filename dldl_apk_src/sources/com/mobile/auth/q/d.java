package com.mobile.auth.q;

import android.content.Context;
import com.mobile.auth.k.o;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes2.dex */
public class d {
    public static int a(Context context, int i) {
        try {
            try {
                com.mobile.auth.e.a.a(context).b(context);
                return Integer.parseInt(o.a(context).a(true));
            } catch (Exception e) {
                e.toString();
                return i;
            }
        } catch (Throwable th) {
            try {
                com.mobile.auth.gatewayauth.a.a(th);
                return -1;
            } catch (Throwable th2) {
                com.mobile.auth.gatewayauth.a.a(th2);
                return -1;
            }
        }
    }

    public static String a(Context context) {
        try {
            try {
                com.mobile.auth.e.a.a(context).b(context);
                return o.a(context).c();
            } catch (Exception e) {
                e.toString();
                return null;
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
