package com.huya.hysignal.core;

import android.content.Context;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.text.TextUtils;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceParser;
import com.huya.berry.gamesdk.module.ICommonConstants;
import com.huya.data.MonitorReqData;
import com.huya.hysignal.bizreq.HySignalLaunch;
import com.huya.hysignal.jce.WSMsgAckReq;
import com.huya.hysignal.jce.WSMsgAckRsp;
import com.huya.hysignal.jce.WSMsgItem;
import com.huya.hysignal.jce.WSPushMessage;
import com.huya.hysignal.jce.WSPushMessage_V2;
import com.huya.hysignal.listener.HySignalReportListener;
import com.huya.hysignal.listener.PushListener;
import com.huya.hysignal.util.FieldsCache;
import com.huya.hysignal.util.HySignalLog;
import com.huya.hysignal.util.ThreadManager;
import com.huya.mtp.api.MTPApi;
import com.huya.mtp.hyns.Constants;
import com.huya.mtp.hyns.api.NSLongLinkApi;
import com.huya.mtp.hyns.api.NSNetUtilApi;
import com.huya.mtp.hyns.api.Request;
import com.huya.mtp.hyns.report.NSPushReporter;
import com.sy37sdk.core.INewUrl;
import com.taptap.sdk.common.oaid.helper.OAIDHelper;
import com.tencent.bugly.Bugly;
import com.tencent.mars.BaseEvent;
import com.tencent.mars.Mars;
import com.tencent.mars.NetWorkChangeListener;
import com.tencent.mars.app.AppLogic;
import com.tencent.mars.comm.WakerLock;
import com.tencent.mars.sdt.SdtLogic;
import com.tencent.mars.stn.StnLogic;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
final class HyMars implements StnLogic.ICallBack, SdtLogic.ICallBack, AppLogic.ICallBack, NetWorkChangeListener {
    public static final String DEFAULT_LONGLINK_GROUP = "default-group";
    public static final String DEFAULT_LONGLINK_NAME = "default-longlink";
    public static final String DEFAULT_PUSH_LONGLINK_NAME = "push-longlink";
    public static final String DEFAULT_QUICLINK_NAME = "quic";
    private static final String TAG = "HyMars";
    private static final String THREAD_NAME_PREFIX = "DispatchThread-";
    public static final int pushNeedAck = 1;
    private String appId;
    private Context context;
    private boolean isTest;
    private boolean isZsPlatform;
    private final String mAppFilePath;
    private HysignalDns mDns;
    private HySignalReportListener mHySignalReportListener;
    private ArrayList<String> mShortHosts = new ArrayList<>();
    private Map<Integer, Call> mCalls = new ConcurrentHashMap();
    private AppLogic.AccountInfo accountInfo = new AppLogic.AccountInfo();
    private AppLogic.DeviceInfo info = new AppLogic.DeviceInfo(Build.MANUFACTURER + "-" + Build.MODEL, "android-" + Build.VERSION.SDK_INT);
    private final List<NSLongLinkApi.PushListener> mPushListeners = new ArrayList();
    private final List<PushListener> mDeprecatedPushListeners = new ArrayList();
    private final Map<String, List<NSNetUtilApi.LinkStatusListener>> mLinkStatusListeners = new ConcurrentHashMap();
    private final Map<String, Integer> mLinkStatusMap = new ConcurrentHashMap();
    private HandlerThreadWrapper sDispatchThread = new HandlerThreadWrapper("loop");
    private HandlerThreadWrapper sReportThread = new HandlerThreadWrapper(ICommonConstants.FuncName.METRIC_REPORT);
    private HandlerThreadWrapper sPushThread = new HandlerThreadWrapper(INewUrl.KEY_S_PUSH);
    private boolean isCreatePushLink = false;
    private long lastCount = 0;
    private long currCount = 0;
    private long lastBw = 0;
    private long currentBw = 0;
    private long recvMsgCount = 0;
    private final long REPORT_MSG_LIMIT = 100;
    private final ArrayList<Long> cacheAckMsgId = new ArrayList<>();
    private volatile boolean isWaitingAck = false;
    private ScheduledExecutorService pushAckService = Executors.newScheduledThreadPool(1);
    private Runnable requestAckRunnable = new Runnable() { // from class: com.huya.hysignal.core.HyMars.1
        @Override // java.lang.Runnable
        public void run() {
            HyMars.this.ackPushMsg();
        }
    };
    private final long minPushAckDelayTime = 1000;
    private long pushAckDelayMillSecond = 1000;

    @Override // com.tencent.mars.app.AppLogic.ICallBack
    public int getClientVersion() {
        return 100;
    }

    @Override // com.tencent.mars.stn.StnLogic.ICallBack
    public boolean isLogoned() {
        return false;
    }

    @Override // com.tencent.mars.stn.StnLogic.ICallBack
    public boolean makesureAuthed() {
        return true;
    }

    @Override // com.tencent.mars.stn.StnLogic.ICallBack
    public boolean onLongLinkIdentifyResp(byte[] bArr, byte[] bArr2) {
        return false;
    }

    @Override // com.tencent.mars.sdt.SdtLogic.ICallBack
    public void reportSignalDetectResults(String str) {
    }

    @Override // com.tencent.mars.stn.StnLogic.ICallBack
    public void requestDoSync() {
    }

    @Override // com.tencent.mars.stn.StnLogic.ICallBack
    public String[] requestNetCheckShortLinkHosts() {
        return new String[0];
    }

