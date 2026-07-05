package com.duowan.kiwi.barrage.render;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import com.duowan.kiwi.barrage.GunPowder;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public interface IBarrageRender {
    void ceaseFire(boolean z);

    void clearCanvas();

    void draw(Canvas canvas);

    long getCurrentTime();

    boolean isBarrageOn();

    boolean isBarrageRenderOn();

    boolean isStop();

    void notifyDispSizeChanged(int i, int i2);

    void offer(GunPowder gunPowder, int i);

    void onBarrageSizeChanged(int i);

    void onRequireMarqueeInSurface(Bitmap bitmap, float f, long j);

    void resetSmooth();

    void setAutoIncrease(int i, boolean z);

    void setBarrageAlpha(float f);

    void setBarrageRenderOn(boolean z);

    void setBarrageType(int i);

    void setOrientation(int i, boolean z);

    void start();

    void stop();
}
