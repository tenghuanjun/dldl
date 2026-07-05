package com.huya.mtp.api;

import com.huya.data.MonitorReqData;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class MonitorApiDelegate implements MonitorApi {
    private MonitorApi mMonitorApi;

    public void setMonitorApi(MonitorApi monitorApi) {
        this.mMonitorApi = monitorApi;
    }

    @Override // com.huya.mtp.api.MonitorApi
    public void execute(Runnable runnable) {
        MonitorApi monitorApi = this.mMonitorApi;
        if (monitorApi != null) {
            monitorApi.execute(runnable);
        }
    }

    @Override // com.huya.mtp.api.MonitorApi
    public void executeDelayed(Runnable runnable, long j) {
        MonitorApi monitorApi = this.mMonitorApi;
        if (monitorApi != null) {
            monitorApi.executeDelayed(runnable, j);
        }
    }

    @Override // com.huya.mtp.api.MonitorApi
    public void request(MonitorReqData monitorReqData) {
        MonitorApi monitorApi = this.mMonitorApi;
        if (monitorApi != null) {
            monitorApi.request(monitorReqData);
        }
    }
}
