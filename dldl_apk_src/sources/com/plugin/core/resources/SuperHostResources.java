package com.plugin.core.resources;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Build;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class SuperHostResources {
    private Context mContext;
    private Integer mPluginApkCookie;
    private Resources mResources;

    public SuperHostResources(Context context, String str) {
        this.mContext = context;
        this.mResources = buildHostResources(str);
    }

    private Resources buildHostResources(String str) {
        if (Build.VERSION.SDK_INT > 19) {
            try {
                AssetManager assets = this.mContext.getResources().getAssets();
                Method declaredMethod = AssetManager.class.getDeclaredMethod("addAssetPath", String.class);
                declaredMethod.setAccessible(true);
                this.mPluginApkCookie = (Integer) declaredMethod.invoke(assets, str);
                return new Resources(assets, this.mContext.getResources().getDisplayMetrics(), this.mContext.getResources().getConfiguration());
            } catch (Exception e) {
                e.printStackTrace();
                return this.mContext.getResources();
            }
        }
        try {
            AssetManager assetManager = (AssetManager) AssetManager.class.newInstance();
            Method declaredMethod2 = AssetManager.class.getDeclaredMethod("addAssetPath", String.class);
            declaredMethod2.setAccessible(true);
            declaredMethod2.invoke(assetManager, str);
            this.mPluginApkCookie = (Integer) declaredMethod2.invoke(assetManager, this.mContext.getApplicationInfo().sourceDir);
            return new Resources(assetManager, this.mContext.getResources().getDisplayMetrics(), this.mContext.getResources().getConfiguration());
        } catch (Exception e2) {
            e2.printStackTrace();
            return this.mContext.getResources();
        }
    }

    public Resources get() {
        return this.mResources;
    }

    public Integer getPluginApkCookie() {
        return this.mPluginApkCookie;
    }
}
