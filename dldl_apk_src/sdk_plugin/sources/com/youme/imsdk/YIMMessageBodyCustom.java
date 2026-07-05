package com.youme.imsdk;

import android.util.Base64;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public class YIMMessageBodyCustom implements IYIMMessageBodyBase {
    private byte[] mMsgContent;

    public void setMessageContent(String str) {
        this.mMsgContent = Base64.decode(str, 0);
    }

    public byte[] getMessageContent() {
        return this.mMsgContent;
    }
}
