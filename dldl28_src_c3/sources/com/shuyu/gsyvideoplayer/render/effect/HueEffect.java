package com.shuyu.gsyvideoplayer.render.effect;

import android.opengl.GLSurfaceView;
import com.shuyu.gsyvideoplayer.render.view.GSYVideoGLView;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public class HueEffect implements GSYVideoGLView.ShaderInterface {
    float hueValue;

    public HueEffect(float f) {
        this.hueValue = (((f - 45.0f) / 45.0f) + 0.5f) * (-1.0f);
    }

    @Override // com.shuyu.gsyvideoplayer.render.view.GSYVideoGLView.ShaderInterface
    public String getShader(GLSurfaceView gLSurfaceView) {
        return "#extension GL_OES_EGL_image_external : require\nprecision mediump float;\nvarying vec2 vTextureCoord;\nuniform samplerExternalOES sTexture;\nfloat hue=" + this.hueValue + ";\nvoid main() {\nvec4 kRGBToYPrime = vec4 (0.299, 0.587, 0.114, 0.0);\nvec4 kRGBToI = vec4 (0.595716, -0.274453, -0.321263, 0.0);\nvec4 kRGBToQ = vec4 (0.211456, -0.522591, 0.31135, 0.0);\nvec4 kYIQToR = vec4 (1.0, 0.9563, 0.6210, 0.0);\nvec4 kYIQToG = vec4 (1.0, -0.2721, -0.6474, 0.0);\nvec4 kYIQToB = vec4 (1.0, -1.1070, 1.7046, 0.0);\nvec4 color = texture2D(sTexture, vTextureCoord);\nfloat YPrime = dot(color, kRGBToYPrime);\nfloat I = dot(color, kRGBToI);\nfloat Q = dot(color, kRGBToQ);\nfloat chroma = sqrt (I * I + Q * Q);\nQ = chroma * sin (hue);\nI = chroma * cos (hue);\nvec4 yIQ = vec4 (YPrime, I, Q, 0.0);\ncolor.r = dot (yIQ, kYIQToR);\ncolor.g = dot (yIQ, kYIQToG);\ncolor.b = dot (yIQ, kYIQToB);\ngl_FragColor = color;\n}\n";
    }
}
