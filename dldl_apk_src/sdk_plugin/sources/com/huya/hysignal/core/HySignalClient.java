package com.huya.hysignal.core;

import android.app.Activity;
import android.app.Application;
import android.os.Build;
import android.os.Bundle;
import android.util.ArrayMap;
import com.huya.hysignal.bizreq.HySignalLaunch;
import com.huya.hysignal.bizreq.HySignalUserHeartBeat;
import com.huya.hysignal.bizreq.HyTimeSyncClient;
import com.huya.hysignal.core.Request;
import com.huya.hysignal.jce.WSUpdateUserInfoReq;
import com.huya.hysignal.listener.HySignalGuidListener;
import com.huya.hysignal.listener.PushListener;
import com.huya.hysignal.util.FieldsCache;
import com.huya.hysignal.util.HySignalFileUtil;
import com.huya.hysignal.util.HySignalLog;
import com.huya.hysignal.util.ThreadManager;
import com.huya.mtp.hyns.api.NSLongLinkApi;
import com.huya.mtp.hyns.api.NSNetUtilApi;
import com.huya.mtp.hyns.api.Request;
import com.huya.mtp.hyns.report.NSPushReporter;
import com.tencent.mars.BaseEvent;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public final class HySignalClient implements HySignalGuidListener {
    private static final String TAG = "HySignalClient";
    private static HySignalClient sInstance;
    private List<HySignalGuidListener> mGuidListeners = new ArrayList();
    private HyMars sHyMars;
    private boolean sInited;

    public interface RequestAllCallback {
        void onResp(String str, Response response);
    }

    private String channelTypeToName(int i) {
        return i != 2 ? i != 4 ? "push-longlink" : "quic" : "default-longlink";
    }

    private HySignalClient() {
    }

    public static synchronized HySignalClient getInstance() {
        if (sInstance == null) {
            sInstance = new HySignalClient();
        }
        return sInstance;
    }

    public synchronized boolean init(HySignalConfig hySignalConfig) {
        if (this.sInited) {
            HySignalLog.error(TAG, "hySignalClient has init");
            return false;
        }
        if (!checkConfig(hySignalConfig)) {
            return false;
        }
        Application application = (Application) hySignalConfig.mContext.getApplicationContext();
        HySignalFileUtil.copyCaFile(application);
        this.sHyMars = new HyMars(hySignalConfig);
        HySignalReportHelper.getsInstance().init(hySignalConfig.appSrc);
        if (hySignalConfig.mAutoUpdateInterval > 0) {
            this.sHyMars.setAutoConnectInterval(hySignalConfig.mAutoUpdateInterval);
        }
        this.sHyMars.setReportListener(hySignalConfig.mReportListener);
        addGuidListener(hySignalConfig.mGuidListener);
        HySignalProxy.init(hySignalConfig.mEnableProxy, hySignalConfig.mProxyIP, hySignalConfig.mProxyPort, this.sHyMars, application);
        application.registerActivityLifecycleCallbacks(new MonitorActivityCallbacks());
        this.sInited = true;
        HySignalLaunch.getInstance().init(hySignalConfig, hySignalConfig.mExistGuid, this, hySignalConfig.mContext);
        HySignalUserHeartBeat.init();
        HyTimeSyncClient.getInstance().init(hySignalConfig.mLongLinkHost);
        return true;
    }

    public Call newCall(com.huya.mtp.hyns.api.Request request) {
        if (request == null) {
            HySignalLog.error(TAG, "request is null");
            return new FakeCall(request);
        }
        if (!this.sInited) {
            HySignalLog.error(TAG, "new call need init");
            return new FakeCall(request);
        }
        if (HySignalProxy.isProxyEnable() && request.channel() == 3) {
            request = new Request.Builder().fromRequest(request).channel(1).build();
        }
        return this.sHyMars.newCall(request);
    }

    @Deprecated
    public Call newCall(Request request) {
        if (request == null) {
            HySignalLog.error(TAG, "request is null");
            return new FakeCall(request);
        }
        if (!this.sInited) {
            HySignalLog.error(TAG, "new call need init");
            return new FakeCall(request);
        }
        if (HySignalProxy.isProxyEnable() && request.channel() != 1) {
            request = new Request.Builder().fromRequest(request).channel(1).build();
        }
        return this.sHyMars.newCall(request.toNSRequest());
    }

    public boolean addPushListener(NSLongLinkApi.PushListener pushListener) {
        if (!this.sInited) {
            HySignalLog.error(TAG, "add push listener need init");
            return false;
        }
        if (pushListener == null) {
            HySignalLog.error(TAG, "addPushListener ia null");
            return false;
        }
        return this.sHyMars.addPushListener(pushListener);
    }

    public boolean removePushListener(NSLongLinkApi.PushListener pushListener) {
        if (!this.sInited) {
            HySignalLog.error(TAG, "remove push listener need init");
            return false;
        }
        if (pushListener == null) {
            HySignalLog.error(TAG, "removePushListener ia null");
            return false;
        }
        this.sHyMars.removePushListener(pushListener);
        return true;
    }

    @Deprecated
    public boolean addPushListener(PushListener pushListener) {
        if (!this.sInited) {
            HySignalLog.error(TAG, "add push listener need init");
            return false;
        }
        if (pushListener == null) {
            HySignalLog.error(TAG, "addPushListener ia null");
            return false;
        }
        return this.sHyMars.addPushListener(pushListener);
    }

    @Deprecated
    public boolean removePushListener(PushListener pushListener) {
        if (!this.sInited) {
            HySignalLog.error(TAG, "remove push listener need init");
            return false;
        }
        if (pushListener == null) {
            HySignalLog.error(TAG, "removePushListener ia null");
            return false;
        }
        this.sHyMars.removePushListener(pushListener);
        return true;
    }

    public boolean addLinkStatusListener(String str, NSNetUtilApi.LinkStatusListener linkStatusListener) {
        if (!this.sInited) {
            HySignalLog.error(TAG, "add quic listener need init");
            return false;
        }
        if (linkStatusListener == null) {
            HySignalLog.error(TAG, "addQuicListener ia null");
            return false;
        }
        return this.sHyMars.addLinkStatusListener(str, linkStatusListener);
    }

    public boolean addLinkStatusListener(int i, NSNetUtilApi.LinkStatusListener linkStatusListener) {
        return addLinkStatusListener(channelTypeToName(i), linkStatusListener);
    }

    public boolean removeLinkStatusListener(String str, NSNetUtilApi.LinkStatusListener linkStatusListener) {
        if (!this.sInited) {
            HySignalLog.error(TAG, "remove quic listener need init");
            return false;
        }
        if (linkStatusListener == null) {
            HySignalLog.error(TAG, "removeQuicListener ia null");
            return false;
        }
        this.sHyMars.removeLinkStatusListener(str, linkStatusListener);
        return true;
    }

    public boolean isLongLinkConnected(String str) {
        if (!this.sInited) {
            HySignalLog.error(TAG, "get link status need init");
            return false;
        }
        int linkStatus = this.sHyMars.getLinkStatus(str);
        return str.equals("quic") ? linkStatus == 4 : linkStatus == 4;
    }

    public boolean isLongLinkConnected(int i) {
        return isLongLinkConnected(channelTypeToName(i));
    }

    public boolean addGuidListener(HySignalGuidListener hySignalGuidListener) {
        if (this.mGuidListeners.contains(hySignalGuidListener)) {
            return false;
        }
        ArrayList arrayList = new ArrayList(this.mGuidListeners.size() + 1);
        arrayList.addAll(this.mGuidListeners);
        arrayList.add(hySignalGuidListener);
        this.mGuidListeners = arrayList;
        return true;
    }

    public boolean removeGuidListener(HySignalGuidListener hySignalGuidListener) {
        int iIndexOf = this.mGuidListeners.indexOf(hySignalGuidListener);
        if (iIndexOf == -1) {
            return false;
        }
        ArrayList arrayList = new ArrayList(this.mGuidListeners.size() - 1);
        arrayList.addAll(this.mGuidListeners.subList(0, iIndexOf));
        List<HySignalGuidListener> list = this.mGuidListeners;
        arrayList.addAll(list.subList(iIndexOf + 1, list.size()));
        this.mGuidListeners = arrayList;
        return true;
    }

    public boolean updateUid(long j) {
        if (!this.sInited) {
            HySignalLog.error(TAG, "updateUid need init");
            return false;
        }
        if (j < 0) {
            HySignalLog.error(TAG, "update uid < 0");
            return false;
        }
        this.sHyMars.setHuyaUid(j);
        HySignalLaunch.getInstance().updateUid(j);
        return true;
    }

    public boolean updateExperiment(String str) {
        if (!this.sInited) {
            HySignalLog.error(TAG, "update exp need init");
            return false;
        }
        if (str == null) {
            HySignalLog.error(TAG, "update empty experiment");
            return false;
        }
        this.sHyMars.setHuyaExperiment(str);
        return true;
    }

    public boolean updateAppSrc(String str) {
        if (!this.sInited) {
            HySignalLog.error(TAG, "update app src need init");
            return false;
        }
        if (empty(str)) {
            HySignalLog.error(TAG, "update empty app src");
            return false;
        }
        FieldsCache.getInstance().updateAppSrc(str);
        this.sHyMars.setHuyaAppsrc(str);
        HySignalReportHelper.getsInstance().updateAppSrc(str);
        updateRemoteUserInfo();
        return true;
    }

    public void requestAllDefaultChannel(com.huya.mtp.hyns.api.Request request, final RequestAllCallback requestAllCallback) {
        HySignalError hySignalError = new HySignalError(10, -1001);
        try {
            if (getInstance().isLongLinkConnected("default-longlink")) {
                getInstance().newCall(new Request.Builder().fromRequest(request).channel(2).build()).enqueue(new Callback() { // from class: com.huya.hysignal.core.HySignalClient.1
                    @Override // com.huya.hysignal.core.Callback
                    public void onResponse(byte[] bArr, HySignalError hySignalError2) {
                        requestAllCallback.onResp("Long", new Response(bArr, hySignalError2));
                    }
                });
            } else {
                requestAllCallback.onResp("Long", new Response(null, hySignalError));
            }
            if (this.sHyMars != null && this.sHyMars.isEnableMutliConn()) {
                if (getInstance().isLongLinkConnected("push-longlink")) {
                    getInstance().newCall(new Request.Builder().fromRequest(request).channel(5).build()).enqueue(new Callback() { // from class: com.huya.hysignal.core.HySignalClient.2
                        @Override // com.huya.hysignal.core.Callback
                        public void onResponse(byte[] bArr, HySignalError hySignalError2) {
                            requestAllCallback.onResp("PushChannel", new Response(bArr, hySignalError2));
                        }
                    });
                } else {
                    requestAllCallback.onResp("PushChannel", new Response(null, hySignalError));
                }
            }
            if (getInstance().isLongLinkConnected("quic")) {
                getInstance().newCall(new Request.Builder().fromRequest(request).channel(4).build()).enqueue(new Callback() { // from class: com.huya.hysignal.core.HySignalClient.3
                    @Override // com.huya.hysignal.core.Callback
                    public void onResponse(byte[] bArr, HySignalError hySignalError2) {
                        requestAllCallback.onResp("Quic", new Response(bArr, hySignalError2));
                    }
                });
            } else {
                requestAllCallback.onResp("Quic", new Response(null, hySignalError));
            }
        } catch (Exception e) {
            e.printStackTrace();
            HySignalLog.error(TAG, "request All err: %s", e.getMessage());
            requestAllCallback.onResp("All", new Response(null, new HySignalError(10, -1002)));
        }
    }

    public void updateRemoteUserInfo() {
        if (!this.sInited) {
            HySignalLog.error(TAG, "set push report ratio need init");
            return;
        }
        final int percentage = NSPushReporter.getInstance().getPercentage();
        final String appSrc = FieldsCache.getInstance().getAppSrc();
        HySignalLog.info(TAG, "updateRemoteUserInfo radio: %d, appSrc:%s", Integer.valueOf(percentage), appSrc);
        ThreadManager.deliverOnRequestThread(new Runnable() { // from class: com.huya.hysignal.core.HySignalClient.4
            @Override // java.lang.Runnable
            public void run() {
                WSUpdateUserInfoReq wSUpdateUserInfoReq = new WSUpdateUserInfoReq();
                wSUpdateUserInfoReq.iReportMsgIdRatio = percentage;
                wSUpdateUserInfoReq.sAppSrc = appSrc;
                wSUpdateUserInfoReq.iSupportAck = 1;
                HySignalClient.getInstance().requestAllDefaultChannel(new Request.Builder().cmdId(33).cgi("/cmdid/33").body(wSUpdateUserInfoReq.toByteArray()).build(), new RequestAllCallback() { // from class: com.huya.hysignal.core.HySignalClient.4.1
                    @Override // com.huya.hysignal.core.HySignalClient.RequestAllCallback
                    public void onResp(String str, Response response) {
                        if (response == null) {
                            HySignalLog.error("hysignal updateRemoteUserInfo channel: " + str + " rsp is empty");
                            return;
                        }
                        HySignalLog.info(HySignalClient.TAG, "hysignal updateRemoteUserInfo channel: " + str + "result: errType=" + response.error.getErrType() + " errCode=" + response.error.getErrCode());
                    }
                });
            }
        });
    }

    public void updateDynamicConfig(Map<String, String> map) {
        HyMars hyMars;
        if (!this.sInited || (hyMars = this.sHyMars) == null) {
            HySignalLog.error(TAG, "updateDynamicConfig need init");
        } else {
            hyMars.updateDynamicConfig(map);
        }
    }

    public String getGuid() {
        return FieldsCache.getInstance().getGuid();
    }

    public String getClientIp() {
        return HySignalLaunch.getInstance().getClientIp();
    }

    @Deprecated
    public int getLinkStatus() {
        HyMars hyMars = this.sHyMars;
        if (hyMars != null) {
            return hyMars.getLinkStatus();
        }
        return 0;
    }

    public int getLinkStatus(int i) {
        return getLinkStatus(channelTypeToName(i));
    }

    public int getLinkStatus(String str) {
        HyMars hyMars = this.sHyMars;
        if (hyMars != null) {
            return hyMars.getLinkStatus(str);
        }
        return 0;
    }

    public int getLocalIPStack() {
        HyMars hyMars = this.sHyMars;
        if (hyMars != null) {
            return hyMars.getLocalIPStack();
        }
        HySignalLog.error(TAG, "getLocalIPStack but not init");
        return 0;
    }

    private boolean checkConfig(HySignalConfig hySignalConfig) {
        if (hySignalConfig == null || hySignalConfig.mContext == null) {
            HySignalLog.error(TAG, "config or config.mContext can't be null");
            return false;
        }
        if (hySignalConfig.mLongLinkHost == null || "".equals(hySignalConfig.mLongLinkHost)) {
            HySignalLog.error(TAG, "mLongLinkHost is null or empty");
            return false;
        }
        if (hySignalConfig.mShortLinkHost == null || "".equals(hySignalConfig.mShortLinkHost)) {
            HySignalLog.error(TAG, "mShortLinkHost is null or empty");
            return false;
        }
        if (hySignalConfig.mHySignalDns != null) {
            return true;
        }
        HySignalLog.info(TAG, "httpdns is null, disable");
        return true;
    }

    @Override // com.huya.hysignal.listener.HySignalGuidListener
    public void onGuid(final String str) {
        this.sHyMars.onGuid(str);
        final List<HySignalGuidListener> list = this.mGuidListeners;
        ThreadManager.deliverOnResponseThread(new Runnable() { // from class: com.huya.hysignal.core.HySignalClient.5
            @Override // java.lang.Runnable
            public void run() {
                for (HySignalGuidListener hySignalGuidListener : list) {
                    if (hySignalGuidListener != null) {
                        hySignalGuidListener.onGuid(str);
                    }
                }
            }
        });
    }

    public boolean isMultiConnEnable() {
        HyMars hyMars = this.sHyMars;
        if (hyMars == null) {
            return false;
        }
        return hyMars.isEnableMutliConn();
    }

    private class MonitorActivityCallbacks implements Application.ActivityLifecycleCallbacks {
        private int mCount;

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityCreated(Activity activity, Bundle bundle) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityDestroyed(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPaused(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityResumed(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        MonitorActivityCallbacks() {
            this.mCount = 0;
            this.mCount = getStartedActivityCount();
        }

        private int getStartedActivityCount() {
            Map map;
            int i = 0;
            try {
                Class<?> cls = Class.forName("android.app.ActivityThread");
                Object objInvoke = cls.getMethod("currentActivityThread", new Class[0]).invoke(null, new Object[0]);
                Field declaredField = cls.getDeclaredField("mActivities");
                declaredField.setAccessible(true);
                if (Build.VERSION.SDK_INT < 19) {
                    map = (HashMap) declaredField.get(objInvoke);
                } else {
                    map = (ArrayMap) declaredField.get(objInvoke);
                }
                for (Object obj : map.values()) {
                    Field declaredField2 = obj.getClass().getDeclaredField("stopped");
                    declaredField2.setAccessible(true);
                    if (!declaredField2.getBoolean(obj)) {
                        i++;
                    }
                }
            } catch (Exception unused) {
            }
            return i;
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStarted(Activity activity) {
            if (this.mCount == 0) {
                BaseEvent.onForeground(true);
                HySignalUserHeartBeat.onForeground();
                HySignalLaunch.getInstance().onForeground();
            }
            this.mCount++;
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStopped(Activity activity) {
            int i = this.mCount - 1;
            this.mCount = i;
            if (i == 0) {
                BaseEvent.onForeground(false);
            }
        }
    }

    private static boolean empty(String str) {
        return str == null || str.length() <= 0;
    }
}
