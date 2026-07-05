package com.huya.berry.gamesdk.module;

import com.duowan.auk.ArkValue;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class ICommonConstants {
    String DEFAULT_URL_DEBUG = "http://183.60.218.225:8084";
    String METRIC_URL;

    interface FuncName {
        public static final String GAME_PARTY_HEARTBEAT = "gamePartyHeartbeat";
        public static final String GET_COM_LIST = "getComList";
        public static final String GET_MY_LIST_ANNOUNCEMENT = "getPresenterLiveAnnouncement";
        public static final String METRIC_REPORT = "report";
        public static final String PRESENTER_VERIFY_INFO = "getPresenterVeriInfo";
        public static final String SEND_MESSAGE = "sendMessage";
        public static final String SET_LIVE_TAG = "setLiveTag";
        public static final String SET_MY_LIST_ANNOUNCEMENT = "setPresenterLiveAnnouncement";
    }

    interface ServiceName {
        public static final String GAME_LIVE_SERVER_NAME = "liveui";
        public static final String METRIC = "metric";
        public static final String MOBILE_LIVE_SERVER_NAME = "mobileui";
        public static final String PRESENTER_SERVER_NAME = "presenterui";
        public static final String WUP_UI = "wupui";
    }

    public ICommonConstants() {
        this.METRIC_URL = ArkValue.debuggable() ? this.DEFAULT_URL_DEBUG : "http://stat.wup.huya.com/";
    }
}
