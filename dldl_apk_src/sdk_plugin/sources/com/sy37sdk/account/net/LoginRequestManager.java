package com.sy37sdk.account.net;

import com.sq.tool.network.SqHttpCallback;
import com.sq.tool.network.SqRequest;
import com.sq.tool.sqtools.detector.DevicesFingerprint;
import com.sqwan.common.constants.SqConstants;
import com.sqwan.common.request.CommonParamsV3;
import com.sqwan.common.track.SqTrackBtn;
import com.sqwan.common.util.AppUtils;
import com.sqwan.common.util.LogUtil;
import com.sqwan.common.util.SQContextWrapper;
import com.sy37sdk.account.UrlConstant;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class LoginRequestManager {
    public static void phoneLoginPwd(String str, String str2, SqHttpCallback<String> sqHttpCallback) {
        LogUtil.i("手机+密码登录");
        SqRequest.of(UrlConstant.URL_LOGIN_PHONE_PWD).signV3().addHeader("D-Token", DevicesFingerprint.getDevToken(SQContextWrapper.getActivity())).addParam("pwd", str2).addParam("mobile", str).addParam("display_name", AppUtils.getAppName(SQContextWrapper.getActivity())).addParam(SqConstants.TRANS_INFO, SqRequest.getTransInfo()).addParamsTransformer(new CommonParamsV3()).post(sqHttpCallback, String.class);
    }

    public static void sendPhoneCode(String str, SqHttpCallback<Void> sqHttpCallback) {
        LogUtil.i("获取手机验证码");
        SqRequest.of(UrlConstant.URL_LOGIN_SEND_CODE).signV3().addParam("mobile", str).addParamsTransformer(new CommonParamsV3()).post(sqHttpCallback, Void.class);
    }

    public static void phoneLoginCheckCode(String str, String str2, SqHttpCallback<String> sqHttpCallback) {
        LogUtil.i("确认验证码");
        SqRequest.of(UrlConstant.URL_LOGIN_CHECK_CODE).signV3().addHeader("D-Token", DevicesFingerprint.getDevToken(SQContextWrapper.getActivity())).addParam("mobile", str).addParam(SqConstants.SCODE, str2).addParam(SqConstants.TRANS_INFO, SqRequest.getTransInfo()).addParamsTransformer(new CommonParamsV3()).post(sqHttpCallback, String.class);
    }

    public static void phoneLoginTicket(String str, SqHttpCallback<String> sqHttpCallback) {
        LogUtil.i("Ticket登录");
        SqRequest.of(UrlConstant.URL_LOGIN_TICKET).signV3().addHeader("D-Token", DevicesFingerprint.getDevToken(SQContextWrapper.getActivity())).addParam("ticket", str).addParam("display_name", AppUtils.getAppName(SQContextWrapper.getActivity())).addParam(SqConstants.TRANS_INFO, SqRequest.getTransInfo()).addParamsTransformer(new CommonParamsV3()).post(sqHttpCallback, String.class);
    }

    public static void wechatLogin(String str, SqHttpCallback<String> sqHttpCallback) {
        LogUtil.i(SqTrackBtn.SqTrackBtnExt.wechat);
        SqRequest.of(UrlConstant.WECHAT_LOGIN).signV3().addHeader("D-Token", DevicesFingerprint.getDevToken(SQContextWrapper.getActivity())).addParam("auth_code", str).addParam("display_name", AppUtils.getAppName(SQContextWrapper.getActivity())).addParam(SqConstants.TRANS_INFO, SqRequest.getTransInfo()).addParamsTransformer(new CommonParamsV3()).post(sqHttpCallback, String.class);
    }
}
