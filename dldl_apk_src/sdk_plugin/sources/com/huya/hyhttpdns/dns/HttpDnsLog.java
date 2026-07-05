package com.huya.hyhttpdns.dns;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public interface HttpDnsLog {
    void debug(String str, String str2);

    void debug(String str, String str2, Object... objArr);

    void error(String str, String str2);

    void error(String str, String str2, Object... objArr);

    void info(String str, String str2);

    void info(String str, String str2, Object... objArr);

    void warn(String str, String str2);

    void warn(String str, String str2, Object... objArr);
}
