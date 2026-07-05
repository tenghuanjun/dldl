package com.duowan.kiwi.barrage;

import android.content.Context;
import android.util.AttributeSet;
import com.duowan.auk.ArkUtils;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class BarrageGLSurfaceViewForFloating extends BarrageGLSurfaceViewForLiveRoom {
    private static final String TAG = "GLBarrageViewForFloating";
    private boolean mIsOpend;

    @Override // com.duowan.kiwi.barrage.BarrageGLSurfaceView
    protected int getBarrageModel() {
        return 2;
    }

    @Override // com.duowan.kiwi.barrage.BarrageGLSurfaceView, com.duowan.kiwi.barrage.view.IBarrageView
    public boolean hasCustomTopMargin() {
        return true;
    }

    @Override // com.duowan.kiwi.barrage.BarrageGLSurfaceView
    protected boolean isCanDelay() {
        return true;
    }

    @Override // com.duowan.kiwi.barrage.BarrageGLSurfaceViewWithLifeCycle
    protected boolean isFromFloating() {
        return true;
    }

    @Override // com.duowan.kiwi.barrage.BarrageGLSurfaceView, com.duowan.kiwi.barrage.view.IGLBarrageView
    public boolean isNeedClearEnable() {
        return false;
    }

    @Override // com.duowan.kiwi.barrage.BarrageGLSurfaceView, com.duowan.kiwi.barrage.view.IGLBarrageView
    public boolean isStencilEnable() {
        return false;
    }

    public BarrageGLSurfaceViewForFloating(Context context) {
        super(context.getApplicationContext());
        this.mIsOpend = false;
    }

    public BarrageGLSurfaceViewForFloating(Context context, AttributeSet attributeSet) {
        super(context.getApplicationContext(), attributeSet);
        this.mIsOpend = false;
    }

    public void register() {
        ArkUtils.register(this);
    }

    public void unregister() {
        ArkUtils.unregister(this);
    }

    @Override // com.duowan.kiwi.barrage.BarrageGLSurfaceView
    protected void initBarrageModel() {
        this.mModel = new AtomicInteger(2);
    }

    @Override // com.duowan.kiwi.barrage.BarrageGLSurfaceView
    protected AtomicInteger getModel() {
        if (this.mModel == null) {
            this.mModel = new AtomicInteger(2);
        }
        this.mModel.set(2);
        return this.mModel;
    }

    @Override // com.duowan.kiwi.barrage.BarrageGLSurfaceViewWithLifeCycle, com.duowan.kiwi.barrage.BarrageGLSurfaceView, com.duowan.kiwi.barrage.view.IBarrageView
    public void switchRender(boolean z) {
        if (!z) {
            ceaseFire(true);
            super.switchRender(false);
        } else if (this.mIsOpend) {
            super.switchRender(true);
        }
    }

    public void openOrNot(boolean z) {
        this.mIsOpend = z;
        switchRender(z);
        if (z) {
            register();
            setVisibility(0);
        } else {
            unregister();
            setVisibility(8);
        }
    }
}
