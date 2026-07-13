package com.shuyu.gsyvideoplayer.render.effect;

import android.opengl.GLSurfaceView;
import com.shuyu.gsyvideoplayer.render.view.GSYVideoGLView;
import java.util.Date;
import java.util.Random;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public class GrainEffect implements GSYVideoGLView.ShaderInterface {
    private int mHeight;
    private Random mRandom;
    private int mWidth;
    private float strength;

    public GrainEffect(float f) {
        f = f < 0.0f ? 0.0f : f;
        this.strength = f > 1.0f ? 1.0f : f;
    }

    private void initValues(GLSurfaceView gLSurfaceView) {
        this.mWidth = gLSurfaceView.getWidth();
        this.mHeight = gLSurfaceView.getHeight();
        this.mRandom = new Random(new Date().getTime());
    }

    @Override // com.shuyu.gsyvideoplayer.render.view.GSYVideoGLView.ShaderInterface
    public String getShader(GLSurfaceView gLSurfaceView) {
        initValues(gLSurfaceView);
        float[] fArr = {this.mRandom.nextFloat(), this.mRandom.nextFloat()};
        String str = "scale = " + this.strength + ";\n";
        String[] strArr = {"seed[0] = " + fArr[0] + ";\n", "seed[1] = " + fArr[1] + ";\n"};
        return "#extension GL_OES_EGL_image_external : require\nprecision mediump float;\n vec2 seed;\nvarying vec2 vTextureCoord;\nuniform samplerExternalOES tex_sampler_0;\nuniform samplerExternalOES tex_sampler_1;\nfloat scale;\n float stepX;\n float stepY;\nfloat rand(vec2 loc) {\n  float theta1 = dot(loc, vec2(0.9898, 0.233));\n  float theta2 = dot(loc, vec2(12.0, 78.0));\n  float value = cos(theta1) * sin(theta2) + sin(theta1) * cos(theta2);\n  float temp = mod(197.0 * value, 1.0) + value;\n  float part1 = mod(220.0 * temp, 1.0) + temp;\n  float part2 = value * 0.5453;\n  float part3 = cos(theta1 + theta2) * 0.43758;\n  float sum = (part1 + part2 + part3);\n  return fract(sum)*scale;\n}\nvoid main() {\n" + strArr[0] + strArr[1] + str + ("stepX = " + (0.5f / this.mWidth) + ";\n") + ("stepY = " + (0.5f / this.mHeight) + ";\n") + "  float noise = texture2D(tex_sampler_1, vTextureCoord + vec2(-stepX, -stepY)).r * 0.224;\n  noise += texture2D(tex_sampler_1, vTextureCoord + vec2(-stepX, stepY)).r * 0.224;\n  noise += texture2D(tex_sampler_1, vTextureCoord + vec2(stepX, -stepY)).r * 0.224;\n  noise += texture2D(tex_sampler_1, vTextureCoord + vec2(stepX, stepY)).r * 0.224;\n  noise += 0.4448;\n  noise *= scale;\n  vec4 color = texture2D(tex_sampler_0, vTextureCoord);\n  float energy = 0.33333 * color.r + 0.33333 * color.g + 0.33333 * color.b;\n  float mask = (1.0 - sqrt(energy));\n  float weight = 1.0 - 1.333 * mask * noise;\n  gl_FragColor = vec4(color.rgb * weight, color.a);\n  gl_FragColor = gl_FragColor+vec4(rand(vTextureCoord + seed), rand(vTextureCoord + seed),rand(vTextureCoord + seed),1);\n}\n";
    }
}
