package com.youme.imsdk;

import android.util.Base64;
import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public class YIMHistoryMessageBody {

    @SerializedName("ChatType")
    private Integer chatType;

    @SerializedName("CreateTime")
    private int createTime;

    @SerializedName("Duration")
    private int duration;

    @SerializedName("FileExtension")
    private String fileExtension;

    @SerializedName("FileName")
    private String fileName;

    @SerializedName("FileSize")
    private int fileSize;

    @SerializedName("FileType")
    private int fileType;

    @SerializedName("IsPlayed")
    private int isPlayed;

    @SerializedName("IsRead")
    private int isRead;

    @SerializedName("LocalPath")
    private String localPath;

    @SerializedName("Serial")
    private Long messageID;

    @SerializedName("MessageType")
    private Integer messageType;

    @SerializedName("Param")
    private String param;

    @SerializedName("ReceiveID")
    private String receiveID;

    @SerializedName("SenderID")
    private String senderID;

    @SerializedName("Content")
    private String text;

    @SerializedName("Text")
    private String voiceToText;

    public int getFileType() {
        return this.fileType;
    }

    public void setFileType(int i) {
        this.fileType = i;
    }

    public String getFileName() {
        return this.fileName;
    }

    public void setFileName(String str) {
        this.fileName = str;
    }

    public String getFileExtension() {
        return this.fileExtension;
    }

    public void setFileExtension(String str) {
        this.fileExtension = str;
    }

    public int getFileSize() {
        return this.fileSize;
    }

    public void setFileSize(int i) {
        this.fileSize = i;
    }

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

    public boolean getIsPlayed() {
        return this.isPlayed == 1;
    }

    public void setPlayed(boolean z) {
        if (z) {
            this.isPlayed = 1;
        } else {
            this.isPlayed = 0;
        }
    }

    public int getChatType() {
        return this.chatType.intValue();
    }

    public void setChatType(int i) {
        this.chatType = Integer.valueOf(i);
    }

    public int getMessageType() {
        return this.messageType.intValue();
    }

    public void setMessageType(int i) {
        this.messageType = Integer.valueOf(i);
    }

    public String getParam() {
        return this.param;
    }

    public void setParam(String str) {
        this.param = str;
    }

    public String getReceiveID() {
        return this.receiveID;
    }

    public void setReceiveID(String str) {
        this.receiveID = str;
    }

    public String getSenderID() {
        return this.senderID;
    }

    public void setSenderID(String str) {
        this.senderID = str;
    }

    public long getMessageID() {
        return this.messageID.longValue();
    }

    public void setMessageID(long j) {
        this.messageID = Long.valueOf(j);
    }

    public String getText() {
        if (this.messageType.intValue() == 5) {
            return this.voiceToText;
        }
        return this.text;
    }

    public byte[] getCustomMesssageContent() {
        if (this.messageType.intValue() == 2) {
            return Base64.decode(this.text, 0);
        }
        return null;
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

    public int getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(int i) {
        this.createTime = i;
    }

    public int getDuration() {
        return this.duration;
    }

    public void setDuration(int i) {
        this.duration = i;
    }

    public String getVoiceToText() {
        return this.voiceToText;
    }

    public void setVoiceToText(String str) {
        this.voiceToText = str;
    }
}
