package com.sq.webview.report;

import com.sq.tools.report.event.IEventReporter;
import com.sq.webview.util.WebLogUtil;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class WebEventReporter {
    private final IEventReporter mReporter;

    public WebEventReporter(IEventReporter reporter) {
        this.mReporter = reporter;
    }

    public void report(String event, Map<String, Object> properties) {
        IEventReporter iEventReporter = this.mReporter;
        if (iEventReporter != null) {
            try {
                iEventReporter.report(event, properties);
            } catch (Exception e) {
                WebLogUtil.e(event + "事件上报失败", e);
            }
        }
    }

    public void flush() {
        IEventReporter iEventReporter = this.mReporter;
        if (iEventReporter != null) {
            try {
                iEventReporter.flush();
            } catch (Exception unused) {
            }
        }
    }
}
