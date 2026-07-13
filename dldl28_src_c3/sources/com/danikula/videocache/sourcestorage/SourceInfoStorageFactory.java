package com.danikula.videocache.sourcestorage;

import android.content.Context;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public class SourceInfoStorageFactory {
    public static SourceInfoStorage newSourceInfoStorage(Context context) {
        return new DatabaseSourceInfoStorage(context);
    }

    public static SourceInfoStorage newEmptySourceInfoStorage() {
        return new NoSourceInfoStorage();
    }
}
