package com.youme.imsdk.internal;

import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public class SpeechMessageInfo {

    @SerializedName("DownloadURL")
    private String downloadURL;

    @SerializedName("Duration")
    private Integer duration;

    @SerializedName("FileSize")
    private Integer fileSize;

    @SerializedName("LocalPath")
    private String localPath;

    @SerializedName("RequestID")
    private String requestID;

    @SerializedName("Text")
    private String text;

    public String getDownloadURL() {
        return this.downloadURL;
    }

    public void setDownloadURL(String str) {
        this.downloadURL = str;
    }

    public int getDuration() {
        return this.duration.intValue();
    }

    public void setDuration(int i) {
        this.duration = Integer.valueOf(i);
    }

    public int getFileSize() {
        return this.fileSize.intValue();
    }

    public void setFileSize(int i) {
        this.fileSize = Integer.valueOf(i);
    }

    public String getLocalPath() {
        return this.localPath;
    }

    public void setLocalPath(String str) {
        this.localPath = str;
    }

    public long getRequestID() {
        return Long.parseLong(this.requestID);
    }

    public void setRequestID(long j) {
        this.requestID = String.valueOf(j);
    }

    public String getText() {
        return this.text;
    }

    public void setText(String str) {
        this.text = str;
    }
}
