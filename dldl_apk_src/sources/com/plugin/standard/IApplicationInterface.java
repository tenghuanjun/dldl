package com.plugin.standard;

import android.app.Application;
import android.content.Context;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public interface IApplicationInterface {
    void attachBaseContext(Context context);

    Context getApplicationContext();

    void insertAppContext(Application application);

    void onCreate();
}
