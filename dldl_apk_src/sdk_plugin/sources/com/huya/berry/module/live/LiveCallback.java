package com.huya.berry.module.live;

import com.duowan.HUYA.BeginLiveRsp;
import com.duowan.HUYA.TransMsgToViewerRsp;
import com.duowan.HUYA.ZhixuPopupNotify;
import com.huya.berry.gamesdk.base.BaseCallback;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class LiveCallback extends BaseCallback {

    public static class AlertWindowPermissionCancel {
    }

    public static class AlertWindowPermissionRequest {
    }

    public static class HeartBeatError {
    }

    public static class StopLive {
    }

    public static class StartLive {
        public BeginLiveRsp rsp;

        public StartLive(BeginLiveRsp beginLiveRsp) {
            this.rsp = beginLiveRsp;
        }
    }

    public static class EndLiveNotice {
        public String msg;

        public EndLiveNotice(String str) {
            this.msg = str;
        }
    }

    public static class ZhixuPopupNotice {
        public ZhixuPopupNotify notify;

        public ZhixuPopupNotice(ZhixuPopupNotify zhixuPopupNotify) {
            this.notify = zhixuPopupNotify;
        }
    }

    public static class TransMsgToViewer {
        public TransMsgToViewerRsp resp;
        public int status;

        public TransMsgToViewer(int i, TransMsgToViewerRsp transMsgToViewerRsp) {
            this.status = i;
            this.resp = transMsgToViewerRsp;
        }
    }
}