    @Override // com.tencent.mars.stn.StnLogic.ICallBack
    public void trafficData(int i, int i2) {
    }

    static /* synthetic */ long access$108(HyMars hyMars) {
        long j = hyMars.currCount;
        hyMars.currCount = 1 + j;
        return j;
    }

    HyMars(HySignalConfig hySignalConfig) {
        this.appId = "";
        this.isZsPlatform = false;
        this.context = hySignalConfig.mContext;
        this.isTest = hySignalConfig.mTest;
        this.mDns = hySignalConfig.mHySignalDns;
        this.mAppFilePath = getAppFilePath(hySignalConfig.mContext);
        this.mShortHosts.add(hySignalConfig.mShortLinkHost);
        if (!TextUtils.isEmpty(hySignalConfig.appSrc) || hySignalConfig.appSrc.split("&")[0] != null) {
            this.appId = hySignalConfig.appSrc.split("&")[0];
            if (hySignalConfig.appSrc.contains("zs")) {
                this.isZsPlatform = true;
            }
        }
        try {
            AppLogic.setCallBack(this);
            StnLogic.setCallBack(this);
            SdtLogic.setCallBack(this);
            StnLogic.setEncryptKey(hySignalConfig.mEncryptKey);
            if (hySignalConfig.mEnableStrictIdle) {
                setEnableStrictIdle(true);
            }
            initDynamicConfig(hySignalConfig.mDynamicConfig);
            Mars.init(hySignalConfig.mContext, new Handler(Looper.getMainLooper()));
            String str = hySignalConfig.mLongLinkHost;
            if (!this.isCreatePushLink && Constants.CHINA_NEW_REQUEST_LONG_HOST.equals(str)) {
                str = Constants.NATIONAL_LONG_LINK_HOST;
            }
            if (hySignalConfig.mDebug) {
                setShortlinkSvrAddrWithTryCatch(hySignalConfig.mDebugPort, hySignalConfig.mDebugIP);
                setLonglinkSvrAddrWithTryCatch(str, new int[]{hySignalConfig.mDebugPort}, hySignalConfig.mDebugIP);
                setQuiclinkSvrAddrWithTryCatch(hySignalConfig.mQuicLinkHost, new int[]{hySignalConfig.mDebugPort}, hySignalConfig.mDebugIP);
            } else {
                StnLogic.setShortlinkSvrAddr(443);
                StnLogic.setLonglinkSvrAddr(str, new int[]{443});
                setQuiclinkSvrAddrWithTryCatch(hySignalConfig.mQuicLinkHost, new int[]{443}, null);
            }
            setClientVersionWithTryCatch(100);
            if (hySignalConfig.isTest() || hySignalConfig.isDebug()) {
                setTagQuicLogEnable(true);
            }
            setHuyaGuid(FieldsCache.getInstance().getGuid());
            setHuyaUid(hySignalConfig.getUid());
            setHuyaUA(hySignalConfig.getUa());
            setHuyaAppsrc(hySignalConfig.getAppSrc());
            setHuyaExperiment(hySignalConfig.getExperiment());
            setHuyaDeviceid(FieldsCache.getInstance().getDeviceId());
            setHuyaMid(FieldsCache.getInstance().getMid());
            initBackupIPList();
            Mars.onCreate(true);
            BaseEvent.onForeground(true);
            StnLogic.makesureLongLinkConnectedWithTryCatch();
            if (this.isCreatePushLink) {
                createLonglinkWithTryCatch(new StnLogic.LongLinkConfig("push-longlink", "default-group", hySignalConfig.getLongLinkHost().equals(Constants.CHINA_NEW_REQUEST_LONG_HOST) ? Constants.NATIONAL_LONG_LINK_HOST : hySignalConfig.getLongLinkHost(), false, true));
            }
        } catch (UnsatisfiedLinkError e) {
            HySignalLog.error(TAG, "init mars failed, error: %s", e.toString());
        }
        hySignalConfig.mContext.registerReceiver(new BaseEvent.ConnectionReceiver(this), new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
        if (hySignalConfig.isDebug()) {
            Executors.newScheduledThreadPool(1).scheduleAtFixedRate(new Runnable() { // from class: com.huya.hysignal.core.HyMars.2
                @Override // java.lang.Runnable
                public void run() {
                    HySignalLog.debug(HyMars.TAG, "currentQPS: " + (HyMars.this.currCount - HyMars.this.lastCount) + ", bw:" + (HyMars.this.currentBw - HyMars.this.lastBw));
                    HyMars hyMars = HyMars.this;
                    hyMars.lastCount = hyMars.currCount;
                    HyMars hyMars2 = HyMars.this;
                    hyMars2.lastBw = hyMars2.currentBw;
                }
            }, OAIDHelper.TIMEOUT, 1000L, TimeUnit.MILLISECONDS);
        }
    }

    Call newCall(com.huya.mtp.hyns.api.Request request) {
        ArrayList arrayList = new ArrayList();
        if (request.shortHost != null && request.shortHost.length() > 0) {
            arrayList.add(request.shortHost);
        } else {
            arrayList.addAll(this.mShortHosts);
        }
        return RealCall.newRealCall(arrayList, request, this);
    }

    void startTask(StnLogic.Task task, Call call) {
        if (task != null) {
            this.mCalls.put(Integer.valueOf(task.taskID), call);
            StnLogic.startTaskWirhTryCatch(task);
        }
    }

    void stopTask(StnLogic.Task task) {
        if (task != null) {
            int i = task.taskID;
            RealCall realCall = (RealCall) this.mCalls.remove(Integer.valueOf(i));
            if (realCall != null) {
                StnLogic.stopTask(i);
                cancelCallback(realCall);
            }
        }
    }

    void cancelCallback(final RealCall realCall) {
        dispatch(new Runnable() { // from class: com.huya.hysignal.core.HyMars.3
            @Override // java.lang.Runnable
            public void run() {
                realCall.handleCallback(null, 10, 0);
            }
        });
    }

    void setProxy(String str, int i) {
        StnLogic.setShortlinkSvrAddrWithTryCatch(i, str);
    }

    boolean addPushListener(NSLongLinkApi.PushListener pushListener) {
        if (pushListener == null) {
            return false;
        }
        synchronized (this.mPushListeners) {
            if (this.mPushListeners.contains(pushListener)) {
                return false;
            }
            this.mPushListeners.add(pushListener);
            return true;
        }
    }

    boolean removePushListener(NSLongLinkApi.PushListener pushListener) {
        boolean zRemove;
        synchronized (this.mPushListeners) {
            zRemove = this.mPushListeners.remove(pushListener);
        }
        return zRemove;
    }

    @Deprecated
    boolean addPushListener(PushListener pushListener) {
        if (pushListener == null) {
            return false;
        }
        synchronized (this.mDeprecatedPushListeners) {
            if (this.mDeprecatedPushListeners.contains(pushListener)) {
                return false;
            }
            this.mDeprecatedPushListeners.add(pushListener);
            return true;
        }
    }

    @Deprecated
    boolean removePushListener(PushListener pushListener) {
        boolean zRemove;
        if (pushListener == null) {
            return false;
        }
        synchronized (this.mDeprecatedPushListeners) {
            zRemove = this.mDeprecatedPushListeners.remove(pushListener);
        }
        return zRemove;
    }

    boolean addLinkStatusListener(String str, NSNetUtilApi.LinkStatusListener linkStatusListener) {
        if (str == null || "".equals(str)) {
            str = "push-longlink";
        }
        synchronized (this.mLinkStatusListeners) {
            List<NSNetUtilApi.LinkStatusListener> list = this.mLinkStatusListeners.get(str);
            if (list == null) {
                ArrayList arrayList = new ArrayList();
                arrayList.add(linkStatusListener);
                this.mLinkStatusListeners.put(str, arrayList);
                return true;
            }
            if (list.contains(linkStatusListener)) {
                return false;
            }
            return list.add(linkStatusListener);
        }
    }

    boolean removeLinkStatusListener(String str, NSNetUtilApi.LinkStatusListener linkStatusListener) {
        if (str == null || "".equals(str)) {
            str = "push-longlink";
        }
        synchronized (this.mLinkStatusListeners) {
            List<NSNetUtilApi.LinkStatusListener> list = this.mLinkStatusListeners.get(str);
            if (list == null) {
                return false;
            }
            return list.remove(linkStatusListener);
        }
    }

    void setReportListener(HySignalReportListener hySignalReportListener) {
        this.mHySignalReportListener = hySignalReportListener;
    }

    @Deprecated
    int getLinkStatus() {
        return getLinkStatus(null);
    }

    int getLinkStatus(String str) {
        Integer num;
        if (str == null || "".equals(str)) {
            str = "push-longlink";
        }
        synchronized (this.mLinkStatusMap) {
            num = this.mLinkStatusMap.get(str);
        }
        if (num == null) {
            if (str.equals("quic")) {
            }
            return 0;
        }
        return num.intValue();
    }

    boolean isEnableMutliConn() {
        return this.isCreatePushLink;
    }

    int getLocalIPStack() {
        return StnLogic.getLocalIPStack();
    }

    void setHySignalDns(HysignalDns hysignalDns) {
        this.mDns = hysignalDns;
    }

    void setAutoConnectInterval(long j) {
        if (j < 0) {
            j = 0;
        }
        StnLogic.setAutoConnectInterval(j);
    }

    void setHuyaExperiment(String str) {
        if (str != null) {
            try {
                StnLogic.setHuyaExperiment(str);
            } catch (UnsatisfiedLinkError e) {
                HySignalLog.error(TAG, "invoke setHuyaExperiment failed: %s", e.toString());
            }
        }
    }

    void setHuyaUid(long j) {
        if (j >= 0) {
            try {
                StnLogic.setHuyaUid(String.valueOf(j));
            } catch (UnsatisfiedLinkError e) {
                HySignalLog.error(TAG, "invoke setHuyaUid failed: %s", e.toString());
            }
        }
    }

    void setHuyaGuid(String str) {
        if (str == null || !HySignalLaunch.getInstance().isGuidApproved(str)) {
            return;
        }
        try {
            StnLogic.setHuyaGuid(str);
        } catch (UnsatisfiedLinkError e) {
            HySignalLog.error(TAG, "invoke setHuyaGuid failed: %s, retry", e.toString());
            try {
                StnLogic.setHuyaGuid(str);
            } catch (UnsatisfiedLinkError e2) {
                HySignalLog.error(TAG, "retry invoke setHuyaGuid failed:%s, skip", e2.toString());
            }
        }
    }

    void setHuyaUA(String str) {
        if (str != null) {
            try {
                StnLogic.setHuyaUA(str);
            } catch (UnsatisfiedLinkError e) {
                HySignalLog.error(TAG, "invoke setHuyaUA failed: %s", e.toString());
            }
        }
    }

    void setHuyaDeviceid(String str) {
        if (str != null) {
            try {
                StnLogic.setHuyaDeviceid(str);
            } catch (UnsatisfiedLinkError e) {
                HySignalLog.error(TAG, "invoke setHuyaDeviceid failed: %s", e.toString());
            }
        }
    }

    void setHuyaAppsrc(String str) {
        if (str != null) {
            try {
                StnLogic.setHuyaAppSrc(str);
            } catch (UnsatisfiedLinkError e) {
                HySignalLog.error(TAG, "invoke setHuyaAppsrc failed: %s", e.toString());
            }
        }
    }

    void setHuyaMid(String str) {
        if (str == null || str.isEmpty()) {
            return;
        }
        try {
            StnLogic.setHuyaMid(str);
        } catch (UnsatisfiedLinkError e) {
            HySignalLog.error(TAG, "invoke setHuyaMid failed: %s", e.toString());
        }
    }

    void setTagQuicLogEnable(boolean z) {
        try {
            StnLogic.setQuicLogEnable(z);
        } catch (UnsatisfiedLinkError e) {
            HySignalLog.error(TAG, "invoke setTagQuicLogEnable failed: %s", e.toString());
        }
    }

    void setBackUpIPs(String str, String[] strArr) {
        try {
            StnLogic.setBackupIPs(str, strArr);
        } catch (UnsatisfiedLinkError e) {
            HySignalLog.error(TAG, "invoke setBackUpIPs failed: %s", e.toString());
        }
    }

    @Override // com.tencent.mars.stn.StnLogic.ICallBack
    public String[] onNewDns(String str) {
        HysignalDns hysignalDns;
        if (this.isTest || (hysignalDns = this.mDns) == null) {
            return null;
        }
        return hysignalDns.onDns(str, 1800L);
    }

    @Override // com.tencent.mars.stn.StnLogic.ICallBack
    public void onPush(final String str, final int i, String str2, final int i2, final byte[] bArr) {
        dispatchPush(new Runnable() { // from class: com.huya.hysignal.core.HyMars.4
            @Override // java.lang.Runnable
            public void run() {
                HyMars.this.currentBw += (long) bArr.length;
                HyMars.access$108(HyMars.this);
                try {
                    JceInputStream jceInputStream = new JceInputStream(bArr);
                    if (i == 22) {
                        WSPushMessage_V2 wSPushMessage_V2 = new WSPushMessage_V2();
                        wSPushMessage_V2.readFrom(jceInputStream);
                        ArrayList<WSMsgItem> vMsgItem = wSPushMessage_V2.getVMsgItem();
                        if (vMsgItem != null) {
                            boolean z = true;
                            for (WSMsgItem wSMsgItem : vMsgItem) {
                                HyMars.this.dispatchPushMsg((int) wSMsgItem.iUri, wSMsgItem.sMsg, wSPushMessage_V2.sGroupId, wSMsgItem.lMsgId, str);
                                if (z) {
                                    HyMars.this.reportAndAckPush(i, wSMsgItem.sMsg, wSMsgItem.iUri, wSMsgItem.lMsgId, i2, vMsgItem.size(), 0);
                                    z = false;
                                }
                            }
                            return;
                        }
                        return;
                    }
                    if (i == 7) {
                        WSPushMessage wSPushMessage = new WSPushMessage();
                        wSPushMessage.readFrom(jceInputStream);
                        HyMars.this.dispatchPushMsg((int) wSPushMessage.iUri, wSPushMessage.sMsg, wSPushMessage.sGroupId, wSPushMessage.lMsgId, str);
                        HyMars.this.reportAndAckPush(i, wSPushMessage.sMsg, wSPushMessage.iUri, wSPushMessage.lMsgId, i2, 1, wSPushMessage.iMsgTag);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    HySignalLog.error(HyMars.TAG, "onPush error: %s", e.getMessage());
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispatchPushMsg(int i, byte[] bArr, String str, long j, String str2) {
        synchronized (this.mPushListeners) {
            for (NSLongLinkApi.PushListener pushListener : this.mPushListeners) {
                if (pushListener != null) {
                    pushListener.onPush(new NSLongLinkApi.HySignalMessage(i, bArr, str, j, false, str2));
                }
            }
        }
        synchronized (this.mDeprecatedPushListeners) {
            for (PushListener pushListener2 : this.mDeprecatedPushListeners) {
                if (pushListener2 != null) {
                    pushListener2.onPush(new HySignalMessage(i, bArr, str, j, false, str2));
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reportAndAckPush(final int i, final byte[] bArr, final long j, final long j2, final int i2, final int i3, final int i4) {
        this.recvMsgCount++;
        if ((i4 & 1) == 1) {
            synchronized (this.cacheAckMsgId) {
                this.cacheAckMsgId.add(Long.valueOf(j2));
            }
            if (!this.isWaitingAck) {
                this.pushAckService.schedule(this.requestAckRunnable, this.pushAckDelayMillSecond, TimeUnit.MILLISECONDS);
                this.isWaitingAck = true;
            }
        }
        if (j2 != 1090001) {
            if (this.isZsPlatform && this.recvMsgCount >= 100) {
                this.recvMsgCount = 0L;
            } else if (!NSPushReporter.getInstance().isNeedReport(j2)) {
                return;
            }
        }
        dispatchReport(new Runnable() { // from class: com.huya.hysignal.core.HyMars.5
            @Override // java.lang.Runnable
            public void run() {
                MonitorReqData monitorReqData = new MonitorReqData();
                monitorReqData.sMetricName = NSPushReporter.NS_PUSH_REPORT_METRIC_NAME;
                monitorReqData.iTS = System.currentTimeMillis();
                monitorReqData.vDimension.add(new MonitorReqData.DimensionWrapper("platform", "adr"));
                monitorReqData.vDimension.add(new MonitorReqData.DimensionWrapper("device", Build.MODEL));
                monitorReqData.vDimension.add(new MonitorReqData.DimensionWrapper("appid", HyMars.this.appId));
                monitorReqData.vDimension.add(new MonitorReqData.DimensionWrapper(NSPushReporter.NS_PUSH_OSVER_KEY, String.valueOf(Build.VERSION.SDK_INT)));
                monitorReqData.vDimension.add(new MonitorReqData.DimensionWrapper("ns_version", "1.9.105-exvolley"));
                monitorReqData.vDimension.add(new MonitorReqData.DimensionWrapper(NSPushReporter.NS_PUSH_CMDID_KEY, String.valueOf(i)));
                monitorReqData.vDimension.add(new MonitorReqData.DimensionWrapper("uri", String.valueOf(j)));
                monitorReqData.vDimension.add(new MonitorReqData.DimensionWrapper(NSPushReporter.NS_PUSH_NEED_ACK, String.valueOf(i4)));
                monitorReqData.vField.add(new MonitorReqData.FieldWrapper(NSPushReporter.NS_PUSH_LENGTH_KEY, bArr != null ? r3.length * i3 : 0.0d));
                monitorReqData.vField.add(new MonitorReqData.FieldWrapper(NSPushReporter.NS_PUSH_COUNT, i3));
                monitorReqData.vField.add(new MonitorReqData.FieldWrapper(NSPushReporter.NS_PUSH_MESGID_KEY, j2));
                monitorReqData.vField.add(new MonitorReqData.FieldWrapper(NSPushReporter.NS_PUSH_ALL_CONSUME_TIME_KEY, i2));
                monitorReqData.vField.add(new MonitorReqData.FieldWrapper(NSPushReporter.NS_PUSH_SYS_CONSUME_TIME_KEY, Math.abs(System.currentTimeMillis() - ((j2 >> 23) + 1546272000000L))));
                monitorReqData.vField.add(new MonitorReqData.FieldWrapper(NSPushReporter.NS_PUSH_CLIENT_RECV_TIME_KEY, monitorReqData.iTS));
                MTPApi.MONITOR.request(monitorReqData);
            }
        });
    }

    @Override // com.tencent.mars.stn.StnLogic.ICallBack
    public void reportConnectInfo(final String str, int i, final int i2) {
        dispatch(new Runnable() { // from class: com.huya.hysignal.core.HyMars.6
            @Override // java.lang.Runnable
            public void run() {
                boolean z = str.equals("default-longlink") && !HyMars.this.isCreatePushLink;
                synchronized (HyMars.this.mLinkStatusMap) {
                    HyMars.this.mLinkStatusMap.put(str, Integer.valueOf(i2));
                    if (z) {
                        HyMars.this.mLinkStatusMap.put("push-longlink", Integer.valueOf(i2));
                    }
                }
                if (str.equals("push-longlink") || z) {
                    synchronized (HyMars.this.mDeprecatedPushListeners) {
                        for (PushListener pushListener : HyMars.this.mDeprecatedPushListeners) {
                            if (pushListener != null) {
                                pushListener.onLinkStateChange(i2);
                            }
                        }
                    }
                }
                if (str.equals("push-longlink") || z) {
                    synchronized (HyMars.this.cacheAckMsgId) {
                        HyMars.this.cacheAckMsgId.clear();
                    }
                    synchronized (HyMars.this.mPushListeners) {
                        for (NSLongLinkApi.PushListener pushListener2 : HyMars.this.mPushListeners) {
                            if (pushListener2 != null) {
                                pushListener2.onLinkStateChange(i2);
                            }
                        }
                    }
                }
                synchronized (HyMars.this.mLinkStatusListeners) {
                    for (String str2 : HyMars.this.mLinkStatusListeners.keySet()) {
                        if (str.equals(str2) || (str2.equals("push-longlink") && z)) {
                            List<NSNetUtilApi.LinkStatusListener> list = (List) HyMars.this.mLinkStatusListeners.get(str2);
                            if (list != null) {
                                for (NSNetUtilApi.LinkStatusListener linkStatusListener : list) {
                                    if (linkStatusListener != null) {
                                        linkStatusListener.onLinkStateChange(str2, i2 == 4);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        });
    }

    @Override // com.tencent.mars.stn.StnLogic.ICallBack
    public void reportQuicStatus(final int i) {
        dispatch(new Runnable() { // from class: com.huya.hysignal.core.HyMars.7
            @Override // java.lang.Runnable
            public void run() {
                List<NSNetUtilApi.LinkStatusListener> list;
                synchronized (HyMars.this.mLinkStatusMap) {
                    HyMars.this.mLinkStatusMap.put("quic", Integer.valueOf(i));
                }
                synchronized (HyMars.this.mLinkStatusListeners) {
                    for (String str : HyMars.this.mLinkStatusListeners.keySet()) {
                        if (str.equals("quic") && (list = (List) HyMars.this.mLinkStatusListeners.get(str)) != null) {
                            for (NSNetUtilApi.LinkStatusListener linkStatusListener : list) {
                                if (linkStatusListener != null) {
                                    linkStatusListener.onLinkStateChange(str, i == 4);
                                }
                            }
                        }
                    }
                }
            }
        });
    }

    @Override // com.tencent.mars.stn.StnLogic.ICallBack
    public int getLongLinkIdentifyCheckBuffer(ByteArrayOutputStream byteArrayOutputStream, ByteArrayOutputStream byteArrayOutputStream2, int[] iArr) {
        return StnLogic.ECHECK_NEVER;
    }

    @Override // com.tencent.mars.stn.StnLogic.ICallBack
    public boolean req2Buf(int i, Object obj, ByteArrayOutputStream byteArrayOutputStream, int[] iArr, int i2) {
        com.huya.mtp.hyns.api.Request request;
        try {
            Call call = this.mCalls.get(Integer.valueOf(i));
            if (call == null || (request = call.request()) == null) {
                return false;
            }
            byteArrayOutputStream.write(request.getBody());
            return true;
        } catch (IOException e) {
            HySignalLog.error(TAG, e.getMessage());
            return false;
        }
    }

    @Override // com.tencent.mars.stn.StnLogic.ICallBack
    public int buf2Resp(int i, Object obj, final byte[] bArr, int[] iArr, int i2) {
        final RealCall realCall = (RealCall) this.mCalls.get(Integer.valueOf(i));
        if (realCall != null) {
            this.mCalls.remove(Integer.valueOf(i));
            dispatch(new Runnable() { // from class: com.huya.hysignal.core.HyMars.8
                @Override // java.lang.Runnable
                public void run() {
                    realCall.handleCallback(bArr, 0, 0);
                }
            });
        }
        return StnLogic.RESP_FAIL_HANDLE_NORMAL;
    }

    @Override // com.tencent.mars.stn.StnLogic.ICallBack
    public int onTaskEnd(int i, Object obj, final int i2, final int i3) {
        final RealCall realCall = (RealCall) this.mCalls.get(Integer.valueOf(i));
        if (realCall == null) {
            return 0;
        }
        this.mCalls.remove(Integer.valueOf(i));
        dispatch(new Runnable() { // from class: com.huya.hysignal.core.HyMars.9
            @Override // java.lang.Runnable
            public void run() {
                realCall.handleCallback(null, i2, i3);
            }
        });
        return 0;
    }

    @Override // com.tencent.mars.stn.StnLogic.ICallBack
    public void reportTaskProfile(final String str) {
        dispatchReport(new Runnable() { // from class: com.huya.hysignal.core.HyMars.10
            @Override // java.lang.Runnable
            public void run() {
                HySignalReportHelper.getsInstance().reportTaskProfile(HyMars.this.context, str, HyMars.this.mHySignalReportListener);
            }
        });
    }

    @Override // com.tencent.mars.stn.StnLogic.ICallBack
    public void onLinkConnectError(final String str, final int i, final long j, final int i2, final int i3, final String str2, final int i4) {
        dispatchReport(new Runnable() { // from class: com.huya.hysignal.core.HyMars.11
            @Override // java.lang.Runnable
            public void run() {
                HySignalReportHelper.getsInstance().onLinkConnectError(HyMars.this.context, str, i, j, i2, i3, str2, i4, HyMars.this.mHySignalReportListener);
            }
        });
    }

    @Override // com.tencent.mars.app.AppLogic.ICallBack
    public String getAppFilePath() {
        return this.mAppFilePath;
    }

    @Override // com.tencent.mars.app.AppLogic.ICallBack
    public AppLogic.AccountInfo getAccountInfo() {
        return this.accountInfo;
    }

    @Override // com.tencent.mars.app.AppLogic.ICallBack
    public AppLogic.DeviceInfo getDeviceType() {
        return this.info;
    }

    private void dispatch(Runnable runnable) {
        if (Thread.currentThread() == this.sDispatchThread.getThread()) {
            runnable.run();
        } else {
            this.sDispatchThread.getHandler().post(runnable);
        }
    }

    private void dispatchReport(Runnable runnable) {
        if (Thread.currentThread() == this.sReportThread.getThread()) {
            runnable.run();
        } else {
            this.sReportThread.getHandler().post(runnable);
        }
    }

    private void dispatchPush(Runnable runnable) {
        if (Thread.currentThread() == this.sPushThread.getThread()) {
            runnable.run();
        } else {
            this.sPushThread.getHandler().post(runnable);
        }
    }

    private String getAppFilePath(Context context) {
        try {
            File filesDir = context.getFilesDir();
            if (!filesDir.exists()) {
                filesDir.createNewFile();
            }
            return filesDir.toString();
        } catch (Exception unused) {
            return null;
        }
    }

    void onGuid(String str) {
        if (str == null || str.length() <= 0) {
            HySignalLog.error(TAG, "guid is wrong");
        } else {
            setHuyaGuid(str);
        }
    }

    @Override // com.tencent.mars.NetWorkChangeListener
    public void onNetWorkChange() {
        HySignalLaunch.getInstance().queryGuid(null);
    }

    /* JADX WARN: Removed duplicated region for block: B:128:0x01e7 A[Catch: UnsatisfiedLinkError -> 0x0211, TryCatch #1 {UnsatisfiedLinkError -> 0x0211, blocks: (B:4:0x001c, B:7:0x0024, B:10:0x002e, B:12:0x003a, B:14:0x0040, B:16:0x0046, B:17:0x004b, B:18:0x004f, B:21:0x0057, B:40:0x0099, B:43:0x00a1, B:62:0x00e3, B:64:0x00e9, B:66:0x00f5, B:68:0x00fb, B:70:0x0101, B:71:0x0106, B:72:0x010a, B:74:0x0110, B:76:0x011a, B:78:0x0126, B:79:0x012a, B:81:0x0130, B:82:0x0136, B:84:0x0140, B:86:0x0144, B:87:0x0149, B:89:0x0151, B:91:0x015d, B:93:0x0163, B:95:0x0169, B:96:0x016e, B:97:0x0172, B:99:0x0178, B:100:0x017e, B:104:0x018b, B:102:0x0183, B:105:0x018e, B:107:0x0194, B:109:0x01a0, B:111:0x01a6, B:113:0x01ac, B:114:0x01b3, B:115:0x01b7, B:117:0x01bd, B:120:0x01c7, B:128:0x01e7, B:121:0x01cd, B:124:0x01d3, B:129:0x01e9, B:131:0x01ef, B:138:0x0204, B:140:0x020c, B:45:0x00a7, B:47:0x00b9, B:49:0x00bf, B:60:0x00db, B:53:0x00c8, B:55:0x00ce, B:57:0x00d4, B:61:0x00df, B:23:0x005d, B:25:0x006f, B:27:0x0075, B:38:0x0091, B:31:0x007e, B:33:0x0084, B:35:0x008a, B:39:0x0095, B:143:0x0214), top: B:149:0x001c, inners: #2, #3, #4 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void initDynamicConfig(java.util.Map<java.lang.String, java.lang.String> r17) {
        /*
            Method dump skipped, instruction units count: 553
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huya.hysignal.core.HyMars.initDynamicConfig(java.util.Map):void");
    }

    private static synchronized void setEnableStrictIdle(boolean z) {
        WakerLock.useWakeLock = !z;
        StnLogic.setEnableIdleBreak(z);
    }

    /* JADX WARN: Multi-variable type inference failed */
    void updateDynamicConfig(Map<String, String> map) {
        int i;
        String str = TAG;
        if (map == null || map.isEmpty()) {
            HySignalLog.info(TAG, "update dynamic config is empty");
            return;
        }
        HySignalLog.info(TAG, "update dynamicConfig map:" + transMapToString(map));
        if (map.containsKey(Constants.ENABLE_TLS_1_3_KEY)) {
            String str2 = map.get(Constants.ENABLE_TLS_1_3_KEY);
            if (!TextUtils.isEmpty(str2) && !Bugly.SDK_IS_DEV.equals(str2) && !"0".equals(str2)) {
                StnLogic.setEnableNewTls(true);
            } else {
                StnLogic.setEnableNewTls(false);
            }
        }
        if (map.containsKey(Constants.NS_IP_SORT_STRATEGY)) {
            try {
                i = Integer.parseInt(map.get(Constants.NS_IP_SORT_STRATEGY));
            } catch (Exception unused) {
                HySignalLog.error(TAG, "ip sort strategy type not int!");
                i = 0;
            }
            if (i >= 0) {
                StnLogic.setIPSortStrategy(i);
            }
        }
        if (map.containsKey(Constants.NS_PUSH_ACK_DELAY_MILLISECONDS)) {
            String str3 = map.get(Constants.NS_PUSH_ACK_DELAY_MILLISECONDS);
            long j = 0;
            j = 0;
            try {
                if (str3 == null) {
                    HySignalLog.error(TAG, "delayTime str is empty");
                    str = str;
                } else {
                    long j2 = Long.parseLong(str3);
                    j = j2;
                    str = j2;
                }
            } catch (Exception e) {
                HySignalLog.error(str, "delayTime type not long! err: %s", e.getMessage());
            }
            if (j > 1000) {
                this.pushAckDelayMillSecond = j;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ackPushMsg() {
        ThreadManager.deliverOnRequestThread(new Runnable() { // from class: com.huya.hysignal.core.HyMars.12
            @Override // java.lang.Runnable
            public void run() {
                ArrayList<Long> arrayList;
                synchronized (HyMars.this.cacheAckMsgId) {
                    arrayList = new ArrayList<>(HyMars.this.cacheAckMsgId);
                    HyMars.this.cacheAckMsgId.clear();
                }
                HySignalLog.info(HyMars.TAG, "ackPushMsg will ack msgIds: %s", HyMars.transListToString(arrayList));
                if (!HySignalClient.getInstance().isLongLinkConnected(5)) {
                    HySignalLog.error(HyMars.TAG, "ackPushMsg failed, longLink is not connected");
                    HyMars.this.isWaitingAck = false;
                } else {
                    WSMsgAckReq wSMsgAckReq = new WSMsgAckReq();
                    wSMsgAckReq.setVMsgId(arrayList);
                    HySignalClient.getInstance().newCall(new Request.Builder().cmdId(35).cgi("/cmdid/35").channel(5).body(wSMsgAckReq.toByteArray()).networkStatusSensitive(true).build()).enqueue(new Callback() { // from class: com.huya.hysignal.core.HyMars.12.1
                        @Override // com.huya.hysignal.core.Callback
                        public void onResponse(byte[] bArr, HySignalError hySignalError) {
                            HyMars.this.isWaitingAck = false;
                            if (hySignalError.getErrType() != 0) {
                                HySignalLog.error(HyMars.TAG, "ackPushMsg failed, errType: %d, code: %d", Integer.valueOf(hySignalError.getErrType()), Integer.valueOf(hySignalError.getErrCode()));
                                return;
                            }
                            WSMsgAckRsp wSMsgAckRsp = (WSMsgAckRsp) JceParser.parseJce(bArr, new WSMsgAckRsp());
                            if (wSMsgAckRsp == null) {
                                HySignalLog.error(HyMars.TAG, "ackPushMsg failed, WSMsgAckRsp is empty");
                            } else {
                                HySignalLog.info(HyMars.TAG, "ackPushMsg success, iRet: %d", Integer.valueOf(wSMsgAckRsp.getIRet()));
                            }
                        }
                    });
                }
            }
        });
    }

    private static String transMapToString(Map map) {
        if (map == null || map.isEmpty()) {
            return "empty";
        }
        StringBuilder sb = new StringBuilder();
        Iterator it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            if (entry.getKey() != null) {
                sb.append(entry.getKey().toString());
                sb.append("'");
                sb.append(entry.getValue() == null ? "" : entry.getValue().toString());
                sb.append(it.hasNext() ? "^" : "");
            }
        }
        return sb.toString();
    }

    private void initBackupIPList() {
        String[] strArr = {"39.106.195.53", "39.106.195.149", "47.100.180.125", "47.100.183.245", "120.78.220.139", "39.108.135.122", "120.79.64.157"};
        setBackUpIPs("testws.va.huya.com", new String[]{"14.29.112.71", "14.29.112.73"});
        setBackUpIPs("testws.master.live", new String[]{"52.66.11.39", "3.6.22.151"});
        setBackUpIPs(Constants.NATIONAL_LONG_LINK_HOST, strArr);
        setBackUpIPs("cdn.wup.huya.com", strArr);
        setBackUpIPs(Constants.CHINA_NEW_REQUEST_LONG_HOST, strArr);
        setBackUpIPs(Constants.CHINA_QUIC_HOST, strArr);
        setBackUpIPs("wsapi.master.live", new String[]{"13.248.132.233", "76.223.5.114"});
    }

    private void setShortlinkSvrAddrWithTryCatch(int i, String str) {
        try {
            StnLogic.setShortlinkSvrAddr(i, str);
        } catch (UnsatisfiedLinkError e) {
            try {
                HySignalLog.error(TAG, "setShortlinkSvrAddrWithTryCatch error: %s", e.toString());
                StnLogic.setShortlinkSvrAddr(i, str);
            } catch (UnsatisfiedLinkError e2) {
                HySignalLog.error(TAG, "setShortlinkSvrAddrWithTryCatch again error: %s", e2.toString());
            }
        }
    }

    private void setLonglinkSvrAddrWithTryCatch(String str, int[] iArr, String str2) {
        try {
            try {
                StnLogic.setLonglinkSvrAddr(str, iArr, str2);
            } catch (UnsatisfiedLinkError unused) {
                StnLogic.setLonglinkSvrAddr(str, iArr, str2);
            }
        } catch (UnsatisfiedLinkError e) {
            HySignalLog.error(TAG, "setLonglinkSvrAddrWithTryCatch again error: %s", e.toString());
        }
    }

    private void setQuiclinkSvrAddrWithTryCatch(String str, int[] iArr, String str2) {
        try {
            try {
                StnLogic.setQuiclinkSvrAddr(str, iArr, str2);
            } catch (UnsatisfiedLinkError unused) {
                StnLogic.setQuiclinkSvrAddr(str, iArr, str2);
            }
        } catch (UnsatisfiedLinkError e) {
            HySignalLog.error(TAG, "setQuiclinkSvrAddrWithTryCatch again error: %s", e.toString());
        }
    }

    private void setClientVersionWithTryCatch(int i) {
        try {
            try {
                StnLogic.setClientVersion(i);
            } catch (UnsatisfiedLinkError unused) {
                StnLogic.setClientVersion(i);
            }
        } catch (UnsatisfiedLinkError e) {
            HySignalLog.error(TAG, "setClientVersionWithTryCatch again error: %s", e.toString());
        }
    }

    private void createLonglinkWithTryCatch(StnLogic.LongLinkConfig longLinkConfig) {
        try {
            StnLogic.createLonglink(longLinkConfig);
        } catch (UnsatisfiedLinkError e) {
            HySignalLog.error(TAG, "createLonglinkWithTryCatch error: %s", e.toString());
            try {
                StnLogic.createLonglink(longLinkConfig);
            } catch (UnsatisfiedLinkError e2) {
                HySignalLog.error(TAG, "createLonglinkWithTryCatch again error: %s", e2.toString());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String transListToString(List<Long> list) {
        if (list == null || list.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (Long l : list) {
            if (l != null) {
                sb.append(l);
                sb.append(",");
            }
        }
        return sb.toString();
    }

    private static class HandlerThreadWrapper {
        private Handler mHandler;
        private HandlerThread mThread;

        HandlerThreadWrapper(String str) {
            this(str, null);
        }

        HandlerThreadWrapper(String str, Handler.Callback callback) {
            HandlerThread handlerThread = new HandlerThread(HyMars.THREAD_NAME_PREFIX + str);
            handlerThread.setPriority(10);
            this.mThread = handlerThread;
            handlerThread.start();
            this.mHandler = new Handler(handlerThread.getLooper(), callback);
        }

        Handler getHandler() {
            return this.mHandler;
        }

        Thread getThread() {
            return this.mThread;
        }
    }
}
