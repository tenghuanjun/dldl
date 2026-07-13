package com.volcengine.cloudphone.apiservice.outinterface;

import com.volcengine.cloudcore.common.mode.QueueInfo;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public interface IGamePlayerListener {
    void onError(int i, String str);

    void onNetworkChanged(int i);

    void onPlaySuccess(String str, int i, Map<String, String> map, String str2, String str3);

    void onQueueSuccessAndStart(int i);

    void onQueueUpdate(List<QueueInfo> list);

    void onServiceInit();

    void onWarning(int i, String str);
}
