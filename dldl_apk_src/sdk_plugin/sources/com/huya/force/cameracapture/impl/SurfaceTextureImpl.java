package com.huya.force.cameracapture.impl;

import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.opengl.GLES20;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.view.Surface;
import com.huya.force.collect.FpsCounter;
import com.huya.force.common.VideoFrameData;
import com.huya.force.export.surface.ISurface;
import com.huya.force.export.surface.SurfaceConfig;
import com.huya.force.gles.GlUtil;
import com.huya.force.log.ForceLog;
import java.io.IOException;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class SurfaceTextureImpl extends ISurface implements SurfaceTexture.OnFrameAvailableListener {
    private static final String TAG = "SurfaceTextureImpl";
    private Handler mHandler;
    private SurfaceTexture mSurfaceTexture;
    private int mTextureId = -1;
    private FpsCounter mFpsCounter = new FpsCounter(TAG);

    @Override // com.huya.force.export.surface.ISurface
    public void start(SurfaceConfig surfaceConfig) {
        this.mHandler = new Handler();
        this.mTextureId = createOESTextureId();
        SurfaceTexture surfaceTexture = new SurfaceTexture(this.mTextureId);
        this.mSurfaceTexture = surfaceTexture;
        surfaceTexture.setDefaultBufferSize(surfaceConfig.width, surfaceConfig.height);
        this.mSurfaceTexture.setOnFrameAvailableListener(this);
    }

    @Override // com.huya.force.export.surface.ISurface
    public void updateSize(int i, int i2) {
        if (this.mSurfaceTexture == null) {
            ForceLog.error(TAG, "mSurfaceTexture == null");
        } else {
            ForceLog.info(TAG, String.format("updateSize width=%d, height=%d", Integer.valueOf(i), Integer.valueOf(i2)));
            this.mSurfaceTexture.setDefaultBufferSize(i, i2);
        }
    }

    @Override // com.huya.force.export.surface.ISurface
    public void stop() {
        int i = this.mTextureId;
        if (i != -1) {
            GLES20.glDeleteTextures(1, new int[]{i}, 0);
            this.mTextureId = -1;
        }
        SurfaceTexture surfaceTexture = this.mSurfaceTexture;
        if (surfaceTexture != null) {
            surfaceTexture.detachFromGLContext();
            this.mSurfaceTexture.release();
            this.mSurfaceTexture = null;
        }
        this.mHandler.removeCallbacksAndMessages(null);
        this.mHandler = null;
    }

    @Override // com.huya.force.export.surface.ISurface
    public Surface getSurface() {
        return new Surface(this.mSurfaceTexture);
    }

    @Override // com.huya.force.export.surface.ISurface
    public Class getOutputClass() {
        return SurfaceTexture.class;
    }

    @Override // com.huya.force.export.surface.ISurface
    public Object getSurfaceObject() {
        return this.mSurfaceTexture;
    }

    @Override // com.huya.force.export.surface.ISurface
    public void setCamera1Preview(Camera camera) {
        if (camera == null) {
            ForceLog.error(TAG, "setCamera1Preview camera == null");
            return;
        }
        try {
            camera.setPreviewTexture(this.mSurfaceTexture);
        } catch (IOException e) {
            ForceLog.error(TAG, String.format("setCamera1Preview IOException e=%s", e.toString()));
            e.printStackTrace();
        }
    }

    @Override // android.graphics.SurfaceTexture.OnFrameAvailableListener
    public void onFrameAvailable(final SurfaceTexture surfaceTexture) {
        if (!this.mValid) {
            ForceLog.error(TAG, "onFrameAvailable !mValid");
            return;
        }
        this.mFpsCounter.addFrame();
        if (this.mHandler == null) {
            ForceLog.error(TAG, "onFrameAvailable mHandler == null");
        } else if (isEglThread()) {
            onFrameAvailableImpl(surfaceTexture);
        } else {
            this.mHandler.post(new Runnable() { // from class: com.huya.force.cameracapture.impl.SurfaceTextureImpl.1
                @Override // java.lang.Runnable
                public void run() {
                    SurfaceTextureImpl.this.onFrameAvailableImpl(surfaceTexture);
                }
            });
        }
    }

    private boolean isEglThread() {
        Handler handler = this.mHandler;
        return handler != null && handler.getLooper() == Looper.myLooper();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onFrameAvailableImpl(SurfaceTexture surfaceTexture) {
        float[] fArr;
        SurfaceTexture surfaceTexture2 = this.mSurfaceTexture;
        if (surfaceTexture2 == null) {
            ForceLog.error(TAG, "onFrameAvailable mSurfaceTexture == null");
            return;
        }
        if (surfaceTexture2 != surfaceTexture) {
            ForceLog.error(TAG, "onFrameAvailable mSurfaceTexture != surfaceTexture");
            return;
        }
        if (this.mListener != null) {
            this.mListener.makePreviewCurrent();
        }
        this.mSurfaceTexture.updateTexImage();
        if (this.mTransform == null) {
            fArr = new float[GlUtil.IDENTITY_MATRIX.length];
            this.mSurfaceTexture.getTransformMatrix(fArr);
        } else {
            fArr = this.mTransform;
        }
        float[] fArr2 = fArr;
        long jElapsedRealtimeNanos = SystemClock.elapsedRealtimeNanos();
        if (this.mListener != null) {
            this.mListener.onFrameAvailable(new VideoFrameData(this.mTextureId, 36197, fArr2, jElapsedRealtimeNanos));
        }
    }

    private int createOESTextureId() {
        int[] iArr = new int[1];
        GLES20.glGenTextures(1, iArr, 0);
        GlUtil.checkGlError("glGenTextures");
        int i = iArr[0];
        GLES20.glBindTexture(36197, i);
        GlUtil.checkGlError("glBindTexture " + i);
        GLES20.glTexParameterf(36197, 10241, 9728.0f);
        GLES20.glTexParameterf(36197, 10240, 9729.0f);
        GLES20.glTexParameteri(36197, 10242, 33071);
        GLES20.glTexParameteri(36197, 10243, 33071);
        GlUtil.checkGlError("glTexParameter");
        return i;
    }
}
