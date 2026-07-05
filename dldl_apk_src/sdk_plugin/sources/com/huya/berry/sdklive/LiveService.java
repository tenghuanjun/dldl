package com.huya.berry.sdklive;

import com.duowan.auk.ArkValue;
import com.huya.berry.sdklive.api.ILiveService;
import com.huya.berry.sdklive.liveTool.FloatWindowHelper;
import com.huya.live.service.AbsService;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class LiveService extends AbsService implements ILiveService {
    @Override // com.huya.berry.sdklive.api.ILiveService
    public void startLiveToolService() {
        FloatWindowHelper.startLiveToolService(ArkValue.gContext, null);
    }

    @Override // com.huya.berry.sdklive.api.ILiveService
    public void onStartLive() {
        FloatWindowHelper.onStartLive();
    }

    @Override // com.huya.berry.sdklive.api.ILiveService
    public void onEndLive() {
        FloatWindowHelper.onEndLive();
    }

    @Override // com.huya.berry.sdklive.api.ILiveService
    public void stopLiveToolService() {
        FloatWindowHelper.stopLiveToolService(ArkValue.gContext);
    }
}
