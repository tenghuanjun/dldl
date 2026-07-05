package com.alipay.sdk.app;

import com.bytedance.sdk.openadsdk.TTAdConstant;
import com.mobile.auth.gatewayauth.ResultCode;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public enum k {
    SUCCEEDED(9000, "处理成功"),
    FAILED(TTAdConstant.INIT_LOCAL_FAIL_CODE, "系统繁忙，请稍后再试"),
    CANCELED(6001, "用户取消"),
    NETWORK_ERROR(6002, "网络连接异常"),
    PARAMS_ERROR(4001, ResultCode.MSG_ERROR_INVALID_PARAM),
    DOUBLE_REQUEST(5000, "重复请求"),
    PAY_WAITTING(8000, "支付结果确认中");

    private int h;
    private String i;

    k(int i, String str) {
        this.h = i;
        this.i = str;
    }

    public void a(int i) {
        this.h = i;
    }

    public int a() {
        return this.h;
    }

    public void a(String str) {
        this.i = str;
    }

    public String b() {
        return this.i;
    }

    public static k b(int i) {
        if (i == 4001) {
            return PARAMS_ERROR;
        }
        if (i == 5000) {
            return DOUBLE_REQUEST;
        }
        if (i == 8000) {
            return PAY_WAITTING;
        }
        if (i == 9000) {
            return SUCCEEDED;
        }
        if (i == 6001) {
            return CANCELED;
        }
        if (i == 6002) {
            return NETWORK_ERROR;
        }
        return FAILED;
    }
}
