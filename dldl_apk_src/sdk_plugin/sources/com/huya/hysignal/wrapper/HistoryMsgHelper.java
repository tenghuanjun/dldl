package com.huya.hysignal.wrapper;

import com.duowan.taf.jce.JceParser;
import com.huya.hysignal.core.Call;
import com.huya.hysignal.core.Callback;
import com.huya.hysignal.core.HySignalClient;
import com.huya.hysignal.core.HySignalError;
import com.huya.hysignal.jce.WSHistoryMsgReq;
import com.huya.hysignal.jce.WSHistoryMsgRsp;
import com.huya.hysignal.jce.WSPushMessage;
import com.huya.hysignal.util.FieldsCache;
import com.huya.hysignal.util.HySignalLog;
import com.huya.hysignal.util.ThreadManager;
import com.huya.mtp.hyns.api.NSLongLinkApi;
import com.huya.mtp.hyns.api.Request;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;
import kotlin.jvm.internal.LongCompanionObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
class HistoryMsgHelper {
    private static final int LAST_PULL_MORE_PAGE = 1;
    private final String TAG;
    private boolean mIsLogin;
    private RequestInfo mRequestInfo;
    private int mType;
    private long mUid;
    private boolean mUnableLostMsg;
    private long mLastMsgIdForQuery = 0;
    private long mLatestPushMsgId = 0;
    private long mFirstPushMsgIdAfterConnected = LongCompanionObject.MAX_VALUE;
    private int mQueryCount = 0;
    private AtomicLong mUUidProducer = new AtomicLong(0);
    private Set<Long> mRegisterMsgUriSet = null;

    HistoryMsgHelper(int i, long j, boolean z, boolean z2) {
        this.mType = i;
        this.mUid = j;
        this.mIsLogin = z;
        this.mUnableLostMsg = z2;
        StringBuilder sb = new StringBuilder();
        sb.append(HistoryMsgHelper.class.getName());
        sb.append(this.mType == 0 ? ":uid" : ":group");
        this.TAG = sb.toString();
    }

    void resetWhenLinkStatusChange(int i) {
        if (i != 4) {
            this.mFirstPushMsgIdAfterConnected = LongCompanionObject.MAX_VALUE;
        }
    }

    synchronized void resetUnableLostMsg(boolean z) {
        this.mUnableLostMsg = z;
    }

    void updateRegisterUris(Set<Long> set) {
        this.mRegisterMsgUriSet = set;
    }

    void updateUid(long j) {
        if (j >= 0) {
            this.mUid = j;
        }
    }

    void updateIsLogin(boolean z) {
        this.mIsLogin = z;
    }

    void resetLastPushMsgId(long j) {
        this.mLatestPushMsgId = j;
        if (this.mFirstPushMsgIdAfterConnected == LongCompanionObject.MAX_VALUE) {
            HySignalLog.info(this.TAG, "update first uid id, type: %d", Integer.valueOf(this.mType));
            this.mFirstPushMsgIdAfterConnected = j;
        }
    }

