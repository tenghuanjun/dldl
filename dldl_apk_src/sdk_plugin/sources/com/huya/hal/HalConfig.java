package com.huya.hal;

import android.content.Context;
import android.util.Log;
import com.huya.hal.HalUserInfo;
import com.huya.hyhttpdns.dns.HttpDnsConfig;
import com.huya.hysignal.listener.HySignalGuidListener;
import com.huya.hysignal.wrapper.P2pPushDelegate;
import com.huya.hysignal.wrapper.SignalWrapConfig;
import com.huya.mtp.hyns.Constants;
import java.util.Map;
import java.util.Set;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class HalConfig {
    final String IMEI;
    final String appSrc;
    final String deviceId;
    final boolean enableEncrypt;
    final String encryptKey;
    final boolean mAutoRegisterUid;
    final long mAutoUpdateInterval;
    final String mCacheDir;
    final Context mContext;
    final boolean mDebug;
    final Map<String, String> mDynamicConfig;
    final boolean mEnableP2PPush;
    final boolean mEnableProxy;
    final boolean mEnableStrictIdle;
    final Map<String, String> mExperimentConfig;
    final long mGroupMsgCount;
    final String mGuid;
    final HySignalGuidListener mGuidListener;
    final String mLongLinkHost;
    final long mMsgMaxCount;
    final boolean mNeedVerifyToken;
    final boolean mOverSea;
    final P2pPushDelegate mP2PPushDelegate;
    final String mProxyIP;
    final int mProxyPort;
    final Map<String, String> mPushFrequencyConfig;
    final String mQuicLinkHost;
    final Set<Long> mRegisterMsgUris;
    final HalReportListener mReportListener;
    final String mShortLinkHost;
    final boolean mTest;
    final String mTestIP;
    final String mTestIPv6;
    final int mTestPort;
    final boolean mUnableLostMsg;
    final String mUserCustomHttpDNSHost;
    final String mUserSetTestHost;
    final String ua;
    final HalUserInfo userInfo;

    private HalConfig(Builder builder) {
        this.mContext = builder.mContext;
        this.mTest = builder.mTest;
        this.mOverSea = builder.mOversea;
        this.mDebug = builder.mDebug;
        this.mTestIP = builder.mTestIP;
        this.mTestIPv6 = builder.mTestIPv6;
        this.mTestPort = builder.mTestPort;
        this.mLongLinkHost = builder.mLongLinkHost;
        this.mQuicLinkHost = builder.mQuicLinkHost;
        this.mShortLinkHost = builder.mShortLinkHost;
        this.mUserSetTestHost = builder.mUserSetTestHost;
        this.mEnableProxy = builder.mEnableProxy;
        this.mProxyIP = builder.mProxyIp;
        this.mProxyPort = builder.mProxyPort;
        this.mAutoUpdateInterval = builder.mAutoUpdateInterval;
        this.mExperimentConfig = builder.mExperimentConfig;
        this.mPushFrequencyConfig = builder.mPushFrequencyConfig;
        this.mDynamicConfig = builder.mDynamicConfig;
        this.mUnableLostMsg = builder.mUnableLostMsg;
        this.mRegisterMsgUris = builder.mRegisterMsgUris;
        this.mMsgMaxCount = builder.mMsgMaxCount;
        this.mGroupMsgCount = builder.mGroupMsgCount;
        this.mNeedVerifyToken = builder.mNeedVerifyToken;
        this.mAutoRegisterUid = builder.mAutoRegisterUid;
        this.mEnableP2PPush = builder.mEnableP2PPush;
        this.mP2PPushDelegate = builder.mP2PPushDelegate;
        this.mGuid = builder.mGuid;
        this.mGuidListener = builder.mGuidListener;
        this.mCacheDir = builder.mCacheDir;
        this.mUserCustomHttpDNSHost = builder.mUserCustomHttpNDSHost;
        this.mReportListener = builder.mReportListener;
        this.userInfo = builder.userInfo;
        this.appSrc = builder.appSrc;
        this.ua = builder.ua;
        this.deviceId = builder.deviceId;
        this.IMEI = builder.IMEI;
        this.enableEncrypt = builder.enableEncrypt;
        this.encryptKey = builder.encryptKey;
        this.mEnableStrictIdle = builder.mEnableStrictIdle;
    }

    public String toString() {
        return "————————————————————【NetService Config】————————————————————\nmTest=" + this.mTest + ", \nmDebug=" + this.mDebug + ", \nmTestIP='" + this.mTestIP + "', \nmTestIPv6='" + this.mTestIPv6 + "', \nmTestPort=" + this.mTestPort + ", \nmLongLinkHost='" + this.mLongLinkHost + "', \nmShortLinkHost='" + this.mShortLinkHost + "', \nmGuid='" + this.mGuid + "', \nmGuidListener=" + this.mGuidListener + ", \nmEnableProxy=" + this.mEnableProxy + ", \nmProxyIP='" + this.mProxyIP + "', \nmProxyPort=" + this.mProxyPort + ", \nmAutoUpdateInterval=" + this.mAutoUpdateInterval + ", \nmExperimentConfig=" + this.mExperimentConfig + ", \nmPushFrequencyConfig=" + this.mPushFrequencyConfig + ", \nmDynamicConfig=" + this.mDynamicConfig + ", \nmUnableLostMsg=" + this.mUnableLostMsg + ", \nmRegisterMsgUris=" + this.mRegisterMsgUris + ", \nmMsgMaxCount=" + this.mMsgMaxCount + ", \nmGroupMsgCount=" + this.mGroupMsgCount + ", \nmNeedVerifyToken=" + this.mNeedVerifyToken + ", \nmAutoRegisterUid=" + this.mAutoRegisterUid + ", \nmEnableP2PPush=" + this.mEnableP2PPush + ", \nmP2PPushDelegate=" + this.mP2PPushDelegate + ", \nmReportListener=" + this.mReportListener + ", \nuserInfo=" + this.userInfo + ", \nappSrc='" + this.appSrc + "', \nua='" + this.ua + "', \ndeviceId='" + this.deviceId + "', \nIMEI='" + this.IMEI + "', \nEnableStrictIdle=" + this.mEnableStrictIdle + AbstractJsonLexerKt.END_OBJ;
    }

    public String readableString() {
        return "————————————————————【NetService Config】————————————————————\n关键参数:,\nappSrc = '" + this.appSrc + "',\nua = '" + this.ua + "',\n测试环境 = " + this.mTest + ",\nDebug环境 = " + this.mDebug + ",\n测试IP = '" + this.mTestIP + "',\n测试端口 = " + this.mTestPort + ",\n长连接域名 = '" + this.mLongLinkHost + "',\n短连接域名 = '" + this.mShortLinkHost + "',\nHttpDNS缓存路径 = '" + this.mCacheDir + "',\n使用代理 = " + this.mEnableProxy + ",\n代理IP = '" + this.mProxyIP + "',\n代理端口 = " + this.mProxyPort + ",\n动态配置参数 = " + this.mDynamicConfig + "\n\n其他参数:\n实验配置参数 = '" + this.mExperimentConfig + "',\n防闪断开关 = " + this.mUnableLostMsg + ",\n开启防闪断uri = " + this.mRegisterMsgUris + ",\n单播最大缓存数 = " + this.mMsgMaxCount + ",\n广播最大缓存数 = " + this.mGroupMsgCount + ",\n授信开关 = " + this.mNeedVerifyToken + ",\n严格不唤醒 = " + this.mEnableStrictIdle;
    }

    public static class Builder {
        boolean mAutoRegisterUid;
        long mAutoUpdateInterval;
        String mCacheDir;
        Context mContext;
        boolean mEnableP2PPush;
        boolean mEnableProxy;
        Map<String, String> mExperimentConfig;
        long mGroupMsgCount;
        String mLongLinkHost;
        long mMsgMaxCount;
        boolean mNeedVerifyToken;
        P2pPushDelegate mP2PPushDelegate;
        String mProxyIp;
        int mProxyPort;
        Map<String, String> mPushFrequencyConfig;
        String mQuicLinkHost;
        Set<Long> mRegisterMsgUris;
        String mShortLinkHost;
        boolean mTest;
        String mTestIP;
        int mTestPort;
        boolean mUnableLostMsg;
        boolean mDebug = false;
        boolean mOversea = false;
        String mTestIPv6 = "";
        String mUserSetTestHost = null;
        String mGuid = null;
        HySignalGuidListener mGuidListener = null;
        Map<String, String> mDynamicConfig = null;
        String mUserCustomHttpNDSHost = null;
        HalReportListener mReportListener = null;
        HalUserInfo userInfo = new HalUserInfo.Builder().defaultUserInfo();
        String ua = "";
        String deviceId = "";
        String appSrc = "";
        String IMEI = "";
        boolean enableEncrypt = false;
        String encryptKey = "";
        boolean mEnableStrictIdle = false;

        @Deprecated
        public Builder setBackupDnsMap(Map map) {
            if (map == null) {
            }
            return this;
        }

        @Deprecated
        public Builder setBackupIps(String[] strArr) {
            if (strArr == null) {
            }
            return this;
        }

        public Builder(Context context) {
            this.mContext = context;
            SignalWrapConfig signalWrapConfigBuild = new SignalWrapConfig.Builder(context).build();
            this.mTest = signalWrapConfigBuild.isTest();
            this.mTestIP = signalWrapConfigBuild.getDebugIP();
            this.mTestPort = signalWrapConfigBuild.getDebugPort();
            this.mLongLinkHost = signalWrapConfigBuild.getLongLinkHost();
            this.mQuicLinkHost = signalWrapConfigBuild.getQuicLinkHost();
            this.mShortLinkHost = signalWrapConfigBuild.getShortLinkHost();
            this.mEnableProxy = signalWrapConfigBuild.isEnableProxy();
            this.mProxyIp = signalWrapConfigBuild.getProxyIP();
            this.mProxyPort = signalWrapConfigBuild.getProxyPort();
            this.mAutoUpdateInterval = signalWrapConfigBuild.getAutoUpdateInterval();
            this.mExperimentConfig = signalWrapConfigBuild.getExperimentConfig();
            this.mPushFrequencyConfig = signalWrapConfigBuild.getPushFrequencyConfig();
            this.mUnableLostMsg = signalWrapConfigBuild.isUnableLostMsg();
            this.mRegisterMsgUris = signalWrapConfigBuild.getRegisterMsgUris();
            this.mMsgMaxCount = signalWrapConfigBuild.getMsgMaxCount();
            this.mGroupMsgCount = signalWrapConfigBuild.getGroupMsgCount();
            this.mNeedVerifyToken = signalWrapConfigBuild.isNeedVerifyToken();
            this.mAutoRegisterUid = signalWrapConfigBuild.isAutoRegisterUid();
            this.mEnableP2PPush = signalWrapConfigBuild.isEnableP2PPush();
            this.mP2PPushDelegate = signalWrapConfigBuild.getP2PPushDelegate();
            this.mCacheDir = new HttpDnsConfig.Builder(context).build().getCacheDir();
        }

        public boolean isTestEnv() {
            return this.mTest;
        }

        public boolean isOversea() {
            return this.mOversea;
        }

        public String getUserSetTestHost() {
            return this.mUserSetTestHost;
        }

        public Builder setUserInfo(HalUserInfo halUserInfo) {
            if (halUserInfo != null) {
                this.userInfo = halUserInfo;
            }
            return this;
        }

        public Builder setExperimentConfig(Map<String, String> map) {
            this.mExperimentConfig = map;
            return this;
        }

        public Builder setPushFrequencyConfig(Map<String, String> map) {
            this.mPushFrequencyConfig = map;
            return this;
        }

        public Builder isUnableLostMsg(boolean z) {
            this.mUnableLostMsg = z;
            return this;
        }

        public Builder setUnableLostMsgUris(Set<Long> set) {
            this.mRegisterMsgUris = set;
            return this;
        }

        public Builder setMsgMaxCount(long j) {
            if (j > 0) {
                this.mMsgMaxCount = j;
            }
            return this;
        }

        public Builder setGroupMsgMaxCount(long j) {
            if (j > 0) {
                this.mGroupMsgCount = j;
            }
            return this;
        }

        public Builder isNeedVerifyToken(boolean z, boolean z2) {
            this.mNeedVerifyToken = z;
            this.mAutoRegisterUid = z2;
            return this;
        }

        public Builder isEnableP2PPush(boolean z, P2pPushDelegate p2pPushDelegate) {
            if (z && p2pPushDelegate == null) {
                Log.e("HalConfig", "enable P2P but delegate is null");
                return this;
            }
            this.mEnableP2PPush = z;
            this.mP2PPushDelegate = p2pPushDelegate;
            return this;
        }

        public Builder isTestEnv(boolean z) {
            this.mTest = z;
            return this;
        }

        public Builder isDebug(boolean z) {
            this.mDebug = z;
            return this;
        }

        public Builder setTestIP(String str) {
            if (str != null && !"".equals(str)) {
                this.mTestIP = str;
            }
            return this;
        }

        public Builder setTestIPv6(String str) {
            if (str != null && !"".equals(str)) {
                this.mTestIPv6 = str;
            }
            return this;
        }

        public Builder setTestPort(int i) {
            if (i > 0) {
                this.mTestPort = i;
            }
            return this;
        }

        public Builder setLongLinkHost(String str) {
            if (str != null && !"".equals(str)) {
                this.mLongLinkHost = str;
            }
            return this;
        }

        public Builder setQuicLinkHost(String str) {
            if (str != null && !"".equals(str)) {
                this.mQuicLinkHost = str;
            }
            return this;
        }

        public Builder setShortLinkHost(String str) {
            if (str != null && !"".equals(str)) {
                this.mShortLinkHost = str;
            }
            return this;
        }

        public Builder setUserTestHost(String str) {
            if (str != null && !str.isEmpty()) {
                this.mUserSetTestHost = str;
            }
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
            if (j < 0) {
                return this;
            }
            this.mAutoUpdateInterval = j;
            return this;
        }

        public Builder setGuid(String str) {
            this.mGuid = str;
            return this;
        }

        public Builder setGuidListener(HySignalGuidListener hySignalGuidListener) {
            this.mGuidListener = hySignalGuidListener;
            return this;
        }

        @Deprecated
        public Builder setCacheDir(String str) {
            if (str != null && !"".equals(str)) {
                this.mCacheDir = str;
            }
            return this;
        }

        public Builder setReportListener(HalReportListener halReportListener) {
            this.mReportListener = halReportListener;
            return this;
        }

        public Builder setAppSrc(String str) {
            if (str == null) {
                return this;
            }
            this.appSrc = str;
            return this;
        }

        public Builder setUa(String str) {
            if (str == null) {
                return this;
            }
            this.ua = str;
            return this;
        }

        public Builder setDeviceId(String str) {
            if (str == null) {
                return this;
            }
            this.deviceId = str;
            return this;
        }

        public Builder setImei(String str) {
            if (str == null) {
                return this;
            }
            this.IMEI = str;
            return this;
        }

        public Builder setDynamicConfig(Map<String, String> map) {
            this.mDynamicConfig = map;
            return this;
        }

        public Builder setEnableencrypt(boolean z) {
            this.enableEncrypt = z;
            return this;
        }

        public Builder setEncryptKey(String str) {
            this.encryptKey = str;
            return this;
        }

        public Builder setUserCustomHttpDNSHost(String str) {
            this.mUserCustomHttpNDSHost = str;
            return this;
        }

        public Builder enableStrictIdle(boolean z) {
            this.mEnableStrictIdle = z;
            return this;
        }

        public Builder setSimpleConfig(boolean z, boolean z2) {
            isTestEnv(z);
            setTestPort(4434);
            this.mOversea = z2;
            if (z2) {
                if (z) {
                    setShortLinkHost("testws.master.live");
                    setLongLinkHost("testws.master.live");
                    setQuicLinkHost("testws.master.live");
                } else {
                    setShortLinkHost("wsapi.master.live");
                    setLongLinkHost("wsapi.master.live");
                    setQuicLinkHost("wsapi.master.live");
                }
                setTestIP("52.66.11.39");
                setTestIPv6("");
            } else {
                if (z) {
                    setShortLinkHost("testws.va.huya.com");
                    setLongLinkHost("testws.va.huya.com");
                    setQuicLinkHost("testws.va.huya.com");
                } else {
                    setLongLinkHost(Constants.CHINA_NEW_REQUEST_LONG_HOST);
                    setShortLinkHost("cdn.wup.huya.com");
                    setQuicLinkHost(Constants.CHINA_QUIC_HOST);
                }
                setTestIP("14.116.175.151");
                setTestIPv6("");
            }
            return this;
        }

        public HalConfig build() {
            String str = this.mUserSetTestHost;
            if (str != null && !str.isEmpty()) {
                setShortLinkHost(this.mUserSetTestHost);
                setLongLinkHost(this.mUserSetTestHost);
                setQuicLinkHost(this.mUserSetTestHost);
            }
            return new HalConfig(this);
        }
    }

    public Context getContext() {
        return this.mContext;
    }

    public boolean isTest() {
        return this.mTest;
    }

    public boolean isDebug() {
        return this.mDebug;
    }

    public String getTestIP() {
        return this.mTestIP;
    }

    public String getmTestIPv6() {
        return this.mTestIPv6;
    }

    public int getTestPort() {
        return this.mTestPort;
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

    public String getGuid() {
        return this.mGuid;
    }

    public HySignalGuidListener getGuidListener() {
        return this.mGuidListener;
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

    public Map<String, String> getExperimentConfig() {
        return this.mExperimentConfig;
    }

    public Map<String, String> getPushFrequencyConfig() {
        return this.mPushFrequencyConfig;
    }

    public boolean isUnableLostMsg() {
        return this.mUnableLostMsg;
    }

    public Set<Long> getRegisterMsgUris() {
        return this.mRegisterMsgUris;
    }

    public long getMsgMaxCount() {
        return this.mMsgMaxCount;
    }

    public long getGroupMsgCount() {
        return this.mGroupMsgCount;
    }

    public boolean isNeedVerifyToken() {
        return this.mNeedVerifyToken;
    }

    public boolean isAutoRegisterUid() {
        return this.mAutoRegisterUid;
    }

    public boolean isEnableP2PPush() {
        return this.mEnableP2PPush;
    }

    public P2pPushDelegate getP2PPushDelegate() {
        return this.mP2PPushDelegate;
    }

    public String getCacheDir() {
        return this.mCacheDir;
    }

    public HalReportListener getReportListener() {
        return this.mReportListener;
    }

    public HalUserInfo getUserInfo() {
        return this.userInfo;
    }

    public Map<String, String> getDynamicConfig() {
        return this.mDynamicConfig;
    }

    public boolean getEnableEncrypt() {
        return this.enableEncrypt;
    }

    public String getEncryptKey() {
        return this.encryptKey;
    }
}
