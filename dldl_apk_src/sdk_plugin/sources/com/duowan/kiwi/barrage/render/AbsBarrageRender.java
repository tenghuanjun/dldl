package com.duowan.kiwi.barrage.render;

import android.graphics.Canvas;
import com.duowan.ark.util.pools.ArrayListPoolFactory;
import com.duowan.kiwi.barrage.GunPowder;
import com.duowan.kiwi.barrage.config.BarrageConfig;
import com.duowan.kiwi.barrage.config.BarrageLog;
import com.duowan.kiwi.barrage.newcache.DrawingFactory;
import com.duowan.kiwi.barrage.render.area.AbsBarrageArea;
import com.duowan.kiwi.barrage.render.area.FlashArea;
import com.duowan.kiwi.barrage.render.area.HorizontalArea;
import com.duowan.kiwi.barrage.render.area.VerticalArea;
import com.duowan.kiwi.barrage.render.draw.BulletBuilder;
import com.duowan.kiwi.barrage.trace.AbsTrace;
import com.duowan.kiwi.barrage.view.IBarrageView;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
abstract class AbsBarrageRender<T extends AbsTrace, DRAWING_TYPE> implements IRenderConfig<T>, IBarrageRender, DrawingFactory.BuildMachine<DRAWING_TYPE> {
    private static final String TAG = "[Barrage]render";
    private float mAlpha;
    private IBarrageView mBarrageView;
    private FlashArea mFlashRect;
    private HorizontalArea mHorizontalRect;
    private AtomicInteger mOrientation;
    private float mScale;
    private VerticalArea mVerticalRect;
    private int mType = -1;
    private AtomicBoolean mBarrageOn = new AtomicBoolean(false);
    private AtomicBoolean mBarrageRenderOn = new AtomicBoolean(true);
    private ArrayListPoolFactory mArrayListPoolFactory = new ArrayListPoolFactory(4);
    private OnRemoveAnimMatcher mBarrageMatcher = new OnRemoveAnimMatcher() { // from class: com.duowan.kiwi.barrage.render.AbsBarrageRender.4
        @Override // com.duowan.kiwi.barrage.render.AbsBarrageRender.OnRemoveAnimMatcher
        public boolean isMatch(AbsTrace absTrace) {
            return !absTrace.isUseBitmap();
        }
    };
    private ArrayList<T> mAnimations = this.mArrayListPoolFactory.obtain();
    private BulletBuilder<DRAWING_TYPE> mBulletBuilder = new BulletBuilder<>(BarrageConfig.sBaseLandscapeSize, BarrageConfig.ShadowRadius, this);

    public interface OnRemoveAnimMatcher {
        boolean isMatch(AbsTrace absTrace);
    }

    @Override // com.duowan.kiwi.barrage.render.IBarrageRender
    public void clearCanvas() {
    }

    protected abstract AbsTrace createTrace(BulletBuilder.Bullet<DRAWING_TYPE> bullet, int i);

    @Override // com.duowan.kiwi.barrage.render.IBarrageRender
    public void draw(Canvas canvas) {
    }

    @Override // com.duowan.kiwi.barrage.render.IBarrageRender
    public void start() {
    }

    @Override // com.duowan.kiwi.barrage.render.IBarrageRender
    public void stop() {
    }

    protected float toCustomWorldPositionX(float f) {
        return f;
    }

    protected float toCustomWorldPositionY(float f) {
        return f;
    }

