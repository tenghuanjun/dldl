package com.huya.mtp.hycloudgame.base.websocket.wsmanager;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class WsStatus {
    public static final int CONNECTED = 1;
    public static final int CONNECTING = 0;
    public static final int DISCONNECTED = -1;
    public static final int RECONNECT = 2;

    class CODE {
        public static final int ABNORMAL_CLOSE = 1001;
        public static final int NORMAL_CLOSE = 1000;

        CODE() {
        }
    }

    class TIP {
        public static final String ABNORMAL_CLOSE = "abnormal close";
        public static final String NORMAL_CLOSE = "normal close";

        TIP() {
        }
    }
}
