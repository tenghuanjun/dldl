package com.youme.imsdk.internal;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public class RoomHistoryMessage {

    @SerializedName("MessageList")
    public ArrayList<RecvMessage> messageList;

    @SerializedName("Remain")
    public Integer remain;

    @SerializedName("RoomID")
    public String roomID;
}
