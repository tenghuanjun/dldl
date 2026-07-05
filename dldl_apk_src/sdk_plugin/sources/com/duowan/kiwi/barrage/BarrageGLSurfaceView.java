package com.duowan.kiwi.barrage;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;
import android.widget.Toast;
import com.duowan.kiwi.barrage.BarrageEvent;
import com.duowan.kiwi.barrage.config.BarrageConfig;
import com.duowan.kiwi.barrage.config.BarrageContext;
import com.duowan.kiwi.barrage.config.BarrageLog;
import com.duowan.kiwi.barrage.render.GLBarrageRender;
import com.duowan.kiwi.barrage.render.IBarrageRender;
import com.duowan.kiwi.barrage.report.BarrageCacheForReport;
import com.duowan.kiwi.barrage.view.IGLBarrageView;
import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class BarrageGLSurfaceView extends GLSurfaceView implements IGLBarrageView {
    private static final int DELAY_FIRE_TIME = 2500;
    public static final String TAG = "[Barrage]view";
    private int mCount;
    private Runnable mDelayFireBarrage;
    private boolean mHasDelay;
    protected AtomicInteger mModel;
    protected IBarrageRender mRender;

    @Override // com.duowan.kiwi.barrage.view.IBarrageConfigView
    public int getQueueLine() {
        return 0;
    }

    @Override // com.duowan.kiwi.barrage.view.IBarrageView
    public boolean hasCustomTopMargin() {
        return false;
    }

    @Override // com.duowan.kiwi.barrage.view.IGLBarrageView
    public boolean isNeedClearEnable() {
        return true;
    }

    @Override // com.duowan.kiwi.barrage.view.IBarrageConfigView
    public boolean isQueueFixed() {
        return false;
    }

    @Override // com.duowan.kiwi.barrage.view.IGLBarrageView
    public boolean isStencilEnable() {
        return true;
    }

    public BarrageGLSurfaceView(Context context) {
        super(context);
        this.mHasDelay = true;
        this.mCount = 0;
    }

    public BarrageGLSurfaceView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mHasDelay = true;
        this.mCount = 0;
    }

    protected void initGLBarrageView(Context context) {
        initSurfaceConfig();
        initBarrageModel();
        GLBarrageRender gLBarrageRender = new GLBarrageRender(this, BarrageConfig.modelToType(getBarrageModel()), 1 == getBarrageModel(), getResources().getConfiguration().orientation, getInitAlpha());
        this.mRender = gLBarrageRender;
        setRenderer(gLBarrageRender);
        setRenderMode(0);
        BarrageCacheForReport.getInstance().switchReport(true);
    }

    protected void initSurfaceConfig() {
        setEGLContextClientVersion(2);
        setZOrderOnTop(false);
        setZOrderMediaOverlay(true);
        setEGLConfigChooser(8, 8, 8, 8, 16, 0);
        getHolder().setFormat(-3);
    }

    protected void initBarrageModel() {
        this.mModel = new AtomicInteger(BarrageConfig.getBarrageModel());
    }

    public void delayFireBarrage(boolean z, boolean z2) {
        if (!z && !z2 && !this.mHasDelay) {
            if (this.mDelayFireBarrage == null) {
                this.mDelayFireBarrage = new Runnable() { // from class: com.duowan.kiwi.barrage.BarrageGLSurfaceView.1
                    @Override // java.lang.Runnable
                    public void run() {
                        BarrageGLSurfaceView.this.cancelDelayFireBarrage();
                    }
                };
            }
            this.mHasDelay = false;
            postDelayed(this.mDelayFireBarrage, 2500L);
            BarrageLog.debug("[Barrage]view", "delayFireBarrage");
            return;
        }
        cancelDelayFireBarrage();
    }

    public void cancelDelayFireBarrage() {
        this.mHasDelay = true;
        Runnable runnable = this.mDelayFireBarrage;
        if (runnable != null) {
            removeCallbacks(runnable);
            this.mDelayFireBarrage = null;
        }
        fire();
        BarrageLog.debug("[Barrage]view", "cancelDelayFireBarrage");
    }

    @Override // com.duowan.kiwi.barrage.view.IBarrageView
    public synchronized void switchRender(boolean z) {
        BarrageLog.info("[Barrage]view", "switchRender %b", Boolean.valueOf(z));
        if (z) {
            this.mRender.resetSmooth();
            setRenderMode(1);
        } else {
            setRenderMode(0);
            requestRender();
        }
        this.mRender.setBarrageRenderOn(z);
    }

    public void ceaseFire(final boolean z) {
        BarrageLog.info("[Barrage]view", "enter ceaseFire: %b", Boolean.valueOf(z));
        queueEvent(new Runnable() { // from class: com.duowan.kiwi.barrage.BarrageGLSurfaceView.2
            @Override // java.lang.Runnable
            public void run() {
                if (BarrageGLSurfaceView.this.mRender != null) {
                    BarrageGLSurfaceView.this.mRender.ceaseFire(z);
                }
            }
        });
    }

    @Override // com.duowan.kiwi.barrage.view.IGLBarrageView
    public void showToast(final String str) {
        post(new Runnable() { // from class: com.duowan.kiwi.barrage.BarrageGLSurfaceView.3
            @Override // java.lang.Runnable
            public void run() {
                Toast.makeText(BarrageContext.gContext, str, 0).show();
            }
        });
    }

    @Override // com.duowan.kiwi.barrage.view.IBarrageView
    public void offerGunPowder(GunPowder gunPowder, int i) {
        this.mRender.offer(gunPowder, i);
    }

    @Override // com.duowan.kiwi.barrage.view.IBarrageView
    @Subscribe(threadMode = ThreadMode.PostThread)
    public void onBarrageAlphaChanged(BarrageEvent.BarrageAlphaChanged barrageAlphaChanged) {
        BarrageLog.info("[Barrage]view", "onBarrageAlphaChanged , alpha = " + barrageAlphaChanged.arg0);
        setBarrageAlpha(barrageAlphaChanged.arg0.floatValue());
    }

    protected void setBarrageAlpha(final float f) {
        queueEvent(new Runnable() { // from class: com.duowan.kiwi.barrage.BarrageGLSurfaceView.4
            @Override // java.lang.Runnable
            public void run() {
                BarrageGLSurfaceView.this.mRender.setBarrageAlpha(f);
            }
        });
    }

    @Override // com.duowan.kiwi.barrage.view.IBarrageView
    @Subscribe(threadMode = ThreadMode.PostThread)
    public void onBarrageSizeChanged(BarrageEvent.BarrageSizeChanged barrageSizeChanged) {
        final int iIntValue = barrageSizeChanged.arg0.intValue();
        queueEvent(new Runnable() { // from class: com.duowan.kiwi.barrage.BarrageGLSurfaceView.5
            @Override // java.lang.Runnable
            public void run() {
                BarrageGLSurfaceView.this.mRender.onBarrageSizeChanged(iIntValue);
            }
        });
    }

    @Override // com.duowan.kiwi.barrage.view.IBarrageView
    @Subscribe(threadMode = ThreadMode.PostThread)
    public void onBarrageModelChanged(BarrageEvent.BarrageModelChanged barrageModelChanged) {
        updateBarrageModel(barrageModelChanged.mode);
    }

    protected void updateBarrageModel(final int i) {
        BarrageLog.debug("[Barrage]view", "updateBarrageModel, model = %d", Integer.valueOf(i));
        if (i != getBarrageModel()) {
            final int barrageModel = getBarrageModel();
            this.mModel.set(i);
            queueEvent(new Runnable() { // from class: com.duowan.kiwi.barrage.BarrageGLSurfaceView.6
                @Override // java.lang.Runnable
                public void run() {
                    int iModelToType = BarrageConfig.modelToType(i);
                    BarrageGLSurfaceView.this.mRender.setAutoIncrease(iModelToType, 1 == i);
                    BarrageGLSurfaceView.this.mRender.setBarrageType(iModelToType);
                    if (i == 0) {
                        BarrageGLSurfaceView.this.ceaseFire(false);
                    } else if (barrageModel == 0) {
                        BarrageGLSurfaceView.this.mRender.resetSmooth();
                        BarrageGLSurfaceView.this.fireIfNeed();
                    }
                }
            });
        }
    }

    protected void fireIfNeed() {
        int i = this.mCount + 1;
        this.mCount = i;
        if (i > 100) {
            this.mCount = 0;
            BarrageLog.info("[Barrage]view", "barrage Model = %d , isBarrageOn = %b , mHasDelay = %b", Integer.valueOf(getBarrageModel()), Boolean.valueOf(this.mRender.isBarrageOn()), Boolean.valueOf(this.mHasDelay));
        }
        fire();
    }

    private void fire() {
        if (getBarrageModel() == 0 || !this.mRender.isBarrageOn() || !isCanDelay() || isRenderOpen()) {
            return;
        }
        switchRender(true);
    }

    protected boolean isCanDelay() {
        return this.mHasDelay;
    }

    protected boolean isRenderOpen() {
        return getRenderMode() == 1;
    }

    protected int getBarrageModel() {
        return this.mModel.get();
    }

    protected IBarrageRender getRender() {
        return this.mRender;
    }

    protected AtomicInteger getModel() {
        return this.mModel;
    }

    protected float getInitAlpha() {
        return BarrageConfig.getBarrageAlpha();
    }
}
