package com.shuyu.gsyvideoplayer.cache;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public class CacheFactory {
    private static Class<? extends ICacheManager> sICacheManager;

    public static void setCacheManager(Class<? extends ICacheManager> cls) {
        sICacheManager = cls;
    }

    public static ICacheManager getCacheManager() {
        if (sICacheManager == null) {
            sICacheManager = ProxyCacheManager.class;
        }
        try {
            return sICacheManager.newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        } catch (InstantiationException e2) {
            e2.printStackTrace();
            return null;
        }
    }
}
