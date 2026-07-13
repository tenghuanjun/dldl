package com.bytedance.bdtracker;

import android.util.Log;
import com.bytedance.applog.log.ILogProcessor;
import com.bytedance.applog.log.LogInfo;
import com.bytedance.applog.log.LogInfoBuilder;

/* JADX INFO: loaded from: classes2.dex */
public class y0 implements ILogProcessor {
    public y0(d dVar) {
        LogInfoBuilder logInfoBuilderThread = LogInfo.builder().appId(dVar.m).level(1).thread(Thread.currentThread().getName());
        StringBuilder sbA = a.a("Console logger debug is:");
        sbA.append(dVar.G);
        onLog(logInfoBuilderThread.message(sbA.toString()).build());
    }

    @Override // com.bytedance.applog.log.ILogProcessor
    public void onLog(LogInfo logInfo) {
        int level = logInfo.getLevel();
        if (level == 2) {
            Log.i("AppLog", logInfo.toLiteString());
            return;
        }
        if (level == 3) {
            Log.w("AppLog", logInfo.toLiteString(), logInfo.getThrowable());
        } else if (level == 4 || level == 5) {
            Log.e("AppLog", logInfo.toLiteString(), logInfo.getThrowable());
        } else {
            Log.d("AppLog", logInfo.toLiteString());
        }
    }
}