    public AbsBarrageRender(IBarrageView iBarrageView, int i, boolean z, int i2, float f, int i3) {
        this.mBarrageView = iBarrageView;
        this.mAlpha = f;
        int i4 = 5;
        this.mHorizontalRect = new HorizontalArea(this, i2 == 2 ? 5 : 3) { // from class: com.duowan.kiwi.barrage.render.AbsBarrageRender.1
            @Override // com.duowan.kiwi.barrage.render.area.AbsBarrageArea
            public AbsTrace createBulletTrace(BulletBuilder.Bullet bullet, int i5) {
                return AbsBarrageRender.this.createTrace(bullet, i5);
            }

            @Override // com.duowan.kiwi.barrage.render.area.AbsBarrageArea
            protected float toWorldPositionX(float f2) {
                return AbsBarrageRender.this.toCustomWorldPositionX(f2);
            }
        };
        this.mVerticalRect = new VerticalArea(this, i4) { // from class: com.duowan.kiwi.barrage.render.AbsBarrageRender.2
            @Override // com.duowan.kiwi.barrage.render.area.AbsBarrageArea
            public AbsTrace createBulletTrace(BulletBuilder.Bullet bullet, int i5) {
                return AbsBarrageRender.this.createTrace(bullet, i5);
            }

            @Override // com.duowan.kiwi.barrage.render.area.AbsBarrageArea
            protected float toWorldPositionY(float f2) {
                return AbsBarrageRender.this.toCustomWorldPositionY(f2);
            }
        };
        this.mFlashRect = new FlashArea(this, 10) { // from class: com.duowan.kiwi.barrage.render.AbsBarrageRender.3
            @Override // com.duowan.kiwi.barrage.render.area.AbsBarrageArea
            public AbsTrace createBulletTrace(BulletBuilder.Bullet bullet, int i5) {
                return AbsBarrageRender.this.createTrace(bullet, i5);
            }
        };
        this.mOrientation = new AtomicInteger(i2);
        this.mHorizontalRect.setDuration(i2);
        setLineCountByType(i);
        setAutoIncrease(i, z);
        initScale(i3);
    }

    @Override // com.duowan.kiwi.barrage.render.IBarrageRender
    public boolean isBarrageRenderOn() {
        return this.mBarrageRenderOn.get();
    }

    @Override // com.duowan.kiwi.barrage.render.IBarrageRender
    public void setBarrageRenderOn(boolean z) {
        BarrageLog.info("[Barrage]render", "enter setBarrageRenderOn:%b", Boolean.valueOf(z));
        this.mBarrageRenderOn.set(z);
    }

    protected void recycleUnusedFrame() {
        ArrayList<T> arrayListPollAnimations = pollAnimations();
        for (T t : arrayListPollAnimations) {
            if (t.mDuration <= t.mCurrentTime && !repeat(t)) {
                t.recycle();
                if (t.mListener != null) {
                    t.mListener.onAnimationEnd(t);
                }
            } else {
                addAnimation(t);
            }
        }
        pollAnimationsEnd(arrayListPollAnimations);
    }

    private boolean repeat(T t) {
        if (-1 != t.mRepeatCount && t.mRepeatCount <= t.mRepeated) {
            return false;
        }
        if (t.mRepeatCount > t.mRepeated) {
            t.mRepeated++;
        }
        if (1 == t.mRepeatModel) {
            int length = t.mHolds.length;
            for (int i = 0; i < length; i++) {
                float[] fArr = t.mHolds[i];
                float f = fArr[0];
                fArr[0] = fArr[1];
                fArr[1] = f;
            }
        }
        t.init();
        return true;
    }

    @Override // com.duowan.kiwi.barrage.render.IBarrageRender
    public void setOrientation(int i, boolean z) {
        BarrageLog.info("[Barrage]render", "mOrientation.get() = %d,  orientation = %d", Integer.valueOf(this.mOrientation.get()), Integer.valueOf(i));
        if (this.mOrientation.get() != i || z) {
            this.mOrientation.set(i);
            initScale(BarrageConfig.DEFAULT_BARRAGE_SIZE);
        }
        this.mHorizontalRect.setDuration(i);
    }

    private void initScale(int i) {
        if (i == BarrageConfig.DEFAULT_BARRAGE_SIZE) {
            if (this.mOrientation.get() == 2) {
                i = BarrageConfig.getBarrageSize();
            } else {
                i = BarrageConfig.getVerticalBarrageSize();
            }
        }
        BarrageLog.info("[Barrage]render", "initBarrageSize = %d", Integer.valueOf(i));
        this.mScale = (i * 1.0f) / BarrageConfig.sBaseLandscapeSize;
    }

    public void setRect(int i, int i2, int i3, int i4) {
        int i5;
        this.mVerticalRect.setRect(i3 / 10, 0, i3, i4);
        this.mFlashRect.setRect(0, i4 / 2, i3, i4);
        if (!this.mBarrageView.hasCustomTopMargin()) {
            if (this.mOrientation.get() == 2) {
                i5 = BarrageConfig.LANDSCAPE_TOP_MARGIN;
            } else {
                i5 = BarrageConfig.PORTRAIT_TOP_MARGIN;
                i4 -= BarrageConfig.PORTRAIT_BOTTOM_MARGIN;
            }
        } else {
            i5 = BarrageConfig.FLOATING_TOP_MARGIN;
        }
        this.mHorizontalRect.setRect(0, i5, i3, i4);
        setHorizontalRectLineCount(this.mHorizontalRect.isAutoIncrease());
    }

