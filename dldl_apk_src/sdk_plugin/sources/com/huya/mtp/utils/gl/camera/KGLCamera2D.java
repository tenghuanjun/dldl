package com.huya.mtp.utils.gl.camera;

import android.opengl.Matrix;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public final class KGLCamera2D extends KGLAbsCamera {
    public static KGLCamera2D create() {
        return new KGLCamera2D();
    }

    private KGLCamera2D() {
    }

    @Override // com.huya.mtp.utils.gl.camera.KGLAbsCamera
    protected void sharp(float[] fArr, float f, float f2, float f3, float f4, float f5, float f6) {
        Matrix.orthoM(fArr, 0, f, f2, f3, f4, f5, f6);
    }
}
