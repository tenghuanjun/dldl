package com.huya.berry.module.props;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.LruCache;
import android.util.SparseArray;
import android.util.SparseIntArray;
import com.duowan.auk.ArkValue;
import com.duowan.auk.util.L;
import com.huya.berry.module.props.prop.PropItem;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class PropsMgr {
    private static final int CACHE_SIZE = 1048576;
    private static final String TAG = "PropsMgr";
    private static PropsMgr mInstance;
    private SparseArray<PropItem> mActiveProps;
    private LruCache<Integer, Bitmap> mIconCache;
    private SparseIntArray mIconHash;
    private final int PRIME = 31;
    private boolean isPropLoaded = false;
    private long mUid = 0;
    private int mGameId = 0;

    private int getKeyHashCode(int i, long j, int i2, int i3) {
        return (((((i2 * 31) + ((int) (j ^ (j >>> 32)))) * 31) + i3) * 31) + i;
    }

    public static boolean isSeal(int i) {
        return (301 <= i && i <= 322) || (202 <= i && i <= 223);
    }

    private PropsMgr() {
        this.mIconCache = null;
        this.mActiveProps = null;
        this.mIconHash = null;
        this.mIconCache = new LruCache<>(1048576);
        this.mIconHash = new SparseIntArray();
        this.mActiveProps = new SparseArray<>();
    }

    public static PropsMgr instance() {
        if (mInstance == null) {
            synchronized (PropsMgr.class) {
                mInstance = new PropsMgr();
            }
        }
        return mInstance;
    }

    public boolean isComnPropLoaded() {
        return this.isPropLoaded;
    }

    public void setComnPropLoaded(boolean z) {
        this.isPropLoaded = z;
    }

    public void setLiving(long j, int i) {
        this.mUid = j;
        this.mGameId = i;
    }

    public synchronized Bitmap getLivingPropIcon(int i) {
        int keyHashCode = getKeyHashCode(0, this.mUid, this.mGameId, i);
        Bitmap bitmap = null;
        Integer numValueOf = Integer.valueOf(this.mIconHash.get(keyHashCode));
        if (numValueOf.intValue() != 0) {
            L.error(TAG, "getLivingPropIcon k %d - v %d", Integer.valueOf(keyHashCode), numValueOf);
            bitmap = this.mIconCache.get(numValueOf);
        }
        if (bitmap != null) {
            return bitmap;
        }
        return this.mIconCache.get(Integer.valueOf(i));
    }

    public synchronized Drawable getLivingSmallPropDraw(int i, int i2) {
        Bitmap livingPropIcon = getLivingPropIcon(i);
        if (livingPropIcon == null) {
            return null;
        }
        BitmapDrawable bitmapDrawable = new BitmapDrawable(ArkValue.gContext.getResources(), livingPropIcon);
        bitmapDrawable.setBounds(0, 0, i2, i2);
        return bitmapDrawable;
    }

    public synchronized void clearActiveProps() {
        this.mActiveProps.clear();
    }

    public synchronized boolean addActiveProp(PropItem propItem, long j, int i) {
        if (j != 0 && i != 0) {
            int keyHashCode = getKeyHashCode(0, j, i, propItem.getId());
            int iHashCode = propItem.getResUrl().hashCode();
            this.mIconHash.put(keyHashCode, iHashCode);
            this.mActiveProps.put(iHashCode, propItem);
        } else {
            this.mActiveProps.put(propItem.getId(), propItem);
        }
        return true;
    }

    public synchronized PropItem getPropReal(int i, boolean z) {
        PropItem propItem;
        if (z) {
            propItem = this.mActiveProps.get(this.mIconHash.get(getKeyHashCode(0, this.mUid, this.mGameId, i)));
            if (propItem == null) {
                propItem = this.mActiveProps.get(i);
            }
        } else {
            propItem = this.mActiveProps.get(i);
        }
        return propItem;
    }

    public synchronized PropItem getProp(int i, boolean z) {
        PropItem propReal;
        propReal = getPropReal(i, z);
        if (propReal == null) {
            L.error(TAG, "get prop must not be null " + i);
        }
        return propReal;
    }
}
