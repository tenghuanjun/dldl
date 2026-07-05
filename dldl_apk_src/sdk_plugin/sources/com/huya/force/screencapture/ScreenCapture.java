package com.huya.force.screencapture;

import android.app.Activity;
import android.content.Intent;
import android.graphics.SurfaceTexture;
import android.media.projection.MediaProjection;
import android.media.projection.MediaProjectionManager;
import android.opengl.GLES20;
import android.os.Handler;
import android.os.SystemClock;
import android.util.DisplayMetrics;
import android.view.Surface;
import android.view.WindowManager;
import com.huya.force.common.VideoFrameData;
import com.huya.force.export.screencapture.BaseScreenCapture;
import com.huya.force.export.videocapture.BaseVideoCapture;
import com.huya.force.export.videocapture.VideoCaptureInput;
import com.huya.force.gles.GlUtil;
import com.huya.force.log.ForceLog;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class ScreenCapture extends BaseScreenCapture implements SurfaceTexture.OnFrameAvailableListener {
    private static final int REQUEST_SCREEN_RECORD = 10001;
    private static final String TAG = "ScreenCapture";
    private static final String VIRTUAL_DISPLAY_SCREEN_CAPTURE = "huyaforcescreencapture";
    private Handler mHandler;
    private Surface mInputSurface;
    long mLastFrameTimestamp;
    private MediaProjection mMediaProjection;
    MediaProjectionManager mMediaProjectionManager;
    ScreenCaptureInput mScreenCaptureInput;
    private SurfaceTexture mSurfaceTexture;
    private int mTextureId;
    VideoFrameData mVideoFrameData;

    @Override // com.huya.force.export.videocapture.BaseVideoCapture
    public SurfaceTexture getPreviewSurfaceTexture() {
        return null;
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

    public ScreenCapture(VideoCaptureInput videoCaptureInput) {
        super(videoCaptureInput);
        this.mTextureId = -1;
        this.mLastFrameTimestamp = -1L;
        this.mScreenCaptureInput = (ScreenCaptureInput) videoCaptureInput;
    }

    @Override // com.huya.force.export.videocapture.BaseVideoCapture
    public void start() {
        super.start();
        ForceLog.info(TAG, "start");
        if (this.mScreenCaptureInput.getEglHandler() != null) {
            this.mHandler = this.mScreenCaptureInput.getEglHandler();
        } else {
            this.mHandler = new Handler();
        }
        this.mLastFrameTimestamp = -1L;
        Activity activity = this.mScreenCaptureInput.getActivity();
        MediaProjectionManager mediaProjectionManager = (MediaProjectionManager) activity.getSystemService("media_projection");
        this.mMediaProjectionManager = mediaProjectionManager;
        activity.startActivityForResult(mediaProjectionManager.createScreenCaptureIntent(), 10001);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startImpl() {
        this.mTextureId = createOESTextureId();
        SurfaceTexture surfaceTexture = new SurfaceTexture(this.mTextureId);
        this.mSurfaceTexture = surfaceTexture;
        surfaceTexture.setDefaultBufferSize(this.mScreenCaptureInput.getWidth(), this.mScreenCaptureInput.getHeight());
        this.mSurfaceTexture.setOnFrameAvailableListener(this);
        WindowManager windowManager = (WindowManager) this.mScreenCaptureInput.getActivity().getSystemService("window");
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        this.mInputSurface = new Surface(this.mSurfaceTexture);
        try {
            this.mMediaProjection.createVirtualDisplay(VIRTUAL_DISPLAY_SCREEN_CAPTURE, this.mScreenCaptureInput.getWidth(), this.mScreenCaptureInput.getHeight(), displayMetrics.densityDpi, 16, this.mInputSurface, null, null);
            if (this.mListener != null) {
                this.mListener.onStartResult(BaseVideoCapture.VideoCaptureStartResult.kStartSuccess);
            }
            this.mHandler.postDelayed(new Runnable() { // from class: com.huya.force.screencapture.ScreenCapture.1
                @Override // java.lang.Runnable
                public void run() {
                    ScreenCapture.this.onFrameTimeout();
                }
            }, 1000 / this.mScreenCaptureInput.getFps());
            this.mStarted = true;
        } catch (Exception e) {
            e.printStackTrace();
            if (this.mListener != null) {
                this.mListener.onStartResult(BaseVideoCapture.VideoCaptureStartResult.kStartError);
            }
        }
    }

    @Override // com.huya.force.export.screencapture.BaseScreenCapture
    public void onActivityResult(int i, int i2, Intent intent) {
        if (i == 10001) {
            this.mMediaProjection = this.mMediaProjectionManager.getMediaProjection(i2, intent);
            this.mHandler.post(new Runnable() { // from class: com.huya.force.screencapture.ScreenCapture.2
                @Override // java.lang.Runnable
                public void run() {
                    ScreenCapture.this.startImpl();
                }
            });
        }
    }

    @Override // com.huya.force.export.videocapture.BaseVideoCapture
    public void stop() {
        ForceLog.info(TAG, "stop");
        this.mHandler.post(new Runnable() { // from class: com.huya.force.screencapture.ScreenCapture.3
            @Override // java.lang.Runnable
            public void run() {
                ScreenCapture.this.stopImpl();
            }
        });
    }

    @Override // android.graphics.SurfaceTexture.OnFrameAvailableListener
    public void onFrameAvailable(SurfaceTexture surfaceTexture) {
        if (this.mSurfaceTexture == null || !this.mStarted) {
            return;
        }
        this.mSurfaceTexture.updateTexImage();
        float[] fArr = new float[16];
        this.mSurfaceTexture.getTransformMatrix(fArr);
        long jElapsedRealtimeNanos = SystemClock.elapsedRealtimeNanos();
        this.mLastFrameTimestamp = jElapsedRealtimeNanos;
        this.mVideoFrameData = new VideoFrameData(this.mTextureId, 36197, fArr, jElapsedRealtimeNanos);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onFrameTimeout() {
        if (this.mListener != null && this.mVideoFrameData != null) {
            long jElapsedRealtimeNanos = SystemClock.elapsedRealtimeNanos();
            this.mVideoFrameData.timestamp += jElapsedRealtimeNanos - this.mLastFrameTimestamp;
            this.mLastFrameTimestamp = jElapsedRealtimeNanos;
            this.mListener.onFrameAvailable(this.mVideoFrameData);
        }
        if (this.mStarted) {
            this.mHandler.postDelayed(new Runnable() { // from class: com.huya.force.screencapture.ScreenCapture.4
                @Override // java.lang.Runnable
                public void run() {
                    ScreenCapture.this.onFrameTimeout();
                }
            }, 1000 / this.mScreenCaptureInput.getFps());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void stopImpl() {
        this.mStarted = false;
        this.mLastFrameTimestamp = -1L;
        MediaProjection mediaProjection = this.mMediaProjection;
        if (mediaProjection != null) {
            mediaProjection.stop();
            this.mMediaProjection = null;
        }
        Surface surface = this.mInputSurface;
        if (surface != null) {
            surface.release();
            this.mInputSurface = null;
        }
        int i = this.mTextureId;
        if (i != -1) {
            GLES20.glDeleteTextures(1, new int[]{i}, 0);
            this.mTextureId = -1;
        }
        SurfaceTexture surfaceTexture = this.mSurfaceTexture;
        if (surfaceTexture != null) {
            surfaceTexture.release();
            this.mSurfaceTexture = null;
        }
        super.stop();
    }
}
