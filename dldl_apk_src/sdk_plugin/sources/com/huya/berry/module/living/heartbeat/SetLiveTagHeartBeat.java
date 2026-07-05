package com.huya.berry.module.living.heartbeat;

import com.duowan.HUYA.LiveAttributeInfo;
import com.duowan.HUYA.SetLiveAttributeReq;
import com.duowan.auk.util.L;
import com.huya.berry.gamesdk.SdkProperties;
import com.huya.berry.gamesdk.module.ICommonWup;
import com.huya.berry.module.help.LiveHelper;
import com.huya.berry.module.living.heartbeat.BaseHeartBeat;
import com.huya.live.ns.rxjava.WupObserver;
import com.huya.mtp.hyns.NS;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import java.util.HashMap;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class SetLiveTagHeartBeat extends BaseHeartBeat<SetLiveAttributeReq> {
    private final String KEY_PRIVACY;
    private final byte[] PRIVACY_CLOSE;
    private final byte[] PRIVACY_OPEN;

    public SetLiveTagHeartBeat(long j, BaseHeartBeat.HeartBeatListener heartBeatListener) {
        super(j, heartBeatListener);
        this.KEY_PRIVACY = "privacyMS";
        this.PRIVACY_OPEN = "1".getBytes();
        this.PRIVACY_CLOSE = "0".getBytes();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.huya.berry.module.living.heartbeat.BaseHeartBeat
    protected void onHeartBeat() {
        L.info("HeartBeat", "SetLiveTagHeartBeat mpAttribute:" + ((SetLiveAttributeReq) this.mHeartBeatReq).mpAttribute.get("privacyMS") + " openPrivacy " + SdkProperties.openPrivacy.get());
        ((ICommonWup) NS.get(ICommonWup.class)).setLiveAttribute((SetLiveAttributeReq) this.mHeartBeatReq).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new WupObserver<Object>() { // from class: com.huya.berry.module.living.heartbeat.SetLiveTagHeartBeat.1
            @Override // com.huya.live.ns.rxjava.WupObserver, io.reactivex.Observer
            public void onNext(Object obj) {
                L.info("HeartBeat", "SetLiveTagHeartBeat success");
                if (obj == null || SetLiveTagHeartBeat.this.mHeartBeatListener == null) {
                    return;
                }
                SetLiveTagHeartBeat.this.mHeartBeatListener.onHeartBeatError(null);
            }

            @Override // com.huya.live.ns.rxjava.WupObserver, io.reactivex.Observer
            public void onError(Throwable th) {
                L.info("HeartBeat", "SetLiveTagHeartBeat error:" + th);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [Req, com.duowan.HUYA.SetLiveAttributeReq] */
    @Override // com.huya.berry.module.living.heartbeat.BaseHeartBeat
    public SetLiveAttributeReq initHeartBeatReq() {
        this.mHeartBeatReq = new SetLiveAttributeReq();
        ((SetLiveAttributeReq) this.mHeartBeatReq).setTId(LiveHelper.getUserId());
        ((SetLiveAttributeReq) this.mHeartBeatReq).setLLiveId(SdkProperties.liveId.get().longValue());
        HashMap map = new HashMap();
        LiveAttributeInfo liveAttributeInfo = new LiveAttributeInfo();
        liveAttributeInfo.lExpTimeMs = 60000L;
        liveAttributeInfo.vData = SdkProperties.openPrivacy.get().booleanValue() ? this.PRIVACY_OPEN : this.PRIVACY_CLOSE;
        map.put("privacyMS", liveAttributeInfo);
        ((SetLiveAttributeReq) this.mHeartBeatReq).setMpAttribute(map);
        return (SetLiveAttributeReq) this.mHeartBeatReq;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.huya.berry.module.living.heartbeat.BaseHeartBeat
    public SetLiveAttributeReq updateHeartBeatReq() {
        SetLiveAttributeReq setLiveAttributeReq = (SetLiveAttributeReq) this.mHeartBeatReq;
        if (setLiveAttributeReq != null && setLiveAttributeReq.mpAttribute != null) {
            LiveAttributeInfo liveAttributeInfo = setLiveAttributeReq.mpAttribute.get("privacyMS");
            if (liveAttributeInfo != null) {
                liveAttributeInfo.vData = SdkProperties.openPrivacy.get().booleanValue() ? this.PRIVACY_OPEN : this.PRIVACY_CLOSE;
                setLiveAttributeReq.mpAttribute.put("privacyMS", liveAttributeInfo);
            } else {
                LiveAttributeInfo liveAttributeInfo2 = new LiveAttributeInfo();
                liveAttributeInfo2.lExpTimeMs = 60000L;
                liveAttributeInfo2.vData = SdkProperties.openPrivacy.get().booleanValue() ? this.PRIVACY_OPEN : this.PRIVACY_CLOSE;
                setLiveAttributeReq.mpAttribute.put("privacyMS", liveAttributeInfo2);
            }
            return setLiveAttributeReq;
        }
        return initHeartBeatReq();
    }
}
