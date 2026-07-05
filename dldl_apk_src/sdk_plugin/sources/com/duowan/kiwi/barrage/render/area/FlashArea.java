package com.duowan.kiwi.barrage.render.area;

import com.duowan.kiwi.barrage.GunPowder;
import com.duowan.kiwi.barrage.render.IRenderConfig;
import com.duowan.kiwi.barrage.render.draw.BulletBuilder;
import com.duowan.kiwi.barrage.trace.AbsTrace;
import java.util.Random;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public abstract class FlashArea extends AbsBarrageArea {
    public Random mRandom;

    @Override // com.duowan.kiwi.barrage.render.area.AbsBarrageArea
    public boolean calculateBarrageReal(AbsTrace absTrace) {
        return true;
    }

    public FlashArea(IRenderConfig iRenderConfig, int i) {
        super(iRenderConfig, i);
        this.mRandom = new Random();
    }

    @Override // com.duowan.kiwi.barrage.render.area.AbsBarrageArea
    protected AnimationListenerImpl createAnimationListener() {
        return new AnimationListenerImpl() { // from class: com.duowan.kiwi.barrage.render.area.FlashArea.1
            @Override // com.duowan.kiwi.barrage.render.area.AnimationListenerImpl
            protected void onLastItemEnd(AbsTrace absTrace) {
                BulletBuilder.Bullet bulletGunPowderToBullet;
                AbsTrace absTraceFire;
                if (absTrace.mLineIndex >= FlashArea.this.mLockers.size()) {
                    return;
                }
                GunPowder gunPowderPoll = FlashArea.this.mGunPowderQueue.poll();
                if (gunPowderPoll != null && (bulletGunPowderToBullet = FlashArea.this.mBarrageHolder.getShellBuilder().gunPowderToBullet(gunPowderPoll)) != null && (absTraceFire = FlashArea.this.fire(bulletGunPowderToBullet, 0.0f, 0.0f)) != null) {
                    FlashArea flashArea = FlashArea.this;
                    flashArea.start(absTraceFire, flashArea.mBarrageHolder, absTrace.mLineIndex);
                } else {
                    FlashArea.this.mLockers.set(absTrace.mLineIndex, false);
                }
            }
        };
    }

    @Override // com.duowan.kiwi.barrage.render.area.AbsBarrageArea
    protected AbsTrace fire(BulletBuilder.Bullet bullet, float f, float f2) {
        if (!bullet.hasPixels()) {
            return null;
        }
        AbsTrace absTraceCreateBulletTrace = createBulletTrace(bullet, 256);
        float fNextInt = (this.mRandom.nextInt(this.mRight) % ((this.mRight - this.mLeft) + 1)) + this.mLeft;
        float fNextInt2 = (this.mRandom.nextInt(this.mBottom) % ((this.mBottom - this.mTop) + 1)) + this.mTop;
        if ((absTraceCreateBulletTrace.mWidth * this.mBarrageHolder.getScale()) + fNextInt > this.mRight) {
            fNextInt = this.mRight - (absTraceCreateBulletTrace.mWidth * this.mBarrageHolder.getScale());
        }
        if ((absTraceCreateBulletTrace.mHeight * this.mBarrageHolder.getScale()) + fNextInt2 > this.mBottom) {
            fNextInt2 = this.mBottom - (absTraceCreateBulletTrace.mHeight * this.mBarrageHolder.getScale());
        }
        if (fNextInt < this.mLeft) {
            fNextInt = this.mLeft;
        }
        if (fNextInt2 > this.mBottom) {
            fNextInt2 = this.mTop;
        }
        absTraceCreateBulletTrace.alpha(0.0f, 1.0f).x(fNextInt, fNextInt).y(fNextInt2, fNextInt2).duration(bullet.getDuration()).setRepeatCount(5).setRepeatModel(1);
        absTraceCreateBulletTrace.scaleX(this.mBarrageHolder.getScale(), this.mBarrageHolder.getScale());
        absTraceCreateBulletTrace.scaleY(this.mBarrageHolder.getScale(), this.mBarrageHolder.getScale());
        return absTraceCreateBulletTrace;
    }
}
