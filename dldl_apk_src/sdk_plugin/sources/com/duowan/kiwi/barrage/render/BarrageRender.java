package com.duowan.kiwi.barrage.render;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.view.Choreographer;
import com.duowan.ark.util.ThreadUtils;
import com.duowan.kiwi.barrage.GunPowder;
import com.duowan.kiwi.barrage.config.BarrageLog;
import com.duowan.kiwi.barrage.newcache.AbsDrawingCache;
import com.duowan.kiwi.barrage.render.AbsBarrageRender;
import com.duowan.kiwi.barrage.render.draw.BulletBuilder;
import com.duowan.kiwi.barrage.render.draw.DrawHelper;
import com.duowan.kiwi.barrage.trace.AbsTrace;
import com.duowan.kiwi.barrage.view.IBarrageViewController;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class BarrageRender extends AbsBarrageRender<BulletTrace, Bitmap> {
    private static final int ALPHA = 5;
    private static final int CLEAR_CANVAS = 3;
    private static final int RECT_CHANGED = 4;
    private static final int START = 0;
    private static final int STOP = 1;
    private static final int UPDATE = 2;
    private AtomicBoolean mBarrageOn;
    private IBarrageViewController mBarrageViewController;
    private Paint mBitPaint;
    private Choreographer.FrameCallback mFrameCallback;
    private Handler mHandler;
    private SmoothDeltaTime mSmoothDelta;
    private AtomicBoolean mStopped;
    private static final String TAG = BarrageRender.class.getSimpleName();
    private static final HandlerThread RENDER_HANDLER_THREAD = ThreadUtils.newStartHandlerThread("BarrageRenderThread");

    @Override // com.duowan.kiwi.barrage.render.IBarrageRender
    public void onRequireMarqueeInSurface(Bitmap bitmap, float f, long j) {
    }

    @Override // com.duowan.kiwi.barrage.render.AbsBarrageRender
    public /* bridge */ /* synthetic */ void calculateBarrage(float f) {
        super.calculateBarrage(f);
    }

    @Override // com.duowan.kiwi.barrage.render.AbsBarrageRender
    public /* bridge */ /* synthetic */ void ceaseFire(boolean z, boolean z2) {
        super.ceaseFire(z, z2);
    }

    @Override // com.duowan.kiwi.barrage.render.AbsBarrageRender
    public /* bridge */ /* synthetic */ void cleanQueue(boolean z) {
        super.cleanQueue(z);
    }

    @Override // com.duowan.kiwi.barrage.render.AbsBarrageRender
    public /* bridge */ /* synthetic */ void clearAnimations(AbsBarrageRender.OnRemoveAnimMatcher onRemoveAnimMatcher) {
        super.clearAnimations(onRemoveAnimMatcher);
    }

    @Override // com.duowan.kiwi.barrage.render.AbsBarrageRender, com.duowan.kiwi.barrage.render.IRenderConfig
    public /* bridge */ /* synthetic */ float getAlpha() {
        return super.getAlpha();
    }

    @Override // com.duowan.kiwi.barrage.render.AbsBarrageRender, com.duowan.kiwi.barrage.render.IRenderConfig
    public /* bridge */ /* synthetic */ ArrayList getAnimations() {
        return super.getAnimations();
    }

    @Override // com.duowan.kiwi.barrage.render.AbsBarrageRender
    public /* bridge */ /* synthetic */ int getBarrageType() {
        return super.getBarrageType();
    }

    @Override // com.duowan.kiwi.barrage.render.AbsBarrageRender, com.duowan.kiwi.barrage.render.IRenderConfig
    public /* bridge */ /* synthetic */ int getFixedLine() {
        return super.getFixedLine();
    }

    @Override // com.duowan.kiwi.barrage.render.AbsBarrageRender, com.duowan.kiwi.barrage.render.IRenderConfig
    public /* bridge */ /* synthetic */ int getLineSpace() {
        return super.getLineSpace();
    }

    @Override // com.duowan.kiwi.barrage.render.AbsBarrageRender, com.duowan.kiwi.barrage.render.IRenderConfig
    public /* bridge */ /* synthetic */ float getScale() {
        return super.getScale();
    }

    @Override // com.duowan.kiwi.barrage.render.AbsBarrageRender, com.duowan.kiwi.barrage.render.IRenderConfig
    public /* bridge */ /* synthetic */ BulletBuilder getShellBuilder() {
        return super.getShellBuilder();
    }

    @Override // com.duowan.kiwi.barrage.render.AbsBarrageRender, com.duowan.kiwi.barrage.render.IRenderConfig
    public /* bridge */ /* synthetic */ int getSpaceX() {
        return super.getSpaceX();
    }

    @Override // com.duowan.kiwi.barrage.render.AbsBarrageRender, com.duowan.kiwi.barrage.render.IBarrageRender
    public /* bridge */ /* synthetic */ boolean isBarrageRenderOn() {
        return super.isBarrageRenderOn();
    }

    @Override // com.duowan.kiwi.barrage.render.AbsBarrageRender
    public /* bridge */ /* synthetic */ boolean isEmpty() {
        return super.isEmpty();
    }

    @Override // com.duowan.kiwi.barrage.render.AbsBarrageRender, com.duowan.kiwi.barrage.render.IRenderConfig
    public /* bridge */ /* synthetic */ boolean isFixedQueue() {
        return super.isFixedQueue();
    }

    @Override // com.duowan.kiwi.barrage.render.AbsBarrageRender, com.duowan.kiwi.barrage.render.IBarrageRender
    public /* bridge */ /* synthetic */ void onBarrageSizeChanged(int i) {
        super.onBarrageSizeChanged(i);
    }

    @Override // com.duowan.kiwi.barrage.render.AbsBarrageRender
    public /* bridge */ /* synthetic */ void pollAnimationsEnd(ArrayList arrayList) {
        super.pollAnimationsEnd(arrayList);
    }

    @Override // com.duowan.kiwi.barrage.render.AbsBarrageRender
    public /* bridge */ /* synthetic */ void setAlpha(float f) {
        super.setAlpha(f);
    }

    @Override // com.duowan.kiwi.barrage.render.AbsBarrageRender, com.duowan.kiwi.barrage.render.IBarrageRender
    public /* bridge */ /* synthetic */ void setAutoIncrease(int i, boolean z) {
        super.setAutoIncrease(i, z);
    }

    @Override // com.duowan.kiwi.barrage.render.AbsBarrageRender, com.duowan.kiwi.barrage.render.IBarrageRender
    public /* bridge */ /* synthetic */ void setBarrageRenderOn(boolean z) {
        super.setBarrageRenderOn(z);
    }

    @Override // com.duowan.kiwi.barrage.render.AbsBarrageRender, com.duowan.kiwi.barrage.render.IBarrageRender
    public /* bridge */ /* synthetic */ void setOrientation(int i, boolean z) {
        super.setOrientation(i, z);
    }

    @Override // com.duowan.kiwi.barrage.render.AbsBarrageRender
    public /* bridge */ /* synthetic */ void setRect(int i, int i2, int i3, int i4) {
        super.setRect(i, i2, i3, i4);
    }

    public BarrageRender(IBarrageViewController iBarrageViewController, int i, boolean z, int i2, float f, int i3) {
        super(iBarrageViewController, i, z, i2, f, i3);
        this.mFrameCallback = new Choreographer.FrameCallback() { // from class: com.duowan.kiwi.barrage.render.BarrageRender.1
            @Override // android.view.Choreographer.FrameCallback
            public void doFrame(long j) {
                BarrageRender.this.mHandler.sendEmptyMessage(2);
            }
        };
        this.mStopped = new AtomicBoolean(false);
        this.mBarrageOn = new AtomicBoolean(true);
        this.mSmoothDelta = new SmoothDeltaTime();
        Paint paint = new Paint(1);
        this.mBitPaint = paint;
        paint.setFilterBitmap(true);
        this.mBitPaint.setDither(true);
        this.mBarrageOn.set(getBarrageType() != 0);
        this.mBarrageViewController = iBarrageViewController;
        this.mHandler = new Handler(RENDER_HANDLER_THREAD.getLooper(), new Handler.Callback() { // from class: com.duowan.kiwi.barrage.render.BarrageRender.2
            @Override // android.os.Handler.Callback
            public boolean handleMessage(Message message) {
                int i4 = message.what;
                if (i4 != 0) {
                    if (i4 != 1) {
                        if (i4 != 2) {
                            if (i4 != 3) {
                                if (i4 == 4) {
                                    BarrageRender.this.setRect(0, 0, message.arg1, message.arg2);
                                } else if (i4 == 5) {
                                    BarrageRender.this.setAlpha(((Float) message.obj).floatValue());
                                }
                            }
                        }
                        return false;
                    }
                    Choreographer.getInstance().removeFrameCallback(BarrageRender.this.mFrameCallback);
                    BarrageRender.this.mBarrageViewController.clearCanvas();
                    return false;
                }
                BarrageRender.this.mSmoothDelta.start();
                BarrageRender.this.updateInChoreographer();
                return false;
            }
        });
    }

    @Override // com.duowan.kiwi.barrage.render.AbsBarrageRender, com.duowan.kiwi.barrage.render.IBarrageRender
    public void start() {
        this.mHandler.removeCallbacksAndMessages(null);
        this.mStopped.set(false);
        this.mHandler.sendEmptyMessage(2);
    }

    @Override // com.duowan.kiwi.barrage.render.AbsBarrageRender, com.duowan.kiwi.barrage.render.IBarrageRender
    public void stop() {
        this.mStopped.set(true);
        this.mHandler.sendEmptyMessage(1);
    }

    @Override // com.duowan.kiwi.barrage.render.IBarrageRender
    public boolean isStop() {
        return this.mStopped.get();
    }

    @Override // com.duowan.kiwi.barrage.render.AbsBarrageRender, com.duowan.kiwi.barrage.render.IBarrageRender
    public void offer(GunPowder gunPowder, int i) {
        if (this.mBarrageViewController.isViewReady()) {
            super.offer(gunPowder, i);
        }
    }

    @Override // com.duowan.kiwi.barrage.render.AbsBarrageRender, com.duowan.kiwi.barrage.render.IBarrageRender
    public void ceaseFire(final boolean z) {
        queueEvent(new Runnable() { // from class: com.duowan.kiwi.barrage.render.BarrageRender.3
            @Override // java.lang.Runnable
            public void run() {
                BarrageRender.super.ceaseFire(z);
            }
        });
    }

    private void queueEvent(Runnable runnable) {
        this.mHandler.post(runnable);
    }

    @Override // com.duowan.kiwi.barrage.render.IBarrageRender
    public long getCurrentTime() {
        return this.mSmoothDelta.getCurrentTime();
    }

    @Override // com.duowan.kiwi.barrage.render.IBarrageRender
    public void resetSmooth() {
        SmoothDeltaTime smoothDeltaTime = this.mSmoothDelta;
        if (smoothDeltaTime != null) {
            smoothDeltaTime.reset();
        }
    }

    @Override // com.duowan.kiwi.barrage.render.IBarrageRender
    public void setBarrageAlpha(float f) {
        this.mHandler.obtainMessage(5, Float.valueOf(f)).sendToTarget();
    }

    @Override // com.duowan.kiwi.barrage.render.AbsBarrageRender, com.duowan.kiwi.barrage.render.IBarrageRender
    public void setBarrageType(int i) {
        super.setBarrageType(i);
        if (getBarrageType() != 0) {
            this.mHandler.sendEmptyMessage(0);
        } else {
            this.mHandler.sendEmptyMessage(1);
        }
    }

    @Override // com.duowan.kiwi.barrage.render.AbsBarrageRender, com.duowan.kiwi.barrage.render.IBarrageRender
    public boolean isBarrageOn() {
        return this.mBarrageOn.get();
    }

    @Override // com.duowan.kiwi.barrage.render.IBarrageRender
    public void notifyDispSizeChanged(int i, int i2) {
        this.mHandler.obtainMessage(4, i, i2).sendToTarget();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.duowan.kiwi.barrage.render.AbsBarrageRender
    public BulletTrace createTrace(BulletBuilder.Bullet<Bitmap> bullet, int i) {
        return new BulletTrace(bullet, i);
    }

    @Override // com.duowan.kiwi.barrage.render.AbsBarrageRender, com.duowan.kiwi.barrage.render.IBarrageRender
    public void clearCanvas() {
        this.mHandler.obtainMessage(3).sendToTarget();
    }

    @Override // com.duowan.kiwi.barrage.render.AbsBarrageRender, com.duowan.kiwi.barrage.render.IBarrageRender
    public void draw(Canvas canvas) {
        if (this.mBarrageViewController.isViewReady()) {
            DrawHelper.clearCanvas(canvas);
        }
        recycleUnusedFrame();
        calculateBarrage(this.mSmoothDelta.getSmoothDelta());
        drawCurrentFrame(canvas);
        this.mSmoothDelta.recordRenderingTime();
    }

    protected void drawCurrentFrame(Canvas canvas) {
        ArrayList animations = getAnimations();
        int size = animations.size();
        while (true) {
            size--;
            if (size < 0) {
                break;
            }
            BulletTrace bulletTrace = (BulletTrace) animations.get(size);
            if (bulletTrace.mExplosive != 2 && bulletTrace.mExplosive != 3) {
                drawFrame(canvas, bulletTrace);
            }
        }
        for (int size2 = animations.size() - 1; size2 >= 0; size2--) {
            BulletTrace bulletTrace2 = (BulletTrace) animations.get(size2);
            if (bulletTrace2.mExplosive == 2) {
                drawFrame(canvas, bulletTrace2);
            }
        }
        for (int size3 = animations.size() - 1; size3 >= 0; size3--) {
            BulletTrace bulletTrace3 = (BulletTrace) animations.get(size3);
            if (bulletTrace3.mExplosive == 3) {
                drawFrame(canvas, bulletTrace3);
            }
        }
    }

    private void drawFrame(Canvas canvas, BulletTrace bulletTrace) {
        Bitmap content = bulletTrace.mBullet.getCacheObject().getContent();
        if (content == null || content.isRecycled()) {
            BarrageLog.error(TAG, "bitmap isnull or isRecycled");
            return;
        }
        Paint paint = new Paint(this.mBitPaint);
        paint.setAlpha((int) (getAlpha() * 255.0f));
        if (this.mBarrageViewController.isViewReady()) {
            realDrawFrame(canvas, bulletTrace, paint);
        }
    }

    protected void realDrawFrame(Canvas canvas, BulletTrace bulletTrace, Paint paint) {
        canvas.drawBitmap(bulletTrace.mBullet.getCacheObject().getContent(), (Rect) null, new Rect((int) bulletTrace.getCurrentFrame().mX, (int) bulletTrace.getCurrentFrame().mY, (int) ((r0.getWidth() * getScale()) + bulletTrace.getCurrentFrame().mX), (int) ((r0.getHeight() * getScale()) + bulletTrace.getCurrentFrame().mY)), paint);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateInChoreographer() {
        if (isStop() || !this.mBarrageViewController.isViewReady()) {
            BarrageLog.info(TAG, "updateInChoreographer return");
            return;
        }
        Choreographer.getInstance().postFrameCallback(this.mFrameCallback);
        if (this.mSmoothDelta.calcSmoothDelta() < 0) {
            this.mHandler.removeMessages(2);
            return;
        }
        this.mSmoothDelta.onDrawCost(this.mBarrageViewController.drawDanmakus());
        this.mHandler.removeMessages(2);
    }

    @Override // com.duowan.kiwi.barrage.newcache.DrawingFactory.BuildMachine
    public AbsDrawingCache<Bitmap> createDrawingCache(Bitmap bitmap) {
        return new AbsDrawingCache.ViewDrawingCache(bitmap);
    }

    public static class BulletTrace extends AbsTrace {
        public final BulletBuilder.Bullet<Bitmap> mBullet;

        public BulletTrace(BulletBuilder.Bullet<Bitmap> bullet, int i) {
            exploreBullet(bullet);
            this.mTarget = i;
            this.mBullet = bullet;
        }

        @Override // com.duowan.kiwi.barrage.trace.AbsTrace
        public void recycle() {
            BulletBuilder.Bullet<Bitmap> bullet = this.mBullet;
            if (bullet != null) {
                bullet.getCacheObject().decreaseReferenceCount();
            }
        }

        @Override // com.duowan.kiwi.barrage.trace.AbsTrace
        public AbsTrace x(float f, float f2) {
            this.mHolds[0][0] = f;
            this.mHolds[0][1] = f2;
            return this;
        }

        @Override // com.duowan.kiwi.barrage.trace.AbsTrace
        public AbsTrace y(float f, float f2) {
            this.mHolds[1][0] = f;
            this.mHolds[1][1] = f2;
            return this;
        }
    }
}
