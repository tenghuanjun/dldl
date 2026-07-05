package com.huyaudbunify.msg;

import com.huyaudbunify.BuildConfig;
import com.huyaudbunify.HuyaAuth;
import com.huyaudbunify.bean.ReqRegisterSendSms;
import com.huyaudbunify.util.HuyaUrlUtil;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class MsgRegisterSendSms extends MsgBase<ReqRegisterSendSms> {
    public static long mMsgId = 4112;
    public static String mUrl;
    public static String mDomain = HuyaUrlUtil.constUrlReg;
    public static String mDevDomain = HuyaUrlUtil.constUrlRegDev;

    static {
        mUrl = BuildConfig.IS_HUYA_ACCOUNT.booleanValue() ? "/open/hy/sendRegCodeMobile" : "/open/smsCode/send";
    }

    public static String getUrl() {
        return (HuyaAuth.getInstance().isDeveloper() ? mDevDomain : mDomain) + mUrl;
    }

    public static String getCgi() {
        return "/" + HuyaUrlUtil.constServName + "/hysendRegCodeMobile";
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [T, com.huyaudbunify.bean.ReqRegisterSendSms] */
    public MsgRegisterSendSms() {
        this.mMsgData = new ReqRegisterSendSms();
    }
}
