package com.huyaudbunify.msg;

import com.huyaudbunify.HuyaAuth;
import com.huyaudbunify.bean.ReqBindNewVerifySms;
import com.huyaudbunify.util.HuyaUrlUtil;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class MsgBindNewVerifySms extends MsgBase<ReqBindNewVerifySms> {
    public static long mMsgId = 4181;
    public static String mDomain = HuyaUrlUtil.constUrlReg;
    public static String mDevDomain = HuyaUrlUtil.constUrlRegDev;
    public static String mUrl = "/open/bindnew";

    public static String getUrl() {
        return (HuyaAuth.getInstance().isDeveloper() ? mDevDomain : mDomain) + mUrl;
    }

    public static String getCgi() {
        return "/" + HuyaUrlUtil.constServName + "/hyBindNewVerifySms";
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [T, com.huyaudbunify.bean.ReqBindNewVerifySms] */
    public MsgBindNewVerifySms() {
        this.mMsgData = new ReqBindNewVerifySms();
    }
}
