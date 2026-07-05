package com.youme.imsdk;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public class YIMHistoryMessage {

    @SerializedName("messageList")
    public List<YIMHistoryMessageBody> messageList;

    @SerializedName("Remain")
    public int remain;

    @SerializedName("TargetID")
    public String targetID;
}
