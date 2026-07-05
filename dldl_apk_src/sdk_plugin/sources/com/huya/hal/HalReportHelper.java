package com.huya.hal;

import com.huya.hyhttpdns.dns.HttpDnsReportListener;
import com.huya.hysignal.listener.HySignalReportListener;
import com.huya.hysignal.util.HySignalLog;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
class HalReportHelper implements HySignalReportListener, HttpDnsReportListener {
    private static HalReportHelper sInstance;
    private HalReportListener listener = null;

    private HalReportHelper() {
    }

    static HalReportHelper getInstance() {
        if (sInstance == null) {
            sInstance = new HalReportHelper();
        }
        return sInstance;
    }

    void init(HalReportListener halReportListener) {
        this.listener = halReportListener;
    }

    @Override // com.huya.hysignal.listener.HySignalReportListener, com.huya.hyhttpdns.dns.HttpDnsReportListener
    public void report(String str, String str2, Map<String, String> map, Map<String, Double> map2, Map<String, String> map3) {
        HalReportListener halReportListener = this.listener;
        if (halReportListener == null) {
            HySignalLog.error("hal report listener null, return");
        } else {
            halReportListener.report(str, str2, map, map2, map3);
        }
    }
}
