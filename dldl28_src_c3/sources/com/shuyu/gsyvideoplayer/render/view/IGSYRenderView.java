package com.shuyu.gsyvideoplayer.render.view;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.view.View;
import com.shuyu.gsyvideoplayer.listener.GSYVideoShotListener;
import com.shuyu.gsyvideoplayer.listener.GSYVideoShotSaveListener;
import com.shuyu.gsyvideoplayer.render.glrender.GSYVideoGLViewBaseRender;
import com.shuyu.gsyvideoplayer.render.view.GSYVideoGLView;
import com.shuyu.gsyvideoplayer.render.view.listener.IGSYSurfaceListener;
import com.shuyu.gsyvideoplayer.utils.MeasureHelper;
import java.io.File;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public interface IGSYRenderView {
    IGSYSurfaceListener getIGSYSurfaceListener();

    View getRenderView();

    int getSizeH();

    int getSizeW();

    Bitmap initCover();

    Bitmap initCoverHigh();

    void onRenderPause();

    void onRenderResume();

    void releaseRenderAll();

    void saveFrame(File file, boolean z, GSYVideoShotSaveListener gSYVideoShotSaveListener);

    void setGLEffectFilter(GSYVideoGLView.ShaderInterface shaderInterface);

    void setGLMVPMatrix(float[] fArr);

    void setGLRenderer(GSYVideoGLViewBaseRender gSYVideoGLViewBaseRender);

    void setIGSYSurfaceListener(IGSYSurfaceListener iGSYSurfaceListener);

    void setRenderMode(int i);

    void setRenderTransform(Matrix matrix);

    void setVideoParamsListener(MeasureHelper.MeasureFormVideoParamsListener measureFormVideoParamsListener);

    void taskShotPic(GSYVideoShotListener gSYVideoShotListener, boolean z);
}
