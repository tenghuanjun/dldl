package com.sqwan.liveshow.error;

import com.huyaudbunify.core.LoginEvent;
import com.sq.diagnostic.assistant.log.impl.ErrorStats;
import com.sy37sdk.account.alifast.FastLoginConstants;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public enum LiveshowResult {
    error_inited_youmemanager(10001, "YouMeManager init fail"),
    error_joinRoom(10002, "加入房间失败"),
    error_leaveRoom(ErrorStats.ERROR_CODE_COMPRESSION_LOG_FAIL, "离开房间失败"),
    error_inited_api(10004, "api init fail"),
    error_inited_reqeust(10005, "api request room fail"),
    error_inited_room_empty(10006, "error_inited_room_empty"),
    error_inited_not_submitrole(LoginEvent.LoginMessage.onDCChanged, "error_inited_not_submitrole"),
    error_inited_joining(LoginEvent.LoginMessage.onSvcData, "error_inited_joining"),
    error_im_join(LoginEvent.LoginMessage.onUserDCChanged, "error_im_join"),
    error_im_login(10010, "error_im_login"),
    error_inited_sdkinit(10011, "error_inited_sdkinit"),
    error_sdkcode_support(10012, "直播功能不支持手机系统5.0以下"),
    error_cpu_supprot(LoginEvent.LoginMessage.onUInfoLogo, "直播功能不支持手机系统架构"),
    error_unknown(10000, FastLoginConstants.MESSAGE.FAILURE_VERIFY_FAIL_UNKNOWN),
    success_inited("初始化成功"),
    success_joinRoom("加入房间成功"),
    success_leaveRoom("离开房间成功"),
    success_voiceChange("频道状态切换"),
    success_joinRoomList("打开直播房间列表成功"),
    success_destroyRoom("直播销毁"),
    success_im_login("success_im_login"),
    success_im_join("success_im_login");

    private int error;
    private String msg;

    LiveshowResult(int i, String str) {
        this.error = i;
        this.msg = str;
    }

    LiveshowResult(String str) {
        this.msg = str;
    }

    public int getError() {
        return this.error;
    }

    public void setError(int i) {
        this.error = i;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String str) {
        this.msg = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return "LiveshowResult{error=" + this.error + ", msg='" + this.msg + '\'' + AbstractJsonLexerKt.END_OBJ;
    }
}
