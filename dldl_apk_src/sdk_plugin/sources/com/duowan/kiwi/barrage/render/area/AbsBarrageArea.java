package com.duowan.kiwi.barrage.render.area;

import android.graphics.Rect;
import android.util.SparseIntArray;
import com.duowan.kiwi.barrage.GunPowder;
import com.duowan.kiwi.barrage.config.BarrageConfig;
import com.duowan.kiwi.barrage.config.BarrageLog;
import com.duowan.kiwi.barrage.queue.GunPowderQueue;
import com.duowan.kiwi.barrage.render.IRenderConfig;
import com.duowan.kiwi.barrage.render.draw.BulletBuilder;
import com.duowan.kiwi.barrage.trace.AbsTrace;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public abstract class AbsBarrageArea {
    protected static final int InValid = -1948;
    protected List<AnimationListenerImpl> mAnimationListenerImpl;
    protected AbsTrace mBarrageCache = null;
    protected IRenderConfig mBarrageHolder;
    protected int mBottom;
    protected GunPowderQueue mGunPowderQueue;
    protected int mLeft;
    protected List<Boolean> mLockers;
    protected int mRange;
    protected int mRight;
    protected int mTop;

    public abstract boolean calculateBarrageReal(AbsTrace absTrace);

    protected abstract AnimationListenerImpl createAnimationListener();

    protected abstract AbsTrace createBulletTrace(BulletBuilder.Bullet bullet, int i);

    protected abstract AbsTrace fire(BulletBuilder.Bullet bullet, float f, float f2);

    public void onCalculateFinish() {
    }

    protected float toWorldPositionX(float f) {
        return f;
    }

    protected float toWorldPositionY(float f) {
        return f;
    }

    protected AbsBarrageArea(IRenderConfig iRenderConfig, int i) {
        this.mLockers = null;
        this.mAnimationListenerImpl = null;
        this.mBarrageHolder = iRenderConfig;
        this.mLockers = new ArrayList(i);
        this.mAnimationListenerImpl = new ArrayList(i);
        for (int i2 = 0; i2 < i; i2++) {
            this.mLockers.add(false);
            this.mAnimationListenerImpl.add(createAnimationListener());
        }
        this.mGunPowderQueue = new GunPowderQueue();
        this.mLeft = 0;
        this.mTop = 0;
        this.mRight = 0;
        this.mBottom = 0;
    }

    public void setRect(int i, int i2, int i3, int i4) {
        this.mLeft = i;
        this.mTop = i2;
        this.mRight = i3;
        this.mBottom = i4;
    }

    public Rect getRect() {
        return new Rect(this.mLeft, this.mTop, this.mRight, this.mBottom);
    }

    public void offer(GunPowder gunPowder) {
        this.mGunPowderQueue.offer(gunPowder);
    }

    public void reset(boolean z) {
        AbsTrace absTrace = this.mBarrageCache;
        if (absTrace != null) {
            absTrace.recycle();
            this.mBarrageCache = null;
        }
        if (z) {
            this.mGunPowderQueue.clear();
        }
        resetLockers();
    }

    public int getLineCount() {
        return this.mLockers.size();
    }

    public void setQueueLimited(int i) {
        if (this.mBarrageHolder.isFixedQueue()) {
            this.mGunPowderQueue.setLimited(this.mBarrageHolder.getFixedLine());
        } else {
            this.mGunPowderQueue.setLimited(i);
        }
    }

    public void setLineCount(int i, List<AbsTrace> list) {
        BarrageLog.info("barrage", "setLineCount : %d", Integer.valueOf(i));
        if (i == this.mLockers.size()) {
            return;
        }
        int size = this.mLockers.size();
        int iAbs = Math.abs(i - size);
        if (i < size) {
            for (int i2 = 1; i2 <= iAbs; i2++) {
                int i3 = size - i2;
                this.mLockers.remove(i3);
                AnimationListenerImpl animationListenerImpl = this.mAnimationListenerImpl.get(i3);
                if (animationListenerImpl != null) {
                    animationListenerImpl.setTarget(null);
                }
                this.mAnimationListenerImpl.remove(i3);
            }
            return;
        }
        SparseIntArray sparseIntArray = new SparseIntArray();
        Iterator<AbsTrace> it = list.iterator();
        while (it.hasNext()) {
            sparseIntArray.put(it.next().mLineIndex, 0);
        }
        for (int i4 = 0; i4 < iAbs; i4++) {
            int i5 = size + i4;
            this.mLockers.add(i5, Boolean.valueOf(sparseIntArray.get(i5, -1) != -1));
            this.mAnimationListenerImpl.add(i5, createAnimationListener());
        }
    }

    protected OnAnimationListener getAnimationListener(int i) {
        if (i < this.mAnimationListenerImpl.size()) {
            return this.mAnimationListenerImpl.get(i);
        }
        return null;
    }

    private void resetLockers() {
        for (int i = 0; i < this.mLockers.size(); i++) {
            this.mLockers.set(i, false);
        }
    }

    public void start(AbsTrace absTrace, IRenderConfig iRenderConfig, int i) {
        if (absTrace.getTarget() == 16) {
            float scale = this.mBarrageHolder.getScale();
            absTrace.y(this.mRange, (-absTrace.mHeight) * scale);
            absTrace.scaleX(scale, scale);
            absTrace.scaleY(scale, scale);
        }
        if (i >= this.mLockers.size()) {
            BarrageLog.error("barrage", "lineIndex %d >= mLockers.size() %d, return", Integer.valueOf(i), Integer.valueOf(this.mLockers.size()));
            return;
        }
        absTrace.mLineIndex = i;
        absTrace.setListener(getAnimationListener(i));
        absTrace.start(iRenderConfig);
        this.mLockers.set(absTrace.mLineIndex, true);
    }

    public void start(AbsTrace absTrace, IRenderConfig iRenderConfig, int i, int i2) {
        float scale = this.mBarrageHolder.getScale();
        if (i2 > 0 && this.mGunPowderQueue.size() < BarrageConfig.OPEN_RANDOM_SPEED_SIZE) {
            absTrace.x(this.mRange + (i2 * scale), (-absTrace.mWidth) * scale);
        } else {
            absTrace.x(this.mRange, (-absTrace.mWidth) * scale);
        }
        absTrace.scaleX(scale, scale);
        absTrace.scaleY(scale, scale);
        start(absTrace, iRenderConfig, i);
    }
}
