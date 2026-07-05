package com.duowan.HUYA;

import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;
import java.util.Arrays;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class NobleInfo extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static NobleLevelAttr cache_tLevelAttr;
    static NoblePetAttr cache_tPetAttr;
    public long lUid = 0;
    public long lPid = 0;
    public long lValidDate = 0;
    public String sNobleName = "";
    public int iNobleLevel = 0;
    public int iNoblePet = 0;
    public int iNobleStatus = 0;
    public int iNobleType = 0;
    public int iRemainDays = 0;
    public NobleLevelAttr tLevelAttr = null;
    public NoblePetAttr tPetAttr = null;
    public long lOpenTime = 0;

    public String className() {
        return "HUYA.NobleInfo";
    }

    public String fullClassName() {
        return "com.duowan.HUYA.NobleInfo";
    }

    public long getLUid() {
        return this.lUid;
    }

    public void setLUid(long j) {
        this.lUid = j;
    }

    public long getLPid() {
        return this.lPid;
    }

    public void setLPid(long j) {
        this.lPid = j;
    }

    public long getLValidDate() {
        return this.lValidDate;
    }

    public void setLValidDate(long j) {
        this.lValidDate = j;
    }

    public String getSNobleName() {
        return this.sNobleName;
    }

    public void setSNobleName(String str) {
        this.sNobleName = str;
    }

    public int getINobleLevel() {
        return this.iNobleLevel;
    }

    public void setINobleLevel(int i) {
        this.iNobleLevel = i;
    }

    public int getINoblePet() {
        return this.iNoblePet;
    }

    public void setINoblePet(int i) {
        this.iNoblePet = i;
    }

    public int getINobleStatus() {
        return this.iNobleStatus;
    }

    public void setINobleStatus(int i) {
        this.iNobleStatus = i;
    }

    public int getINobleType() {
        return this.iNobleType;
    }

    public void setINobleType(int i) {
        this.iNobleType = i;
    }

    public int getIRemainDays() {
        return this.iRemainDays;
    }

    public void setIRemainDays(int i) {
        this.iRemainDays = i;
    }

    public NobleLevelAttr getTLevelAttr() {
        return this.tLevelAttr;
    }

    public void setTLevelAttr(NobleLevelAttr nobleLevelAttr) {
        this.tLevelAttr = nobleLevelAttr;
    }

    public NoblePetAttr getTPetAttr() {
        return this.tPetAttr;
    }

    public void setTPetAttr(NoblePetAttr noblePetAttr) {
        this.tPetAttr = noblePetAttr;
    }

    public long getLOpenTime() {
        return this.lOpenTime;
    }

    public void setLOpenTime(long j) {
        this.lOpenTime = j;
    }

    public NobleInfo() {
        setLUid(0L);
        setLPid(this.lPid);
        setLValidDate(this.lValidDate);
        setSNobleName(this.sNobleName);
        setINobleLevel(this.iNobleLevel);
        setINoblePet(this.iNoblePet);
        setINobleStatus(this.iNobleStatus);
        setINobleType(this.iNobleType);
        setIRemainDays(this.iRemainDays);
        setTLevelAttr(this.tLevelAttr);
        setTPetAttr(this.tPetAttr);
        setLOpenTime(this.lOpenTime);
    }

    public NobleInfo(long j, long j2, long j3, String str, int i, int i2, int i3, int i4, int i5, NobleLevelAttr nobleLevelAttr, NoblePetAttr noblePetAttr, long j4) {
        setLUid(j);
        setLPid(j2);
        setLValidDate(j3);
        setSNobleName(str);
        setINobleLevel(i);
        setINoblePet(i2);
        setINobleStatus(i3);
        setINobleType(i4);
        setIRemainDays(i5);
        setTLevelAttr(nobleLevelAttr);
        setTPetAttr(noblePetAttr);
        setLOpenTime(j4);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        NobleInfo nobleInfo = (NobleInfo) obj;
        return JceUtil.equals(this.lUid, nobleInfo.lUid) && JceUtil.equals(this.lPid, nobleInfo.lPid) && JceUtil.equals(this.lValidDate, nobleInfo.lValidDate) && JceUtil.equals(this.sNobleName, nobleInfo.sNobleName) && JceUtil.equals(this.iNobleLevel, nobleInfo.iNobleLevel) && JceUtil.equals(this.iNoblePet, nobleInfo.iNoblePet) && JceUtil.equals(this.iNobleStatus, nobleInfo.iNobleStatus) && JceUtil.equals(this.iNobleType, nobleInfo.iNobleType) && JceUtil.equals(this.iRemainDays, nobleInfo.iRemainDays) && JceUtil.equals(this.tLevelAttr, nobleInfo.tLevelAttr) && JceUtil.equals(this.tPetAttr, nobleInfo.tPetAttr) && JceUtil.equals(this.lOpenTime, nobleInfo.lOpenTime);
    }

    public int hashCode() {
        return Arrays.hashCode(new int[]{JceUtil.hashCode(this.lUid), JceUtil.hashCode(this.lPid), JceUtil.hashCode(this.lValidDate), JceUtil.hashCode(this.sNobleName), JceUtil.hashCode(this.iNobleLevel), JceUtil.hashCode(this.iNoblePet), JceUtil.hashCode(this.iNobleStatus), JceUtil.hashCode(this.iNobleType), JceUtil.hashCode(this.iRemainDays), JceUtil.hashCode(this.tLevelAttr), JceUtil.hashCode(this.tPetAttr), JceUtil.hashCode(this.lOpenTime)});
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
        jceOutputStream.write(this.lPid, 1);
        jceOutputStream.write(this.lValidDate, 2);
        String str = this.sNobleName;
        if (str != null) {
            jceOutputStream.write(str, 3);
        }
        jceOutputStream.write(this.iNobleLevel, 4);
        jceOutputStream.write(this.iNoblePet, 5);
        jceOutputStream.write(this.iNobleStatus, 6);
        jceOutputStream.write(this.iNobleType, 7);
        jceOutputStream.write(this.iRemainDays, 8);
        NobleLevelAttr nobleLevelAttr = this.tLevelAttr;
        if (nobleLevelAttr != null) {
            jceOutputStream.write((JceStruct) nobleLevelAttr, 9);
        }
        NoblePetAttr noblePetAttr = this.tPetAttr;
        if (noblePetAttr != null) {
            jceOutputStream.write((JceStruct) noblePetAttr, 10);
        }
        jceOutputStream.write(this.lOpenTime, 11);
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        setLUid(jceInputStream.read(this.lUid, 0, false));
        setLPid(jceInputStream.read(this.lPid, 1, false));
        setLValidDate(jceInputStream.read(this.lValidDate, 2, false));
        setSNobleName(jceInputStream.readString(3, false));
        setINobleLevel(jceInputStream.read(this.iNobleLevel, 4, false));
        setINoblePet(jceInputStream.read(this.iNoblePet, 5, false));
        setINobleStatus(jceInputStream.read(this.iNobleStatus, 6, false));
        setINobleType(jceInputStream.read(this.iNobleType, 7, false));
        setIRemainDays(jceInputStream.read(this.iRemainDays, 8, false));
        if (cache_tLevelAttr == null) {
            cache_tLevelAttr = new NobleLevelAttr();
        }
        setTLevelAttr((NobleLevelAttr) jceInputStream.read((JceStruct) cache_tLevelAttr, 9, false));
        if (cache_tPetAttr == null) {
            cache_tPetAttr = new NoblePetAttr();
        }
        setTPetAttr((NoblePetAttr) jceInputStream.read((JceStruct) cache_tPetAttr, 10, false));
        setLOpenTime(jceInputStream.read(this.lOpenTime, 11, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display(this.lUid, "lUid");
        jceDisplayer.display(this.lPid, "lPid");
        jceDisplayer.display(this.lValidDate, "lValidDate");
        jceDisplayer.display(this.sNobleName, "sNobleName");
        jceDisplayer.display(this.iNobleLevel, "iNobleLevel");
        jceDisplayer.display(this.iNoblePet, "iNoblePet");
        jceDisplayer.display(this.iNobleStatus, "iNobleStatus");
        jceDisplayer.display(this.iNobleType, "iNobleType");
        jceDisplayer.display(this.iRemainDays, "iRemainDays");
        jceDisplayer.display((JceStruct) this.tLevelAttr, "tLevelAttr");
        jceDisplayer.display((JceStruct) this.tPetAttr, "tPetAttr");
        jceDisplayer.display(this.lOpenTime, "lOpenTime");
    }
}
