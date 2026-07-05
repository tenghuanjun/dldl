package com.duowan.networkmars.hysignal;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.SystemClock;
import android.text.TextUtils;
import com.duowan.auk.util.L;
import com.huya.mtp.hyns.NS;
import com.huya.mtp.hyns.api.NSLongLinkApi;
import com.huya.mtp.hyns.api.NSPushControlApi;
import com.huya.mtp.hyns.api.NSRegisterApi;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class HySignalProxy implements NSLongLinkApi.PushListener {
    public static final String CHAT_GROUP = "chat:";
    private static final String LINK_MIC_GROUP = "linkmic:";
    public static final String LIVE_GROUP = "live:";
    private static final String PASSW0RD_CHAT_GROUP = "schat:";
    public static final String PASSW0RD_LIVE_GROUP = "slive:";
    private static final String TAG = "HySignalProxy";
    private static final HandlerThread sHysignalProxyPushMsgThread;
    private static HySignalProxy sInstance;
    private HySignalLinkStateListenter mLinkStateListenter;
    private Handler mPushHandler;
    private long mUid = -1;
    private boolean mIsInit = false;
    private long mMaxMessageCount = 100000;
    private long mLastLogTime = 0;
    private volatile long mMessageCount = 0;
    private volatile long mMessageCount1 = 0;
    private volatile long mMessageByteCount = 0;
    private volatile long mMessageByteCount1 = 0;
    private final Object mObserverLock = new Object();
    private List<ServicePushObserver> mPushObserverList = new ArrayList();
    private HySignalPushMessageListenter mListener = new HySignalPushMessageListenter() { // from class: com.duowan.networkmars.hysignal.HySignalProxy.1
        @Override // com.duowan.networkmars.hysignal.HySignalProxy.HySignalPushMessageListenter
        public void onReceiveMessage(NSLongLinkApi.HySignalMessage hySignalMessage) {
            synchronized (HySignalProxy.this.mObserverLock) {
                Iterator it = HySignalProxy.this.mPushObserverList.iterator();
                while (it.hasNext()) {
                    ((ServicePushObserver) it.next()).onReceiveEvent(hySignalMessage.getIUri(), hySignalMessage.getSMsg());
                }
            }
        }
    };
    private final Object mMessageCountLock = new Object();

    public interface HySignalLinkStateListenter {
        void onLinkStateChange(boolean z);
    }

    public interface HySignalPushMessageListenter {
        void onReceiveMessage(NSLongLinkApi.HySignalMessage hySignalMessage);
    }

    static {
        HandlerThread handlerThread = new HandlerThread("HysignalProxyPushMsgThread");
        sHysignalProxyPushMsgThread = handlerThread;
        handlerThread.start();
    }

    private HySignalProxy() {
    }

    public static synchronized HySignalProxy getInstance() {
        if (sInstance == null) {
            sInstance = new HySignalProxy();
        }
        return sInstance;
    }

    public void setPushMessageListenter(HySignalPushMessageListenter hySignalPushMessageListenter) {
        this.mListener = hySignalPushMessageListenter;
    }

    public void setLinkStateListenter(HySignalLinkStateListenter hySignalLinkStateListenter) {
        this.mLinkStateListenter = hySignalLinkStateListenter;
    }

    public void updateRegisterMsgUriSet(Set<Long> set) {
        ((NSPushControlApi) NS.get(NSPushControlApi.class)).updateRegisterMsgUriSet(set);
    }

    public void init() {
        if (this.mIsInit) {
            return;
        }
        this.mIsInit = true;
        L.info(TAG, "HySignalProxy init");
        this.mPushHandler = new Handler(sHysignalProxyPushMsgThread.getLooper());
        ((NSLongLinkApi) NS.get(NSLongLinkApi.class)).addPushListener(this);
        onLinkStateChange(((NSLongLinkApi) NS.get(NSLongLinkApi.class)).getLinkStatus());
    }

    public void registerGroupLive(NSRegisterApi.RegisterPushMsgListener registerPushMsgListener) {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add(LIVE_GROUP + this.mUid);
        arrayList.add(CHAT_GROUP + this.mUid);
        ((NSRegisterApi) NS.get(NSRegisterApi.class)).registerGroup(arrayList, registerPushMsgListener);
    }

    public void unRegisterGroupLive() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add(LIVE_GROUP + this.mUid);
        arrayList.add(CHAT_GROUP + this.mUid);
        ((NSRegisterApi) NS.get(NSRegisterApi.class)).unRegisterGroup(arrayList, null);
    }

    public void registerGroupLink(long j, NSRegisterApi.RegisterPushMsgListener registerPushMsgListener) {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add(LINK_MIC_GROUP + j);
        ((NSRegisterApi) NS.get(NSRegisterApi.class)).registerGroup(arrayList, registerPushMsgListener);
    }

    public void unRegisterGroupLink(long j) {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add(LINK_MIC_GROUP + j);
        ((NSRegisterApi) NS.get(NSRegisterApi.class)).unRegisterGroup(arrayList, null);
    }

    private ArrayList<String> getLiveGroups(long j, String str) {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add(LIVE_GROUP + j);
        arrayList.add(CHAT_GROUP + j);
        if (!TextUtils.isEmpty(str)) {
            arrayList.add(PASSW0RD_LIVE_GROUP + j + "-" + str);
            arrayList.add(PASSW0RD_CHAT_GROUP + j + "-" + str);
        }
        return arrayList;
    }

    public void unRegisterLiveGroups(String str) {
        ((NSRegisterApi) NS.get(NSRegisterApi.class)).unRegisterGroup(getLiveGroups(this.mUid, str), null);
    }

    public void registerLiveGroups(String str, NSRegisterApi.RegisterPushMsgListener registerPushMsgListener) {
        ((NSRegisterApi) NS.get(NSRegisterApi.class)).registerGroup(getLiveGroups(this.mUid, str), registerPushMsgListener);
    }

    public void registerLiveGroups(long j, NSRegisterApi.RegisterPushMsgListener registerPushMsgListener) {
        ((NSRegisterApi) NS.get(NSRegisterApi.class)).registerGroup(getLiveGroups(j, ""), registerPushMsgListener);
    }

    public void registerLiveGroups(long j, String str, NSRegisterApi.RegisterPushMsgListener registerPushMsgListener) {
        ((NSRegisterApi) NS.get(NSRegisterApi.class)).registerGroup(getLiveGroups(j, str), registerPushMsgListener);
    }

    public void unRegisterLiveGroups(long j) {
        unRegisterLiveGroups(j, "");
    }

    public void unRegisterLiveGroups(long j, String str) {
        unRegisterLiveGroups(j, str, null);
    }

    public void unRegisterLiveGroups(long j, String str, NSRegisterApi.UnRegisterPushMsgListener unRegisterPushMsgListener) {
        ((NSRegisterApi) NS.get(NSRegisterApi.class)).unRegisterGroup(getLiveGroups(j, str), unRegisterPushMsgListener);
    }

    @Override // com.huya.mtp.hyns.api.NSLongLinkApi.PushListener
    public void onLinkStateChange(int i) {
        Object[] objArr = new Object[2];
        objArr[0] = Boolean.valueOf(i == 4);
        objArr[1] = Integer.valueOf(i);
        L.info(TAG, "onLinkStateChange:%s,%d", objArr);
        HySignalLinkStateListenter hySignalLinkStateListenter = this.mLinkStateListenter;
        if (hySignalLinkStateListenter != null) {
            hySignalLinkStateListenter.onLinkStateChange(i == 4);
        }
    }

    @Override // com.huya.mtp.hyns.api.NSLongLinkApi.PushListener
    public void onPush(final NSLongLinkApi.HySignalMessage hySignalMessage) {
        synchronized (this.mMessageCountLock) {
            this.mMessageCount++;
            this.mMessageByteCount += (long) hySignalMessage.getSMsg().length;
        }
        if (this.mMessageCount > this.mMaxMessageCount) {
            long jUptimeMillis = SystemClock.uptimeMillis();
            if (jUptimeMillis - this.mLastLogTime > 10000) {
                this.mLastLogTime = jUptimeMillis;
                L.info(TAG, "onPush max count return " + this.mMessageCount);
                return;
            }
            return;
        }
        this.mPushHandler.post(new Runnable() { // from class: com.duowan.networkmars.hysignal.HySignalProxy.2
            @Override // java.lang.Runnable
            public void run() {
                synchronized (HySignalProxy.this.mMessageCountLock) {
                    HySignalProxy.this.mMessageCount--;
                    HySignalProxy.this.mMessageByteCount -= (long) hySignalMessage.getSMsg().length;
                }
                L.debug(HySignalProxy.TAG, "onPush uri= %s, groupId = %s", Integer.valueOf(hySignalMessage.getIUri()), hySignalMessage.getSGroupId());
                if (HySignalProxy.this.mListener == null) {
                    return;
                }
                HySignalProxy.this.mListener.onReceiveMessage(hySignalMessage);
            }
        });
    }

    public void clearLoginInfo() {
        this.mUid = -1L;
    }

    public void setLoginInfo(long j) {
        this.mUid = j;
    }

    public long getMessageCount() {
        return this.mMessageCount;
    }

    public long getMessageByteCount() {
        return this.mMessageByteCount;
    }

    public void setMaxMessageCount(long j) {
        this.mMaxMessageCount = j;
    }

    public void registerPushEvent(ServicePushObserver servicePushObserver) {
        synchronized (this.mObserverLock) {
            this.mPushObserverList.add(servicePushObserver);
        }
    }

    public void unregisterPushEvent(ServicePushObserver servicePushObserver) {
        synchronized (this.mObserverLock) {
            this.mPushObserverList.remove(servicePushObserver);
        }
    }
}
