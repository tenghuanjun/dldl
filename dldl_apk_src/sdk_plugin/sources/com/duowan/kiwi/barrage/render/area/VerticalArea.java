package com.duowan.kiwi.barrage.render.area;

import com.duowan.kiwi.barrage.GunPowder;
import com.duowan.kiwi.barrage.config.BarrageConfig;
import com.duowan.kiwi.barrage.render.IRenderConfig;
import com.duowan.kiwi.barrage.render.draw.BulletBuilder;
import com.duowan.kiwi.barrage.trace.AbsTrace;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public abstract class VerticalArea extends AbsBarrageArea {
    public VerticalArea(IRenderConfig iRenderConfig, int i) {
        super(iRenderConfig, i);
    }

    @Override // com.duowan.kiwi.barrage.render.area.AbsBarrageArea
    public void setRect(int i, int i2, int i3, int i4) {
        super.setRect(i, i2, i3, i4);
        this.mRange = Math.abs(i4 - i2);
    }

    @Override // com.duowan.kiwi.barrage.render.area.AbsBarrageArea
    public boolean calculateBarrageReal(AbsTrace absTrace) {
        GunPowder gunPowderPoll;
        BulletBuilder.Bullet bulletGunPowderToBullet;
        if (toWorldPositionY(absTrace.getCurrentFrame().y()) + BarrageConfig.SpaceY + (absTrace.mHeight * absTrace.getCurrentFrame().scaleY()) < this.mRange) {
            if (this.mBarrageCache == null && (gunPowderPoll = this.mGunPowderQueue.poll()) != null && (bulletGunPowderToBullet = this.mBarrageHolder.getShellBuilder().gunPowderToBullet(gunPowderPoll)) != null) {
                this.mBarrageCache = fire(bulletGunPowderToBullet, -1948.0f, 0.0f);
            }
            if (this.mBarrageCache == null) {
                return false;
            }
            for (int i = 0; i < this.mLockers.size(); i++) {
                if (!this.mLockers.get(i).booleanValue()) {
                    float animationPosX = getAnimationPosX(this.mBarrageCache.mWidth, i);
                    this.mBarrageCache.x(animationPosX, animationPosX);
                    start(this.mBarrageCache, this.mBarrageHolder, i);
                    this.mBarrageCache = null;
                    return true;
                }
            }
            if (absTrace.getDuration() - absTrace.getCurrentTime() < (this.mBarrageCache.getDuration() * (this.mRange - BarrageConfig.SpaceY)) / (this.mRange + (this.mBarrageCache.mHeight * this.mBarrageHolder.getScale())) && absTrace.mLineIndex < this.mLockers.size()) {
                float animationPosX2 = getAnimationPosX(absTrace.mWidth, absTrace.mLineIndex);
                this.mBarrageCache.x(animationPosX2, animationPosX2);
                start(this.mBarrageCache, this.mBarrageHolder, absTrace.mLineIndex);
                this.mBarrageCache = null;
                absTrace.mHasFollower = true;
            }
        }
        return true;
    }

    @Override // com.duowan.kiwi.barrage.render.area.AbsBarrageArea
    protected AnimationListenerImpl createAnimationListener() {
        return new AnimationListenerImpl() { // from class: com.duowan.kiwi.barrage.render.area.VerticalArea.1
            @Override // com.duowan.kiwi.barrage.render.area.AnimationListenerImpl
            protected void onLastItemEnd(AbsTrace absTrace) {
                BulletBuilder.Bullet bulletGunPowderToBullet;
                if (absTrace.mLineIndex >= VerticalArea.this.mLockers.size()) {
                    return;
                }
                GunPowder gunPowderPoll = VerticalArea.this.mGunPowderQueue.poll();
                if (gunPowderPoll != null && (bulletGunPowderToBullet = VerticalArea.this.mBarrageHolder.getShellBuilder().gunPowderToBullet(gunPowderPoll)) != null) {
                    AbsTrace absTraceFire = VerticalArea.this.fire(bulletGunPowderToBullet, VerticalArea.this.getAnimationPosX(absTrace.mWidth, absTrace.mLineIndex), 0.0f);
                    if (absTraceFire != null) {
                        VerticalArea verticalArea = VerticalArea.this;
                        verticalArea.start(absTraceFire, verticalArea.mBarrageHolder, absTrace.mLineIndex);
                        return;
                    }
                }
                VerticalArea.this.mLockers.set(absTrace.mLineIndex, false);
            }
        };
    }

    @Override // com.duowan.kiwi.barrage.render.area.AbsBarrageArea
    protected AbsTrace fire(BulletBuilder.Bullet bullet, float f, float f2) {
        if (!bullet.hasPixels()) {
            return null;
        }
        AbsTrace absTraceCreateBulletTrace = createBulletTrace(bullet, 16);
        absTraceCreateBulletTrace.duration(bullet.getDuration());
        absTraceCreateBulletTrace.alpha(this.mBarrageHolder.getAlpha(), this.mBarrageHolder.getAlpha());
        if (-1948.0f != f) {
            absTraceCreateBulletTrace.x(f, f);
        }
        return absTraceCreateBulletTrace;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public float getAnimationPosX(int i, int i2) {
        return (((i * this.mBarrageHolder.getScale()) + BarrageConfig.COLUMN_SPACE) * i2) + this.mLeft;
    }
}
