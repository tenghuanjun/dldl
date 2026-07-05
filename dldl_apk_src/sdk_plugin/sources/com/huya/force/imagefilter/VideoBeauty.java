package com.huya.force.imagefilter;

import android.opengl.GLES20;
import android.os.SystemClock;
import android.util.Log;
import com.huya.force.common.VideoFrameData;
import com.huya.force.export.imagefilter.BaseImageFilter;
import com.huya.force.export.imagefilter.ImageFilterInput;
import com.huya.force.export.imagefilter.Watermark;
import com.huya.force.gles.FullFrameRect;
import com.huya.force.gles.GlUtil;
import com.huya.force.gles.Texture2dProgram;
import com.huya.force.gpuimage.GPUImageFilter;
import com.huya.force.gpuimage.GlHelper;
import com.huya.force.gpuimage.OpenGlUtils;
import com.huya.force.imagefilter.beauty.BeautyFilterFactory;
import com.huya.force.imagefilter.beauty.GPUImageStTrackFilter;
import com.huya.force.log.ForceLog;
import java.nio.FloatBuffer;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class VideoBeauty extends BaseImageFilter {
    private static final int MESSAGE_PUT = 1;
    private static final int MESSAGE_SET_WATERMARK = 3;
    private static final int MESSAGE_START = 0;
    private static final int MESSAGE_STOP = 2;
    private static final int MESSAGE_SWITCH_BEAUTY = 4;
    private static final String TAG = "VideoBeauty";
    private int m2DFrameBufferId;
    private int m2DTextureId;
    private GPUImageFilter mBeautyFilter;
    private GPUImageFilter mBlendFilter;
    private FullFrameRect mDraw2d;
    private FullFrameRect mDrawExt;
    private ImageFilterInput mImageFilterInput;
    private int mPrefixFrameBufferId;
    private int mPrefixTextureId;
    private GPUImageStTrackFilter mThinFaceFilter;
    private float[] mTransform;
    private FloatBuffer mWatermarkTextureCoords;
    private List<Integer> mWatermarkTextureId;

    private static float flip(float f) {
        return f == 0.0f ? 1.0f : 0.0f;
    }

    public VideoBeauty(ImageFilterInput imageFilterInput) {
        super(imageFilterInput);
        this.mPrefixTextureId = -1;
        this.mPrefixFrameBufferId = -1;
        this.m2DTextureId = -1;
        this.m2DFrameBufferId = -1;
        this.mWatermarkTextureId = new LinkedList();
        this.mImageFilterInput = imageFilterInput;
    }

    @Override // com.huya.force.export.imagefilter.BaseImageFilter
    public ImageFilterInput getInput() {
        return this.mImageFilterInput;
    }

    @Override // com.huya.force.export.imagefilter.BaseImageFilter
    public void start() {
        ForceLog.info(TAG, "start");
        if (this.mImageFilterInput.getEglHandler() != null) {
            this.mImageFilterInput.getEglHandler().post(new Runnable() { // from class: com.huya.force.imagefilter.VideoBeauty.1
                @Override // java.lang.Runnable
                public void run() {
                    VideoBeauty.this.startImpl();
                }
            });
        } else {
            startImpl();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startImpl() {
        this.mTransform = GlHelper.newIdentityTransform();
        GPUImageFilter gPUImageFilter = new GPUImageFilter();
        this.mBlendFilter = gPUImageFilter;
        gPUImageFilter.init();
        GPUImageStTrackFilter gPUImageStTrackFilter = new GPUImageStTrackFilter();
        this.mThinFaceFilter = gPUImageStTrackFilter;
        gPUImageStTrackFilter.init();
        setFaceKeyPoints(new float[106]);
        initFrameBuffer();
        switchBeauty(this.mImageFilterInput.getBeautyType());
        setWatermark(this.mImageFilterInput.getWatermark());
    }

    @Override // com.huya.force.export.imagefilter.BaseImageFilter
    public void stop() {
        ForceLog.info(TAG, "stop");
        if (this.mImageFilterInput.getEglHandler() != null) {
            this.mImageFilterInput.getEglHandler().post(new Runnable() { // from class: com.huya.force.imagefilter.VideoBeauty.2
                @Override // java.lang.Runnable
                public void run() {
                    VideoBeauty.this.stopImpl();
                }
            });
        } else {
            stopImpl();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void stopImpl() {
        deleteWatermarkTexture();
        destroyFrameBuffer();
        destroyBeautyFilter();
        GPUImageStTrackFilter gPUImageStTrackFilter = this.mThinFaceFilter;
        if (gPUImageStTrackFilter != null) {
            gPUImageStTrackFilter.destroy();
            this.mThinFaceFilter = null;
        }
    }

    @Override // com.huya.force.export.imagefilter.BaseImageFilter
    public void switchBeauty(final BaseImageFilter.Type type) {
        this.mImageFilterInput.setBeautyType(type);
        if (this.mImageFilterInput.getEglHandler() != null) {
            this.mImageFilterInput.getEglHandler().post(new Runnable() { // from class: com.huya.force.imagefilter.VideoBeauty.3
                @Override // java.lang.Runnable
                public void run() {
                    VideoBeauty.this.switchBeautyImpl(type);
                }
            });
        } else {
            switchBeautyImpl(type);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void switchBeautyImpl(BaseImageFilter.Type type) {
        if (type != BaseImageFilter.Type.NONE) {
            this.mBeautyFilter = createBeautyFilter(type);
        } else {
            destroyBeautyFilter();
        }
    }

    @Override // com.huya.force.export.imagefilter.BaseImageFilter
    public void setBeautyWhite(float f) {
        BeautyFilterFactory.setNewBeautyWhite(this.mBeautyFilter, f);
    }

    @Override // com.huya.force.export.imagefilter.BaseImageFilter
    public void setBeautyDermabrasion(float f) {
        BeautyFilterFactory.setNewBeautyDermabrasion(this.mBeautyFilter, f);
    }

    @Override // com.huya.force.export.imagefilter.BaseImageFilter
    public void setFaceKeyPoints(float[] fArr) {
        GPUImageStTrackFilter gPUImageStTrackFilter = this.mThinFaceFilter;
        if (gPUImageStTrackFilter != null) {
            gPUImageStTrackFilter.setFaceKeyPoints(fArr);
        }
    }

    @Override // com.huya.force.export.imagefilter.BaseImageFilter
    public void setEyeScale(float f) {
        GPUImageStTrackFilter gPUImageStTrackFilter = this.mThinFaceFilter;
        if (gPUImageStTrackFilter != null) {
            gPUImageStTrackFilter.setEyeSize(f);
        }
    }

    @Override // com.huya.force.export.imagefilter.BaseImageFilter
    public void setFaceScale(float f) {
        GPUImageStTrackFilter gPUImageStTrackFilter = this.mThinFaceFilter;
        if (gPUImageStTrackFilter != null) {
            gPUImageStTrackFilter.setSmallfaceSize(f);
        }
    }

    @Override // com.huya.force.export.imagefilter.BaseImageFilter
    public void setShaveScale(float f) {
        GPUImageStTrackFilter gPUImageStTrackFilter = this.mThinFaceFilter;
        if (gPUImageStTrackFilter != null) {
            gPUImageStTrackFilter.setCutfaceSize(f);
        }
    }

    @Override // com.huya.force.export.imagefilter.BaseImageFilter
    public void setThinScale(float f) {
        GPUImageStTrackFilter gPUImageStTrackFilter = this.mThinFaceFilter;
        if (gPUImageStTrackFilter != null) {
            gPUImageStTrackFilter.setFaceSize(f);
        }
    }

    @Override // com.huya.force.export.imagefilter.BaseImageFilter
    public void setChinScale(float f) {
        GPUImageStTrackFilter gPUImageStTrackFilter = this.mThinFaceFilter;
        if (gPUImageStTrackFilter != null) {
            gPUImageStTrackFilter.setChinSize(f);
        }
    }

    @Override // com.huya.force.export.imagefilter.BaseImageFilter
    public void setWatermark(final List<Watermark> list) {
        if (this.mImageFilterInput.getEglHandler() != null) {
            this.mImageFilterInput.getEglHandler().post(new Runnable() { // from class: com.huya.force.imagefilter.VideoBeauty.4
                @Override // java.lang.Runnable
                public void run() {
                    VideoBeauty.this.mImageFilterInput.setWatermark(list);
                    VideoBeauty.this.setWatermarkImpl();
                }
            });
        } else {
            this.mImageFilterInput.setWatermark(list);
            setWatermarkImpl();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setWatermarkImpl() {
        deleteWatermarkTexture();
        List<Watermark> watermark = this.mImageFilterInput.getWatermark();
        if (watermark == null) {
            return;
        }
        float[] fArr = new float[8];
        GlHelper.FULL_RECTANGLE_TEX_BUF.position(0);
        GlHelper.FULL_RECTANGLE_TEX_BUF.get(fArr);
        this.mWatermarkTextureCoords = GlUtil.createFloatBuffer(new float[]{fArr[0], flip(fArr[1]), fArr[2], flip(fArr[3]), fArr[4], flip(fArr[5]), fArr[6], flip(fArr[7])});
        Iterator<Watermark> it = watermark.iterator();
        while (it.hasNext()) {
            this.mWatermarkTextureId.add(Integer.valueOf(OpenGlUtils.loadTexture(it.next().getBitmap(), -1, false)));
        }
    }

    @Override // com.huya.force.export.imagefilter.BaseImageFilter
    public void put(final VideoFrameData videoFrameData) {
        if (this.mImageFilterInput.getEglHandler() != null) {
            this.mImageFilterInput.getEglHandler().post(new Runnable() { // from class: com.huya.force.imagefilter.VideoBeauty.5
                @Override // java.lang.Runnable
                public void run() {
                    VideoBeauty.this.putImpl(videoFrameData);
                }
            });
        } else {
            putImpl(videoFrameData);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void putImpl(VideoFrameData videoFrameData) {
        List<Watermark> watermark;
        if (this.mDrawExt == null) {
            return;
        }
        long jUptimeMillis = SystemClock.uptimeMillis();
        int i = videoFrameData.textureId;
        int i2 = videoFrameData.frameBufferId;
        int i3 = videoFrameData.textureTarget;
        float[] fArr = videoFrameData.transform;
        if (i3 == 36197) {
            GLES20.glViewport(0, 0, this.mImageFilterInput.getPreviewWidth(), this.mImageFilterInput.getPreviewHeight());
            GLES20.glBindFramebuffer(36160, this.mPrefixFrameBufferId);
            GLES20.glClearColor(0.0f, 0.0f, 1.0f, 0.0f);
            this.mDrawExt.drawFrame(videoFrameData.textureId, videoFrameData.transform);
            GLES20.glViewport(0, 0, this.mImageFilterInput.getEncodeWidth(), this.mImageFilterInput.getEncodeHeight());
            GLES20.glBindFramebuffer(36160, this.m2DFrameBufferId);
            GlUtil.checkGlError("glBindFramebuffer m2DFrameBufferId");
            this.mDraw2d.drawFrame(this.mPrefixTextureId, GlUtil.IDENTITY_MATRIX);
            i = this.m2DTextureId;
            i2 = this.m2DFrameBufferId;
            i3 = 3553;
            fArr = this.mTransform;
        }
        if (this.mBeautyFilter != null && this.mEnableBeauty) {
            this.mBeautyFilter.onDraw(i, GlHelper.FULL_RECTANGLE_BUF, GlHelper.FULL_RECTANGLE_TEX_BUF);
            GlUtil.checkGlError("beauty draw");
        }
        if (this.mThinFaceFilter != null && this.mEnableThinFace) {
            this.mThinFaceFilter.onDraw(i);
            GlUtil.checkGlError("thinface draw");
        }
        if (this.mEnableWaterMark && this.mBlendFilter != null && (watermark = this.mImageFilterInput.getWatermark()) != null && this.mWatermarkTextureId != null) {
            for (int i4 = 0; i4 < this.mWatermarkTextureId.size(); i4++) {
                Watermark watermark2 = watermark.get(i4);
                GLES20.glViewport(watermark2.getX(), (this.mImageFilterInput.getEncodeHeight() - watermark2.getHeight()) - watermark2.getY(), watermark2.getWidth(), watermark2.getHeight());
                GLES20.glDisable(2929);
                GLES20.glEnable(3042);
                GLES20.glBlendFunc(1, 771);
                this.mBlendFilter.onDraw(this.mWatermarkTextureId.get(i4).intValue(), GlHelper.FULL_RECTANGLE_BUF, this.mWatermarkTextureCoords);
                GLES20.glDisable(3042);
            }
        }
        GlUtil.checkGlError("draw end");
        GLES20.glBindFramebuffer(36160, 0);
        videoFrameData.textureId = i;
        videoFrameData.frameBufferId = i2;
        videoFrameData.textureTarget = i3;
        videoFrameData.transform = fArr;
        Log.i(TAG, "preprocess put time=" + (SystemClock.uptimeMillis() - jUptimeMillis));
        if (this.mListener != null) {
            this.mListener.onImageFilterResult(videoFrameData);
        }
    }

    private void initFrameBuffer() {
        if (this.mPrefixTextureId == -1 && this.mPrefixFrameBufferId == -1) {
            this.mPrefixTextureId = GlHelper.createTexture(3553, this.mImageFilterInput.getPreviewWidth(), this.mImageFilterInput.getPreviewHeight());
            int iCreateFrameBuffer = GlHelper.createFrameBuffer();
            this.mPrefixFrameBufferId = iCreateFrameBuffer;
            GlHelper.bindFrameBufferWidthTexture(36160, iCreateFrameBuffer, 3553, this.mPrefixTextureId);
        }
        if (this.m2DTextureId == -1 && this.m2DFrameBufferId == -1) {
            this.m2DTextureId = GlHelper.createTexture(3553, this.mImageFilterInput.getEncodeWidth(), this.mImageFilterInput.getEncodeHeight());
            int iCreateFrameBuffer2 = GlHelper.createFrameBuffer();
            this.m2DFrameBufferId = iCreateFrameBuffer2;
            GlHelper.bindFrameBufferWidthTexture(36160, iCreateFrameBuffer2, 3553, this.m2DTextureId);
        }
        if (this.mDrawExt == null) {
            this.mDrawExt = new FullFrameRect(new Texture2dProgram(Texture2dProgram.ProgramType.TEXTURE_EXT));
        }
        if (this.mDraw2d == null) {
            this.mDraw2d = new FullFrameRect(new Texture2dProgram(Texture2dProgram.ProgramType.TEXTURE_2D));
        }
    }

    private void destroyFrameBuffer() {
        this.mPrefixTextureId = GlHelper.deleteTexture(this.mPrefixTextureId);
        this.mPrefixFrameBufferId = GlHelper.deleteFrameBuffer(this.mPrefixFrameBufferId);
        this.m2DTextureId = GlHelper.deleteTexture(this.m2DTextureId);
        this.m2DFrameBufferId = GlHelper.deleteFrameBuffer(this.m2DFrameBufferId);
        FullFrameRect fullFrameRect = this.mDrawExt;
        if (fullFrameRect != null) {
            fullFrameRect.release(false);
            this.mDrawExt = null;
        }
        FullFrameRect fullFrameRect2 = this.mDraw2d;
        if (fullFrameRect2 != null) {
            fullFrameRect2.release(false);
            this.mDraw2d = null;
        }
    }

    private GPUImageFilter createBeautyFilter(BaseImageFilter.Type type) {
        ImageFilterInput imageFilterInput = this.mImageFilterInput;
        if (imageFilterInput == null) {
            return null;
        }
        GPUImageFilter gPUImageFilterCreateBeautyFilter = BeautyFilterFactory.createBeautyFilter(imageFilterInput.getContext(), type);
        if (gPUImageFilterCreateBeautyFilter != null) {
            gPUImageFilterCreateBeautyFilter.init();
            gPUImageFilterCreateBeautyFilter.onOutputSizeChanged(this.mImageFilterInput.getEncodeWidth(), this.mImageFilterInput.getEncodeHeight());
        }
        return gPUImageFilterCreateBeautyFilter;
    }

    private void destroyBeautyFilter() {
        GPUImageFilter gPUImageFilter = this.mBeautyFilter;
        if (gPUImageFilter != null) {
            gPUImageFilter.destroy();
            this.mBeautyFilter = null;
        }
    }

    private boolean enabled() {
        return this.mEnableBeauty || this.mEnableThinFace || this.mEnableWaterMark;
    }

    private void deleteWatermarkTexture() {
        List<Integer> list = this.mWatermarkTextureId;
        if (list != null) {
            Iterator<Integer> it = list.iterator();
            while (it.hasNext()) {
                GlHelper.deleteTexture(it.next().intValue());
            }
            this.mWatermarkTextureId.clear();
            this.mWatermarkTextureCoords = null;
        }
    }
}
