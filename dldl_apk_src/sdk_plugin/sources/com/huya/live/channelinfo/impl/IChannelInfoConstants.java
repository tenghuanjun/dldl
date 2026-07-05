package com.huya.live.channelinfo.impl;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public interface IChannelInfoConstants {
    public static final String KEY_BEGIN_LIVE_NOTICE_MODE = "key_begin_live_notice_mode";
    public static final String KEY_CHANNEL_ORIENTATION = "channel_orientation";
    public static final String KEY_CHANNEL_ORIENTATION_ORIGIN = "channel_orientation_origin";
    public static final String KEY_CHANNEL_PREVIEW_ORIENTATION = "channel_preview_orientation";
    public static final String KEY_CHANNEL_TYPE_LIST_CACHE = "key_channel_type_list_cache_%d_%d";
    public static final String KEY_DUAL_CAMERA_RECT = "dual_camera_rect";
    public static final String KEY_ENABLE_AUTO_MESSAGE = "enable_auto_message_%d";
    public static final String KEY_ENABLE_AUTO_MESSAGE_GUIDE = "enable_auto_message_guide_new_%d";
    public static final String KEY_GAME_LIST_CACHE = "game_list_cache_%d_%d";
    public static final String KEY_GUIDE_TOOL_SHOW = "guide_tool_show";
    public static final String KEY_HIGHT_DEFINITION_MODE = "hight_definition_mode";
    public static final String KEY_HISTORY_LIVE = "key_history_live_%d_%d";
    public static final String KEY_LAST_CHANNEL = "last_channel_%d_%d";
    public static final String KEY_LAST_LABEL_ID = "last_label_id";
    public static final String KEY_LAST_LABEL_NAME = "last_label_name";
    public static final String KEY_LAST_LABEL_TYPE = "last_label_type";
    public static final String KEY_LAST_LIVE_MODE = "last_live_mode_%d";
    public static final String KEY_LAST_LIVE_NAME = "last_live_name_%d";
    public static final String KEY_LAST_PACKAGE_NAME = "last_package_name_%d";
    public static final String KEY_LAST_PIC_URL = "last_pc_url_%d";

    public interface FuncName {
        public static final String CHANGE_LIVE_INFO = "changeLiveInfo";
        public static final String CREATE_ADDR = "createAddr";
        public static final String GET_GAME_LIVEING_GAME_LIST = "getMobileGameList";
        public static final String GET_MOBILE_LIVING_GAME_INFO = "getMobileLivingGameInfo";
        public static final String GET_MOBILE_PRESENTER_CHANNEL = "getMobilePresenterChannel";
        public static final String GET_OPEN_RTMP_ADDR = "getOpenRtmpAddr";
        public static final String GET_PRESENTER_SIGN_INVITE = "getPresenterSignInvite";
        public static final String GET_ZHU_SHOU_LIVEING_GAME_LIST = "getZhuShouLivingGameList";
        public static final String OPEN_RTMP_END_LIVE = "openRtmpEndLive";
        public static final String QUERY_ADDR = "queryAddr";
        public static final String QUERY_POPUP_DATA = "queryPopupData";
    }

    public interface ServiceName {
        public static final String GAME_LIVE_SERVER_NAME = "liveui";
        public static final String MOBILE_LIVE_SERVER_NAME = "mobileui";
        public static final String PRESENTERUI = "presenterui";
    }
}
