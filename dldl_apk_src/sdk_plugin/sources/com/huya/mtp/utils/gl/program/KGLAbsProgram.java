package com.huya.mtp.utils.gl.program;

import android.opengl.GLES20;
import com.huya.mtp.utils.gl.core.KGLAbsGLObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public abstract class KGLAbsProgram extends KGLAbsGLObject {
    protected int mProgramID = -1;

    public abstract int getAlphaHandle();

    public abstract int getPositionHandle();

    public abstract int getTextureHandle();

    public abstract int getTextureSampler();

    public abstract void setMVPMatrix(float[] fArr, float[] fArr2, float[] fArr3, float[] fArr4);

    public void bind() {
        GLES20.glUseProgram(this.mProgramID);
    }

    public void unBind() {
        GLES20.glUseProgram(0);
    }
}
