package android.support.multidex;

import android.app.Application;
import android.content.Context;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes.dex */
public class MultiDexApplication extends Application {
    @Override // android.content.ContextWrapper
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
