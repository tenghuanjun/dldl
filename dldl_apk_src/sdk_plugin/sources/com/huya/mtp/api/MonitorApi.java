package com.huya.mtp.api;

import com.huya.data.MonitorReqData;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public interface MonitorApi {
    void execute(Runnable runnable);

    void executeDelayed(Runnable runnable, long j);

    void request(MonitorReqData monitorReqData);
}