    void pullHistoryMsg(final NSLongLinkApi.PushListener pushListener) {
        if (!this.mUnableLostMsg) {
            HySignalLog.error(this.TAG, "enable lost msg, skip pull history message");
        } else if (!HySignalClient.getInstance().isLongLinkConnected(5)) {
            HySignalLog.error(this.TAG, "longLink is not connect");
            handleFlagOnQueryStatus(1);
        } else {
            ThreadManager.deliverOnRequestThread(new Runnable() { // from class: com.huya.hysignal.wrapper.HistoryMsgHelper.1
                @Override // java.lang.Runnable
                public void run() {
                    WSHistoryMsgReq wSHistoryMsgReq = new WSHistoryMsgReq();
                    if (HistoryMsgHelper.this.mType == 0) {
                        long uid = HistoryMsgHelper.this.getUid();
                        if (!HistoryMsgHelper.this.isLogin() || uid == 0) {
                            HySignalLog.error(HistoryMsgHelper.this.TAG, "pullHistoryMsg is not login");
                            HistoryMsgHelper.this.handleFlagOnQueryStatus(0);
                            return;
                        }
                        wSHistoryMsgReq.setLUid(uid);
                    } else if (HistoryMsgHelper.this.mType == 1) {
                        ArrayList<String> registeredGroupIdList = FieldsCache.getInstance().getRegisteredGroupIdList();
                        if (HistoryMsgHelper.empty(registeredGroupIdList)) {
                            HistoryMsgHelper.this.handleFlagOnQueryStatus(0);
                            return;
                        }
                        wSHistoryMsgReq.setVGroupId(registeredGroupIdList);
                    }
                    if (HistoryMsgHelper.this.mLastMsgIdForQuery == 0) {
                        HistoryMsgHelper historyMsgHelper = HistoryMsgHelper.this;
                        historyMsgHelper.mLastMsgIdForQuery = historyMsgHelper.mLatestPushMsgId;
                    }
                    if (HistoryMsgHelper.this.mLastMsgIdForQuery == 0) {
                        HySignalLog.debug(HistoryMsgHelper.this.TAG, "mLastMsgIdForQuery is 0, return");
                        HistoryMsgHelper.this.handleFlagOnQueryStatus(0);
                    } else {
                        wSHistoryMsgReq.setLLastMsgId(HistoryMsgHelper.this.mLastMsgIdForQuery);
                        HistoryMsgHelper.this.requestHistoryMsg(wSHistoryMsgReq, pushListener);
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void requestHistoryMsg(final WSHistoryMsgReq wSHistoryMsgReq, final NSLongLinkApi.PushListener pushListener) {
        HySignalLog.debug(this.TAG, "pullUidHistoryMsg wsHistoryMsgReq = %s", wSHistoryMsgReq);
        Request requestBuild = new Request.Builder().cmdId(25).cgi("/cmdid/25").channel(5).retryCount(0).body(wSHistoryMsgReq.toByteArray()).networkStatusSensitive(true).build();
        RequestInfo requestInfo = this.mRequestInfo;
        if (requestInfo != null) {
            requestInfo.call.cancel();
            this.mRequestInfo = null;
        }
        RequestInfo requestInfo2 = new RequestInfo(HySignalClient.getInstance().newCall(requestBuild), this.mUUidProducer.incrementAndGet());
        this.mRequestInfo = requestInfo2;
        final long j = requestInfo2.uuid;
        this.mRequestInfo.call.enqueue(new Callback() { // from class: com.huya.hysignal.wrapper.HistoryMsgHelper.2
            @Override // com.huya.hysignal.core.Callback
            public void onResponse(final byte[] bArr, final HySignalError hySignalError) {
                ThreadManager.deliverOnResponseThread(new Runnable() { // from class: com.huya.hysignal.wrapper.HistoryMsgHelper.2.1
                    @Override // java.lang.Runnable
                    public void run() {
                        HistoryMsgHelper.this.handleMsgPullResult(wSHistoryMsgReq, pushListener, bArr, hySignalError.getErrType(), hySignalError.getErrCode(), j);
                    }
                });
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleMsgPullResult(WSHistoryMsgReq wSHistoryMsgReq, NSLongLinkApi.PushListener pushListener, byte[] bArr, int i, int i2, long j) {
        int size;
        char c = 0;
        char c2 = 1;
        HySignalLog.debug(this.TAG, "pullHistoryMsg errType = %d, errorCode = %d uuid = %d", Integer.valueOf(i), Integer.valueOf(i2), Long.valueOf(j));
        RequestInfo requestInfo = this.mRequestInfo;
        if (requestInfo == null || requestInfo.uuid != j) {
            return;
        }
        this.mRequestInfo = null;
        if (i != 0) {
            HySignalLog.error(this.TAG, "pull msg failed, errorType: %d, errorCode: %d", Integer.valueOf(i), Integer.valueOf(i2));
            handleFlagOnQueryStatus(1);
            return;
        }
        if (this.mType == 0 && wSHistoryMsgReq.getLUid() != getUid()) {
            HySignalLog.error(this.TAG, "pull msg uid is not equal, return");
            handleFlagOnQueryStatus(0);
            return;
        }
        if (this.mType == 1 && !wSHistoryMsgReq.getVGroupId().equals(FieldsCache.getInstance().getRegisteredGroupIdList())) {
            handleFlagOnQueryStatus(0);
            return;
        }
        WSHistoryMsgRsp wSHistoryMsgRsp = (WSHistoryMsgRsp) JceParser.parseJce(bArr, new WSHistoryMsgRsp());
        if (wSHistoryMsgRsp == null) {
            HySignalLog.debug(this.TAG, "WSHistoryMsgRsp is null, return");
            handleFlagOnQueryStatus(1);
            return;
        }
        ArrayList<String> registeredGroupIdList = FieldsCache.getInstance().getRegisteredGroupIdList();
        ArrayList<WSPushMessage> vMsg = wSHistoryMsgRsp.getVMsg();
        if (vMsg != null) {
            size = vMsg.size();
            for (WSPushMessage wSPushMessage : vMsg) {
                String str = wSPushMessage.sGroupId;
                if (str != null && str.length() > 0 && (empty(registeredGroupIdList) || !registeredGroupIdList.contains(str))) {
                    String str2 = this.TAG;
                    Object[] objArr = new Object[3];
                    objArr[c] = str;
                    objArr[c2] = Long.valueOf(wSPushMessage.iUri);
                    objArr[2] = Long.valueOf(wSPushMessage.lMsgId);
                    HySignalLog.debug(str2, "HistoryMsg register groups not contain groups: %s, msg uri:%d, id:%d", objArr);
                } else if (!empty(this.mRegisterMsgUriSet) && !this.mRegisterMsgUriSet.contains(Long.valueOf(wSPushMessage.iUri))) {
                    String str3 = this.TAG;
                    Object[] objArr2 = new Object[2];
                    objArr2[c] = Long.valueOf(wSPushMessage.iUri);
                    objArr2[c2] = Long.valueOf(wSPushMessage.lMsgId);
                    HySignalLog.debug(str3, "HistoryMsg uri : %d is not cared, msgid: %d", objArr2);
                } else {
                    try {
                        pushListener.onPush(new NSLongLinkApi.HySignalMessage((int) wSPushMessage.iUri, wSPushMessage.sMsg, wSPushMessage.sGroupId, wSPushMessage.lMsgId, true, "HistoryMsgNetQuery"));
                    } catch (Exception e) {
                        HySignalLog.error(this.TAG, e.getMessage());
                    }
                    c = 0;
                    c2 = 1;
                }
            }
        } else {
            size = 0;
        }
        long j2 = wSHistoryMsgRsp.lLastMsgId;
        HySignalLog.debug(this.TAG, "wsHistoryMsgRsp msgCount = %d lastMsgId = %d", Integer.valueOf(size), Long.valueOf(j2));
        this.mLastMsgIdForQuery = j2;
        if (j2 > this.mFirstPushMsgIdAfterConnected) {
            HySignalLog.info(this.TAG, "msgId > firstPushMsgIdAfterConnected, pull more");
            this.mQueryCount++;
        }
        int iQueryPullingStatus = queryPullingStatus(size);
        handleFlagOnQueryStatus(iQueryPullingStatus);
        if (iQueryPullingStatus == 2) {
            pullHistoryMsg(pushListener);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleFlagOnQueryStatus(int i) {
        HySignalLog.info(this.TAG, "status = %d", Integer.valueOf(i));
        if (i == 0) {
            this.mQueryCount = 0;
            this.mLastMsgIdForQuery = 0L;
            HySignalLog.info(this.TAG, "query push msg finish");
        } else {
            if (i != 1) {
                return;
            }
            this.mQueryCount = 0;
            HySignalLog.info(this.TAG, "query push msg failed");
        }
    }

    private int queryPullingStatus(int i) {
        return (this.mQueryCount > 1 || i == 0) ? 0 : 2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isLogin() {
        return this.mIsLogin;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public long getUid() {
        return this.mUid;
    }

    private static class RequestInfo {
        Call call;
        long uuid;

        RequestInfo(Call call, long j) {
            this.call = call;
            this.uuid = j;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean empty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }
}
