package com.youme.imsdk;

import com.google.gson.annotations.SerializedName;
import com.sqwan.common.track.SqTrackNetDnsKey;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public class YIMExtraGifParam {

    @SerializedName(SqTrackNetDnsKey.extra)
    private String extra;

    @SerializedName("level")
    private String level;

    @SerializedName("location")
    private String location;

    @SerializedName("nickname")
    private String nickName;

    @SerializedName("score")
    private String score;

    @SerializedName("server_area")
    private String serverArea;

    @SerializedName("vip_level")
    private String vipLevel;

    public YIMExtraGifParam() {
    }

    public YIMExtraGifParam(String str, String str2, String str3, Long l, Integer num, Integer num2, String str4) {
        this.nickName = str;
        this.serverArea = str2;
        this.location = str3;
        this.score = l.toString();
        this.level = num.toString();
        this.vipLevel = num2.toString();
        this.extra = str4;
    }

    public String getNickName() {
        return this.nickName;
    }

    public String getServerArea() {
        return this.serverArea;
    }

    public String getLocation() {
        return this.location;
    }

    public Long getScore() {
        return Long.getLong(this.score, 0L);
    }

    public Integer getLevel() {
        return Integer.getInteger(this.level, 0);
    }

    public Integer getVipLevel() {
        return Integer.getInteger(this.vipLevel, 0);
    }

    public String getExtra() {
        return this.extra;
    }
}
