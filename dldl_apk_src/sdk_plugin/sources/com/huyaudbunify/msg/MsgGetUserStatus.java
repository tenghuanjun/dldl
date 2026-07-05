package com.huyaudbunify.msg;

import com.huyaudbunify.HuyaAuth;
import com.huyaudbunify.bean.ReqGetUserStatus;
import com.huyaudbunify.util.HuyaUrlUtil;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class MsgGetUserStatus extends MsgBase<ReqGetUserStatus> {
    public static long mMsgId = 4186;
    public static String mDomain = HuyaUrlUtil.constUrlLgn;
    public static String mDevDomain = HuyaUrlUtil.constUrlLgnDev;
    public static String mUrl = "open/cloud/user/getStatus";

    public static String getUrl() {
        return (HuyaAuth.getInstance().isDeveloper() ? mDevDomain : mDomain) + mUrl;
    }

    public static String getCgi() {
        return "/" + HuyaUrlUtil.constServName + "/hyuserStatus";
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [T, com.huyaudbunify.bean.ReqGetUserStatus] */
    public MsgGetUserStatus() {
        this.mMsgData = new ReqGetUserStatus();
    }
}
