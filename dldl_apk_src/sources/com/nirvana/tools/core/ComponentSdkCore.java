package com.nirvana.tools.core;

import android.content.Context;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes6.dex */
public class ComponentSdkCore {
    protected static Context sApplicationContext;

    public static Context getApplicationContext() {
        return sApplicationContext;
    }

    public static void register(Context context) {
        sApplicationContext = context;
    }
}
