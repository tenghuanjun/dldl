package com.huyaudbunify.msg;

import com.huyaudbunify.BuildConfig;
import com.huyaudbunify.HuyaAuth;
import com.huyaudbunify.bean.ReqLoginAnonymous;
import com.huyaudbunify.util.HuyaUrlUtil;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class MsgLoginAnonymous extends MsgBase<ReqLoginAnonymous> {
    public static long mMsgId = 4107;
    public static String mDomain = HuyaUrlUtil.constUrlLgn;
    public static String mDevDomain = HuyaUrlUtil.constUrlLgnDev;
    public static String mUrl = "/open/hy/anonymousLogin";

    public static String getUrl() {
        return (HuyaAuth.getInstance().isDeveloper() ? mDevDomain : mDomain) + mUrl;
    }

    public static String getCgi() {
        return "/" + HuyaUrlUtil.constServName + "/" + (BuildConfig.IS_HUYA_ACCOUNT.booleanValue() ? "hyanonymousLogin" : "hynewanonymousLogin");
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [T, com.huyaudbunify.bean.ReqLoginAnonymous] */
    public MsgLoginAnonymous() {
        this.mMsgData = new ReqLoginAnonymous();
    }
}
