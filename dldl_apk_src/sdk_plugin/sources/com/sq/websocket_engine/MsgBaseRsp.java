package com.sq.websocket_engine;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class MsgBaseRsp {
    public String ev;
    public int op;
    public int opres;

    public boolean isSuccess() {
        return this.opres == 1;
    }
}
