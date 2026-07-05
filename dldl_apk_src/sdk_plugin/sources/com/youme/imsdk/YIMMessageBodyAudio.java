package com.youme.imsdk;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public class YIMMessageBodyAudio implements IYIMMessageBodyBase {
    private String localPath;
    private String mExtraText = null;
    private String mParam = null;
    private int mAudioTime = 0;

    public String getText() {
        return this.mExtraText;
    }

    public String getParam() {
        return this.mParam;
    }

    public int getAudioTime() {
        return this.mAudioTime;
    }

    public void setText(String str) {
        this.mExtraText = str;
    }

    public void setParam(String str) {
        this.mParam = str;
    }

    public void setAudioTime(int i) {
        this.mAudioTime = i;
    }

    public void setLocalPath(String str) {
        this.localPath = str;
    }

    public String getLocalPath() {
        return this.localPath;
    }
}
