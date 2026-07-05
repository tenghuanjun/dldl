package com.plugin.standard;

import android.app.Application;
import android.content.Context;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class BaseApplication implements IApplicationInterface {
    private Application mApplication;

    @Override // com.plugin.standard.IApplicationInterface
    public void attachBaseContext(Context context) {
    }

    @Override // com.plugin.standard.IApplicationInterface
    public void onCreate() {
    }

    @Override // com.plugin.standard.IApplicationInterface
    public void insertAppContext(Application application) {
        this.mApplication = application;
    }

    @Override // com.plugin.standard.IApplicationInterface
    public Context getApplicationContext() {
        return this.mApplication;
    }
}
