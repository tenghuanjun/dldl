package com.sqwan.common.util;

import com.sq.diagnostic.assistant.log.impl.ErrorStats;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public enum SDKError {
    NET_REQUEST_FAIL(10001, "网络异常，请稍候再试"),
    NET_DATA_PARSE_ERROR(10002, "服务端数据异常，请联系客服【20002】"),
    NET_TIME_OUT_ERROR(ErrorStats.ERROR_CODE_COMPRESSION_LOG_FAIL, "网络超时,请稍候再试"),
    ACCOUNT_LOGIN_ERROR(ErrorStats.ERROR_CODE_GET_PING, "登录数据错误，请联系客服【10004】"),
    ACCOUNT_LOGIN_CANCEL(20002, "取消登录"),
    PARAMS_ERROR_NULL(ErrorStats.ERROR_CODE_FETCH_SCHEDULE_TASK, "游戏数据为空，请联系客服【10005】"),
    PAY_FAIL(ErrorStats.ERROR_CODE_GET_HELP_DOC, "支付失败【20003】");

    public int code;
    public String message;

    SDKError(int i, String str) {
        this.code = i;
        this.message = str;
    }
}
