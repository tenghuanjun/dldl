package com.huyaudbunify.msg;

import com.huyaudbunify.bean.ReqGetTicket;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class MsgGetRegInfo extends MsgBase<ReqGetTicket> {
    public static long mMsgId = 184549377;
    public static String mUrl = "";

    /* JADX WARN: Type inference failed for: r0v0, types: [T, com.huyaudbunify.bean.ReqGetTicket] */
    public MsgGetRegInfo() {
        this.mMsgData = new ReqGetTicket();
    }
}
