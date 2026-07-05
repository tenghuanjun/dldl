package com.huya.mtp.utils.gl.render.director;

import com.huya.mtp.utils.gl.camera.KGLCamera2D;
import com.huya.mtp.utils.gl.core.KGLCoordinate;
import com.huya.mtp.utils.gl.render.scene.KGLScene2D;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class KGLDirector2D extends KGLAbsDirector<KGLScene2D> {
    private static final float BOTTOM = -1.0f;
    private static final float EYE_Z = -1.0f;
    private static final float FAR = 2.0f;
    private static final float LEFT = -1.0f;
    private static final float NEAR = 1.0f;
    private static final float NEAR_Z = 0.0f;
    private static final float RIGHT = 1.0f;
    private static final float TOP = 1.0f;

    public KGLDirector2D(int i) {
        super(KGLCamera2D.create(), KGLCoordinate.create(1.0f, -1.0f, 1.0f, 2.0f, -1.0f, 0.0f, -1.0f, 1.0f, 2.0f, 1.0f, 1.0f, 1.0f));
    }
}
