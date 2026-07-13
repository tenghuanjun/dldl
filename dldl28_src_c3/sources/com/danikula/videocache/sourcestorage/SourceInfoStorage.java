package com.danikula.videocache.sourcestorage;

import com.danikula.videocache.SourceInfo;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public interface SourceInfoStorage {
    SourceInfo get(String str);

    void put(String str, SourceInfo sourceInfo);

    void release();
}
