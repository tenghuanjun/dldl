package com.youme.imsdk.internal;

import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public class NoticeInfo {

    @SerializedName("BeginTime")
    public int iBeginTime;

    @SerializedName("EndTime")
    public int iEndTime;

    @SerializedName("NoticeID")
    public long iNoticeID;

    @SerializedName("NoticeType")
    public int iNoticeType;

    @SerializedName("ChannelID")
    public String strChannelID;

    @SerializedName("NoticeContent")
    public String strContent;

    @SerializedName("LinkAddress")
    public String strLinkAddr;

    @SerializedName("LinkeText")
    public String strLinkeText;
}
