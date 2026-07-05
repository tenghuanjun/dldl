package com.huya.mtp.utils.gl.texture;

import com.huya.mtp.utils.gl.buffer.KGLVertexBufferObject;
import com.huya.mtp.utils.gl.core.KGLAbsGLObject;
import com.huya.mtp.utils.gl.utils.KGLUtils;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public final class KGLDrawOrder2D extends KGLAbsGLObject {
    private static final short[] DrawOrder2D = {0, 1, 2, 2, 3, 1};
    private KGLVertexBufferObject mVBO;

    public static KGLDrawOrder2D createDrawOrder2D() {
        return createDrawOrder2D(DrawOrder2D);
    }

    public static KGLDrawOrder2D createDrawOrder2D(short[] sArr) {
        KGLDrawOrder2D kGLDrawOrder2D = new KGLDrawOrder2D(sArr);
        if (kGLDrawOrder2D.isValid()) {
            return kGLDrawOrder2D;
        }
        return null;
    }

    private KGLDrawOrder2D(short[] sArr) {
        this.mVBO = KGLVertexBufferObject.create(KGLVertexBufferObject.Target.ELEMENT_ARRAY_BUFFER, KGLVertexBufferObject.Usage.STATIC_DRAW, sArr.length * 2, KGLUtils.arrayToBuffer(sArr));
    }

    public void bind() {
        this.mVBO.bind();
    }

    public void unBind() {
        this.mVBO.unBind();
    }

    @Override // com.huya.mtp.utils.gl.core.KGLAbsGLObject
    public boolean isValid() {
        KGLVertexBufferObject kGLVertexBufferObject = this.mVBO;
        return kGLVertexBufferObject != null && kGLVertexBufferObject.isValid();
    }

    @Override // com.huya.mtp.utils.gl.core.KGLAbsGLObject
    protected void deleteGLObject() {
        KGLVertexBufferObject kGLVertexBufferObject = this.mVBO;
        if (kGLVertexBufferObject != null) {
            this.mVBO = (KGLVertexBufferObject) delete(kGLVertexBufferObject);
        }
    }
}
