package com.duowan.kiwi.barrage.newcache;

import android.graphics.Bitmap;
import android.util.LruCache;
import com.duowan.kiwi.barrage.config.BarrageLog;
import java.util.Iterator;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public abstract class LruDrawingCacheManager<CONTENT> extends AbsDrawingCacheManager<CONTENT> {
    public static final int DEFAULT_MAX_CACHE_SIZE = 50;
    private final LruCache<Integer, AbsDrawingCache<CONTENT>> mCache;

    public interface DrawingCacheStrategy {
        int maxSize();

        int sizeOf(AbsDrawingCache absDrawingCache);
    }

    public LruDrawingCacheManager(BarrageBitmapManager barrageBitmapManager, final DrawingCacheStrategy drawingCacheStrategy) {
        super(barrageBitmapManager);
        this.mCache = new LruCache<Integer, AbsDrawingCache<CONTENT>>(drawingCacheStrategy.maxSize()) { // from class: com.duowan.kiwi.barrage.newcache.LruDrawingCacheManager.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.util.LruCache
            public void entryRemoved(boolean z, Integer num, AbsDrawingCache<CONTENT> absDrawingCache, AbsDrawingCache<CONTENT> absDrawingCache2) {
                if (absDrawingCache.isHoldingBitmap() && absDrawingCache.getReferenceCount() == 0) {
                    LruDrawingCacheManager.this.mBitmapManager.recycle((Bitmap) absDrawingCache.getContent());
                }
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.util.LruCache
            public int sizeOf(Integer num, AbsDrawingCache<CONTENT> absDrawingCache) {
                return drawingCacheStrategy.sizeOf(absDrawingCache);
            }
        };
    }

    private void dumpDebugInfo() {
        Iterator<AbsDrawingCache<CONTENT>> it = this.mCache.snapshot().values().iterator();
        int byteCount = 0;
        while (it.hasNext()) {
            byteCount += it.next().getByteCount();
        }
        BarrageLog.debug("yaojun", "cache size is :" + byteCount + ", " + this.mCache);
    }

    @Override // com.duowan.kiwi.barrage.newcache.AbsDrawingCacheManager
    public void add2Cache(int i, AbsDrawingCache<CONTENT> absDrawingCache) {
        this.mCache.put(Integer.valueOf(i), absDrawingCache);
    }

    @Override // com.duowan.kiwi.barrage.newcache.AbsDrawingCacheManager
    public AbsDrawingCache<CONTENT> getCache(int i) {
        AbsDrawingCache<CONTENT> absDrawingCache = this.mCache.get(Integer.valueOf(i));
        if (absDrawingCache == null) {
            return null;
        }
        return absDrawingCache;
    }

    private static DrawingCacheStrategy getDefaultCacheStrategy() {
        return new DrawingCacheStrategy() { // from class: com.duowan.kiwi.barrage.newcache.LruDrawingCacheManager.2
            @Override // com.duowan.kiwi.barrage.newcache.LruDrawingCacheManager.DrawingCacheStrategy
            public int maxSize() {
                return 50;
            }

            @Override // com.duowan.kiwi.barrage.newcache.LruDrawingCacheManager.DrawingCacheStrategy
            public int sizeOf(AbsDrawingCache absDrawingCache) {
                return 1;
            }
        };
    }
}
