package com.shuyu.gsyvideoplayer.render;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import com.shuyu.gsyvideoplayer.listener.GSYVideoShotListener;
import com.shuyu.gsyvideoplayer.listener.GSYVideoShotSaveListener;
import com.shuyu.gsyvideoplayer.render.glrender.GSYVideoGLViewBaseRender;
import com.shuyu.gsyvideoplayer.render.view.GSYSurfaceView;
import com.shuyu.gsyvideoplayer.render.view.GSYTextureView;
import com.shuyu.gsyvideoplayer.render.view.GSYVideoGLView;
import com.shuyu.gsyvideoplayer.render.view.IGSYRenderView;
import com.shuyu.gsyvideoplayer.render.view.listener.IGSYSurfaceListener;
import com.shuyu.gsyvideoplayer.utils.GSYVideoType;
import com.shuyu.gsyvideoplayer.utils.MeasureHelper;
import java.io.File;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public class GSYRenderView {
    protected IGSYRenderView mShowView;

    public void requestLayout() {
        IGSYRenderView iGSYRenderView = this.mShowView;
        if (iGSYRenderView != null) {
            iGSYRenderView.getRenderView().requestLayout();
        }
    }

    public float getRotation() {
        return this.mShowView.getRenderView().getRotation();
    }

    public void setRotation(float f) {
        IGSYRenderView iGSYRenderView = this.mShowView;
        if (iGSYRenderView != null) {
            iGSYRenderView.getRenderView().setRotation(f);
        }
    }

    public void invalidate() {
        IGSYRenderView iGSYRenderView = this.mShowView;
        if (iGSYRenderView != null) {
            iGSYRenderView.getRenderView().invalidate();
        }
    }

    public int getWidth() {
        IGSYRenderView iGSYRenderView = this.mShowView;
        if (iGSYRenderView != null) {
            return iGSYRenderView.getRenderView().getWidth();
        }
        return 0;
    }

    public int getHeight() {
        IGSYRenderView iGSYRenderView = this.mShowView;
        if (iGSYRenderView != null) {
            return iGSYRenderView.getRenderView().getHeight();
        }
        return 0;
    }

    public View getShowView() {
        IGSYRenderView iGSYRenderView = this.mShowView;
        if (iGSYRenderView != null) {
            return iGSYRenderView.getRenderView();
        }
        return null;
    }

    public ViewGroup.LayoutParams getLayoutParams() {
        return this.mShowView.getRenderView().getLayoutParams();
    }

    public void setLayoutParams(ViewGroup.LayoutParams layoutParams) {
        IGSYRenderView iGSYRenderView = this.mShowView;
        if (iGSYRenderView != null) {
            iGSYRenderView.getRenderView().setLayoutParams(layoutParams);
        }
    }

    public void addView(Context context, ViewGroup viewGroup, int i, IGSYSurfaceListener iGSYSurfaceListener, MeasureHelper.MeasureFormVideoParamsListener measureFormVideoParamsListener, GSYVideoGLView.ShaderInterface shaderInterface, float[] fArr, GSYVideoGLViewBaseRender gSYVideoGLViewBaseRender, int i2) {
        if (GSYVideoType.getRenderType() == 1) {
            this.mShowView = GSYSurfaceView.addSurfaceView(context, viewGroup, i, iGSYSurfaceListener, measureFormVideoParamsListener);
        } else if (GSYVideoType.getRenderType() == 2) {
            this.mShowView = GSYVideoGLView.addGLView(context, viewGroup, i, iGSYSurfaceListener, measureFormVideoParamsListener, shaderInterface, fArr, gSYVideoGLViewBaseRender, i2);
        } else {
            this.mShowView = GSYTextureView.addTextureView(context, viewGroup, i, iGSYSurfaceListener, measureFormVideoParamsListener);
        }
    }

    public void setTransform(Matrix matrix) {
        IGSYRenderView iGSYRenderView = this.mShowView;
        if (iGSYRenderView != null) {
            iGSYRenderView.setRenderTransform(matrix);
        }
    }

    public Bitmap initCover() {
        IGSYRenderView iGSYRenderView = this.mShowView;
        if (iGSYRenderView != null) {
            return iGSYRenderView.initCover();
        }
        return null;
    }

    public Bitmap initCoverHigh() {
        IGSYRenderView iGSYRenderView = this.mShowView;
        if (iGSYRenderView != null) {
            return iGSYRenderView.initCoverHigh();
        }
        return null;
    }

    public void taskShotPic(GSYVideoShotListener gSYVideoShotListener) {
        taskShotPic(gSYVideoShotListener, false);
    }

    public void taskShotPic(GSYVideoShotListener gSYVideoShotListener, boolean z) {
        IGSYRenderView iGSYRenderView = this.mShowView;
        if (iGSYRenderView != null) {
            iGSYRenderView.taskShotPic(gSYVideoShotListener, z);
        }
    }

    public void saveFrame(File file, GSYVideoShotSaveListener gSYVideoShotSaveListener) {
        saveFrame(file, false, gSYVideoShotSaveListener);
    }

    public void saveFrame(File file, boolean z, GSYVideoShotSaveListener gSYVideoShotSaveListener) {
        IGSYRenderView iGSYRenderView = this.mShowView;
        if (iGSYRenderView != null) {
            iGSYRenderView.saveFrame(file, z, gSYVideoShotSaveListener);
        }
    }

    public void onResume() {
        IGSYRenderView iGSYRenderView = this.mShowView;
        if (iGSYRenderView != null) {
            iGSYRenderView.onRenderResume();
        }
    }

    public void onPause() {
        IGSYRenderView iGSYRenderView = this.mShowView;
        if (iGSYRenderView != null) {
            iGSYRenderView.onRenderPause();
        }
    }

    public void releaseAll() {
        IGSYRenderView iGSYRenderView = this.mShowView;
        if (iGSYRenderView != null) {
            iGSYRenderView.releaseRenderAll();
        }
    }

    public void setGLRenderMode(int i) {
        IGSYRenderView iGSYRenderView = this.mShowView;
        if (iGSYRenderView != null) {
            iGSYRenderView.setRenderMode(i);
        }
    }

    public void setGLRenderer(GSYVideoGLViewBaseRender gSYVideoGLViewBaseRender) {
        IGSYRenderView iGSYRenderView = this.mShowView;
        if (iGSYRenderView != null) {
            iGSYRenderView.setGLRenderer(gSYVideoGLViewBaseRender);
        }
    }

    public void setMatrixGL(float[] fArr) {
        IGSYRenderView iGSYRenderView = this.mShowView;
        if (iGSYRenderView != null) {
            iGSYRenderView.setGLMVPMatrix(fArr);
        }
    }

    public void setEffectFilter(GSYVideoGLView.ShaderInterface shaderInterface) {
        IGSYRenderView iGSYRenderView = this.mShowView;
        if (iGSYRenderView != null) {
            iGSYRenderView.setGLEffectFilter(shaderInterface);
        }
    }

    public static void addToParent(ViewGroup viewGroup, View view) {
        int textureParams = getTextureParams();
        if (viewGroup instanceof RelativeLayout) {
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(textureParams, textureParams);
            layoutParams.addRule(13);
            viewGroup.addView(view, layoutParams);
        } else if (viewGroup instanceof FrameLayout) {
            FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(textureParams, textureParams);
            layoutParams2.gravity = 17;
            viewGroup.addView(view, layoutParams2);
        }
    }

    public static int getTextureParams() {
        return GSYVideoType.getShowType() != 0 ? -2 : -1;
    }
}
