package com.huya.mtp.utils.gl.render;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import com.huya.mtp.utils.gl.render.director.KGLDirector2D;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public abstract class KGLRender2D implements GLSurfaceView.Renderer {
    private KGLDirector2D mDirector;

    protected abstract void onSurfaceChanged(int i, int i2);

    protected abstract void onSurfaceCreated();

    @Override // android.opengl.GLSurfaceView.Renderer
    public void onSurfaceCreated(GL10 gl10, EGLConfig eGLConfig) {
        this.mDirector = new KGLDirector2D(0);
        onSurfaceCreated();
    }

    @Override // android.opengl.GLSurfaceView.Renderer
    public void onSurfaceChanged(GL10 gl10, int i, int i2) {
        this.mDirector.onWindowChanged(i, i2);
        onSurfaceChanged(i, i2);
    }

    @Override // android.opengl.GLSurfaceView.Renderer
    public void onDrawFrame(GL10 gl10) {
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        GLES20.glClear(16640);
        this.mDirector.drawScene();
    }

    protected final KGLDirector2D getDirector() {
        return this.mDirector;
    }
}
