package com.volcengine.cloudphone.apiservice;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public interface IProbeNetworkListener {
    public static final int ERROR_BAD_NETWORK = 1;
    public static final int ERROR_CANCEL_BY_USER = 2;
    public static final int ERROR_EMPTY_STATS = 3;
    public static final int QUALITY_EXCELLENT = 1;
    public static final int QUALITY_GOOD = 2;
    public static final int QUALITY_POOR = 3;

    void onProbeCompleted(ProbeStats probeStats, int i);

    void onProbeError(int i, String str);

    void onProbeProgress(ProbeStats probeStats);

    void onProbeStarted();
}
