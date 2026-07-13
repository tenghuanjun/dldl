package com.lzy.okserver.upload;

import com.lzy.okserver.ProgressListener;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public abstract class UploadListener<T> implements ProgressListener<T> {
    public final Object tag;

    public UploadListener(Object obj) {
        this.tag = obj;
    }
}
