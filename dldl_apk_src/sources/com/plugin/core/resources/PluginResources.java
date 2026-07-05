package com.plugin.core.resources;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class PluginResources {
    private Context mContext;
    private String mPluginPath;
    private String mPluginPkgName;
    private Resources mResources = build();

    public PluginResources(Context context, String str) {
        this.mContext = context;
        this.mPluginPath = str;
    }

    public String getPkgName() {
        return this.mPluginPkgName;
    }

    public Resources get() {
        return this.mResources;
    }

    private Resources build() {
        try {
            PackageInfo packageInfo = this.mContext.getPackageManager().getPackageInfo(this.mContext.getPackageName(), 205);
            String str = packageInfo.applicationInfo.publicSourceDir;
            String str2 = packageInfo.applicationInfo.sourceDir;
            String str3 = packageInfo.packageName;
            packageInfo.applicationInfo.publicSourceDir = this.mPluginPath;
            packageInfo.applicationInfo.sourceDir = this.mPluginPath;
            this.mPluginPkgName = this.mContext.getPackageManager().getPackageArchiveInfo(this.mPluginPath, 1).packageName;
            Resources resourcesForApplication = this.mContext.getPackageManager().getResourcesForApplication(packageInfo.applicationInfo);
            packageInfo.applicationInfo.publicSourceDir = str;
            packageInfo.applicationInfo.sourceDir = str2;
            packageInfo.packageName = str3;
            return resourcesForApplication;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
