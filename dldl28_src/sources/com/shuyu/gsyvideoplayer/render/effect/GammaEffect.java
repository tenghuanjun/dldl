package com.shuyu.gsyvideoplayer.render.effect;

import android.opengl.GLSurfaceView;
import com.shuyu.gsyvideoplayer.render.view.GSYVideoGLView;

/* JADX INFO: loaded from: classes3.dex */
public class GammaEffect implements GSYVideoGLView.ShaderInterface {
    private float gammaValue;

    public GammaEffect(float f) {
        f = f < 0.0f ? 0.0f : f;
        this.gammaValue = f > 2.0f ? 2.0f : f;
    }

    @Override // com.shuyu.gsyvideoplayer.render.view.GSYVideoGLView.ShaderInterface
    public String getShader(GLSurfaceView gLSurfaceView) {
        return "#extension GL_OES_EGL_image_external : require\nprecision mediump float;\nvarying vec2 vTextureCoord;\nuniform samplerExternalOES sTexture;\nfloat gamma=" + this.gammaValue + ";\nvoid main() {\nvec4 textureColor = texture2D(sTexture, vTextureCoord);\ngl_FragColor = vec4(pow(textureColor.rgb, vec3(gamma)), textureColor.w);\n}\n";
    }
}
