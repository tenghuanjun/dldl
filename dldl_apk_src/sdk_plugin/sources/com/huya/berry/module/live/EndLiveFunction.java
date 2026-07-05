package com.huya.berry.module.live;

import com.android.volley.VolleyError;
import com.duowan.HUYA.EndLiveReq;
import com.duowan.networkmars.wup.KiwiWupFunction;
import com.duowan.taf.jce.JceStruct;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class EndLiveFunction extends KiwiWupFunction<EndLiveReq, JceStruct> {
    private static final String END_LIVE = "endLive";
    private static final String GAME_LIVE_SERVER_NAME = "liveui";

    @Override // com.duowan.networkmars.wup.HaWupFunction
    public String getFuncName() {
        return "endLive";
    }

    @Override // com.duowan.networkmars.wup.HaWupFunction
    public JceStruct getRspProxy() {
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
    public void onResponse(JceStruct jceStruct, boolean z) {
    }

    public EndLiveFunction(EndLiveReq endLiveReq) {
        super(endLiveReq);
    }
}
