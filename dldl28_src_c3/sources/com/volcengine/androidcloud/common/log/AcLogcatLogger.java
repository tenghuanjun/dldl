package com.volcengine.androidcloud.common.log;

import android.util.Log;
import com.volcengine.androidcloud.common.log.AcLog;
import java.util.Locale;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public class AcLogcatLogger implements AcLog.ILogger {
    @Override // com.volcengine.androidcloud.common.log.AcLog.ILogger
    public void onDebug(String str, String str2) {
        Log.d(str, str2);
    }

    @Override // com.volcengine.androidcloud.common.log.AcLog.ILogger
    public void onError(String str, String str2) {
        Log.e(str, str2);
    }

    @Override // com.volcengine.androidcloud.common.log.AcLog.ILogger
    public void onError(String str, String str2, Throwable th) {
        Log.e(str, String.format(Locale.getDefault(), "%s %s", str2, th.getMessage()));
    }

    @Override // com.volcengine.androidcloud.common.log.AcLog.ILogger
    public void onInfo(String str, String str2) {
        Log.i(str, str2);
    }

    @Override // com.volcengine.androidcloud.common.log.AcLog.ILogger
    public void onVerbose(String str, String str2) {
        Log.v(str, str2);
    }

    @Override // com.volcengine.androidcloud.common.log.AcLog.ILogger
    public void onWarn(String str, String str2) {
        Log.w(str, str2);
    }
}
