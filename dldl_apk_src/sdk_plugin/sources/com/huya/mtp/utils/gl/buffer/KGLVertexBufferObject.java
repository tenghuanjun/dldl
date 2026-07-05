package com.huya.mtp.utils.gl.buffer;

import android.opengl.GLES20;
import com.huya.mtp.utils.gl.utils.KGLGetError;
import java.nio.Buffer;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public final class KGLVertexBufferObject extends KGLAbsBufferObject {
    private static final String TAG = "KGLVertexBufferObject";
    private int mBufferTarget;
    private int mVertexBufferID;

    public enum Target {
        ARRAY_BUFFER(34962),
        ELEMENT_ARRAY_BUFFER(34963);

        private final int mTarget;

        Target(int i) {
            this.mTarget = i;
        }
    }

    public enum Usage {
        STREAM_DRAW(35040),
        STATIC_DRAW(35044),
        DYNAMIC_DRAW(35048);

        private final int mUsage;

        Usage(int i) {
            this.mUsage = i;
        }
    }

    public static KGLVertexBufferObject create(Target target, Usage usage, int i, Buffer buffer) {
        KGLVertexBufferObject kGLVertexBufferObject = new KGLVertexBufferObject(target, usage, i, buffer);
        if (kGLVertexBufferObject.isValid()) {
            return kGLVertexBufferObject;
        }
        return null;
    }

    @Override // com.huya.mtp.utils.gl.buffer.KGLAbsBufferObject
    public void bind() {
        GLES20.glBindBuffer(this.mBufferTarget, this.mVertexBufferID);
    }

    @Override // com.huya.mtp.utils.gl.buffer.KGLAbsBufferObject
    public void unBind() {
        GLES20.glBindBuffer(this.mBufferTarget, 0);
    }

    public void subBuffer(int i, Buffer buffer) {
        bind();
        GLES20.glBufferSubData(this.mBufferTarget, 0, i, buffer);
        unBind();
        KGLGetError.info("KGLVertexBufferObjectsubBuffer");
    }

    @Override // com.huya.mtp.utils.gl.core.KGLAbsGLObject
    public boolean isValid() {
        return -1 != this.mVertexBufferID;
    }

    @Override // com.huya.mtp.utils.gl.core.KGLAbsGLObject
    protected void deleteGLObject() {
        int i = this.mVertexBufferID;
        if (-1 != i) {
            GLES20.glDeleteBuffers(1, new int[]{i}, 0);
            KGLGetError.info("KGLVertexBufferObjectdelete");
            this.mVertexBufferID = -1;
        }
    }

    private KGLVertexBufferObject(Target target, Usage usage, int i, Buffer buffer) {
        this.mVertexBufferID = -1;
        this.mBufferTarget = Target.ARRAY_BUFFER.mTarget;
        this.mBufferTarget = target.mTarget;
        int[] iArr = {-1};
        GLES20.glGenBuffers(1, iArr, 0);
        this.mVertexBufferID = iArr[0];
        bind();
        if (GLES20.glIsBuffer(this.mVertexBufferID)) {
            GLES20.glBufferData(this.mBufferTarget, i, buffer, usage.mUsage);
            unBind();
        } else {
            unBind();
            this.mVertexBufferID = -1;
        }
        KGLGetError.info("KGLVertexBufferObjectcreate");
    }
}
