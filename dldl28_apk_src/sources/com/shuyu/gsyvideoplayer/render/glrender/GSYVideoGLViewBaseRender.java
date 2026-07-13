package com.shuyu.gsyvideoplayer.render.glrender;

import android.graphics.Bitmap;
import android.graphics.SurfaceTexture;
import android.opengl.GLES20;
import android.opengl.GLException;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.os.Handler;
import android.view.Surface;
import com.shuyu.gsyvideoplayer.listener.GSYVideoShotListener;
import com.shuyu.gsyvideoplayer.render.view.GSYVideoGLView;
import com.shuyu.gsyvideoplayer.render.view.listener.GLSurfaceListener;
import com.shuyu.gsyvideoplayer.render.view.listener.GSYVideoGLRenderErrorListener;
import com.shuyu.gsyvideoplayer.utils.Debuger;
import java.nio.IntBuffer;
import javax.microedition.khronos.opengles.GL10;

/* JADX INFO: loaded from: classes3.dex */
public abstract class GSYVideoGLViewBaseRender implements GLSurfaceView.Renderer, SurfaceTexture.OnFrameAvailableListener {
    protected GLSurfaceListener mGSYSurfaceListener;
    protected GSYVideoGLRenderErrorListener mGSYVideoGLRenderErrorListener;
    protected GLSurfaceView mSurfaceView;
    protected boolean mHighShot = false;
    protected float[] mMVPMatrix = new float[16];
    protected float[] mSTMatrix = new float[16];
    protected int mCurrentViewWidth = 0;
    protected int mCurrentViewHeight = 0;
    protected int mCurrentVideoWidth = 0;
    protected int mCurrentVideoHeight = 0;
    protected boolean mChangeProgram = false;
    protected boolean mChangeProgramSupportError = false;
    protected Handler mHandler = new Handler();

    public GSYVideoGLView.ShaderInterface getEffect() {
        return null;
    }

    public abstract void releaseAll();

    public void setEffect(GSYVideoGLView.ShaderInterface shaderInterface) {
    }

    public void setGSYVideoShotListener(GSYVideoShotListener gSYVideoShotListener, boolean z) {
    }

    public void takeShotPic() {
    }

    public void setSurfaceView(GLSurfaceView gLSurfaceView) {
        this.mSurfaceView = gLSurfaceView;
    }

    public void sendSurfaceForPlayer(final Surface surface) {
        this.mHandler.post(new Runnable() { // from class: com.shuyu.gsyvideoplayer.render.glrender.GSYVideoGLViewBaseRender.1
            @Override // java.lang.Runnable
            public void run() {
                if (GSYVideoGLViewBaseRender.this.mGSYSurfaceListener != null) {
                    GSYVideoGLViewBaseRender.this.mGSYSurfaceListener.onSurfaceAvailable(surface);
                }
            }
        });
    }

    protected int loadShader(int i, String str) {
        int iGlCreateShader = GLES20.glCreateShader(i);
        if (iGlCreateShader == 0) {
            return iGlCreateShader;
        }
        GLES20.glShaderSource(iGlCreateShader, str);
        GLES20.glCompileShader(iGlCreateShader);
        int[] iArr = new int[1];
        GLES20.glGetShaderiv(iGlCreateShader, 35713, iArr, 0);
        if (iArr[0] != 0) {
            return iGlCreateShader;
        }
        Debuger.printfError("Could not compile shader " + i + ":");
        Debuger.printfError(GLES20.glGetShaderInfoLog(iGlCreateShader));
        GLES20.glDeleteShader(iGlCreateShader);
        return 0;
    }

    protected int createProgram(String str, String str2) {
        int iLoadShader;
        int iLoadShader2 = loadShader(35633, str);
        if (iLoadShader2 == 0 || (iLoadShader = loadShader(35632, str2)) == 0) {
            return 0;
        }
        int iGlCreateProgram = GLES20.glCreateProgram();
        if (iGlCreateProgram != 0) {
            GLES20.glAttachShader(iGlCreateProgram, iLoadShader2);
            checkGlError("glAttachShader");
            GLES20.glAttachShader(iGlCreateProgram, iLoadShader);
            checkGlError("glAttachShader");
            GLES20.glLinkProgram(iGlCreateProgram);
            int[] iArr = new int[1];
            GLES20.glGetProgramiv(iGlCreateProgram, 35714, iArr, 0);
            if (iArr[0] != 1) {
                Debuger.printfError("Could not link program: ");
                Debuger.printfError(GLES20.glGetProgramInfoLog(iGlCreateProgram));
                GLES20.glDeleteProgram(iGlCreateProgram);
                return 0;
            }
        }
        return iGlCreateProgram;
    }

