package com.huya.component.user.api.data;

import com.duowan.auk.NoProguard;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class UserInfo implements NoProguard {
    public UserInfo attachInfo;
    public Object extra;
    public Gender gender;
    public String huyaId;
    public String nickname;
    public int nobleLevel;
    public String portrait;
    public String signature;
    public int superFansType;
    public long uid;
    public int userLevel;
    public long yy;

    public enum Gender implements NoProguard {
        Male,
        Female
    }

    public UserInfo() {
    }

    public UserInfo(long j, String str, String str2) {
        this.uid = j;
        this.portrait = str;
        this.nickname = str2;
    }

    public UserInfo(UserInfo userInfo) {
        this.yy = userInfo.yy;
        this.uid = userInfo.uid;
        this.portrait = userInfo.portrait;
        this.nickname = userInfo.nickname;
        this.signature = userInfo.signature;
        this.gender = userInfo.gender;
        this.extra = userInfo.extra;
        this.attachInfo = userInfo.attachInfo;
        this.nobleLevel = userInfo.nobleLevel;
        this.userLevel = userInfo.userLevel;
        this.huyaId = userInfo.huyaId;
        this.superFansType = userInfo.superFansType;
    }

    public void setExtra(Object obj) {
        this.extra = obj;
    }

    public String toString() {
        return "UserInfo{yy=" + this.yy + ", uid=" + this.uid + ", portrait='" + this.portrait + "', nickname='" + this.nickname + "', signature='" + this.signature + "', gender=" + this.gender + ", nobleLevel=" + this.nobleLevel + ", userLevel=" + this.userLevel + ", extra=" + this.extra + ", attachInfo=" + this.attachInfo + ", huyaId=" + this.huyaId + ", superFansType= " + this.superFansType + AbstractJsonLexerKt.END_OBJ;
    }
}
