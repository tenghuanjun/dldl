package com.huya.mtp.utils.gl.core;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public abstract class KGLAbsGLObject extends KGLAbsReference implements KGLIGLObject {
    protected static final int Invalid = -1;

    protected abstract void deleteGLObject();

    public abstract boolean isValid();

    public static <T extends KGLAbsGLObject> T delete(T t) {
        t.deleteGLObject();
        return null;
    }

    @Override // com.huya.mtp.utils.gl.core.KGLAbsReference
    protected void releaseInternal() {
        deleteGLObject();
    }
}
