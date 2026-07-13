package com.shuyu.gsyvideoplayer.render.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.SurfaceTexture;
import android.util.AttributeSet;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import com.shuyu.gsyvideoplayer.listener.GSYVideoShotListener;
import com.shuyu.gsyvideoplayer.listener.GSYVideoShotSaveListener;
import com.shuyu.gsyvideoplayer.render.GSYRenderView;
import com.shuyu.gsyvideoplayer.render.glrender.GSYVideoGLViewBaseRender;
import com.shuyu.gsyvideoplayer.render.view.GSYVideoGLView;
import com.shuyu.gsyvideoplayer.render.view.listener.IGSYSurfaceListener;
import com.shuyu.gsyvideoplayer.utils.Debuger;
import com.shuyu.gsyvideoplayer.utils.FileUtils;
import com.shuyu.gsyvideoplayer.utils.GSYVideoType;
import com.shuyu.gsyvideoplayer.utils.MeasureHelper;
import java.io.File;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public class GSYTextureView extends TextureView implements TextureView.SurfaceTextureListener, IGSYRenderView, MeasureHelper.MeasureFormVideoParamsListener {
    private IGSYSurfaceListener mIGSYSurfaceListener;
    private SurfaceTexture mSaveTexture;
    private Surface mSurface;
    private MeasureHelper.MeasureFormVideoParamsListener mVideoParamsListener;
    private MeasureHelper measureHelper;

    @Override // com.shuyu.gsyvideoplayer.render.view.IGSYRenderView
    public View getRenderView() {
        return this;
    }

    public GSYTextureView(Context context) {
        super(context);
        init();
    }

    public GSYTextureView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    private void init() {
        this.measureHelper = new MeasureHelper(this, this);
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        this.measureHelper.prepareMeasure(i, i2, (int) getRotation());
        setMeasuredDimension(this.measureHelper.getMeasuredWidth(), this.measureHelper.getMeasuredHeight());
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
        if (GSYVideoType.isMediaCodecTexture()) {
            SurfaceTexture surfaceTexture2 = this.mSaveTexture;
            if (surfaceTexture2 == null) {
                this.mSaveTexture = surfaceTexture;
                this.mSurface = new Surface(surfaceTexture);
            } else {
                setSurfaceTexture(surfaceTexture2);
            }
            IGSYSurfaceListener iGSYSurfaceListener = this.mIGSYSurfaceListener;
            if (iGSYSurfaceListener != null) {
                iGSYSurfaceListener.onSurfaceAvailable(this.mSurface);
                return;
            }
            return;
        }
        Surface surface = new Surface(surfaceTexture);
        this.mSurface = surface;
        IGSYSurfaceListener iGSYSurfaceListener2 = this.mIGSYSurfaceListener;
        if (iGSYSurfaceListener2 != null) {
            iGSYSurfaceListener2.onSurfaceAvailable(surface);
        }
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
        IGSYSurfaceListener iGSYSurfaceListener = this.mIGSYSurfaceListener;
        if (iGSYSurfaceListener != null) {
            iGSYSurfaceListener.onSurfaceSizeChanged(this.mSurface, i, i2);
        }
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        IGSYSurfaceListener iGSYSurfaceListener = this.mIGSYSurfaceListener;
        if (iGSYSurfaceListener != null) {
            iGSYSurfaceListener.onSurfaceDestroyed(this.mSurface);
        }
        return !GSYVideoType.isMediaCodecTexture() || this.mSaveTexture == null;
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
        IGSYSurfaceListener iGSYSurfaceListener = this.mIGSYSurfaceListener;
        if (iGSYSurfaceListener != null) {
            iGSYSurfaceListener.onSurfaceUpdated(this.mSurface);
        }
    }

    @Override // com.shuyu.gsyvideoplayer.render.view.IGSYRenderView
    public IGSYSurfaceListener getIGSYSurfaceListener() {
        return this.mIGSYSurfaceListener;
    }

    @Override // com.shuyu.gsyvideoplayer.render.view.IGSYRenderView
    public void setIGSYSurfaceListener(IGSYSurfaceListener iGSYSurfaceListener) {
        setSurfaceTextureListener(this);
        this.mIGSYSurfaceListener = iGSYSurfaceListener;
    }

    @Override // com.shuyu.gsyvideoplayer.render.view.IGSYRenderView
    public int getSizeH() {
        return getHeight();
    }

    @Override // com.shuyu.gsyvideoplayer.render.view.IGSYRenderView
    public int getSizeW() {
        return getWidth();
    }

    @Override // com.shuyu.gsyvideoplayer.render.view.IGSYRenderView
    public Bitmap initCover() {
        return getBitmap(Bitmap.createBitmap(getSizeW(), getSizeH(), Bitmap.Config.RGB_565));
    }

    @Override // com.shuyu.gsyvideoplayer.render.view.IGSYRenderView
    public Bitmap initCoverHigh() {
        return getBitmap(Bitmap.createBitmap(getSizeW(), getSizeH(), Bitmap.Config.ARGB_8888));
    }

    @Override // com.shuyu.gsyvideoplayer.render.view.IGSYRenderView
    public void taskShotPic(GSYVideoShotListener gSYVideoShotListener, boolean z) {
        if (z) {
            gSYVideoShotListener.getBitmap(initCoverHigh());
        } else {
            gSYVideoShotListener.getBitmap(initCover());
        }
    }

    @Override // com.shuyu.gsyvideoplayer.render.view.IGSYRenderView
    public void saveFrame(final File file, boolean z, final GSYVideoShotSaveListener gSYVideoShotSaveListener) {
        GSYVideoShotListener gSYVideoShotListener = new GSYVideoShotListener() { // from class: com.shuyu.gsyvideoplayer.render.view.GSYTextureView.1
            @Override // com.shuyu.gsyvideoplayer.listener.GSYVideoShotListener
            public void getBitmap(Bitmap bitmap) {
                if (bitmap == null) {
                    gSYVideoShotSaveListener.result(false, file);
                } else {
                    FileUtils.saveBitmap(bitmap, file);
                    gSYVideoShotSaveListener.result(true, file);
                }
            }
        };
        if (z) {
            gSYVideoShotListener.getBitmap(initCoverHigh());
        } else {
            gSYVideoShotListener.getBitmap(initCover());
        }
    }

    @Override // com.shuyu.gsyvideoplayer.render.view.IGSYRenderView
    public void onRenderResume() {
        Debuger.printfLog(getClass().getSimpleName() + " not support onRenderResume now");
    }

    @Override // com.shuyu.gsyvideoplayer.render.view.IGSYRenderView
    public void onRenderPause() {
        Debuger.printfLog(getClass().getSimpleName() + " not support onRenderPause now");
    }

    @Override // com.shuyu.gsyvideoplayer.render.view.IGSYRenderView
    public void releaseRenderAll() {
        Debuger.printfLog(getClass().getSimpleName() + " not support releaseRenderAll now");
    }

    @Override // com.shuyu.gsyvideoplayer.render.view.IGSYRenderView
    public void setRenderMode(int i) {
        Debuger.printfLog(getClass().getSimpleName() + " not support setRenderMode now");
    }

    @Override // com.shuyu.gsyvideoplayer.render.view.IGSYRenderView
    public void setRenderTransform(Matrix matrix) {
        setTransform(matrix);
    }

    @Override // com.shuyu.gsyvideoplayer.render.view.IGSYRenderView
    public void setGLRenderer(GSYVideoGLViewBaseRender gSYVideoGLViewBaseRender) {
        Debuger.printfLog(getClass().getSimpleName() + " not support setGLRenderer now");
    }

    @Override // com.shuyu.gsyvideoplayer.render.view.IGSYRenderView
    public void setGLMVPMatrix(float[] fArr) {
        Debuger.printfLog(getClass().getSimpleName() + " not support setGLMVPMatrix now");
    }

    @Override // com.shuyu.gsyvideoplayer.render.view.IGSYRenderView
    public void setGLEffectFilter(GSYVideoGLView.ShaderInterface shaderInterface) {
        Debuger.printfLog(getClass().getSimpleName() + " not support setGLEffectFilter now");
    }

    @Override // com.shuyu.gsyvideoplayer.render.view.IGSYRenderView
    public void setVideoParamsListener(MeasureHelper.MeasureFormVideoParamsListener measureFormVideoParamsListener) {
        this.mVideoParamsListener = measureFormVideoParamsListener;
    }

    @Override // com.shuyu.gsyvideoplayer.utils.MeasureHelper.MeasureFormVideoParamsListener
    public int getCurrentVideoWidth() {
        MeasureHelper.MeasureFormVideoParamsListener measureFormVideoParamsListener = this.mVideoParamsListener;
        if (measureFormVideoParamsListener != null) {
            return measureFormVideoParamsListener.getCurrentVideoWidth();
        }
        return 0;
    }

    @Override // com.shuyu.gsyvideoplayer.utils.MeasureHelper.MeasureFormVideoParamsListener
    public int getCurrentVideoHeight() {
        MeasureHelper.MeasureFormVideoParamsListener measureFormVideoParamsListener = this.mVideoParamsListener;
        if (measureFormVideoParamsListener != null) {
            return measureFormVideoParamsListener.getCurrentVideoHeight();
        }
        return 0;
    }

    @Override // com.shuyu.gsyvideoplayer.utils.MeasureHelper.MeasureFormVideoParamsListener
    public int getVideoSarNum() {
        MeasureHelper.MeasureFormVideoParamsListener measureFormVideoParamsListener = this.mVideoParamsListener;
        if (measureFormVideoParamsListener != null) {
            return measureFormVideoParamsListener.getVideoSarNum();
        }
        return 0;
    }

    @Override // com.shuyu.gsyvideoplayer.utils.MeasureHelper.MeasureFormVideoParamsListener
    public int getVideoSarDen() {
        MeasureHelper.MeasureFormVideoParamsListener measureFormVideoParamsListener = this.mVideoParamsListener;
        if (measureFormVideoParamsListener != null) {
            return measureFormVideoParamsListener.getVideoSarDen();
        }
        return 0;
    }

    public static GSYTextureView addTextureView(Context context, ViewGroup viewGroup, int i, IGSYSurfaceListener iGSYSurfaceListener, MeasureHelper.MeasureFormVideoParamsListener measureFormVideoParamsListener) {
        if (viewGroup.getChildCount() > 0) {
            viewGroup.removeAllViews();
        }
        GSYTextureView gSYTextureView = new GSYTextureView(context);
        gSYTextureView.setIGSYSurfaceListener(iGSYSurfaceListener);
        gSYTextureView.setVideoParamsListener(measureFormVideoParamsListener);
        gSYTextureView.setRotation(i);
        GSYRenderView.addToParent(viewGroup, gSYTextureView);
        return gSYTextureView;
    }
}
