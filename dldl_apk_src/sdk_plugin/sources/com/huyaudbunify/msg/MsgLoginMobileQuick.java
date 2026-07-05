package com.huyaudbunify.msg;

import com.huyaudbunify.HuyaAuth;
import com.huyaudbunify.bean.ReqLoginMobileQuick;
import com.huyaudbunify.util.HuyaUrlUtil;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class MsgLoginMobileQuick extends MsgBase<ReqLoginMobileQuick> {
    public static long mMsgId = 4109;
    public static String mDomain = HuyaUrlUtil.constUrlLgn;
    public static String mDevDomain = HuyaUrlUtil.constUrlLgnDev;
    public static String mUrl = "/open/hy/mobileQuickLogin";

    public static String getUrl() {
        return (HuyaAuth.getInstance().isDeveloper() ? mDevDomain : mDomain) + mUrl;
    }

    public static String getCgi() {
        return "/" + HuyaUrlUtil.constServName + "/hymobilequickLogin";
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [T, com.huyaudbunify.bean.ReqLoginMobileQuick] */
    public MsgLoginMobileQuick() {
        this.mMsgData = new ReqLoginMobileQuick();
    }
}
