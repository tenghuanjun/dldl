package com.huya.berry.module.living.heartbeat;

import com.duowan.HUYA.GamePartyHeartbeatReq;
import com.duowan.HUYA.GamePartyHeartbeatRsp;
import com.duowan.auk.util.L;
import com.huya.berry.gamesdk.SdkProperties;
import com.huya.berry.gamesdk.module.ICommonWup;
import com.huya.berry.module.help.LiveHelper;
import com.huya.berry.module.living.heartbeat.BaseHeartBeat;
import com.huya.live.ns.rxjava.WupObserver;
import com.huya.mtp.hyns.NS;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class GangUpHeartBeat extends BaseHeartBeat<GamePartyHeartbeatReq> {
    public GangUpHeartBeat(long j, BaseHeartBeat.HeartBeatListener heartBeatListener) {
        super(j, heartBeatListener);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.huya.berry.module.living.heartbeat.BaseHeartBeat
    protected void onHeartBeat() {
        ((ICommonWup) NS.get(ICommonWup.class)).gamePartyHeartbeat((GamePartyHeartbeatReq) this.mHeartBeatReq).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new WupObserver<GamePartyHeartbeatRsp>() { // from class: com.huya.berry.module.living.heartbeat.GangUpHeartBeat.1
            @Override // com.huya.live.ns.rxjava.WupObserver, io.reactivex.Observer
            public void onNext(GamePartyHeartbeatRsp gamePartyHeartbeatRsp) {
                if (gamePartyHeartbeatRsp != null) {
                    if (gamePartyHeartbeatRsp.iResultCode == 0) {
                        L.info("HeartBeat", "GamePartyHeartbeat success");
                    } else {
                        L.info("HeartBeat", "GamePartyHeartbeat fail");
                    }
                    if (GangUpHeartBeat.this.mHeartBeatListener != null) {
                        GangUpHeartBeat.this.mHeartBeatListener.onHeartBeatError(null);
                    }
                }
            }

            @Override // com.huya.live.ns.rxjava.WupObserver, io.reactivex.Observer
            public void onError(Throwable th) {
                L.info("HeartBeat", "GamePartyHeartbeat error:" + th);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [Req, com.duowan.HUYA.GamePartyHeartbeatReq] */
    @Override // com.huya.berry.module.living.heartbeat.BaseHeartBeat
    public GamePartyHeartbeatReq initHeartBeatReq() {
        this.mHeartBeatReq = new GamePartyHeartbeatReq();
        ((GamePartyHeartbeatReq) this.mHeartBeatReq).setTId(LiveHelper.getUserId());
        ((GamePartyHeartbeatReq) this.mHeartBeatReq).setSContent(SdkProperties.gangUpData.get());
        return (GamePartyHeartbeatReq) this.mHeartBeatReq;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.huya.berry.module.living.heartbeat.BaseHeartBeat
    public GamePartyHeartbeatReq updateHeartBeatReq() {
        GamePartyHeartbeatReq gamePartyHeartbeatReq = (GamePartyHeartbeatReq) this.mHeartBeatReq;
        if (gamePartyHeartbeatReq != null) {
            gamePartyHeartbeatReq.setSContent(SdkProperties.gangUpData.get());
            return gamePartyHeartbeatReq;
        }
        return initHeartBeatReq();
    }
}
