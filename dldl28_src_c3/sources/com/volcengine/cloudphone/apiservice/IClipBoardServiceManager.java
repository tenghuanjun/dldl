package com.volcengine.cloudphone.apiservice;

import android.content.ClipData;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public interface IClipBoardServiceManager {
    void sendClipBoardMessage(ClipData clipData);

    void setBoardSyncClipListener(IClipBoardListener iClipBoardListener);
}
