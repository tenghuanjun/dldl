package com.duowan.kiwi.barrage;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.SystemClock;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
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
public class BarrageSurfaceView extends SurfaceView implements IBarrageViewController, SurfaceHolder.Callback {
    private static final String TAG = BarrageSurfaceView.class.getSimpleName();
    private BarrageFpsHelper mBarrageFpsHelper;
    protected AtomicInteger mModel;
    private IBarrageRender mRender;
    private boolean mSurfaceCreated;
    private SurfaceHolder mSurfaceHolder;

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

    public BarrageSurfaceView(Context context) {
        super(context);
        this.mBarrageFpsHelper = BarrageFpsHelper.create();
        init();
        initBarrageView();
    }

    private void init() {
        setZOrderMediaOverlay(true);
        setWillNotCacheDrawing(true);
        setDrawingCacheEnabled(false);
        setWillNotDraw(true);
        SurfaceHolder holder = getHolder();
        this.mSurfaceHolder = holder;
        holder.addCallback(this);
        this.mSurfaceHolder.setFormat(-2);
        DrawHelper.useDrawColorToClearCanvas(true, true);
    }

    private void initBarrageView() {
        initBarrageModel();
        this.mRender = new BarrageRender(this, BarrageConfig.modelToType(getBarrageModel()), 1 == getBarrageModel(), getResources().getConfiguration().orientation, getInitAlpha(), getInitSize()) { // from class: com.duowan.kiwi.barrage.BarrageSurfaceView.1
            @Override // com.duowan.kiwi.barrage.render.BarrageRender
            protected void realDrawFrame(Canvas canvas, BarrageRender.BulletTrace bulletTrace, Paint paint) {
                canvas.drawBitmap(bulletTrace.mBullet.getCacheObject().getContent(), bulletTrace.getCurrentFrame().mX, bulletTrace.getCurrentFrame().mY, paint);
            }
        };
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

    @Override // com.duowan.kiwi.barrage.view.IBarrageViewController
    public long drawDanmakus() {
        if (!this.mSurfaceCreated) {
            return 0L;
        }
        if (!isShown()) {
            return -1L;
        }
        long jUptimeMillis = SystemClock.uptimeMillis();
        IBarrageRender iBarrageRender = this.mRender;
        if (iBarrageRender != null && iBarrageRender.isBarrageOn() && this.mRender.isBarrageRenderOn()) {
            try {
                Canvas canvasLockCanvas = this.mSurfaceHolder.lockCanvas();
                if (canvasLockCanvas != null) {
                    this.mRender.draw(canvasLockCanvas);
                    this.mBarrageFpsHelper.update();
                    if (BarrageConfig.isBarrageRefreshPrint()) {
                        DrawHelper.drawFPS(canvasLockCanvas, this.mBarrageFpsHelper.getFpsStr());
                    }
                }
                if (this.mSurfaceCreated) {
                    this.mSurfaceHolder.unlockCanvasAndPost(canvasLockCanvas);
                }
            } catch (Exception e) {
                BarrageLog.error(TAG, "BarrageSurfaceView.drawDanmakus error, %s", e);
            }
        }
        return SystemClock.uptimeMillis() - jUptimeMillis;
    }

    @Override // com.duowan.kiwi.barrage.view.IBarrageViewController
    public boolean isViewReady() {
        return this.mSurfaceCreated;
    }

    @Override // com.duowan.kiwi.barrage.view.IBarrageViewController
    public float getFps() {
        return this.mBarrageFpsHelper.fps();
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        Canvas canvasLockCanvas = surfaceHolder.lockCanvas();
        if (canvasLockCanvas != null) {
            DrawHelper.clearCanvas(canvasLockCanvas);
            surfaceHolder.unlockCanvasAndPost(canvasLockCanvas);
        }
        IBarrageRender iBarrageRender = this.mRender;
        if (iBarrageRender != null) {
            iBarrageRender.ceaseFire(true);
            this.mRender.resetSmooth();
        }
        this.mSurfaceCreated = true;
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        IBarrageRender iBarrageRender = this.mRender;
        if (iBarrageRender != null) {
            iBarrageRender.notifyDispSizeChanged(i2, i3);
        }
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        this.mSurfaceCreated = false;
        IBarrageRender iBarrageRender = this.mRender;
        if (iBarrageRender != null) {
            iBarrageRender.ceaseFire(true);
            this.mRender.resetSmooth();
        }
    }
}
