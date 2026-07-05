package com.huya.berry.client.customui.model;

import com.huya.berry.gamesdk.base.BaseCallback;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class AuthorInfo extends BaseCallback {
    public String avatarUrl;
    public int fansCount;
    public boolean isLogin;
    public String liveUrl;
    public String nickname;
    public int roomId;
    public long uid;

    public AuthorInfo(String str, String str2, String str3, int i, int i2, long j, boolean z) {
        this.liveUrl = str;
        this.avatarUrl = str2;
        this.nickname = str3;
        this.roomId = i;
        this.fansCount = i2;
        this.uid = j;
        this.isLogin = z;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("[");
        stringBuffer.append(this.liveUrl);
        stringBuffer.append(",");
        stringBuffer.append(this.avatarUrl);
        stringBuffer.append(",");
        stringBuffer.append(this.nickname);
        stringBuffer.append(",");
        stringBuffer.append(this.roomId);
        stringBuffer.append(",");
        stringBuffer.append(this.uid);
        stringBuffer.append(",");
        stringBuffer.append(this.isLogin);
        stringBuffer.append(",");
        stringBuffer.append(this.fansCount);
        stringBuffer.append("]");
        return stringBuffer.toString();
    }
}
