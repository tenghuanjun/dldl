package com.huya.component.user.api.data;

import com.duowan.HUYA.PresenterLevelNotice;
import com.duowan.HUYA.PresenterLevelProgressRsp;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class PresenterLevel {
    public boolean bLevelMax;
    public boolean bLightUp;
    public int iLevel;
    public int iRank;
    public long lCurrLevelExp;
    public long lExp;
    public long lNext2LevelExp;
    public long lNextLevelExp;
    public long lWeeklyExp;
    public long lWeeklyIncExp;

    public PresenterLevel(PresenterLevelProgressRsp presenterLevelProgressRsp) {
        if (presenterLevelProgressRsp == null || presenterLevelProgressRsp.tLevelBase == null) {
            return;
        }
        this.iLevel = presenterLevelProgressRsp.tLevelBase.iLevel;
        this.lExp = presenterLevelProgressRsp.tLevelBase.lExp;
        this.bLightUp = presenterLevelProgressRsp.iLightUp == 1;
        this.bLevelMax = presenterLevelProgressRsp.iLevelMax == 1;
        this.lCurrLevelExp = presenterLevelProgressRsp.lCurrLevelExp;
        this.lNextLevelExp = presenterLevelProgressRsp.lNextLevelExp;
        this.lNext2LevelExp = presenterLevelProgressRsp.lNext2LevelExp;
        if (presenterLevelProgressRsp.tGrowInfo != null) {
            this.lWeeklyExp = presenterLevelProgressRsp.tGrowInfo.lWeeklyExp;
            this.lWeeklyIncExp = presenterLevelProgressRsp.tGrowInfo.lWeeklyIncExp;
            this.iRank = presenterLevelProgressRsp.tGrowInfo.iRank;
        }
    }

    public PresenterLevel(PresenterLevelNotice presenterLevelNotice) {
        if (presenterLevelNotice == null || presenterLevelNotice.tLevelBase == null) {
            return;
        }
        this.iLevel = presenterLevelNotice.tLevelBase.iLevel;
        this.lExp = presenterLevelNotice.tLevelBase.lExp;
        this.bLightUp = presenterLevelNotice.iLightUp == 1;
        this.bLevelMax = presenterLevelNotice.iLevelMax == 1;
        this.lCurrLevelExp = presenterLevelNotice.lCurrLevelExp;
        this.lNextLevelExp = presenterLevelNotice.lNextLevelExp;
        this.lNext2LevelExp = presenterLevelNotice.lNext2LevelExp;
        if (presenterLevelNotice.tGrowInfo != null) {
            this.lWeeklyExp = presenterLevelNotice.tGrowInfo.lWeeklyExp;
            this.lWeeklyIncExp = presenterLevelNotice.tGrowInfo.lWeeklyIncExp;
            this.iRank = presenterLevelNotice.tGrowInfo.iRank;
        }
    }

    public String toString() {
        return "PresenterLevel{iLevel=" + this.iLevel + ", lExp=" + this.lExp + ", bLightUp=" + this.bLightUp + ", bLevelMax=" + this.bLevelMax + ", lCurrLevelExp=" + this.lCurrLevelExp + ", lNextLevelExp=" + this.lNextLevelExp + ", lNext2LevelExp=" + this.lNext2LevelExp + ", lWeeklyExp=" + this.lWeeklyExp + ", lWeeklyIncExp=" + this.lWeeklyIncExp + ", iRank=" + this.iRank + AbstractJsonLexerKt.END_OBJ;
    }
}
