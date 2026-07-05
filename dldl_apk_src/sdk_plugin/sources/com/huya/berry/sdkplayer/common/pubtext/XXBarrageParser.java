package com.huya.berry.sdkplayer.common.pubtext;

import com.duowan.auk.ArkValue;
import com.duowan.kiwi.barrage.config.BarrageConfig;
import com.huya.berry.gamesdk.utils.ResourceUtil;
import com.sqwan.liveshow.huya.SqR;
import java.util.HashMap;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public final class XXBarrageParser {
    public static final int DEFAULT_SPEED_MODE = 0;
    public static final int FAST_DURATION = 2000;
    public static final int FAST_SPEED_MODE = 1;
    public static final int SUPER_FAST_DURATION = 900;
    private static XXBarrageParser mInstance;
    private HashMap<String, Integer> mColor;
    private HashMap<String, Integer> mDuration;
    private HashMap<String, Integer> mSpeedMode;
    private HashMap<String, Integer> mType;
    private final int CmdLength = 1;
    private final String Spliter = "|";
    private final String CmdSplit = "\\|";
    private final String CmdStart = "/";
    private final String CmdEnd = " ";

    public static XXBarrageParser getInstance() {
        if (mInstance == null) {
            mInstance = new XXBarrageParser();
        }
        return mInstance;
    }

    private XXBarrageParser() {
        initType();
        initColor();
        initDuration();
        initSpeedMode();
    }

    public int getDurationFromSpeed(int i, boolean z) {
        if (i == 1) {
            return 2000;
        }
        return z ? BarrageConfig.DEFAULT_DURATION : BarrageConfig.VERTICAL_DEFAULT_DURATION;
    }

    public int[] parseCmd(String str, int i, int i2, int i3) {
        int[] iArr = {i, i2, i3};
        String[] strArrSplit = str.split("\\|");
        if (strArrSplit == null) {
            return iArr;
        }
        int length = strArrSplit.length;
        int i4 = 0;
        while (true) {
            if (i4 >= length) {
                break;
            }
            String str2 = strArrSplit[i4];
            if (str2 != null && 1 == str2.length()) {
                Integer num = this.mType.get(str2);
                if (num != null) {
                    iArr[0] = num.intValue();
                    break;
                }
                Integer num2 = this.mColor.get(str2);
                if (num2 != null) {
                    iArr[1] = num2.intValue();
                    break;
                }
                Integer num3 = this.mDuration.get(str2);
                if (num3 != null) {
                    iArr[2] = num3.intValue();
                    break;
                }
            }
            i4++;
        }
        return iArr;
    }

    private void initType() {
        HashMap<String, Integer> map = new HashMap<>(3);
        this.mType = map;
        map.put(ArkValue.gContext.getString(ResourceUtil.getStringResIDByName(SqR.string.barrage_horizontal)), 1);
        this.mType.put(ArkValue.gContext.getString(ResourceUtil.getStringResIDByName(SqR.string.barrage_vertical)), 16);
        this.mType.put(ArkValue.gContext.getString(ResourceUtil.getStringResIDByName(SqR.string.barrage_flash)), 256);
    }

    private void initDuration() {
        HashMap<String, Integer> map = new HashMap<>(1);
        this.mDuration = map;
        map.put(ArkValue.gContext.getString(ResourceUtil.getStringResIDByName(SqR.string.barrage_fast)), 2000);
    }

    private void initSpeedMode() {
        HashMap<String, Integer> map = new HashMap<>(1);
        this.mSpeedMode = map;
        map.put(ArkValue.gContext.getString(ResourceUtil.getStringResIDByName(SqR.string.barrage_fast)), 1);
    }

    private void initColor() {
        HashMap<String, Integer> map = new HashMap<>(8);
        this.mColor = map;
        map.put(ArkValue.gContext.getString(ResourceUtil.getStringResIDByName(SqR.string.barrage_gray)), -6514540);
        this.mColor.put(ArkValue.gContext.getString(ResourceUtil.getStringResIDByName(SqR.string.barrage_orange)), -45568);
        this.mColor.put(ArkValue.gContext.getString(ResourceUtil.getStringResIDByName(SqR.string.barrage_yellow)), -468990);
        this.mColor.put(ArkValue.gContext.getString(ResourceUtil.getStringResIDByName(SqR.string.barrage_green)), -15218623);
        this.mColor.put(ArkValue.gContext.getString(ResourceUtil.getStringResIDByName(SqR.string.barrage_cyanogen)), -15868451);
        this.mColor.put(ArkValue.gContext.getString(ResourceUtil.getStringResIDByName(SqR.string.barrage_pink)), -30582);
        this.mColor.put(ArkValue.gContext.getString(ResourceUtil.getStringResIDByName(SqR.string.barrage_blue)), -15749633);
        this.mColor.put(ArkValue.gContext.getString(ResourceUtil.getStringResIDByName(SqR.string.barrage_dark_brown)), -4160191);
        this.mColor.put(ArkValue.gContext.getString(ResourceUtil.getStringResIDByName(SqR.string.barrage_brown)), -4834270);
    }
}
