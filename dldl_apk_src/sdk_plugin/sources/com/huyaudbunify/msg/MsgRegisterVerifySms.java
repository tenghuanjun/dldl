package com.huyaudbunify.msg;

import com.huyaudbunify.BuildConfig;
import com.huyaudbunify.HuyaAuth;
import com.huyaudbunify.bean.ReqRegisterVerifySms;
import com.huyaudbunify.util.HuyaUrlUtil;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class MsgRegisterVerifySms extends MsgBase<ReqRegisterVerifySms> {
    public static long mMsgId = 4113;
    public static String mUrl;
    public static String mDomain = HuyaUrlUtil.constUrlReg;
    public static String mDevDomain = HuyaUrlUtil.constUrlRegDev;

    static {
        mUrl = BuildConfig.IS_HUYA_ACCOUNT.booleanValue() ? "/open/hy/verifyRegCodeMobile" : "/open/smsCode/verify";
    }

    public static String getUrl() {
        return (HuyaAuth.getInstance().isDeveloper() ? mDevDomain : mDomain) + mUrl;
    }

    public static String getCgi() {
        return "/" + HuyaUrlUtil.constServName + "/hyverifyRegCodeMobile";
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [T, com.huyaudbunify.bean.ReqRegisterVerifySms] */
    public MsgRegisterVerifySms() {
        this.mMsgData = new ReqRegisterVerifySms();
    }
}
