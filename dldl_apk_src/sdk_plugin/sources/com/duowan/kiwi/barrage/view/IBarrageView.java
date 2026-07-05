package com.duowan.kiwi.barrage.view;

import com.duowan.kiwi.barrage.BarrageEvent;
import com.duowan.kiwi.barrage.GunPowder;
import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public interface IBarrageView extends IBarrageConfigView {
    boolean hasCustomTopMargin();

    void offerGunPowder(GunPowder gunPowder, int i);

    @Subscribe(threadMode = ThreadMode.PostThread)
    void onBarrageAlphaChanged(BarrageEvent.BarrageAlphaChanged barrageAlphaChanged);

    @Subscribe(threadMode = ThreadMode.PostThread)
    void onBarrageModelChanged(BarrageEvent.BarrageModelChanged barrageModelChanged);

    @Subscribe(threadMode = ThreadMode.PostThread)
    void onBarrageSizeChanged(BarrageEvent.BarrageSizeChanged barrageSizeChanged);

    void switchRender(boolean z);
}
