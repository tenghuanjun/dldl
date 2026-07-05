package com.huya.berry.module.live;

import android.os.Build;
import com.android.volley.VolleyError;
import com.duowan.HUYA.PresentHeartBeatReq;
import com.duowan.HUYA.UserId;
import com.duowan.auk.util.L;
import com.duowan.live.one.util.AppStatusReportUtil;
import com.duowan.taf.jce.JceStruct;
import com.huya.berry.gamesdk.SdkProperties;
import com.huya.berry.module.live.HeartBeatWupFunction;
import com.huya.component.login.api.LoginApi;
import com.huya.live.utils.heartbeat.BaseHeartBeat;
import com.sqwan.bugless.core.Constant;
import com.sqwan.bugless.util.DateUtil;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class PresenterHeartBeat extends BaseHeartBeat<PresentHeartBeatReq> {
    private int mHeartUserTime;
    private long mLastChatTime;
    private long mLastGiftTime;
    private HeartBeatWupFunction.PresentHeartBeat mPresentHeartBeat;
    private AtomicInteger mUploadFps;
    private final UserId mUserId;

    public PresenterHeartBeat(UserId userId, long j, BaseHeartBeat.HeartBeatListener heartBeatListener) {
        super(j, heartBeatListener);
        this.mUploadFps = new AtomicInteger(0);
        this.mUserId = userId;
        L.info("HeartBeat", "new PresenterHeartBeat");
    }

    @Override // com.huya.live.utils.heartbeat.BaseHeartBeat
    public void stopHeartBeat() {
        L.info("HeartBeat", "stopHeartBeat...");
        HeartBeatWupFunction.PresentHeartBeat presentHeartBeat = this.mPresentHeartBeat;
        if (presentHeartBeat != null) {
            presentHeartBeat.cancel();
        }
        super.stopHeartBeat();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.huya.live.utils.heartbeat.BaseHeartBeat
    protected void onHeartBeat() {
        L.info("HeartBeat", "onPresenterHeartBeat uid = %d mHeartUserTime = %d,liveId = %d", Long.valueOf(this.mUserId.getLUid()), Integer.valueOf(this.mHeartUserTime), Long.valueOf(((PresentHeartBeatReq) this.mHeartBeatReq).getLLiveId()));
        if (this.mUserId.getLUid() == 0) {
            int i = this.mHeartUserTime + 1;
            this.mHeartUserTime = i;
            if (i < 4) {
                return;
            }
        } else {
            this.mHeartUserTime = 0;
        }
        PresentHeartBeatReq presentHeartBeatReq = (PresentHeartBeatReq) this.mHeartBeatReq;
        if (presentHeartBeatReq == null) {
            L.info("HeartBeat", "onPresenterHeartBeat is stop");
            return;
        }
        HeartBeatWupFunction.PresentHeartBeat presentHeartBeat = new HeartBeatWupFunction.PresentHeartBeat(presentHeartBeatReq) { // from class: com.huya.berry.module.live.PresenterHeartBeat.1
            @Override // com.huya.berry.module.live.HeartBeatWupFunction.PresentHeartBeat, com.huya.berry.module.live.HeartBeatWupFunction, com.duowan.auk.http.v2.Function, com.duowan.auk.http.v2.ResponseListener
            public void onResponse(JceStruct jceStruct, boolean z) {
                L.info("HeartBeat", "onPresenterHeartBeat success");
            }

            @Override // com.huya.berry.module.live.HeartBeatWupFunction, com.duowan.auk.http.v2.Function, com.duowan.auk.http.v2.ResponseListener
            public void onError(VolleyError volleyError) {
                L.info("HeartBeat", "onPresenterHeartBeat error " + volleyError);
                if (PresenterHeartBeat.this.mHeartBeatReq != null) {
                    L.info("HeartBeat", "onPresenterHeartBeat error lLiveId:" + ((PresentHeartBeatReq) PresenterHeartBeat.this.mHeartBeatReq).lLiveId);
                    if (PresenterHeartBeat.this.mHeartBeatListener != null) {
                        PresenterHeartBeat.this.mHeartBeatListener.onHeartBeatError(volleyError);
                        return;
                    }
                    return;
                }
                L.info("HeartBeat", "onPresenterHeartBeat is stop");
            }
        };
        this.mPresentHeartBeat = presentHeartBeat;
        presentHeartBeat.execute();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [Req, com.duowan.HUYA.PresentHeartBeatReq] */
    @Override // com.huya.live.utils.heartbeat.BaseHeartBeat
    public PresentHeartBeatReq initHeartBeatReq() {
        this.mHeartBeatReq = new PresentHeartBeatReq();
        ((PresentHeartBeatReq) this.mHeartBeatReq).setTId(this.mUserId);
        ((PresentHeartBeatReq) this.mHeartBeatReq).setLTid(LoginApi.getUid());
        ((PresentHeartBeatReq) this.mHeartBeatReq).setLSid(LoginApi.getUid());
        ((PresentHeartBeatReq) this.mHeartBeatReq).setLLiveId(SdkProperties.liveId.get().longValue());
        ((PresentHeartBeatReq) this.mHeartBeatReq).setBIsCameraOpen(false);
        ((PresentHeartBeatReq) this.mHeartBeatReq).setBIsRoomSecret(false);
        ((PresentHeartBeatReq) this.mHeartBeatReq).setMReportMessage(getDeviceReport());
        return (PresentHeartBeatReq) this.mHeartBeatReq;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.huya.live.utils.heartbeat.BaseHeartBeat
    public PresentHeartBeatReq updateHeartBeatReq() {
        ((PresentHeartBeatReq) this.mHeartBeatReq).setMReportMessage(getDeviceReport());
        return (PresentHeartBeatReq) this.mHeartBeatReq;
    }

    private Map<String, String> getDeviceReport() {
        HashMap map = new HashMap();
        map.put(Constant.DEV_NETWORK, AppStatusReportUtil.getNetworkConnectionName());
        map.put("cpu", AppStatusReportUtil.getCpuUsage());
        map.put("memory", AppStatusReportUtil.getMemoryUsage());
        map.put("device", Build.MODEL);
        map.put("system", Build.VERSION.RELEASE);
        map.put("uploadFps", String.valueOf(this.mUploadFps.get()));
        long j = this.mLastGiftTime;
        map.put("lastGift", j == 0 ? "" : toTimeFormat(j));
        long j2 = this.mLastChatTime;
        map.put("lastChat", j2 != 0 ? toTimeFormat(j2) : "");
        L.info("HeartBeat", map.toString());
        return map;
    }

    public void setUploadFps(int i) {
        this.mUploadFps.set(i);
    }

    public void setLastGiftTime(long j) {
        this.mLastGiftTime = j;
    }

    public void setLastChatTime(long j) {
        this.mLastChatTime = j;
    }

    private static String toTimeFormat(long j) {
        try {
            return new SimpleDateFormat(DateUtil.DEFAULT_FORMAT_TIME).format(Long.valueOf(j));
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
