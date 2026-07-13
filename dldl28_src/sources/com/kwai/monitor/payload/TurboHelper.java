package com.kwai.monitor.payload;

import android.content.Context;
import java.io.File;

/* JADX INFO: loaded from: classes3.dex */
public final class TurboHelper {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static boolean f493a;
    public static String b;

    public static String getChannel(Context context) {
        if (!f493a) {
            f493a = true;
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
