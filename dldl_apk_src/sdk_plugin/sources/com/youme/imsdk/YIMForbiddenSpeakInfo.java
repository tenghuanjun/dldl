package com.youme.imsdk;

import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public class YIMForbiddenSpeakInfo {

    @SerializedName("ChannelID")
    private String channelID;

    @SerializedName("forbidEndTime")
    private Long endTime;

    @SerializedName("IsForbidRoom")
    private Integer isForbidRoom;

    @SerializedName("reasonType")
    private Integer reasonType;

    public String getChannelID() {
        return this.channelID;
    }

    public boolean getIsForbidRoom() {
        return this.isForbidRoom.intValue() != 0;
    }

    public int getReasonType() {
        return this.reasonType.intValue();
    }

    public long getEndTime() {
        return this.endTime.longValue();
    }
}
