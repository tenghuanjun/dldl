package com.huyaudbunify.msg;

import com.huyaudbunify.BuildConfig;
import com.huyaudbunify.HuyaAuth;
import com.huyaudbunify.bean.ReqLoginCred;
import com.huyaudbunify.util.HuyaUrlUtil;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class MsgAnonyLoginCred extends MsgBase<ReqLoginCred> {
    public static long mMsgId = 4139;
    public static String mUrl;
    public static String mDomain = HuyaUrlUtil.constUrlLgn;
    public static String mDevDomain = HuyaUrlUtil.constUrlLgnDev;

    static {
        BuildConfig.IS_HUYA_ACCOUNT.booleanValue();
        mUrl = "/open/hy/anonymous/credLogin";
    }

    public static String getUrl() {
        return (HuyaAuth.getInstance().isDeveloper() ? mDevDomain : mDomain) + mUrl;
    }

    public static String getCgi() {
        return "/" + HuyaUrlUtil.constServName + "/hyanonymousCredlogin";
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [T, com.huyaudbunify.bean.ReqLoginCred] */
    public MsgAnonyLoginCred() {
        this.mMsgData = new ReqLoginCred();
    }
}
