package com.huya.live.channelinfo.impl.wup;

import com.android.volley.VolleyError;
import com.duowan.HUYA.GetPresenterSignInviteReq;
import com.duowan.HUYA.GetPresenterSignInviteRsp;
import com.duowan.networkmars.wup.KiwiWupFunction;
import com.duowan.taf.jce.JceStruct;
import com.huya.live.channelinfo.impl.IChannelInfoConstants;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class ChannelInfoFunction<Req extends JceStruct, Rsp extends JceStruct> extends KiwiWupFunction<Req, Rsp> implements IChannelInfoConstants {
    @Override // com.duowan.networkmars.wup.HaWupFunction
    public String getFuncName() {
        return null;
    }

    @Override // com.duowan.networkmars.wup.HaWupFunction
    public Rsp getRspProxy() {
        return null;
    }

    @Override // com.duowan.networkmars.wup.HaWupFunction
    public String getServantName() {
        return "liveui";
    }

    @Override // com.duowan.auk.http.v2.Function, com.duowan.auk.http.v2.ResponseListener
    public void onError(VolleyError volleyError) {
    }

    @Override // com.duowan.auk.http.v2.Function, com.duowan.auk.http.v2.ResponseListener
    public void onResponse(Rsp rsp, boolean z) {
    }

    public ChannelInfoFunction(Req req) {
        super(req);
    }

    public static class GetPresenterSignInvite extends ChannelInfoFunction<GetPresenterSignInviteReq, GetPresenterSignInviteRsp> {
        @Override // com.huya.live.channelinfo.impl.wup.ChannelInfoFunction, com.duowan.networkmars.wup.HaWupFunction
        public String getFuncName() {
            return IChannelInfoConstants.FuncName.GET_PRESENTER_SIGN_INVITE;
        }

        @Override // com.huya.live.channelinfo.impl.wup.ChannelInfoFunction, com.duowan.networkmars.wup.HaWupFunction
        public String getServantName() {
            return "presenterui";
        }

        @Override // com.huya.live.channelinfo.impl.wup.ChannelInfoFunction, com.duowan.auk.http.v2.Function, com.duowan.auk.http.v2.ResponseListener
        public /* bridge */ /* synthetic */ void onResponse(Object obj, boolean z) {
            super.onResponse((GetPresenterSignInviteRsp) obj, z);
        }

        public GetPresenterSignInvite(GetPresenterSignInviteReq getPresenterSignInviteReq) {
            super(getPresenterSignInviteReq);
        }

        @Override // com.huya.live.channelinfo.impl.wup.ChannelInfoFunction, com.duowan.networkmars.wup.HaWupFunction
        public GetPresenterSignInviteRsp getRspProxy() {
            return new GetPresenterSignInviteRsp();
        }
    }
}
