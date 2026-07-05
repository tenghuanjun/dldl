package com.youme.imsdk;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public class YIMMessageBodyText implements IYIMMessageBodyBase {
    private String mMsgContent = null;
    private String mAttachParam = null;

    public void setMessageContent(String str) {
        this.mMsgContent = str;
    }

    public String getMessageContent() {
        return this.mMsgContent;
    }

    public String getAttachParam() {
        return this.mAttachParam;
    }

    public void setAttachParam(String str) {
        this.mAttachParam = str;
    }
}
