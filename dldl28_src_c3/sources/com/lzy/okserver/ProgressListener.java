package com.lzy.okserver;

import com.lzy.okgo.model.Progress;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public interface ProgressListener<T> {
    void onError(Progress progress);

    void onFinish(T t, Progress progress);

    void onProgress(Progress progress);

    void onRemove(Progress progress);

    void onStart(Progress progress);
}
