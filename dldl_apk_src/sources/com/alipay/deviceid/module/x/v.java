package com.alipay.deviceid.module.x;

import android.content.Context;
import android.os.Build;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public final class v {
    public static synchronized void a(Context context, String str, String str2, String str3) {
        s sVar = new s(Build.MODEL, context.getApplicationContext().getApplicationInfo().packageName, "APPSecuritySDK-FC", "6.0.7.20211109", str, str2, str3);
        u.a(context.getFilesDir().getAbsolutePath() + "/log/ap", new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime()) + ".log", sVar.toString());
    }

    public static synchronized void a(String str) {
        u.a(str);
    }

    public static synchronized void a(Throwable th) {
        u.a(th);
    }
}
