package com.bytedance.android.live.base.api.outer;

import android.view.View;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public interface ILiveView {
    View getView();

    void onDislike();

    void openLive();

    void release();

    void reportShow();

    void setLiveStatusListener(ILiveStatusListener iLiveStatusListener);

    void setMute(boolean z);

    void show();

    void stream();
}
