package com.sqwan.msdk;

import android.content.Context;
import com.plugin.core.Plugin;
import com.plugin.core.PluginManager;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class PluginLoader {
    private static volatile PluginLoader sInstance;
    private volatile Plugin mPlugin;

    public static PluginLoader getInstance() {
        if (sInstance == null) {
            synchronized (PluginLoader.class) {
                if (sInstance == null) {
                    sInstance = new PluginLoader();
                }
            }
        }
        return sInstance;
    }

    private PluginLoader() {
    }

    Plugin load(Context context) {
        if (this.mPlugin == null) {
            synchronized (PluginLoader.class) {
                if (this.mPlugin == null) {
                    this.mPlugin = PluginManager.getInstance(context).loadPlugin();
                }
            }
        }
        return this.mPlugin;
    }

    public Plugin get() {
        if (this.mPlugin == null) {
            throw new IllegalStateException("load plugin first!");
        }
        return this.mPlugin;
    }
}
