package com.volcengine.androidcloud.common.log;

import com.volcengine.androidcloud.common.log.AcLog;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public class AcLoggingLogger implements AcLog.ILogger {
    private static final Logger LOG = Logger.getLogger(AcLoggingLogger.class.getName());

    @Override // com.volcengine.androidcloud.common.log.AcLog.ILogger
    public void onDebug(String str, String str2) {
        LOG.log(Level.FINE, String.format(Locale.getDefault(), "%s:%s", str, str2));
    }

    @Override // com.volcengine.androidcloud.common.log.AcLog.ILogger
    public void onError(String str, String str2) {
        LOG.log(Level.SEVERE, String.format(Locale.getDefault(), "%s:%s", str, str2));
    }

    @Override // com.volcengine.androidcloud.common.log.AcLog.ILogger
    public void onError(String str, String str2, Throwable th) {
        LOG.log(Level.SEVERE, String.format(Locale.getDefault(), "%s:%s-%s", str, str2, th.getMessage()));
    }

    @Override // com.volcengine.androidcloud.common.log.AcLog.ILogger
    public void onInfo(String str, String str2) {
        LOG.log(Level.INFO, String.format(Locale.getDefault(), "%s:%s", str, str2));
    }

    @Override // com.volcengine.androidcloud.common.log.AcLog.ILogger
    public void onVerbose(String str, String str2) {
        LOG.log(Level.FINEST, String.format(Locale.getDefault(), "%s:%s", str, str2));
    }

    @Override // com.volcengine.androidcloud.common.log.AcLog.ILogger
    public void onWarn(String str, String str2) {
        LOG.log(Level.WARNING, String.format(Locale.getDefault(), "%s:%s", str, str2));
    }
}
