package com.huyaudbunify.msg;

import com.huyaudbunify.HuyaAuth;
import com.huyaudbunify.bean.ReqBindLoginEmail;
import com.huyaudbunify.util.HuyaUrlUtil;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class MsgBindLoginEmail extends MsgBase<ReqBindLoginEmail> {
    public static long mMsgId = 4200;
    public static String mDomain = HuyaUrlUtil.constUrlReg;
    public static String mDevDomain = HuyaUrlUtil.constUrlRegDev;
    public static String mUrl = "/open/bindLoginEmail";

    public static String getUrl() {
        return (HuyaAuth.getInstance().isDeveloper() ? mDevDomain : mDomain) + mUrl;
    }

    public static String getCgi() {
        return "/" + HuyaUrlUtil.constServName + "/hwBindEmail";
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [T, com.huyaudbunify.bean.ReqBindLoginEmail] */
    public MsgBindLoginEmail() {
        this.mMsgData = new ReqBindLoginEmail();
    }
}
