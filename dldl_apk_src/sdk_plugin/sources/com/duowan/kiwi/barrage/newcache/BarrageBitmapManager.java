package com.duowan.kiwi.barrage.newcache;

import android.graphics.Bitmap;
import android.os.Build;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class BarrageBitmapManager {
    private static final Bitmap.Config RGB_CONFIG = Bitmap.Config.ARGB_4444;
    private List<BitmapCache> mRefPool = new ArrayList();

    public void recycle(Bitmap bitmap) {
        this.mRefPool.add(new BitmapCache(bitmap));
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x0050, code lost:
    
        r3 = null;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public android.graphics.Bitmap get(int r6, int r7) {
        /*
            r5 = this;
            java.util.List<com.duowan.kiwi.barrage.newcache.BarrageBitmapManager$BitmapCache> r0 = r5.mRefPool
            java.util.Iterator r0 = r0.iterator()
        L6:
            boolean r1 = r0.hasNext()
            java.lang.String r2 = "BarrageBitmapManager"
            if (r1 == 0) goto L50
            java.lang.Object r1 = r0.next()
            com.duowan.kiwi.barrage.newcache.BarrageBitmapManager$BitmapCache r1 = (com.duowan.kiwi.barrage.newcache.BarrageBitmapManager.BitmapCache) r1
            java.lang.ref.WeakReference<android.graphics.Bitmap> r3 = r1.mBitmapSoftReference
            java.lang.Object r3 = r3.get()
            android.graphics.Bitmap r3 = (android.graphics.Bitmap) r3
            if (r3 != 0) goto L3e
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "BarrageBitmapManager.get free pool, size is "
            r0.append(r1)
            java.util.List<com.duowan.kiwi.barrage.newcache.BarrageBitmapManager$BitmapCache> r1 = r5.mRefPool
            int r1 = r1.size()
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            com.duowan.kiwi.barrage.config.BarrageLog.debug(r2, r0)
            java.util.List<com.duowan.kiwi.barrage.newcache.BarrageBitmapManager$BitmapCache> r0 = r5.mRefPool
            r0.clear()
            goto L50
        L3e:
            int r4 = r3.getWidth()
            if (r4 < r6) goto L6
            int r4 = r3.getWidth()
            if (r4 < r7) goto L6
            java.util.List<com.duowan.kiwi.barrage.newcache.BarrageBitmapManager$BitmapCache> r0 = r5.mRefPool
            r0.remove(r1)
            goto L51
        L50:
            r3 = 0
        L51:
            if (r3 == 0) goto L59
            java.lang.String r6 = "BarrageBitmapManager.get, match! "
            com.duowan.kiwi.barrage.config.BarrageLog.debug(r2, r6)
            return r3
        L59:
            android.graphics.Bitmap r6 = r5.createBitmap(r6, r7)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.duowan.kiwi.barrage.newcache.BarrageBitmapManager.get(int, int):android.graphics.Bitmap");
    }

    private Bitmap createBitmap(int i, int i2) {
        return Bitmap.createBitmap(i, i2, RGB_CONFIG);
    }

    public static class BitmapCache {
        final WeakReference<Bitmap> mBitmapSoftReference;
        final int mByteCount;

        BitmapCache(Bitmap bitmap) {
            this.mBitmapSoftReference = new WeakReference<>(bitmap);
            if (Build.VERSION.SDK_INT >= 19) {
                this.mByteCount = bitmap.getAllocationByteCount();
            } else {
                this.mByteCount = bitmap.getByteCount();
            }
        }
    }
}