    private void setHorizontalRectLineCount(boolean z) {
        if (z) {
            this.mHorizontalRect.resetMaxLineCount();
        } else {
            setLineCount(this.mHorizontalRect, 1, this.mOrientation.get() == 2 ? 5 : 3);
        }
    }

    @Override // com.duowan.kiwi.barrage.render.IBarrageRender
    public void offer(GunPowder gunPowder, int i) {
        if (i == 1) {
            this.mHorizontalRect.offer(gunPowder);
        } else if (i == 16) {
            this.mVerticalRect.offer(gunPowder);
        } else {
            if (i != 256) {
                return;
            }
            this.mFlashRect.offer(gunPowder);
        }
    }

    public void cleanQueue(boolean z) {
        this.mHorizontalRect.reset(z);
        this.mVerticalRect.reset(z);
        this.mFlashRect.reset(z);
    }

    @Override // com.duowan.kiwi.barrage.render.IBarrageRender
    public void ceaseFire(boolean z) {
        ceaseFire(z, true);
    }

    public void ceaseFire(boolean z, boolean z2) {
        BarrageLog.info("[Barrage]render", "clearAll:%b, clearCache:%b", Boolean.valueOf(z), Boolean.valueOf(z2));
        if (z) {
            clearAnimations(null);
        } else {
            clearAnimations(this.mBarrageMatcher);
        }
        cleanQueue(z2);
    }

    public void setAlpha(float f) {
        if (f != this.mAlpha) {
            this.mAlpha = f;
            for (T t : getAnimations()) {
                if (256 != t.getTarget()) {
                    t.setAlpha(f);
                }
            }
        }
    }

    @Override // com.duowan.kiwi.barrage.render.IBarrageRender
    public void onBarrageSizeChanged(int i) {
        float f = (i * 1.0f) / BarrageConfig.sBaseLandscapeSize;
        if (f == this.mScale) {
            return;
        }
        this.mScale = f;
        this.mHorizontalRect.resetMaxLineCount();
    }

    public int getBarrageType() {
        return this.mType;
    }

    private void setLineCountByType(int i) {
        if (i == this.mType) {
            return;
        }
        this.mType = i;
        if (1 != (i & 1)) {
            setLineCount(this.mHorizontalRect, 1, 0);
        }
        setLineCount(this.mVerticalRect, 16, 16 == (this.mType & 16) ? 5 : 0);
        setLineCount(this.mFlashRect, 256, 256 == (this.mType & 256) ? 10 : 0);
    }

    @Override // com.duowan.kiwi.barrage.render.IBarrageRender
    public void setAutoIncrease(int i, boolean z) {
        if (1 == (i & 1)) {
            this.mHorizontalRect.setAutoIncrease(z);
            setHorizontalRectLineCount(z);
        }
    }

    @Override // com.duowan.kiwi.barrage.render.IBarrageRender
    public void setBarrageType(int i) {
        setLineCountByType(i);
        BarrageLog.info("[Barrage]render", "mOrientation = %d, GLBarrage = %d", Integer.valueOf(this.mOrientation.get()), Integer.valueOf(getBarrageType()));
        if (getBarrageType() != 0) {
            this.mBarrageOn.set(true);
            BarrageLog.info("[Barrage]render", "setBarrageType mBarrageOn.set(true)");
        } else {
            this.mBarrageOn.set(false);
            BarrageLog.info("[Barrage]render", "setBarrageType mBarrageOn.set(false)");
        }
    }

    @Override // com.duowan.kiwi.barrage.render.IBarrageRender
    public boolean isBarrageOn() {
        return this.mBarrageOn.get();
    }

    private synchronized void setLineCount(AbsBarrageArea absBarrageArea, int i, int i2) {
        absBarrageArea.setQueueLimited(i2);
        int lineCount = absBarrageArea.getLineCount();
        if (lineCount == i2) {
            return;
        }
        if (i2 < lineCount) {
            ArrayList<T> arrayListPollAnimations = pollAnimations();
            for (T t : arrayListPollAnimations) {
                if (t.mLineIndex >= i2 && i == t.getTarget()) {
                    t.recycle();
                } else {
                    addAnimation(t);
                }
            }
            pollAnimationsEnd(arrayListPollAnimations);
        }
        absBarrageArea.setLineCount(i2, getAnimations());
    }

