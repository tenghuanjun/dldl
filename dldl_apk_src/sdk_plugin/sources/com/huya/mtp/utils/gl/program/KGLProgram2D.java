package com.huya.mtp.utils.gl.program;

import android.opengl.GLES20;
import com.huya.mtp.utils.gl.utils.KGLShaderUtils;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public final class KGLProgram2D extends KGLAbsProgram {
    private static final String ALPHA = "alpha";
    private static final String CURRENT_MATRIX = "current_matrix";
    private static final String FSH_CODE = "precision highp float;varying vec2 texture_coord;uniform float alpha;uniform sampler2D texture_sampler;void main() {gl_FragColor = texture2D(texture_sampler, texture_coord);gl_FragColor.a *= alpha;}";
    private static final String MODEL_MATRIX = "model_matrix";
    private static final String POSITION = "position_vertex";
    private static final String SAMPLER = "texture_sampler";
    private static final String TEXTURE = "texture_vertex";
    private static final String VSH_CODE = "attribute vec4 position_vertex;attribute vec2 texture_vertex;varying vec2 texture_coord;uniform mat4 current_matrix;uniform mat4 model_matrix;void main() {gl_Position = current_matrix *  model_matrix * position_vertex;texture_coord = texture_vertex;}";
    private int mAlphaHandle;
    private int mCurrentMatrixHandle;
    private int mFragmentHandle;
    private int mModelMatrixHandle;
    private int mPositionHandle;
    private int mTextureHandle;
    private int mTextureSampler;
    private int mVertexHandle;

    public static KGLProgram2D create() {
        KGLProgram2D kGLProgram2D = new KGLProgram2D();
        if (kGLProgram2D.isValid()) {
            return kGLProgram2D;
        }
        return null;
    }

    @Override // com.huya.mtp.utils.gl.program.KGLAbsProgram
    public int getPositionHandle() {
        return this.mPositionHandle;
    }

    @Override // com.huya.mtp.utils.gl.program.KGLAbsProgram
    public int getTextureHandle() {
        return this.mTextureHandle;
    }

    @Override // com.huya.mtp.utils.gl.program.KGLAbsProgram
    public int getTextureSampler() {
        return this.mTextureSampler;
    }

    @Override // com.huya.mtp.utils.gl.program.KGLAbsProgram
    public int getAlphaHandle() {
        return this.mAlphaHandle;
    }

    @Override // com.huya.mtp.utils.gl.program.KGLAbsProgram
    public void bind() {
        super.bind();
        GLES20.glEnableVertexAttribArray(this.mPositionHandle);
        GLES20.glEnableVertexAttribArray(this.mTextureHandle);
    }

    @Override // com.huya.mtp.utils.gl.program.KGLAbsProgram
    public void unBind() {
        GLES20.glDisableVertexAttribArray(this.mPositionHandle);
        GLES20.glDisableVertexAttribArray(this.mTextureHandle);
        super.unBind();
    }

    @Override // com.huya.mtp.utils.gl.program.KGLAbsProgram
    public void setMVPMatrix(float[] fArr, float[] fArr2, float[] fArr3, float[] fArr4) {
        GLES20.glUniformMatrix4fv(this.mCurrentMatrixHandle, 1, false, fArr2, 0);
        GLES20.glUniformMatrix4fv(this.mModelMatrixHandle, 1, false, fArr, 0);
    }

    @Override // com.huya.mtp.utils.gl.core.KGLAbsGLObject
    public boolean isValid() {
        return (-1 == this.mProgramID || -1 == this.mVertexHandle || -1 == this.mFragmentHandle) ? false : true;
    }

    private KGLProgram2D() {
        this.mVertexHandle = -1;
        this.mFragmentHandle = -1;
        this.mPositionHandle = -1;
        this.mTextureHandle = -1;
        this.mCurrentMatrixHandle = -1;
        this.mModelMatrixHandle = -1;
        this.mTextureSampler = -1;
        this.mAlphaHandle = -1;
        this.mFragmentHandle = -1;
        this.mVertexHandle = -1;
        this.mProgramID = -1;
        int iCompileShader = KGLShaderUtils.compileShader(KGLShaderUtils.ShaderType.Vertex, VSH_CODE);
        this.mVertexHandle = iCompileShader;
        if (iCompileShader == 0) {
            this.mFragmentHandle = -1;
            this.mVertexHandle = -1;
            this.mProgramID = -1;
            return;
        }
        int iCompileShader2 = KGLShaderUtils.compileShader(KGLShaderUtils.ShaderType.Fragment, FSH_CODE);
        this.mFragmentHandle = iCompileShader2;
        if (iCompileShader2 == 0) {
            this.mFragmentHandle = -1;
            this.mVertexHandle = -1;
            this.mProgramID = -1;
            return;
        }
        this.mProgramID = KGLShaderUtils.linkShader(this.mVertexHandle, iCompileShader2);
        if (this.mProgramID == 0) {
            this.mFragmentHandle = -1;
            this.mVertexHandle = -1;
            this.mProgramID = -1;
        } else {
            if (KGLShaderUtils.validateShaderProgram(this.mProgramID) == 0) {
                KGLShaderUtils.deleteShaderProgram(this.mProgramID, this.mVertexHandle, this.mFragmentHandle);
                this.mFragmentHandle = -1;
                this.mVertexHandle = -1;
                this.mProgramID = -1;
                return;
            }
            this.mPositionHandle = GLES20.glGetAttribLocation(this.mProgramID, POSITION);
            this.mTextureHandle = GLES20.glGetAttribLocation(this.mProgramID, TEXTURE);
            this.mCurrentMatrixHandle = GLES20.glGetUniformLocation(this.mProgramID, CURRENT_MATRIX);
            this.mModelMatrixHandle = GLES20.glGetUniformLocation(this.mProgramID, MODEL_MATRIX);
            this.mTextureSampler = GLES20.glGetUniformLocation(this.mProgramID, SAMPLER);
            this.mAlphaHandle = GLES20.glGetUniformLocation(this.mProgramID, "alpha");
        }
    }

    @Override // com.huya.mtp.utils.gl.core.KGLAbsGLObject
    protected void deleteGLObject() {
        if (-1 != this.mProgramID) {
            KGLShaderUtils.deleteShaderProgram(this.mProgramID, this.mVertexHandle, this.mFragmentHandle);
            this.mFragmentHandle = -1;
            this.mVertexHandle = -1;
            this.mProgramID = -1;
            this.mAlphaHandle = -1;
            this.mTextureSampler = -1;
            this.mModelMatrixHandle = -1;
            this.mCurrentMatrixHandle = -1;
            this.mTextureHandle = -1;
            this.mPositionHandle = -1;
        }
    }
}
