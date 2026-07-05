package com.huya.hysignal.wrapper;

import android.content.Context;
import com.huya.hysignal.core.HySignalConfig;
import com.huya.hysignal.core.HysignalDns;
import com.huya.hysignal.listener.HySignalGuidListener;
import com.huya.hysignal.listener.HySignalReportListener;
import com.huya.hysignal.wrapper.SignalWrapUserInfo;
import com.huya.hysignal.wrapper.listener.RemoveIpListener;
import com.sqwan.bugless.util.FileUtil;
import com.tencent.bugly.Bugly;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class SignalWrapConfig {
    final String mAppSrc;
    final boolean mAutoRegisterUid;
    final long mAutoUpdateInterval;
    final Context mContext;
    final boolean mDebug;
    final String mDebugIP;
    final int mDebugPort;
    final String mDeviceId;
    final Map<String, String> mDynamicConfig;
    final boolean mEnableEncrypt;
    final Map<String, Boolean> mEnableP2PGroupsSwitch;
    final boolean mEnableP2PPush;
    final boolean mEnableProxy;
    final boolean mEnableStrictIdle;
    final String mEncryptKey;
    final Map<String, String> mExperimentConfig;
    final String mGiud;
    final long mGroupMsgCount;
    final HySignalGuidListener mGuidListener;
    final HysignalDns mHttpDns;
    final String mIMEI;
    final String mLongLinkHost;
    final long mMsgMaxCount;
    final boolean mNeedVerifyToken;
    final P2pPushDelegate mP2PPushDelegate;
    final String mProxyIP;
    final int mProxyPort;
    final Map<String, String> mPushFrequencyConfig;
    final String mQuicLinkHost;
    final Set<Long> mRegisterMsgUris;
    final RemoveIpListener mRemoveIpListener;
    final HySignalReportListener mReportListener;
    final String mShortLinkHost;
    final SignalWrapUserInfo mSignalWrapUserInfo;
    final boolean mTest;
    final String mUa;
    final boolean mUnableLostMsg;

    private SignalWrapConfig(Builder builder) {
        this.mContext = builder.mContext;
        this.mTest = builder.mTest;
        this.mDebug = builder.mDebug;
        this.mDebugIP = builder.mDebugIP;
        this.mDebugPort = builder.mDebugPort;
        this.mLongLinkHost = builder.mLongLinkHost;
        this.mQuicLinkHost = builder.mQuicLinkHost;
        this.mShortLinkHost = builder.mShortLinkHost;
        this.mGiud = builder.mGuid;
        this.mGuidListener = builder.mGuidListener;
        this.mHttpDns = builder.mHttpDns;
        this.mEnableProxy = builder.mEnableProxy;
        this.mProxyIP = builder.mProxyIp;
        this.mProxyPort = builder.mProxyPort;
        this.mAutoUpdateInterval = builder.mAutoUpdateInterval;
        this.mReportListener = builder.mReportListener;
        this.mSignalWrapUserInfo = builder.mSignalWrapUserInfo;
        this.mExperimentConfig = builder.mExperimentConfig;
        this.mDynamicConfig = builder.mDynamicConfig;
        this.mPushFrequencyConfig = builder.mPushFrequencyConfig;
        this.mUnableLostMsg = builder.mUnableLostMsg;
        this.mRegisterMsgUris = builder.mRegisterMsgUris;
        this.mMsgMaxCount = builder.mMsgMaxCount;
        this.mGroupMsgCount = builder.mGroupMsgCount;
        this.mNeedVerifyToken = builder.mNeedVerifyToken;
        this.mAutoRegisterUid = builder.mAutoRegisterUid;
        this.mEnableP2PPush = builder.mEnableP2PPush;
        this.mP2PPushDelegate = builder.mP2PPushDelegate;
        this.mEnableP2PGroupsSwitch = builder.mEnableP2PGroupsSwitch;
        this.mRemoveIpListener = builder.removeIpListener;
        this.mUa = builder.mUa;
        this.mAppSrc = builder.mAppSrc;
        this.mDeviceId = builder.mDeviceId;
        this.mIMEI = builder.mIMEI;
        this.mEnableEncrypt = builder.mEnableEncrypt;
        this.mEncryptKey = builder.mEncryptKey;
        this.mEnableStrictIdle = builder.mEnableStrictIdle;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("mTest: ");
        sb.append(this.mTest ? "true" : Bugly.SDK_IS_DEV);
        sb.append(", mDebug: ");
        sb.append(this.mDebug ? "true" : Bugly.SDK_IS_DEV);
        sb.append(", debugIp: ");
        sb.append(this.mDebugIP);
        sb.append(", debugPort: ");
        sb.append(this.mDebugPort);
        sb.append(", longLinkHost: ");
        sb.append(this.mLongLinkHost);
        sb.append(", shortLinkHost: ");
        sb.append(this.mShortLinkHost);
        sb.append(", quicLinkHost: ");
        sb.append(this.mQuicLinkHost);
        sb.append(", guid: ");
        sb.append(this.mGiud);
        sb.append(", guidListener: ");
        HySignalGuidListener hySignalGuidListener = this.mGuidListener;
        String str = AbstractJsonLexerKt.NULL;
        sb.append(hySignalGuidListener == null ? AbstractJsonLexerKt.NULL : "object");
        sb.append(", httpDns: ");
        sb.append(this.mHttpDns == null ? AbstractJsonLexerKt.NULL : "object");
        sb.append(", enableProxy: ");
        sb.append(this.mEnableProxy ? "true" : Bugly.SDK_IS_DEV);
        sb.append(", proxyIp: ");
        sb.append(this.mProxyIP);
        sb.append(", proxyPort: ");
        sb.append(this.mProxyPort);
        sb.append(", autoUpdateInterval: ");
        sb.append(this.mAutoUpdateInterval);
        sb.append(", reportListener: ");
        if (this.mReportListener != null) {
            str = "object";
        }
        sb.append(str);
        sb.append(", userInfo: ");
        sb.append(this.mSignalWrapUserInfo.toString());
        sb.append(", experimentConfig: ");
        sb.append(getExperimentString(this.mExperimentConfig));
        sb.append(", pushFrequencyConfig: [");
        sb.append(getExperimentString(this.mPushFrequencyConfig));
        sb.append("], unableLostMsg: ");
        sb.append(this.mUnableLostMsg ? "true" : Bugly.SDK_IS_DEV);
        sb.append(", unableLostUris: ");
        sb.append(this.mRegisterMsgUris);
        sb.append(", msgMaxCount: ");
        sb.append(this.mMsgMaxCount);
        sb.append(", groupMsgMaxCount: ");
        sb.append(this.mGroupMsgCount);
        sb.append(", needVerifyToken: ");
        sb.append(this.mNeedVerifyToken);
        sb.append(", mAutoRegisterUid: ");
        sb.append(this.mAutoRegisterUid);
        sb.append(", enableP2PPush: ");
        sb.append(this.mEnableP2PPush);
        sb.append(", mP2PPushDelegate: ");
        sb.append(this.mP2PPushDelegate);
        sb.append(", mRemoveIpListener: ");
        sb.append(this.mRemoveIpListener);
        sb.append(", ua: ");
        sb.append(this.mUa);
        sb.append(", appSrc: ");
        sb.append(this.mAppSrc);
        sb.append(", deviceID: ");
        sb.append(this.mDeviceId);
        sb.append(", IMEI: ");
        sb.append(this.mIMEI);
        return sb.toString();
    }

    HySignalConfig buildHySignalConfig() {
        return new HySignalConfig.Builder(this.mContext).isDebugEnv(this.mDebug).isTestEnv(this.mTest).isDebugEnv(this.mDebug).setDebugIP(this.mDebugIP).setDebugPort(this.mDebugPort).setShortLinkHost(this.mShortLinkHost).setQuicLinkHost(this.mQuicLinkHost).setLongLinkHost(this.mLongLinkHost).setHySignalDns(this.mHttpDns).setExistGuid(this.mGiud).setGuidListener(this.mGuidListener).setEnableProxy(this.mEnableProxy, this.mProxyIP, this.mProxyPort).setAutoUpdateInterval(this.mAutoUpdateInterval).setReportListener(this.mReportListener).setUa(this.mUa).setAppSrc(this.mAppSrc).setDeviceId(this.mDeviceId).setImei(this.mIMEI).setUid(this.mSignalWrapUserInfo.uid).setDynamicConfig(this.mDynamicConfig).setExperiment(getExperimentString(this.mExperimentConfig)).setEnableEncrypt(this.mEnableEncrypt).setEncryptKey(this.mEncryptKey).setEnableIdleStrict(this.mEnableStrictIdle).build();
    }

    public static class Builder {
        String mAppSrc;
        long mAutoUpdateInterval;
        Context mContext;
        boolean mDebug;
        String mDebugIP;
        int mDebugPort;
        String mDeviceId;
        Map<String, String> mDynamicConfig;
        boolean mEnableProxy;
        HysignalDns mHttpDns;
        String mIMEI;
        String mLongLinkHost;
        String mProxyIp;
        int mProxyPort;
        String mQuicLinkHost;
        String mShortLinkHost;
        boolean mTest;
        String mUa;
        String mGuid = null;
        HySignalGuidListener mGuidListener = null;
        HySignalReportListener mReportListener = null;
        SignalWrapUserInfo mSignalWrapUserInfo = new SignalWrapUserInfo.Builder().defaultBuild();
        Map<String, String> mExperimentConfig = null;
        Map<String, String> mPushFrequencyConfig = null;
        boolean mUnableLostMsg = false;
        Set<Long> mRegisterMsgUris = null;
        long mMsgMaxCount = 1500;
        long mGroupMsgCount = 36000;
        boolean mNeedVerifyToken = false;
        boolean mAutoRegisterUid = true;
        boolean mEnableP2PPush = true;
        P2pPushDelegate mP2PPushDelegate = null;
        RemoveIpListener removeIpListener = null;
        String mEncryptKey = "";
        boolean mEnableEncrypt = false;
        boolean mEnableStrictIdle = false;
        Map<String, Boolean> mEnableP2PGroupsSwitch = null;

        public Builder(Context context) {
            this.mContext = context;
            HySignalConfig hySignalConfigBuild = new HySignalConfig.Builder(context).build();
            this.mTest = hySignalConfigBuild.isTest();
            this.mDebug = hySignalConfigBuild.isDebug();
            this.mDebugIP = hySignalConfigBuild.getDebugIP();
            this.mDebugPort = hySignalConfigBuild.getDebugPort();
            this.mLongLinkHost = hySignalConfigBuild.getLongLinkHost();
            this.mQuicLinkHost = hySignalConfigBuild.getQuicLinkHost();
            this.mShortLinkHost = hySignalConfigBuild.getShortLinkHost();
            this.mHttpDns = hySignalConfigBuild.getHttpDns();
            this.mEnableProxy = hySignalConfigBuild.isEnableProxy();
            this.mProxyIp = hySignalConfigBuild.getProxyIP();
            this.mProxyPort = hySignalConfigBuild.getProxyPort();
            this.mAutoUpdateInterval = hySignalConfigBuild.getAutoUpdateInterval();
            this.mUa = hySignalConfigBuild.getUa();
            this.mDeviceId = hySignalConfigBuild.getDeviceId();
            this.mAppSrc = hySignalConfigBuild.getAppSrc();
            this.mIMEI = hySignalConfigBuild.getIMEI();
            this.mDynamicConfig = hySignalConfigBuild.getDynamicConfig();
        }

        public Builder setUserInfo(SignalWrapUserInfo signalWrapUserInfo) {
            this.mSignalWrapUserInfo = signalWrapUserInfo;
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

        public Builder isNeedVerifyToken(boolean z) {
            this.mNeedVerifyToken = z;
            return this;
        }

        public Builder isAutoRegisterUid(boolean z) {
            this.mAutoRegisterUid = z;
            return this;
        }

        public Builder isEnableP2PPush(boolean z, P2pPushDelegate p2pPushDelegate) {
            if (z && p2pPushDelegate == null) {
                return this;
            }
            this.mEnableP2PPush = z;
            this.mP2PPushDelegate = p2pPushDelegate;
            return this;
        }

        public Builder setP2PEnableSwitch(Map<String, Boolean> map) {
            this.mEnableP2PGroupsSwitch = map;
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
            this.mDebugIP = str;
            return this;
        }

        public Builder setTestPort(int i) {
            this.mDebugPort = i;
            return this;
        }

        public Builder setLongLinkHost(String str) {
            this.mLongLinkHost = str;
            return this;
        }

        public Builder setQuicLinkHost(String str) {
            this.mQuicLinkHost = str;
            return this;
        }

        public Builder setShortLinkHost(String str) {
            this.mShortLinkHost = str;
            return this;
        }

        public Builder setHySignalDns(HysignalDns hysignalDns) {
            this.mHttpDns = hysignalDns;
            return this;
        }

        public Builder setEnableProxy(boolean z, String str, int i) {
            this.mEnableProxy = z;
            this.mProxyIp = str;
            this.mProxyPort = i;
            return this;
        }

        public Builder setAutoUpdateInterval(long j) {
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

        public Builder setRemoveIpListener(RemoveIpListener removeIpListener) {
            this.removeIpListener = removeIpListener;
            return this;
        }

        public Builder setReportListener(HySignalReportListener hySignalReportListener) {
            this.mReportListener = hySignalReportListener;
            return this;
        }

        public Builder setAppSrc(String str) {
            this.mAppSrc = str;
            return this;
        }

        public Builder setUa(String str) {
            this.mUa = str;
            return this;
        }

        public Builder setDeviceId(String str) {
            this.mDeviceId = str;
            return this;
        }

        public Builder setImei(String str) {
            this.mIMEI = str;
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

        public Builder setEnableStrictIdle(boolean z) {
            this.mEnableStrictIdle = z;
            return this;
        }

        public SignalWrapConfig build() {
            return new SignalWrapConfig(this);
        }
    }

    private String getExperimentString(Map<String, String> map) {
        if (map == null || map.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (Map.Entry entry : new TreeMap(map).entrySet()) {
            if (sb.length() > 0) {
                sb.append(",");
            }
            sb.append((String) entry.getKey());
            sb.append(FileUtil.FILE_EXTENSION_SEPARATOR);
            sb.append((String) entry.getValue());
        }
        return sb.toString();
    }

    public boolean isTest() {
        return this.mTest;
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
        return this.mHttpDns;
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

    public SignalWrapUserInfo getSignalWrapUserInfo() {
        return this.mSignalWrapUserInfo;
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

    public RemoveIpListener getRemoveIpListener() {
        return this.mRemoveIpListener;
    }

    public String getEncryptKey() {
        return this.mEncryptKey;
    }

    public boolean getEnableEncrypt() {
        return this.mEnableEncrypt;
    }
}
