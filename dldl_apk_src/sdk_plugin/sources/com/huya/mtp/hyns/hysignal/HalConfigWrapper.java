package com.huya.mtp.hyns.hysignal;

import android.content.Context;
import android.text.TextUtils;
import com.huya.data.MonitorReqData;
import com.huya.hal.HalConfig;
import com.huya.hal.HalReportListener;
import com.huya.hal.HalUserInfo;
import com.huya.hysignal.listener.HySignalGuidListener;
import com.huya.hysignal.wrapper.P2pPushDelegate;
import com.huya.mtp.api.MTPApi;
import com.huya.mtp.hyns.NSInnerConfig;
import com.huya.mtp.hyns.api.NSUserInfoApi;
import com.huya.mtp.hyns.stat.NSStatManager;
import com.sqwan.bugless.util.FileUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class HalConfigWrapper {
    private static final String KEY_RETCODE = "retcode";
    private static final String KEY_SUCCESS = "success";
    private static final String KEY_TRACE_ID = "traceId";
    public static final String NONE_TRACE = "none";
    private static final String TAG = "HalConfigWrapper";
    private HalConfig.Builder mBuilder;

    public HalConfig.Builder getHalConfig() {
        return this.mBuilder;
    }

    private HalConfigWrapper(HalConfig.Builder builder) {
        this.mBuilder = builder;
    }

    public void setSimpleConfig(boolean z, boolean z2) {
        NSInnerConfig.getInstance().setTestEnv(z);
        NSInnerConfig.getInstance().setOverSea(z2);
        this.mBuilder.setSimpleConfig(z, z2);
    }

    public static class Builder {
        private static String sAppSrc = "";
        private static String sUa = "";
        private HalConfig.Builder mHalConfig;
        List<HalReportListener> mReportListener = new ArrayList();

        public Builder(Context context) {
            this.mHalConfig = new HalConfig.Builder(context);
        }

        public Builder setUserInfo(NSUserInfoApi.NSUserInfo nSUserInfo) {
            this.mHalConfig.setUserInfo(new HalUserInfo.Builder().setLogin(nSUserInfo.isLogin()).setToken(nSUserInfo.getToken()).setTokenType(nSUserInfo.getTokenType()).setUid(nSUserInfo.getUid()).build());
            return this;
        }

        public Builder setExperimentConfig(Map<String, String> map) {
            this.mHalConfig.setExperimentConfig(map);
            return this;
        }

        public Builder setPushFrequencyConfig(Map<String, String> map) {
            this.mHalConfig.setPushFrequencyConfig(map);
            return this;
        }

        public Builder isUnableLostMsg(boolean z) {
            this.mHalConfig.isUnableLostMsg(z);
            return this;
        }

        public Builder setUnableLostMsgUris(Set<Long> set) {
            this.mHalConfig.setUnableLostMsgUris(set);
            return this;
        }

        public Builder setMsgMaxCount(long j) {
            this.mHalConfig.setMsgMaxCount(j);
            return this;
        }

        public Builder setGroupMsgMaxCount(long j) {
            this.mHalConfig.setGroupMsgMaxCount(j);
            return this;
        }

        public Builder isNeedVerifyToken(boolean z, boolean z2) {
            this.mHalConfig.isNeedVerifyToken(z, z2);
            return this;
        }

        public Builder isEnableP2PPush(boolean z, P2pPushDelegate p2pPushDelegate) {
            this.mHalConfig.isEnableP2PPush(z, p2pPushDelegate);
            return this;
        }

        public Builder isTestEnv(boolean z) {
            this.mHalConfig.isTestEnv(z);
            return this;
        }

        @Deprecated
        public Builder setTestIP(String str) {
            this.mHalConfig.setTestIP(str);
            return this;
        }

        @Deprecated
        public Builder setTestPort(int i) {
            this.mHalConfig.setTestPort(i);
            return this;
        }

        public Builder setLongLinkHost(String str) {
            this.mHalConfig.setLongLinkHost(str);
            return this;
        }

        public Builder setQuicLinkHost(String str) {
            this.mHalConfig.setQuicLinkHost(str);
            return this;
        }

        public Builder setShortLinkHost(String str) {
            this.mHalConfig.setShortLinkHost(str);
            return this;
        }

        public Builder setUserTestHost(String str) {
            this.mHalConfig.setUserTestHost(str);
            return this;
        }

        public Builder setEnableProxy(boolean z, String str, int i) {
            this.mHalConfig.setEnableProxy(z, str, i);
            return this;
        }

        public Builder setAutoUpdateInterval(long j) {
            this.mHalConfig.setAutoUpdateInterval(j);
            return this;
        }

        public Builder setGuid(String str) {
            this.mHalConfig.setGuid(str);
            return this;
        }

        public Builder setGuidListener(HySignalGuidListener hySignalGuidListener) {
            this.mHalConfig.setGuidListener(hySignalGuidListener);
            return this;
        }

        public Builder setUserCustomHttpDnsHost(String str) {
            this.mHalConfig.setUserCustomHttpDNSHost(str);
            return this;
        }

        public Builder setCacheDir(String str) {
            this.mHalConfig.setCacheDir(str);
            return this;
        }

        @Deprecated
        public Builder setBackupIps(String[] strArr) {
            this.mHalConfig.setBackupIps(strArr);
            return this;
        }

        @Deprecated
        public Builder setBackupDnsMap(Map map) {
            this.mHalConfig.setBackupDnsMap(map);
            return this;
        }

        public Builder addReportListener(HalReportListener halReportListener) {
            this.mReportListener.add(halReportListener);
            return this;
        }

        public Builder setSimpleConfig(boolean z, boolean z2) {
            NSInnerConfig.getInstance().setTestEnv(z);
            NSInnerConfig.getInstance().setOverSea(z2);
            this.mHalConfig.setSimpleConfig(z, z2);
            return this;
        }

        public Builder setAppSrc(String str) {
            sAppSrc = str;
            this.mHalConfig.setAppSrc(str);
            return this;
        }

        public static String getAppSrc() {
            return TextUtils.isEmpty(sAppSrc) ? "" : sAppSrc;
        }

        public Builder setUa(String str) {
            sUa = str;
            this.mHalConfig.setUa(str);
            return this;
        }

        public static String getUa() {
            return TextUtils.isEmpty(sUa) ? "" : sUa;
        }

        public Builder setDeviceId(String str) {
            this.mHalConfig.setDeviceId(str);
            return this;
        }

        @Deprecated
        public Builder isDebug(boolean z) {
            this.mHalConfig.isDebug(z);
            return this;
        }

        @Deprecated
        public Builder setImei(String str) {
            this.mHalConfig.setImei(str);
            return this;
        }

        public Builder setDynamicConfig(Map<String, String> map) {
            NSInnerConfig.getInstance().setDynamicConfig(map);
            this.mHalConfig.setDynamicConfig(map);
            return this;
        }

        public Builder setEnableEncrypt(boolean z) {
            this.mHalConfig.setEnableencrypt(z);
            return this;
        }

        public Builder enableStrictIdle(boolean z) {
            this.mHalConfig.enableStrictIdle(z);
            return this;
        }

        @Deprecated
        public Builder setDebugEnv(boolean z) {
            this.mHalConfig.isDebug(z);
            return this;
        }

        public HalConfigWrapper build() {
            this.mHalConfig.setReportListener(new HalReportListener() { // from class: com.huya.mtp.hyns.hysignal.HalConfigWrapper.Builder.1
                @Override // com.huya.hal.HalReportListener
                public void report(String str, String str2, Map<String, String> map, Map<String, Double> map2, Map<String, String> map3) {
                    if (map == null || map.isEmpty() || map2 == null || map2.isEmpty()) {
                        MTPApi.LOGGER.debug(HalConfigWrapper.TAG, "dims or fields is empty, return");
                        return;
                    }
                    MonitorReqData monitorReqData = new MonitorReqData();
                    monitorReqData.sMetricName = str + FileUtil.FILE_EXTENSION_SEPARATOR + str2;
                    monitorReqData.iTS = System.currentTimeMillis();
                    for (Map.Entry<String, String> entry : map.entrySet()) {
                        if (entry.getKey() != null && !entry.getKey().isEmpty() && entry.getValue() != null && !entry.getValue().isEmpty()) {
                            monitorReqData.vDimension.add(new MonitorReqData.DimensionWrapper(entry.getKey(), entry.getValue()));
                        }
                    }
                    for (Map.Entry<String, Double> entry2 : map2.entrySet()) {
                        if (entry2.getKey() != null && !entry2.getKey().isEmpty() && entry2.getValue() != null) {
                            monitorReqData.vField.add(new MonitorReqData.FieldWrapper(entry2.getKey(), entry2.getValue().doubleValue()));
                        }
                    }
                    if (map3 != null && !map3.isEmpty()) {
                        for (Map.Entry<String, String> entry3 : map3.entrySet()) {
                            if (entry3.getKey() != null && !entry3.getKey().isEmpty() && entry3.getValue() != null && !entry3.getValue().isEmpty()) {
                                monitorReqData.vExLog.add(new MonitorReqData.DimensionWrapper(entry3.getKey(), entry3.getValue()));
                            }
                        }
                    }
                    String str3 = map.get("traceId");
                    if (!TextUtils.isEmpty(str3) && !"none".equals(str3) && NSInnerConfig.getInstance().isNSStatOpen()) {
                        NSStatManager.getInstance().addMonitorData(str3, monitorReqData);
                    }
                    if (NSInnerConfig.getInstance().isSignalStatOpen()) {
                        MTPApi.MONITOR.request(monitorReqData);
                    }
                    Iterator<HalReportListener> it = Builder.this.mReportListener.iterator();
                    while (it.hasNext()) {
                        it.next().report(str, str2, map, map2, map3);
                    }
                }
            });
            return new HalConfigWrapper(this.mHalConfig);
        }
    }
}
