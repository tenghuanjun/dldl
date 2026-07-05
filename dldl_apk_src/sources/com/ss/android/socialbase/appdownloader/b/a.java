package com.ss.android.socialbase.appdownloader.b;

import android.os.Build;
import android.text.TextUtils;
import com.alipay.sdk.util.i;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes.dex */
public class a {
    public static final String a;

    static {
        StringBuilder sb = new StringBuilder();
        boolean z = !TextUtils.isEmpty(Build.VERSION.RELEASE);
        boolean z2 = !TextUtils.isEmpty(Build.ID);
        boolean z3 = "REL".equals(Build.VERSION.CODENAME) && !TextUtils.isEmpty(Build.MODEL);
        sb.append("AppDownloader");
        if (z) {
            sb.append("/");
            sb.append(Build.VERSION.RELEASE);
        }
        sb.append(" (Linux; U; Android");
        if (z) {
            sb.append(" ");
            sb.append(Build.VERSION.RELEASE);
        }
        if (z3 || z2) {
            sb.append(i.b);
            if (z3) {
                sb.append(" ");
                sb.append(Build.MODEL);
            }
            if (z2) {
                sb.append(" Build/");
                sb.append(Build.ID);
            }
        }
        sb.append(")");
        a = sb.toString();
    }
}
