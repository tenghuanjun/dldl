package com.shuyu.gsyvideoplayer.render.effect;

import android.opengl.GLSurfaceView;
import com.shuyu.gsyvideoplayer.render.view.GSYVideoGLView;

/* JADX INFO: loaded from: classes3.dex */
public class VignetteEffect implements GSYVideoGLView.ShaderInterface {
    private float mScale;
    private int mWidth = 0;
    private int mHeight = 0;
    private final float mShade = 0.85f;

    public VignetteEffect(float f) {
        this.mScale = 0.0f;
        f = f < 0.0f ? 0.0f : f;
        this.mScale = f > 1.0f ? 1.0f : f;
    }

    private void initValues(GLSurfaceView gLSurfaceView) {
        this.mWidth = gLSurfaceView.getWidth();
        this.mHeight = gLSurfaceView.getHeight();
    }

    @Override // com.shuyu.gsyvideoplayer.render.view.GSYVideoGLView.ShaderInterface
    public String getShader(GLSurfaceView gLSurfaceView) {
        initValues(gLSurfaceView);
        float[] fArr = new float[2];
        int i = this.mWidth;
        int i2 = this.mHeight;
        if (i > i2) {
            fArr[0] = 1.0f;
            fArr[1] = i2 / i;
        } else {
            fArr[0] = i / i2;
            fArr[1] = 1.0f;
        }
        float f = fArr[0];
        float f2 = fArr[1];
        float fSqrt = ((float) Math.sqrt((f * f) + (f2 * f2))) * 0.5f;
        String[] strArr = {"scale[0] = " + fArr[0] + ";\n", "scale[1] = " + fArr[1] + ";\n"};
        return "#extension GL_OES_EGL_image_external : require\nprecision mediump float;\nuniform samplerExternalOES sTexture;\n float range;\n float inv_max_dist;\n float shade;\n vec2 scale;\nvarying vec2 vTextureCoord;\nvoid main() {\n" + strArr[0] + strArr[1] + ("inv_max_dist = " + (1.0f / fSqrt) + ";\n") + "shade = 0.85;\n" + ("range = " + (1.3f - (((float) Math.sqrt(this.mScale)) * 0.7f)) + ";\n") + "  const float slope = 20.0;\n  vec2 coord = vTextureCoord - vec2(0.5, 0.5);\n  float dist = length(coord * scale);\n  float lumen = shade / (1.0 + exp((dist * inv_max_dist - range) * slope)) + (1.0 - shade);\n  vec4 color = texture2D(sTexture, vTextureCoord);\n  gl_FragColor = vec4(color.rgb * lumen, color.a);\n}\n";
    }
}
