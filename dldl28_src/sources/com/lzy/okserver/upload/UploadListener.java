package com.lzy.okserver.upload;

import com.lzy.okserver.ProgressListener;

/* JADX INFO: loaded from: classes3.dex */
public abstract class UploadListener<T> implements ProgressListener<T> {
    public final Object tag;

    public UploadListener(Object obj) {
        this.tag = obj;
    }
}
