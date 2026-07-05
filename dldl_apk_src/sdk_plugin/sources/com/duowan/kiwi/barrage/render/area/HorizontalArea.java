package com.duowan.kiwi.barrage.render.area;

import android.content.res.Configuration;
import com.duowan.ark.ArkUtils;
import com.duowan.kiwi.barrage.BarrageEvent;
import com.duowan.kiwi.barrage.GunPowder;
import com.duowan.kiwi.barrage.config.BarrageConfig;
import com.duowan.kiwi.barrage.config.BarrageContext;
import com.duowan.kiwi.barrage.config.BarrageLog;
import com.duowan.kiwi.barrage.render.IRenderConfig;
import com.duowan.kiwi.barrage.render.draw.BulletBuilder;
import com.duowan.kiwi.barrage.report.BarrageCacheForReport;
import com.duowan.kiwi.barrage.stencil.StencilManager;
import com.duowan.kiwi.barrage.trace.AbsTrace;
import java.util.Random;
import master.flame.danmaku.danmaku.model.android.DanmakuFactory;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public abstract class HorizontalArea extends AbsBarrageArea {
    private static final int SHOW_ANTIBLOCK_TIPS_INTENAL = 4000;
    private static final String TAG = "[Barrage]";
    private boolean mAutoIncrease;
    private Configuration mConfiguration;
    private long mLastTimt;
    private final float mLineScale;
    private int mMaxLineCount;
    private int mOrientationDuration;
    protected boolean mSingle;
    Random random;

    public HorizontalArea(IRenderConfig iRenderConfig, int i) {
        super(iRenderConfig, i);
        this.mSingle = true;
        this.mLineScale = 0.8f;
        this.random = new Random();
        this.mOrientationDuration = BarrageConfig.DEFAULT_DURATION;
        this.mConfiguration = BarrageContext.gContext.getResources().getConfiguration();
        this.mLastTimt = 0L;
        this.mMaxLineCount = i;
    }

    public boolean isSingle() {
        return this.mSingle;
    }

    public void initSingleDouble() {
        int size = this.mGunPowderQueue.size();
        if (size < 0) {
            BarrageLog.error("[Barrage]", "initSingleDouble size < 0");
            return;
        }
        if (this.mSingle) {
            if (size >= BarrageConfig.DOUBLE_WHEN_SHELL_CACHE_SIZES) {
                BarrageLog.info("[Barrage]", "open double");
                this.mSingle = false;
                return;
            }
            return;
        }
        if (size <= BarrageConfig.SINGLE_WHEN_SHELL_CACHE_SIZES) {
            BarrageLog.info("[Barrage]", "close double");
            this.mSingle = true;
        }
    }

    public void setDuration(int i) {
        if (i == 1) {
            this.mOrientationDuration = BarrageConfig.VERTICAL_DEFAULT_DURATION;
        } else {
            this.mOrientationDuration = BarrageConfig.DEFAULT_DURATION;
        }
    }

    public void setAutoIncrease(boolean z) {
        this.mAutoIncrease = z;
    }

    public boolean isAutoIncrease() {
        return this.mAutoIncrease;
    }

    public void resetMaxLineCount() {
        int i;
        int iCeil = (int) Math.ceil(this.mBarrageHolder.getShellBuilder().getCharSize()[1] * this.mBarrageHolder.getScale());
        int lineSpace = this.mBarrageHolder.getLineSpace();
        int iAbs = (int) ((Math.abs(this.mBottom - this.mTop) + lineSpace) / ((iCeil + lineSpace) * 0.8f));
        this.mMaxLineCount = iAbs;
        if ((this.mAutoIncrease || iAbs < getLineCount()) && (i = this.mMaxLineCount) >= 0) {
            setQueueLimited(i);
            setLineCount(this.mMaxLineCount, this.mBarrageHolder.getAnimations());
        }
    }

    @Override // com.duowan.kiwi.barrage.render.area.AbsBarrageArea
    public void setRect(int i, int i2, int i3, int i4) {
        super.setRect(i, i2, i3, i4);
        this.mRange = Math.abs(i - i3);
    }

    public boolean onPreCalculateBarrage() {
        initSingleDouble();
        int i = 0;
        while (i < this.mLockers.size() && i < BarrageConfig.EMPTY_STRATEGY_LINE_NUMBER) {
            if (!this.mLockers.get(i).booleanValue()) {
                startNewBarrageAnimation(i, 0);
                return false;
            }
            if (isSingle()) {
                i++;
            }
            i++;
        }
        return true;
    }

    @Override // com.duowan.kiwi.barrage.render.area.AbsBarrageArea
    public boolean calculateBarrageReal(AbsTrace absTrace) {
        int spaceX = this.mBarrageHolder.getSpaceX();
        if ((!isSingle() || absTrace.mLineIndex % 2 != 1) && (this.mRange - (absTrace.mWidth * absTrace.getCurrentFrame().scaleX())) - spaceX > toWorldPositionX(absTrace.getCurrentFrame().x())) {
            int size = this.mLockers.size();
            int i = 0;
            while (true) {
                if (i >= this.mLockers.size()) {
                    break;
                }
                if (!this.mLockers.get(i).booleanValue()) {
                    size = i;
                    break;
                }
                if (isSingle()) {
                    i++;
                }
                i++;
            }
            if (this.mLockers.size() > absTrace.mLineIndex && size > absTrace.mLineIndex) {
                if (startNewBarrageAnimation(absTrace.mLineIndex, absTrace.mWidth)) {
                    absTrace.mHasFollower = true;
                }
                return false;
            }
        }
        return true;
    }

    private void tryShowAntiBlockingBarrageTip() {
        if (!BarrageConfig.isAntiBlockHasTip() && this.mConfiguration.orientation == 2 && StencilManager.getInstance().hasData() && BarrageConfig.isAntiBlockNeverUse() && 1 == BarrageConfig.getBarrageModel() && System.currentTimeMillis() - this.mLastTimt > DanmakuFactory.MIN_DANMAKU_DURATION) {
            ArkUtils.send(new BarrageEvent.ShowAntiBlockTip());
            this.mLastTimt = System.currentTimeMillis();
        }
    }

    private boolean startNewBarrageAnimation(int i, int i2) {
        if (i > 9) {
            tryShowAntiBlockingBarrageTip();
        }
        AbsTrace absTracePollBarrage = pollBarrage(i);
        if (absTracePollBarrage == null) {
            return false;
        }
        BarrageCacheForReport.getInstance().add(absTracePollBarrage);
        float animationPosY = getAnimationPosY(absTracePollBarrage.mHeight, i);
        float fAdjustShowDuration = adjustShowDuration(this.mOrientationDuration);
        absTracePollBarrage.y(animationPosY, animationPosY);
        absTracePollBarrage.duration(fAdjustShowDuration);
        start(absTracePollBarrage, this.mBarrageHolder, i, absTracePollBarrage.mWidth - i2);
        return true;
    }

    private AbsTrace pollBarrage(int i) {
        GunPowder gunPowderPoll = this.mGunPowderQueue.poll(i);
        while (gunPowderPoll != null && !isGunPowderValid(gunPowderPoll)) {
            gunPowderPoll = this.mGunPowderQueue.poll(true);
        }
        if (gunPowderPoll != null) {
            BulletBuilder.Bullet bulletGunPowderToBullet = this.mBarrageHolder.getShellBuilder().gunPowderToBullet(gunPowderPoll);
            if (bulletGunPowderToBullet != null) {
                return fire(bulletGunPowderToBullet, 0.0f, -1948.0f);
            }
            BarrageLog.error("[Barrage]", "gunPowderToBullet failed!");
        }
        return null;
    }

    private boolean isGunPowderValid(GunPowder gunPowder) {
        if (gunPowder.mCacheObject != null || gunPowder.mAttachObject == null) {
            return true;
        }
        ArkUtils.send(gunPowder.mAttachObject);
        return false;
    }

    @Override // com.duowan.kiwi.barrage.render.area.AbsBarrageArea
    public void onCalculateFinish() {
        int i = 0;
        while (i < this.mLockers.size()) {
            if (!this.mLockers.get(i).booleanValue()) {
                startNewBarrageAnimation(i, 0);
                return;
            } else {
                if (isSingle()) {
                    i++;
                }
                i++;
            }
        }
    }

    private float adjustShowDuration(float f) {
        float fAdjustShowDuration = this.mGunPowderQueue.adjustShowDuration(f);
        if (this.mGunPowderQueue.size() < BarrageConfig.OPEN_RANDOM_SPEED_SIZE) {
            return fAdjustShowDuration;
        }
        int iNextInt = (this.random.nextInt(100) % 36) + 65;
        if (iNextInt > 77) {
            iNextInt = (iNextInt + 100) / 2;
        }
        float f2 = (fAdjustShowDuration * iNextInt) / 100.0f;
        if (f2 < 4.5f) {
            return 4.5f;
        }
        return f2;
    }

    @Override // com.duowan.kiwi.barrage.render.area.AbsBarrageArea
    protected AbsTrace fire(BulletBuilder.Bullet bullet, float f, float f2) {
        AbsTrace absTraceCreateBulletTrace;
        if (!bullet.hasPixels() || (absTraceCreateBulletTrace = createBulletTrace(bullet, 1)) == null) {
            return null;
        }
        absTraceCreateBulletTrace.duration(bullet.getDuration());
        absTraceCreateBulletTrace.beginTime(bullet.getBeginTime());
        absTraceCreateBulletTrace.alpha(this.mBarrageHolder.getAlpha(), this.mBarrageHolder.getAlpha());
        if (-1948.0f != f2) {
            absTraceCreateBulletTrace.y(f2, f2);
        }
        return absTraceCreateBulletTrace;
    }

    @Override // com.duowan.kiwi.barrage.render.area.AbsBarrageArea
    protected AnimationListenerImpl createAnimationListener() {
        return new AnimationListenerImpl() { // from class: com.duowan.kiwi.barrage.render.area.HorizontalArea.1
            @Override // com.duowan.kiwi.barrage.render.area.AnimationListenerImpl
            protected void onLastItemEnd(AbsTrace absTrace) {
                if (absTrace.mLineIndex >= HorizontalArea.this.mLockers.size()) {
                    return;
                }
                HorizontalArea.this.mLockers.set(absTrace.mLineIndex, false);
            }
        };
    }

    private float getAnimationPosY(int i, int i2) {
        return ((((((BulletBuilder.getDefaultBarrageHeight() * 0.8f) * this.mBarrageHolder.getScale()) + this.mBarrageHolder.getLineSpace()) * i2) - this.mBarrageHolder.getLineSpace()) + this.mTop) - ((i - BulletBuilder.getDefaultBarrageHeight()) / 2);
    }
}
