package com.duowan.HUYA;

import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;
import java.util.Arrays;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class MobileLivingGameInfo extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public int iGameId = 0;
    public String sGameName = "";
    public String sGameShortName = "";
    public String sDesc = "";
    public int iScreenType = 0;
    public String sIconUrl = "";
    public String sSmallIconUrl = "";
    public String sMultiPacketName = "";
    public int iActionType = 0;
    public String sActionUrl = "";
    public String sCallType = "";
    public int iLoginStatus = 0;
    public int iTips = 0;
    public int iGameType = 0;
    public int iCodecType = 0;
    public String sIosUrl = "";
    public int iIsGame = 0;
    public int iIsEntertainment = 0;
    public int iGameScreenType = 0;

    public String className() {
        return "HUYA.MobileLivingGameInfo";
    }

    public String fullClassName() {
        return "com.duowan.HUYA.MobileLivingGameInfo";
    }

    public int getIGameId() {
        return this.iGameId;
    }

    public void setIGameId(int i) {
        this.iGameId = i;
    }

    public String getSGameName() {
        return this.sGameName;
    }

    public void setSGameName(String str) {
        this.sGameName = str;
    }

    public String getSGameShortName() {
        return this.sGameShortName;
    }

    public void setSGameShortName(String str) {
        this.sGameShortName = str;
    }

    public String getSDesc() {
        return this.sDesc;
    }

    public void setSDesc(String str) {
        this.sDesc = str;
    }

    public int getIScreenType() {
        return this.iScreenType;
    }

    public void setIScreenType(int i) {
        this.iScreenType = i;
    }

    public String getSIconUrl() {
        return this.sIconUrl;
    }

    public void setSIconUrl(String str) {
        this.sIconUrl = str;
    }

    public String getSSmallIconUrl() {
        return this.sSmallIconUrl;
    }

    public void setSSmallIconUrl(String str) {
        this.sSmallIconUrl = str;
    }

    public String getSMultiPacketName() {
        return this.sMultiPacketName;
    }

    public void setSMultiPacketName(String str) {
        this.sMultiPacketName = str;
    }

    public int getIActionType() {
        return this.iActionType;
    }

    public void setIActionType(int i) {
        this.iActionType = i;
    }

    public String getSActionUrl() {
        return this.sActionUrl;
    }

    public void setSActionUrl(String str) {
        this.sActionUrl = str;
    }

    public String getSCallType() {
        return this.sCallType;
    }

    public void setSCallType(String str) {
        this.sCallType = str;
    }

    public int getILoginStatus() {
        return this.iLoginStatus;
    }

    public void setILoginStatus(int i) {
        this.iLoginStatus = i;
    }

    public int getITips() {
        return this.iTips;
    }

    public void setITips(int i) {
        this.iTips = i;
    }

    public int getIGameType() {
        return this.iGameType;
    }

    public void setIGameType(int i) {
        this.iGameType = i;
    }

    public int getICodecType() {
        return this.iCodecType;
    }

    public void setICodecType(int i) {
        this.iCodecType = i;
    }

    public String getSIosUrl() {
        return this.sIosUrl;
    }

    public void setSIosUrl(String str) {
        this.sIosUrl = str;
    }

    public int getIIsGame() {
        return this.iIsGame;
    }

    public void setIIsGame(int i) {
        this.iIsGame = i;
    }

    public int getIIsEntertainment() {
        return this.iIsEntertainment;
    }

    public void setIIsEntertainment(int i) {
        this.iIsEntertainment = i;
    }

    public int getIGameScreenType() {
        return this.iGameScreenType;
    }

    public void setIGameScreenType(int i) {
        this.iGameScreenType = i;
    }

    public MobileLivingGameInfo() {
        setIGameId(0);
        setSGameName(this.sGameName);
        setSGameShortName(this.sGameShortName);
        setSDesc(this.sDesc);
        setIScreenType(this.iScreenType);
        setSIconUrl(this.sIconUrl);
        setSSmallIconUrl(this.sSmallIconUrl);
        setSMultiPacketName(this.sMultiPacketName);
        setIActionType(this.iActionType);
        setSActionUrl(this.sActionUrl);
        setSCallType(this.sCallType);
        setILoginStatus(this.iLoginStatus);
        setITips(this.iTips);
        setIGameType(this.iGameType);
        setICodecType(this.iCodecType);
        setSIosUrl(this.sIosUrl);
        setIIsGame(this.iIsGame);
        setIIsEntertainment(this.iIsEntertainment);
        setIGameScreenType(this.iGameScreenType);
    }

    public MobileLivingGameInfo(int i, String str, String str2, String str3, int i2, String str4, String str5, String str6, int i3, String str7, String str8, int i4, int i5, int i6, int i7, String str9, int i8, int i9, int i10) {
        setIGameId(i);
        setSGameName(str);
        setSGameShortName(str2);
        setSDesc(str3);
        setIScreenType(i2);
        setSIconUrl(str4);
        setSSmallIconUrl(str5);
        setSMultiPacketName(str6);
        setIActionType(i3);
        setSActionUrl(str7);
        setSCallType(str8);
        setILoginStatus(i4);
        setITips(i5);
        setIGameType(i6);
        setICodecType(i7);
        setSIosUrl(str9);
        setIIsGame(i8);
        setIIsEntertainment(i9);
        setIGameScreenType(i10);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        MobileLivingGameInfo mobileLivingGameInfo = (MobileLivingGameInfo) obj;
        return JceUtil.equals(this.iGameId, mobileLivingGameInfo.iGameId) && JceUtil.equals(this.sGameName, mobileLivingGameInfo.sGameName) && JceUtil.equals(this.sGameShortName, mobileLivingGameInfo.sGameShortName) && JceUtil.equals(this.sDesc, mobileLivingGameInfo.sDesc) && JceUtil.equals(this.iScreenType, mobileLivingGameInfo.iScreenType) && JceUtil.equals(this.sIconUrl, mobileLivingGameInfo.sIconUrl) && JceUtil.equals(this.sSmallIconUrl, mobileLivingGameInfo.sSmallIconUrl) && JceUtil.equals(this.sMultiPacketName, mobileLivingGameInfo.sMultiPacketName) && JceUtil.equals(this.iActionType, mobileLivingGameInfo.iActionType) && JceUtil.equals(this.sActionUrl, mobileLivingGameInfo.sActionUrl) && JceUtil.equals(this.sCallType, mobileLivingGameInfo.sCallType) && JceUtil.equals(this.iLoginStatus, mobileLivingGameInfo.iLoginStatus) && JceUtil.equals(this.iTips, mobileLivingGameInfo.iTips) && JceUtil.equals(this.iGameType, mobileLivingGameInfo.iGameType) && JceUtil.equals(this.iCodecType, mobileLivingGameInfo.iCodecType) && JceUtil.equals(this.sIosUrl, mobileLivingGameInfo.sIosUrl) && JceUtil.equals(this.iIsGame, mobileLivingGameInfo.iIsGame) && JceUtil.equals(this.iIsEntertainment, mobileLivingGameInfo.iIsEntertainment) && JceUtil.equals(this.iGameScreenType, mobileLivingGameInfo.iGameScreenType);
    }

    public int hashCode() {
        return Arrays.hashCode(new int[]{JceUtil.hashCode(this.iGameId), JceUtil.hashCode(this.sGameName), JceUtil.hashCode(this.sGameShortName), JceUtil.hashCode(this.sDesc), JceUtil.hashCode(this.iScreenType), JceUtil.hashCode(this.sIconUrl), JceUtil.hashCode(this.sSmallIconUrl), JceUtil.hashCode(this.sMultiPacketName), JceUtil.hashCode(this.iActionType), JceUtil.hashCode(this.sActionUrl), JceUtil.hashCode(this.sCallType), JceUtil.hashCode(this.iLoginStatus), JceUtil.hashCode(this.iTips), JceUtil.hashCode(this.iGameType), JceUtil.hashCode(this.iCodecType), JceUtil.hashCode(this.sIosUrl), JceUtil.hashCode(this.iIsGame), JceUtil.hashCode(this.iIsEntertainment), JceUtil.hashCode(this.iGameScreenType)});
    }

    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException unused) {
            return null;
        }
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void writeTo(JceOutputStream jceOutputStream) {
        jceOutputStream.write(this.iGameId, 0);
        String str = this.sGameName;
        if (str != null) {
            jceOutputStream.write(str, 1);
        }
        String str2 = this.sGameShortName;
        if (str2 != null) {
            jceOutputStream.write(str2, 2);
        }
        String str3 = this.sDesc;
        if (str3 != null) {
            jceOutputStream.write(str3, 3);
        }
        jceOutputStream.write(this.iScreenType, 4);
        String str4 = this.sIconUrl;
        if (str4 != null) {
            jceOutputStream.write(str4, 5);
        }
        String str5 = this.sSmallIconUrl;
        if (str5 != null) {
            jceOutputStream.write(str5, 6);
        }
        String str6 = this.sMultiPacketName;
        if (str6 != null) {
            jceOutputStream.write(str6, 7);
        }
        jceOutputStream.write(this.iActionType, 8);
        String str7 = this.sActionUrl;
        if (str7 != null) {
            jceOutputStream.write(str7, 9);
        }
        String str8 = this.sCallType;
        if (str8 != null) {
            jceOutputStream.write(str8, 10);
        }
        jceOutputStream.write(this.iLoginStatus, 11);
        jceOutputStream.write(this.iTips, 12);
        jceOutputStream.write(this.iGameType, 13);
        jceOutputStream.write(this.iCodecType, 14);
        String str9 = this.sIosUrl;
        if (str9 != null) {
            jceOutputStream.write(str9, 15);
        }
        jceOutputStream.write(this.iIsGame, 16);
        jceOutputStream.write(this.iIsEntertainment, 17);
        jceOutputStream.write(this.iGameScreenType, 18);
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        setIGameId(jceInputStream.read(this.iGameId, 0, false));
        setSGameName(jceInputStream.readString(1, false));
        setSGameShortName(jceInputStream.readString(2, false));
        setSDesc(jceInputStream.readString(3, false));
        setIScreenType(jceInputStream.read(this.iScreenType, 4, false));
        setSIconUrl(jceInputStream.readString(5, false));
        setSSmallIconUrl(jceInputStream.readString(6, false));
        setSMultiPacketName(jceInputStream.readString(7, false));
        setIActionType(jceInputStream.read(this.iActionType, 8, false));
        setSActionUrl(jceInputStream.readString(9, false));
        setSCallType(jceInputStream.readString(10, false));
        setILoginStatus(jceInputStream.read(this.iLoginStatus, 11, false));
        setITips(jceInputStream.read(this.iTips, 12, false));
        setIGameType(jceInputStream.read(this.iGameType, 13, false));
        setICodecType(jceInputStream.read(this.iCodecType, 14, false));
        setSIosUrl(jceInputStream.readString(15, false));
        setIIsGame(jceInputStream.read(this.iIsGame, 16, false));
        setIIsEntertainment(jceInputStream.read(this.iIsEntertainment, 17, false));
        setIGameScreenType(jceInputStream.read(this.iGameScreenType, 18, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display(this.iGameId, "iGameId");
        jceDisplayer.display(this.sGameName, "sGameName");
        jceDisplayer.display(this.sGameShortName, "sGameShortName");
        jceDisplayer.display(this.sDesc, "sDesc");
        jceDisplayer.display(this.iScreenType, "iScreenType");
        jceDisplayer.display(this.sIconUrl, "sIconUrl");
        jceDisplayer.display(this.sSmallIconUrl, "sSmallIconUrl");
        jceDisplayer.display(this.sMultiPacketName, "sMultiPacketName");
        jceDisplayer.display(this.iActionType, "iActionType");
        jceDisplayer.display(this.sActionUrl, "sActionUrl");
        jceDisplayer.display(this.sCallType, "sCallType");
        jceDisplayer.display(this.iLoginStatus, "iLoginStatus");
        jceDisplayer.display(this.iTips, "iTips");
        jceDisplayer.display(this.iGameType, "iGameType");
        jceDisplayer.display(this.iCodecType, "iCodecType");
        jceDisplayer.display(this.sIosUrl, "sIosUrl");
        jceDisplayer.display(this.iIsGame, "iIsGame");
        jceDisplayer.display(this.iIsEntertainment, "iIsEntertainment");
        jceDisplayer.display(this.iGameScreenType, "iGameScreenType");
    }
}
