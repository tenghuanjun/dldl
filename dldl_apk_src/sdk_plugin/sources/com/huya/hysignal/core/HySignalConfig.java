package com.huya.hysignal.core;

import android.content.Context;
import com.huya.hysignal.listener.HySignalGuidListener;
import com.huya.hysignal.listener.HySignalReportListener;
import com.huya.mtp.hyns.Constants;
import java.util.Map;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class HySignalConfig {
    final String IMEI;
    final String appSrc;
    final String deviceId;
    final long mAutoUpdateInterval;
    final Context mContext;
    final boolean mDebug;
    final String mDebugIP;
    final int mDebugPort;
    final Map<String, String> mDynamicConfig;
    final boolean mEnableEncrypt;
    final boolean mEnableProxy;
    final boolean mEnableStrictIdle;
    final String mEncryptKey;
    final String mExistGuid;
    final String mExperiment;
    final HySignalGuidListener mGuidListener;
    final HysignalDns mHySignalDns;
    final String mLongLinkHost;
    final String mProxyIP;
    final int mProxyPort;
    final String mQuicLinkHost;
    final HySignalReportListener mReportListener;
    final String mShortLinkHost;
    final boolean mTest;
    final long mUid;
    final String ua;

    public String toString() {
        return "【Hal配置】 {\nmContext=" + this.mContext + ", \nmTest=" + this.mTest + ", \nmDebug=" + this.mDebug + ", \nmDebugIP='" + this.mDebugIP + "', \nmDebugPort=" + this.mDebugPort + ", \nmQuicLinkHost='" + this.mQuicLinkHost + "', \nmLongLinkHost='" + this.mLongLinkHost + "', \nmShortLinkHost='" + this.mShortLinkHost + "', \nmHySignalDns=" + this.mHySignalDns + ", \nmExistGuid='" + this.mExistGuid + "', \nmGuidListener=" + this.mGuidListener + ", \nmEnableProxy=" + this.mEnableProxy + ", \nmProxyIP='" + this.mProxyIP + "', \nmProxyPort=" + this.mProxyPort + ", \nmAutoUpdateInterval=" + this.mAutoUpdateInterval + ", \nua='" + this.ua + "', \ndeviceId='" + this.deviceId + "', \nappSrc='" + this.appSrc + "', \nIMEI='" + this.IMEI + "', \nmUid=" + this.mUid + ", \nmExperiment='" + this.mExperiment + "', \nmReportListener=" + this.mReportListener + ", \nEnableIdleStrict=" + this.mEnableStrictIdle + ", \nmDynamicConfig=" + this.mDynamicConfig + AbstractJsonLexerKt.END_OBJ;
    }

    private HySignalConfig(Builder builder) {
        this.mContext = builder.mContext;
        this.mTest = builder.mTestEnv;
        this.mDebug = builder.isDebug;
        this.mDebugIP = builder.mDebugIP;
        this.mDebugPort = builder.mDebugPort;
        this.mQuicLinkHost = builder.mQuicLinkHost;
        this.mLongLinkHost = builder.mLongLinkHost;
        this.mShortLinkHost = builder.mShortLinkHost;
        this.mHySignalDns = builder.mHySignalDns;
        this.mExistGuid = builder.mExistGuid;
        this.mGuidListener = builder.mGuidListener;
        this.mEnableProxy = builder.mEnableProxy;
        this.mProxyIP = builder.mProxyIp;
        this.mProxyPort = builder.mProxyPort;
        this.mAutoUpdateInterval = builder.mAutoUpdateInterval;
        this.mReportListener = builder.mReportListener;
        this.ua = builder.ua;
        this.deviceId = builder.deviceId;
        this.appSrc = builder.appSrc;
        this.IMEI = builder.IMEI;
        this.mUid = builder.mUid;
        this.mExperiment = builder.mExperiment;
        this.mEncryptKey = builder.mEncryptKey;
        this.mDynamicConfig = builder.mDynamicConfig;
        this.mEnableEncrypt = builder.mEnableEncrypt;
        this.mEnableStrictIdle = builder.mEnableIdleStrict;
    }

    public static class Builder {
        Context mContext;
        HySignalGuidListener mGuidListener;
        boolean mTestEnv = false;
        boolean isDebug = false;
        String mDebugIP = "14.116.175.151";
        int mDebugPort = 4434;
        String mQuicLinkHost = Constants.NATIONAL_LONG_LINK_HOST;
        String mLongLinkHost = Constants.NATIONAL_LONG_LINK_HOST;
        String mShortLinkHost = "cdn.wup.huya.com";
        HysignalDns mHySignalDns = null;
        String mExistGuid = null;
        boolean mEnableProxy = false;
        String mProxyIp = null;
        int mProxyPort = 0;
        long mAutoUpdateInterval = 0;
        HySignalReportListener mReportListener = null;
        String ua = "";
        String deviceId = "";
        String appSrc = "";
        String IMEI = "";
        long mUid = 0;
        String mExperiment = "";
        String mEncryptKey = "";
        Map<String, String> mDynamicConfig = null;
        boolean mEnableEncrypt = false;
        boolean mEnableIdleStrict = false;

        public Builder(Context context) {
            this.mContext = context;
        }

        public Builder isTestEnv(boolean z) {
            this.mTestEnv = z;
            return this;
        }

        @Deprecated
        public Builder isDebugEnv(boolean z) {
            this.isDebug = z;
            return this;
        }

        @Deprecated
        public Builder setDebugIP(String str) {
            if (str != null && !"".equals(str)) {
                this.mDebugIP = str;
            }
            return this;
        }

        @Deprecated
        public Builder setDebugPort(int i) {
            if (i > 0) {
                this.mDebugPort = i;
            }
            return this;
        }

        public Builder setQuicLinkHost(String str) {
            if (this.mQuicLinkHost != null && !"".equals(str)) {
                this.mQuicLinkHost = str;
            }
            return this;
        }

        public Builder setLongLinkHost(String str) {
            if (str != null && !"".equals(str)) {
                this.mLongLinkHost = str;
            }
            return this;
        }

        public Builder setShortLinkHost(String str) {
            if (str != null && !"".equals(str)) {
                this.mShortLinkHost = str;
            }
            return this;
        }

        public Builder setHySignalDns(HysignalDns hysignalDns) {
            this.mHySignalDns = hysignalDns;
            return this;
        }

        public Builder setExistGuid(String str) {
            this.mExistGuid = str;
            return this;
        }

        public Builder setGuidListener(HySignalGuidListener hySignalGuidListener) {
            this.mGuidListener = hySignalGuidListener;
            return this;
        }

        public Builder setUid(long j) {
            this.mUid = j;
            return this;
        }

        public Builder setExperiment(String str) {
            this.mExperiment = str;
            return this;
        }

        public Builder setEnableProxy(boolean z, String str, int i) {
            if (z && (str == null || "".equals(str) || i <= 0)) {
                return this;
            }
            this.mEnableProxy = z;
            this.mProxyIp = str;
            this.mProxyPort = i;
            return this;
        }

        public Builder setAutoUpdateInterval(long j) {
            if (j > 0) {
                this.mAutoUpdateInterval = j;
            }
            return this;
        }

        public Builder setReportListener(HySignalReportListener hySignalReportListener) {
            this.mReportListener = hySignalReportListener;
            return this;
        }

        public Builder setDeviceId(String str) {
            this.deviceId = str;
            return this;
        }

        public Builder setAppSrc(String str) {
            this.appSrc = str;
            return this;
        }

        public Builder setImei(String str) {
            this.IMEI = str;
            return this;
        }

        public Builder setUa(String str) {
            this.ua = str;
            return this;
        }

        public Builder setDynamicConfig(Map<String, String> map) {
            this.mDynamicConfig = map;
            return this;
        }

        public Builder setEncryptKey(String str) {
            this.mEncryptKey = str;
            return this;
        }

        public Builder setEnableEncrypt(boolean z) {
            this.mEnableEncrypt = z;
            return this;
        }

        public Builder setEnableIdleStrict(boolean z) {
            this.mEnableIdleStrict = z;
            return this;
        }

        public HySignalConfig build() {
            return new HySignalConfig(this);
        }
    }

    public boolean isTest() {
        return this.mTest;
    }

    public boolean isDebug() {
        return this.mDebug;
    }

    public String getDebugIP() {
        return this.mDebugIP;
    }

    public int getDebugPort() {
        return this.mDebugPort;
    }

    public String getLongLinkHost() {
        return this.mLongLinkHost;
    }

    public String getQuicLinkHost() {
        return this.mQuicLinkHost;
    }

    public String getShortLinkHost() {
        return this.mShortLinkHost;
    }

    public HysignalDns getHttpDns() {
        return this.mHySignalDns;
    }

    public boolean isEnableProxy() {
        return this.mEnableProxy;
    }

    public String getProxyIP() {
        return this.mProxyIP;
    }

    public int getProxyPort() {
        return this.mProxyPort;
    }

    public long getAutoUpdateInterval() {
        return this.mAutoUpdateInterval;
    }

    public String getUa() {
        return this.ua;
    }

    public String getDeviceId() {
        return this.deviceId;
    }

    public String getAppSrc() {
        return this.appSrc;
    }

    public String getIMEI() {
        return this.IMEI;
    }

    public Context getContext() {
        return this.mContext;
    }

    public long getUid() {
        return this.mUid;
    }

    public String getExperiment() {
        return this.mExperiment;
    }

    public Map<String, String> getDynamicConfig() {
        return this.mDynamicConfig;
    }

    public String getEncryptKey() {
        return this.mEncryptKey;
    }

    public boolean getEnableEncrypt() {
        return this.mEnableEncrypt;
    }
}
