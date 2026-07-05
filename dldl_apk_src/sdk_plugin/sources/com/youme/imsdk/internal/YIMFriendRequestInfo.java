package com.youme.imsdk.internal;

import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public class YIMFriendRequestInfo {

    @SerializedName("Status")
    public int addStatus;

    @SerializedName("AskerID")
    public String askerID;

    @SerializedName("AskerNickname")
    public String askerNickname;

    @SerializedName("CreateTime")
    public int createTime;

    @SerializedName("InviteeID")
    public String inviteeID;

    @SerializedName("InviteeNickname")
    public String inviteeNickname;

    @SerializedName("ValidateInfo")
    public String validateInfo;
}
