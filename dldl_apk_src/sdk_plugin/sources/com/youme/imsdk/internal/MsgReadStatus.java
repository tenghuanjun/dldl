package com.youme.imsdk.internal;

import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public class MsgReadStatus {

    @SerializedName("ChatType")
    private int chatType;

    @SerializedName("MsgId")
    private long msgId;

    @SerializedName("RecvId")
    private String recvId;

    public void setRecvId(String str) {
        this.recvId = str;
    }

    public String getRecvId() {
        return this.recvId;
    }

    public void setChatType(int i) {
        this.chatType = i;
    }

    public int getChatType() {
        return this.chatType;
    }

    public void setMsgId(long j) {
        this.msgId = j;
    }

    public long getMsgId() {
        return this.msgId;
    }
}
