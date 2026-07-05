package com.bumptech.glide.request;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public interface Request {
    void begin();

    void clear();

    boolean isCleared();

    boolean isComplete();

    boolean isEquivalentTo(Request request);

    boolean isFailed();

    boolean isResourceSet();

    boolean isRunning();

    void recycle();
}
