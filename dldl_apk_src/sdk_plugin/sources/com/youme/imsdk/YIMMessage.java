package com.youme.imsdk;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public class YIMMessage {
    private int createTime;
    private int distance;
    private int isRead;
    private int mChatType = 0;
    private String mReceiveId = null;
    private String mSenderId = null;
    private IYIMMessageBodyBase mMessageBody = null;
    private long mMsgId = 0;
    private int mMsgType = 0;

    public boolean getIsRead() {
        return this.isRead == 1;
    }

    public void setRead(boolean z) {
        if (z) {
            this.isRead = 1;
        } else {
            this.isRead = 0;
        }
    }

    public int getDistance() {
        return this.distance;
    }

    public void setDistance(int i) {
        this.distance = i;
    }

    public int getChatType() {
        return this.mChatType;
    }

    public String getReceiveID() {
        return this.mReceiveId;
    }

    public String getSenderID() {
        return this.mSenderId;
    }

    public IYIMMessageBodyBase getMessageBody() {
        return this.mMessageBody;
    }

    public int getMessageType() {
        return this.mMsgType;
    }

    public long getMessageID() {
        return this.mMsgId;
    }

    public void setChatType(int i) {
        this.mChatType = i;
    }

    public void setReceiveID(String str) {
        this.mReceiveId = str;
    }

    public void setSenderID(String str) {
        this.mSenderId = str;
    }

    public void setMessageBody(IYIMMessageBodyBase iYIMMessageBodyBase) {
        this.mMessageBody = iYIMMessageBodyBase;
    }

    public void setMeesageID(long j) {
        this.mMsgId = j;
    }

    public void setMessageType(int i) {
        this.mMsgType = i;
    }

    public int getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(int i) {
        this.createTime = i;
    }
}
