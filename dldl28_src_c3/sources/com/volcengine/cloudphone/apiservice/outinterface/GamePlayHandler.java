package com.volcengine.cloudphone.apiservice.outinterface;

import com.volcengine.cloudcore.common.mode.QueueInfo;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public abstract class GamePlayHandler implements IGamePlayerListener {
    @Override // com.volcengine.cloudphone.apiservice.outinterface.IGamePlayerListener
    public void onError(int i, String str) {
    }

    @Override // com.volcengine.cloudphone.apiservice.outinterface.IGamePlayerListener
    public void onNetworkChanged(int i) {
    }

    @Override // com.volcengine.cloudphone.apiservice.outinterface.IGamePlayerListener
    public void onPlaySuccess(String str, int i, Map<String, String> map, String str2, String str3) {
    }

    @Override // com.volcengine.cloudphone.apiservice.outinterface.IGamePlayerListener
    public void onQueueSuccessAndStart(int i) {
    }

    @Override // com.volcengine.cloudphone.apiservice.outinterface.IGamePlayerListener
    public void onQueueUpdate(List<QueueInfo> list) {
    }

    @Override // com.volcengine.cloudphone.apiservice.outinterface.IGamePlayerListener
    public void onServiceInit() {
    }

    @Override // com.volcengine.cloudphone.apiservice.outinterface.IGamePlayerListener
    public void onWarning(int i, String str) {
    }
}
