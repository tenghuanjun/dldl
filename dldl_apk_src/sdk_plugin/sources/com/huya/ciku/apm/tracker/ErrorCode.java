package com.huya.ciku.apm.tracker;

import android.text.TextUtils;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public enum ErrorCode {
    OK(0, "正常"),
    ERR_UID_INVALID(1, "登录状态异常，请重新登录"),
    ERR_NO_CHANNEL_ID(2, "没有获取到直播间"),
    ERR_NO_GAME_ID(3, "获取不到开播品类"),
    ERR_TICKET_EMPTY(4, "登录状态异常，请重新登录"),
    ERR_GET_CONFIG_FAIL(5, "获取不到开播设置"),
    ERR_START_LIVE_TIMEOUT(6, "连接服务器失败，请重新开播"),
    ERR_START_LIVE_FAIL(7, "连接服务器失败，请重新开播"),
    ERR_LIVE_CONFIG_INVALID(100, "开播参数无效，请稍候重试"),
    ERR_TICKET_INVALID(101, "登录状态异常，请重新登录"),
    ERR_NEED_KICK_OTHER(102, "是否踢掉其他设备进频道?"),
    ERR_TAKE_OVER_TIMEOUT(1000, "转让房间超时"),
    ERR_TAKE_OVER_FAIL(1001, "转让房间失败"),
    ERR_NO_STREAM_NAME(8, "开播streamName无效");

    private int mCode;
    private String mError;
    private String mMsg;
    private Object mObject;
    private boolean mHasSvrCode = false;
    private int mSvrRespCode = 0;

    public boolean isHasSvrCode() {
        return this.mHasSvrCode;
    }

    public void setHasSvrCode(boolean z) {
        this.mHasSvrCode = z;
    }

    public int getSvrRespCode() {
        return this.mSvrRespCode;
    }

    public void setSvrRespCode(int i) {
        this.mSvrRespCode = i;
    }

    ErrorCode(int i, String str) {
        this.mObject = null;
        this.mCode = i;
        this.mMsg = str;
        this.mObject = 0;
    }

    public int getCode() {
        return this.mCode;
    }

    public String getMsg() {
        return this.mMsg;
    }

    public void setMsg(String str) {
        this.mMsg = str;
    }

    public String getError() {
        return this.mError;
    }

    public void setError(String str) {
        this.mError = str;
    }

    public Object getObject() {
        return this.mObject;
    }

    public void setObject(Object obj) {
        this.mObject = obj;
    }

    public String getReportMsg() {
        if (TextUtils.isEmpty(this.mError)) {
            return this.mMsg;
        }
        return this.mMsg + "_" + this.mError;
    }
}
