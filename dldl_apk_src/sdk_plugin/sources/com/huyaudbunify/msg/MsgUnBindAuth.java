package com.huyaudbunify.msg;

import com.huyaudbunify.HuyaAuth;
import com.huyaudbunify.bean.ReqUnBindAuth;
import com.huyaudbunify.util.HuyaUrlUtil;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class MsgUnBindAuth extends MsgBase<ReqUnBindAuth> {
    public static long mMsgId = 4184;
    public static String mDomain = HuyaUrlUtil.constUrlGetPass;
    public static String mDevDomain = HuyaUrlUtil.constUrlGetPassDev;
    public static String mUrl = "/open/hy/third/login/V2/unbind";

    public static String getUrl() {
        return (HuyaAuth.getInstance().isDeveloper() ? mDevDomain : mDomain) + mUrl;
    }

    public static String getCgi() {
        return "/" + HuyaUrlUtil.constServName + "/hyUnBindAuth";
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [T, com.huyaudbunify.bean.ReqUnBindAuth] */
    public MsgUnBindAuth() {
        this.mMsgData = new ReqUnBindAuth();
    }
}
