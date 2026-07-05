package com.sq.websocket_engine;

import com.sq.websocket_engine.RequestQueueHandler;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class MsgBaseReq {
    private String ev;
    private int op;
    public int timeout = 10000;
    public RequestQueueHandler.RequestPriority requestPriority = RequestQueueHandler.RequestPriority.DEFAULT;
    private String appid = "platform";

    public int getOp() {
        return this.op;
    }

    public String getEv() {
        return this.ev;
    }

    public void setOp(int i) {
        this.op = i;
    }

    public void setEv(String str) {
        this.ev = str;
    }

    public String getAppid() {
        return this.appid;
    }

    public void setAppid(String str) {
        this.appid = str;
    }
}
