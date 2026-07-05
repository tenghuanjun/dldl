package com.duowan.HUYA;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class ELiveCompatibleFlag {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final int _ELiveCompatibleFlag_180 = 1024;
    public static final int _ELiveCompatibleFlag_360 = 1;
    public static final int _ELiveCompatibleFlag_3D = 2;
    public static final int _ELiveCompatibleFlag_3D_TOPBOTTOM = 4;
    public static final int _ELiveCompatibleFlag_BEGINTIMEBACK = 16;
    public static final int _ELiveCompatibleFlag_EXTRASTREAM_180 = 64;
    public static final int _ELiveCompatibleFlag_EXTRASTREAM_360 = 128;
    public static final int _ELiveCompatibleFlag_EXTRASTREAM_3D = 256;
    public static final int _ELiveCompatibleFlag_EXTRASTREAM_3D_TOPBOTTOM = 512;
    public static final int _ELiveCompatibleFlag_FLAC_SUPPORT = 8;
    public static final int _ELiveCompatibleFlag_HUAWEI_DASH_FOV_MODE = 2048;
    public static final int _ELiveCompatibleFlag_WORLD_PERSPECTIVE = 32;
    private String __T;
    private int __value;
    private static ELiveCompatibleFlag[] __values = new ELiveCompatibleFlag[12];
    public static final ELiveCompatibleFlag ELiveCompatibleFlag_360 = new ELiveCompatibleFlag(0, 1, "ELiveCompatibleFlag_360");
    public static final ELiveCompatibleFlag ELiveCompatibleFlag_3D = new ELiveCompatibleFlag(1, 2, "ELiveCompatibleFlag_3D");
    public static final ELiveCompatibleFlag ELiveCompatibleFlag_3D_TOPBOTTOM = new ELiveCompatibleFlag(2, 4, "ELiveCompatibleFlag_3D_TOPBOTTOM");
    public static final ELiveCompatibleFlag ELiveCompatibleFlag_FLAC_SUPPORT = new ELiveCompatibleFlag(3, 8, "ELiveCompatibleFlag_FLAC_SUPPORT");
    public static final ELiveCompatibleFlag ELiveCompatibleFlag_BEGINTIMEBACK = new ELiveCompatibleFlag(4, 16, "ELiveCompatibleFlag_BEGINTIMEBACK");
    public static final ELiveCompatibleFlag ELiveCompatibleFlag_WORLD_PERSPECTIVE = new ELiveCompatibleFlag(5, 32, "ELiveCompatibleFlag_WORLD_PERSPECTIVE");
    public static final ELiveCompatibleFlag ELiveCompatibleFlag_EXTRASTREAM_180 = new ELiveCompatibleFlag(6, 64, "ELiveCompatibleFlag_EXTRASTREAM_180");
    public static final ELiveCompatibleFlag ELiveCompatibleFlag_EXTRASTREAM_360 = new ELiveCompatibleFlag(7, 128, "ELiveCompatibleFlag_EXTRASTREAM_360");
    public static final ELiveCompatibleFlag ELiveCompatibleFlag_EXTRASTREAM_3D = new ELiveCompatibleFlag(8, 256, "ELiveCompatibleFlag_EXTRASTREAM_3D");
    public static final ELiveCompatibleFlag ELiveCompatibleFlag_EXTRASTREAM_3D_TOPBOTTOM = new ELiveCompatibleFlag(9, 512, "ELiveCompatibleFlag_EXTRASTREAM_3D_TOPBOTTOM");
    public static final ELiveCompatibleFlag ELiveCompatibleFlag_180 = new ELiveCompatibleFlag(10, 1024, "ELiveCompatibleFlag_180");
    public static final ELiveCompatibleFlag ELiveCompatibleFlag_HUAWEI_DASH_FOV_MODE = new ELiveCompatibleFlag(11, 2048, "ELiveCompatibleFlag_HUAWEI_DASH_FOV_MODE");

    public static ELiveCompatibleFlag convert(int i) {
        int i2 = 0;
        while (true) {
            ELiveCompatibleFlag[] eLiveCompatibleFlagArr = __values;
            if (i2 >= eLiveCompatibleFlagArr.length) {
                return null;
            }
            if (eLiveCompatibleFlagArr[i2].value() == i) {
                return __values[i2];
            }
            i2++;
        }
    }

    public static ELiveCompatibleFlag convert(String str) {
        int i = 0;
        while (true) {
            ELiveCompatibleFlag[] eLiveCompatibleFlagArr = __values;
            if (i >= eLiveCompatibleFlagArr.length) {
                return null;
            }
            if (eLiveCompatibleFlagArr[i].toString().equals(str)) {
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

    private ELiveCompatibleFlag(int i, int i2, String str) {
        this.__T = new String();
        this.__T = str;
        this.__value = i2;
        __values[i] = this;
    }
}
