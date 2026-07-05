package master.flame.danmaku.controller;

import android.content.Context;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public interface IDanmakuViewController {
    void clear();

    long drawDanmakus();

    Context getContext();

    int getViewHeight();

    int getViewWidth();

    boolean isDanmakuDrawingCacheEnabled();

    boolean isHardwareAccelerated();

    boolean isViewReady();
}
