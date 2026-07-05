package com.sqwan.liveshow.huya.request.bean.danmu.http;

import com.sqwan.liveshow.huya.request.bean.danmu.LiveshowBaseRequest;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class EnterRoomReqBean extends LiveshowBaseRequest {
    public String rid;

    public EnterRoomReqBean(String str) {
        this.rid = str;
    }

    public static class RspBeanWrapperBase {
        private String msg;
        private int state;

        public int getState() {
            return this.state;
        }

        public void setState(int i) {
            this.state = i;
        }

        public String getMsg() {
            return this.msg;
        }

        public void setMsg(String str) {
            this.msg = str;
        }

        public boolean isSuccess() {
            return this.state == 1;
        }
    }
}
