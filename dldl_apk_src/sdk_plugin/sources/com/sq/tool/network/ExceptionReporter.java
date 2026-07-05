package com.sq.tool.network;

import android.content.Context;
import com.sq.tools.report.exception.IExceptionReporter;
import com.sqwan.common.BuglessAction;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class ExceptionReporter implements IExceptionReporter {
    @Override // com.sq.tools.report.exception.IExceptionReporter
    public void init(Context context) {
    }

    @Override // com.sq.tools.report.exception.IExceptionReporter
    public void setUserConsent(Map<String, Boolean> map) {
    }

    @Override // com.sq.tools.report.exception.IExceptionReporter
    public void reportException(Throwable th, int i, String str, String str2) {
        BuglessAction.reportCatchException(th, str, str2, i);
    }
}
