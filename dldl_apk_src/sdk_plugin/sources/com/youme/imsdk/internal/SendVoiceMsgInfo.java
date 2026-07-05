package com.youme.imsdk.internal;

import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public class SendVoiceMsgInfo {

    @SerializedName("Duration")
    private Integer duration;

    @SerializedName("forbidEndTime")
    private Long endTime;

    @SerializedName("IsForbidRoom")
    private Integer isForbidRoom;

    @SerializedName("LocalPath")
    private String localPath;

    @SerializedName("messageID")
    private Long messageID;

    @SerializedName("reasonType")
    private Integer reasonType;

    @SerializedName("RequestID")
    private Long requestId;

    @SerializedName("SendTime")
    private Integer sendTime;

    @SerializedName("Text")
    private String text;

    public long getRequestId() {
        return this.requestId.longValue();
    }

    public void setRequestId(long j) {
        this.requestId = Long.valueOf(j);
    }

    public String getText() {
        return this.text;
    }

    public void setText(String str) {
        this.text = str;
    }

    public String getLocalPath() {
        return this.localPath;
    }

    public void setLocalPath(String str) {
        this.localPath = str;
    }

    public int getDuration() {
        return this.duration.intValue();
    }

    public void setDuration(int i) {
        this.duration = Integer.valueOf(i);
    }

    public int getSendTime() {
        return this.sendTime.intValue();
    }

    public void setSendTime(int i) {
        this.sendTime = Integer.valueOf(i);
    }

    public boolean getIsForbidRoom() {
        return this.isForbidRoom.intValue() == 1;
    }

    public void setIsForbidRoom(boolean z) {
        this.isForbidRoom = Integer.valueOf(z ? 1 : 0);
    }

    public int getReasonType() {
        return this.reasonType.intValue();
    }

    public void setReasonType(int i) {
        this.reasonType = Integer.valueOf(i);
    }

    public long getEndTime() {
        return this.endTime.longValue();
    }

    public void setEndTime(long j) {
        this.endTime = Long.valueOf(j);
    }

    public long getMessageID() {
        return this.messageID.longValue();
    }

    public void setMessageID(long j) {
        this.messageID = Long.valueOf(j);
    }
}
