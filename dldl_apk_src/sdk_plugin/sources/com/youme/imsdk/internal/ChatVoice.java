package com.youme.imsdk.internal;

import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public class ChatVoice {

    @SerializedName("Duration")
    public Integer duration;

    @SerializedName("forbidEndTime")
    public Long endTime;

    @SerializedName("Text")
    public String extraText;

    @SerializedName("IsForbidRoom")
    public Integer isForbidRoom;

    @SerializedName("LocalPath")
    public String localPath;

    @SerializedName("reasonType")
    public Integer reasonType;

    @SerializedName("RequestID")
    public Long requestId;

    @SerializedName("SendTime")
    public Integer sendTime;
}
