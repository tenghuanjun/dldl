package com.huya.mtp.utils.gl.texture;

import com.huya.mtp.utils.gl.buffer.KGLVertexBufferObject;
import com.huya.mtp.utils.gl.core.KGLAbsGLObject;
import com.huya.mtp.utils.gl.utils.KGLUtils;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public final class KGLTextureRect2D extends KGLAbsGLObject {
    private static final int Stride2D = 16;
    private static final int TexOffset2D = 8;
    private static final float[] Vertex2D = {-1.0f, -1.0f, 0.0f, 0.0f, 1.0f, -1.0f, 1.0f, 0.0f, -1.0f, 1.0f, 0.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f};
    private int mStride;
    private int mTexOffset;
    private KGLVertexBufferObject mVBO;

    public static KGLTextureRect2D createRect2D() {
        return createRect2D(Vertex2D, 16, 8);
    }

    public static KGLTextureRect2D createRect2D(float[] fArr, int i, int i2) {
        KGLTextureRect2D kGLTextureRect2D = new KGLTextureRect2D(fArr, i, i2);
        if (kGLTextureRect2D.isValid()) {
            return kGLTextureRect2D;
        }
        return null;
    }

    public void bind() {
        this.mVBO.bind();
    }

    public void unBind() {
        this.mVBO.unBind();
    }

    public int getStride() {
        return this.mStride;
    }

    public int getTexOffset() {
        return this.mTexOffset;
    }

    public void sub(float[] fArr) {
        this.mVBO.subBuffer(fArr.length * 4, KGLUtils.arrayToBuffer(fArr));
    }

    private KGLTextureRect2D(float[] fArr, int i, int i2) {
        this.mStride = i;
        this.mTexOffset = i2;
        this.mVBO = KGLVertexBufferObject.create(KGLVertexBufferObject.Target.ARRAY_BUFFER, KGLVertexBufferObject.Usage.STATIC_DRAW, fArr.length * 4, KGLUtils.arrayToBuffer(fArr));
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
