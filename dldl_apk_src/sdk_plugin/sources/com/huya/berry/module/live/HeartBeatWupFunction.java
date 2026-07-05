package com.huya.berry.module.live;

import com.android.volley.VolleyError;
import com.duowan.HUYA.PresentHeartBeatReq;
import com.duowan.jce.wup.UniPacket;
import com.duowan.networkmars.wup.KiwiWupFunction;
import com.duowan.taf.jce.JceStruct;
import com.huya.berry.module.live.ILiveConstants;
import java.util.concurrent.atomic.AtomicLong;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public abstract class HeartBeatWupFunction<Req extends JceStruct, Rsp extends JceStruct> extends KiwiWupFunction<Req, Rsp> {
    protected static AtomicLong mAtomicLong = new AtomicLong(0);

    @Override // com.duowan.auk.http.v2.Function, com.duowan.auk.http.v2.ResponseListener
    public void onError(VolleyError volleyError) {
    }

    @Override // com.duowan.auk.http.v2.Function, com.duowan.auk.http.v2.ResponseListener
    public void onResponse(Rsp rsp, boolean z) {
    }

    public HeartBeatWupFunction(Req req) {
        super(req);
    }

    @Override // com.duowan.networkmars.wup.HaWupFunction, com.duowan.auk.http.v2.wup.WupRequestDelegate
    public UniPacket getUniPacketBody() {
        if (mAtomicLong.get() > 2147483647L) {
            mAtomicLong.set(0L);
        }
        UniPacket uniPacketBody = super.getUniPacketBody();
        uniPacketBody.setRequestId((int) mAtomicLong.getAndIncrement());
        return uniPacketBody;
    }

    public static class PresentHeartBeat extends HeartBeatWupFunction<PresentHeartBeatReq, JceStruct> {
        @Override // com.duowan.networkmars.wup.HaWupFunction
        public String getFuncName() {
            return ILiveConstants.FuncName.PRESENTER_HEART_BEAT;
        }

        @Override // com.duowan.networkmars.wup.HaWupFunction
        public JceStruct getRspProxy() {
            return null;
        }

        @Override // com.duowan.networkmars.wup.HaWupFunction
        public String getServantName() {
            return ILiveConstants.ServiceName.ON_LIVE_SERVER_NAME;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.huya.berry.module.live.HeartBeatWupFunction, com.duowan.auk.http.v2.Function, com.duowan.auk.http.v2.ResponseListener
        public /* bridge */ /* synthetic */ void onResponse(Object obj, boolean z) {
            super.onResponse((JceStruct) obj, z);
        }

        public PresentHeartBeat(PresentHeartBeatReq presentHeartBeatReq) {
            super(presentHeartBeatReq);
        }
    }
}
