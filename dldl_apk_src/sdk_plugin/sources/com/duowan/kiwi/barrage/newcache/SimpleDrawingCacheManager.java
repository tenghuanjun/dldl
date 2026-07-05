package com.duowan.kiwi.barrage.newcache;

import android.graphics.Bitmap;
import android.util.SparseArray;
import com.duowan.kiwi.barrage.newcache.AbsDrawingCache;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class SimpleDrawingCacheManager<CONTENT> extends AbsDrawingCacheManager<CONTENT> {
    private final SparseArray<AbsDrawingCache<CONTENT>> mCache;

    public SimpleDrawingCacheManager(BarrageBitmapManager barrageBitmapManager) {
        super(barrageBitmapManager);
        this.mCache = new SparseArray<>();
    }

    @Override // com.duowan.kiwi.barrage.newcache.AbsDrawingCacheManager
    public synchronized void add2Cache(final int i, final AbsDrawingCache<CONTENT> absDrawingCache) {
        this.mCache.put(i, absDrawingCache);
        absDrawingCache.setCallback(new AbsDrawingCache.OnFreeCallback() { // from class: com.duowan.kiwi.barrage.newcache.SimpleDrawingCacheManager.1
            @Override // com.duowan.kiwi.barrage.newcache.AbsDrawingCache.OnFreeCallback
            public void onFree() {
                SimpleDrawingCacheManager.this.removeCache(i);
                if (absDrawingCache.isHoldingBitmap()) {
                    SimpleDrawingCacheManager.this.mBitmapManager.recycle((Bitmap) absDrawingCache.getContent());
                }
            }
        });
    }

    @Override // com.duowan.kiwi.barrage.newcache.AbsDrawingCacheManager
    public synchronized AbsDrawingCache<CONTENT> getCache(int i) {
        return this.mCache.get(i);
    }

    @Override // com.duowan.kiwi.barrage.newcache.AbsDrawingCacheManager
    public synchronized void removeCache(int i) {
        this.mCache.remove(i);
    }
}
