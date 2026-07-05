package com.duowan.kiwi.barrage;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Build;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.View;
import com.duowan.kiwi.barrage.BarrageEvent;
import com.duowan.kiwi.barrage.config.BarrageConfig;
import com.duowan.kiwi.barrage.config.BarrageLog;
import com.duowan.kiwi.barrage.render.BarrageRender;
import com.duowan.kiwi.barrage.render.IBarrageRender;
import com.duowan.kiwi.barrage.render.draw.DrawHelper;
import com.duowan.kiwi.barrage.report.BarrageCacheForReport;
import com.duowan.kiwi.barrage.utils.BarrageFpsHelper;
import com.duowan.kiwi.barrage.view.IBarrageViewController;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class BarrageView extends View implements IBarrageViewController {
    private static final String TAG = BarrageView.class.getSimpleName();
    private boolean isSurfaceCreated;
    private BarrageFpsHelper mBarrageFpsHelper;
    private boolean mDrawFinished;
    private final Object mDrawMonitor;
    protected volatile BarrageRender mHandler;
    protected AtomicInteger mModel;
    private IBarrageRender mRender;

    @Override // com.duowan.kiwi.barrage.view.IBarrageViewController
    public void clearCanvas() {
    }

    @Override // com.duowan.kiwi.barrage.view.IBarrageConfigView
    public int getQueueLine() {
        return 0;
    }

    @Override // com.duowan.kiwi.barrage.view.IBarrageView
    public boolean hasCustomTopMargin() {
        return false;
    }

    @Override // com.duowan.kiwi.barrage.view.IBarrageConfigView
    public boolean isQueueFixed() {
        return false;
    }

    public BarrageView(Context context) {
        super(context);
        this.mDrawMonitor = new Object();
        this.mDrawFinished = false;
        this.mBarrageFpsHelper = BarrageFpsHelper.create();
        initBarrageView();
    }

    public BarrageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mDrawMonitor = new Object();
        this.mDrawFinished = false;
        this.mBarrageFpsHelper = BarrageFpsHelper.create();
        initBarrageView();
    }

    public BarrageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mDrawMonitor = new Object();
        this.mDrawFinished = false;
        this.mBarrageFpsHelper = BarrageFpsHelper.create();
        initBarrageView();
    }

    private void initBarrageView() {
        initBarrageModel();
        this.mRender = new BarrageRender(this, BarrageConfig.modelToType(getBarrageModel()), 1 == getBarrageModel(), getResources().getConfiguration().orientation, getInitAlpha(), getInitSize());
        BarrageCacheForReport.getInstance().switchReport(false);
    }

    protected void initBarrageModel() {
        this.mModel = new AtomicInteger(BarrageConfig.getBarrageModel());
    }

    protected int getBarrageModel() {
        return this.mModel.get();
    }

    protected float getInitAlpha() {
        return BarrageConfig.getBarrageAlpha();
    }

    protected int getInitSize() {
        return BarrageConfig.DEFAULT_BARRAGE_SIZE;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        if (this.mRender.isBarrageOn() && this.mRender.isBarrageRenderOn()) {
            this.mRender.draw(canvas);
            this.mBarrageFpsHelper.update();
            if (BarrageConfig.isBarrageRefreshPrint()) {
                DrawHelper.drawFPS(canvas, this.mBarrageFpsHelper.getFpsStr());
            }
            unlockCanvasAndPost();
            return;
        }
        super.onDraw(canvas);
    }

    @Override // android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        IBarrageRender iBarrageRender = this.mRender;
        if (iBarrageRender != null) {
            iBarrageRender.notifyDispSizeChanged(i3 - i, i4 - i2);
        }
        this.isSurfaceCreated = true;
    }

    @Override // com.duowan.kiwi.barrage.view.IBarrageViewController
    public IBarrageRender getRender() {
        return this.mRender;
    }

    @Override // com.duowan.kiwi.barrage.view.IBarrageView
    public void switchRender(boolean z) {
        BarrageLog.info(TAG, "switchRender %b", Boolean.valueOf(z));
        if (z) {
            this.mRender.resetSmooth();
            this.mRender.start();
        } else {
            this.mRender.stop();
            unlockCanvasAndPost();
        }
        this.mRender.setBarrageRenderOn(z);
    }

    @Override // com.duowan.kiwi.barrage.view.IBarrageView
    public void offerGunPowder(GunPowder gunPowder, int i) {
        this.mRender.offer(gunPowder, i);
    }

    @Override // com.duowan.kiwi.barrage.view.IBarrageView
    public void onBarrageAlphaChanged(BarrageEvent.BarrageAlphaChanged barrageAlphaChanged) {
        this.mRender.setBarrageAlpha(barrageAlphaChanged.arg0.floatValue());
    }

    @Override // com.duowan.kiwi.barrage.view.IBarrageView
    public void onBarrageSizeChanged(BarrageEvent.BarrageSizeChanged barrageSizeChanged) {
        this.mRender.onBarrageSizeChanged(barrageSizeChanged.arg0.intValue());
    }

    @Override // com.duowan.kiwi.barrage.view.IBarrageView
    public void onBarrageModelChanged(BarrageEvent.BarrageModelChanged barrageModelChanged) {
        updateBarrageModel(barrageModelChanged.mode);
    }

    protected void updateBarrageModel(int i) {
        if (i != getBarrageModel()) {
            int barrageModel = getBarrageModel();
            this.mModel.set(i);
            int iModelToType = BarrageConfig.modelToType(i);
            this.mRender.setAutoIncrease(iModelToType, 1 == i);
            this.mRender.setBarrageType(iModelToType);
            if (i == 0) {
                this.mRender.ceaseFire(true);
            } else if (barrageModel == 0) {
                this.mRender.resetSmooth();
                switchRender(true);
            }
        }
    }

    private void unlockCanvasAndPost() {
        synchronized (this.mDrawMonitor) {
            this.mDrawFinished = true;
            this.mDrawMonitor.notifyAll();
        }
    }

    protected void lockCanvas() {
        postInvalidateCompat();
        synchronized (this.mDrawMonitor) {
            while (!this.mDrawFinished && this.mRender != null) {
                try {
                    this.mDrawMonitor.wait(200L);
                } catch (InterruptedException unused) {
                    if (this.mRender == null || this.mRender.isStop()) {
                        break;
                    } else {
                        Thread.currentThread().interrupt();
                    }
                    this.mDrawFinished = false;
                }
            }
            this.mDrawFinished = false;
        }
    }

    private void postInvalidateCompat() {
        if (Build.VERSION.SDK_INT >= 16) {
            postInvalidateOnAnimation();
        } else {
            postInvalidate();
        }
    }

    @Override // com.duowan.kiwi.barrage.view.IBarrageViewController
    public long drawDanmakus() {
        if (!this.isSurfaceCreated) {
            return 0L;
        }
        if (!isShown()) {
            return -1L;
        }
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        lockCanvas();
        return SystemClock.elapsedRealtime() - jElapsedRealtime;
    }

    @Override // com.duowan.kiwi.barrage.view.IBarrageViewController
    public boolean isViewReady() {
        return this.isSurfaceCreated;
    }

    @Override // com.duowan.kiwi.barrage.view.IBarrageViewController
    public float getFps() {
        return this.mBarrageFpsHelper.fps();
    }
}
