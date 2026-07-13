package com.volcengine.cloudphone.apiservice;

import android.content.ClipData;

/* JADX INFO: loaded from: classes3.dex */
public interface IClipBoardServiceManager {
    void sendClipBoardMessage(ClipData clipData);

    void setBoardSyncClipListener(IClipBoardListener iClipBoardListener);
}
