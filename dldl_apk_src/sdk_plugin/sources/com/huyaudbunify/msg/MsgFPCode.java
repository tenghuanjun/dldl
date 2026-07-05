package com.huyaudbunify.msg;

import com.huyaudbunify.BuildConfig;
import com.huyaudbunify.HuyaAuth;
import com.huyaudbunify.bean.ReqFPCode;
import com.huyaudbunify.util.HuyaUrlUtil;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class MsgFPCode extends MsgBase<ReqFPCode> {
    public static long mMsgId = 4118;
    public static String mUrl;
    public static String mDomain = HuyaUrlUtil.constUrlReg;
    public static String mDevDomain = HuyaUrlUtil.constUrlRegDev;

    static {
        mUrl = BuildConfig.IS_HUYA_ACCOUNT.booleanValue() ? "/open/hy/findPswBySms" : "/open/findPswBySms";
    }

    public static String getUrl() {
        return (HuyaAuth.getInstance().isDeveloper() ? mDevDomain : mDomain) + mUrl;
    }

    public static String getCgi() {
        return "/" + HuyaUrlUtil.constServName + "/hyfindPswBySms";
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [T, com.huyaudbunify.bean.ReqFPCode] */
    public MsgFPCode() {
        this.mMsgData = new ReqFPCode();
    }
}
