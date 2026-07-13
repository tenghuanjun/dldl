package com.bytedance.bdtracker;

import com.bytedance.applog.ILogger;
import com.bytedance.applog.log.ILogProcessor;
import com.bytedance.applog.log.LogInfo;

/* JADX INFO: loaded from: classes2.dex */
public class z0 implements ILogProcessor {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ILogger f342a;

    public z0(ILogger iLogger) {
        this.f342a = iLogger;
    }

    @Override // com.bytedance.applog.log.ILogProcessor
    public void onLog(LogInfo logInfo) {
        ILogger iLogger = this.f342a;
        if (iLogger != null) {
            iLogger.log(logInfo.getMessage(), logInfo.getThrowable());
        }
    }
}
