package com.volcengine.cloudphone.apiservice.outinterface;

import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public interface IPlayerListener {
    void onError(int i, String str);

    void onNetworkChanged(int i);

    void onPlaySuccess(String str, int i);

    @Deprecated
    void onServiceInit();

    void onServiceInit(Map<String, Object> map);

    void onWarning(int i, String str);
}
