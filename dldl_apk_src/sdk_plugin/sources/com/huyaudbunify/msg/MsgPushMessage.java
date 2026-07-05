package com.huyaudbunify.msg;

import com.huyaudbunify.bean.ReqPushMessage;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class MsgPushMessage extends MsgBase<ReqPushMessage> {
    public static long mMsgId = 65537;
    public static String mUrl = "";

    /* JADX WARN: Type inference failed for: r0v0, types: [T, com.huyaudbunify.bean.ReqPushMessage] */
    public MsgPushMessage() {
        this.mMsgData = new ReqPushMessage();
    }
}
