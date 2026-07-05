package com.duowan.HUYA;

import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class ActiveEventInfo extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public int iID = 0;
    public String sTitle = "";
    public int iActBeginTime = 0;
    public String sPicUrl = "";
    public String sGameName = "";
    public int iGameID = 0;
    public String sDigest = "";
    public int iActButtionState = 0;
    public int iActiveState = 0;
    public String sActiveState = "";
    public String sLinkUrl = "";
    public String sDetailUrl = "";
    public int iActEndTime = 0;
    public long lTid = 0;
    public long lSubid = 0;
    public long lPUid = 0;
    public int iSourceType = 0;
    public long iSubCnt = 0;
    public int iScreenType = 0;
    public boolean bDetailUrlNeedLogin = true;
    public boolean bLinkUrlNeedLogin = true;
    public String sAppIcon = "";
    public String sDes = "";
    public String sPrize_id = "";

    public String className() {
        return "HUYA.ActiveEventInfo";
    }

    public String fullClassName() {
        return "com.duowan.HUYA.ActiveEventInfo";
    }

    public int getIID() {
        return this.iID;
    }

    public void setIID(int i) {
        this.iID = i;
    }

    public String getSTitle() {
        return this.sTitle;
    }

    public void setSTitle(String str) {
        this.sTitle = str;
    }

    public int getIActBeginTime() {
        return this.iActBeginTime;
    }

    public void setIActBeginTime(int i) {
        this.iActBeginTime = i;
    }

    public String getSPicUrl() {
        return this.sPicUrl;
    }

    public void setSPicUrl(String str) {
        this.sPicUrl = str;
    }

    public String getSGameName() {
        return this.sGameName;
    }

    public void setSGameName(String str) {
        this.sGameName = str;
    }

    public int getIGameID() {
        return this.iGameID;
    }

    public void setIGameID(int i) {
        this.iGameID = i;
    }

    public String getSDigest() {
        return this.sDigest;
    }

    public void setSDigest(String str) {
        this.sDigest = str;
    }

    public int getIActButtionState() {
        return this.iActButtionState;
    }

    public void setIActButtionState(int i) {
        this.iActButtionState = i;
    }

    public int getIActiveState() {
        return this.iActiveState;
    }

    public void setIActiveState(int i) {
        this.iActiveState = i;
    }

    public String getSActiveState() {
        return this.sActiveState;
    }

    public void setSActiveState(String str) {
        this.sActiveState = str;
    }

    public String getSLinkUrl() {
        return this.sLinkUrl;
    }

    public void setSLinkUrl(String str) {
        this.sLinkUrl = str;
    }

    public String getSDetailUrl() {
        return this.sDetailUrl;
    }

    public void setSDetailUrl(String str) {
        this.sDetailUrl = str;
    }

    public int getIActEndTime() {
        return this.iActEndTime;
    }

    public void setIActEndTime(int i) {
        this.iActEndTime = i;
    }

    public long getLTid() {
        return this.lTid;
    }

    public void setLTid(long j) {
        this.lTid = j;
    }

    public long getLSubid() {
        return this.lSubid;
    }

    public void setLSubid(long j) {
        this.lSubid = j;
    }

    public long getLPUid() {
        return this.lPUid;
    }

    public void setLPUid(long j) {
        this.lPUid = j;
    }

    public int getISourceType() {
        return this.iSourceType;
    }

    public void setISourceType(int i) {
        this.iSourceType = i;
    }

    public long getISubCnt() {
        return this.iSubCnt;
    }

    public void setISubCnt(long j) {
        this.iSubCnt = j;
    }

    public int getIScreenType() {
        return this.iScreenType;
    }

    public void setIScreenType(int i) {
        this.iScreenType = i;
    }

    public boolean getBDetailUrlNeedLogin() {
        return this.bDetailUrlNeedLogin;
    }

    public void setBDetailUrlNeedLogin(boolean z) {
        this.bDetailUrlNeedLogin = z;
    }

    public boolean getBLinkUrlNeedLogin() {
        return this.bLinkUrlNeedLogin;
    }

    public void setBLinkUrlNeedLogin(boolean z) {
        this.bLinkUrlNeedLogin = z;
    }

    public String getSAppIcon() {
        return this.sAppIcon;
    }

    public void setSAppIcon(String str) {
        this.sAppIcon = str;
    }

    public String getSDes() {
        return this.sDes;
    }

    public void setSDes(String str) {
        this.sDes = str;
    }

    public String getSPrize_id() {
        return this.sPrize_id;
    }

    public void setSPrize_id(String str) {
        this.sPrize_id = str;
    }

    public ActiveEventInfo() {
        setIID(0);
        setSTitle(this.sTitle);
        setIActBeginTime(this.iActBeginTime);
        setSPicUrl(this.sPicUrl);
        setSGameName(this.sGameName);
        setIGameID(this.iGameID);
        setSDigest(this.sDigest);
        setIActButtionState(this.iActButtionState);
        setIActiveState(this.iActiveState);
        setSActiveState(this.sActiveState);
        setSLinkUrl(this.sLinkUrl);
        setSDetailUrl(this.sDetailUrl);
        setIActEndTime(this.iActEndTime);
        setLTid(this.lTid);
        setLSubid(this.lSubid);
        setLPUid(this.lPUid);
        setISourceType(this.iSourceType);
        setISubCnt(this.iSubCnt);
        setIScreenType(this.iScreenType);
        setBDetailUrlNeedLogin(this.bDetailUrlNeedLogin);
        setBLinkUrlNeedLogin(this.bLinkUrlNeedLogin);
        setSAppIcon(this.sAppIcon);
        setSDes(this.sDes);
        setSPrize_id(this.sPrize_id);
    }

    public ActiveEventInfo(int i, String str, int i2, String str2, String str3, int i3, String str4, int i4, int i5, String str5, String str6, String str7, int i6, long j, long j2, long j3, int i7, long j4, int i8, boolean z, boolean z2, String str8, String str9, String str10) {
        setIID(i);
        setSTitle(str);
        setIActBeginTime(i2);
        setSPicUrl(str2);
        setSGameName(str3);
        setIGameID(i3);
        setSDigest(str4);
        setIActButtionState(i4);
        setIActiveState(i5);
        setSActiveState(str5);
        setSLinkUrl(str6);
        setSDetailUrl(str7);
        setIActEndTime(i6);
        setLTid(j);
        setLSubid(j2);
        setLPUid(j3);
        setISourceType(i7);
        setISubCnt(j4);
        setIScreenType(i8);
        setBDetailUrlNeedLogin(z);
        setBLinkUrlNeedLogin(z2);
        setSAppIcon(str8);
        setSDes(str9);
        setSPrize_id(str10);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ActiveEventInfo activeEventInfo = (ActiveEventInfo) obj;
        return JceUtil.equals(this.iID, activeEventInfo.iID) && JceUtil.equals(this.sTitle, activeEventInfo.sTitle) && JceUtil.equals(this.iActBeginTime, activeEventInfo.iActBeginTime) && JceUtil.equals(this.sPicUrl, activeEventInfo.sPicUrl) && JceUtil.equals(this.sGameName, activeEventInfo.sGameName) && JceUtil.equals(this.iGameID, activeEventInfo.iGameID) && JceUtil.equals(this.sDigest, activeEventInfo.sDigest) && JceUtil.equals(this.iActButtionState, activeEventInfo.iActButtionState) && JceUtil.equals(this.iActiveState, activeEventInfo.iActiveState) && JceUtil.equals(this.sActiveState, activeEventInfo.sActiveState) && JceUtil.equals(this.sLinkUrl, activeEventInfo.sLinkUrl) && JceUtil.equals(this.sDetailUrl, activeEventInfo.sDetailUrl) && JceUtil.equals(this.iActEndTime, activeEventInfo.iActEndTime) && JceUtil.equals(this.lTid, activeEventInfo.lTid) && JceUtil.equals(this.lSubid, activeEventInfo.lSubid) && JceUtil.equals(this.lPUid, activeEventInfo.lPUid) && JceUtil.equals(this.iSourceType, activeEventInfo.iSourceType) && JceUtil.equals(this.iSubCnt, activeEventInfo.iSubCnt) && JceUtil.equals(this.iScreenType, activeEventInfo.iScreenType) && JceUtil.equals(this.bDetailUrlNeedLogin, activeEventInfo.bDetailUrlNeedLogin) && JceUtil.equals(this.bLinkUrlNeedLogin, activeEventInfo.bLinkUrlNeedLogin) && JceUtil.equals(this.sAppIcon, activeEventInfo.sAppIcon) && JceUtil.equals(this.sDes, activeEventInfo.sDes) && JceUtil.equals(this.sPrize_id, activeEventInfo.sPrize_id);
    }

    public int hashCode() {
        try {
            throw new Exception("Need define key first!");
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
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
        jceOutputStream.write(this.iID, 0);
        String str = this.sTitle;
        if (str != null) {
            jceOutputStream.write(str, 1);
        }
        jceOutputStream.write(this.iActBeginTime, 2);
        String str2 = this.sPicUrl;
        if (str2 != null) {
            jceOutputStream.write(str2, 4);
        }
        String str3 = this.sGameName;
        if (str3 != null) {
            jceOutputStream.write(str3, 5);
        }
        jceOutputStream.write(this.iGameID, 6);
        String str4 = this.sDigest;
        if (str4 != null) {
            jceOutputStream.write(str4, 7);
        }
        jceOutputStream.write(this.iActButtionState, 8);
        jceOutputStream.write(this.iActiveState, 9);
        String str5 = this.sActiveState;
        if (str5 != null) {
            jceOutputStream.write(str5, 10);
        }
        String str6 = this.sLinkUrl;
        if (str6 != null) {
            jceOutputStream.write(str6, 11);
        }
        String str7 = this.sDetailUrl;
        if (str7 != null) {
            jceOutputStream.write(str7, 12);
        }
        jceOutputStream.write(this.iActEndTime, 13);
        jceOutputStream.write(this.lTid, 14);
        jceOutputStream.write(this.lSubid, 15);
        jceOutputStream.write(this.lPUid, 16);
        jceOutputStream.write(this.iSourceType, 17);
        jceOutputStream.write(this.iSubCnt, 18);
        jceOutputStream.write(this.iScreenType, 19);
        jceOutputStream.write(this.bDetailUrlNeedLogin, 20);
        jceOutputStream.write(this.bLinkUrlNeedLogin, 21);
        String str8 = this.sAppIcon;
        if (str8 != null) {
            jceOutputStream.write(str8, 22);
        }
        String str9 = this.sDes;
        if (str9 != null) {
            jceOutputStream.write(str9, 23);
        }
        String str10 = this.sPrize_id;
        if (str10 != null) {
            jceOutputStream.write(str10, 24);
        }
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        setIID(jceInputStream.read(this.iID, 0, false));
        setSTitle(jceInputStream.readString(1, false));
        setIActBeginTime(jceInputStream.read(this.iActBeginTime, 2, false));
        setSPicUrl(jceInputStream.readString(4, false));
        setSGameName(jceInputStream.readString(5, false));
        setIGameID(jceInputStream.read(this.iGameID, 6, false));
        setSDigest(jceInputStream.readString(7, false));
        setIActButtionState(jceInputStream.read(this.iActButtionState, 8, false));
        setIActiveState(jceInputStream.read(this.iActiveState, 9, false));
        setSActiveState(jceInputStream.readString(10, false));
        setSLinkUrl(jceInputStream.readString(11, false));
        setSDetailUrl(jceInputStream.readString(12, false));
        setIActEndTime(jceInputStream.read(this.iActEndTime, 13, false));
        setLTid(jceInputStream.read(this.lTid, 14, false));
        setLSubid(jceInputStream.read(this.lSubid, 15, false));
        setLPUid(jceInputStream.read(this.lPUid, 16, false));
        setISourceType(jceInputStream.read(this.iSourceType, 17, false));
        setISubCnt(jceInputStream.read(this.iSubCnt, 18, false));
        setIScreenType(jceInputStream.read(this.iScreenType, 19, false));
        setBDetailUrlNeedLogin(jceInputStream.read(this.bDetailUrlNeedLogin, 20, false));
        setBLinkUrlNeedLogin(jceInputStream.read(this.bLinkUrlNeedLogin, 21, false));
        setSAppIcon(jceInputStream.readString(22, false));
        setSDes(jceInputStream.readString(23, false));
        setSPrize_id(jceInputStream.readString(24, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display(this.iID, "iID");
        jceDisplayer.display(this.sTitle, "sTitle");
        jceDisplayer.display(this.iActBeginTime, "iActBeginTime");
        jceDisplayer.display(this.sPicUrl, "sPicUrl");
        jceDisplayer.display(this.sGameName, "sGameName");
        jceDisplayer.display(this.iGameID, "iGameID");
        jceDisplayer.display(this.sDigest, "sDigest");
        jceDisplayer.display(this.iActButtionState, "iActButtionState");
        jceDisplayer.display(this.iActiveState, "iActiveState");
        jceDisplayer.display(this.sActiveState, "sActiveState");
        jceDisplayer.display(this.sLinkUrl, "sLinkUrl");
        jceDisplayer.display(this.sDetailUrl, "sDetailUrl");
        jceDisplayer.display(this.iActEndTime, "iActEndTime");
        jceDisplayer.display(this.lTid, "lTid");
        jceDisplayer.display(this.lSubid, "lSubid");
        jceDisplayer.display(this.lPUid, "lPUid");
        jceDisplayer.display(this.iSourceType, "iSourceType");
        jceDisplayer.display(this.iSubCnt, "iSubCnt");
        jceDisplayer.display(this.iScreenType, "iScreenType");
        jceDisplayer.display(this.bDetailUrlNeedLogin, "bDetailUrlNeedLogin");
        jceDisplayer.display(this.bLinkUrlNeedLogin, "bLinkUrlNeedLogin");
        jceDisplayer.display(this.sAppIcon, "sAppIcon");
        jceDisplayer.display(this.sDes, "sDes");
        jceDisplayer.display(this.sPrize_id, "sPrize_id");
    }
}
