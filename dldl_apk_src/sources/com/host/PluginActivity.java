package com.host;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import com.plugin.core.ProxyPluginActivity;
import com.plugin.core.loader.ApkClassLoader;
import com.sqwan.msdk.PluginLoader;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes.dex */
public class PluginActivity extends ProxyPluginActivity {
    @Override // com.plugin.core.ProxyPluginActivity, android.view.ContextThemeWrapper, android.content.ContextWrapper, android.content.Context
    public Resources getResources() {
        return PluginLoader.getInstance().get().mResource;
    }

    @Override // com.plugin.core.ProxyPluginActivity, android.content.ContextWrapper, android.content.Context
    public ApkClassLoader getClassLoader() {
        return PluginLoader.getInstance().get().mClassLoader;
    }

    @Override // com.plugin.core.ProxyPluginActivity, android.app.Activity, android.content.ContextWrapper, android.content.Context
    public void startActivity(Intent intent) {
        PluginExActivityHandler.getInstance().handlerActivityIntent(this, intent, getClassLoader());
        super.startActivity(intent);
    }

    @Override // android.app.Activity
    public void startActivityForResult(Intent intent, int i, Bundle bundle) {
        PluginExActivityHandler.getInstance().handlerActivityIntent(this, intent, getClassLoader());
        super.startActivityForResult(intent, i, bundle);
    }
}
