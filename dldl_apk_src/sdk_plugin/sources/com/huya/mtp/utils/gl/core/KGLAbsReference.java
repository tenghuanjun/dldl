package com.huya.mtp.utils.gl.core;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public abstract class KGLAbsReference {
    private long mCount;

    protected abstract void releaseInternal();

    protected KGLAbsReference() {
        this.mCount = 0L;
        this.mCount = 0L;
    }

    public <T extends KGLAbsReference, E extends KGLAbsReference> E reference(T t, E e) throws RuntimeException {
        if (this != e) {
            throw new RuntimeException("reference must be this");
        }
        if (this != t) {
            if (t != null) {
                t.release();
            }
            this.mCount++;
        }
        return e;
    }

    public void retain() {
        this.mCount++;
    }

    public void release() {
        this.mCount--;
        if (isInvalidReference()) {
            releaseInternal();
        }
    }

    public void dispose() {
        this.mCount = 0L;
        releaseInternal();
    }

    public boolean isInvalidReference() {
        return 0 >= this.mCount;
    }
}
