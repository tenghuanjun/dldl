package com.huya.berry.client.customui.model;

import com.huya.berry.gamesdk.base.BaseCallback;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class LiveListInfo extends BaseCallback {
    public String audienceCount;
    public long channelId;
    public int gameId;
    public long subId;
    public long uid;
    public String coverUrl = "";
    public String title = "";
    public String nickName = "";
    public String avatar = "";

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("[");
        stringBuffer.append(this.title);
        stringBuffer.append(",");
        stringBuffer.append(this.nickName);
        stringBuffer.append(",");
        stringBuffer.append(this.coverUrl);
        stringBuffer.append(",");
        stringBuffer.append(this.gameId);
        stringBuffer.append(",");
        stringBuffer.append(this.audienceCount);
        stringBuffer.append(",");
        stringBuffer.append(this.channelId);
        stringBuffer.append(",");
        stringBuffer.append(this.subId);
        stringBuffer.append(",");
        stringBuffer.append(this.avatar);
        stringBuffer.append(",");
        stringBuffer.append(this.uid);
        stringBuffer.append("]");
        return stringBuffer.toString();
    }
}
