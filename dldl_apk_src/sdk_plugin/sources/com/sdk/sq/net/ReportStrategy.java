package com.sdk.sq.net;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public enum ReportStrategy {
    NOT_REPORT,
    ONLY_EVENT,
    ONLY_EXCEPTION,
    ALL;

    public static boolean canReportException(ReportStrategy strategy) {
        return strategy == null || strategy == ALL || strategy == ONLY_EXCEPTION;
    }

    public static boolean canReportEvent(ReportStrategy strategy) {
        return strategy == null || strategy == ALL || strategy == ONLY_EVENT;
    }
}
