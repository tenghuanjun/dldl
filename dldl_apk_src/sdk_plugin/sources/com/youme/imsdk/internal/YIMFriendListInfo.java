package com.youme.imsdk.internal;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public class YIMFriendListInfo {

    @SerializedName("StartIndex")
    public int startIndex;

    @SerializedName("Type")
    public int type;

    @SerializedName("UserList")
    public ArrayList<YIMUserBriefInfo> userList;
}