    @Override // com.duowan.kiwi.barrage.render.IRenderConfig
    public BulletBuilder getShellBuilder() {
        return this.mBulletBuilder;
    }

    @Override // com.duowan.kiwi.barrage.render.IRenderConfig
    public float getAlpha() {
        return this.mAlpha;
    }

    @Override // com.duowan.kiwi.barrage.render.IRenderConfig
    public float getScale() {
        return this.mScale;
    }

    @Override // com.duowan.kiwi.barrage.render.IRenderConfig
    public int getLineSpace() {
        if (this.mOrientation.get() == 2) {
        }
        return 1;
    }

    @Override // com.duowan.kiwi.barrage.render.IRenderConfig
    public int getSpaceX() {
        if (this.mOrientation.get() == 2) {
            return BarrageConfig.LANDSCAPE_SPACE_X;
        }
        return BarrageConfig.PORTRAIT_SPACE_X;
    }

    public void calculateBarrage(float f) {
        boolean zOnPreCalculateBarrage = onPreCalculateBarrage();
        ArrayList<T> arrayListPollAnimations = pollAnimations();
        for (T t : arrayListPollAnimations) {
            calculateCurrentFrameReal(t, f);
            if (zOnPreCalculateBarrage && t != null && !t.mHasFollower) {
                zOnPreCalculateBarrage = tryAddFollower(t);
            }
            addAnimation(t);
        }
        pollAnimationsEnd(arrayListPollAnimations);
        if (zOnPreCalculateBarrage) {
            onCalculateFinish();
        }
    }

    private boolean onPreCalculateBarrage() {
        return this.mHorizontalRect.onPreCalculateBarrage();
    }

    private void onCalculateFinish() {
        this.mHorizontalRect.onCalculateFinish();
    }

    private boolean tryAddFollower(AbsTrace absTrace) {
        int target = absTrace.getTarget();
        if (target == 1) {
            return this.mHorizontalRect.calculateBarrageReal(absTrace);
        }
        if (target == 16) {
            return this.mVerticalRect.calculateBarrageReal(absTrace);
        }
        if (target != 256) {
            return true;
        }
        return this.mFlashRect.calculateBarrageReal(absTrace);
    }

    public boolean isEmpty() {
        return this.mAnimations.isEmpty();
    }

    protected ArrayList<T> pollAnimations() {
        ArrayList<T> arrayList = this.mAnimations;
        this.mAnimations = this.mArrayListPoolFactory.obtain();
        return arrayList;
    }

    public void pollAnimationsEnd(ArrayList arrayList) {
        this.mArrayListPoolFactory.recycle(arrayList);
    }

    @Override // com.duowan.kiwi.barrage.render.IRenderConfig
    public void addAnimation(T t) {
        this.mAnimations.add(t);
    }

    public void clearAnimations(OnRemoveAnimMatcher onRemoveAnimMatcher) {
        if (onRemoveAnimMatcher != null) {
            ListIterator<T> listIterator = this.mAnimations.listIterator();
            while (listIterator.hasNext()) {
                T next = listIterator.next();
                if (onRemoveAnimMatcher.isMatch(next)) {
                    next.recycle();
                    listIterator.remove();
                }
            }
            return;
        }
        Iterator<T> it = this.mAnimations.iterator();
        while (it.hasNext()) {
            it.next().recycle();
        }
        this.mAnimations.clear();
    }

    protected void calculateCurrentFrameReal(T t, float f) {
        int length = t.mHolds.length;
        for (int i = 0; i < length; i++) {
            t.stepCurrentFrame(i, t.mSpeeds[i] * f);
        }
        t.mCurrentTime += f;
    }

    @Override // com.duowan.kiwi.barrage.render.IRenderConfig
    public ArrayList<T> getAnimations() {
        return this.mAnimations;
    }

    @Override // com.duowan.kiwi.barrage.render.IRenderConfig
    public boolean isFixedQueue() {
        return this.mBarrageView.isQueueFixed();
    }

    @Override // com.duowan.kiwi.barrage.render.IRenderConfig
    public int getFixedLine() {
        return this.mBarrageView.getQueueLine();
    }
}
