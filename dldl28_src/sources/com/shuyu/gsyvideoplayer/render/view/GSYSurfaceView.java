package com.shuyu.gsyvideoplayer.render.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import com.shuyu.gsyvideoplayer.listener.GSYVideoShotListener;
import com.shuyu.gsyvideoplayer.listener.GSYVideoShotSaveListener;
import com.shuyu.gsyvideoplayer.render.GSYRenderView;
import com.shuyu.gsyvideoplayer.render.glrender.GSYVideoGLViewBaseRender;
import com.shuyu.gsyvideoplayer.render.view.GSYVideoGLView;
import com.shuyu.gsyvideoplayer.render.view.listener.IGSYSurfaceListener;
import com.shuyu.gsyvideoplayer.utils.Debuger;
import com.shuyu.gsyvideoplayer.utils.MeasureHelper;
import java.io.File;

/* JADX INFO: loaded from: classes3.dex */
public class GSYSurfaceView extends SurfaceView implements SurfaceHolder.Callback2, IGSYRenderView, MeasureHelper.MeasureFormVideoParamsListener {
    private IGSYSurfaceListener mIGSYSurfaceListener;
    private MeasureHelper.MeasureFormVideoParamsListener mVideoParamsListener;
    private MeasureHelper measureHelper;

    @Override // com.shuyu.gsyvideoplayer.render.view.IGSYRenderView
    public View getRenderView() {
        return this;
    }

    @Override // android.view.SurfaceHolder.Callback2
    public void surfaceRedrawNeeded(SurfaceHolder surfaceHolder) {
    }

    public GSYSurfaceView(Context context) {
        super(context);
        init();
    }

    public GSYSurfaceView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    private void init() {
        this.measureHelper = new MeasureHelper(this, this);
    }

    @Override // android.view.SurfaceView, android.view.View
    protected void onMeasure(int i, int i2) {
        this.measureHelper.prepareMeasure(i, i2, (int) getRotation());
        setMeasuredDimension(this.measureHelper.getMeasuredWidth(), this.measureHelper.getMeasuredHeight());
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        IGSYSurfaceListener iGSYSurfaceListener = this.mIGSYSurfaceListener;
        if (iGSYSurfaceListener != null) {
            iGSYSurfaceListener.onSurfaceAvailable(surfaceHolder.getSurface());
        }
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        IGSYSurfaceListener iGSYSurfaceListener = this.mIGSYSurfaceListener;
        if (iGSYSurfaceListener != null) {
            iGSYSurfaceListener.onSurfaceSizeChanged(surfaceHolder.getSurface(), i2, i3);
        }
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        IGSYSurfaceListener iGSYSurfaceListener = this.mIGSYSurfaceListener;
        if (iGSYSurfaceListener != null) {
            iGSYSurfaceListener.onSurfaceDestroyed(surfaceHolder.getSurface());
        }
    }

    @Override // com.shuyu.gsyvideoplayer.render.view.IGSYRenderView
    public IGSYSurfaceListener getIGSYSurfaceListener() {
        return this.mIGSYSurfaceListener;
    }

    @Override // com.shuyu.gsyvideoplayer.render.view.IGSYRenderView
    public void setIGSYSurfaceListener(IGSYSurfaceListener iGSYSurfaceListener) {
        getHolder().addCallback(this);
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
        Debuger.printfLog(getClass().getSimpleName() + " not support initCover now");
        return null;
    }

    @Override // com.shuyu.gsyvideoplayer.render.view.IGSYRenderView
    public Bitmap initCoverHigh() {
        Debuger.printfLog(getClass().getSimpleName() + " not support initCoverHigh now");
        return null;
    }

    @Override // com.shuyu.gsyvideoplayer.render.view.IGSYRenderView
    public void taskShotPic(GSYVideoShotListener gSYVideoShotListener, boolean z) {
        Debuger.printfLog(getClass().getSimpleName() + " not support taskShotPic now");
    }

    @Override // com.shuyu.gsyvideoplayer.render.view.IGSYRenderView
    public void saveFrame(File file, boolean z, GSYVideoShotSaveListener gSYVideoShotSaveListener) {
        Debuger.printfLog(getClass().getSimpleName() + " not support saveFrame now");
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
        Debuger.printfLog(getClass().getSimpleName() + " not support setRenderTransform now");
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

    public static GSYSurfaceView addSurfaceView(Context context, ViewGroup viewGroup, int i, IGSYSurfaceListener iGSYSurfaceListener, MeasureHelper.MeasureFormVideoParamsListener measureFormVideoParamsListener) {
        if (viewGroup.getChildCount() > 0) {
            viewGroup.removeAllViews();
        }
        GSYSurfaceView gSYSurfaceView = new GSYSurfaceView(context);
        gSYSurfaceView.setIGSYSurfaceListener(iGSYSurfaceListener);
        gSYSurfaceView.setVideoParamsListener(measureFormVideoParamsListener);
        gSYSurfaceView.setRotation(i);
        GSYRenderView.addToParent(viewGroup, gSYSurfaceView);
        return gSYSurfaceView;
    }
}
