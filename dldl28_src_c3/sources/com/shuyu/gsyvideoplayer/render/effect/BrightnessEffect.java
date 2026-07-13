package com.shuyu.gsyvideoplayer.render.effect;

import android.opengl.GLSurfaceView;
import com.shuyu.gsyvideoplayer.render.view.GSYVideoGLView;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public class BrightnessEffect implements GSYVideoGLView.ShaderInterface {
    private float brightnessValue;

    public BrightnessEffect(float f) {
        f = f < 0.1f ? 0.1f : f;
        this.brightnessValue = f > 2.0f ? 2.0f : f;
    }

    @Override // com.shuyu.gsyvideoplayer.render.view.GSYVideoGLView.ShaderInterface
    public String getShader(GLSurfaceView gLSurfaceView) {
        return "#extension GL_OES_EGL_image_external : require\nprecision mediump float;\nuniform samplerExternalOES sTexture;\nfloat brightness ;\nvarying vec2 vTextureCoord;\nvoid main() {\n  brightness =" + this.brightnessValue + ";\n  vec4 color = texture2D(sTexture, vTextureCoord);\n  gl_FragColor = brightness * color;\n}\n";
    }
}
