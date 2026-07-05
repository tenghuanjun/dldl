package com.sqwan.common.util;

import android.app.Activity;
import android.content.Context;
import com.sq.tool.logger.SQLog;
import java.lang.reflect.Field;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public final class SQContextWrapper {
    private static Context sAppContext;

    private SQContextWrapper() {
    }

    public static void init(Context context) {
        sAppContext = context.getApplicationContext() != null ? context.getApplicationContext() : context;
        hookOldContext(context);
    }

    public static void init(Activity activity) {
        sAppContext = activity.getApplicationContext();
        SqAtyRef.getInstance().init(activity);
        com.sq.sdk.tool.util.SQContextWrapper.init(activity);
    }

    public static Activity getActivity() {
        return SqAtyRef.getInstance().getActivity();
    }

    public static Context getApplicationContext() {
        return sAppContext;
    }

    private static void hookOldContext(Context context) {
        try {
            Field declaredField = com.sq.sdk.tool.util.SQContextWrapper.class.getDeclaredField("mAppContext");
            declaredField.setAccessible(true);
            declaredField.set(null, context);
        } catch (Throwable unused) {
            SQLog.w("设置com.sq.sdk.tool.util.SQContextWrapper context失败");
        }
    }
}
