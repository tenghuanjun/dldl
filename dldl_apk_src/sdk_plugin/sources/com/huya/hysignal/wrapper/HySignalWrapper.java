package com.huya.hysignal.wrapper;

import android.content.Context;
import android.content.IntentFilter;
import android.os.SystemClock;
import android.text.TextUtils;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceParser;
import com.huya.hysignal.bizreq.HyTimeSyncClient;
import com.huya.hysignal.core.Call;
import com.huya.hysignal.core.Callback;
import com.huya.hysignal.core.HySignalClient;
import com.huya.hysignal.core.HySignalError;
import com.huya.hysignal.core.Response;
import com.huya.hysignal.jce.UserId;
import com.huya.hysignal.jce.WSEnterP2P;
import com.huya.hysignal.jce.WSExitP2P;
import com.huya.hysignal.jce.WSP2PCloseNotify;
import com.huya.hysignal.jce.WSP2POpenNotify;
import com.huya.hysignal.jce.WSPushMessage;
import com.huya.hysignal.jce.WSRedirect;
import com.huya.hysignal.jce.WSRegisterGroupReq;
import com.huya.hysignal.jce.WSRegisterGroupRsp;
import com.huya.hysignal.jce.WSSyncGroupReq;
import com.huya.hysignal.jce.WSSyncGroupRsp;
import com.huya.hysignal.jce.WSUNVerifyReq;
import com.huya.hysignal.jce.WSUNVerifyRsp;
import com.huya.hysignal.jce.WSUnRegisterGroupReq;
import com.huya.hysignal.jce.WSUnRegisterGroupRsp;
import com.huya.hysignal.jce.WSUpdateUserExpsReq;
import com.huya.hysignal.jce.WSUpdateUserExpsRsp;
import com.huya.hysignal.jce.WSVerifyHuyaTokenReq;
import com.huya.hysignal.jce.WSVerifyHuyaTokenRsp;
import com.huya.hysignal.listener.HySignalGuidListener;
import com.huya.hysignal.util.FieldsCache;
import com.huya.hysignal.util.HySignalLog;
import com.huya.hysignal.util.ThreadManager;
import com.huya.hysignal.wrapper.SignalWrapUserInfo;
import com.huya.hysignal.wrapper.business.BaseBiz;
import com.huya.hysignal.wrapper.business.LiveLaunchBiz;
import com.huya.hysignal.wrapper.business.NetUtilBiz;
import com.huya.hysignal.wrapper.business.PushControlBiz;
import com.huya.hysignal.wrapper.business.RegisterBiz;
import com.huya.hysignal.wrapper.business.UserInfoBiz;
import com.huya.hysignal.wrapper.business.VerifyBiz;
import com.huya.hysignal.wrapper.listener.HySignalVerifyBizListener;
import com.huya.hysignal.wrapper.listener.HySignalVerifyListenerV2;
import com.huya.hysignal.wrapper.listener.P2pPushListener;
import com.huya.hysignal.wrapper.listener.RegisterLiveGroupListener;
import com.huya.hysignal.wrapper.listener.RegisterPushMsgListener;
import com.huya.hysignal.wrapper.listener.RemoveIpListener;
import com.huya.hysignal.wrapper.listener.UnRegisterPushMsgListener;
import com.huya.mtp.api.MTPApi;
import com.huya.mtp.hyns.Constants;
import com.huya.mtp.hyns.api.NSLongLinkApi;
import com.huya.mtp.hyns.api.NSNetUtilApi;
import com.huya.mtp.hyns.api.Request;
import com.huya.mtp.hyns.report.NSPushReporter;
import com.sqwan.bugless.util.FileUtil;
import com.tencent.bugly.Bugly;
import com.tencent.mars.BaseEvent;
import com.tencent.mars.comm.NetStatusUtil;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArraySet;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public final class HySignalWrapper implements BaseBiz, UserInfoBiz, PushControlBiz, LiveLaunchBiz, RegisterBiz, VerifyBiz, NetUtilBiz, NSLongLinkApi.PushListener {
    private static final String CHAT_GROUP_PREFIX = "chat:";
    private static final int DEFAULT_MAX_FREQUENCY = 200;
    private static final String LIVE_GROUP_PREFIX = "live:";
    private static final String SCHAT_GROUP_PREFIX = "schat:";
    private static final String SLIVE_GROUP_PREFIX = "slive:";
    private static final String TAG = "HySignalWrapper";
    private static boolean sInited;
    private static HySignalWrapper sInstance;
    private PushMsgCache mGroupMsgCache;
    private HistoryMsgHelper mGroupMsgPuller;
    private Map<String, String> mMaxFrequencyMap;
    private PushMsgCache mMsgCache;
    private HistoryMsgHelper mMsgPuller;
    private P2pPushListener mP2pPushListener;
    private long mInitTime = 0;
    private Context mContext = null;
    private RemoveIpListener mRemoveIpListener = null;
    private boolean mEnableP2PPush = true;
    private long mGroupMsgCount = 36000;
    private long mMsgMaxCount = 1500;
    private boolean mNeedVerifyToken = false;
    private String mUa = "";
    private SignalWrapUserInfo mSignalWrapUserInfo = null;
    private boolean mEnableEncrypt = false;
    private Map<Integer, PushFrequencyControlItem> mPushFrequencyCache = new ConcurrentHashMap();
    private List<NSLongLinkApi.PushListener> mPushListeners = new ArrayList();
    private Set<NSLongLinkApi.PushStatListener> mPushStatListeners = new CopyOnWriteArraySet();
    private boolean isOpenSynRegister = false;
    private volatile boolean mRegisterThreadRunning = false;
    private ConcurrentLinkedQueue<Runnable> mWaitRegisterQueue = new ConcurrentLinkedQueue<>();
    private ArrayList<String> mCurrentGroupList = new ArrayList<>();
    private Map<String, String> mCurrentGroupTokenMap = new ConcurrentHashMap();
    private HashSet<String> mWaitInGroupList = new HashSet<>();
    private HashSet<String> mWaitOutGroupList = new HashSet<>();
    private Map<String, String> mWaitInGroupTokenMap = new ConcurrentHashMap();
    private P2pPushDelegate mP2pPushDelegate = null;
    private Map<String, Boolean> mP2pEnterStatusMap = new ConcurrentHashMap();
    private final Map<String, Boolean> mP2PGroupEnableSwitch = new HashMap();
    private Map<String, Long> mPushMsgCountMap = new HashMap();
    private String mLastPrintPushLogTime = "";
    private long mAllPushMsgCount = 0;
    private final long MAX_RECV_PUSH_MSG_BEFORE_LOG = 200;

    private HySignalWrapper() {
    }

    public static synchronized HySignalWrapper getInstance() {
        if (sInstance == null) {
            sInstance = new HySignalWrapper();
        }
        return sInstance;
    }

    @Override // com.huya.hysignal.wrapper.business.BaseBiz
    public synchronized boolean init(SignalWrapConfig signalWrapConfig) {
        if (sInited) {
            HySignalLog.error(TAG, "is inited, return");
            return false;
        }
        if (!checkConfig(signalWrapConfig)) {
            return false;
        }
        this.mInitTime = System.currentTimeMillis();
        FieldsCache.getInstance().init(signalWrapConfig.mContext, signalWrapConfig.mDeviceId, signalWrapConfig.mAppSrc);
        this.mContext = signalWrapConfig.mContext;
        this.mRemoveIpListener = signalWrapConfig.mRemoveIpListener;
        if (signalWrapConfig.mP2PPushDelegate == null) {
            this.mEnableP2PPush = false;
            HySignalLog.error(TAG, "mP2PPushDelegate is null, disable signal P2P");
        }
        this.mP2pPushDelegate = signalWrapConfig.mP2PPushDelegate;
        this.mGroupMsgCount = signalWrapConfig.mGroupMsgCount;
        this.mMsgMaxCount = signalWrapConfig.mMsgMaxCount;
        this.mNeedVerifyToken = signalWrapConfig.mNeedVerifyToken;
        this.mUa = signalWrapConfig.mUa;
        this.mEnableEncrypt = signalWrapConfig.mEnableEncrypt;
        if (signalWrapConfig.mEnableP2PGroupsSwitch != null) {
            this.mP2PGroupEnableSwitch.putAll(signalWrapConfig.mEnableP2PGroupsSwitch);
        }
        Map<String, String> map = signalWrapConfig.mDynamicConfig;
        if (!this.isOpenSynRegister && map != null && map.containsKey(Constants.OPEN_SYN_REGISTER) && "1".equals(map.get(Constants.OPEN_SYN_REGISTER))) {
            this.isOpenSynRegister = true;
        }
        HySignalLog.debug(TAG, "************\nHySignalWrapper init, config: %s ************\n", signalWrapConfig.toString());
        if (signalWrapConfig.mSignalWrapUserInfo != null) {
            this.mSignalWrapUserInfo = signalWrapConfig.mSignalWrapUserInfo;
            FieldsCache.getInstance().setUid(signalWrapConfig.mSignalWrapUserInfo.uid);
        } else {
            this.mSignalWrapUserInfo = new SignalWrapUserInfo.Builder().defaultBuild();
        }
        initMsgPush();
        initMsgQuery(signalWrapConfig.mUnableLostMsg);
        NSPushReporter.getInstance().updateReportMsgIdRatio(signalWrapConfig.mDynamicConfig);
        if (!HySignalClient.getInstance().init(signalWrapConfig.buildHySignalConfig())) {
            HySignalLog.error(TAG, "HySignalClient init failed, return");
            return false;
        }
        HySignalClient.getInstance().addPushListener(this);
        setupP2P();
        sInited = true;
        updateExperimentConfig(signalWrapConfig.mExperimentConfig);
        if (signalWrapConfig.mNeedVerifyToken) {
            verifyTokenIfNeed(null);
        }
        updateFrequencyConfig(signalWrapConfig.mPushFrequencyConfig);
        updateRegisterMsgUriSet(signalWrapConfig.mRegisterMsgUris);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.wifi.WIFI_STATE_CHANGED");
        intentFilter.addAction("android.net.wifi.STATE_CHANGE");
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        if (HySignalClient.getInstance().isMultiConnEnable()) {
            HySignalClient.getInstance().addLinkStatusListener(2, new NSNetUtilApi.LinkStatusListener() { // from class: com.huya.hysignal.wrapper.HySignalWrapper.1
                @Override // com.huya.mtp.hyns.api.NSNetUtilApi.LinkStatusListener
                public void onLinkStateChange(String str, boolean z) {
                    HySignalLog.info(HySignalWrapper.TAG, "default channel link change, name: %s, status: %s", str, Boolean.valueOf(z));
                    if (z) {
                        HySignalWrapper.this.verifyTokenInner(null, true, false, false);
                        HySignalClient.getInstance().updateRemoteUserInfo();
                    }
                }
            });
        }
        HySignalClient.getInstance().addLinkStatusListener(4, new NSNetUtilApi.LinkStatusListener() { // from class: com.huya.hysignal.wrapper.HySignalWrapper.2
            @Override // com.huya.mtp.hyns.api.NSNetUtilApi.LinkStatusListener
            public void onLinkStateChange(String str, boolean z) {
                HySignalLog.info(HySignalWrapper.TAG, "quic channel link change, name: %s, status: %s", str, Boolean.valueOf(z));
                if (z) {
                    HySignalWrapper.this.verifyTokenInner(null, false, false, true);
                    HySignalClient.getInstance().updateRemoteUserInfo();
                }
            }
        });
        return true;
    }

    @Override // com.huya.hysignal.wrapper.business.BaseBiz
    public synchronized boolean isInited() {
        return sInited;
    }

    @Override // com.huya.hysignal.wrapper.business.BaseBiz
    public Call newCall(Request request) {
        if (!sInited) {
            HySignalLog.error(TAG, "new call need init");
            return null;
        }
        return HySignalClient.getInstance().newCall(request);
    }

    @Override // com.huya.hysignal.wrapper.business.NetUtilBiz
    public synchronized boolean addLinkStatusListener(String str, NSNetUtilApi.LinkStatusListener linkStatusListener) {
        if (!sInited) {
            HySignalLog.error(TAG, "addQuicStatusListener need init");
            return false;
        }
        return HySignalClient.getInstance().addLinkStatusListener(str, linkStatusListener);
    }

    @Override // com.huya.hysignal.wrapper.business.NetUtilBiz
    public synchronized boolean removeLinkStatusListener(String str, NSNetUtilApi.LinkStatusListener linkStatusListener) {
        if (!sInited) {
            HySignalLog.error(TAG, "removeQuicStatusListener need init");
            return false;
        }
        return HySignalClient.getInstance().removeLinkStatusListener(str, linkStatusListener);
    }

    @Override // com.huya.hysignal.wrapper.business.NetUtilBiz
    public boolean isLongLinkConnected(int i) {
        if (!sInited) {
            HySignalLog.error(TAG, "isLongLinkConnected need init");
            return false;
        }
        return HySignalClient.getInstance().isLongLinkConnected(i);
    }

    @Override // com.huya.hysignal.wrapper.business.NetUtilBiz
    public boolean isLongLinkConnected(String str) {
        if (!sInited) {
            HySignalLog.error(TAG, "isLongLinkConnected need init");
            return false;
        }
        return HySignalClient.getInstance().isLongLinkConnected(str);
    }

    @Override // com.huya.hysignal.wrapper.business.PushBiz, com.huya.hysignal.wrapper.business.NetUtilBiz
    public int getLinkStatus() {
        if (!sInited) {
            HySignalLog.error(TAG, "get link status need init");
            return 0;
        }
        int linkStatus = HySignalClient.getInstance().getLinkStatus();
        HySignalLog.info(TAG, "get link status: %d", Integer.valueOf(linkStatus));
        return linkStatus;
    }

    @Override // com.huya.hysignal.wrapper.business.NetUtilBiz
    public int getLinkStatus(int i) {
        if (!sInited) {
            HySignalLog.error(TAG, "get link status need init, channelType=" + i);
            return 0;
        }
        return HySignalClient.getInstance().getLinkStatus(i);
    }

    @Override // com.huya.hysignal.wrapper.business.NetUtilBiz
    public int getLinkStatus(String str) {
        if (!sInited) {
            HySignalLog.error(TAG, "get link status need init, channelName=" + str);
            return 0;
        }
        int linkStatus = HySignalClient.getInstance().getLinkStatus(str);
        HySignalLog.info(TAG, "get link status: %d, channel: %s", Integer.valueOf(linkStatus), str);
        return linkStatus;
    }

    @Override // com.huya.hysignal.wrapper.business.NetUtilBiz
    public NSNetUtilApi.HySignalIPStack getLocalIPStack() {
        if (!sInited) {
            HySignalLog.error(TAG, "getLocalIPStack need init");
            return NSNetUtilApi.HySignalIPStack.None;
        }
        int localIPStack = HySignalClient.getInstance().getLocalIPStack();
        HySignalLog.info(TAG, "getLocalIPStack: %d", Integer.valueOf(localIPStack));
        return NSNetUtilApi.HySignalIPStack.values()[localIPStack];
    }

    @Override // com.huya.hysignal.wrapper.business.PushControlBiz
    public void updateFrequencyConfig(Map<String, String> map) {
        if (!sInited) {
            HySignalLog.error(TAG, "updateFrequencyConfig need init");
            return;
        }
        if (map == null || map.isEmpty()) {
            HySignalLog.error(TAG, "update frequency mConfig, mConfig is null");
            return;
        }
        HySignalLog.info(TAG, "will update frequency mConfig: %s", getExperimentString(map));
        this.mMaxFrequencyMap = map;
        for (Map.Entry<Integer, PushFrequencyControlItem> entry : this.mPushFrequencyCache.entrySet()) {
            entry.getValue().setMaxCount(getMaxFrequencyForUri(entry.getKey().intValue()));
        }
    }

    @Override // com.huya.hysignal.wrapper.business.BaseBiz
    public void updateExperimentConfig(Map<String, String> map) {
        if (!sInited) {
            HySignalLog.error(TAG, "updateExperimentConfig need init");
            return;
        }
        if (map == null || map.isEmpty()) {
            HySignalLog.error(TAG, "update experiment mConfig, mConfig is null");
            return;
        }
        String experimentString = getExperimentString(map);
        HySignalLog.info(TAG, "will update experiment mConfig: %s", experimentString);
        HySignalClient.getInstance().updateExperiment(experimentString);
        requestUpdateExperiment(map);
    }

    @Deprecated
    public void updateUserInfo(SignalWrapUserInfo signalWrapUserInfo) {
        if (!sInited) {
            HySignalLog.error(TAG, "updateUserInfo need init");
            return;
        }
        if (signalWrapUserInfo == null) {
            HySignalLog.error(TAG, "updateUserInfo signalWrapUserInfo is null, return");
            return;
        }
        if (!isUserInfoChange(signalWrapUserInfo)) {
            HySignalLog.info(TAG, "update user info not change, return");
            return;
        }
        HySignalLog.info(TAG, "will update user info, uid:%d,token:%s,tokenType:%d", Long.valueOf(signalWrapUserInfo.uid), signalWrapUserInfo.token, Integer.valueOf(signalWrapUserInfo.tokenType));
        if (signalWrapUserInfo.isLogin == null) {
            HySignalLog.info(TAG, "updateUserInfo isLogin is null");
        } else {
            Object[] objArr = new Object[1];
            objArr[0] = signalWrapUserInfo.isLogin.booleanValue() ? "true" : Bugly.SDK_IS_DEV;
            HySignalLog.info(TAG, "updateUserInfo isLogin: %s", objArr);
        }
        if (FieldsCache.getInstance().setUid(signalWrapUserInfo.uid) && this.mNeedVerifyToken) {
            verifyTokenIfNeed(null);
        }
        this.mMsgPuller.updateUid(signalWrapUserInfo.uid);
        if (signalWrapUserInfo.isLogin != null) {
            this.mMsgPuller.updateIsLogin(signalWrapUserInfo.isLogin.booleanValue());
        }
        this.mGroupMsgPuller.updateUid(signalWrapUserInfo.uid);
        if (signalWrapUserInfo.isLogin != null) {
            this.mGroupMsgPuller.updateIsLogin(signalWrapUserInfo.isLogin.booleanValue());
        }
        this.mSignalWrapUserInfo = signalWrapUserInfo;
        HySignalClient.getInstance().updateUid(signalWrapUserInfo.uid);
    }

    @Override // com.huya.hysignal.wrapper.business.UserInfoBiz
    public void updateUid(long j) {
        if (!sInited) {
            HySignalLog.error(TAG, "update uid need init");
            return;
        }
        if (j < 0) {
            HySignalLog.error(TAG, "update uid<0");
            return;
        }
        HySignalLog.info(TAG, "will update uid: %d", Long.valueOf(j));
        if (FieldsCache.getInstance().setUid(j) && this.mNeedVerifyToken) {
            verifyTokenIfNeed(null);
        }
        this.mMsgPuller.updateUid(j);
        this.mGroupMsgPuller.updateUid(j);
        this.mSignalWrapUserInfo.uid = j;
        HySignalClient.getInstance().updateUid(j);
    }

    @Override // com.huya.hysignal.wrapper.business.UserInfoBiz
    public void updateIsLogin(boolean z) {
        if (!sInited) {
            HySignalLog.error(TAG, "update isLogin need init");
            return;
        }
        Object[] objArr = new Object[1];
        objArr[0] = z ? "true" : Bugly.SDK_IS_DEV;
        HySignalLog.info(TAG, "will update is login: %s", objArr);
        this.mMsgPuller.updateIsLogin(z);
        this.mGroupMsgPuller.updateIsLogin(z);
        this.mSignalWrapUserInfo.isLogin = Boolean.valueOf(z);
    }

    @Override // com.huya.hysignal.wrapper.business.UserInfoBiz
    public void updateToken(String str) {
        if (!sInited) {
            HySignalLog.error(TAG, "update token need init");
        } else if (str != null) {
            HySignalLog.info(TAG, "will update token:%s", str);
            this.mSignalWrapUserInfo.token = str;
        }
    }

    @Override // com.huya.hysignal.wrapper.business.UserInfoBiz
    public void updateTokenType(int i) {
        if (!sInited) {
            HySignalLog.error(TAG, "update token type need init");
        } else if (i >= 0) {
            HySignalLog.info(TAG, "will update token type:%d", Integer.valueOf(i));
            this.mSignalWrapUserInfo.tokenType = i;
        }
    }

    @Override // com.huya.hysignal.wrapper.business.UserInfoBiz
    public boolean updateAppSrc(String str) {
        if (!sInited) {
            HySignalLog.error(TAG, "update app src need init");
            return false;
        }
        if (empty(str)) {
            HySignalLog.error(TAG, "update empty app src");
            return false;
        }
        HySignalLog.info(TAG, "will update appSrc:%s", str);
        return HySignalClient.getInstance().updateAppSrc(str);
    }

    @Override // com.huya.hysignal.wrapper.business.LiveLaunchBiz
    public String getGuid() {
        if (!sInited) {
            HySignalLog.error(TAG, "get guid need init or guid is wrong");
            return "";
        }
        String guid = HySignalClient.getInstance().getGuid();
        HySignalLog.debug(TAG, "get guid: %s", guid);
        return guid;
    }

    @Override // com.huya.hysignal.wrapper.business.LiveLaunchBiz
    public String getClientIp() {
        return HySignalClient.getInstance().getClientIp();
    }

    @Override // com.huya.hysignal.wrapper.business.LiveLaunchBiz
    public boolean addGuidListener(HySignalGuidListener hySignalGuidListener) {
        HySignalLog.info(TAG, "add guid listener");
        return HySignalClient.getInstance().addGuidListener(hySignalGuidListener);
    }

    @Override // com.huya.hysignal.wrapper.business.LiveLaunchBiz
    public boolean removeGuidListener(HySignalGuidListener hySignalGuidListener) {
        HySignalLog.info(TAG, "remove guid listener");
        return HySignalClient.getInstance().removeGuidListener(hySignalGuidListener);
    }

    @Override // com.huya.hysignal.wrapper.business.PushBiz
    public synchronized void addPushListener(NSLongLinkApi.PushListener pushListener) {
        if (!this.mPushListeners.contains(pushListener)) {
            ArrayList arrayList = new ArrayList(this.mPushListeners.size() + 1);
            arrayList.addAll(this.mPushListeners);
            arrayList.add(pushListener);
            this.mPushListeners = arrayList;
            HySignalLog.info(TAG, "add Push listener success, index:%d", Integer.valueOf(arrayList.indexOf(pushListener)));
        }
    }

    @Override // com.huya.hysignal.wrapper.business.PushBiz
    public synchronized void removePushListener(NSLongLinkApi.PushListener pushListener) {
        int iIndexOf = this.mPushListeners.indexOf(pushListener);
        if (iIndexOf != -1) {
            ArrayList arrayList = new ArrayList(this.mPushListeners.size() - 1);
            arrayList.addAll(this.mPushListeners.subList(0, iIndexOf));
            arrayList.addAll(this.mPushListeners.subList(iIndexOf + 1, this.mPushListeners.size()));
            this.mPushListeners = arrayList;
            HySignalLog.info(TAG, "remove push listener, index:%d", Integer.valueOf(iIndexOf));
        }
    }

    @Override // com.huya.hysignal.wrapper.business.PushBiz
    public boolean addPushStatInfoListener(NSLongLinkApi.PushStatListener pushStatListener) {
        return this.mPushStatListeners.add(pushStatListener);
    }

    @Override // com.huya.hysignal.wrapper.business.PushBiz
    public boolean removePushStatInfoListener(NSLongLinkApi.PushStatListener pushStatListener) {
        return this.mPushStatListeners.remove(pushStatListener);
    }

    @Override // com.huya.hysignal.wrapper.business.PushControlBiz
    public void updateMsgMaxCacheCount(long j) {
        if (!sInited) {
            HySignalLog.error(TAG, "updateMsgMaxCacheCount need init");
        } else if (j <= 0) {
            HySignalLog.info(TAG, "msg push count<=0, count: %d", Long.valueOf(j));
        } else {
            HySignalLog.info(TAG, "will update max msg cache count: %d", Long.valueOf(j));
            this.mMsgCache.updateMaxCacheCount(j);
        }
    }

    @Override // com.huya.hysignal.wrapper.business.PushControlBiz
    public void updateGroupMsgMaxCacheCount(long j) {
        if (!sInited) {
            HySignalLog.error(TAG, "updateGroupMsgMaxCacheCount need init");
        } else if (j <= 0) {
            HySignalLog.info(TAG, "msg push count<=0, count: %d", Long.valueOf(j));
        } else {
            HySignalLog.info(TAG, "will update max group msg cache count: %d", Long.valueOf(j));
            this.mGroupMsgCache.updateMaxCacheCount(j);
        }
    }

    @Override // com.huya.hysignal.wrapper.business.PushControlBiz
    public void resetUnableLostMsg(boolean z) {
        if (!sInited) {
            HySignalLog.error(TAG, "resetUnableLostMsg need init NS first");
        }
        this.mMsgPuller.resetUnableLostMsg(z);
        this.mGroupMsgPuller.resetUnableLostMsg(z);
    }

    @Override // com.huya.hysignal.wrapper.business.PushControlBiz
    public void updateRegisterMsgUriSet(Set<Long> set) {
        if (!sInited) {
            HySignalLog.error(TAG, "updateRegisterMsgUriSet need init");
            return;
        }
        if (set == null || set.isEmpty()) {
            HySignalLog.error(TAG, "updateRegisterMsgUriSet is empty, return");
            return;
        }
        StringBuilder sb = new StringBuilder();
        Iterator<Long> it = set.iterator();
        while (it.hasNext()) {
            sb.append(it.next().toString());
            sb.append(",");
        }
        HySignalLog.info(TAG, "will update register msg set: %s", sb);
        this.mMsgPuller.updateRegisterUris(set);
        this.mGroupMsgPuller.updateRegisterUris(set);
    }

    @Override // com.huya.hysignal.wrapper.business.PushControlBiz
    public void updateP2PEnableSwitch(Map<String, Boolean> map) {
        if (map == null) {
            HySignalLog.error(TAG, "updateP2PEnableSwitch empty");
            return;
        }
        synchronized (this.mP2PGroupEnableSwitch) {
            this.mP2PGroupEnableSwitch.clear();
            this.mP2PGroupEnableSwitch.putAll(map);
        }
    }

    @Override // com.huya.hysignal.wrapper.business.VerifyBiz
    public void verifyTokenIfNeed(final HySignalVerifyBizListener hySignalVerifyBizListener) {
        verifyTokenIfNeedV2(new HySignalVerifyListenerV2() { // from class: com.huya.hysignal.wrapper.HySignalWrapper.3
            @Override // com.huya.hysignal.wrapper.listener.HySignalVerifyListenerV2
            public void onResult(boolean z, int i, String str) {
                HySignalWrapper.this.postVerifyResult(hySignalVerifyBizListener, z, str);
            }
        });
    }

    @Override // com.huya.hysignal.wrapper.business.VerifyBiz
    public void verifyTokenIfNeedV2(HySignalVerifyListenerV2 hySignalVerifyListenerV2) {
        verifyTokenInner(hySignalVerifyListenerV2, true, true, true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void verifyTokenInner(HySignalVerifyListenerV2 hySignalVerifyListenerV2, boolean z, boolean z2, boolean z3) {
        HySignalLog.info(TAG, "invoke verifyTokenInner, isReqC:%s, isPushC:%s, isQuicC:%s", Boolean.valueOf(z), Boolean.valueOf(z2), Boolean.valueOf(z3));
        if (!sInited) {
            HySignalLog.error(TAG, "verify need init");
            postVerifyResultV2(hySignalVerifyListenerV2, false, -9, "verify need init NS first");
            return;
        }
        CharSequence guid = FieldsCache.getInstance().getGuid();
        if (empty(guid)) {
            HySignalLog.error(TAG, "VerifyToken, guid is empty, but still request");
        }
        SignalWrapUserInfo signalWrapUserInfo = this.mSignalWrapUserInfo;
        if (signalWrapUserInfo == null) {
            HySignalLog.error(TAG, "VerifyToken, get signalWrapUserInfo is empty, return");
            postVerifyResultV2(hySignalVerifyListenerV2, false, -10, "get signalWrapUserInfo is empty");
            return;
        }
        if (signalWrapUserInfo.uid == 0) {
            HySignalLog.error(TAG, "VerifyToken, signalWrapUserInfo.uid=0, return");
            postVerifyResultV2(hySignalVerifyListenerV2, false, -11, "signalWrapUserInfo.uid=0 ");
            return;
        }
        if (empty(this.mUa)) {
            HySignalLog.error(TAG, "VerifyToken, signalWrapUserInfo.ua is empty, but still request");
        }
        String appSrc = FieldsCache.getInstance().getAppSrc();
        if (empty(appSrc)) {
            HySignalLog.error(TAG, "VerifyToken, signalWrapUserInfo.appsrc is empty, but still request");
        }
        if (empty(signalWrapUserInfo.token)) {
            HySignalLog.error(TAG, "VerifyToken, signalWrapUserInfo.token is empty");
            postVerifyResultV2(hySignalVerifyListenerV2, false, -12, "signalWrapUserInfo.token is empty");
        } else if (!HySignalClient.getInstance().isLongLinkConnected(5)) {
            HySignalLog.error(TAG, "VerifyToken, push longLink is not connected, return");
            postVerifyResultV2(hySignalVerifyListenerV2, false, -13, "longLink is not ready, can't verify");
        } else {
            HySignalLog.info(TAG, "will verify, uid: %d, ua: %s, appsrc: %s, token: %s, tokenType: %d, guid: %s", Long.valueOf(signalWrapUserInfo.uid), this.mUa, appSrc, signalWrapUserInfo.token, Integer.valueOf(signalWrapUserInfo.tokenType), guid);
            ThreadManager.deliverOnRequestThread(new AnonymousClass4(signalWrapUserInfo, appSrc, z2, hySignalVerifyListenerV2, z3, z));
        }
    }

    /* JADX INFO: renamed from: com.huya.hysignal.wrapper.HySignalWrapper$4, reason: invalid class name */
    class AnonymousClass4 implements Runnable {
        final /* synthetic */ String val$appSrc;
        final /* synthetic */ boolean val$isPushChannel;
        final /* synthetic */ boolean val$isQuicChannel;
        final /* synthetic */ boolean val$isReqChannel;
        final /* synthetic */ HySignalVerifyListenerV2 val$listener;
        final /* synthetic */ SignalWrapUserInfo val$signalWrapUserInfo;

        AnonymousClass4(SignalWrapUserInfo signalWrapUserInfo, String str, boolean z, HySignalVerifyListenerV2 hySignalVerifyListenerV2, boolean z2, boolean z3) {
            this.val$signalWrapUserInfo = signalWrapUserInfo;
            this.val$appSrc = str;
            this.val$isPushChannel = z;
            this.val$listener = hySignalVerifyListenerV2;
            this.val$isQuicChannel = z2;
            this.val$isReqChannel = z3;
        }

        @Override // java.lang.Runnable
        public void run() {
            WSVerifyHuyaTokenReq wSVerifyHuyaTokenReq = new WSVerifyHuyaTokenReq();
            UserId userId = new UserId();
            userId.setLUid(this.val$signalWrapUserInfo.uid);
            userId.setSHuYaUA(HySignalWrapper.this.mUa);
            userId.setSToken(this.val$signalWrapUserInfo.token);
            userId.setITokenType(this.val$signalWrapUserInfo.tokenType);
            userId.setSGuid(FieldsCache.getInstance().getGuid());
            wSVerifyHuyaTokenReq.setTId(userId);
            wSVerifyHuyaTokenReq.setSAppSrc(this.val$appSrc);
            wSVerifyHuyaTokenReq.setBAutoRegisterUid(1);
            Request requestBuild = new Request.Builder().cmdId(12).cgi("/cmdid/12").channel(5).retryCount(2).body(wSVerifyHuyaTokenReq.toByteArray()).networkStatusSensitive(true).encrypt(HySignalWrapper.this.mEnableEncrypt).build();
            HySignalLog.info(HySignalWrapper.TAG, "will verify, uid: %d, autoRegisterUid: %d", Long.valueOf(wSVerifyHuyaTokenReq.getTId().getLUid()), Integer.valueOf(wSVerifyHuyaTokenReq.getBAutoRegisterUid()));
            if (this.val$isPushChannel && HySignalClient.getInstance().isLongLinkConnected(5)) {
                HySignalClient.getInstance().newCall(requestBuild).enqueue(new Callback() { // from class: com.huya.hysignal.wrapper.HySignalWrapper.4.1
                    @Override // com.huya.hysignal.core.Callback
                    public void onResponse(final byte[] bArr, final HySignalError hySignalError) {
                        ThreadManager.deliverOnResponseThread(new Runnable() { // from class: com.huya.hysignal.wrapper.HySignalWrapper.4.1.1
                            @Override // java.lang.Runnable
                            public void run() {
                                String str = "verify failed, net rsp is empty";
                                if (hySignalError.getErrType() != 0) {
                                    HySignalWrapper.this.postVerifyResultV2(AnonymousClass4.this.val$listener, false, -14, "verify failed errorCode= " + hySignalError.getErrCode() + " , errorType: " + hySignalError.getErrType());
                                    str = "verify failed";
                                } else {
                                    WSVerifyHuyaTokenRsp wSVerifyHuyaTokenRsp = (WSVerifyHuyaTokenRsp) JceParser.parseJce(bArr, new WSVerifyHuyaTokenRsp());
                                    if (wSVerifyHuyaTokenRsp == null) {
                                        HySignalWrapper.this.postVerifyResultV2(AnonymousClass4.this.val$listener, false, -15, "verify failed, net rsp is empty");
                                    } else if (wSVerifyHuyaTokenRsp.iValidate == 0) {
                                        HySignalWrapper.this.onVerifyTokenSucceed();
                                        HySignalWrapper.this.postVerifyResultV2(AnonymousClass4.this.val$listener, true, 0, "");
                                        str = "verify success, iValidate=%d";
                                    } else {
                                        str = "verify failed, iValidate=" + wSVerifyHuyaTokenRsp.iValidate;
                                        HySignalWrapper.this.postVerifyResultV2(AnonymousClass4.this.val$listener, false, wSVerifyHuyaTokenRsp.iValidate, "signal req success, verify failed, iValidate=" + wSVerifyHuyaTokenRsp.iValidate);
                                    }
                                }
                                HySignalLog.info(HySignalWrapper.TAG, "push recv verify result: %s, errType:%d, errCode:%d", str, Integer.valueOf(hySignalError.getErrType()), Integer.valueOf(hySignalError.getErrCode()));
                            }
                        });
                    }
                });
            } else {
                HySignalLog.error(HySignalWrapper.TAG, "VerifyToken, push longLink is not connected or not choice, return");
                HySignalWrapper.this.postVerifyResultV2(this.val$listener, false, -13, "longLink is not ready, can't verify");
            }
            WSVerifyHuyaTokenReq wSVerifyHuyaTokenReq2 = new WSVerifyHuyaTokenReq();
            wSVerifyHuyaTokenReq2.setTId(userId);
            wSVerifyHuyaTokenReq2.setSAppSrc(this.val$appSrc);
            wSVerifyHuyaTokenReq2.setBAutoRegisterUid(0);
            if (this.val$isQuicChannel && HySignalClient.getInstance().isLongLinkConnected(4)) {
                HySignalClient.getInstance().newCall(new Request.Builder().fromRequest(requestBuild).body(wSVerifyHuyaTokenReq2.toByteArray()).channel(4).build()).enqueue(new Callback() { // from class: com.huya.hysignal.wrapper.HySignalWrapper.4.2
                    @Override // com.huya.hysignal.core.Callback
                    public void onResponse(byte[] bArr, HySignalError hySignalError) {
                        String str;
                        if (hySignalError.getErrType() != 0) {
                            str = "verify failed";
                        } else {
                            WSVerifyHuyaTokenRsp wSVerifyHuyaTokenRsp = (WSVerifyHuyaTokenRsp) JceParser.parseJce(bArr, new WSVerifyHuyaTokenRsp());
                            if (wSVerifyHuyaTokenRsp == null) {
                                str = "verify failed, net rsp is empty";
                            } else if (wSVerifyHuyaTokenRsp.iValidate != 0) {
                                str = "verify failed, iValidate=" + wSVerifyHuyaTokenRsp.iValidate;
                            } else {
                                str = "verify success, iValidate=%d";
                            }
                        }
                        HySignalLog.info(HySignalWrapper.TAG, " quic recv verify result: %s, errType:%d, errCode:%d", str, Integer.valueOf(hySignalError.getErrType()), Integer.valueOf(hySignalError.getErrCode()));
                    }
                });
            } else {
                HySignalLog.info(HySignalWrapper.TAG, " quic verify failed, not connect or not choice");
            }
            if (this.val$isReqChannel && HySignalClient.getInstance().isMultiConnEnable() && HySignalClient.getInstance().isLongLinkConnected(2)) {
                HySignalClient.getInstance().newCall(new Request.Builder().fromRequest(requestBuild).body(wSVerifyHuyaTokenReq2.toByteArray()).channel(2).build()).enqueue(new Callback() { // from class: com.huya.hysignal.wrapper.HySignalWrapper.4.3
                    @Override // com.huya.hysignal.core.Callback
                    public void onResponse(byte[] bArr, HySignalError hySignalError) {
                        String str;
                        if (hySignalError.getErrType() != 0) {
                            str = "verify failed";
                        } else {
                            WSVerifyHuyaTokenRsp wSVerifyHuyaTokenRsp = (WSVerifyHuyaTokenRsp) JceParser.parseJce(bArr, new WSVerifyHuyaTokenRsp());
                            if (wSVerifyHuyaTokenRsp == null) {
                                str = "verify failed, net rsp is empty";
                            } else if (wSVerifyHuyaTokenRsp.iValidate != 0) {
                                str = "verify failed, iValidate=" + wSVerifyHuyaTokenRsp.iValidate;
                            } else {
                                str = "verify success, iValidate=%d";
                            }
                        }
                        HySignalLog.info(HySignalWrapper.TAG, " long channel recv verify result: %s, errType:%d, errCode:%d", str, Integer.valueOf(hySignalError.getErrType()), Integer.valueOf(hySignalError.getErrCode()));
                    }
                });
            } else {
                HySignalLog.info(HySignalWrapper.TAG, " long channel verify failed, not connect or not choice");
            }
        }
    }

    @Override // com.huya.hysignal.wrapper.business.VerifyBiz
    public void unVerifyTokenIfNeed(HySignalVerifyBizListener hySignalVerifyBizListener) {
        if (!sInited) {
            HySignalLog.error(TAG, "unVerify need init");
            postVerifyResult(hySignalVerifyBizListener, false, "unVerify need init hysignal first");
            return;
        }
        if (empty(FieldsCache.getInstance().getGuid())) {
            HySignalLog.error(TAG, "unVerifyToken, guid is empty, return, but still request");
        }
        SignalWrapUserInfo signalWrapUserInfo = this.mSignalWrapUserInfo;
        if (signalWrapUserInfo == null) {
            HySignalLog.error(TAG, "unVerifyToken, get signalWrapUserInfo is empty, return");
            postVerifyResult(hySignalVerifyBizListener, false, "get signalWrapUserInfo is empty");
            return;
        }
        if (signalWrapUserInfo.uid == 0) {
            HySignalLog.error(TAG, "unVerifyToken, signalWrapUserInfo.uid=0, return");
            postVerifyResult(hySignalVerifyBizListener, false, "signalWrapUserInfo.uid=0");
            return;
        }
        if (empty(this.mUa)) {
            HySignalLog.error(TAG, "unVerifyToken, signalWrapUserInfo.ua is empty, but still request");
        }
        if (empty(signalWrapUserInfo.token)) {
            HySignalLog.error(TAG, "unVerifyToken, signalWrapUserInfo.token is empty");
            postVerifyResult(hySignalVerifyBizListener, false, "signalWrapUserInfo.token is empty");
        } else if (!HySignalClient.getInstance().isLongLinkConnected(5)) {
            HySignalLog.error(TAG, "UnVerifyToken, longLink is not connected");
            postVerifyResult(hySignalVerifyBizListener, false, "longLink is not ready, can't verify");
        } else {
            HySignalLog.info(TAG, "will unVerify token, uid:%d, ua: %s, token: %s", Long.valueOf(signalWrapUserInfo.uid), this.mUa, signalWrapUserInfo.token);
            ThreadManager.deliverOnRequestThread(new AnonymousClass5(signalWrapUserInfo, hySignalVerifyBizListener));
        }
    }

    /* JADX INFO: renamed from: com.huya.hysignal.wrapper.HySignalWrapper$5, reason: invalid class name */
    class AnonymousClass5 implements Runnable {
        final /* synthetic */ HySignalVerifyBizListener val$listener;
        final /* synthetic */ SignalWrapUserInfo val$signalWrapUserInfo;

        AnonymousClass5(SignalWrapUserInfo signalWrapUserInfo, HySignalVerifyBizListener hySignalVerifyBizListener) {
            this.val$signalWrapUserInfo = signalWrapUserInfo;
            this.val$listener = hySignalVerifyBizListener;
        }

        @Override // java.lang.Runnable
        public void run() {
            WSUNVerifyReq wSUNVerifyReq = new WSUNVerifyReq();
            UserId userId = new UserId();
            userId.setLUid(this.val$signalWrapUserInfo.uid);
            userId.setSToken(this.val$signalWrapUserInfo.token);
            userId.setITokenType(this.val$signalWrapUserInfo.tokenType);
            userId.setSHuYaUA(HySignalWrapper.this.mUa);
            userId.setSGuid(FieldsCache.getInstance().getGuid());
            wSUNVerifyReq.setTId(userId);
            Request requestBuild = new Request.Builder().cmdId(14).cgi("/cmdid/14").channel(5).retryCount(2).body(wSUNVerifyReq.toByteArray()).networkStatusSensitive(true).build();
            HySignalLog.info(HySignalWrapper.TAG, "will unVerify, uid: %d", Long.valueOf(wSUNVerifyReq.getTId().getLUid()));
            HySignalClient.getInstance().newCall(requestBuild).enqueue(new Callback() { // from class: com.huya.hysignal.wrapper.HySignalWrapper.5.1
                @Override // com.huya.hysignal.core.Callback
                public void onResponse(final byte[] bArr, final HySignalError hySignalError) {
                    ThreadManager.deliverOnResponseThread(new Runnable() { // from class: com.huya.hysignal.wrapper.HySignalWrapper.5.1.1
                        @Override // java.lang.Runnable
                        public void run() {
                            String str = "unverify failed, net rsp is null";
                            if (hySignalError.getErrType() != 0) {
                                HySignalWrapper.this.postVerifyResult(AnonymousClass5.this.val$listener, false, "verify failed errorCode= " + hySignalError.getErrCode() + " , errorType: " + hySignalError.getErrType());
                                str = "unverify failed";
                            } else {
                                WSUNVerifyRsp wSUNVerifyRsp = (WSUNVerifyRsp) JceParser.parseJce(bArr, new WSUNVerifyRsp());
                                if (wSUNVerifyRsp == null) {
                                    HySignalWrapper.this.postVerifyResult(AnonymousClass5.this.val$listener, false, "unverify failed, net rsp is null");
                                } else if (wSUNVerifyRsp.getSMsg() == null) {
                                    HySignalWrapper.this.postVerifyResult(AnonymousClass5.this.val$listener, false, "signal req success, unverify failed, rsp.msg is empty");
                                    str = "unverify failed, msg=null";
                                } else {
                                    str = "unverify success, msg=" + wSUNVerifyRsp.getSMsg();
                                    HySignalWrapper.this.postVerifyResult(AnonymousClass5.this.val$listener, true, "");
                                }
                            }
                            HySignalLog.info(HySignalWrapper.TAG, "recv unVerify result: %s, errType:%d, errCode:%d", str, Integer.valueOf(hySignalError.getErrType()), Integer.valueOf(hySignalError.getErrCode()));
                        }
                    });
                }
            });
            if (HySignalClient.getInstance().isLongLinkConnected(4)) {
                HySignalClient.getInstance().newCall(new Request.Builder().fromRequest(requestBuild).channel(4).build()).enqueue(new Callback() { // from class: com.huya.hysignal.wrapper.HySignalWrapper.5.2
                    @Override // com.huya.hysignal.core.Callback
                    public void onResponse(byte[] bArr, HySignalError hySignalError) {
                        String str;
                        if (hySignalError.getErrType() != 0) {
                            str = "unverify failed";
                        } else {
                            WSUNVerifyRsp wSUNVerifyRsp = (WSUNVerifyRsp) JceParser.parseJce(bArr, new WSUNVerifyRsp());
                            if (wSUNVerifyRsp == null) {
                                str = "unverify failed, rsp is null";
                            } else if (wSUNVerifyRsp.getSMsg() == null) {
                                str = "unverify failed, rsp msg is null";
                            } else {
                                str = "unverify success, msg= " + wSUNVerifyRsp.getSMsg();
                            }
                        }
                        HySignalLog.info(HySignalWrapper.TAG, " quic recv verify result: %s, errType:%d, errCode:%d", str, Integer.valueOf(hySignalError.getErrType()), Integer.valueOf(hySignalError.getErrCode()));
                    }
                });
            }
            if (HySignalClient.getInstance().isLongLinkConnected(2)) {
                HySignalClient.getInstance().newCall(new Request.Builder().fromRequest(requestBuild).channel(2).build()).enqueue(new Callback() { // from class: com.huya.hysignal.wrapper.HySignalWrapper.5.3
                    @Override // com.huya.hysignal.core.Callback
                    public void onResponse(byte[] bArr, HySignalError hySignalError) {
                        String str;
                        if (hySignalError.getErrType() != 0) {
                            str = "unverify failed";
                        } else {
                            WSUNVerifyRsp wSUNVerifyRsp = (WSUNVerifyRsp) JceParser.parseJce(bArr, new WSUNVerifyRsp());
                            if (wSUNVerifyRsp == null) {
                                str = "unverify failed, rsp is null";
                            } else if (wSUNVerifyRsp.getSMsg() == null) {
                                str = "unverify failed, rsp msg is null";
                            } else {
                                str = "unverify success, msg= " + wSUNVerifyRsp.getSMsg();
                            }
                        }
                        HySignalLog.info(HySignalWrapper.TAG, " push channel recv verify result: %s, errType:%d, errCode:%d", str, Integer.valueOf(hySignalError.getErrType()), Integer.valueOf(hySignalError.getErrCode()));
                    }
                });
            }
        }
    }

    @Override // com.huya.hysignal.wrapper.business.RegisterBiz
    public void registerGroup(final ArrayList<String> arrayList, final RegisterPushMsgListener registerPushMsgListener) {
        if (arrayList == null) {
            HySignalLog.error(TAG, "registerGroup groups is empty");
            registerPushMsgListener.onRegisterFailed(new RegistResultInfo("empty", 1, "register groups is empty"));
        } else if (!sInited) {
            HySignalLog.error(TAG, "registerGroup need init");
            registerPushMsgListener.onRegisterFailed(new RegistResultInfo(arrayList.toString(), 1, "register need init hysignal first"));
        } else {
            HySignalLog.info(TAG, "will register groups: %s", parseArrayListToString(arrayList));
            ThreadManager.deliverOnRequestThread(new Runnable() { // from class: com.huya.hysignal.wrapper.HySignalWrapper.6
                @Override // java.lang.Runnable
                public void run() {
                    Iterator it = arrayList.iterator();
                    while (it.hasNext()) {
                        FieldsCache.getInstance().addGroupPushRegister((String) it.next(), new PushRegister(registerPushMsgListener));
                    }
                    if (!HySignalWrapper.this.isOpenSynRegister) {
                        HySignalWrapper.this.registerGroupForBackUp(arrayList);
                    } else {
                        HySignalWrapper.this.registerGroup(arrayList, new ArrayList(), new HashMap());
                    }
                }
            });
        }
    }

    @Override // com.huya.hysignal.wrapper.business.RegisterBiz
    public void unRegisterGroup(final ArrayList<String> arrayList, final UnRegisterPushMsgListener unRegisterPushMsgListener) {
        if (!this.isOpenSynRegister) {
            unRegisterGroupForBackUp(arrayList, unRegisterPushMsgListener);
        } else if (!sInited) {
            HySignalLog.error(TAG, "unRegisterGroup need init");
        } else {
            ThreadManager.deliverOnRequestThread(new Runnable() { // from class: com.huya.hysignal.wrapper.HySignalWrapper.7
                @Override // java.lang.Runnable
                public void run() {
                    for (String str : arrayList) {
                        FieldsCache.getInstance().removeGroupPushRegister(str);
                        FieldsCache.getInstance().addGroupPushUnRegister(str, new PushUnRegister(unRegisterPushMsgListener));
                    }
                    HySignalWrapper.this.registerGroup(new ArrayList(), arrayList, new HashMap());
                }
            });
            tryExitP2p(arrayList);
        }
    }

    @Override // com.huya.hysignal.wrapper.business.RegisterBiz
    @Deprecated
    public void registerLiveGroup(long j, String str, final RegisterLiveGroupListener registerLiveGroupListener) {
        if (!sInited) {
            HySignalLog.error(TAG, "registerLiveGroup need init");
            return;
        }
        if (j == 0) {
            HySignalLog.error(TAG, "registerGroup pid = %s", Long.valueOf(j));
            if (registerLiveGroupListener != null) {
                registerLiveGroupListener.onJoinFailed();
                return;
            }
            return;
        }
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("live:" + j);
        arrayList.add("chat:" + j);
        if (!empty(str)) {
            arrayList.add("slive:" + j + "-" + str);
            arrayList.add(SCHAT_GROUP_PREFIX + j + "-" + str);
        }
        HySignalLog.info(TAG, "will register live group, presenterUid:%d, password:%s", Long.valueOf(j), str);
        registerGroup(arrayList, new RegisterPushMsgListener() { // from class: com.huya.hysignal.wrapper.HySignalWrapper.8
            @Override // com.huya.hysignal.wrapper.listener.RegisterPushMsgListener
            public void onRegisterSucceed(RegistResultInfo registResultInfo) {
                String groupId = registResultInfo.getGroupId();
                HySignalLog.debug(HySignalWrapper.TAG, "onRegisterSucceed groupId = %s", groupId);
                if (registerLiveGroupListener != null) {
                    if (groupId.startsWith("live:")) {
                        registerLiveGroupListener.onJoinSucceed();
                    }
                    if (groupId.startsWith("slive:")) {
                        registerLiveGroupListener.onJoinPasswordSucceed();
                    }
                }
            }

            @Override // com.huya.hysignal.wrapper.listener.RegisterPushMsgListener
            public void onRegisterFailed(RegistResultInfo registResultInfo) {
                String groupId = registResultInfo.getGroupId();
                HySignalLog.debug(HySignalWrapper.TAG, "onRegisterFailed groupId = %s", groupId);
                if (registerLiveGroupListener != null) {
                    if (groupId.startsWith("live:")) {
                        registerLiveGroupListener.onJoinFailed();
                    }
                    if (groupId.startsWith("slive:")) {
                        registerLiveGroupListener.onJoinPasswordFailed();
                    }
                }
            }
        });
    }

    @Override // com.huya.hysignal.wrapper.business.RegisterBiz
    @Deprecated
    public void unRegisterLiveGroup(final long j, final UnRegisterPushMsgListener unRegisterPushMsgListener) {
        if (!sInited) {
            HySignalLog.error(TAG, "unRegisterLiveGroup need init");
        } else {
            ThreadManager.deliverOnRequestThread(new Runnable() { // from class: com.huya.hysignal.wrapper.HySignalWrapper.9
                @Override // java.lang.Runnable
                public void run() {
                    ArrayList<String> arrayList = new ArrayList<>();
                    for (String str : FieldsCache.getInstance().getGroupPushRegisterMap().keySet()) {
                        if (HySignalWrapper.this.isKeyContainPid(str, j)) {
                            arrayList.add(str);
                        }
                    }
                    if (arrayList.size() == 0) {
                        HySignalLog.debug(HySignalWrapper.TAG, "unRegisterGroup groupIds size is 0");
                    } else {
                        HySignalWrapper.this.unRegisterGroup(arrayList, unRegisterPushMsgListener);
                    }
                }
            });
        }
    }

    @Override // com.huya.hysignal.wrapper.business.RegisterBiz
    public void reRegisterGroupsIfNeed() {
        if (!this.isOpenSynRegister) {
            reRegisterGroupsIfNeedForBackUp();
            return;
        }
        if (!sInited) {
            HySignalLog.error(TAG, "reRegisterGroupsIfNeed need init");
        } else if (FieldsCache.getInstance().isGroupPushMsgRegisterred()) {
            HySignalLog.debug(TAG, "ChannelOrGroupPushMsg is Registered");
            registerGroup(new ArrayList<>());
            this.mGroupMsgPuller.pullHistoryMsg(this);
        }
    }

    @Override // com.huya.hysignal.wrapper.business.RegisterBiz
    public void registerPrivateGroup(String str, String str2) {
        if (this.isOpenSynRegister) {
            if (TextUtils.isEmpty(str)) {
                HySignalLog.error(TAG, "register SecretGroupId is empty, return");
                return;
            }
            HashMap<String, String> map = new HashMap<>();
            map.put(str, str2);
            FieldsCache.getInstance().addGroupPushRegister(str, new PushRegister(new RegisterPushMsgListener() { // from class: com.huya.hysignal.wrapper.HySignalWrapper.10
                @Override // com.huya.hysignal.wrapper.listener.RegisterPushMsgListener
                public void onRegisterFailed(RegistResultInfo registResultInfo) {
                }

                @Override // com.huya.hysignal.wrapper.listener.RegisterPushMsgListener
                public void onRegisterSucceed(RegistResultInfo registResultInfo) {
                }
            }));
            registerGroup(new ArrayList<>(), new ArrayList<>(), map);
        }
    }

    @Override // com.huya.mtp.hyns.api.NSLongLinkApi.PushListener
    public void onLinkStateChange(int i) {
        HySignalLog.info(TAG, "onLinkStateChange, status=%d", Integer.valueOf(i));
        resetMsgQueryWhenLinkStatusChange(i);
        if (i == 4) {
            reRegisterGroupsIfNeed();
            if (this.mNeedVerifyToken) {
                verifyTokenIfNeed(null);
            }
            HySignalClient.getInstance().updateRemoteUserInfo();
        }
        Iterator<NSLongLinkApi.PushListener> it = this.mPushListeners.iterator();
        while (it.hasNext()) {
            it.next().onLinkStateChange(i);
        }
    }

    @Override // com.huya.mtp.hyns.api.NSLongLinkApi.PushListener
    public void onPush(NSLongLinkApi.HySignalMessage hySignalMessage) {
        dispatchPushMsg(hySignalMessage);
    }

    public long getInitTime() {
        return this.mInitTime;
    }

    private void unRegisterGroupForBackUp(ArrayList<String> arrayList, UnRegisterPushMsgListener unRegisterPushMsgListener) {
        if (!sInited) {
            HySignalLog.error(TAG, "unRegisterGroup need init");
        } else if (arrayList == null || arrayList.isEmpty()) {
            HySignalLog.error(TAG, "unRegisterGroup groupId is null.");
        } else {
            ThreadManager.deliverOnRequestThread(new AnonymousClass11(arrayList, unRegisterPushMsgListener));
            tryExitP2p(arrayList);
        }
    }

    /* JADX INFO: renamed from: com.huya.hysignal.wrapper.HySignalWrapper$11, reason: invalid class name */
    class AnonymousClass11 implements Runnable {
        final /* synthetic */ ArrayList val$groupIds;
        final /* synthetic */ UnRegisterPushMsgListener val$listener;

        AnonymousClass11(ArrayList arrayList, UnRegisterPushMsgListener unRegisterPushMsgListener) {
            this.val$groupIds = arrayList;
            this.val$listener = unRegisterPushMsgListener;
        }

        @Override // java.lang.Runnable
        public void run() {
            HySignalLog.info(HySignalWrapper.TAG, "unRegisterGroup groupIds = %s", HySignalWrapper.this.parseArrayListToString(this.val$groupIds));
            if (this.val$groupIds.contains(null)) {
                this.val$groupIds.remove((Object) null);
                HySignalLog.error(HySignalWrapper.TAG, "unRegisterGroupForBackUp groups contains null, remove it");
            }
            for (String str : this.val$groupIds) {
                if (str != null) {
                    FieldsCache.getInstance().removeGroupPushRegister(str);
                }
            }
            WSUnRegisterGroupReq wSUnRegisterGroupReq = new WSUnRegisterGroupReq();
            wSUnRegisterGroupReq.setVGroupId(this.val$groupIds);
            Request requestBuild = new Request.Builder().cmdId(18).cgi("/cmdid/18").channel(5).retryCount(0).body(wSUnRegisterGroupReq.toByteArray()).networkStatusSensitive(true).build();
            if (!HySignalClient.getInstance().isLongLinkConnected(5)) {
                HySignalLog.error(HySignalWrapper.TAG, "unRegisterGroup, longlink is not connected");
                HySignalWrapper.this.notifyUnregisterFailedForBackUp(this.val$groupIds, this.val$listener, "longlink is not ready");
            } else {
                HySignalClient.getInstance().newCall(requestBuild).enqueue(new Callback() { // from class: com.huya.hysignal.wrapper.HySignalWrapper.11.1
                    @Override // com.huya.hysignal.core.Callback
                    public void onResponse(final byte[] bArr, final HySignalError hySignalError) {
                        ThreadManager.deliverOnResponseThread(new Runnable() { // from class: com.huya.hysignal.wrapper.HySignalWrapper.11.1.1
                            @Override // java.lang.Runnable
                            public void run() {
                                if (hySignalError.getErrType() != 0) {
                                    HySignalLog.error(HySignalWrapper.TAG, "unRegisterGroup errType = %d,errCode = %d", Integer.valueOf(hySignalError.getErrType()), Integer.valueOf(hySignalError.getErrCode()));
                                    HySignalWrapper.this.notifyUnregisterFailedForBackUp(AnonymousClass11.this.val$groupIds, AnonymousClass11.this.val$listener, hySignalError.toString());
                                    return;
                                }
                                HySignalWrapper.this.notifyUnregisterSuccess(AnonymousClass11.this.val$listener, AnonymousClass11.this.val$groupIds);
                                HySignalWrapper.this.mCurrentGroupList.removeAll(AnonymousClass11.this.val$groupIds);
                                WSUnRegisterGroupRsp wSUnRegisterGroupRsp = (WSUnRegisterGroupRsp) JceParser.parseJce(bArr, new WSUnRegisterGroupRsp());
                                if (wSUnRegisterGroupRsp == null) {
                                    HySignalLog.error(HySignalWrapper.TAG, "wsUnRegisterGroupRsp is null");
                                    return;
                                }
                                HySignalLog.info(HySignalWrapper.TAG, "wsUnRegisterGroupRsp.iResCode = " + wSUnRegisterGroupRsp.iResCode);
                            }
                        });
                    }
                });
            }
        }
    }

    private void reRegisterGroupsIfNeedForBackUp() {
        if (!sInited) {
            HySignalLog.error(TAG, "reRegisterGroupsIfNeed need init");
        } else if (FieldsCache.getInstance().isGroupPushMsgRegisterred()) {
            HySignalLog.debug(TAG, "ChannelOrGroupPushMsg is Registered");
            registerGroup(FieldsCache.getInstance().getRegisteredGroupIdList());
            this.mGroupMsgPuller.pullHistoryMsg(this);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispatchPushMsg(NSLongLinkApi.HySignalMessage hySignalMessage) {
        NSLongLinkApi.PushStatListener.PushMsgType pushMsgType;
        int iUri = hySignalMessage.getIUri();
        long msgId = hySignalMessage.getMsgId();
        boolean z = !hySignalMessage.isFromQuery();
        boolean zIsFromP2p = hySignalMessage.isFromP2p();
        if (hySignalMessage.getIUri() == 1090001) {
            HySignalLog.info(TAG, "onPush msg uri = %s msgId = %s, isFromPush = %b, isFromP2p = %b", Integer.valueOf(hySignalMessage.getIUri()), Long.valueOf(msgId), Boolean.valueOf(z), Boolean.valueOf(zIsFromP2p));
        } else {
            HySignalLog.debug(TAG, "onPush msg uri = %s msgId = %s, isFromPush = %b, isFromP2p = %b", Integer.valueOf(hySignalMessage.getIUri()), Long.valueOf(msgId), Boolean.valueOf(z), Boolean.valueOf(zIsFromP2p));
        }
        if (!isMsgValid(hySignalMessage)) {
            HySignalLog.error(TAG, "msg is inValid, uri:%d msgid:%d", Integer.valueOf(iUri), Long.valueOf(msgId));
            return;
        }
        if (!checkIsOverFrequency(iUri)) {
            HySignalLog.error(TAG, "msg is over frequency, uri:%d", Integer.valueOf(iUri));
            return;
        }
        increaseMsgCount(hySignalMessage);
        handleP2pPush(hySignalMessage);
        if (iUri == 1025305) {
            removeIps(hySignalMessage);
            return;
        }
        for (NSLongLinkApi.PushListener pushListener : this.mPushListeners) {
            if (pushListener != null) {
                pushListener.onPush(hySignalMessage);
            }
        }
        if (this.mPushStatListeners.isEmpty()) {
            return;
        }
        if (hySignalMessage.isFromP2p()) {
            pushMsgType = NSLongLinkApi.PushStatListener.PushMsgType.P2P_PUSH_TYPE;
        } else if (hySignalMessage.isFromQuery()) {
            pushMsgType = NSLongLinkApi.PushStatListener.PushMsgType.RETRY_PULL_TYPE;
        } else {
            pushMsgType = NSLongLinkApi.PushStatListener.PushMsgType.SIGNAL_SERVER_PUSH_TYPE;
        }
        long j = (msgId >> 23) + 1546272000000L;
        for (NSLongLinkApi.PushStatListener pushStatListener : this.mPushStatListeners) {
            if (pushStatListener != null) {
                pushStatListener.onPushStatInfo(pushMsgType, hySignalMessage.msgId, hySignalMessage.sMsg.length, hySignalMessage.getSGroupId(), j, HyTimeSyncClient.getInstance().getEpochTime() - j);
            }
        }
    }

    private void initMsgPush() {
        this.mMsgCache = new PushMsgCache(3, this.mMsgMaxCount);
        this.mGroupMsgCache = new PushMsgCache(3, this.mGroupMsgCount);
    }

    private void initMsgQuery(boolean z) {
        Boolean bool = this.mSignalWrapUserInfo.isLogin;
        if (bool == null) {
            bool = false;
        }
        this.mMsgPuller = new HistoryMsgHelper(0, this.mSignalWrapUserInfo.uid, bool.booleanValue(), z);
        this.mGroupMsgPuller = new HistoryMsgHelper(1, this.mSignalWrapUserInfo.uid, bool.booleanValue(), z);
    }

    private void resetMsgQueryWhenLinkStatusChange(int i) {
        this.mMsgPuller.resetWhenLinkStatusChange(i);
        this.mGroupMsgPuller.resetWhenLinkStatusChange(i);
    }

    private boolean isMsgValid(NSLongLinkApi.HySignalMessage hySignalMessage) {
        if (empty(hySignalMessage.getSGroupId())) {
            return addMsg(hySignalMessage, this.mMsgCache, this.mMsgPuller);
        }
        return addMsg(hySignalMessage, this.mGroupMsgCache, this.mGroupMsgPuller);
    }

    private boolean addMsg(NSLongLinkApi.HySignalMessage hySignalMessage, PushMsgCache pushMsgCache, HistoryMsgHelper historyMsgHelper) {
        if (hySignalMessage.getMsgId() == 0) {
            HySignalLog.debug(TAG, "add Msg server id=0, return");
            return true;
        }
        if (!hySignalMessage.isFromQuery()) {
            historyMsgHelper.resetLastPushMsgId(hySignalMessage.getMsgId());
        }
        return pushMsgCache.cacheIfNeed(hySignalMessage.getMsgId());
    }

    private int getMaxFrequencyForUri(int i) {
        Map<String, String> map = this.mMaxFrequencyMap;
        if (map == null) {
            return 200;
        }
        return safelyParseInt(map.get(String.valueOf(i)), safelyParseInt(map.get("default"), 200));
    }

    private boolean checkIsOverFrequency(int i) {
        PushFrequencyControlItem pushFrequencyControlItem = this.mPushFrequencyCache.get(Integer.valueOf(i));
        if (pushFrequencyControlItem == null) {
            pushFrequencyControlItem = new PushFrequencyControlItem(getMaxFrequencyForUri(i), SystemClock.uptimeMillis());
            this.mPushFrequencyCache.put(Integer.valueOf(i), pushFrequencyControlItem);
        }
        if (pushFrequencyControlItem.isOverFrequency()) {
            HySignalLog.info(TAG, "uri= %s is over frequency, maxFrequency = %d, currentCount = %d ", Integer.valueOf(i), Integer.valueOf(pushFrequencyControlItem.getMaxCount()), Integer.valueOf(pushFrequencyControlItem.getCurrentCount()));
            return false;
        }
        pushFrequencyControlItem.increase();
        return true;
    }

    private void requestUpdateExperiment(final Map<String, String> map) {
        ThreadManager.deliverOnRequestThread(new Runnable() { // from class: com.huya.hysignal.wrapper.HySignalWrapper.12
            @Override // java.lang.Runnable
            public void run() {
                Map map2 = map;
                if (map2 == null || map2.isEmpty()) {
                    HySignalLog.info(HySignalWrapper.TAG, "config is empty, skip update");
                    return;
                }
                WSUpdateUserExpsReq wSUpdateUserExpsReq = new WSUpdateUserExpsReq();
                wSUpdateUserExpsReq.setMExps(map);
                HySignalClient.getInstance().requestAllDefaultChannel(new Request.Builder().cmdId(23).cgi("/cmdid/23").body(wSUpdateUserExpsReq.toByteArray()).build(), new HySignalClient.RequestAllCallback() { // from class: com.huya.hysignal.wrapper.HySignalWrapper.12.1
                    @Override // com.huya.hysignal.core.HySignalClient.RequestAllCallback
                    public void onResp(String str, Response response) {
                        if (response == null) {
                            HySignalLog.error("hysignal updateExperimentConfig channel: " + str + " rsp is empty");
                            return;
                        }
                        if (response.error.getErrType() != 0) {
                            HySignalLog.error(HySignalWrapper.TAG, "hysignal updateExperimentConfig channel: " + str + " errType = %d,errCode = %d", Integer.valueOf(response.error.getErrType()), Integer.valueOf(response.error.getErrCode()));
                            return;
                        }
                        HySignalLog.info(HySignalWrapper.TAG, "hysignal updateExperimentConfig channel: " + str + " rsp=" + ((WSUpdateUserExpsRsp) JceParser.parseJce(response.data, new WSUpdateUserExpsRsp())));
                    }
                });
            }
        });
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

    private void setupP2P() {
        if (this.mEnableP2PPush) {
            this.mP2pPushListener = new P2pPushListener() { // from class: com.huya.hysignal.wrapper.HySignalWrapper.13
                @Override // com.huya.hysignal.wrapper.listener.P2pPushListener
                public void onDatas(String str, long j, Vector<byte[]> vector, int i) {
                    Iterator<byte[]> it = vector.iterator();
                    while (it.hasNext()) {
                        try {
                            JceInputStream jceInputStream = new JceInputStream(it.next());
                            WSPushMessage wSPushMessage = new WSPushMessage();
                            wSPushMessage.readFrom(jceInputStream);
                            NSLongLinkApi.HySignalMessage hySignalMessage = new NSLongLinkApi.HySignalMessage((int) wSPushMessage.iUri, wSPushMessage.sMsg, wSPushMessage.sGroupId, wSPushMessage.lMsgId, false, "P2P");
                            hySignalMessage.setFromP2p(true);
                            HySignalWrapper.this.dispatchPushMsg(hySignalMessage);
                        } catch (Exception e) {
                            HySignalLog.error(HySignalWrapper.TAG, "P2P onDatas  error = %s", e.getMessage());
                        }
                    }
                }

                @Override // com.huya.hysignal.wrapper.listener.P2pPushListener
                public void onSignalStreamReqStatus(String str, long j, int i) {
                    Boolean bool;
                    HySignalLog.info(HySignalWrapper.TAG, "onSignalStreamReqStatus streamName = %s, anchorUid=%d, status=%d", str, Long.valueOf(j), Integer.valueOf(i));
                    ArrayList arrayList = new ArrayList();
                    arrayList.add(str);
                    if (i == 0) {
                        HySignalWrapper.this.mP2pEnterStatusMap.put(str, true);
                        HySignalWrapper.this.requestEnterP2p(arrayList);
                        return;
                    }
                    if (i == 1 && (bool = (Boolean) HySignalWrapper.this.mP2pEnterStatusMap.get(str)) != null && bool.booleanValue()) {
                        HySignalWrapper.this.requestExitP2p(arrayList);
                    }
                    HySignalWrapper.this.mP2pEnterStatusMap.put(str, false);
                }
            };
        }
    }

    private void handleP2pPush(NSLongLinkApi.HySignalMessage hySignalMessage) {
        if (hySignalMessage.getIUri() == 1025307) {
            tryEntryP2P(((WSP2POpenNotify) JceParser.parseJce(hySignalMessage.getSMsg(), new WSP2POpenNotify())).getVGroupId());
        }
        if (hySignalMessage.getIUri() == 1025308) {
            tryExitP2p(((WSP2PCloseNotify) JceParser.parseJce(hySignalMessage.getSMsg(), new WSP2PCloseNotify())).getVGroupId());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void tryEntryP2P(ArrayList<String> arrayList) {
        ArrayList<String> arrayList2;
        MTPApi.LOGGER.info(TAG, "tryExitP2p: %s", parseArrayListToString(arrayList));
        if (!this.mEnableP2PPush) {
            HySignalLog.info(TAG, "tryEntryP2P mIsP2pMsgEnable is false");
            return;
        }
        if (arrayList == null || arrayList.isEmpty()) {
            HySignalLog.info(TAG, "tryEntryP2P mIsP2pMsgEnable is empty");
            return;
        }
        synchronized (this.mP2PGroupEnableSwitch) {
            arrayList2 = new ArrayList();
            for (String str : arrayList) {
                Boolean bool = this.mP2PGroupEnableSwitch.get(str);
                if (bool == null || bool.booleanValue()) {
                    arrayList2.add(str);
                }
            }
        }
        ArrayList<String> arrayList3 = new ArrayList<>();
        ArrayList<String> arrayList4 = new ArrayList();
        for (String str2 : arrayList2) {
            Boolean bool2 = this.mP2pEnterStatusMap.get(str2);
            if (bool2 != null && bool2.booleanValue()) {
                arrayList3.add(str2);
            } else {
                arrayList4.add(str2);
            }
        }
        if (arrayList3.size() > 0) {
            HySignalLog.info(TAG, "tryEntryP2P startedGids = %s", arrayList3);
            requestEnterP2p(arrayList3);
        }
        if (arrayList4.size() > 0) {
            HySignalLog.info(TAG, "tryEntryP2P unstartedGids = %s", arrayList4);
            if (this.mP2pPushDelegate == null) {
                HySignalLog.info(TAG, "startP2p mP2pPushDelegate is null");
                return;
            }
            for (String str3 : arrayList4) {
                this.mP2pPushDelegate.start(str3, getPid(str3), this.mP2pPushListener);
            }
        }
    }

    private void tryExitP2p(ArrayList<String> arrayList) {
        MTPApi.LOGGER.info(TAG, "tryExitP2p: %s", parseArrayListToString(arrayList));
        if (!this.mEnableP2PPush) {
            HySignalLog.info(TAG, "tryEntryP2P mIsP2pMsgEnable is false");
            return;
        }
        if (arrayList != null) {
            for (String str : arrayList) {
                P2pPushDelegate p2pPushDelegate = this.mP2pPushDelegate;
                if (p2pPushDelegate != null) {
                    p2pPushDelegate.stop(str);
                }
                this.mP2pEnterStatusMap.remove(str);
            }
            requestExitP2p(arrayList);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void requestEnterP2p(final ArrayList<String> arrayList) {
        MTPApi.LOGGER.info(TAG, "will requestEnterP2p: %s", parseArrayListToString(arrayList));
        ThreadManager.deliverOnRequestThread(new Runnable() { // from class: com.huya.hysignal.wrapper.HySignalWrapper.14
            @Override // java.lang.Runnable
            public void run() {
                if (!HySignalClient.getInstance().isLongLinkConnected(5)) {
                    HySignalLog.error(HySignalWrapper.TAG, "requestEnterP2p, longlink is not connected");
                    return;
                }
                WSEnterP2P wSEnterP2P = new WSEnterP2P();
                wSEnterP2P.setVGroupId(arrayList);
                HySignalClient.getInstance().newCall(new Request.Builder().cmdId(27).cgi("/cmdid/27").channel(5).retryCount(2).body(wSEnterP2P.toByteArray()).networkStatusSensitive(true).build()).enqueue(new Callback() { // from class: com.huya.hysignal.wrapper.HySignalWrapper.14.1
                    @Override // com.huya.hysignal.core.Callback
                    public void onResponse(final byte[] bArr, final HySignalError hySignalError) {
                        ThreadManager.deliverOnResponseThread(new Runnable() { // from class: com.huya.hysignal.wrapper.HySignalWrapper.14.1.1
                            @Override // java.lang.Runnable
                            public void run() {
                                if (hySignalError.getErrType() != 0) {
                                    return;
                                }
                                HySignalLog.info(HySignalWrapper.TAG, "p2p wsVerifyHuyaTokenRsp = %s", (WSVerifyHuyaTokenRsp) JceParser.parseJce(bArr, new WSVerifyHuyaTokenRsp()));
                            }
                        });
                    }
                });
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void requestExitP2p(final ArrayList<String> arrayList) {
        MTPApi.LOGGER.info(TAG, "will requestExitP2p: %s", parseArrayListToString(arrayList));
        ThreadManager.deliverOnRequestThread(new Runnable() { // from class: com.huya.hysignal.wrapper.HySignalWrapper.15
            @Override // java.lang.Runnable
            public void run() {
                if (!HySignalClient.getInstance().isLongLinkConnected(5)) {
                    HySignalLog.error(HySignalWrapper.TAG, "requestExitP2p, longlink is not connected");
                    return;
                }
                WSExitP2P wSExitP2P = new WSExitP2P();
                wSExitP2P.setVGroupId(arrayList);
                HySignalClient.getInstance().newCall(new Request.Builder().cmdId(29).cgi("/cmdid/29").channel(5).retryCount(2).body(wSExitP2P.toByteArray()).networkStatusSensitive(true).build()).enqueue(new Callback() { // from class: com.huya.hysignal.wrapper.HySignalWrapper.15.1
                    @Override // com.huya.hysignal.core.Callback
                    public void onResponse(final byte[] bArr, final HySignalError hySignalError) {
                        ThreadManager.deliverOnResponseThread(new Runnable() { // from class: com.huya.hysignal.wrapper.HySignalWrapper.15.1.1
                            @Override // java.lang.Runnable
                            public void run() {
                                if (hySignalError.getErrType() != 0) {
                                    return;
                                }
                                HySignalLog.info(HySignalWrapper.TAG, "p2p wsVerifyHuyaTokenRsp = %s", (WSVerifyHuyaTokenRsp) JceParser.parseJce(bArr, new WSVerifyHuyaTokenRsp()));
                            }
                        });
                    }
                });
            }
        });
    }

    private void registerGroup(ArrayList<String> arrayList) {
        if (!this.isOpenSynRegister) {
            registerGroupForBackUp(arrayList);
        } else {
            registerGroup(arrayList, new ArrayList<>(), new HashMap<>());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void registerGroupForBackUp(ArrayList<String> arrayList) {
        if (empty(arrayList)) {
            HySignalLog.error(TAG, "register groups is empty, return");
        } else {
            ThreadManager.deliverOnRegisterThread(new AnonymousClass16(arrayList));
        }
    }

    /* JADX INFO: renamed from: com.huya.hysignal.wrapper.HySignalWrapper$16, reason: invalid class name */
    class AnonymousClass16 implements Runnable {
        final /* synthetic */ ArrayList val$groupIds;

        AnonymousClass16(ArrayList arrayList) {
            this.val$groupIds = arrayList;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.val$groupIds.contains(null)) {
                this.val$groupIds.remove((Object) null);
                HySignalLog.error(HySignalWrapper.TAG, "registerGroupForBackUp groups contains null, remove it");
            }
            WSRegisterGroupReq wSRegisterGroupReq = new WSRegisterGroupReq();
            wSRegisterGroupReq.setVGroupId(this.val$groupIds);
            Request requestBuild = new Request.Builder().cmdId(16).cgi("/cmdid/16").channel(5).retryCount(0).body(wSRegisterGroupReq.toByteArray()).networkStatusSensitive(true).build();
            if (!HySignalClient.getInstance().isLongLinkConnected(5)) {
                HySignalLog.error(HySignalWrapper.TAG, "registerGroup, longLink is not connected");
                HySignalWrapper.this.notifyRegisterFailed(this.val$groupIds, "longlink is not ready");
            } else {
                HySignalLog.info(HySignalWrapper.TAG, "RegisterGroup groupIds = %s", this.val$groupIds.toString());
                HySignalClient.getInstance().newCall(requestBuild).enqueue(new Callback() { // from class: com.huya.hysignal.wrapper.HySignalWrapper.16.1
                    @Override // com.huya.hysignal.core.Callback
                    public void onResponse(final byte[] bArr, final HySignalError hySignalError) {
                        ThreadManager.deliverOnResponseThread(new Runnable() { // from class: com.huya.hysignal.wrapper.HySignalWrapper.16.1.1
                            @Override // java.lang.Runnable
                            public void run() {
                                if (hySignalError.getErrType() != 0) {
                                    HySignalLog.error(HySignalWrapper.TAG, "registerGroup errType = %d,errCode = %d", Integer.valueOf(hySignalError.getErrType()), Integer.valueOf(hySignalError.getErrCode()));
                                    HySignalWrapper.this.notifyRegisterFailed(AnonymousClass16.this.val$groupIds, "request success, rsp errType:" + hySignalError.getErrType() + "errCode: " + hySignalError.getErrCode());
                                    return;
                                }
                                WSRegisterGroupRsp wSRegisterGroupRsp = (WSRegisterGroupRsp) JceParser.parseJce(bArr, new WSRegisterGroupRsp());
                                HySignalLog.info(HySignalWrapper.TAG, "finish register group");
                                if (wSRegisterGroupRsp == null) {
                                    HySignalWrapper.this.notifyRegisterFailed(AnonymousClass16.this.val$groupIds, "request failed, rsp errType:" + hySignalError.getErrType() + "errCode:" + hySignalError.getErrCode() + ", WSRegisterGroupRsp is null");
                                    return;
                                }
                                if (wSRegisterGroupRsp.getIResCode() == 0) {
                                    HySignalWrapper.this.notifyRegisterSuccess(AnonymousClass16.this.val$groupIds);
                                    for (String str : AnonymousClass16.this.val$groupIds) {
                                        if (!HySignalWrapper.this.mCurrentGroupList.contains(str)) {
                                            HySignalWrapper.this.mCurrentGroupList.add(str);
                                        }
                                    }
                                    HySignalLog.info(HySignalWrapper.TAG, " wsRegisterGroupRsp.getVSupportP2PGroupId() = %s", wSRegisterGroupRsp.getVSupportP2PGroupId());
                                    HySignalWrapper.this.tryEntryP2P(wSRegisterGroupRsp.getVSupportP2PGroupId());
                                    return;
                                }
                                HySignalLog.error(HySignalWrapper.TAG, "register wsRegisterGroupRsp.getIResCode: %d", Integer.valueOf(wSRegisterGroupRsp.iResCode));
                                HySignalWrapper.this.notifyRegisterFailed(AnonymousClass16.this.val$groupIds, "request success, but wsRegisterGroupRsp.getIResCode is " + wSRegisterGroupRsp.iResCode + ", not 0");
                            }
                        });
                    }
                });
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void registerGroup(final ArrayList<String> arrayList, final ArrayList<String> arrayList2, final HashMap<String, String> map) {
        this.mWaitRegisterQueue.add(new Runnable() { // from class: com.huya.hysignal.wrapper.HySignalWrapper.17
            @Override // java.lang.Runnable
            public void run() {
                HySignalWrapper.this.addToWaitRegister(arrayList, arrayList2, map);
            }
        });
        if (this.mRegisterThreadRunning) {
            return;
        }
        ThreadManager.deliverOnRegisterThread(new Runnable() { // from class: com.huya.hysignal.wrapper.HySignalWrapper.18
            @Override // java.lang.Runnable
            public void run() {
                HySignalWrapper.this.mRegisterThreadRunning = true;
                while (!HySignalWrapper.this.mWaitRegisterQueue.isEmpty()) {
                    ((Runnable) HySignalWrapper.this.mWaitRegisterQueue.remove()).run();
                }
                if (HySignalClient.getInstance().isLongLinkConnected(5)) {
                    if (!HySignalWrapper.this.synRegisterGroup(new ArrayList(HySignalWrapper.this.mWaitInGroupList), new ArrayList(HySignalWrapper.this.mWaitOutGroupList), HySignalWrapper.this.mWaitInGroupTokenMap)) {
                        HySignalWrapper.this.clearWaitingGroups();
                    }
                    if (!HySignalWrapper.this.mWaitRegisterQueue.isEmpty()) {
                        ThreadManager.deliverOnRegisterThread(this);
                    }
                    HySignalWrapper.this.mRegisterThreadRunning = false;
                    return;
                }
                HySignalLog.error("network is unConnected, wait for network ava.");
                HySignalWrapper.this.mRegisterThreadRunning = false;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearWaitingGroups() {
        this.mWaitInGroupList.clear();
        this.mWaitOutGroupList.clear();
        this.mWaitInGroupTokenMap.clear();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void addToWaitRegister(ArrayList<String> arrayList, ArrayList<String> arrayList2, HashMap<String, String> map) {
        boolean z;
        Object[] objArr = new Object[2];
        boolean z2 = false;
        objArr[0] = arrayList != null ? arrayList.toString() : AbstractJsonLexerKt.NULL;
        objArr[1] = arrayList2 != null ? arrayList2.toString() : AbstractJsonLexerKt.NULL;
        HySignalLog.info(TAG, "addToWaitRegister addGroup:%s, removeGroups:%s", objArr);
        if (arrayList == null || arrayList.isEmpty()) {
            z = false;
        } else {
            this.mWaitInGroupList.addAll(arrayList);
            this.mWaitOutGroupList.removeAll(arrayList);
            z = true;
        }
        if (map != null && !map.isEmpty()) {
            this.mWaitInGroupTokenMap.putAll(map);
            this.mWaitInGroupList.addAll(map.keySet());
            z = true;
        }
        if (arrayList2 != null && !arrayList2.isEmpty()) {
            this.mWaitInGroupList.removeAll(arrayList2);
            this.mWaitOutGroupList.addAll(arrayList2);
            Iterator<String> it = arrayList2.iterator();
            while (it.hasNext()) {
                this.mWaitInGroupTokenMap.remove(it.next());
            }
            z2 = true;
        }
        if (z && z2) {
            HySignalLog.error(TAG, "addToWaitRegister param error, both addGroups and removeGroups are null.");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean synRegisterGroup(ArrayList<String> arrayList, ArrayList<String> arrayList2, Map<String, String> map) {
        Response responseExecute;
        if (empty(arrayList) && empty(arrayList2) && (map == null || map.isEmpty())) {
            HySignalLog.error(TAG, "parameter error, return");
            return false;
        }
        if (map == null) {
            map = new HashMap<>();
        }
        HySignalLog.info(TAG, "requestSetGroup addGroupIds = %s", parseArrayListToString(arrayList));
        Object[] objArr = new Object[1];
        objArr[0] = map != null ? map.toString() : AbstractJsonLexerKt.NULL;
        HySignalLog.info(TAG, "requestSetGroup addGroupIdToken = %s", objArr);
        HySignalLog.info(TAG, "requestSetGroup removeGroups = %s", parseArrayListToString(arrayList2));
        ArrayList<String> arrayList3 = new ArrayList<>(this.mCurrentGroupList);
        for (String str : arrayList) {
            if (!arrayList3.contains(str)) {
                arrayList3.add(str);
            }
        }
        Iterator<String> it = arrayList2.iterator();
        while (it.hasNext()) {
            arrayList3.remove(it.next());
        }
        HashMap map2 = new HashMap();
        map2.putAll(map);
        map2.putAll(this.mCurrentGroupTokenMap);
        for (String str2 : map2.keySet()) {
            if (!arrayList3.contains(str2)) {
                arrayList3.add(str2);
            }
        }
        if (!HySignalClient.getInstance().isLongLinkConnected(5)) {
            HySignalLog.error(TAG, "registerGroup, longLink is not connected");
            notifyRegisterFailed(arrayList, "longlink is not ready");
            notifyUnregisterFailed(arrayList2, "longlink is not ready");
            return true;
        }
        WSSyncGroupReq wSSyncGroupReq = new WSSyncGroupReq();
        wSSyncGroupReq.setVGroupId(arrayList3);
        wSSyncGroupReq.setMGroupId2Token(map2);
        try {
            responseExecute = HySignalClient.getInstance().newCall(new Request.Builder().cmdId(31).cgi("/cmdid/31").channel(5).retryCount(10).body(wSSyncGroupReq.toByteArray()).networkStatusSensitive(true).build()).execute();
        } catch (Exception e) {
            HySignalLog.error(TAG, "register syn request exception", e);
            e.printStackTrace();
        }
        if (responseExecute == null) {
            HySignalLog.error(TAG, "registerGroup failed net resp is empty");
            notifyRegisterFailed(arrayList, "registerGroup failed net resp is empty");
            notifyUnregisterFailed(arrayList2, "registerGroup failed net resp is empty");
            return false;
        }
        HySignalError hySignalError = responseExecute.error;
        byte[] bArr = responseExecute.data;
        if (hySignalError.getErrType() != 0) {
            HySignalLog.error(TAG, "registerGroup errType = %d,errCode = %d", Integer.valueOf(hySignalError.getErrType()), Integer.valueOf(hySignalError.getErrCode()));
            String str3 = "request success, rsp errType:" + hySignalError.getErrType() + "errCode: " + hySignalError.getErrCode();
            notifyRegisterFailed(arrayList, str3);
            notifyUnregisterFailed(arrayList2, str3);
            return false;
        }
        WSSyncGroupRsp wSSyncGroupRsp = (WSSyncGroupRsp) JceParser.parseJce(bArr, new WSSyncGroupRsp());
        HySignalLog.info(TAG, "finish register group");
        if (wSSyncGroupRsp == null) {
            notifyRegisterFailed(arrayList, "request success, rsp errType:" + hySignalError.getErrType() + "errCode:" + hySignalError.getErrCode() + ", WSRegisterGroupRsp is null");
            return false;
        }
        if (wSSyncGroupRsp.getIResCode() == 0) {
            ArrayList<String> arrayList4 = new ArrayList<>();
            for (String str4 : arrayList) {
                if (wSSyncGroupRsp.vRegisterGroupId.contains(str4)) {
                    arrayList4.add(str4);
                    this.mWaitInGroupList.remove(str4);
                    if (this.mWaitInGroupTokenMap.containsKey(str4)) {
                        this.mCurrentGroupTokenMap.put(str4, this.mWaitInGroupTokenMap.get(str4));
                    } else {
                        this.mCurrentGroupTokenMap.remove(str4);
                    }
                }
            }
            notifyRegisterSuccess(arrayList4);
            ArrayList<String> arrayList5 = new ArrayList<>();
            for (String str5 : arrayList2) {
                if (!wSSyncGroupRsp.vRegisterGroupId.contains(str5)) {
                    arrayList5.add(str5);
                    this.mWaitOutGroupList.remove(str5);
                    this.mWaitInGroupTokenMap.remove(str5);
                }
            }
            notifyUnregisterSuccess(arrayList5);
            this.mCurrentGroupList = wSSyncGroupRsp.vRegisterGroupId;
            HySignalLog.info(TAG, " wsRegisterGroupRsp.getVSupportP2PGroupId() = %s", wSSyncGroupRsp.getVSupportP2PGroupId());
            tryEntryP2P(wSSyncGroupRsp.getVSupportP2PGroupId());
            return true;
        }
        HySignalLog.error(TAG, "register wsRegisterGroupRsp.getIResCode: %d", Integer.valueOf(wSSyncGroupRsp.iResCode));
        notifyRegisterFailed(arrayList, "request success, but wsRegisterGroupRsp.getIResCode is " + wSSyncGroupRsp.iResCode + ", not 0");
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyRegisterSuccess(ArrayList<String> arrayList) {
        for (String str : arrayList) {
            PushRegister pushRegister = FieldsCache.getInstance().getGroupPushRegisterMap().get(str);
            if (pushRegister != null && !pushRegister.hasNotify) {
                RegisterPushMsgListener registerPushMsgListener = pushRegister.getRegisterPushMsgListener();
                if (registerPushMsgListener != null) {
                    registerPushMsgListener.onRegisterSucceed(new RegistResultInfo(str, 0, ""));
                }
                pushRegister.setHasNotify(true);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyRegisterFailed(ArrayList<String> arrayList, String str) {
        RegisterPushMsgListener registerPushMsgListener;
        for (String str2 : arrayList) {
            PushRegister pushRegister = FieldsCache.getInstance().getGroupPushRegisterMap().get(str2);
            if (pushRegister != null && (registerPushMsgListener = pushRegister.getRegisterPushMsgListener()) != null) {
                registerPushMsgListener.onRegisterFailed(new RegistResultInfo(str2, 1, str));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyUnregisterSuccess(UnRegisterPushMsgListener unRegisterPushMsgListener, ArrayList<String> arrayList) {
        if (unRegisterPushMsgListener != null) {
            Iterator<String> it = arrayList.iterator();
            while (it.hasNext()) {
                unRegisterPushMsgListener.onUnRegisterSucceed(new RegistResultInfo(it.next(), 0, ""));
            }
        }
    }

    private void notifyUnregisterSuccess(ArrayList<String> arrayList) {
        for (String str : arrayList) {
            PushUnRegister pushUnRegisterRemove = FieldsCache.getInstance().getGroupPushUnRegisterMap().remove(str);
            if (pushUnRegisterRemove != null && !pushUnRegisterRemove.hasNotify) {
                UnRegisterPushMsgListener registerPushMsgListener = pushUnRegisterRemove.getRegisterPushMsgListener();
                if (registerPushMsgListener != null) {
                    registerPushMsgListener.onUnRegisterSucceed(new RegistResultInfo(str, 0, ""));
                }
                pushUnRegisterRemove.setHasNotify(true);
            }
        }
    }

    private void notifyUnregisterFailed(ArrayList<String> arrayList, String str) {
        UnRegisterPushMsgListener registerPushMsgListener;
        for (String str2 : arrayList) {
            PushUnRegister pushUnRegisterRemove = FieldsCache.getInstance().getGroupPushUnRegisterMap().remove(str2);
            if (pushUnRegisterRemove != null && !pushUnRegisterRemove.hasNotify && (registerPushMsgListener = pushUnRegisterRemove.getRegisterPushMsgListener()) != null) {
                registerPushMsgListener.onUnRegisterFailed(new RegistResultInfo(str2, 1, str));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyUnregisterFailedForBackUp(ArrayList<String> arrayList, UnRegisterPushMsgListener unRegisterPushMsgListener, String str) {
        if (unRegisterPushMsgListener != null) {
            Iterator<String> it = arrayList.iterator();
            while (it.hasNext()) {
                unRegisterPushMsgListener.onUnRegisterFailed(new RegistResultInfo(it.next(), 1, str));
            }
        }
    }

    private void removeIps(NSLongLinkApi.HySignalMessage hySignalMessage) {
        if (this.mRemoveIpListener == null) {
            HySignalLog.info(TAG, "remove ip failed, listener is null");
            return;
        }
        ArrayList<String> vRemoveIps = ((WSRedirect) JceParser.parseJce(hySignalMessage.getSMsg(), new WSRedirect())).getVRemoveIps();
        if (vRemoveIps == null || vRemoveIps.isEmpty()) {
            HySignalLog.info(TAG, "remove ips empty, return");
        } else if (this.mRemoveIpListener.onRemoveIps(vRemoveIps)) {
            HySignalLog.info(TAG, "remove ips: %s", parseArrayListToString(vRemoveIps));
            networkChanged();
        }
    }

    private void networkChanged() {
        HySignalLog.info(TAG, "involve networkChange");
        BaseEvent.ConnectionReceiver.onNetworkChangedWithTryCatch();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void postVerifyResult(HySignalVerifyBizListener hySignalVerifyBizListener, boolean z, String str) {
        if (hySignalVerifyBizListener == null) {
            return;
        }
        hySignalVerifyBizListener.onResult(z, str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void postVerifyResultV2(HySignalVerifyListenerV2 hySignalVerifyListenerV2, boolean z, int i, String str) {
        if (hySignalVerifyListenerV2 == null) {
            return;
        }
        hySignalVerifyListenerV2.onResult(z, i, str);
    }

    private boolean isNetworkAvailable() {
        Context context = this.mContext;
        if (context == null) {
            return false;
        }
        return NetStatusUtil.isNetworkConnected(context);
    }

    private boolean checkConfig(SignalWrapConfig signalWrapConfig) {
        if (signalWrapConfig == null) {
            HySignalLog.error(TAG, "mConfig is null, return");
            return false;
        }
        if (signalWrapConfig.mContext != null) {
            return true;
        }
        HySignalLog.error(TAG, "config mContext is null, return");
        return false;
    }

    private boolean isUserInfoChange(SignalWrapUserInfo signalWrapUserInfo) {
        boolean z;
        if (signalWrapUserInfo.uid < 0 || this.mSignalWrapUserInfo.uid == signalWrapUserInfo.uid) {
            signalWrapUserInfo.uid = this.mSignalWrapUserInfo.uid;
            z = false;
        } else {
            z = true;
        }
        if (signalWrapUserInfo.tokenType == -1 || this.mSignalWrapUserInfo.tokenType == signalWrapUserInfo.tokenType) {
            signalWrapUserInfo.tokenType = this.mSignalWrapUserInfo.tokenType;
        } else {
            z = true;
        }
        if (signalWrapUserInfo.token == null || signalWrapUserInfo.token.equals(this.mSignalWrapUserInfo.token)) {
            signalWrapUserInfo.token = this.mSignalWrapUserInfo.token;
        } else {
            z = true;
        }
        if (signalWrapUserInfo.isLogin != null && !signalWrapUserInfo.isLogin.equals(this.mSignalWrapUserInfo.isLogin)) {
            return true;
        }
        signalWrapUserInfo.isLogin = this.mSignalWrapUserInfo.isLogin;
        return z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isKeyContainPid(String str, long j) {
        return getPid(str) == j;
    }

    private long getPid(String str) {
        String strSubstring;
        if (!str.contains(":")) {
            return -1L;
        }
        if (str.contains("-")) {
            strSubstring = str.substring(str.indexOf(":") + 1, str.indexOf("-"));
        } else {
            strSubstring = str.substring(str.indexOf(":") + 1);
        }
        try {
            return Long.valueOf(strSubstring).longValue();
        } catch (NumberFormatException unused) {
            return -1L;
        }
    }

    private String createMsgCountKey(NSLongLinkApi.HySignalMessage hySignalMessage) {
        if (hySignalMessage == null) {
            return "null_msg";
        }
        String sGroupId = hySignalMessage.getSGroupId();
        if (sGroupId == null || sGroupId.length() == 0) {
            sGroupId = "singleBroadcast";
        }
        return "u_" + hySignalMessage.getIUri() + "/g_" + sGroupId + "/p_" + (hySignalMessage.isFromP2p() ? "1" : "0") + "/q_" + (hySignalMessage.isFromQuery() ? "1" : "0");
    }

    private synchronized void increaseMsgCount(NSLongLinkApi.HySignalMessage hySignalMessage) {
        String strCreateMsgCountKey = createMsgCountKey(hySignalMessage);
        Long l = this.mPushMsgCountMap.get(strCreateMsgCountKey);
        if (l == null) {
            this.mPushMsgCountMap.put(strCreateMsgCountKey, 1L);
            return;
        }
        this.mPushMsgCountMap.put(strCreateMsgCountKey, Long.valueOf(l.longValue() + 1));
        long j = this.mAllPushMsgCount + 1;
        this.mAllPushMsgCount = j;
        if (j >= 200) {
            Calendar calendar = Calendar.getInstance();
            String str = calendar.get(11) + ":" + calendar.get(12) + ":" + calendar.get(13);
            MTPApi.LOGGER.info(TAG, "NSPushC [%s~%s], msg: %s", this.mLastPrintPushLogTime, str, transMapToString(this.mPushMsgCountMap));
            HySignalLog.info(TAG, "NSPushS msg uri=%s msgId=%s, isQ=%b, isP2p=%b, sTime=%s", Integer.valueOf(hySignalMessage.getIUri()), Long.valueOf(hySignalMessage.msgId), Boolean.valueOf(hySignalMessage.isFromQuery), Boolean.valueOf(hySignalMessage.isFromP2p), Long.valueOf(HyTimeSyncClient.getInstance().getEpochTime()));
            this.mLastPrintPushLogTime = str;
            this.mAllPushMsgCount = 0L;
            this.mPushMsgCountMap.clear();
        }
    }

    private boolean empty(CharSequence charSequence) {
        return charSequence == null || charSequence.length() == 0;
    }

    private boolean empty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    private int safelyParseInt(String str, int i) {
        if (TextUtils.isEmpty(str)) {
            return i;
        }
        try {
            return Integer.valueOf(str).intValue();
        } catch (Exception unused) {
            return i;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String parseArrayListToString(ArrayList<String> arrayList) {
        if (arrayList == null || arrayList.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        Iterator<String> it = arrayList.iterator();
        while (it.hasNext()) {
            sb.append(it.next());
            sb.append("\t");
        }
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onVerifyTokenSucceed() {
        if (!sInited) {
            HySignalLog.error(TAG, "onVerifyTokenSucceed need init");
        } else {
            HySignalLog.info(TAG, "onVerifyTokenSucceed");
            this.mMsgPuller.pullHistoryMsg(this);
        }
    }

    private static String transMapToString(Map<String, Long> map) {
        if (map == null || map.isEmpty()) {
            return "empty";
        }
        StringBuilder sb = new StringBuilder();
        Iterator<Map.Entry<String, Long>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Long> next = it.next();
            if (next.getKey() != null) {
                sb.append(next.getKey().toString());
                sb.append("'");
                sb.append(next.getValue() == null ? "" : next.getValue().toString());
                sb.append(it.hasNext() ? "^" : "");
            }
        }
        return sb.toString();
    }

    public ArrayList<String> getCurrentGroupList() {
        return this.mCurrentGroupList;
    }

    public HashSet<String> getWaitInGroupList() {
        return this.mWaitInGroupList;
    }

    public HashSet<String> getWaitOutGroupList() {
        return this.mWaitOutGroupList;
    }

    public void setDefaultSynRegister(boolean z) {
        this.isOpenSynRegister = z;
    }

    public SignalWrapUserInfo getSignalWrapUserInfo() {
        return this.mSignalWrapUserInfo;
    }

    public String getUa() {
        return this.mUa;
    }
}
