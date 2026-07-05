package com.youme.imsdk.internal;

import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public class RecvMessage {

    @SerializedName("Anchor")
    public String anchor;

    @SerializedName("AttachParam")
    public String attachParam;

    @SerializedName("ChatType")
    public Integer chatType;

    @SerializedName("Content")
    public String content;

    @SerializedName("CreateTime")
    public Integer createTime;

    @SerializedName("Distance")
    public Integer distance;

    @SerializedName("Duration")
    public Integer duration;

    @SerializedName("ExtraParam")
    public String extraParam;

    @SerializedName("Text")
    public String extraText;

    @SerializedName("FileExtension")
    public String fileExtension;

    @SerializedName("FileName")
    public String fileName;

    @SerializedName("FileSize")
    public Integer fileSize;

    @SerializedName("FileType")
    public Integer fileType;

    @SerializedName("GiftCount")
    public Integer giftCount;

    @SerializedName("GiftID")
    public Integer giftID;

    @SerializedName("IsRead")
    private int isRead;

    @SerializedName("MessageType")
    public Integer msgType;

    @SerializedName("Param")
    public String param;

    @SerializedName("ReceiveID")
    public String receiveId;

    @SerializedName("SenderID")
    public String senderId;

    @SerializedName("Serial")
    public Long serial;

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
}
