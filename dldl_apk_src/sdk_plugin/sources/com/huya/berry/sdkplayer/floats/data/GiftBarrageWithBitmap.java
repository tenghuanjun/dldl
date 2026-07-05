package com.huya.berry.sdkplayer.floats.data;

import android.graphics.Bitmap;
import com.duowan.auk.NoProguard;
import com.duowan.kiwi.barrage.GunPowder;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class GiftBarrageWithBitmap implements NoProguard {
    public Bitmap mBitmap;
    public GunPowder mGunPowder;

    public GiftBarrageWithBitmap(Bitmap bitmap) {
        this.mBitmap = bitmap;
    }

    public GiftBarrageWithBitmap(Bitmap bitmap, GunPowder gunPowder) {
        this.mBitmap = bitmap;
        this.mGunPowder = gunPowder;
    }
}
