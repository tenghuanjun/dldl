package com.bun.miitmdid;

import android.app.Activity;
import android.content.Context;
import androidx.core.app.ActivityCompat;

/* JADX INFO: loaded from: classes2.dex */
public class p0 {
    public static boolean a(Activity activity, String str) {
        return ActivityCompat.shouldShowRequestPermissionRationale(activity, str);
    }

    public static native boolean a(Context context, String... strArr);

    public static native boolean a(int[] iArr);
}
