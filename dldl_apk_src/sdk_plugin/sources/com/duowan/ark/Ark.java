package com.duowan.ark;

import android.app.Application;
import android.os.Build;
import android.os.Bundle;
import com.duowan.ark.module.ArkModule;
import com.duowan.ark.util.KLog;
import com.duowan.ark.util.Utils;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class Ark {
    public static void init(Application application, int i, boolean z) {
        loadAsyncTaskClass();
        ArkValue.init(application, i, z);
    }

    public static void init(Application application, int i) {
        init(application, i, false);
    }

    public static boolean startModule(Class<? extends ArkModule> cls) {
        return startModule(cls, new Bundle());
    }

    public static boolean startModule(Class<? extends ArkModule> cls, Bundle bundle) {
        KLog.info(Ark.class, "start module: %s", cls.getSimpleName());
        return ArkValue.startModule(cls, bundle);
    }

    public static boolean stopModule(Class<? extends ArkModule> cls) {
        KLog.info(Ark.class, "stop module: %s", cls.getSimpleName());
        return ArkValue.stopModule(cls);
    }

    private static void loadAsyncTaskClass() {
        if (Build.VERSION.SDK_INT <= 16) {
            try {
                Class.forName("android.os.AsyncTask");
            } catch (Throwable unused) {
                Utils.dwAssert(false);
            }
        }
    }
}
