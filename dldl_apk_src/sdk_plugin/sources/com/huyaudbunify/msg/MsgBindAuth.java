package com.huyaudbunify.msg;

import com.huyaudbunify.HuyaAuth;
import com.huyaudbunify.bean.ReqBindAuth;
import com.huyaudbunify.util.HuyaUrlUtil;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class MsgBindAuth extends MsgBase<ReqBindAuth> {
    public static long mMsgId = 4183;
    public static String mDomain = HuyaUrlUtil.constUrlGetPass;
    public static String mDevDomain = HuyaUrlUtil.constUrlGetPassDev;
    public static String mUrl = "/open/hy/third/login/V2/bind";

    public static String getUrl() {
        return (HuyaAuth.getInstance().isDeveloper() ? mDevDomain : mDomain) + mUrl;
    }

    public static String getCgi() {
        return "/" + HuyaUrlUtil.constServName + "/hyBindAuth";
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [T, com.huyaudbunify.bean.ReqBindAuth] */
    public MsgBindAuth() {
        this.mMsgData = new ReqBindAuth();
    }
}
