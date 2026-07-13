package com.shuyu.gsyvideoplayer.video.base;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.shuyu.gsyvideoplayer.render.GSYRenderView;
import com.shuyu.gsyvideoplayer.render.effect.NoEffect;
import com.shuyu.gsyvideoplayer.render.glrender.GSYVideoGLViewBaseRender;
import com.shuyu.gsyvideoplayer.render.view.GSYVideoGLView;
import com.shuyu.gsyvideoplayer.render.view.listener.IGSYSurfaceListener;
import com.shuyu.gsyvideoplayer.utils.GSYVideoType;
import com.shuyu.gsyvideoplayer.utils.MeasureHelper;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public abstract class GSYTextureRenderView extends FrameLayout implements IGSYSurfaceListener, MeasureHelper.MeasureFormVideoParamsListener {
    protected GSYVideoGLView.ShaderInterface mEffectFilter;
    protected Bitmap mFullPauseBitmap;
    protected float[] mMatrixGL;
    protected int mMode;
    protected GSYVideoGLViewBaseRender mRenderer;
    protected int mRotate;
    protected Surface mSurface;
    protected GSYRenderView mTextureView;
    protected ViewGroup mTextureViewContainer;

    @Override // com.shuyu.gsyvideoplayer.render.view.listener.IGSYSurfaceListener
    public void onSurfaceSizeChanged(Surface surface, int i, int i2) {
    }

    protected abstract void releasePauseCover();

    protected abstract void releaseSurface(Surface surface);

    protected abstract void setDisplay(Surface surface);

    protected abstract void setSmallVideoTextureView();

    protected abstract void showPauseCover();

    public GSYTextureRenderView(Context context) {
        super(context);
        this.mEffectFilter = new NoEffect();
        this.mMatrixGL = null;
        this.mMode = 0;
    }

    public GSYTextureRenderView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mEffectFilter = new NoEffect();
        this.mMatrixGL = null;
        this.mMode = 0;
    }

    public GSYTextureRenderView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mEffectFilter = new NoEffect();
        this.mMatrixGL = null;
        this.mMode = 0;
    }

    @Override // com.shuyu.gsyvideoplayer.render.view.listener.IGSYSurfaceListener
    public void onSurfaceAvailable(Surface surface) {
        GSYRenderView gSYRenderView = this.mTextureView;
        pauseLogic(surface, gSYRenderView != null && (gSYRenderView.getShowView() instanceof TextureView));
    }

    @Override // com.shuyu.gsyvideoplayer.render.view.listener.IGSYSurfaceListener
    public boolean onSurfaceDestroyed(Surface surface) {
        setDisplay(null);
        releaseSurface(surface);
        return true;
    }

    @Override // com.shuyu.gsyvideoplayer.render.view.listener.IGSYSurfaceListener
    public void onSurfaceUpdated(Surface surface) {
        releasePauseCover();
    }

    protected void pauseLogic(Surface surface, boolean z) {
        this.mSurface = surface;
        if (z) {
            showPauseCover();
        }
        setDisplay(this.mSurface);
    }

    protected void addTextureView() {
        GSYRenderView gSYRenderView = new GSYRenderView();
        this.mTextureView = gSYRenderView;
        gSYRenderView.addView(getContext(), this.mTextureViewContainer, this.mRotate, this, this, this.mEffectFilter, this.mMatrixGL, this.mRenderer, this.mMode);
    }

    protected int getTextureParams() {
        return GSYVideoType.getShowType() != 0 ? -2 : -1;
    }

    protected void changeTextureViewShowType() {
        if (this.mTextureView != null) {
            int textureParams = getTextureParams();
            ViewGroup.LayoutParams layoutParams = this.mTextureView.getLayoutParams();
            layoutParams.width = textureParams;
            layoutParams.height = textureParams;
            this.mTextureView.setLayoutParams(layoutParams);
        }
    }

    protected void initCover() {
        GSYRenderView gSYRenderView = this.mTextureView;
        if (gSYRenderView != null) {
            this.mFullPauseBitmap = gSYRenderView.initCover();
        }
    }

    protected void setSmallVideoTextureView(View.OnTouchListener onTouchListener) {
        this.mTextureViewContainer.setOnTouchListener(onTouchListener);
        this.mTextureViewContainer.setOnClickListener(null);
        setSmallVideoTextureView();
    }

    public GSYVideoGLView.ShaderInterface getEffectFilter() {
        return this.mEffectFilter;
    }

    public GSYRenderView getRenderProxy() {
        return this.mTextureView;
    }

    public void setEffectFilter(GSYVideoGLView.ShaderInterface shaderInterface) {
        this.mEffectFilter = shaderInterface;
        GSYRenderView gSYRenderView = this.mTextureView;
        if (gSYRenderView != null) {
            gSYRenderView.setEffectFilter(shaderInterface);
        }
    }

    public void setMatrixGL(float[] fArr) {
        this.mMatrixGL = fArr;
        GSYRenderView gSYRenderView = this.mTextureView;
        if (gSYRenderView != null) {
            gSYRenderView.setMatrixGL(fArr);
        }
    }

    public void setCustomGLRenderer(GSYVideoGLViewBaseRender gSYVideoGLViewBaseRender) {
        this.mRenderer = gSYVideoGLViewBaseRender;
        GSYRenderView gSYRenderView = this.mTextureView;
        if (gSYRenderView != null) {
            gSYRenderView.setGLRenderer(gSYVideoGLViewBaseRender);
        }
    }

    public void setGLRenderMode(int i) {
        this.mMode = i;
        GSYRenderView gSYRenderView = this.mTextureView;
        if (gSYRenderView != null) {
            gSYRenderView.setGLRenderMode(i);
        }
    }
}
