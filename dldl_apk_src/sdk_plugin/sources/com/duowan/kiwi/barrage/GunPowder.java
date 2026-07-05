package com.duowan.kiwi.barrage;

import com.duowan.kiwi.barrage.config.BarrageConfig;
import com.duowan.kiwi.barrage.newcache.AbsDrawingCache;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class GunPowder {
    public static final int DIRECTION_LTR = 0;
    public static final int DIRECTION_TTB = 1;
    public static final int EXPLOSIVE_HIGH = 2;
    public static final int EXPLOSIVE_HIGH_SHOW = 3;
    public static final int EXPLOSIVE_NORMAL = 1;
    public static final int EXPLOSIVE_SINGLE = 4;
    public Object mAttachObject;
    public float mBeginTime;
    public AbsDrawingCache mCacheObject;
    public int mColor;
    public int mDirection;
    public float mDuration;
    public int mExplosive;
    public boolean mNeedClean;
    public String mNickName;
    public String mPowder;
    public long mUid;

    public GunPowder(String str, int i, int i2, int i3, float f) {
        this(str, i, i2, i3, f, 0.0f);
    }

    public GunPowder(String str, int i, int i2, int i3, float f, float f2) {
        this.mDirection = 0;
        this.mUid = 0L;
        this.mPowder = str;
        this.mExplosive = i;
        this.mColor = i2;
        this.mDirection = i3;
        this.mDuration = f;
        this.mBeginTime = f2;
    }

    public GunPowder(long j, String str, String str2) {
        this(str2, 2, BarrageConfig.DefaultColor, 0, BarrageConfig.DEFAULT_DURATION);
        this.mUid = j;
        this.mNickName = str;
    }

    public GunPowder(long j, String str, String str2, int i, int i2, int i3, float f) {
        this(str2, i, i2, i3, f);
        this.mUid = j;
        this.mNickName = str;
    }

    public GunPowder(boolean z, String str, int i, int i2, float f) {
        this(str, i, i2, 0, f);
        this.mNeedClean = z;
    }

    public GunPowder(Object obj, int i, float f) {
        this.mDirection = 0;
        this.mUid = 0L;
        this.mAttachObject = obj;
        this.mExplosive = i;
        this.mDuration = f;
    }

    public GunPowder(GunPowder gunPowder, AbsDrawingCache absDrawingCache, int i, float f) {
        this.mDirection = 0;
        this.mUid = 0L;
        if (gunPowder != null) {
            this.mUid = gunPowder.mUid;
            this.mPowder = gunPowder.mPowder;
            this.mNickName = gunPowder.mNickName;
        }
        this.mCacheObject = absDrawingCache;
        this.mExplosive = i;
        this.mDuration = f;
    }

    public int getCharacteristic() {
        int i = (this.mExplosive + 31) * 31;
        String str = this.mPowder;
        return ((((i + (str == null ? 0 : str.hashCode())) * 31) + this.mColor) * 31) + this.mDirection;
    }
}
