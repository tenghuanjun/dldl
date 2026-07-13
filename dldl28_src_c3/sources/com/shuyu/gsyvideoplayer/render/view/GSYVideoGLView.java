package com.shuyu.gsyvideoplayer.render.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;
import android.view.Surface;
import android.view.View;
import android.view.ViewGroup;
import com.shuyu.gsyvideoplayer.listener.GSYVideoShotListener;
import com.shuyu.gsyvideoplayer.listener.GSYVideoShotSaveListener;
import com.shuyu.gsyvideoplayer.render.GSYRenderView;
import com.shuyu.gsyvideoplayer.render.effect.NoEffect;
import com.shuyu.gsyvideoplayer.render.glrender.GSYVideoGLViewBaseRender;
import com.shuyu.gsyvideoplayer.render.glrender.GSYVideoGLViewSimpleRender;
import com.shuyu.gsyvideoplayer.render.view.listener.GLSurfaceListener;
import com.shuyu.gsyvideoplayer.render.view.listener.GSYVideoGLRenderErrorListener;
import com.shuyu.gsyvideoplayer.render.view.listener.IGSYSurfaceListener;
import com.shuyu.gsyvideoplayer.utils.Debuger;
import com.shuyu.gsyvideoplayer.utils.FileUtils;
import com.shuyu.gsyvideoplayer.utils.MeasureHelper;
import java.io.File;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public class GSYVideoGLView extends GLSurfaceView implements GLSurfaceListener, IGSYRenderView, MeasureHelper.MeasureFormVideoParamsListener {
    public static final int MODE_LAYOUT_SIZE = 0;
    public static final int MODE_RENDER_SIZE = 1;
    private static final String TAG = "com.shuyu.gsyvideoplayer.render.view.GSYVideoGLView";
    private Context mContext;
    private ShaderInterface mEffect;
    private IGSYSurfaceListener mIGSYSurfaceListener;
    private float[] mMVPMatrix;
    private int mMode;
    private GLSurfaceListener mOnGSYSurfaceListener;
    private GSYVideoGLViewBaseRender mRenderer;
    private MeasureHelper.MeasureFormVideoParamsListener mVideoParamsListener;
    private MeasureHelper measureHelper;

    public interface ShaderInterface {
        String getShader(GLSurfaceView gLSurfaceView);
    }

    @Override // com.shuyu.gsyvideoplayer.render.view.IGSYRenderView
    public View getRenderView() {
        return this;
    }

    public GSYVideoGLView(Context context) {
        super(context);
        this.mEffect = new NoEffect();
        this.mMode = 0;
        init(context);
    }

    public GSYVideoGLView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mEffect = new NoEffect();
        this.mMode = 0;
        init(context);
    }

    private void init(Context context) {
        this.mContext = context;
        setEGLContextClientVersion(2);
        this.mRenderer = new GSYVideoGLViewSimpleRender();
        this.measureHelper = new MeasureHelper(this, this);
        this.mRenderer.setSurfaceView(this);
    }

    @Override // android.opengl.GLSurfaceView
    public void onResume() {
        super.onResume();
        GSYVideoGLViewBaseRender gSYVideoGLViewBaseRender = this.mRenderer;
        if (gSYVideoGLViewBaseRender != null) {
            gSYVideoGLViewBaseRender.initRenderSize();
        }
    }

    @Override // android.view.SurfaceView, android.view.View
    protected void onMeasure(int i, int i2) {
        if (this.mMode == 1) {
            super.onMeasure(i, i2);
            this.measureHelper.prepareMeasure(i, i2, (int) getRotation());
            initRenderMeasure();
        } else {
            this.measureHelper.prepareMeasure(i, i2, (int) getRotation());
            setMeasuredDimension(this.measureHelper.getMeasuredWidth(), this.measureHelper.getMeasuredHeight());
        }
    }

    @Override // com.shuyu.gsyvideoplayer.render.view.IGSYRenderView
    public IGSYSurfaceListener getIGSYSurfaceListener() {
        return this.mIGSYSurfaceListener;
    }

    @Override // com.shuyu.gsyvideoplayer.render.view.IGSYRenderView
    public void setIGSYSurfaceListener(IGSYSurfaceListener iGSYSurfaceListener) {
        setOnGSYSurfaceListener(this);
        this.mIGSYSurfaceListener = iGSYSurfaceListener;
    }

    @Override // com.shuyu.gsyvideoplayer.render.view.listener.GLSurfaceListener
    public void onSurfaceAvailable(Surface surface) {
        IGSYSurfaceListener iGSYSurfaceListener = this.mIGSYSurfaceListener;
        if (iGSYSurfaceListener != null) {
            iGSYSurfaceListener.onSurfaceAvailable(surface);
        }
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
        if (gSYVideoShotListener != null) {
            setGSYVideoShotListener(gSYVideoShotListener, z);
            takeShotPic();
        }
    }

    @Override // com.shuyu.gsyvideoplayer.render.view.IGSYRenderView
    public void saveFrame(final File file, boolean z, final GSYVideoShotSaveListener gSYVideoShotSaveListener) {
        setGSYVideoShotListener(new GSYVideoShotListener() { // from class: com.shuyu.gsyvideoplayer.render.view.GSYVideoGLView.1
            @Override // com.shuyu.gsyvideoplayer.listener.GSYVideoShotListener
            public void getBitmap(Bitmap bitmap) {
                if (bitmap == null) {
                    gSYVideoShotSaveListener.result(false, file);
                } else {
                    FileUtils.saveBitmap(bitmap, file);
                    gSYVideoShotSaveListener.result(true, file);
                }
            }
        }, z);
        takeShotPic();
    }

    @Override // com.shuyu.gsyvideoplayer.render.view.IGSYRenderView
    public void onRenderResume() {
        requestLayout();
        onResume();
    }

    @Override // com.shuyu.gsyvideoplayer.render.view.IGSYRenderView
    public void onRenderPause() {
        requestLayout();
        onPause();
    }

    @Override // com.shuyu.gsyvideoplayer.render.view.IGSYRenderView
    public void releaseRenderAll() {
        requestLayout();
        releaseAll();
    }

    @Override // android.opengl.GLSurfaceView, com.shuyu.gsyvideoplayer.render.view.IGSYRenderView
    public void setRenderMode(int i) {
        setMode(i);
    }

    @Override // com.shuyu.gsyvideoplayer.render.view.IGSYRenderView
    public void setRenderTransform(Matrix matrix) {
        Debuger.printfLog(getClass().getSimpleName() + " not support setRenderTransform now");
    }

    @Override // com.shuyu.gsyvideoplayer.render.view.IGSYRenderView
    public void setGLRenderer(GSYVideoGLViewBaseRender gSYVideoGLViewBaseRender) {
        setCustomRenderer(gSYVideoGLViewBaseRender);
    }

    @Override // com.shuyu.gsyvideoplayer.render.view.IGSYRenderView
    public void setGLMVPMatrix(float[] fArr) {
        setMVPMatrix(fArr);
    }

    @Override // com.shuyu.gsyvideoplayer.render.view.IGSYRenderView
    public void setGLEffectFilter(ShaderInterface shaderInterface) {
        setEffect(shaderInterface);
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

    protected void initRenderMeasure() {
        MeasureHelper.MeasureFormVideoParamsListener measureFormVideoParamsListener = this.mVideoParamsListener;
        if (measureFormVideoParamsListener == null || this.mMode != 1) {
            return;
        }
        try {
            int currentVideoWidth = measureFormVideoParamsListener.getCurrentVideoWidth();
            int currentVideoHeight = this.mVideoParamsListener.getCurrentVideoHeight();
            GSYVideoGLViewBaseRender gSYVideoGLViewBaseRender = this.mRenderer;
            if (gSYVideoGLViewBaseRender != null) {
                gSYVideoGLViewBaseRender.setCurrentViewWidth(this.measureHelper.getMeasuredWidth());
                this.mRenderer.setCurrentViewHeight(this.measureHelper.getMeasuredHeight());
                this.mRenderer.setCurrentVideoWidth(currentVideoWidth);
                this.mRenderer.setCurrentVideoHeight(currentVideoHeight);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initRender() {
        setRenderer(this.mRenderer);
    }

    public void setGSYVideoGLRenderErrorListener(GSYVideoGLRenderErrorListener gSYVideoGLRenderErrorListener) {
        this.mRenderer.setGSYVideoGLRenderErrorListener(gSYVideoGLRenderErrorListener);
    }

    public void setCustomRenderer(GSYVideoGLViewBaseRender gSYVideoGLViewBaseRender) {
        this.mRenderer = gSYVideoGLViewBaseRender;
        gSYVideoGLViewBaseRender.setSurfaceView(this);
        initRenderMeasure();
    }

    public void setOnGSYSurfaceListener(GLSurfaceListener gLSurfaceListener) {
        this.mOnGSYSurfaceListener = gLSurfaceListener;
        this.mRenderer.setGSYSurfaceListener(gLSurfaceListener);
    }

    public void setEffect(ShaderInterface shaderInterface) {
        if (shaderInterface != null) {
            this.mEffect = shaderInterface;
            this.mRenderer.setEffect(shaderInterface);
        }
    }

    public void setMVPMatrix(float[] fArr) {
        if (fArr != null) {
            this.mMVPMatrix = fArr;
            this.mRenderer.setMVPMatrix(fArr);
        }
    }

    public void takeShotPic() {
        this.mRenderer.takeShotPic();
    }

    public void setGSYVideoShotListener(GSYVideoShotListener gSYVideoShotListener, boolean z) {
        this.mRenderer.setGSYVideoShotListener(gSYVideoShotListener, z);
    }

    public int getMode() {
        return this.mMode;
    }

    public void setMode(int i) {
        this.mMode = i;
    }

    public void releaseAll() {
        GSYVideoGLViewBaseRender gSYVideoGLViewBaseRender = this.mRenderer;
        if (gSYVideoGLViewBaseRender != null) {
            gSYVideoGLViewBaseRender.releaseAll();
        }
    }

    public GSYVideoGLViewBaseRender getRenderer() {
        return this.mRenderer;
    }

    public ShaderInterface getEffect() {
        return this.mEffect;
    }

    public float[] getMVPMatrix() {
        return this.mMVPMatrix;
    }

    public static GSYVideoGLView addGLView(final Context context, final ViewGroup viewGroup, final int i, final IGSYSurfaceListener iGSYSurfaceListener, final MeasureHelper.MeasureFormVideoParamsListener measureFormVideoParamsListener, ShaderInterface shaderInterface, float[] fArr, GSYVideoGLViewBaseRender gSYVideoGLViewBaseRender, final int i2) {
        if (viewGroup.getChildCount() > 0) {
            viewGroup.removeAllViews();
        }
        GSYVideoGLView gSYVideoGLView = new GSYVideoGLView(context);
        if (gSYVideoGLViewBaseRender != null) {
            gSYVideoGLView.setCustomRenderer(gSYVideoGLViewBaseRender);
        }
        gSYVideoGLView.setEffect(shaderInterface);
        gSYVideoGLView.setVideoParamsListener(measureFormVideoParamsListener);
        gSYVideoGLView.setRenderMode(i2);
        gSYVideoGLView.setIGSYSurfaceListener(iGSYSurfaceListener);
        gSYVideoGLView.setRotation(i);
        gSYVideoGLView.initRender();
        gSYVideoGLView.setGSYVideoGLRenderErrorListener(new GSYVideoGLRenderErrorListener() { // from class: com.shuyu.gsyvideoplayer.render.view.GSYVideoGLView.2
            @Override // com.shuyu.gsyvideoplayer.render.view.listener.GSYVideoGLRenderErrorListener
            public void onError(GSYVideoGLViewBaseRender gSYVideoGLViewBaseRender2, String str, int i3, boolean z) {
                if (z) {
                    GSYVideoGLView.addGLView(context, viewGroup, i, iGSYSurfaceListener, measureFormVideoParamsListener, gSYVideoGLViewBaseRender2.getEffect(), gSYVideoGLViewBaseRender2.getMVPMatrix(), gSYVideoGLViewBaseRender2, i2);
                }
            }
        });
        if (fArr != null && fArr.length == 16) {
            gSYVideoGLView.setMVPMatrix(fArr);
        }
        GSYRenderView.addToParent(viewGroup, gSYVideoGLView);
        return gSYVideoGLView;
    }
}
