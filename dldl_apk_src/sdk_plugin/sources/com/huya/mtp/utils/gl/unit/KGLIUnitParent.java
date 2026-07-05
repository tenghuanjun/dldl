package com.huya.mtp.utils.gl.unit;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public interface KGLIUnitParent {
    void addChild(KGLAbsUnit kGLAbsUnit) throws RuntimeException;

    boolean isClipChildren();

    void removeChild(KGLAbsUnit kGLAbsUnit) throws RuntimeException;

    void setClipChildren(boolean z);
}
