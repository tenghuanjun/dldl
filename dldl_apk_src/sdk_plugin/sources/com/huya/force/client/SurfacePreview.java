package com.huya.force.client;

import android.graphics.Point;
import android.opengl.GLES20;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.Surface;
import com.huya.force.common.VideoFrameData;
import com.huya.force.gles.EglCore;
import com.huya.force.gles.FullFrameRect;
import com.huya.force.gles.Texture2dProgram;
import com.huya.force.gles.WindowSurface;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class SurfacePreview {
    private static final String TAG = "SurfacePreview";
    private FullFrameRect m2DFullScreen;
    private final EglCore mEglCore;
    private Handler mEglHandler;
    private FullFrameRect mExtFullScreen;
    private int mPreviewHeight;
    private int mPreviewWidth;
    private final Surface mSurface;
    private WindowSurface mWindowSurface;
    private int mWindowWidth = 0;
    private int mWindowHeight = 0;
    private int mDeltaX = 0;
    private int mDeltaY = 0;

    public SurfacePreview(Handler handler, EglCore eglCore, Surface surface, int i, int i2) {
        this.mPreviewWidth = 0;
        this.mPreviewHeight = 0;
        this.mEglHandler = handler;
        this.mEglCore = eglCore;
        this.mSurface = surface;
        this.mPreviewWidth = i;
        this.mPreviewHeight = i2;
    }

    public void start(int i, int i2) {
        this.mPreviewWidth = i;
        this.mPreviewHeight = i2;
        this.mEglHandler.post(new Runnable() { // from class: com.huya.force.client.SurfacePreview.1
            @Override // java.lang.Runnable
            public void run() {
                SurfacePreview.this.makeEglCurrent();
                SurfacePreview.this.mExtFullScreen = new FullFrameRect(new Texture2dProgram(Texture2dProgram.ProgramType.TEXTURE_EXT));
                SurfacePreview.this.m2DFullScreen = new FullFrameRect(new Texture2dProgram(Texture2dProgram.ProgramType.TEXTURE_2D));
            }
        });
    }

    public void stop() {
        this.mEglHandler.post(new Runnable() { // from class: com.huya.force.client.SurfacePreview.2
            @Override // java.lang.Runnable
            public void run() {
                if (SurfacePreview.this.mWindowSurface != null) {
                    SurfacePreview.this.mWindowSurface.release();
                    SurfacePreview.this.mWindowSurface = null;
                }
                if (SurfacePreview.this.mExtFullScreen != null) {
                    SurfacePreview.this.mExtFullScreen.release(true);
                    SurfacePreview.this.mExtFullScreen = null;
                }
                if (SurfacePreview.this.m2DFullScreen != null) {
                    SurfacePreview.this.m2DFullScreen.release(true);
                    SurfacePreview.this.m2DFullScreen = null;
                }
            }
        });
    }

    private Point captureSize(int i, int i2, int i3, int i4) {
        if (i * i4 == i2 * i3) {
            return new Point(i3, i4);
        }
        float f = (i * 1.0f) / i2;
        float f2 = i3;
        float f3 = i4;
        if (f > (1.0f * f2) / f3) {
            return new Point((int) (f3 * f), i4);
        }
        return new Point(i3, (int) (f2 / f));
    }

    private void switchToSurface() {
        this.mWindowSurface.makeCurrent();
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        GLES20.glClear(16640);
        int i = this.mDeltaX;
        int i2 = this.mDeltaY;
        GLES20.glViewport((-i) / 2, (-i2) / 2, this.mWindowWidth + i, this.mWindowHeight + i2);
    }

    public void put(VideoFrameData videoFrameData) {
        if (this.mWindowSurface != null) {
            long jUptimeMillis = SystemClock.uptimeMillis();
            switchToSurface();
            if (videoFrameData.textureTarget == 36197) {
                this.mExtFullScreen.drawFrame(videoFrameData.textureId, videoFrameData.transform);
            } else {
                this.m2DFullScreen.drawFrame(videoFrameData.textureId, videoFrameData.transform);
            }
            this.mWindowSurface.swapBuffers();
            Log.i(TAG, "preview time=" + (SystemClock.uptimeMillis() - jUptimeMillis));
        }
    }

    public void updateSize(int i, int i2) {
        if (this.mWindowWidth == i && this.mWindowHeight == i2) {
            return;
        }
        updateSizeImpl(i, i2);
    }

    private void updateSizeImpl(int i, int i2) {
        Point pointCaptureSize = captureSize(this.mPreviewWidth, this.mPreviewHeight, i, i2);
        this.mDeltaX = pointCaptureSize.x - i;
        this.mDeltaY = pointCaptureSize.y - i2;
        this.mWindowWidth = i;
        this.mWindowHeight = i2;
    }

    public void makeEglCurrent() {
        if (this.mWindowSurface == null) {
            this.mWindowSurface = new WindowSurface(this.mEglCore, this.mSurface, false);
        }
        this.mWindowSurface.makeCurrent();
    }
}
