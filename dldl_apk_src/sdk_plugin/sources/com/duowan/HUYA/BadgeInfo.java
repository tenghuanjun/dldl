package com.duowan.HUYA;

import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class BadgeInfo extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static PresenterChannelInfo cache_tChannelInfo;
    static FaithInfo cache_tFaithInfo;
    static SuperFansInfo cache_tSuperFansInfo;
    public long lUid = 0;
    public long lBadgeId = 0;
    public String sPresenterNickName = "";
    public String sBadgeName = "";
    public int iBadgeLevel = 0;
    public int iRank = 0;
    public int iScore = 0;
    public int iNextScore = 0;
    public int iQuotaUsed = 0;
    public int iQuota = 0;
    public long lQuotaTS = 0;
    public long lOpenTS = 0;
    public int iVFlag = 0;
    public String sVLogo = "";
    public PresenterChannelInfo tChannelInfo = null;
    public String sPresenterLogo = "";
    public long lVExpiredTS = 0;
    public int iBadgeType = 0;
    public FaithInfo tFaithInfo = null;
    public SuperFansInfo tSuperFansInfo = null;

    public String className() {
        return "HUYA.BadgeInfo";
    }

    public String fullClassName() {
        return "com.duowan.HUYA.BadgeInfo";
    }

    public long getLUid() {
        return this.lUid;
    }

    public void setLUid(long j) {
        this.lUid = j;
    }

    public long getLBadgeId() {
        return this.lBadgeId;
    }

    public void setLBadgeId(long j) {
        this.lBadgeId = j;
    }

    public String getSPresenterNickName() {
        return this.sPresenterNickName;
    }

    public void setSPresenterNickName(String str) {
        this.sPresenterNickName = str;
    }

    public String getSBadgeName() {
        return this.sBadgeName;
    }

    public void setSBadgeName(String str) {
        this.sBadgeName = str;
    }

    public int getIBadgeLevel() {
        return this.iBadgeLevel;
    }

    public void setIBadgeLevel(int i) {
        this.iBadgeLevel = i;
    }

    public int getIRank() {
        return this.iRank;
    }

    public void setIRank(int i) {
        this.iRank = i;
    }

    public int getIScore() {
        return this.iScore;
    }

    public void setIScore(int i) {
        this.iScore = i;
    }

    public int getINextScore() {
        return this.iNextScore;
    }

    public void setINextScore(int i) {
        this.iNextScore = i;
    }

    public int getIQuotaUsed() {
        return this.iQuotaUsed;
    }

    public void setIQuotaUsed(int i) {
        this.iQuotaUsed = i;
    }

    public int getIQuota() {
        return this.iQuota;
    }

    public void setIQuota(int i) {
        this.iQuota = i;
    }

    public long getLQuotaTS() {
        return this.lQuotaTS;
    }

    public void setLQuotaTS(long j) {
        this.lQuotaTS = j;
    }

    public long getLOpenTS() {
        return this.lOpenTS;
    }

    public void setLOpenTS(long j) {
        this.lOpenTS = j;
    }

    public int getIVFlag() {
        return this.iVFlag;
    }

    public void setIVFlag(int i) {
        this.iVFlag = i;
    }

    public String getSVLogo() {
        return this.sVLogo;
    }

    public void setSVLogo(String str) {
        this.sVLogo = str;
    }

    public PresenterChannelInfo getTChannelInfo() {
        return this.tChannelInfo;
    }

    public void setTChannelInfo(PresenterChannelInfo presenterChannelInfo) {
        this.tChannelInfo = presenterChannelInfo;
    }

    public String getSPresenterLogo() {
        return this.sPresenterLogo;
    }

    public void setSPresenterLogo(String str) {
        this.sPresenterLogo = str;
    }

    public long getLVExpiredTS() {
        return this.lVExpiredTS;
    }

    public void setLVExpiredTS(long j) {
        this.lVExpiredTS = j;
    }

    public int getIBadgeType() {
        return this.iBadgeType;
    }

    public void setIBadgeType(int i) {
        this.iBadgeType = i;
    }

    public FaithInfo getTFaithInfo() {
        return this.tFaithInfo;
    }

    public void setTFaithInfo(FaithInfo faithInfo) {
        this.tFaithInfo = faithInfo;
    }

    public SuperFansInfo getTSuperFansInfo() {
        return this.tSuperFansInfo;
    }

    public void setTSuperFansInfo(SuperFansInfo superFansInfo) {
        this.tSuperFansInfo = superFansInfo;
    }

    public BadgeInfo() {
        setLUid(0L);
        setLBadgeId(this.lBadgeId);
        setSPresenterNickName(this.sPresenterNickName);
        setSBadgeName(this.sBadgeName);
        setIBadgeLevel(this.iBadgeLevel);
        setIRank(this.iRank);
        setIScore(this.iScore);
        setINextScore(this.iNextScore);
        setIQuotaUsed(this.iQuotaUsed);
        setIQuota(this.iQuota);
        setLQuotaTS(this.lQuotaTS);
        setLOpenTS(this.lOpenTS);
        setIVFlag(this.iVFlag);
        setSVLogo(this.sVLogo);
        setTChannelInfo(this.tChannelInfo);
        setSPresenterLogo(this.sPresenterLogo);
        setLVExpiredTS(this.lVExpiredTS);
        setIBadgeType(this.iBadgeType);
        setTFaithInfo(this.tFaithInfo);
        setTSuperFansInfo(this.tSuperFansInfo);
    }

    public BadgeInfo(long j, long j2, String str, String str2, int i, int i2, int i3, int i4, int i5, int i6, long j3, long j4, int i7, String str3, PresenterChannelInfo presenterChannelInfo, String str4, long j5, int i8, FaithInfo faithInfo, SuperFansInfo superFansInfo) {
        setLUid(j);
        setLBadgeId(j2);
        setSPresenterNickName(str);
        setSBadgeName(str2);
        setIBadgeLevel(i);
        setIRank(i2);
        setIScore(i3);
        setINextScore(i4);
        setIQuotaUsed(i5);
        setIQuota(i6);
        setLQuotaTS(j3);
        setLOpenTS(j4);
        setIVFlag(i7);
        setSVLogo(str3);
        setTChannelInfo(presenterChannelInfo);
        setSPresenterLogo(str4);
        setLVExpiredTS(j5);
        setIBadgeType(i8);
        setTFaithInfo(faithInfo);
        setTSuperFansInfo(superFansInfo);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        BadgeInfo badgeInfo = (BadgeInfo) obj;
        return JceUtil.equals(this.lUid, badgeInfo.lUid) && JceUtil.equals(this.lBadgeId, badgeInfo.lBadgeId) && JceUtil.equals(this.sPresenterNickName, badgeInfo.sPresenterNickName) && JceUtil.equals(this.sBadgeName, badgeInfo.sBadgeName) && JceUtil.equals(this.iBadgeLevel, badgeInfo.iBadgeLevel) && JceUtil.equals(this.iRank, badgeInfo.iRank) && JceUtil.equals(this.iScore, badgeInfo.iScore) && JceUtil.equals(this.iNextScore, badgeInfo.iNextScore) && JceUtil.equals(this.iQuotaUsed, badgeInfo.iQuotaUsed) && JceUtil.equals(this.iQuota, badgeInfo.iQuota) && JceUtil.equals(this.lQuotaTS, badgeInfo.lQuotaTS) && JceUtil.equals(this.lOpenTS, badgeInfo.lOpenTS) && JceUtil.equals(this.iVFlag, badgeInfo.iVFlag) && JceUtil.equals(this.sVLogo, badgeInfo.sVLogo) && JceUtil.equals(this.tChannelInfo, badgeInfo.tChannelInfo) && JceUtil.equals(this.sPresenterLogo, badgeInfo.sPresenterLogo) && JceUtil.equals(this.lVExpiredTS, badgeInfo.lVExpiredTS) && JceUtil.equals(this.iBadgeType, badgeInfo.iBadgeType) && JceUtil.equals(this.tFaithInfo, badgeInfo.tFaithInfo) && JceUtil.equals(this.tSuperFansInfo, badgeInfo.tSuperFansInfo);
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
        jceOutputStream.write(this.lUid, 0);
        jceOutputStream.write(this.lBadgeId, 1);
        String str = this.sPresenterNickName;
        if (str != null) {
            jceOutputStream.write(str, 2);
        }
        String str2 = this.sBadgeName;
        if (str2 != null) {
            jceOutputStream.write(str2, 3);
        }
        jceOutputStream.write(this.iBadgeLevel, 4);
        jceOutputStream.write(this.iRank, 5);
        jceOutputStream.write(this.iScore, 6);
        jceOutputStream.write(this.iNextScore, 7);
        jceOutputStream.write(this.iQuotaUsed, 8);
        jceOutputStream.write(this.iQuota, 9);
        jceOutputStream.write(this.lQuotaTS, 10);
        jceOutputStream.write(this.lOpenTS, 11);
        jceOutputStream.write(this.iVFlag, 12);
        String str3 = this.sVLogo;
        if (str3 != null) {
            jceOutputStream.write(str3, 13);
        }
        PresenterChannelInfo presenterChannelInfo = this.tChannelInfo;
        if (presenterChannelInfo != null) {
            jceOutputStream.write((JceStruct) presenterChannelInfo, 14);
        }
        String str4 = this.sPresenterLogo;
        if (str4 != null) {
            jceOutputStream.write(str4, 15);
        }
        jceOutputStream.write(this.lVExpiredTS, 16);
        jceOutputStream.write(this.iBadgeType, 17);
        FaithInfo faithInfo = this.tFaithInfo;
        if (faithInfo != null) {
            jceOutputStream.write((JceStruct) faithInfo, 18);
        }
        SuperFansInfo superFansInfo = this.tSuperFansInfo;
        if (superFansInfo != null) {
            jceOutputStream.write((JceStruct) superFansInfo, 19);
        }
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        setLUid(jceInputStream.read(this.lUid, 0, false));
        setLBadgeId(jceInputStream.read(this.lBadgeId, 1, false));
        setSPresenterNickName(jceInputStream.readString(2, false));
        setSBadgeName(jceInputStream.readString(3, false));
        setIBadgeLevel(jceInputStream.read(this.iBadgeLevel, 4, false));
        setIRank(jceInputStream.read(this.iRank, 5, false));
        setIScore(jceInputStream.read(this.iScore, 6, false));
        setINextScore(jceInputStream.read(this.iNextScore, 7, false));
        setIQuotaUsed(jceInputStream.read(this.iQuotaUsed, 8, false));
        setIQuota(jceInputStream.read(this.iQuota, 9, false));
        setLQuotaTS(jceInputStream.read(this.lQuotaTS, 10, false));
        setLOpenTS(jceInputStream.read(this.lOpenTS, 11, false));
        setIVFlag(jceInputStream.read(this.iVFlag, 12, false));
        setSVLogo(jceInputStream.readString(13, false));
        if (cache_tChannelInfo == null) {
            cache_tChannelInfo = new PresenterChannelInfo();
        }
        setTChannelInfo((PresenterChannelInfo) jceInputStream.read((JceStruct) cache_tChannelInfo, 14, false));
        setSPresenterLogo(jceInputStream.readString(15, false));
        setLVExpiredTS(jceInputStream.read(this.lVExpiredTS, 16, false));
        setIBadgeType(jceInputStream.read(this.iBadgeType, 17, false));
        if (cache_tFaithInfo == null) {
            cache_tFaithInfo = new FaithInfo();
        }
        setTFaithInfo((FaithInfo) jceInputStream.read((JceStruct) cache_tFaithInfo, 18, false));
        if (cache_tSuperFansInfo == null) {
            cache_tSuperFansInfo = new SuperFansInfo();
        }
        setTSuperFansInfo((SuperFansInfo) jceInputStream.read((JceStruct) cache_tSuperFansInfo, 19, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display(this.lUid, "lUid");
        jceDisplayer.display(this.lBadgeId, "lBadgeId");
        jceDisplayer.display(this.sPresenterNickName, "sPresenterNickName");
        jceDisplayer.display(this.sBadgeName, "sBadgeName");
        jceDisplayer.display(this.iBadgeLevel, "iBadgeLevel");
        jceDisplayer.display(this.iRank, "iRank");
        jceDisplayer.display(this.iScore, "iScore");
        jceDisplayer.display(this.iNextScore, "iNextScore");
        jceDisplayer.display(this.iQuotaUsed, "iQuotaUsed");
        jceDisplayer.display(this.iQuota, "iQuota");
        jceDisplayer.display(this.lQuotaTS, "lQuotaTS");
        jceDisplayer.display(this.lOpenTS, "lOpenTS");
        jceDisplayer.display(this.iVFlag, "iVFlag");
        jceDisplayer.display(this.sVLogo, "sVLogo");
        jceDisplayer.display((JceStruct) this.tChannelInfo, "tChannelInfo");
        jceDisplayer.display(this.sPresenterLogo, "sPresenterLogo");
        jceDisplayer.display(this.lVExpiredTS, "lVExpiredTS");
        jceDisplayer.display(this.iBadgeType, "iBadgeType");
        jceDisplayer.display((JceStruct) this.tFaithInfo, "tFaithInfo");
        jceDisplayer.display((JceStruct) this.tSuperFansInfo, "tSuperFansInfo");
    }
}
