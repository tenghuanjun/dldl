package com.huya.live.ns.impl;

import com.duowan.auk.util.L;
import com.huya.mtp.api.LogApi;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class NSLogApiImpl implements LogApi {
    @Override // com.huya.mtp.api.LogApi
    public void verbose(String str) {
        L.verbose(str);
    }

    @Override // com.huya.mtp.api.LogApi
    public void verbose(Object obj, String str) {
        L.verbose(obj, str);
    }

    @Override // com.huya.mtp.api.LogApi
    public void verbose(Object obj, String str, Object... objArr) {
        L.verbose(obj, str, objArr);
    }

    @Override // com.huya.mtp.api.LogApi
    public void verbose(Object obj, String str, Throwable th) {
        L.verbose(obj, str, th.getMessage());
    }

    @Override // com.huya.mtp.api.LogApi
    public void verbose(Object obj, Throwable th) {
        L.verbose(obj, th.getMessage());
    }

    @Override // com.huya.mtp.api.LogApi
    public void debug(String str) {
        L.debug(str);
    }

    @Override // com.huya.mtp.api.LogApi
    public void debug(Object obj, String str) {
        L.debug(obj, str);
    }

    @Override // com.huya.mtp.api.LogApi
    public void debug(Object obj, String str, Object... objArr) {
        L.debug(obj, str, objArr);
    }

    @Override // com.huya.mtp.api.LogApi
    public void debug(Object obj, String str, Throwable th) {
        L.debug(obj, str, th.getMessage());
    }

    @Override // com.huya.mtp.api.LogApi
    public void debug(Object obj, Throwable th) {
        L.debug(obj, th.getMessage());
    }

    @Override // com.huya.mtp.api.LogApi
    public void info(String str) {
        L.info(str);
    }

    @Override // com.huya.mtp.api.LogApi
    public void info(Object obj, String str) {
        L.info(obj, str);
    }

    @Override // com.huya.mtp.api.LogApi
    public void info(Object obj, String str, Object... objArr) {
        L.info(obj, str, objArr);
    }

    @Override // com.huya.mtp.api.LogApi
    public void info(Object obj, String str, Throwable th) {
        L.info(obj, str, th.getMessage());
    }

    @Override // com.huya.mtp.api.LogApi
    public void info(Object obj, Throwable th) {
        L.info(obj, th.getMessage());
    }

    @Override // com.huya.mtp.api.LogApi
    public void warn(String str) {
        L.warn(str);
    }

    @Override // com.huya.mtp.api.LogApi
    public void warn(Object obj, String str) {
        L.warn(obj, str);
    }

    @Override // com.huya.mtp.api.LogApi
    public void warn(Object obj, String str, Object... objArr) {
        L.warn(obj, str, objArr);
    }

    @Override // com.huya.mtp.api.LogApi
    public void warn(Object obj, String str, Throwable th) {
        L.warn(obj, th.getMessage());
    }

    @Override // com.huya.mtp.api.LogApi
    public void warn(Object obj, Throwable th) {
        L.warn(obj, th.getMessage());
    }

    @Override // com.huya.mtp.api.LogApi
    public void error(String str) {
        L.error(str);
    }

    @Override // com.huya.mtp.api.LogApi
    public void error(Object obj, String str) {
        L.error(obj, str);
    }

    @Override // com.huya.mtp.api.LogApi
    public void error(Object obj, String str, Object... objArr) {
        L.error(obj, str, objArr);
    }

    @Override // com.huya.mtp.api.LogApi
    public void error(Object obj, String str, Throwable th) {
        L.error(obj, str, th);
    }

    @Override // com.huya.mtp.api.LogApi
    public void error(Object obj, Throwable th) {
        L.error(obj, th);
    }

    @Override // com.huya.mtp.api.LogApi
    public void fatal(String str) {
        L.error(str);
    }

    @Override // com.huya.mtp.api.LogApi
    public void fatal(Object obj, String str) {
        L.error(obj, str);
    }

    @Override // com.huya.mtp.api.LogApi
    public void fatal(Object obj, String str, Object... objArr) {
        L.error(obj, str, objArr);
    }

    @Override // com.huya.mtp.api.LogApi
    public void fatal(Object obj, String str, Throwable th) {
        L.error(obj, str, th);
    }

    @Override // com.huya.mtp.api.LogApi
    public void fatal(Object obj, Throwable th) {
        L.error(obj, th);
    }

    @Override // com.huya.mtp.api.LogApi
    public void uncaughtException(Throwable th) {
        L.uncaughtException(th);
    }

    @Override // com.huya.mtp.api.LogApi
    public void flushToDisk() {
        L.flushToDisk();
    }

    @Override // com.huya.mtp.api.LogApi
    public boolean isLogLevelEnabled(int i) {
        return L.isLogLevelEnabled(i);
    }
}
