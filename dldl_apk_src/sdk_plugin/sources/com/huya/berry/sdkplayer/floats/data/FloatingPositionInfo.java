package com.huya.berry.sdkplayer.floats.data;

import com.duowan.HUYA.ScreenType;
import com.duowan.auk.util.L;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class FloatingPositionInfo {
    private final int MEMBER_NUM;
    private final String TAG;
    private int currentType;
    private int height;
    private int isBigger;
    private int mDirection;
    private ScreenType mScreenType;
    private int width;
    private int x;
    private int y;

    public FloatingPositionInfo() {
        this.TAG = "FloatingPositionInfo";
        this.MEMBER_NUM = 8;
        this.mDirection = -1;
        this.isBigger = 1;
        this.currentType = 0;
    }

    public FloatingPositionInfo(ScreenType screenType, int i, int i2, int i3, int i4, int i5, boolean z, int i6) {
        this.TAG = "FloatingPositionInfo";
        this.MEMBER_NUM = 8;
        this.mDirection = -1;
        this.isBigger = 1;
        this.currentType = 0;
        this.mScreenType = screenType;
        this.mDirection = i;
        this.x = i2;
        this.y = i3;
        this.width = i4;
        this.height = i5;
        this.isBigger = z ? 1 : 0;
        this.currentType = i6;
    }

    public int getCurrentType() {
        return this.currentType;
    }

    public ScreenType getScreenType() {
        return this.mScreenType;
    }

    public int getDirection() {
        return this.mDirection;
    }

    public boolean isNextBigger() {
        return this.isBigger == 1;
    }

    public int getX() {
        return this.x;
    }

    public void setX(int i) {
        this.x = i;
    }

    public int getY() {
        return this.y;
    }

    public void setY(int i) {
        this.y = i;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public String saveToString() {
        return this.mScreenType.value() + "|" + this.x + "|" + this.y + "|" + this.width + "|" + this.height + "|" + this.isBigger + "|" + this.currentType + "|" + this.mDirection;
    }

    public void parseFromString(String str) {
        String[] strArrSplit = str.split("\\|");
        if (strArrSplit.length == 8) {
            try {
                this.mScreenType = ScreenType.convert(Integer.valueOf(strArrSplit[0]).intValue());
                this.x = Integer.valueOf(strArrSplit[1]).intValue();
                this.y = Integer.valueOf(strArrSplit[2]).intValue();
                this.width = Integer.valueOf(strArrSplit[3]).intValue();
                this.height = Integer.valueOf(strArrSplit[4]).intValue();
                this.isBigger = Integer.valueOf(strArrSplit[5]).intValue();
                this.currentType = Integer.valueOf(strArrSplit[6]).intValue();
                this.mDirection = Integer.valueOf(strArrSplit[7]).intValue();
            } catch (Exception unused) {
                L.debug("infoString: " + str);
            }
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("FloatingPositionInfo{mLiveRoomTypeValue=");
        ScreenType screenType = this.mScreenType;
        sb.append(screenType == null ? -1 : screenType.value());
        sb.append("mDirection=");
        sb.append(this.mDirection);
        sb.append(", x=");
        sb.append(this.x);
        sb.append(", y=");
        sb.append(this.y);
        sb.append(", width=");
        sb.append(this.width);
        sb.append(", height=");
        sb.append(this.height);
        sb.append(", isBigger=");
        sb.append(this.isBigger);
        sb.append(", currentType=");
        sb.append(this.currentType);
        sb.append(AbstractJsonLexerKt.END_OBJ);
        return sb.toString();
    }
}
