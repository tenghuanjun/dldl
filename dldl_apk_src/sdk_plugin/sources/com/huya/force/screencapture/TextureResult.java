package com.huya.force.screencapture;

import android.graphics.SurfaceTexture;
import android.view.Surface;
import com.huya.force.gles.EglCore;
import com.huya.force.gles.FullFrameRect;
import com.huya.force.gles.Texture2dProgram;
import com.huya.force.gles.WindowSurface;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class TextureResult implements SurfaceTexture.OnFrameAvailableListener {
    private EglCore mEglCore = new EglCore(null, 1);
    private FullFrameRect mFullScreen;
    private Surface mInputSurface;
    private SurfaceTexture mSurfaceTexture;
    private int mTextureId;
    private WindowSurface mWindowSurface;

    public TextureResult(int i, int i2, Surface surface) {
        FullFrameRect fullFrameRect = new FullFrameRect(new Texture2dProgram(Texture2dProgram.ProgramType.TEXTURE_EXT));
        this.mFullScreen = fullFrameRect;
        this.mTextureId = fullFrameRect.createTextureObject();
        SurfaceTexture surfaceTexture = new SurfaceTexture(this.mTextureId);
        this.mSurfaceTexture = surfaceTexture;
        surfaceTexture.setDefaultBufferSize(i, i2);
        this.mSurfaceTexture.setOnFrameAvailableListener(this);
        this.mInputSurface = new Surface(this.mSurfaceTexture);
        this.mWindowSurface = new WindowSurface(this.mEglCore, surface, false);
    }

    public Surface getInputSurface() {
        return this.mInputSurface;
    }

    @Override // android.graphics.SurfaceTexture.OnFrameAvailableListener
    public void onFrameAvailable(SurfaceTexture surfaceTexture) {
        this.mSurfaceTexture.updateTexImage();
        this.mWindowSurface.makeCurrent();
        float[] fArr = new float[16];
        this.mSurfaceTexture.getTransformMatrix(fArr);
        this.mFullScreen.drawFrame(this.mTextureId, fArr);
        this.mSurfaceTexture.getTimestamp();
    }
}
