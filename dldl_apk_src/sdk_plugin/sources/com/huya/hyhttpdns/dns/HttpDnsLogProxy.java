package com.huya.hyhttpdns.dns;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
class HttpDnsLogProxy implements HttpDnsLog {
    static HttpDnsLogProxy sInstance;
    private HttpDnsLog mLog;
    private boolean mTest;

    public static HttpDnsLogProxy getInstance() {
        if (sInstance == null) {
            synchronized (HttpDnsLogProxy.class) {
                if (sInstance == null) {
                    sInstance = new HttpDnsLogProxy();
                }
            }
        }
        return sInstance;
    }

    private HttpDnsLogProxy() {
    }

    public void init(HttpDnsLog httpDnsLog, boolean z) {
        this.mLog = httpDnsLog;
        this.mTest = z;
    }

    @Override // com.huya.hyhttpdns.dns.HttpDnsLog
    public void debug(String str, String str2) {
        HttpDnsLog httpDnsLog = this.mLog;
        if (httpDnsLog != null) {
            if (this.mTest) {
                httpDnsLog.info(str, str2);
            } else {
                httpDnsLog.debug(str, str2);
            }
        }
    }

    @Override // com.huya.hyhttpdns.dns.HttpDnsLog
    public void debug(String str, String str2, Object... objArr) {
        HttpDnsLog httpDnsLog = this.mLog;
        if (httpDnsLog != null) {
            if (this.mTest) {
                httpDnsLog.info(str, str2, objArr);
            } else {
                httpDnsLog.debug(str, str2, objArr);
            }
        }
    }

    @Override // com.huya.hyhttpdns.dns.HttpDnsLog
    public void info(String str, String str2) {
        HttpDnsLog httpDnsLog = this.mLog;
        if (httpDnsLog != null) {
            httpDnsLog.info(str, str2);
        }
    }

    @Override // com.huya.hyhttpdns.dns.HttpDnsLog
    public void info(String str, String str2, Object... objArr) {
        HttpDnsLog httpDnsLog = this.mLog;
        if (httpDnsLog != null) {
            httpDnsLog.info(str, str2, objArr);
        }
    }

    @Override // com.huya.hyhttpdns.dns.HttpDnsLog
    public void warn(String str, String str2) {
        HttpDnsLog httpDnsLog = this.mLog;
        if (httpDnsLog != null) {
            httpDnsLog.warn(str, str2);
        }
    }

    @Override // com.huya.hyhttpdns.dns.HttpDnsLog
    public void warn(String str, String str2, Object... objArr) {
        HttpDnsLog httpDnsLog = this.mLog;
        if (httpDnsLog != null) {
            httpDnsLog.warn(str, str2, objArr);
        }
    }

    @Override // com.huya.hyhttpdns.dns.HttpDnsLog
    public void error(String str, String str2) {
        HttpDnsLog httpDnsLog = this.mLog;
        if (httpDnsLog != null) {
            httpDnsLog.error(str, str2);
        }
    }

    @Override // com.huya.hyhttpdns.dns.HttpDnsLog
    public void error(String str, String str2, Object... objArr) {
        HttpDnsLog httpDnsLog = this.mLog;
        if (httpDnsLog != null) {
            httpDnsLog.error(str, str2, objArr);
        }
    }
}
