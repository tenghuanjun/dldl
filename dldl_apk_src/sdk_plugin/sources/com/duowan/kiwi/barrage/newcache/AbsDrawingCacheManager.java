package com.duowan.kiwi.barrage.newcache;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public abstract class AbsDrawingCacheManager<CONTENT> {
    protected final BarrageBitmapManager mBitmapManager;

    public abstract void add2Cache(int i, AbsDrawingCache<CONTENT> absDrawingCache);

    public abstract AbsDrawingCache<CONTENT> getCache(int i);

    public abstract void removeCache(int i);

    public AbsDrawingCacheManager(BarrageBitmapManager barrageBitmapManager) {
        this.mBitmapManager = barrageBitmapManager;
    }
}
