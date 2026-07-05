package com.huyaudbunify.msg;

import com.huyaudbunify.HuyaAuth;
import com.huyaudbunify.bean.ReqOtherAppCredLogin;
import com.huyaudbunify.util.HuyaUrlUtil;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class MsgOtherAppCredLogin extends MsgBase<ReqOtherAppCredLogin> {
    public static long mMsgId = 4110;
    public static String mDomain = HuyaUrlUtil.constUrlReg;
    public static String mDevDomain = HuyaUrlUtil.constUrlRegDev;
    public static String mUrl = "/open/hy/otpLogin";

    public static String getUrl() {
        return (HuyaAuth.getInstance().isDeveloper() ? mDevDomain : mDomain) + mUrl;
    }

    public static String getCgi() {
        return "/" + HuyaUrlUtil.constServName + "/hyAppOtpLogin";
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [T, com.huyaudbunify.bean.ReqOtherAppCredLogin] */
    public MsgOtherAppCredLogin() {
        this.mMsgData = new ReqOtherAppCredLogin();
    }
}
