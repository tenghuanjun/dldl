package com.huya.mtp.hyns;

import com.huya.mtp.api.MTPApi;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class NSInnerConfig {
    private static final String NS_DETECT_OPEN = "ns.error_detect_open";
    public static final String NS_MONITOR_HYNS = "ns.ns_monitor_control_hyns";
    public static final String NS_MONITOR_HYSIGNAL = "ns.ns_monitor_control_hysignal";
    public static final String TAG = "HyNSInnerConfig";
    private static NSInnerConfig hyNSInnerConfig;
    private Map<String, String> dynamicConfig = new HashMap();
    private boolean mSignalStatOpen = true;
    private boolean mNSStatOpen = true;
    private boolean mNSErrorDetectOpen = false;
    private String mEnvStr = "";
    private String mOverSeaStr = "";
    private boolean isTestEnv = false;
    private boolean isOverSea = false;

    public static NSInnerConfig getInstance() {
        if (hyNSInnerConfig == null) {
            hyNSInnerConfig = new NSInnerConfig();
        }
        return hyNSInnerConfig;
    }

    public synchronized void setDynamicConfig(Map<String, String> map) {
        this.dynamicConfig = map;
        if (map != null) {
            if (map.get(NS_MONITOR_HYNS) != null && map.get(NS_MONITOR_HYNS).equals("0")) {
                this.mNSStatOpen = false;
            }
            if (map.get(NS_MONITOR_HYSIGNAL) != null && map.get(NS_MONITOR_HYSIGNAL).equals("0")) {
                this.mSignalStatOpen = false;
            }
            if (map.get(NS_DETECT_OPEN) != null && map.get(NS_DETECT_OPEN).equals("1")) {
                this.mNSErrorDetectOpen = true;
            }
        }
        MTPApi.LOGGER.info("NetServiceHyNSInnerConfig", "set dy config ns_monitor_control_hyns = %s, ns_monitor_control_hysignal = %s, ns.error_detect_open = %s.", Boolean.valueOf(this.mNSStatOpen), Boolean.valueOf(this.mSignalStatOpen), Boolean.valueOf(this.mNSErrorDetectOpen));
    }

    public Map<String, String> getDynamicConfig() {
        return this.dynamicConfig;
    }

    public boolean isSignalStatOpen() {
        return this.mSignalStatOpen;
    }

    public boolean isNSStatOpen() {
        return this.mNSStatOpen;
    }

    public boolean isNSErrorDetectOpen() {
        return this.mNSErrorDetectOpen;
    }

    public boolean isTestEnv() {
        return this.isTestEnv;
    }

    public NSInnerConfig setTestEnv(boolean z) {
        this.isTestEnv = z;
        this.mEnvStr = z ? "TestEnv" : "ReleaseEnv";
        return this;
    }

    public boolean isOverSea() {
        return this.isOverSea;
    }

    public NSInnerConfig setOverSea(boolean z) {
        this.isOverSea = z;
        return this;
    }

    public String getEnvStr() {
        return this.mEnvStr;
    }
}
