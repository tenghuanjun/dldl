package com.youme.imsdk.internal;

import com.google.gson.annotations.SerializedName;
import kotlin.jvm.internal.LongCompanionObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public class SendMessage {

    @SerializedName("forbidEndTime")
    private String endTime;

    @SerializedName("IsForbidRoom")
    private int isForbidRoom;

    @SerializedName("messageID")
    private Long messageID;

    @SerializedName("reasonType")
    private int reasonType;

    @SerializedName("RequestID")
    private long requestId;

    @SerializedName("SendTime")
    private int sendTime;

    public long getRequestId() {
        return this.requestId;
    }

    public void setRequestId(long j) {
        this.requestId = j;
    }

    public int getSendTime() {
        return this.sendTime;
    }

    public void setSendTime(int i) {
        this.sendTime = i;
    }

    public boolean getIsForbidRoom() {
        return this.isForbidRoom == 1;
    }

    public void setIsForbidRoom(boolean z) {
        this.isForbidRoom = z ? 1 : 0;
    }

    public int getReasonType() {
        return this.reasonType;
    }

    public void setReasonType(int i) {
        this.reasonType = i;
    }

    public long getEndTime() {
        String str = this.endTime;
        if (str == null || str.isEmpty()) {
            return 0L;
        }
        try {
            return Long.parseLong(this.endTime);
        } catch (Throwable unused) {
            return LongCompanionObject.MAX_VALUE;
        }
    }

    public String getEndTimeStr() {
        return this.endTime;
    }

    public void setEndTime(long j) {
        this.endTime = "" + j;
    }

    public long getMessageID() {
        return this.messageID.longValue();
    }

    public void setMessageID(long j) {
        this.messageID = Long.valueOf(j);
    }
}
