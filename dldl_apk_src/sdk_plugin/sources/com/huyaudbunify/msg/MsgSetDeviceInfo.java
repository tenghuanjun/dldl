package com.huyaudbunify.msg;

import com.huyaudbunify.bean.ReqSetDeviceInfo;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class MsgSetDeviceInfo extends MsgBase<ReqSetDeviceInfo> {
    public static long mMsgId = 184549409;
    public static String mUrl = "";

    /* JADX WARN: Type inference failed for: r0v0, types: [T, com.huyaudbunify.bean.ReqSetDeviceInfo] */
    public MsgSetDeviceInfo() {
        this.mMsgData = new ReqSetDeviceInfo();
    }
}
