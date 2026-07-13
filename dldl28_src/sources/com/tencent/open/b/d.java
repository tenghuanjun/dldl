package com.tencent.open.b;

import android.content.Context;
import android.text.TextUtils;
import android.view.WindowManager;
import java.util.Locale;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes3.dex */
public class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static String f1052a;
    private static String b;

    public static String a(Context context) {
        if (!TextUtils.isEmpty(f1052a)) {
            return f1052a;
        }
        if (context == null) {
            return "";
        }
        f1052a = "";
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        if (windowManager != null) {
            f1052a = windowManager.getDefaultDisplay().getWidth() + "x" + windowManager.getDefaultDisplay().getHeight();
        }
        return f1052a;
    }

    public static String a() {
        return Locale.getDefault().getLanguage();
    }
}
