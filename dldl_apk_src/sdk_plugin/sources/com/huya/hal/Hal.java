package com.huya.hal;

import com.huya.hyhttpdns.dns.HttpDns;
import com.huya.hyhttpdns.dns.HttpDnsBiz;
import com.huya.hyhttpdns.dns.HttpDnsConfig;
import com.huya.hyhttpdns.dns.HttpDnsLog;
import com.huya.hyhttpdns.dns.HttpDnsUserInfo;
import com.huya.hysignal.bizreq.HyTimeSyncClient;
import com.huya.hysignal.core.HysignalDns;
import com.huya.hysignal.util.HySignalLog;
import com.huya.hysignal.wrapper.HySignalWrapper;
import com.huya.hysignal.wrapper.SignalWrapConfig;
import com.huya.hysignal.wrapper.SignalWrapUserInfo;
import com.huya.hysignal.wrapper.business.BaseBiz;
import com.huya.hysignal.wrapper.business.LiveLaunchBiz;
import com.huya.hysignal.wrapper.business.NetUtilBiz;
import com.huya.hysignal.wrapper.business.PushBiz;
import com.huya.hysignal.wrapper.business.PushControlBiz;
import com.huya.hysignal.wrapper.business.RegisterBiz;
import com.huya.hysignal.wrapper.business.TimeSyncBiz;
import com.huya.hysignal.wrapper.business.UserInfoBiz;
import com.huya.hysignal.wrapper.business.VerifyBiz;
import com.huya.hysignal.wrapper.listener.RemoveIpListener;
import java.util.ArrayList;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public final class Hal {
    public static HalConfig mInitConfig;
    private static boolean sInit;

    public static synchronized void init(HalConfig halConfig) {
        if (sInit) {
            throw new UnsupportedOperationException("hal can't init more than once");
        }
        if (halConfig == null) {
            HySignalLog.error("init hal with null config");
            return;
        }
        mInitConfig = halConfig;
        HalReportHelper.getInstance().init(halConfig.mReportListener);
        initHttpDns(halConfig);
        sInit = initHySignal(halConfig);
    }

    public static synchronized boolean isInit() {
        if (!sInit) {
            return false;
        }
        return HySignalWrapper.getInstance().isInited();
    }

    public static boolean isOverSeaEnv() {
        HalConfig halConfig = mInitConfig;
        if (halConfig == null) {
            return false;
        }
        return halConfig.mOverSea;
    }

    @Deprecated
    public static void updateUserInfo(HalUserInfo halUserInfo) {
        if (!sInit || halUserInfo == null) {
            HySignalLog.info("update hal user info not init or change, return");
            return;
        }
        if (halUserInfo.uid >= 0) {
            HttpDnsUserInfo httpDnsUserInfo = new HttpDnsUserInfo();
            httpDnsUserInfo.setUid(halUserInfo.uid);
            HttpDns.getInstance().updateUserInfo(httpDnsUserInfo);
        }
        HySignalWrapper.getInstance().updateUserInfo(new SignalWrapUserInfo.Builder().setLogin(halUserInfo.isLogin).setUid(halUserInfo.uid).setToken(halUserInfo.token).setTokenType(halUserInfo.tokenType).build());
    }

    public static BaseBiz getBaseBiz() {
        return HySignalWrapper.getInstance();
    }

    public static UserInfoBiz getUserInfoBiz() {
        return HySignalWrapper.getInstance();
    }

    public static PushBiz getPushBiz() {
        return HySignalWrapper.getInstance();
    }

    public static PushControlBiz getPushControlBiz() {
        return HySignalWrapper.getInstance();
    }

    public static RegisterBiz getRegisterBiz() {
        return HySignalWrapper.getInstance();
    }

    public static VerifyBiz getVerifyBiz() {
        return HySignalWrapper.getInstance();
    }

    public static LiveLaunchBiz getLiveLaunchBiz() {
        return HySignalWrapper.getInstance();
    }

    public static HttpDnsBiz getHttpDnsBiz() {
        return HttpDns.getInstance();
    }

    public static TimeSyncBiz getTimeSyncBiz() {
        return HyTimeSyncClient.getInstance();
    }

    public static NetUtilBiz getNetUtilBiz() {
        return HySignalWrapper.getInstance();
    }

    private static void initHttpDns(HalConfig halConfig) {
        HttpDnsUserInfo httpDnsUserInfo = new HttpDnsUserInfo();
        if (halConfig.userInfo != null) {
            httpDnsUserInfo.setUid(halConfig.userInfo.uid);
        }
        HttpDnsConfig.Builder ua = new HttpDnsConfig.Builder(halConfig.mContext).setTestEnv(halConfig.mTest).isOverSea(halConfig.mOverSea).setCustomHost(halConfig.mUserCustomHttpDNSHost).setCacheDir(halConfig.mCacheDir).setReportListener(HalReportHelper.getInstance()).setUserInfo(httpDnsUserInfo).setHttpDnsLog(new HttpDnsLog() { // from class: com.huya.hal.Hal.1
            @Override // com.huya.hyhttpdns.dns.HttpDnsLog
            public void debug(String str, String str2) {
                HySignalLog.debug(str, str2);
            }

            @Override // com.huya.hyhttpdns.dns.HttpDnsLog
            public void debug(String str, String str2, Object... objArr) {
                HySignalLog.debug(str, str2, objArr);
            }

            @Override // com.huya.hyhttpdns.dns.HttpDnsLog
            public void info(String str, String str2) {
                HySignalLog.info(str, str2);
            }

            @Override // com.huya.hyhttpdns.dns.HttpDnsLog
            public void info(String str, String str2, Object... objArr) {
                HySignalLog.info(str, str2, objArr);
            }

            @Override // com.huya.hyhttpdns.dns.HttpDnsLog
            public void warn(String str, String str2) {
                HySignalLog.warn(str, str2);
            }

            @Override // com.huya.hyhttpdns.dns.HttpDnsLog
            public void warn(String str, String str2, Object... objArr) {
                HySignalLog.warn(str, str2, objArr);
            }

            @Override // com.huya.hyhttpdns.dns.HttpDnsLog
            public void error(String str, String str2) {
                HySignalLog.error(str, str2);
            }

            @Override // com.huya.hyhttpdns.dns.HttpDnsLog
            public void error(String str, String str2, Object... objArr) {
                HySignalLog.error(str, str2, objArr);
            }
        }).setAppSrc(halConfig.appSrc).setUa(halConfig.ua);
        if (halConfig.mUserSetTestHost != null && !halConfig.mUserSetTestHost.isEmpty()) {
            ua.setTestUrl(halConfig.mUserSetTestHost);
        }
        HttpDns.getInstance().init(ua.build());
    }

    private static boolean initHySignal(HalConfig halConfig) {
        HysignalDns hysignalDns = new HysignalDns() { // from class: com.huya.hal.Hal.2
            @Override // com.huya.hysignal.core.HysignalDns
            public String[] onDns(String str, long j) {
                return HttpDns.getInstance().getHostByName(str, j, true);
            }
        };
        return HySignalWrapper.getInstance().init(new SignalWrapConfig.Builder(halConfig.mContext).isDebug(halConfig.mDebug).isTestEnv(halConfig.mTest).setTestIP(halConfig.mTestIP).setTestPort(halConfig.mTestPort).setLongLinkHost(halConfig.mLongLinkHost).setQuicLinkHost(halConfig.mQuicLinkHost).setShortLinkHost(halConfig.mShortLinkHost).setGuid(halConfig.mGuid).setGuidListener(halConfig.mGuidListener).setHySignalDns(hysignalDns).setEnableProxy(halConfig.mEnableProxy, halConfig.mProxyIP, halConfig.mProxyPort).setAutoUpdateInterval(halConfig.mAutoUpdateInterval).setReportListener(HalReportHelper.getInstance()).setUserInfo(halConfig.userInfo != null ? new SignalWrapUserInfo.Builder().setLogin(halConfig.userInfo.isLogin).setUid(halConfig.userInfo.uid).setToken(halConfig.userInfo.token).setTokenType(halConfig.userInfo.tokenType).build() : null).setExperimentConfig(halConfig.mExperimentConfig).setPushFrequencyConfig(halConfig.mPushFrequencyConfig).isUnableLostMsg(halConfig.mUnableLostMsg).setUnableLostMsgUris(halConfig.mRegisterMsgUris).setMsgMaxCount(halConfig.mMsgMaxCount).setGroupMsgMaxCount(halConfig.mGroupMsgCount).isNeedVerifyToken(halConfig.mNeedVerifyToken).isAutoRegisterUid(halConfig.mAutoRegisterUid).isEnableP2PPush(halConfig.mEnableP2PPush, halConfig.mP2PPushDelegate).setRemoveIpListener(new RemoveIpListener() { // from class: com.huya.hal.Hal.3
            @Override // com.huya.hysignal.wrapper.listener.RemoveIpListener
            public boolean onRemoveIps(ArrayList<String> arrayList) {
                return HttpDns.getInstance().removeIps(arrayList);
            }
        }).setAppSrc(halConfig.appSrc).setUa(halConfig.ua).setDeviceId(halConfig.deviceId).setImei(halConfig.IMEI).setDynamicConfig(halConfig.mDynamicConfig).setEnableEncrypt(halConfig.enableEncrypt).setEncryptKey(halConfig.encryptKey).setEnableStrictIdle(halConfig.mEnableStrictIdle).build());
    }
}
