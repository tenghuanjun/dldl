package com.sqwan.msdk.api;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.AssetManager;
import android.content.res.Resources;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class PluginContext extends ContextWrapper {
    private Resources mPluginResources;

    public PluginContext(Context context, Resources resources) {
        super(context);
        this.mPluginResources = resources;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public AssetManager getAssets() {
        return this.mPluginResources.getAssets();
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Resources getResources() {
        return this.mPluginResources;
    }
}
