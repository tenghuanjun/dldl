package com.duowan.HUYA;

import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class GuardInfo extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public long lUid = 0;
    public long lPid = 0;
    public int iGuardLevel = 0;
    public long lEndTime = 0;

    public String className() {
        return "HUYA.GuardInfo";
    }

    public String fullClassName() {
        return "com.duowan.HUYA.GuardInfo";
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

    public int getIGuardLevel() {
        return this.iGuardLevel;
    }

    public void setIGuardLevel(int i) {
        this.iGuardLevel = i;
    }

    public long getLEndTime() {
        return this.lEndTime;
    }

    public void setLEndTime(long j) {
        this.lEndTime = j;
    }

    public GuardInfo() {
        setLUid(0L);
        setLPid(this.lPid);
        setIGuardLevel(this.iGuardLevel);
        setLEndTime(this.lEndTime);
    }

    public GuardInfo(long j, long j2, int i, long j3) {
        setLUid(j);
        setLPid(j2);
        setIGuardLevel(i);
        setLEndTime(j3);
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        GuardInfo guardInfo = (GuardInfo) obj;
        return JceUtil.equals(this.lUid, guardInfo.lUid) && JceUtil.equals(this.lPid, guardInfo.lPid) && JceUtil.equals(this.iGuardLevel, guardInfo.iGuardLevel) && JceUtil.equals(this.lEndTime, guardInfo.lEndTime);
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
        jceOutputStream.write(this.iGuardLevel, 2);
        jceOutputStream.write(this.lEndTime, 3);
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        setLUid(jceInputStream.read(this.lUid, 0, false));
        setLPid(jceInputStream.read(this.lPid, 1, false));
        setIGuardLevel(jceInputStream.read(this.iGuardLevel, 2, false));
        setLEndTime(jceInputStream.read(this.lEndTime, 3, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display(this.lUid, "uid");
        jceDisplayer.display(this.lPid, "lPid");
        jceDisplayer.display(this.iGuardLevel, "iGuardLevel");
        jceDisplayer.display(this.lEndTime, "lEndTime");
    }
}
