package com.huyaudbunify.msg;

import com.huyaudbunify.HuyaAuth;
import com.huyaudbunify.bean.ReqEmailLogin;
import com.huyaudbunify.util.HuyaUrlUtil;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class MsgEmailLogin extends MsgBase<ReqEmailLogin> {
    public static long mMsgId = 4201;
    public static String mDomain = HuyaUrlUtil.constUrlReg;
    public static String mDevDomain = HuyaUrlUtil.constUrlRegDev;
    public static String mUrl = "/open/emailLogin";

    public static String getUrl() {
        return (HuyaAuth.getInstance().isDeveloper() ? mDevDomain : mDomain) + mUrl;
    }

    public static String getCgi() {
        return "/" + HuyaUrlUtil.constServName + "/hwEmailLogin";
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [T, com.huyaudbunify.bean.ReqEmailLogin] */
    public MsgEmailLogin() {
        this.mMsgData = new ReqEmailLogin();
    }
}
