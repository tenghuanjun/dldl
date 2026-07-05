package com.huyaudbunify.msg;

import com.huyaudbunify.HuyaAuth;
import com.huyaudbunify.bean.ReqGetBindList;
import com.huyaudbunify.util.HuyaUrlUtil;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class MsgGetBindList extends MsgBase<ReqGetBindList> {
    public static long mMsgId = 4185;
    public static String mDomain = HuyaUrlUtil.constUrlGetPass;
    public static String mDevDomain = HuyaUrlUtil.constUrlGetPassDev;
    public static String mUrl = "/open/hy/third/login/V2/bindList";

    public static String getUrl() {
        return (HuyaAuth.getInstance().isDeveloper() ? mDevDomain : mDomain) + mUrl;
    }

    public static String getCgi() {
        return "/" + HuyaUrlUtil.constServName + "/hyGetBindList";
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [T, com.huyaudbunify.bean.ReqGetBindList] */
    public MsgGetBindList() {
        this.mMsgData = new ReqGetBindList();
    }
}
