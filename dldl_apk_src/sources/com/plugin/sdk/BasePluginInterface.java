package com.plugin.sdk;

import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Resources;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class BasePluginInterface {
    public AssetManager getAssets(AssetManager assetManager) {
        return assetManager;
    }

    public ClassLoader getClassLoader(ClassLoader classLoader) {
        return classLoader;
    }

    public Resources getResources(Resources resources) {
        return resources;
    }

    public boolean isSupportPlugin() {
        return false;
    }

    public void startActivity(Intent intent) {
    }

    public void startActivityForResult(Intent intent, int i) {
    }
}
