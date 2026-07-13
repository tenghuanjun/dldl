package com.shuyu.gsyvideoplayer.render.effect;

import android.opengl.GLSurfaceView;
import com.shuyu.gsyvideoplayer.render.view.GSYVideoGLView;

/* JADX INFO: loaded from: classes3.dex */
public class SaturationEffect implements GSYVideoGLView.ShaderInterface {
    private float scale;

    public SaturationEffect(float f) {
        this.scale = f;
    }

    @Override // com.shuyu.gsyvideoplayer.render.view.GSYVideoGLView.ShaderInterface
    public String getShader(GLSurfaceView gLSurfaceView) {
        float[] fArr = {0.25f, 0.625f, 0.125f};
        String str = "";
        String[] strArr = {"", "", ""};
        float f = this.scale;
        if (f > 0.0f) {
            float[] fArr2 = {(0.9f * f) + 1.0f, (2.1f * f) + 1.0f, (f * 2.7f) + 1.0f};
            strArr[0] = "exponents[0] = " + fArr2[0] + ";\n";
            strArr[1] = "exponents[1] = " + fArr2[1] + ";\n";
            strArr[2] = "exponents[2] = " + fArr2[2] + ";\n";
        } else {
            str = "scale = " + (this.scale + 1.0f) + ";\n";
        }
        String[] strArr2 = {"weights[0] = " + fArr[0] + ";\n", "weights[1] = " + fArr[1] + ";\n", "weights[2] = " + fArr[2] + ";\n"};
        return "#extension GL_OES_EGL_image_external : require\nprecision mediump float;\nuniform samplerExternalOES sTexture;\n float scale;\n float shift;\n vec3 weights;\n vec3 exponents;\nvarying vec2 vTextureCoord;\nvoid main() {\n" + strArr2[0] + strArr2[1] + strArr2[2] + "shift = 0.003921569;\n" + str + "  vec4 oldcolor = texture2D(sTexture, vTextureCoord);\n  float kv = dot(oldcolor.rgb, weights) + shift;\n  vec3 new_color = scale * oldcolor.rgb + (1.0 - scale) * kv;\n  gl_FragColor= vec4(new_color, oldcolor.a);\n" + strArr2[0] + strArr2[1] + strArr2[2] + strArr[0] + strArr[1] + strArr[2] + "  vec4 color = texture2D(sTexture, vTextureCoord);\n  float de = dot(color.rgb, weights);\n  float inv_de = 1.0 / de;\n  vec3 verynew_color = de * pow(color.rgb * inv_de, exponents);\n  float max_color = max(max(max(verynew_color.r, verynew_color.g), verynew_color.b), 1.0);\n  gl_FragColor = gl_FragColor+vec4(verynew_color / max_color, color.a);\n}\n";
    }
}
