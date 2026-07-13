package com.shuyu.gsyvideoplayer.render.effect;

import android.graphics.Color;
import android.opengl.GLSurfaceView;
import com.shuyu.gsyvideoplayer.render.view.GSYVideoGLView;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public class DuotoneEffect implements GSYVideoGLView.ShaderInterface {
    private int mFirstColor;
    private int mSecondColor;

    public DuotoneEffect(int i, int i2) {
        this.mFirstColor = i;
        this.mSecondColor = i2;
    }

    @Override // com.shuyu.gsyvideoplayer.render.view.GSYVideoGLView.ShaderInterface
    public String getShader(GLSurfaceView gLSurfaceView) {
        float[] fArr = {Color.red(this.mFirstColor) / 255.0f, Color.green(this.mFirstColor) / 255.0f, Color.blue(this.mFirstColor) / 255.0f};
        float[] fArr2 = {Color.red(this.mSecondColor) / 255.0f, Color.green(this.mSecondColor) / 255.0f, Color.blue(this.mSecondColor) / 255.0f};
        String[] strArr = {"first[0] = " + fArr[0] + ";\n", "first[1] = " + fArr[1] + ";\n", "first[2] = " + fArr[2] + ";\n"};
        String[] strArr2 = {"second[0] = " + fArr2[0] + ";\n", "second[1] = " + fArr2[1] + ";\n", "second[2] = " + fArr2[2] + ";\n"};
        return "#extension GL_OES_EGL_image_external : require\nprecision mediump float;\nuniform samplerExternalOES sTexture;\n vec3 first;\n vec3 second;\nvarying vec2 vTextureCoord;\nvoid main() {\n" + strArr[0] + strArr[1] + strArr[2] + strArr2[0] + strArr2[1] + strArr2[2] + "  vec4 color = texture2D(sTexture, vTextureCoord);\n  float energy = (color.r + color.g + color.b) * 0.3333;\n  vec3 new_color = (1.0 - energy) * first + energy * second;\n  gl_FragColor = vec4(new_color.rgb, color.a);\n}\n";
    }
}
