package com.huya.berry.gamesdk.module.commonevent;

import com.duowan.HUYA.ComponentDistributeRsp;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class CommonCallBack {

    public static class SendPubText {
        public boolean isSuccess;

        public SendPubText(boolean z) {
            this.isSuccess = z;
        }
    }

    public static class ComponentDistributeRes {
        public ComponentDistributeRsp rsp;

        public ComponentDistributeRes(ComponentDistributeRsp componentDistributeRsp) {
            this.rsp = componentDistributeRsp;
        }
    }
}
