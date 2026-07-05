package com.huya.mtp.hycloudgame.base.websocket;

import java.util.HashMap;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class WebSocketConfig {
    HashMap<String, String> header;

    public HashMap<String, String> getHeader() {
        return this.header;
    }

    public WebSocketConfig setHeader(HashMap<String, String> map) {
        this.header = map;
        return this;
    }
}
