package com.duowan.HUYA;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class ELiveSourceType {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final int _ELiveSourceType_GameLive = 0;
    public static final int _ELiveSourceType_HUYAOwnClient = 9;
    public static final int _ELiveSourceType_HuyaVideo = 3;
    public static final int _ELiveSourceType_MMS = 1;
    public static final int _ELiveSourceType_MatchLive = 12;
    public static final int _ELiveSourceType_MobileDirector = 7;
    public static final int _ELiveSourceType_MobileLive = 6;
    public static final int _ELiveSourceType_MobileStarShow = 11;
    public static final int _ELiveSourceType_MutilAudio = 10;
    public static final int _ELiveSourceType_ShangJing = 2;
    public static final int _ELiveSourceType_ShangJingRecord = 4;
    public static final int _ELiveSourceType_WSRtmp = 8;
    public static final int _ELiveSourceType_YYLive = 5;
    private String __T;
    private int __value;
    private static ELiveSourceType[] __values = new ELiveSourceType[13];
    public static final ELiveSourceType ELiveSourceType_GameLive = new ELiveSourceType(0, 0, "ELiveSourceType_GameLive");
    public static final ELiveSourceType ELiveSourceType_MMS = new ELiveSourceType(1, 1, "ELiveSourceType_MMS");
    public static final ELiveSourceType ELiveSourceType_ShangJing = new ELiveSourceType(2, 2, "ELiveSourceType_ShangJing");
    public static final ELiveSourceType ELiveSourceType_HuyaVideo = new ELiveSourceType(3, 3, "ELiveSourceType_HuyaVideo");
    public static final ELiveSourceType ELiveSourceType_ShangJingRecord = new ELiveSourceType(4, 4, "ELiveSourceType_ShangJingRecord");
    public static final ELiveSourceType ELiveSourceType_YYLive = new ELiveSourceType(5, 5, "ELiveSourceType_YYLive");
    public static final ELiveSourceType ELiveSourceType_MobileLive = new ELiveSourceType(6, 6, "ELiveSourceType_MobileLive");
    public static final ELiveSourceType ELiveSourceType_MobileDirector = new ELiveSourceType(7, 7, "ELiveSourceType_MobileDirector");
    public static final ELiveSourceType ELiveSourceType_WSRtmp = new ELiveSourceType(8, 8, "ELiveSourceType_WSRtmp");
    public static final ELiveSourceType ELiveSourceType_HUYAOwnClient = new ELiveSourceType(9, 9, "ELiveSourceType_HUYAOwnClient");
    public static final ELiveSourceType ELiveSourceType_MutilAudio = new ELiveSourceType(10, 10, "ELiveSourceType_MutilAudio");
    public static final ELiveSourceType ELiveSourceType_MobileStarShow = new ELiveSourceType(11, 11, "ELiveSourceType_MobileStarShow");
    public static final ELiveSourceType ELiveSourceType_MatchLive = new ELiveSourceType(12, 12, "ELiveSourceType_MatchLive");

    public static ELiveSourceType convert(int i) {
        int i2 = 0;
        while (true) {
            ELiveSourceType[] eLiveSourceTypeArr = __values;
            if (i2 >= eLiveSourceTypeArr.length) {
                return null;
            }
            if (eLiveSourceTypeArr[i2].value() == i) {
                return __values[i2];
            }
            i2++;
        }
    }

    public static ELiveSourceType convert(String str) {
        int i = 0;
        while (true) {
            ELiveSourceType[] eLiveSourceTypeArr = __values;
            if (i >= eLiveSourceTypeArr.length) {
                return null;
            }
            if (eLiveSourceTypeArr[i].toString().equals(str)) {
                return __values[i];
            }
            i++;
        }
    }

    public int value() {
        return this.__value;
    }

    public String toString() {
        return this.__T;
    }

    private ELiveSourceType(int i, int i2, String str) {
        this.__T = new String();
        this.__T = str;
        this.__value = i2;
        __values[i] = this;
    }
}
