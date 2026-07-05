package com.sqwan.base;

import android.app.Activity;
import android.content.Context;
import com.sqwan.common.util.LogUtil;
import java.lang.ref.WeakReference;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class L {
    private static String TAG = "L";
    private static WeakReference<Activity> activityWeakReference;
    private static Context context;

    public static void init(Context context2) {
        if ((context2 instanceof Activity) && activityWeakReference == null) {
            activityWeakReference = new WeakReference<>((Activity) context2);
        }
        if (context == null) {
            context = context2.getApplicationContext();
        }
    }

    public static Activity getActivity() {
        LogUtil.i(TAG, "getActivity");
        WeakReference<Activity> weakReference = activityWeakReference;
        if (weakReference != null) {
            Activity activity = weakReference.get();
            LogUtil.i(TAG, "activityWeakReference activity:" + activity);
            return activity;
        }
        LogUtil.i(TAG, AbstractJsonLexerKt.NULL);
        return null;
    }

    public static Context getApplicationContext() {
        return context;
    }
}
