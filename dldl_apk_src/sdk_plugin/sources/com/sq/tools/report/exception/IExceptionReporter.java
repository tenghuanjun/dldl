package com.sq.tools.report.exception;

import android.content.Context;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public interface IExceptionReporter {
    void init(Context context);

    void reportException(Throwable exception, int actionType, String msg, String data);

    void setUserConsent(Map<String, Boolean> consent);
}
