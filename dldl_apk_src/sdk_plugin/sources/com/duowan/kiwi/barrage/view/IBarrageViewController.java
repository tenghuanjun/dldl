package com.duowan.kiwi.barrage.view;

import com.duowan.kiwi.barrage.render.IBarrageRender;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public interface IBarrageViewController extends IBarrageView {
    void clearCanvas();

    long drawDanmakus();

    float getFps();

    IBarrageRender getRender();

    boolean isViewReady();
}
