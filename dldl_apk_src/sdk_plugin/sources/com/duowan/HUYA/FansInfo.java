package com.duowan.HUYA;

import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class FansInfo extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public long lUid = 0;
    public long lBadgeId = 0;
    public int iBadgeLevel = 0;
    public int iScore = 0;
    public int iVFlag = 0;
    public int iBadgeType = 0;
    public int iSFFlag = 0;

    public String className() {
        return "HUYA.FansInfo";
    }

    public String fullClassName() {
        return "com.duowan.HUYA.FansInfo";
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

    public int getIBadgeLevel() {
        return this.iBadgeLevel;
    }

    public void setIBadgeLevel(int i) {
        this.iBadgeLevel = i;
    }

    public int getIScore() {
        return this.iScore;
    }

    public void setIScore(int i) {
        this.iScore = i;
    }

    public int getIVFlag() {
        return this.iVFlag;
    }

    public void setIVFlag(int i) {
        this.iVFlag = i;
    }

    public int getIBadgeType() {
        return this.iBadgeType;
    }

    public void setIBadgeType(int i) {
        this.iBadgeType = i;
    }

    public int getISFFlag() {
        return this.iSFFlag;
    }

    public void setISFFlag(int i) {
        this.iSFFlag = i;
    }

    public FansInfo() {
        setLUid(0L);
        setLBadgeId(this.lBadgeId);
        setIBadgeLevel(this.iBadgeLevel);
        setIScore(this.iScore);
        setIVFlag(this.iVFlag);
        setIBadgeType(this.iBadgeType);
        setISFFlag(this.iSFFlag);
    }

    public FansInfo(long j, long j2, int i, int i2, int i3, int i4, int i5) {
        setLUid(j);
        setLBadgeId(j2);
        setIBadgeLevel(i);
        setIScore(i2);
        setIVFlag(i3);
        setIBadgeType(i4);
        setISFFlag(i5);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        FansInfo fansInfo = (FansInfo) obj;
        return JceUtil.equals(this.lUid, fansInfo.lUid) && JceUtil.equals(this.lBadgeId, fansInfo.lBadgeId) && JceUtil.equals(this.iBadgeLevel, fansInfo.iBadgeLevel) && JceUtil.equals(this.iScore, fansInfo.iScore) && JceUtil.equals(this.iVFlag, fansInfo.iVFlag) && JceUtil.equals(this.iBadgeType, fansInfo.iBadgeType) && JceUtil.equals(this.iSFFlag, fansInfo.iSFFlag);
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
        jceOutputStream.write(this.iBadgeLevel, 2);
        jceOutputStream.write(this.iScore, 3);
        jceOutputStream.write(this.iVFlag, 4);
        jceOutputStream.write(this.iBadgeType, 5);
        jceOutputStream.write(this.iSFFlag, 6);
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        setLUid(jceInputStream.read(this.lUid, 0, false));
        setLBadgeId(jceInputStream.read(this.lBadgeId, 1, false));
        setIBadgeLevel(jceInputStream.read(this.iBadgeLevel, 2, false));
        setIScore(jceInputStream.read(this.iScore, 3, false));
        setIVFlag(jceInputStream.read(this.iVFlag, 4, false));
        setIBadgeType(jceInputStream.read(this.iBadgeType, 5, false));
        setISFFlag(jceInputStream.read(this.iSFFlag, 6, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display(this.lUid, "lUid");
        jceDisplayer.display(this.lBadgeId, "lBadgeId");
        jceDisplayer.display(this.iBadgeLevel, "iBadgeLevel");
        jceDisplayer.display(this.iScore, "iScore");
        jceDisplayer.display(this.iVFlag, "iVFlag");
        jceDisplayer.display(this.iBadgeType, "iBadgeType");
        jceDisplayer.display(this.iSFFlag, "iSFFlag");
    }
}
