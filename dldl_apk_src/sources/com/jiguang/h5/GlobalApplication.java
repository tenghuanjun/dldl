package com.jiguang.h5;

import android.app.Application;
import android.content.Context;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class GlobalApplication extends Application {
    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
    }

    @Override // android.content.ContextWrapper
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
    }
}
