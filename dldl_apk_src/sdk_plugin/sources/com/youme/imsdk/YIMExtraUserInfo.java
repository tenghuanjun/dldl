package com.youme.imsdk;

import com.google.gson.annotations.SerializedName;
import com.sqwan.common.track.SqTrackNetDnsKey;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public class YIMExtraUserInfo {

    @SerializedName(SqTrackNetDnsKey.extra)
    private String extra;

    @SerializedName("level")
    private String level;

    @SerializedName("location")
    private String location;

    @SerializedName("location_id")
    private String locationID;

    @SerializedName("nickname")
    private String nickName;

    @SerializedName("platform")
    private String platform;

    @SerializedName("platform_id")
    private String platformID;

    @SerializedName("server_area")
    private String serverArea;

    @SerializedName("server_area_id")
    private String serverAreaID;
    private String userID;

    @SerializedName("vip_level")
    private String vipLevel;

    public YIMExtraUserInfo() {
    }

    public YIMExtraUserInfo(String str, String str2, String str3, String str4, int i, int i2, String str5, String str6, String str7, String str8) {
        this.nickName = str;
        this.serverArea = str2;
        this.location = str3;
        this.platform = str4;
        this.level = Integer.toString(i);
        this.vipLevel = Integer.toString(i2);
        this.extra = str8;
        this.serverAreaID = str5;
        this.platformID = str6;
        this.locationID = str7;
    }

    public String getNickName() {
        return this.nickName;
    }

    public String getServerArea() {
        return this.serverArea;
    }

    public String getServerAreaID() {
        return this.serverAreaID;
    }

    public String getLocation() {
        return this.location;
    }

    public int getLevel() {
        return Integer.getInteger(this.level, 0).intValue();
    }

    public int getVipLevel() {
        return Integer.getInteger(this.vipLevel, 0).intValue();
    }

    public String getExtra() {
        return this.extra;
    }

    public String getPlatformID() {
        return this.platformID;
    }

    public String getPlatform() {
        return this.platform;
    }

    public String getLocationID() {
        return this.locationID;
    }

    public String getUserID() {
        return this.userID;
    }

    public void setUserID(String str) {
        this.userID = str;
    }
}
