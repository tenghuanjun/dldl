package com.shuyu.gsyvideoplayer.render.effect;

import android.opengl.GLSurfaceView;
import com.shuyu.gsyvideoplayer.render.view.GSYVideoGLView;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public class SepiaEffect implements GSYVideoGLView.ShaderInterface {
    @Override // com.shuyu.gsyvideoplayer.render.view.GSYVideoGLView.ShaderInterface
    public String getShader(GLSurfaceView gLSurfaceView) {
        float[] fArr = {0.3930664f, 0.3491211f, 0.27197266f, 0.76904297f, 0.68603516f, 0.53564453f, 0.18896484f, 0.16796875f, 0.13085938f};
        String[] strArr = {"  matrix[0][0]=" + fArr[0] + ";\n", "  matrix[0][1]=" + fArr[1] + ";\n", "  matrix[0][2]=" + fArr[2] + ";\n", "  matrix[1][0]=" + fArr[3] + ";\n", "  matrix[1][1]=" + fArr[4] + ";\n", "  matrix[1][2]=" + fArr[5] + ";\n", "  matrix[2][0]=" + fArr[6] + ";\n", "  matrix[2][1]=" + fArr[7] + ";\n", "  matrix[2][2]=" + fArr[8] + ";\n"};
        return "#extension GL_OES_EGL_image_external : require\nprecision mediump float;\nuniform samplerExternalOES sTexture;\n mat3 matrix;\nvarying vec2 vTextureCoord;\nvoid main() {\n" + strArr[0] + strArr[1] + strArr[2] + strArr[3] + strArr[4] + strArr[5] + strArr[6] + strArr[7] + strArr[8] + "  vec4 color = texture2D(sTexture, vTextureCoord);\n  vec3 new_color = min(matrix * color.rgb, 1.0);\n  gl_FragColor = vec4(new_color.rgb, color.a);\n}\n";
    }
}
