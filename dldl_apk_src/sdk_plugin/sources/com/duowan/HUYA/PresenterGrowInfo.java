package com.duowan.HUYA;

import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class PresenterGrowInfo extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public long lWeeklyExp = 0;
    public long lWeeklyIncExp = 0;
    public int iRank = 0;

    public String className() {
        return "HUYA.PresenterGrowInfo";
    }

    public String fullClassName() {
        return "com.duowan.HUYA.PresenterGrowInfo";
    }

    public long getLWeeklyExp() {
        return this.lWeeklyExp;
    }

    public void setLWeeklyExp(long j) {
        this.lWeeklyExp = j;
    }

    public long getLWeeklyIncExp() {
        return this.lWeeklyIncExp;
    }

    public void setLWeeklyIncExp(long j) {
        this.lWeeklyIncExp = j;
    }

    public int getIRank() {
        return this.iRank;
    }

    public void setIRank(int i) {
        this.iRank = i;
    }

    public PresenterGrowInfo() {
        setLWeeklyExp(0L);
        setLWeeklyIncExp(this.lWeeklyIncExp);
        setIRank(this.iRank);
    }

    public PresenterGrowInfo(long j, long j2, int i) {
        setLWeeklyExp(j);
        setLWeeklyIncExp(j2);
        setIRank(i);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        PresenterGrowInfo presenterGrowInfo = (PresenterGrowInfo) obj;
        return JceUtil.equals(this.lWeeklyExp, presenterGrowInfo.lWeeklyExp) && JceUtil.equals(this.lWeeklyIncExp, presenterGrowInfo.lWeeklyIncExp) && JceUtil.equals(this.iRank, presenterGrowInfo.iRank);
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
        jceOutputStream.write(this.lWeeklyExp, 0);
        jceOutputStream.write(this.lWeeklyIncExp, 1);
        jceOutputStream.write(this.iRank, 2);
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        setLWeeklyExp(jceInputStream.read(this.lWeeklyExp, 0, false));
        setLWeeklyIncExp(jceInputStream.read(this.lWeeklyIncExp, 1, false));
        setIRank(jceInputStream.read(this.iRank, 2, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display(this.lWeeklyExp, "lWeeklyExp");
        jceDisplayer.display(this.lWeeklyIncExp, "lWeeklyIncExp");
        jceDisplayer.display(this.iRank, "iRank");
    }
}
