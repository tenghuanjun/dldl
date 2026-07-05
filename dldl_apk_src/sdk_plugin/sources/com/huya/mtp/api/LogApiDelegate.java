package com.huya.mtp.api;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class LogApiDelegate implements LogApi {
    private LogApi mLogApi;

    public LogApiDelegate() {
    }

    public LogApiDelegate(LogApi logApi) {
        this.mLogApi = logApi;
    }

    public void setLogApi(LogApi logApi) {
        this.mLogApi = logApi;
    }

    @Override // com.huya.mtp.api.LogApi
    public void verbose(String str) {
        LogApi logApi = this.mLogApi;
        if (logApi != null) {
            logApi.verbose(str);
        }
    }

    @Override // com.huya.mtp.api.LogApi
    public void verbose(Object obj, String str) {
        LogApi logApi = this.mLogApi;
        if (logApi != null) {
            logApi.verbose(obj, str);
        }
    }

    @Override // com.huya.mtp.api.LogApi
    public void verbose(Object obj, String str, Object... objArr) {
        LogApi logApi = this.mLogApi;
        if (logApi != null) {
            logApi.verbose(obj, str, objArr);
        }
    }

    @Override // com.huya.mtp.api.LogApi
    public void verbose(Object obj, String str, Throwable th) {
        LogApi logApi = this.mLogApi;
        if (logApi != null) {
            logApi.verbose(obj, str, th);
        }
    }

    @Override // com.huya.mtp.api.LogApi
    public void verbose(Object obj, Throwable th) {
        LogApi logApi = this.mLogApi;
        if (logApi != null) {
            logApi.verbose(obj, th);
        }
    }

    @Override // com.huya.mtp.api.LogApi
    public void debug(String str) {
        LogApi logApi = this.mLogApi;
        if (logApi != null) {
            logApi.debug(str);
        }
    }

    @Override // com.huya.mtp.api.LogApi
    public void debug(Object obj, String str) {
        LogApi logApi = this.mLogApi;
        if (logApi != null) {
            logApi.debug(obj, str);
        }
    }

    @Override // com.huya.mtp.api.LogApi
    public void debug(Object obj, String str, Object... objArr) {
        LogApi logApi = this.mLogApi;
        if (logApi != null) {
            logApi.debug(obj, str, objArr);
        }
    }

    @Override // com.huya.mtp.api.LogApi
    public void debug(Object obj, String str, Throwable th) {
        LogApi logApi = this.mLogApi;
        if (logApi != null) {
            logApi.debug(obj, str, th);
        }
    }

    @Override // com.huya.mtp.api.LogApi
    public void debug(Object obj, Throwable th) {
        LogApi logApi = this.mLogApi;
        if (logApi != null) {
            logApi.debug(obj, th);
        }
    }

    @Override // com.huya.mtp.api.LogApi
    public void info(String str) {
        LogApi logApi = this.mLogApi;
        if (logApi != null) {
            logApi.info(str);
        }
    }

    @Override // com.huya.mtp.api.LogApi
    public void info(Object obj, String str) {
        LogApi logApi = this.mLogApi;
        if (logApi != null) {
            logApi.info(obj, str);
        }
    }

    @Override // com.huya.mtp.api.LogApi
    public void info(Object obj, String str, Object... objArr) {
        LogApi logApi = this.mLogApi;
        if (logApi != null) {
            logApi.info(obj, str, objArr);
        }
    }

    @Override // com.huya.mtp.api.LogApi
    public void info(Object obj, String str, Throwable th) {
        LogApi logApi = this.mLogApi;
        if (logApi != null) {
            logApi.info(obj, str, th);
        }
    }

    @Override // com.huya.mtp.api.LogApi
    public void info(Object obj, Throwable th) {
        LogApi logApi = this.mLogApi;
        if (logApi != null) {
            logApi.info(obj, th);
        }
    }

    @Override // com.huya.mtp.api.LogApi
    public void warn(String str) {
        LogApi logApi = this.mLogApi;
        if (logApi != null) {
            logApi.warn(str);
        }
    }

    @Override // com.huya.mtp.api.LogApi
    public void warn(Object obj, String str) {
        LogApi logApi = this.mLogApi;
        if (logApi != null) {
            logApi.warn(obj, str);
        }
    }

    @Override // com.huya.mtp.api.LogApi
    public void warn(Object obj, String str, Object... objArr) {
        LogApi logApi = this.mLogApi;
        if (logApi != null) {
            logApi.warn(obj, str, objArr);
        }
    }

    @Override // com.huya.mtp.api.LogApi
    public void warn(Object obj, String str, Throwable th) {
        LogApi logApi = this.mLogApi;
        if (logApi != null) {
            logApi.warn(obj, str, th);
        }
    }

    @Override // com.huya.mtp.api.LogApi
    public void warn(Object obj, Throwable th) {
        LogApi logApi = this.mLogApi;
        if (logApi != null) {
            logApi.warn(obj, th);
        }
    }

    @Override // com.huya.mtp.api.LogApi
    public void error(String str) {
        LogApi logApi = this.mLogApi;
        if (logApi != null) {
            logApi.error(str);
        }
    }

    @Override // com.huya.mtp.api.LogApi
    public void error(Object obj, String str) {
        LogApi logApi = this.mLogApi;
        if (logApi != null) {
            logApi.error(obj, str);
        }
    }

    @Override // com.huya.mtp.api.LogApi
    public void error(Object obj, String str, Object... objArr) {
        LogApi logApi = this.mLogApi;
        if (logApi != null) {
            logApi.error(obj, str, objArr);
        }
    }

    @Override // com.huya.mtp.api.LogApi
    public void error(Object obj, String str, Throwable th) {
        LogApi logApi = this.mLogApi;
        if (logApi != null) {
            logApi.error(obj, str, th);
        }
    }

    @Override // com.huya.mtp.api.LogApi
    public void error(Object obj, Throwable th) {
        LogApi logApi = this.mLogApi;
        if (logApi != null) {
            logApi.error(obj, th);
        }
    }

    @Override // com.huya.mtp.api.LogApi
    public void fatal(String str) {
        LogApi logApi = this.mLogApi;
        if (logApi != null) {
            logApi.fatal(str);
        }
    }

    @Override // com.huya.mtp.api.LogApi
    public void fatal(Object obj, String str) {
        LogApi logApi = this.mLogApi;
        if (logApi != null) {
            logApi.fatal(obj, str);
        }
    }

    @Override // com.huya.mtp.api.LogApi
    public void fatal(Object obj, String str, Object... objArr) {
        LogApi logApi = this.mLogApi;
        if (logApi != null) {
            logApi.fatal(obj, str, objArr);
        }
    }

    @Override // com.huya.mtp.api.LogApi
    public void fatal(Object obj, String str, Throwable th) {
        LogApi logApi = this.mLogApi;
        if (logApi != null) {
            logApi.fatal(obj, str, th);
        }
    }

    @Override // com.huya.mtp.api.LogApi
    public void fatal(Object obj, Throwable th) {
        LogApi logApi = this.mLogApi;
        if (logApi != null) {
            logApi.fatal(obj, th);
        }
    }

    @Override // com.huya.mtp.api.LogApi
    public void uncaughtException(Throwable th) {
        LogApi logApi = this.mLogApi;
        if (logApi != null) {
            logApi.uncaughtException(th);
        }
    }

    @Override // com.huya.mtp.api.LogApi
    public void flushToDisk() {
        LogApi logApi = this.mLogApi;
        if (logApi != null) {
            logApi.flushToDisk();
        }
    }

    @Override // com.huya.mtp.api.LogApi
    public boolean isLogLevelEnabled(int i) {
        LogApi logApi = this.mLogApi;
        if (logApi != null) {
            return logApi.isLogLevelEnabled(i);
        }
        return false;
    }
}
