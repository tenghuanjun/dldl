package com.lzy.okserver.download;

import com.lzy.okserver.ProgressListener;
import java.io.File;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public abstract class DownloadListener implements ProgressListener<File> {
    public final Object tag;

    public DownloadListener(Object obj) {
        this.tag = obj;
    }
}
