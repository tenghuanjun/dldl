package com.huyaudbunify.msg;

import com.huyaudbunify.HuyaAuth;
import com.huyaudbunify.bean.ReqLoginMobileSendSms;
import com.huyaudbunify.util.HuyaUrlUtil;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class MsgLoginSendMobileSms extends MsgBase<ReqLoginMobileSendSms> {
    public static long mMsgId = 4100;
    public static String mDomain = HuyaUrlUtil.constUrlLgn;
    public static String mDevDomain = HuyaUrlUtil.constUrlLgnDev;
    public static String mUrl = "/open/sms/login/code";

    public static String getUrl() {
        return (HuyaAuth.getInstance().isDeveloper() ? mDevDomain : mDomain) + mUrl;
    }

    public static String getCgi() {
        return "/" + HuyaUrlUtil.constServName + "/hyMobileSendSms";
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [T, com.huyaudbunify.bean.ReqLoginMobileSendSms] */
    public MsgLoginSendMobileSms() {
        this.mMsgData = new ReqLoginMobileSendSms();
    }
}
