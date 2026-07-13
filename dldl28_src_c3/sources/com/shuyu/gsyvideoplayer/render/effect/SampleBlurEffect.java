package com.shuyu.gsyvideoplayer.render.effect;

import android.opengl.GLSurfaceView;
import com.shuyu.gsyvideoplayer.render.view.GSYVideoGLView;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public class SampleBlurEffect implements GSYVideoGLView.ShaderInterface {
    float blur;

    public SampleBlurEffect() {
        this.blur = 1.0f;
    }

    public SampleBlurEffect(float f) {
        this.blur = f;
    }

    @Override // com.shuyu.gsyvideoplayer.render.view.GSYVideoGLView.ShaderInterface
    public String getShader(GLSurfaceView gLSurfaceView) {
        return "#extension GL_OES_EGL_image_external : require\nprecision mediump float;\nuniform samplerExternalOES sTexture;\nvarying vec2 vTextureCoord;\nconst float blurSize = " + this.blur + "/800.0;\nconst float weightSum = 70.0 + 2.0 * (1.0 + 8.0 + 28.0 + 56.0);\n\nvoid main(void)\n{\n   vec4 sum = vec4(0.0);\n\n   sum += texture2D(sTexture, vec2(vTextureCoord.x - 4.0*blurSize, vTextureCoord.y)) * 1.0 / weightSum;\n   sum += texture2D(sTexture, vec2(vTextureCoord.x - 3.0*blurSize, vTextureCoord.y)) * 8.0 / weightSum;\n   sum += texture2D(sTexture, vec2(vTextureCoord.x - 2.0*blurSize, vTextureCoord.y)) * 28.0 / weightSum;\n   sum += texture2D(sTexture, vec2(vTextureCoord.x - blurSize, vTextureCoord.y)) * 56.0 / weightSum;\n   sum += texture2D(sTexture, vec2(vTextureCoord.x, vTextureCoord.y)) * 70.0 / weightSum;\n   sum += texture2D(sTexture, vec2(vTextureCoord.x + blurSize, vTextureCoord.y)) * 56.0 / weightSum;\n   sum += texture2D(sTexture, vec2(vTextureCoord.x + 2.0*blurSize, vTextureCoord.y)) * 28.0 / weightSum;\n   sum += texture2D(sTexture, vec2(vTextureCoord.x + 3.0*blurSize, vTextureCoord.y)) * 8.0 / weightSum;\n   sum += texture2D(sTexture, vec2(vTextureCoord.x + 4.0*blurSize, vTextureCoord.y)) * 1.0 / weightSum;\n\n   gl_FragColor = sum;\n}";
    }
}
