package com.sq.tools.network.httpdns.util;

import com.sq.tools.network.httpdns.log.HttpDnsLog;
import com.sq.tools.report.exception.IExceptionReporter;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class ExceptionReporter {
    final IExceptionReporter mReporter;

    public ExceptionReporter(IExceptionReporter reporter) {
        this.mReporter = reporter;
    }

    public void report(ExceptionType type, String data) {
        IExceptionReporter iExceptionReporter = this.mReporter;
        if (iExceptionReporter == null) {
            return;
        }
        try {
            iExceptionReporter.reportException(new Exception(), type.code, type.desc, data);
        } catch (Exception e) {
            HttpDnsLog.e(type + "异常上报失败", e);
        }
    }

    public enum ExceptionType {
        USE_ORIGIN_URL_ERROR(803, "HttpDNS业务接口使用原域名请求"),
        FALL_TO_TX_ERROR(808, "触发腾讯云HttpDNS域名解析"),
        TX_DNS_ERROR(809, "腾讯云HttpDNS域名解析IP失败");

        public final int code;
        public final String desc;

        ExceptionType(int code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.desc + "(" + this.code + ")";
        }
    }
}
