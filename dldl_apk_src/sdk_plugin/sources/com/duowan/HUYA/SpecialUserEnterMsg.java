package com.duowan.HUYA;

import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class SpecialUserEnterMsg extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public long lUid = 0;
    public long lPid = 0;
    public String sNickName = "";
    public int iWeekRank = 0;
    public int iGuardLevel = 0;
    public int iNobleLevel = 0;
    public double dDistance = 0.0d;
    public String sLocation = "";
    public int iTraceSource = 0;
    public String sSharePlatform = "";
    public String sExtraMsg = "";

    public String className() {
        return "HUYA.SpecialUserEnterMsg";
    }

    public String fullClassName() {
        return "com.duowan.HUYA.SpecialUserEnterMsg";
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

    public String getSNickName() {
        return this.sNickName;
    }

    public void setSNickName(String str) {
        this.sNickName = str;
    }

    public int getIWeekRank() {
        return this.iWeekRank;
    }

    public void setIWeekRank(int i) {
        this.iWeekRank = i;
    }

    public int getIGuardLevel() {
        return this.iGuardLevel;
    }

    public void setIGuardLevel(int i) {
        this.iGuardLevel = i;
    }

    public int getINobleLevel() {
        return this.iNobleLevel;
    }

    public void setINobleLevel(int i) {
        this.iNobleLevel = i;
    }

    public double getDDistance() {
        return this.dDistance;
    }

    public void setDDistance(double d) {
        this.dDistance = d;
    }

    public String getSLocation() {
        return this.sLocation;
    }

    public void setSLocation(String str) {
        this.sLocation = str;
    }

    public int getITraceSource() {
        return this.iTraceSource;
    }

    public void setITraceSource(int i) {
        this.iTraceSource = i;
    }

    public String getSSharePlatform() {
        return this.sSharePlatform;
    }

    public void setSSharePlatform(String str) {
        this.sSharePlatform = str;
    }

    public String getSExtraMsg() {
        return this.sExtraMsg;
    }

    public void setSExtraMsg(String str) {
        this.sExtraMsg = str;
    }

    public SpecialUserEnterMsg() {
        setLUid(0L);
        setLPid(this.lPid);
        setSNickName(this.sNickName);
        setIWeekRank(this.iWeekRank);
        setIGuardLevel(this.iGuardLevel);
        setINobleLevel(this.iNobleLevel);
        setDDistance(this.dDistance);
        setSLocation(this.sLocation);
        setITraceSource(this.iTraceSource);
        setSSharePlatform(this.sSharePlatform);
        setSExtraMsg(this.sExtraMsg);
    }

    public SpecialUserEnterMsg(long j, long j2, String str, int i, int i2, int i3, double d, String str2, int i4, String str3, String str4) {
        setLUid(j);
        setLPid(j2);
        setSNickName(str);
        setIWeekRank(i);
        setIGuardLevel(i2);
        setINobleLevel(i3);
        setDDistance(d);
        setSLocation(str2);
        setITraceSource(i4);
        setSSharePlatform(str3);
        setSExtraMsg(str4);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        SpecialUserEnterMsg specialUserEnterMsg = (SpecialUserEnterMsg) obj;
        return JceUtil.equals(this.lUid, specialUserEnterMsg.lUid) && JceUtil.equals(this.lPid, specialUserEnterMsg.lPid) && JceUtil.equals(this.sNickName, specialUserEnterMsg.sNickName) && JceUtil.equals(this.iWeekRank, specialUserEnterMsg.iWeekRank) && JceUtil.equals(this.iGuardLevel, specialUserEnterMsg.iGuardLevel) && JceUtil.equals(this.iNobleLevel, specialUserEnterMsg.iNobleLevel) && JceUtil.equals(this.dDistance, specialUserEnterMsg.dDistance) && JceUtil.equals(this.sLocation, specialUserEnterMsg.sLocation) && JceUtil.equals(this.iTraceSource, specialUserEnterMsg.iTraceSource) && JceUtil.equals(this.sSharePlatform, specialUserEnterMsg.sSharePlatform) && JceUtil.equals(this.sExtraMsg, specialUserEnterMsg.sExtraMsg);
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
        jceOutputStream.write(this.lPid, 1);
        String str = this.sNickName;
        if (str != null) {
            jceOutputStream.write(str, 2);
        }
        jceOutputStream.write(this.iWeekRank, 3);
        jceOutputStream.write(this.iGuardLevel, 4);
        jceOutputStream.write(this.iNobleLevel, 5);
        jceOutputStream.write(this.dDistance, 6);
        String str2 = this.sLocation;
        if (str2 != null) {
            jceOutputStream.write(str2, 7);
        }
        jceOutputStream.write(this.iTraceSource, 8);
        String str3 = this.sSharePlatform;
        if (str3 != null) {
            jceOutputStream.write(str3, 9);
        }
        String str4 = this.sExtraMsg;
        if (str4 != null) {
            jceOutputStream.write(str4, 10);
        }
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        setLUid(jceInputStream.read(this.lUid, 0, false));
        setLPid(jceInputStream.read(this.lPid, 1, false));
        setSNickName(jceInputStream.readString(2, false));
        setIWeekRank(jceInputStream.read(this.iWeekRank, 3, false));
        setIGuardLevel(jceInputStream.read(this.iGuardLevel, 4, false));
        setINobleLevel(jceInputStream.read(this.iNobleLevel, 5, false));
        setDDistance(jceInputStream.read(this.dDistance, 6, false));
        setSLocation(jceInputStream.readString(7, false));
        setITraceSource(jceInputStream.read(this.iTraceSource, 8, false));
        setSSharePlatform(jceInputStream.readString(9, false));
        setSExtraMsg(jceInputStream.readString(10, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display(this.lUid, "lUid");
        jceDisplayer.display(this.lPid, "lPid");
        jceDisplayer.display(this.sNickName, "sNickName");
        jceDisplayer.display(this.iWeekRank, "iWeekRank");
        jceDisplayer.display(this.iGuardLevel, "iGuardLevel");
        jceDisplayer.display(this.iNobleLevel, "iNobleLevel");
        jceDisplayer.display(this.dDistance, "dDistance");
        jceDisplayer.display(this.sLocation, "sLocation");
        jceDisplayer.display(this.iTraceSource, "iTraceSource");
        jceDisplayer.display(this.sSharePlatform, "sSharePlatform");
        jceDisplayer.display(this.sExtraMsg, "sExtraMsg");
    }
}
