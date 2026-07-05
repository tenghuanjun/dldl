package com.huyaudbunify.msg;

import com.huya.berry.gamesdk.module.ICommonConstants;
import com.huyaudbunify.HuyaAuth;
import com.huyaudbunify.bean.EmptyBean;
import com.huyaudbunify.util.HuyaUrlUtil;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class MsgReportCache extends MsgBase<EmptyBean> {
    public static long mMsgId = 4146;
    public static String mDomain = HuyaUrlUtil.constUrlLog;
    public static String mDevDomain = HuyaUrlUtil.constUrlLogDev;
    public static String mUrl = "/open/log/report";

    public static String getUrl() {
        return (HuyaAuth.getInstance().isDeveloper() ? mDevDomain : mDomain) + mUrl;
    }

    public static String getCgi() {
        return "/" + HuyaUrlUtil.constServName + "/" + ICommonConstants.FuncName.METRIC_REPORT;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [T, com.huyaudbunify.bean.EmptyBean] */
    public MsgReportCache() {
        this.mMsgData = new EmptyBean();
    }
}
