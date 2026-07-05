package com.huyaudbunify.msg;

import com.huyaudbunify.bean.EmptyBean;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class MsgNewLogin extends MsgBase<EmptyBean> {
    public static long mMsgId = 65539;
    public static String mUrl = "";

    /* JADX WARN: Type inference failed for: r0v0, types: [T, com.huyaudbunify.bean.EmptyBean] */
    public MsgNewLogin() {
        this.mMsgData = new EmptyBean();
    }
}
