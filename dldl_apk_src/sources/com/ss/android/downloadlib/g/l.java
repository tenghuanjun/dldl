package com.ss.android.downloadlib.g;

import android.text.TextUtils;
import android.util.Log;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes.dex */
public class l {

    private static class a {
        private static l a = new l();
    }

    public static l a() {
        return a.a;
    }

    private l() {
    }

    public void a(String str, String str2, String str3) {
        Log.d("[TTDownloaderLogger]", ((TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) ? "" : String.format("sdk:%s.%s:", str, str2)) + str3);
    }

    public void b(String str, String str2, String str3) {
        Log.e("[TTDownloaderLogger]", ((TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) ? "" : String.format("sdk:%s.%s:", str, str2)) + str3);
    }
}
