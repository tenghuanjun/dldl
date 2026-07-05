package com.sq.sdk.tool.util;

import android.app.Activity;
import android.content.Context;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class SQContextWrapper {
    private static WeakReference<Activity> mActivityContext;
    private static Context mAppContext;

    public static void init(Activity activity) {
        mActivityContext = new WeakReference<>(activity);
        mAppContext = activity.getApplicationContext();
    }

    public static Context getApplicationContext() {
        return mAppContext;
    }

    public static Activity getActivity() {
        return mActivityContext.get();
    }
}
