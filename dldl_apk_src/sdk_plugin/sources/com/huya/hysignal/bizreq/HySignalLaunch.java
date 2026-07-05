package com.huya.hysignal.bizreq;

import android.content.Context;
import com.duowan.jce.wup.TafUniPacket;
import com.duowan.jce.wup.UniPacket;
import com.huya.hysignal.core.Callback;
import com.huya.hysignal.core.HySignalClient;
import com.huya.hysignal.core.HySignalConfig;
import com.huya.hysignal.core.HySignalError;
import com.huya.hysignal.jce.WSDeviceInfo;
import com.huya.hysignal.jce.WSLaunchReq;
import com.huya.hysignal.jce.WSLaunchRsp;
import com.huya.hysignal.listener.HySignalGuidListener;
import com.huya.hysignal.util.FieldsCache;
import com.huya.hysignal.util.HySignalLog;
import com.huya.hysignal.util.ThreadManager;
import com.huya.mtp.hyns.api.Request;
import com.tencent.mars.comm.NetStatusUtil;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public final class HySignalLaunch {
    private static final long FOREGROUND_CHANGE_QUERY_TIME = 1800000;
    private static final String TAG = "HySignalLaunch";
    private static HySignalLaunch sInstance;
    private Context context;
    private boolean sInit = false;
    private final int WUP_CMD_ID = 3;
    private HySignalGuidListener listener = null;
    private String mUa = "";
    private String mIMEI = "";
    private long lastStateChangeTime = 0;
    private String clientIp = "";
    private volatile boolean isQuery = false;
    private long mUid = 0;
    private boolean enableEncrypt = false;

    public interface QueryGuidCallback {
        void onResult(boolean z, String str);
    }

    private HySignalLaunch() {
    }

    public static HySignalLaunch getInstance() {
        if (sInstance == null) {
            sInstance = new HySignalLaunch();
        }
        return sInstance;
    }

    public synchronized void init(HySignalConfig hySignalConfig, String str, HySignalGuidListener hySignalGuidListener, Context context) {
        if (this.sInit) {
            return;
        }
        this.context = context;
        this.listener = hySignalGuidListener;
        updateUid(hySignalConfig.getUid());
        initConfig(hySignalConfig);
        setExistGuid(str);
        String guid = FieldsCache.getInstance().getGuid();
        if (guid != null && !"".equals(guid) && guid.length() > 0) {
            notifyGuidChange(guid);
        }
        this.sInit = true;
        queryGuid(null);
    }

    private void initConfig(HySignalConfig hySignalConfig) {
        this.enableEncrypt = hySignalConfig.getEnableEncrypt();
        if (!empty(hySignalConfig.getUa())) {
            this.mUa = hySignalConfig.getUa();
        }
        if (empty(hySignalConfig.getIMEI())) {
            return;
        }
        this.mIMEI = hySignalConfig.getIMEI();
    }

    public void updateUid(long j) {
        if (j >= 0) {
            this.mUid = j;
        }
    }

    public void OnQueryFinish(boolean z, String str, QueryGuidCallback queryGuidCallback) {
        if (queryGuidCallback == null) {
            return;
        }
        queryGuidCallback.onResult(z, str);
    }

    public void queryGuid(QueryGuidCallback queryGuidCallback) {
        if (!this.sInit) {
            HySignalLog.error(TAG, "query GUID need init");
            OnQueryFinish(false, "query GUID need init", queryGuidCallback);
        } else {
            ThreadManager.deliverOnRequestThread(new AnonymousClass1(queryGuidCallback));
        }
    }

    /* JADX INFO: renamed from: com.huya.hysignal.bizreq.HySignalLaunch$1, reason: invalid class name */
    class AnonymousClass1 implements Runnable {
        final /* synthetic */ QueryGuidCallback val$callback;

        AnonymousClass1(QueryGuidCallback queryGuidCallback) {
            this.val$callback = queryGuidCallback;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (!HySignalLaunch.this.isQuery) {
                HySignalLaunch.this.isQuery = true;
                try {
                    WSLaunchReq wSLaunchReq = new WSLaunchReq();
                    wSLaunchReq.setSGuid(FieldsCache.getInstance().getGuid());
                    WSDeviceInfo wSDeviceInfo = new WSDeviceInfo();
                    wSDeviceInfo.setSDeviceId(FieldsCache.getInstance().getDeviceId());
                    wSLaunchReq.setLUid(HySignalLaunch.this.mUid);
                    wSLaunchReq.setSUA(HySignalLaunch.this.mUa);
                    wSLaunchReq.setSAppSrc(FieldsCache.getInstance().getAppSrc());
                    wSDeviceInfo.setSMId(FieldsCache.getInstance().getMid());
                    wSDeviceInfo.setSIMEI(HySignalLaunch.this.mIMEI);
                    wSDeviceInfo.setSAPN(HySignalLaunch.this.getApn());
                    wSDeviceInfo.setSNetType(HySignalLaunch.this.getNetType());
                    if (wSDeviceInfo.getSDeviceId() == null || wSDeviceInfo.getSDeviceId().length() <= 0) {
                        HySignalLog.error(HySignalLaunch.TAG, "req userInfo device id is empty, may generate err GUID");
                    }
                    wSLaunchReq.setTDeviceInfo(wSDeviceInfo);
                    UniPacket uniPacket = new UniPacket(true);
                    uniPacket.setServantName("launch");
                    uniPacket.setFuncName("wsLaunch");
                    uniPacket.put("tReq", wSLaunchReq);
                    HySignalClient.getInstance().newCall(new Request.Builder().cmdId(3).cgi("/launch/wsLaunch").body(uniPacket.encode()).channel(3).encrypt(HySignalLaunch.this.enableEncrypt).build()).enqueue(new Callback() { // from class: com.huya.hysignal.bizreq.HySignalLaunch.1.1
                        @Override // com.huya.hysignal.core.Callback
                        public void onResponse(final byte[] bArr, final HySignalError hySignalError) {
                            ThreadManager.deliverOnResponseThread(new Runnable() { // from class: com.huya.hysignal.bizreq.HySignalLaunch.1.1.1
                                @Override // java.lang.Runnable
                                public void run() {
                                    String guid = FieldsCache.getInstance().getGuid();
                                    if (hySignalError.getErrType() != 0) {
                                        HySignalLog.error(HySignalLaunch.TAG, "query GUID failed, errorCode:%d, errorType:%d, now GUID:%S", Integer.valueOf(hySignalError.getErrCode()), Integer.valueOf(hySignalError.getErrType()), guid);
                                        HySignalLaunch.this.OnQueryFinish(false, "query signal failed", AnonymousClass1.this.val$callback);
                                        HySignalLaunch.this.isQuery = false;
                                        return;
                                    }
                                    WSLaunchRsp wSLaunchRsp = null;
                                    try {
                                        TafUniPacket tafUniPacket = new TafUniPacket();
                                        tafUniPacket.decode(bArr);
                                        if (tafUniPacket.getTafStatus() != null) {
                                            HySignalLog.info(HySignalLaunch.TAG, "rsp status: %d, desc: %s", Integer.valueOf(tafUniPacket.getTafResultCode()), tafUniPacket.getTafResultDesc());
                                        }
                                        wSLaunchRsp = (WSLaunchRsp) tafUniPacket.getByClass("tRsp", new WSLaunchRsp());
                                    } catch (Exception e) {
                                        HySignalLog.error(HySignalLaunch.TAG, "get rsp failed " + e.getMessage());
                                    }
                                    if (wSLaunchRsp != null) {
                                        HySignalLaunch.this.clientIp = wSLaunchRsp.sClientIp;
                                        String str = wSLaunchRsp.sGuid;
                                        String guid2 = FieldsCache.getInstance().getGuid();
                                        if (HySignalLaunch.this.isGuidApproved(str)) {
                                            if (!str.equals(guid2)) {
                                                HySignalLaunch.this.notifyGuidChange(str);
                                            }
                                        } else {
                                            HySignalLog.error(HySignalLaunch.TAG, "rsp guid error: %s", str);
                                        }
                                        HySignalLaunch.this.isQuery = false;
                                        HySignalLaunch.this.OnQueryFinish(true, "", AnonymousClass1.this.val$callback);
                                        return;
                                    }
                                    HySignalLog.error(HySignalLaunch.TAG, "decode rsp failed, errorCode:%d, errorType:%d, now GUID:%S", Integer.valueOf(hySignalError.getErrCode()), Integer.valueOf(hySignalError.getErrType()), guid);
                                    HySignalLaunch.this.OnQueryFinish(false, "decode rsp failed", AnonymousClass1.this.val$callback);
                                    HySignalLaunch.this.isQuery = false;
                                }
                            });
                        }
                    });
                    return;
                } catch (Exception e) {
                    HySignalLog.error(HySignalLaunch.TAG, "build req failed" + e.getMessage());
                    HySignalLaunch.this.OnQueryFinish(false, "build req failed" + e.getMessage(), this.val$callback);
                    HySignalLaunch.this.isQuery = false;
                    return;
                }
            }
            HySignalLog.info(HySignalLaunch.TAG, "is query, return");
            HySignalLaunch.this.OnQueryFinish(false, "is query, return", this.val$callback);
        }
    }

    public String getClientIp() {
        return this.clientIp;
    }

    public boolean isGuidApproved(String str) {
        return (str == null || str.isEmpty() || str.trim().length() != 32) ? false : true;
    }

    public synchronized void onForeground() {
        long jCurrentTimeMillis = System.currentTimeMillis();
        String guid = FieldsCache.getInstance().getGuid();
        if ((this.lastStateChangeTime == 0 || jCurrentTimeMillis - this.lastStateChangeTime < FOREGROUND_CHANGE_QUERY_TIME) && !"".equals(guid)) {
            HySignalLog.debug(TAG, "control query frequency, return");
        } else {
            queryGuid(null);
            this.lastStateChangeTime = jCurrentTimeMillis;
        }
    }

    private void setExistGuid(String str) {
        if (empty(str)) {
            HySignalLog.info(TAG, "set null or same guid, return");
            return;
        }
        if (!isGuidApproved(str)) {
            HySignalLog.info(TAG, "set err guid: %s, return", str);
            return;
        }
        String guid = FieldsCache.getInstance().getGuid();
        if (guid == null || "".equals(guid)) {
            FieldsCache.getInstance().saveGuid(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyGuidChange(final String str) {
        if (empty(str)) {
            return;
        }
        FieldsCache.getInstance().saveGuid(str);
        HySignalLog.info(TAG, "guid change, guid: %s, clientIP:%s", str, getClientIp());
        if (this.listener != null) {
            ThreadManager.deliverOnResponseThread(new Runnable() { // from class: com.huya.hysignal.bizreq.HySignalLaunch.2
                @Override // java.lang.Runnable
                public void run() {
                    HySignalLaunch.this.listener.onGuid(str);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getApn() {
        Context context = this.context;
        if (context == null) {
            return "NON_NETWORK";
        }
        switch (NetStatusUtil.getNetType(context)) {
        }
        return "NON_NETWORK";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getNetType() {
        Context context = this.context;
        return context == null ? "NON_NETWORK" : NetStatusUtil.getNetTypeString(context);
    }

    private boolean empty(String str) {
        return str == null || str.length() <= 0;
    }
}
