package com.shuyu.gsyvideoplayer.render.effect;

import android.graphics.Color;
import android.opengl.GLSurfaceView;
import com.shuyu.gsyvideoplayer.render.view.GSYVideoGLView;

/* JADX INFO: loaded from: classes3.dex */
public class TintEffect implements GSYVideoGLView.ShaderInterface {
    private int mTint;

    public TintEffect(int i) {
        this.mTint = i;
    }

    @Override // com.shuyu.gsyvideoplayer.render.view.GSYVideoGLView.ShaderInterface
    public String getShader(GLSurfaceView gLSurfaceView) {
        float[] fArr = {0.21f, 0.71f, 0.07f};
        String[] strArr = {"color_ratio[0] = " + fArr[0] + ";\n", "color_ratio[1] = " + fArr[1] + ";\n", "color_ratio[2] = " + fArr[2] + ";\n"};
        float[] fArr2 = {Color.red(this.mTint) / 255.0f, Color.green(this.mTint) / 255.0f, Color.blue(this.mTint) / 255.0f};
        String[] strArr2 = {"tint[0] = " + fArr2[0] + ";\n", "tint[1] = " + fArr2[1] + ";\n", "tint[2] = " + fArr2[2] + ";\n"};
        return "#extension GL_OES_EGL_image_external : require\nprecision mediump float;\nuniform samplerExternalOES sTexture;\n vec3 tint;\n vec3 color_ratio;\nvarying vec2 vTextureCoord;\nvoid main() {\n" + strArr[0] + strArr[1] + strArr[2] + strArr2[0] + strArr2[1] + strArr2[2] + "  vec4 color = texture2D(sTexture, vTextureCoord);\n  float avg_color = dot(color_ratio, color.rgb);\n  vec3 new_color = min(0.8 * avg_color + 0.2 * tint, 1.0);\n  gl_FragColor = vec4(new_color.rgb, color.a);\n}\n";
    }
}
