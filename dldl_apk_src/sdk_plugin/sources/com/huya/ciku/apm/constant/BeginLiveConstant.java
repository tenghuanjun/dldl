package com.huya.ciku.apm.constant;

import com.alibaba.fastjson.asm.Opcodes;
import com.sy37sdk.order.third.douyin.DouYinPayWay;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public enum BeginLiveConstant {
    SUCCESS(0, "{\"0\":\"huya开播成功\", \"1\" : \"rtmp开播成功\"}", true),
    SUCCESS_TEN_S(40, "{\"0\":\"huya开播成功\", \"1\" : \"rtmp开播成功\"}", true),
    SUCCESS_MINUTE(41, "{\"0\":\"huya开播成功\", \"1\" : \"rtmp开播成功\"}", true),
    ERR_UID_INVALID(1, "登录状态异常，请重新登录", false),
    ERR_NO_CHANNEL_ID(2, "没有获取到直播间", false),
    ERR_NO_GAME_ID(3, "获取不到开播品类", false),
    ERR_TICKET_EMPTY(4, "登录状态异常，请重新登录", false),
    ERR_GET_CONFIG_FAIL(5, "获取不到开播设置", false),
    ERR_START_LIVE_TIMEOUT(6, "连接服务器失败，请重新开播", false),
    ERR_START_LIVE_FAIL(7, "连接服务器失败，请重新开播", false),
    ERR_LIVE_CONFIG_INVALID(20, "开播参数无效，请稍候重试", false),
    ERR_NO_STREAM_NAME(21, "无效的streamName", false),
    ERR_HUYA_CONFIG_INVALID(100, "huya开播参数无效，请稍候重试", false),
    ERR_HUYA_INIT_TIME_OUT(101, "媒体初始化失败,请重新开播", false),
    ERR_HUYA_VP_TIME_OUT(102, "Vp请求超时,请检查网络", false),
    ERR_HUYA_VP_TRY_TIME_OUT(103, "Vp请求失败,请检查网络", false),
    ERR_HUYA_NO_VP_TIME_OUT(104, "Vp连接失败,请检查网络", false),
    ERR_HUYA_LINK_TIME_OUT(105, "Huya推流连接超时,请检查网络", false),
    ERR_HUYA_BREAK(106, "Huya推流连接失败,请检查网络", false),
    ERR_HUYA_PUSH_FAIL(107, "Huya推流失败,请检查网络", false),
    ERR_HUYA_PUSH_TIME_OUT(108, "Huya推流超时,请检查网络", false),
    ERR_HUYA_STREAM_TASK_FAIL(109, "Huya推流转推失败", false),
    ERR_HUYA_STREAM_TASK_TIME_OUT(110, "Huya推流转推请求超时，请检查网络", false),
    ERR_RTMP_CONFIG_INVALID(130, "RTMP开播参数无效，请稍候重试", false),
    ERR_RTMP_INIT_TIME_OUT(131, "RTMP初始化失败,请重新开播", false),
    ERR_RTMP_CONNECT_TIME_OUT(132, "RTMP推流连接超时,请检查网络", false),
    ERR_RTMP_CONNECT_FAIL(133, "RTMP推流连接失败,请检查网络", false),
    ERR_RTMP_PUSH_FAIL(134, "RTMP推流失败,请检查网络", false),
    ERR_RTMP_PUSH_TIME_OUT(135, "RTMP推流超时,请检查网络", false),
    ERR_VIDEO_OTHER_FAIL(200, "视频采集相关的未知错误", false),
    ERR_VIDEO_CONFIG_INVALID(201, "采集参数无效", false),
    ERR_VIDEO_COLLECTION_INIT_FAIL(202, "视频采集初始化失败", false),
    ERR_VIDEO_ENCODER_INIT_FAIL(203, "视频编码器初始化失败", false),
    ERR_VIDEO_ENCODER_FAIL(204, "视频编码失败", false),
    ERR_VIDEO_COLLECTION_FAIL(205, "视频采集失败", false),
    ERR_VIDEO_COLLECTION_TIME_OUT(206, "视频采集超时", false),
    ERR_AUDIO_OTHER_FAIL(220, "音频采集相关的未知错误", false),
    ERR_AUDIO_COLLECTION_FAIL(221, "音频采集失败", false),
    ERR_VIDEO_ENCODER_INIT_TIME_OUT(222, "视频编码器初始化超时", false),
    ERR_AIRPLAY_LINK_BORKEN(233, "AirPlay连接断开", false),
    SUCCESS_SCREEN_CAST(45, "{\"0\":\"rtsp投屏成功\", \"1\" : \"rtmp投屏成功\"}", true),
    ERR_RTSP_NOT_SAME_AREA_NETWORK(300, "rtsp投屏失败(非同一局域网)", false),
    ERR_RTSP_CONFIG_INVALID(150, "rtsp开播参数无效，请稍候重试", false),
    ERR_RTSP_INIT_TIME_OUT(Opcodes.DCMPL, "rtsp初始化失败,请重新开播", false),
    ERR_RTSP_CONNECT_TIME_OUT(152, "rtsp推流连接超时,请检查网络", false),
    ERR_RTSP_CONNECT_FAIL(Opcodes.IFEQ, "rtsp推流连接失败,请检查网络", false),
    ERR_RTSP_PUSH_FAIL(Opcodes.IFNE, "rtsp推流失败,请检查网络", false),
    ERR_RTSP_PUSH_TIME_OUT(155, "rtsp推流超时,请检查网络", false),
    ERR_RTMP_NOT_SAME_AREA_NETWORK(DouYinPayWay.ERROR_PARAM, "rtmp投屏失败(非同一局域网)", false);

    private int code;
    private Map<Integer, String> data = new HashMap();
    private String msg;
    private int otherCode;
    private boolean success;

    BeginLiveConstant(int i, String str, boolean z) {
        this.code = i;
        this.msg = str;
        this.success = z;
        try {
            JSONObject jSONObject = new JSONObject(str);
            JSONArray jSONArrayNames = jSONObject.names();
            if (jSONArrayNames != null) {
                int length = jSONArrayNames.length();
                for (int i2 = 0; i2 < length; i2++) {
                    String string = jSONArrayNames.getString(i2);
                    String strOptString = jSONObject.optString(string);
                    try {
                        this.data.put(Integer.valueOf(Integer.parseInt(string)), strOptString);
                    } catch (NumberFormatException unused) {
                    }
                }
            }
        } catch (JSONException unused2) {
        }
    }

    public int code() {
        return this.code;
    }

    public String msg() {
        return this.msg;
    }

    public boolean success() {
        return this.success;
    }

    public Map<Integer, String> data() {
        return this.data;
    }

    public static BeginLiveConstant get(int i) {
        for (BeginLiveConstant beginLiveConstant : values()) {
            if (beginLiveConstant.code == i) {
                return beginLiveConstant;
            }
        }
        return null;
    }
}
