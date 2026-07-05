package com.duowan.kiwi.barrage.render.shader;

import android.opengl.GLES20;
import com.duowan.ark.util.glutils.utils.CatchError;
import com.duowan.ark.util.glutils.utils.ShaderUtils;
import com.duowan.kiwi.barrage.config.BarrageLog;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class BarrageShader {
    protected int mAlphaHanler;
    protected int mFragmentShaderHandle;
    protected int mModelMatrixHandle;
    protected int mPositionHandle;
    protected int mProgram;
    protected int mProjectionViewMatrixHandle;
    protected int mTextureHandle;
    protected int mTextureSampler;
    protected int mVertexShaderHandle;

    public BarrageShader() {
        init();
    }

    public void use() {
        GLES20.glUseProgram(this.mProgram);
        CatchError.catchError("shader use1");
    }

    public void unUse() {
        GLES20.glUseProgram(0);
    }

    public void destroy() {
        ShaderUtils.deleteShaderProgram(this.mProgram, this.mVertexShaderHandle, this.mFragmentShaderHandle);
        this.mProgram = -1;
        this.mPositionHandle = -1;
        this.mTextureHandle = -1;
        this.mProjectionViewMatrixHandle = -1;
        this.mModelMatrixHandle = -1;
        this.mVertexShaderHandle = -1;
        this.mFragmentShaderHandle = -1;
        this.mTextureSampler = -1;
        this.mAlphaHanler = -1;
    }

    public int getPosHandle() {
        return this.mPositionHandle;
    }

    public int getTexHandle() {
        return this.mTextureHandle;
    }

    public int getProjectionViewMatrixHandle() {
        return this.mProjectionViewMatrixHandle;
    }

    public int getModelMatrixHandle() {
        return this.mModelMatrixHandle;
    }

    public int getTextureSampler() {
        return this.mTextureSampler;
    }

    public int getAlphaHanler() {
        return this.mAlphaHanler;
    }

    private void init() {
        int i;
        this.mVertexShaderHandle = ShaderUtils.compileShader(35633, "attribute vec4 position_vertex;attribute vec2 texture_vertex;varying vec2 texture_coord;uniform mat4 projection_view_matrix;uniform mat4 model_matrix;void main() {gl_Position = projection_view_matrix *  model_matrix * position_vertex;texture_coord = texture_vertex;}");
        CatchError.catchError("barrage shader init 1");
        this.mFragmentShaderHandle = ShaderUtils.compileShader(35632, "precision highp float;varying vec2 texture_coord;uniform float alpha;uniform sampler2D texture_sampler;void main() {gl_FragColor = texture2D(texture_sampler, texture_coord);if(gl_FragColor.a < 0.001)discard;gl_FragColor.a *= alpha;}");
        CatchError.catchError("barrage shader init 2");
        this.mProgram = ShaderUtils.linkShader(this.mVertexShaderHandle, this.mFragmentShaderHandle);
        CatchError.catchError("barrage shader init 3");
        ShaderUtils.validateShaderProgram(this.mProgram);
        CatchError.catchError("barrage shader init 4");
        if (this.mVertexShaderHandle == 0 || this.mFragmentShaderHandle == 0 || (i = this.mProgram) == 0) {
            BarrageLog.error("CatchError", "barrage shader init error vsh %d fsh %d program %d", Integer.valueOf(this.mVertexShaderHandle), Integer.valueOf(this.mFragmentShaderHandle), Integer.valueOf(this.mProgram));
            this.mProgram = -1;
            this.mFragmentShaderHandle = -1;
            this.mVertexShaderHandle = -1;
            return;
        }
        this.mPositionHandle = GLES20.glGetAttribLocation(i, "position_vertex");
        this.mTextureHandle = GLES20.glGetAttribLocation(this.mProgram, "texture_vertex");
        this.mProjectionViewMatrixHandle = GLES20.glGetUniformLocation(this.mProgram, "projection_view_matrix");
        this.mModelMatrixHandle = GLES20.glGetUniformLocation(this.mProgram, "model_matrix");
        this.mTextureSampler = GLES20.glGetUniformLocation(this.mProgram, "texture_sampler");
        this.mAlphaHanler = GLES20.glGetUniformLocation(this.mProgram, "alpha");
        CatchError.catchError("barrage shader use2");
    }
}
