package com.shuyu.gsyvideoplayer.render.effect;

import android.opengl.GLSurfaceView;
import com.shuyu.gsyvideoplayer.render.view.GSYVideoGLView;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public class OverlayEffect implements GSYVideoGLView.ShaderInterface {
    float fstep;

    public OverlayEffect() {
        this.fstep = 0.0015f;
    }

    public OverlayEffect(float f) {
        this.fstep = f;
    }

    @Override // com.shuyu.gsyvideoplayer.render.view.GSYVideoGLView.ShaderInterface
    public String getShader(GLSurfaceView gLSurfaceView) {
        return "#extension GL_OES_EGL_image_external : require\nprecision mediump float;\n             uniform samplerExternalOES sTexture; \n             varying vec2 vTextureCoord; \n  \n             void main() \n             { \n              vec4 sample0,sample1,sample2,sample3; \n              float fstep=" + this.fstep + "; \n              sample0=texture2D(sTexture,vec2(vTextureCoord.x-fstep,vTextureCoord.y-fstep)); \n              sample1=texture2D(sTexture,vec2(vTextureCoord.x+fstep,vTextureCoord.y-fstep)); \n              sample2=texture2D(sTexture,vec2(vTextureCoord.x+fstep,vTextureCoord.y+fstep)); \n              sample3=texture2D(sTexture,vec2(vTextureCoord.x-fstep,vTextureCoord.y+fstep)); \n              vec4 color=(sample0+sample1+sample2+sample3) / 4.0; \n              gl_FragColor=color; \n             } ";
    }
}
