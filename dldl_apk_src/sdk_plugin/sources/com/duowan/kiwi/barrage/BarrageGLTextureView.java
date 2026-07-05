package com.duowan.kiwi.barrage;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.SurfaceTexture;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.TextureView;
import com.duowan.kiwi.barrage.BarrageEvent;
import com.duowan.kiwi.barrage.config.BarrageConfig;
import com.duowan.kiwi.barrage.config.BarrageLog;
import com.duowan.kiwi.barrage.render.BarrageRender;
import com.duowan.kiwi.barrage.render.IBarrageRender;
import com.duowan.kiwi.barrage.render.draw.DrawHelper;
import com.duowan.kiwi.barrage.utils.BarrageFpsHelper;
import com.duowan.kiwi.barrage.view.IBarrageViewController;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class BarrageGLTextureView extends TextureView implements IBarrageViewController, TextureView.SurfaceTextureListener {
    private static final String TAG = "BarrageGLTextureView";
    private BarrageFpsHelper mBarrageFpsHelper;
    private boolean mIsSurfaceCreated;
    protected AtomicInteger mModel;
    private IBarrageRender mRender;

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

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
    }

    public BarrageGLTextureView(Context context) {
        super(context);
        this.mBarrageFpsHelper = BarrageFpsHelper.create();
        initBarrageView();
    }

    public BarrageGLTextureView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mBarrageFpsHelper = BarrageFpsHelper.create();
        initBarrageView();
    }

    public BarrageGLTextureView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mBarrageFpsHelper = BarrageFpsHelper.create();
        initBarrageView();
    }

    private void initTextureView() {
        setLayerType(2, null);
        setOpaque(false);
        setWillNotCacheDrawing(true);
        setDrawingCacheEnabled(false);
        initTextureListener();
    }

    private void initTextureListener() {
        setSurfaceTextureListener(this);
    }

    private void initBarrageView() {
        initTextureView();
        initBarrageModel();
        this.mRender = new BarrageRender(this, BarrageConfig.modelToType(getBarrageModel()), 1 == getBarrageModel(), getResources().getConfiguration().orientation, getInitAlpha(), getInitSize());
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

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
        IBarrageRender iBarrageRender = this.mRender;
        if (iBarrageRender != null) {
            iBarrageRender.notifyDispSizeChanged(i, i2);
        }
        this.mIsSurfaceCreated = true;
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
        IBarrageRender iBarrageRender = this.mRender;
        if (iBarrageRender != null) {
            iBarrageRender.notifyDispSizeChanged(i, i2);
        }
        this.mIsSurfaceCreated = true;
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        this.mIsSurfaceCreated = false;
        return true;
    }

    @Override // com.duowan.kiwi.barrage.view.IBarrageViewController
    public IBarrageRender getRender() {
        return this.mRender;
    }

    @Override // com.duowan.kiwi.barrage.view.IBarrageView
    public void switchRender(boolean z) {
        BarrageLog.debug(TAG, "switchRender=%b", Boolean.valueOf(z));
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
        if (!this.mIsSurfaceCreated) {
            return 0L;
        }
        if (!isShown()) {
            return -1L;
        }
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        Canvas canvasLockCanvas = lockCanvas();
        if (canvasLockCanvas != null) {
            IBarrageRender iBarrageRender = this.mRender;
            if (iBarrageRender != null && iBarrageRender.isBarrageOn() && this.mRender.isBarrageRenderOn()) {
                this.mRender.draw(canvasLockCanvas);
                this.mBarrageFpsHelper.update();
                if (BarrageConfig.isBarrageRefreshPrint()) {
                    DrawHelper.drawFPS(canvasLockCanvas, this.mBarrageFpsHelper.getFpsStr());
                }
            }
            if (this.mIsSurfaceCreated) {
                unlockCanvasAndPost(canvasLockCanvas);
            }
        }
        return SystemClock.elapsedRealtime() - jElapsedRealtime;
    }

    @Override // com.duowan.kiwi.barrage.view.IBarrageViewController
    public boolean isViewReady() {
        return this.mIsSurfaceCreated;
    }

    @Override // com.duowan.kiwi.barrage.view.IBarrageViewController
    public void clearCanvas() {
        Canvas canvasLockCanvas;
        if (isViewReady() && (canvasLockCanvas = lockCanvas()) != null) {
            DrawHelper.clearCanvas(canvasLockCanvas);
            unlockCanvasAndPost(canvasLockCanvas);
        }
    }

    @Override // com.duowan.kiwi.barrage.view.IBarrageViewController
    public float getFps() {
        return this.mBarrageFpsHelper.fps();
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }
}
