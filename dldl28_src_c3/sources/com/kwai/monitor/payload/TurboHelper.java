package com.kwai.monitor.payload;

import android.content.Context;
import java.io.File;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public final class TurboHelper {
    public static boolean a;
    public static String b;

    public static String getChannel(Context context) {
        if (!a) {
            a = true;
            try {
                b = b.a(new File(context.getApplicationInfo().sourceDir));
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        String str = b;
        return str != null ? str : "";
    }
}
