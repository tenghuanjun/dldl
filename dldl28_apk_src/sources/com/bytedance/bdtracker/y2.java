package com.bytedance.bdtracker;

import com.bytedance.applog.AppLog;
import com.volcengine.onekit.service.Device;

/* JADX INFO: loaded from: classes2.dex */
public class y2 implements Device {
    public String getDeviceID() {
        return AppLog.getDid();
    }

    public String getInstallID() {
        return AppLog.getIid();
    }

    public String getSsID() {
        return AppLog.getSsid();
    }
}
