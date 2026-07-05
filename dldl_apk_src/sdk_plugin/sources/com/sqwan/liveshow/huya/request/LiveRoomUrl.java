package com.sqwan.liveshow.huya.request;

import com.sqwan.common.annotation.UrlUpdate;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class LiveRoomUrl {
    public static final String KEY_LIVE_PLATFORM = "live_platform";
    public static final String KEY_LIVE_RECOMMENDED_ANCHOR = "live_recommend";
    public static final String KEY_LIVE_ROOM_ENTER = "live_room_enter";
    public static final String KEY_LIVE_ROOM_IM_FETCH = "live_fetch_danmu";
    public static final String KEY_LIVE_ROOM_IM_SEND = "live_send_danmu";
    public static final String KEY_LIVE_ROOM_LEAVE = "live_room_leave";
    public static final String KEY_LIVE_SETTING = "live_setting";
    private static String LIVE_SHOW_HOST = "https://live-api-secure.37.com.cn";

    @UrlUpdate(value = KEY_LIVE_PLATFORM, xValue = "x_live_platform")
    public static String LIVE_MENU_URL = LIVE_SHOW_HOST + "/api/live-service/v1/video/platforms";

    @UrlUpdate(value = KEY_LIVE_SETTING, xValue = "x_live_setting")
    public static String LIVE_CONFIG = LIVE_SHOW_HOST + "/api/live-service/v1/video/apps";

    @UrlUpdate(value = KEY_LIVE_RECOMMENDED_ANCHOR, xValue = "x_live_recommend")
    public static String LIVE_RECOMMENDED_ANCHOR = LIVE_SHOW_HOST + "/api/live-service/v1/video/anchors";
    private static String LIVE_SHOW_ROOM_HOST = "https://room-api-secure.37.com.cn";

    @UrlUpdate(value = KEY_LIVE_ROOM_ENTER, xValue = "x_enter_live_room")
    public static String LIVE_ROOM_ENTER = LIVE_SHOW_ROOM_HOST + "/v1/room/enter";

    @UrlUpdate(value = KEY_LIVE_ROOM_LEAVE, xValue = "x_leave_live_room")
    public static String LIVE_ROOM_LEAVE = LIVE_SHOW_ROOM_HOST + "/v1/room/leave";
    private static String LIVE_SHOW_IM_HOST = "https://im-api-secure.37.com.cn";

    @UrlUpdate(value = KEY_LIVE_ROOM_IM_SEND, xValue = "x_send_danma")
    public static String LIVE_ROOM_IM_SEND = LIVE_SHOW_IM_HOST + "/v1/im/send";

    @UrlUpdate(value = KEY_LIVE_ROOM_IM_FETCH, xValue = "x_fetch_danma")
    public static String LIVE_ROOM_IM_FETCH = LIVE_SHOW_IM_HOST + "/v1/im/fetch";
}