    protected void checkGlError(final String str) {
        final int iGlGetError = GLES20.glGetError();
        if (iGlGetError != 0) {
            Debuger.printfError(str + ": glError " + iGlGetError);
            this.mHandler.post(new Runnable() { // from class: com.shuyu.gsyvideoplayer.render.glrender.GSYVideoGLViewBaseRender.2
                @Override // java.lang.Runnable
                public void run() {
                    if (GSYVideoGLViewBaseRender.this.mGSYVideoGLRenderErrorListener != null) {
                        GSYVideoGLViewBaseRender.this.mGSYVideoGLRenderErrorListener.onError(GSYVideoGLViewBaseRender.this, str + ": glError " + iGlGetError, iGlGetError, GSYVideoGLViewBaseRender.this.mChangeProgramSupportError);
                    }
                    GSYVideoGLViewBaseRender.this.mChangeProgramSupportError = false;
                }
            });
        }
    }

    protected Bitmap createBitmapFromGLSurface(int i, int i2, int i3, int i4, GL10 gl10) {
        int i5 = i3 * i4;
        int[] iArr = new int[i5];
        int[] iArr2 = new int[i5];
        IntBuffer intBufferWrap = IntBuffer.wrap(iArr);
        intBufferWrap.position(0);
        try {
            gl10.glReadPixels(i, i2, i3, i4, 6408, 5121, intBufferWrap);
            for (int i6 = 0; i6 < i4; i6++) {
                int i7 = i6 * i3;
                int i8 = ((i4 - i6) - 1) * i3;
                for (int i9 = 0; i9 < i3; i9++) {
                    int i10 = iArr[i7 + i9];
                    iArr2[i8 + i9] = (i10 & (-16711936)) | ((i10 << 16) & 16711680) | ((i10 >> 16) & 255);
                }
            }
            if (this.mHighShot) {
                return Bitmap.createBitmap(iArr2, i3, i4, Bitmap.Config.ARGB_8888);
            }
            return Bitmap.createBitmap(iArr2, i3, i4, Bitmap.Config.RGB_565);
        } catch (GLException unused) {
            return null;
        }
    }

    public void setGSYSurfaceListener(GLSurfaceListener gLSurfaceListener) {
        this.mGSYSurfaceListener = gLSurfaceListener;
    }

    public float[] getMVPMatrix() {
        return this.mMVPMatrix;
    }

    public void setMVPMatrix(float[] fArr) {
        this.mMVPMatrix = fArr;
    }

    public int getCurrentViewWidth() {
        return this.mCurrentViewWidth;
    }

    public void setCurrentViewWidth(int i) {
        this.mCurrentViewWidth = i;
    }

    public int getCurrentViewHeight() {
        return this.mCurrentViewHeight;
    }

    public void setCurrentViewHeight(int i) {
        this.mCurrentViewHeight = i;
    }

    public int getCurrentVideoWidth() {
        return this.mCurrentVideoWidth;
    }

    public void setCurrentVideoWidth(int i) {
        this.mCurrentVideoWidth = i;
    }

    public int getCurrentVideoHeight() {
        return this.mCurrentVideoHeight;
    }

    public void setCurrentVideoHeight(int i) {
        this.mCurrentVideoHeight = i;
    }

    public void initRenderSize() {
        int i = this.mCurrentViewWidth;
        if (i == 0 || this.mCurrentViewHeight == 0) {
            return;
        }
        Matrix.scaleM(this.mMVPMatrix, 0, i / this.mSurfaceView.getWidth(), this.mCurrentViewHeight / this.mSurfaceView.getHeight(), 1.0f);
    }

    public void setGSYVideoGLRenderErrorListener(GSYVideoGLRenderErrorListener gSYVideoGLRenderErrorListener) {
        this.mGSYVideoGLRenderErrorListener = gSYVideoGLRenderErrorListener;
    }
}
