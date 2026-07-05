package com.sqwan.msdk;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class SQReportCore extends BaseSQReportCore {
    public static SQReportCore instance;
    public static byte[] lock = new byte[0];

    private SQReportCore() {
    }

    public static SQReportCore getInstance() {
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = new SQReportCore();
                }
            }
        }
        return instance;
    }
}
