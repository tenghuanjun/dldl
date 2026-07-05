package com.huyaudbunify.msg;

import com.huyaudbunify.HuyaAuth;
import com.huyaudbunify.bean.ReqAuthScanQr;
import com.huyaudbunify.util.HuyaUrlUtil;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class MsgAuthScanQr extends MsgBase<ReqAuthScanQr> {
    public static long mMsgId = 4132;
    public static String mDomain = HuyaUrlUtil.constUrlLgn;
    public static String mDevDomain = HuyaUrlUtil.constUrlLgnDev;
    public static String mUrl = "/open/hy/confirmQuickLogin";

    public static String getUrl() {
        return (HuyaAuth.getInstance().isDeveloper() ? mDevDomain : mDomain) + mUrl;
    }

    public static String getCgi() {
        return "/" + HuyaUrlUtil.constServName + "/hyauthScanQr";
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [T, com.huyaudbunify.bean.ReqAuthScanQr] */
    public MsgAuthScanQr() {
        this.mMsgData = new ReqAuthScanQr();
    }
}
