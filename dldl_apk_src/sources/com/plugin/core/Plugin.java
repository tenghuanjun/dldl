package com.plugin.core;

import android.content.res.Resources;
import com.plugin.core.loader.ApkClassLoader;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class Plugin {
    public ApkClassLoader mClassLoader;
    public String mPath;
    public Resources mResource;

    public String getPluginPath() {
        return this.mPath;
    }

    public void setPluginPath(String str) {
        this.mPath = str;
    }

    public ClassLoader getClassLoader() {
        return this.mClassLoader;
    }

    public void setClassLoader(ApkClassLoader apkClassLoader) {
        this.mClassLoader = apkClassLoader;
    }

    public Resources getResource() {
        return this.mResource;
    }

    public void setResources(Resources resources) {
        this.mResource = resources;
    }
}
