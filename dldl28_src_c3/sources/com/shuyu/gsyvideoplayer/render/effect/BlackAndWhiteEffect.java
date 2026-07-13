package com.shuyu.gsyvideoplayer.render.effect;

import android.opengl.GLSurfaceView;
import com.shuyu.gsyvideoplayer.render.view.GSYVideoGLView;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public class BlackAndWhiteEffect implements GSYVideoGLView.ShaderInterface {
    @Override // com.shuyu.gsyvideoplayer.render.view.GSYVideoGLView.ShaderInterface
    public String getShader(GLSurfaceView gLSurfaceView) {
        return "#extension GL_OES_EGL_image_external : require\nprecision mediump float;\nvarying vec2 vTextureCoord;\nuniform samplerExternalOES sTexture;\nvoid main() {\n  vec4 color = texture2D(sTexture, vTextureCoord);\n  float colorR = (color.r + color.g + color.b) / 3.0;\n  float colorG = (color.r + color.g + color.b) / 3.0;\n  float colorB = (color.r + color.g + color.b) / 3.0;\n  gl_FragColor = vec4(colorR, colorG, colorB, color.a);\n}\n";
    }
}
